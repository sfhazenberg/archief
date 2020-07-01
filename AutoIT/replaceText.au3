#include <Date.au3>
#include <File.au3>
#include <Misc.au3>
#include <Word.au3>
#include <Array.au3>
#include <GuiEdit.au3>
#include <EditConstants.au3>
#include <GUIConstantsEx.au3>
#include <MsgBoxConstants.au3>
#include <WindowsConstants.au3>
#include <ProgressConstants.au3>

; #INDEX# =======================================================================================================================
; Title .........: aanpassenTAV
; AutoIt Version : SciTE-Lite 3.5.4
; Description ...: Script dat de tweede regel van adresgegevens op v/e document opzoekt en vervangt met input v/d gebruiker
; Author(s) .....: Steven Hazenberg
; ===============================================================================================================================

; #CURRENT# =====================================================================================================================
; _conversion
; _getFileName
; _updateLog
; ===============================================================================================================================

Local $mainGUI = GUICreate("Samenvoegen Tav's", 400, 600)

Local $textboxShowSource = GUICtrlCreateInput("Bronbestand", 20, 30, 250, 20, $ES_READONLY)		;tekstvak bronbestand
Local $textboxShowCSV = GUICtrlCreateInput("CSV-bestand", 20, 70, 250, 20, $ES_READONLY)		;tekstvak CSV-bestand
Local $textboxRepText = GUICtrlCreateInput("<tekst>", 20, 110, 250, 20)	;inputbox met de te vervangen tekst. Kan in de GUI worden aangepast.
Local $labelRepText = GUICtrlCreateLabel("wordt vervangen", 280, 113, 100, 40)					;statische tekst naast $repText

Local $iStart = GUICtrlCreateDummy()	;nodig om knoppen uit te kunnen zetten 1/2
Local $btnSelectSource = GUICtrlCreateButton("Kies bronbestand", 280, 25, 100, 30, $ES_CENTER)	;knop selecteren bronbestand
Local $btnSelectCSV = GUICtrlCreateButton("Kies CSV-bestand", 280, 65, 100, 30, $ES_CENTER)		;knop selecteren CSV-bestand
Local $btnStartMerge = GUICtrlCreateButton("Begin handeling", 125, 140, 150, 30, $ES_CENTER)	;knop begin handeling
Local $btnCloseWindow = GUICtrlCreateButton("Sluiten", 280, 560, 100, 30, $ES_CENTER)			;knop sluiten
Local $iEnd = GUICtrlCreateDummy()		;nodig om knoppen uit te kunnen zetten 2/2

Local $progressBar = GUICtrlCreateProgress(20, 175, 360, 10)	; todo-w
Local $hLog = _GUICtrlEdit_Create($mainGUI, "", 20, 190, 360, 360, BitOR($WS_HSCROLL, $WS_VSCROLL, $ES_MULTILINE, $ES_WANTRETURN, $ES_READONLY))

GUISetState(@SW_SHOW)

While True
   Switch GUIGetMsg()
	  Case $btnSelectSource
		 Local $chosenFile = FileOpenDialog("Open het te overschrijven bestand", @ScriptDir, "Word docx file (*.docx)", 1)
		 getFileName($textboxShowSource, $chosenFile)

	  Case $btnSelectCSV
		 Local $oRep = FileOpenDialog("Open het bestand met de correcte data", @ScriptDir, "Comma seperated file (*.csv)", 1)
		 getFileName($textboxShowCSV, $oRep)

	  Case $btnStartMerge
		 Local $repText = GUICtrlRead($textboxRepText)

		 If Not IsDeclared("chosenFile") Then	;checkt of bestanden zijn geselecteerd
			MsgBox($MB_ICONWARNING, "Fout", "Geen bronbestand geselecteerd")
		 ElseIf Not IsDeclared("oRep") Then
			MsgBox($MB_ICONWARNING, "Fout", "Geen CSV-bestand geselecteerd")
		 Else	;bestanden geselecteerd
			For $i = $iStart To $iEnd	;zet alle knoppen uit
			   GUICtrlSetState($i, $GUI_DISABLE)
			Next

			FileCopy($chosenFile, @ScriptDir & "\backup")	;maakt back-up van bronbestand
			updateLog("Back-up gemaakt in " & @ScriptDir & "\backup." & @CRLF & "Document wordt geladen, een moment geduld" & @CRLF, $hLog)
			_conversion($chosenFile, $oRep, $repText)
		 EndIf

	  Case $GUI_EVENT_CLOSE, $btnCloseWindow
		 Exit
   EndSwitch
