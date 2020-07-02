#include <Date.au3>
#include <File.au3>
#include <Array.au3>
#include <GuiEdit.au3>
#include <GUIConstantsEx.au3>
#include <WindowsConstants.au3>

; vereist pdfinfo.exe. Is hier te vinden onder command line tools: https://www.xpdfreader.com/download.html

Local $sRootFolder
Local $gGUI = GUICreate("Het Tellende Schip", 500, 200)
Local $showSrcFolder = GUICtrlCreateInput("Folder met PDF's", 90, 22, 390, 20, $ES_READONLY)
Local $countItems = GUICtrlCreateLabel("", 20, 100, 50, 20)
Local $countPages = GUICtrlCreateLabel("", 15, 130, 60, 50)
Local $btnFolder = GUICtrlCreateButton("Kies folder", 10, 20, 70, 25)
Local $btnCountPages = GUICtrlCreateButton("Tel pagina's", 10, 50, 70, 35)
GUICtrlSetState($btnCountPages, $GUI_DISABLE)

Local $gLog = _GUICtrlEdit_Create($gGUI, "", 90, 50, 390, 130, BitOR($WS_VSCROLL, $ES_MULTILINE, $ES_WANTRETURN, $ES_READONLY))	;had ook $WS_HSCROLL

GUISetState(@SW_SHOW)

; #GUI LOOP# ====================================================================================================================
While True
	Switch GUIGetMsg()
		Case $btnFolder
			$sRootFolder = FileSelectFolder("Kies de folder", @ScriptDir)
			GUICtrlSetData($showSrcFolder, $sRootFolder)
			GUICtrlSetState($btnCountPages, $GUI_ENABLE)

		Case $btnCountPages
			If $sRootFolder <> "" Then
				countPages()
			Else
				MsgBox(16, "Fout", "Geen folder geselecteerd")
			EndIf

		Case $GUI_EVENT_CLOSE
			Exit
	EndSwitch
WEnd

Func countPages()
	GUICtrlSetState($btnFolder, $GUI_DISABLE)
	GUICtrlSetState($btnCountPages, $GUI_DISABLE)

	Local $pageCount = 0
	Local $aFiles = _FileListToArrayRec($sRootFolder, "*.pdf", 1, 1)
	_ArrayDelete($aFiles, 0)	; verwijdert rij met aantal resultaten
	GUICtrlSetData($countItems, UBound($aFiles) & " pdf's")

	For $i = 0 To UBound($aFiles)-1
		Local $sInfo = _XFDF_Info($sRootFolder & "\" & $aFiles[$i], "Pages")
		$pageCount += $sInfo
		_UpdateLog($i + 1 & " - telling tot dusver: " & $pageCount & " pagina's" & @CRLF, $gLog)
		If @error Then ConsoleWrite("error: " & @error & @CRLF)
		If @error Then Exit MsgBox(0, "Error code: " & @error)
	Next

	_UpdateLog("Telling voltooid", $gLog)

	GUICtrlSetData($countPages, $pageCount & " pagina's")

	Local $logFile = FileOpen("telling_" & _NowDate() & "_" & StringLeft(_NowTime(4), 2) & "h-" & StringRight(_NowTime(4), 2) & "m" & ".txt", 2)
	FileWriteLine($logFile, "Root folder: " & $sRootFolder & @CRLF)
	FileWriteLine($logFile, UBound($aFiles) & " pdf's" & @CRLF)
	FileWriteLine($logFile, $pageCount & " pagina's")
	FileClose($logFile)

	GUICtrlSetState($btnFolder, $GUI_ENABLE)
	GUICtrlSetState($btnCountPages, $GUI_ENABLE)
EndFunc

; gejat van autoitscript.com/forum/topic/159949-get-information-from-pdf/?do=findComment&comment=1170718
; beetje gewijzigd ten behoeve van betrouwbaarheid
Func _XFDF_Info($sPDFFile, $sInfo = "")
    Local $sXPDFInfo = @ScriptDir & "\pdfinfo.exe"

    If NOT FileExists($sPDFFile) Then Return SetError(1, 0, 0)
    If NOT FileExists($sXPDFInfo) Then Return SetError(2, 0, 0)

    Local $iPid = Run(@ComSpec & ' /c "' &  $sXPDFInfo & ' "' & $sPDFFile & '"', @ScriptDir, @SW_HIDE, 2)

    #cs initiële manier, geeft onbetrouwbare resultaten
	Local $sResult
	While 1
		;ConsoleWrite("Inside the while loop" & @CRLF)
        $sResult &= StdoutRead($iPid)
		;#cs
		If $sResult <> "" Or @error Then
			;ConsoleWrite("Exiting while loop" & @CRLF)
			ExitLoop
		EndIf
		;#ce
		;ConsoleWrite("$sResult: " & $sResult & @CRLF)
        ;If @error Then ExitLoop
    WEnd
	;ConsoleWrite("Left the while loop" & @CRLF)
	#ce

	; herziene while-loop, geeft betrouwbare resultaten
	Local $sResult
	Local $myTimer = 0
	Local $timeOut = 250
	While $myTimer < $timeOut
		$sResult &= StdoutRead($iPid)
		If $sResult <> "" Then	; zolang $myTimer nog niet $timeOut heeft bereikt, blijft ie nog even verder draaien en wordt de kans dat ie de pagina-info niet ophaalt geminimaliseerd/verwijderd.
			$myTimer += 1
		EndIf
	WEnd

    Local $aInfos = StringRegExp($sResult, "(?m)^(.*?): +(.*)$", 3)
    If Mod( UBound($aInfos, 1), 2) = 1 Then Return SetError(3, 0, 0)

    Local $aResult [ UBound($aInfos, 1) / 2][2]

    For $i = 0 To UBound($aInfos) - 1 Step 2
        If $sInfo <> "" AND $aInfos[$i] = $sInfo Then Return $aInfos[$i + 1]
        $aResult[$i / 2][0] = $aInfos[$i]
        $aResult[$i / 2][1] = $aInfos[$i + 1]
    Next

    If $sInfo <> "" Then Return ""

    Return $aResult
EndFunc ;==> _XFDF_Info

; #FUNCTION# ====================================================================================================================
; Name...........: _UpdateLog
; Description....: Displays text in a GUI textbox
; Parameters.....: $sMsg - The text to be displayed
; 				   $hLog - The GUI-element that needs to display the text
; Return value...: None
; Author:........: AutoBert
; Modified.......: Steve
; Remarks........:
; Related........:
; Link...........: autoitscript.com/forum/topic/184815-how-to-make-text-gui-with-scroll-bar-for-progress-logging/?do=findComment&comment=1327470
; Example........:
; ===============================================================================================================================
Func _UpdateLog($sMsg, $hLog)
   If $hLog <> 0 Then _GUICtrlEdit_AppendText($hLog, $sMsg)
EndFunc	;==>_UpdateLog