WEnd

; #FUNCTION# ====================================================================================================================
Func _conversion($chosenFile, $oRep, $repText)
   Local $oRange, $oDoc = _Word_Create(False)
   If @error <> 0 Then Exit MsgBox($MB_SYSTEMMODAL, "Fout", "Fout bij het creëren van een Word applicatie object." & @CRLF & "Handeling wordt stopgezet.")

   Local $wordFile = _Word_DocOpen($oDoc, $chosenFile)
   If @error <> 0 Then Exit MsgBox($MB_SYSTEMMODAL, "Fout", "Bestand kan niet worden geopend." & @CRLF & "Handeling wordt stopgezet.")

   Local $repArray = FileReadToArray($oRep)
   Local $repArraySize = UBound($repArray)-1
   FileClose($oRep)

   ;filtert tijd en datum op bepaalde karakters die Windows niet accepteert voor een bestandsnaam. Pakt dan tijd en datum voor naam van logbestand
   Local $time = _NowTime(4)
   Local $timeHours = StringLeft($time, 2)
   Local $timeMinutes = StringRight($time, 2)
   Local $date = _NowDate()
   Local $currentDate = StringReplace($date, "/", "-")
   Local $fileName = "logConversie_" & $currentDate & "_" & $timeHours & "-" & $timeMinutes & ".txt"
   Local $logFile = FileOpen($fileName, 2)

   For $i = 0 To $repArraySize	; het magische gedeelte
	  Local $recipient = (StringSplit($repArray[$i], ","))[1]
	  Local $replacee = (StringSplit($repArray[$i], ","))[2]
	  updateLog("Te zoeken tekst: " & $recipient & @CRLF & "Het vervangende woord: " & $replacee & @CRLF & "--------------------" & @CRLF, $hLog)

	  $oRangeFound = _Word_DocFind($wordFile, $recipient)
	  If @error = 0 Then
		 $oRange = _Word_DocRangeSet($wordFile, $oRangeFound, $wdParagraph, 1, $wdParagraph, 1)	; pakt de zin NA de naam v/d ontvanger. Ofwel de zin met "T.a.v. Crediteurenadministratie"
		 If @error Then Exit MsgBox($MB_SYSTEMMODAL, "Fout", "Fout bij het selecteren v/d tekst." & @CRLF & "Handeling wordt stopgezet")
		 _Word_DocFindReplace($wordFile, $repText, $replacee, $wdReplaceOne, $oRange)	; vervangt "T.a.v. etc" met waarde uit csv-bestand.
		 FileWriteLine($logFile, $recipient & " vervangen." & @CRLF)
	  Else
		 updateLog($i+1 & " - Tekst: " & $recipient & " niet gevonden." & @CRLF & "--------------------" & @CRLF, $hLog)
		 FileWriteLine($logFile, "! - " & $recipient & " niet gevonden." & @CRLF)
		 $i += 1
	  EndIf

	  GUICtrlSetData($progressBar, $i)
   Next

   updateLog("Conversie voltooid. Bestand wordt opgeslagen", $hLog)

   _Word_DocSave($wordFile)
   _Word_DocClose($wordFile)
   FileClose($logFile)

   MsgBox($MB_SYSTEMMODAL, "Conversie compleet", "Conversie voltooid." & @CRLF & "Alle handelingen zijn opgeslagen in logs\logConversie[datum]_[tijd].txt")	;todo-c: geef optie om meteen dat bestand te kunnen openen

   For $i = $iStart To $iEnd
	  GUICtrlSetState($i, $GUI_ENABLE)			;zet alle knoppen weer aan
   Next

EndFunc

; #FUNCTION# ====================================================================================================================
Func getFileName($textBox, $selectedFile)
   Local $sDrive = "", $sDir = "", $sFileName = "", $sExtension = ""						; nodig voor _PathSplit
   Local $aPathSplit = _PathSplit($selectedFile, $sDrive, $sDir, $sFileName, $sExtension)	; pakt de bestandsnaam
   GUICtrlSetData($textBox, $sFileName & $sExtension)
EndFunc

; #FUNCTION# ====================================================================================================================
Func updateLog($sMsg, $hLog)
   If $hLog <> 0 Then _GUICtrlEdit_AppendText($hLog, $sMsg & @CRLF)
   EndFunc
