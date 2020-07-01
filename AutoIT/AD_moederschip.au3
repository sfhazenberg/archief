#include <AD.au3>
#include <Array.au3>
#include <Excel.au3>
#include <String.au3>
#include <GuiListBox.au3>
#include <FontConstants.au3>
#include <GuiConstantsEX.au3>
#include <MsgBoxConstants.au3>
#include <StaticConstants.au3>
#include <ButtonConstants.au3>
#include <WindowsConstants.au3>
#include <ListViewConstants.au3>

; #INDEX# =======================================================================================================================
; Title .........: AD Moederschip
; AutoIt Version : SciTE-Lite 3.5.4
; Language ......: Nederlands/English
; Description ...: GUI waarmee het opvragen van bepaalde groepen, gebruikers en rechten uit het AD gestroomlijnd wordt. Kan tevens opgevraagde lijsten naar Excel exporteren.
; Author(s) .....: Steven Hazenberg
; Modified.......: 20190902 (YYYMMDD)
; ===============================================================================================================================

; #NOTES# =======================================================================================================================
; Dit is een persoonlijke versie enkel bedoeld voor eventuele raadpleging.
; ===============================================================================================================================

; #VARIABLES# ===================================================================================================================
Global $a1[0]
Global $a2[0]
Global $a3[0]
Global $ga1[0]	;ga = Global Array
Global $ga2[0]
Global $ga3[0]
Global $ga4[0]
Global $ga5[0]
Global $ga6[0]
Global $ga7[0]
Global $ga8[0]
Global $adCheck			;boolean om te checken of er verbinding met AD is. Wordt ingevuld in getGroups() en gecheckt als het Moederschip wordt afgesloten.
Global $prefixCheck		;nodig voor het vaststellen van $prefix. Functies getMembers() en createExcelList() gebruiken het.	;heette voorheen $location

;nodig om de AD-paden correct aan te vullen. Geen prefix nodig voor mailgroepen/boxen
Global $prefixArray[5][2] = [["0", "-"] _
						   , ["1", "_"] _
						   , ["2", " "] _
						   , ["3", "_"] _
						   , ["4", "-"]]

; #GUI CREATION# ===================================================================================================================
Local $mainGUI = GUICreate("AD Moederschip", 1560, 600)	;width was 1660
Local $closeBtn = GUICtrlCreateButton("Sluiten", 20, 550, 50, 30)

Local $labelRadioBtns = GUICtrlCreateLabel("Welk domein?", 20, 10, 100, 20)
Local $domainCombo = GUICtrlCreateCombo("", 20, 30, 90, 20)
Local $grabData = GUICtrlCreateButton("Haal data op", 120, 28, 80, 25)
GUICtrlSetData($domainCombo, "1|2", "1")

Local $groupLabel = GUICtrlCreateLabel("Welke groep?", 20, 65, 360, 30, $SS_LEFT)
Local $groupCombo = GUICtrlCreateCombo("", 20, 90, 90, 20)
GUICtrlSetData($groupCombo, "1|2|3|4|5|6|7", "1")

Local $hiddenDummyStart = GUICtrlCreateDummy()	;dummy fungeert als container voor onderstaande labels
Local $statusMembers 	= GUICtrlCreateLabel("Moment geduld aub, dit kan even duren", 390, 305, 95, 50, BitOR($SS_CENTER, $BS_MULTILINE))
Local $currentGroup 	= GUICtrlCreateLabel("Huidige groep", 500, 90, 360, 20, $SS_LEFT)
Local $currentUser 		= GUICtrlCreateLabel("Huidige gebruiker", 980, 90, 90, 20, $SS_LEFT)
Local $deptCurrentUser 	= GUICtrlCreateLabel("Afdeling", 1090, 90, 90, 20, $SS_LEFT)
Local $fullNameLabel	= GUICtrlCreateLabel("Volledige naam", 1230, 90, 90, 20, $SS_LEFT)
Local $fullName			= GUICtrlCreateLabel("", 1230, 110, 200, 20, $SS_LEFT)
Local $countGroupsLabel = GUICtrlCreateLabel(" resultaten", 330, 527, 50, 20, $SS_LEFT)
Local $countGroups 		= GUICtrlCreateLabel("", 288, 527, 40, 20, $SS_RIGHT)
Local $countUsersLabel 	= GUICtrlCreateLabel(" resultaten", 810, 527, 50, 20, $SS_LEFT)
Local $countUsers 		= GUICtrlCreateLabel("", 768, 527, 40, 20, $SS_RIGHT)
Local $countRightsLabel = GUICtrlCreateLabel(" resultaten", 1590, 527, 50, 20, $SS_LEFT)
Local $countRights 		= GUICtrlCreateLabel("", 1548, 527, 40, 20, $SS_RIGHT)
Local $hiddenDummyEnd 	= GUICtrlCreateDummy()
For $i = $hiddenDummyStart To $hiddenDummyEnd
   GUICtrlSetState($i, $GUI_HIDE)	;verbergt labels in bovenstaande dummy bij het opstarten
Next

Local $selectedGroupLabel = GUICtrlCreateLabel("", 500, 110, 360, 20, $SS_LEFT)		;weergeeft geselecteerde groep boven gebruikersoverzicht
Local $selectedUserLabel = GUICtrlCreateLabel("", 980, 110, 90, 20, $SS_LEFT)		;weergeeft geselecteerde gebruiker boven rechtenoverzicht
Local $deptSelectedUser = GUICtrlCreateLabel("", 1090, 110, 130, 25, $SS_LEFT)		;weergeeft afdeling van geselecteerde gebruiker
Local $memberLabel 	= GUICtrlCreateLabel("Gebruikers", 500, 65, 360, 20, $SS_LEFT)
Local $rightsLabel	= GUICtrlCreateLabel("memberOf", 980, 65, 660, 20, $SS_LEFT)
Local $ssoLabel 	= GUICtrlCreateLabel("Zoek op SSO", 980, 10, 100, 20)
Local $ssoInput 	= GUICtrlCreateInput(@UserName, 980, 30, 65, 20)

GUICtrlSetFont($groupLabel, 10, 800)
GUICtrlSetFont($memberLabel, 10, 800)
GUICtrlSetFont($rightsLabel, 10, 800)
GUICtrlSetFont($currentGroup, 8.5, 400, $GUI_FONTITALIC)
GUICtrlSetFont($currentUser, 8.5, 400, $GUI_FONTITALIC)
GUICtrlSetFont($fullNameLabel, 8.5, 400, $GUI_FONTITALIC)
GUICtrlSetFont($deptCurrentUser, 8.5, 400, $GUI_FONTITALIC)

Local $btnDummyStart = GUICtrlCreateDummy()										;dummy-container voor onderstaande knoppen
Local $showGroups = GUICtrlCreateButton("Weergeef groepen", 120, 88, 100, 25)	;hoort bij dropdown-menu van groepen
Local $ssoSearch = GUICtrlCreateButton("Zoeken", 1055, 27, 50, 25)				;hoort bij ssoInput
Local $getMembers = GUICtrlCreateButton(">> Gebruikers >>", 390, 270, 95, 30)	;gaat van groepen naar gebruikers
Local $btnRights = GUICtrlCreateButton(">> Rechten >>", 870, 270, 95, 30)		;gaat van gebruikers naar rechten
Local $clearListUsers = GUICtrlCreateButton("Wis huidige lijst", 500, 545, 85, 30)	;hoort bij lijst gebruikers van geselecteerde groep
Local $exportUsersToExcel = GUICtrlCreateButton("Exporteer lijst naar Excel", 635, 540, 90, 40, $BS_MULTILINE)	;hoort bij lijst gebruikers van geselecteerde groep
Local $exportRightsToExcel = GUICtrlCreateButton("Exporteer rechten naar Excel", 1210, 540, 100, 40, $BS_MULTILINE)	;hoort bij lijst rechten van geselecteerde/opgezochte gebruiker	;left was 1260
Local $btnDummyEnd = GUICtrlCreateDummy()
For $i = $btnDummyStart To $btnDummyEnd
   GUICtrlSetState($i, $GUI_DISABLE)		;zet knoppen uit bij opstarten
Next

Local $groupView  = GUICtrlCreateList("", 20, 130, 360, 400, BitOR($WS_HSCROLL, $WS_VSCROLL))	;box die groepen weergeeft
Local $usersView  = GUICtrlCreateList("", 500, 130, 360, 400, BitOR($WS_HSCROLL, $WS_VSCROLL))	;box die gebruikers van geselecteerde groep weergeeft
Local $rightsView = GUICtrlCreateList("", 980, 130, 560, 400, BitOR($WS_HSCROLL, $WS_VSCROLL))	;box die rechten van geselecteerde gebruiker weergeeft	;width was 660

GUISetState(@SW_SHOW)

; #GUI LOOP# ===================================================================================================================
While True
   Switch GUIGetMsg()
	  Case $grabData
		 Local $loadingData = GUICtrlCreateLabel("Data wordt opgehaald, een moment geduld", 210, 27, 110, 40, BitOR($SS_CENTER, $BS_MULTILINE))
		 GUICtrlSetState($grabData, $GUI_DISABLE)
		 getGroups(GUICtrlRead($domainCombo))
		 GUICtrlSetState($loadingData, $GUI_HIDE)
		 Local $dataConfirmed = GUICtrlCreateLabel("Data uit AD opgehaald", 210, 34, 110, 20)
		 GUICtrlSetState($grabData, $GUI_ENABLE)
		 GUICtrlSetState($showGroups, $GUI_ENABLE)
		 GUICtrlSetState($ssoSearch, $GUI_ENABLE)

	  Case $showGroups
		 Local $requestedGroup
		 Switch GUICtrlRead($groupCombo)
			Case "1"
			   $prefixCheck = "0"
			   $requestedGroup = $a1
			Case "2"
			   $prefixCheck = "1"
			   $requestedGroup = $a2
			Case "3"
			   $prefixCheck = "0"
			   $requestedGroup = $a3
			Case "4"
			   $prefixCheck = "2"
			   $requestedGroup = $ga1
			Case "5"
			   $prefixCheck = "2"
			   $requestedGroup = $ga2
			Case "6"
			   $prefixCheck = "3"
			   $requestedGroup = $ga3
			Case "7"
			   $prefixCheck = "2"
			   $requestedGroup = $ga4
		 EndSwitch
		 updateView($groupView, $requestedGroup)
		 GUICtrlSetData($countGroups, UBound($requestedGroup))
		 GUICtrlSetState($countGroups, $GUI_SHOW)
		 GUICtrlSetState($countGroupsLabel, $GUI_SHOW)
		 GUICtrlSetState($getMembers, $GUI_ENABLE)

	  Case $getMembers
		 getMembers()
		 GUICtrlSetState($exportUsersToExcel, $GUI_ENABLE)
		 GUICtrlSetState($btnRights, $GUI_ENABLE)
		 GUICtrlSetState($clearListUsers, $GUI_ENABLE)

	  Case $clearListUsers
		 emptyView($usersView)
		 GUICtrlSetData($selectedGroupLabel, "")
		 GUICtrlSetState($currentGroup, $GUI_HIDE)
		 GUICtrlSetState($countUsersLabel, $GUI_HIDE)
		 GUICtrlSetState($countUsers, $GUI_HIDE)
		 GUICtrlSetState($btnRights, $GUI_DISABLE)
		 GUICtrlSetState($clearListUsers, $GUI_DISABLE)
		 GUICtrlSetState($exportUsersToExcel, $GUI_DISABLE)

	  Case $exportUsersToExcel
		 createExcelList("user")

	  Case $exportRightsToExcel
		 createExcelList("rights")

	  Case $btnRights
		 getRights("btn")
		 GUICtrlSetState($exportRightsToExcel, $GUI_ENABLE)

	  Case $ssoSearch
		 getRights("sso")
		 GUICtrlSetState($exportRightsToExcel, $GUI_ENABLE)

	  Case $GUI_EVENT_CLOSE, $closeBtn
		 If $adCheck = True Then
			_AD_Close()
		 EndIf
		 Exit
   EndSwitch
WEnd

; #CURRENT# =====================================================================================================================
; getRights
; getMembers
; getGroups
; createExcelList
; updateView
; emptyView
; updateBtnStyle (OBSOLETE)
; ===============================================================================================================================

Func getRights($msg)
   Local $cn
   Local $vFullName
   If $msg = "btn" Then
	  ;$cn = GUICtrlRead($usersView)
	  ;$cn = StringStripWS($cn, 1)
	  ;$cn = StringLeft($cn, 6)
	  $cn = StringLeft(StringStripWS(GUICtrlRead($usersView), 1), 6)	;zelfde als bovenstaande 3 regels, maar minder leesbaar.
	  Local $indexCN = _ArraySearch($gaCN, $cn)
	  $vFullName = $gaFullnames[$indexCN]	;hier fullname uit $gaFullnames
   Else
	  $cn = GUICtrlRead($ssoInput)
	  Local $firstName = _AD_GetObjectAttribute($cn, "GivenName")	;hier fullname handmatig pakken, want hier is het goed mogelijk dat getMembers() nog niet heeft gedraaid, dus is $gaFullnames nog leeg.
	  Local $lastName = _AD_GetObjectAttribute($cn, "Sn")
	  $vFullName = $firstName & " " & $lastName
   EndIf

   Local $aRights = _AD_GetUserGroups($cn)
   Local $dept = _AD_GetObjectAttribute($cn, "department")
   If @error = 2 Then	;attribuut niet gevonden
	  $dept = "Geen afdeling gevonden"
   EndIf
   _ArrayDelete($aRights, "0")
   $gaRights = $aRights
   updateView($rightsView, $aRights)
   GUICtrlSetData($selectedUserLabel, $cn)
   GUICtrlSetData($fullName, $vFullName)
   GUICtrlSetData($deptSelectedUser, $dept)
   GUICtrlSetData($countRights, UBound($aRights))

   Local $aShow = [$currentUser, $deptCurrentUser, $deptSelectedUser, $countRights, $countRightsLabel, $fullNameLabel, $fullName]
   For $i = 0 To UBound($aShow)-1
	  GUICtrlSetState($aShow[$i], $GUI_SHOW)
   Next
EndFunc

Func getMembers()
   GUICtrlSetState($getMembers, $GUI_DISABLE)
   GUICtrlSetState($statusMembers, $GUI_SHOW)
   Local $aMembers
   Local $selection = GUICtrlRead($groupView)

   ;;; 1: gebruikers uit geselecteerde groep worden hier opgehaald en in array gestopt
   If ($selection <> "") And ($prefixCheck == "0" Or $prefixCheck == "1") Then		;als Gro&Ame/Gro/Ame is geselecteerd
	  Local $prefix = $prefixArray[$prefixCheck][1]
	  ;sommige groepen zijn anders geformuleerd in het AD. Onderstaande For-loop dekt die af.
	  Local $exceptionsArray = ["_1", "_2", "_3", "_4", "_5", "_6"]	;uitzonderingen in groep Groningen
	  For $i = 0 To UBound($exceptionsArray)-1
		 If $exceptionsArray[$i] == $selection Then
			$aMembers = _AD_RecursiveGetGroupMembers($prefixArray[2][1] & $selection, 2, False)
			ExitLoop
		 Else
			If @error Then					;als AD-pad niet gevonden
			   $prefix = $prefixArray[3][1]	;dan 4e prefix gebruiken
			EndIf
			$aMembers = _AD_RecursiveGetGroupMembers($prefix & $selection, 2, False)
		 EndIf
	  Next
   ElseIf $selection <> "" And $prefixCheck == "3" Then	;als een mailgroep is geselecteerd
	  $aMembers = _AD_RecursiveGetGroupMembers($selection)
   ElseIf $selection <> "" And $prefixCheck == "2" Then		;als een applicatie is geselecteerd
	  $aMembers = _AD_RecursiveGetGroupMembers($prefixArray[4][1] & $selection, 2, False)
   Else
	  MsgBox($MB_ICONINFORMATION, "Fout", "Geen groep geselecteerd.")
   EndIf
   _ArrayDelete($aMembers, 0)
   GUICtrlSetData($countUsers, UBound($aMembers))

   ;;; 2: array wordt bewerkt voor weergave en $gaOU en $gaCN worden klaargestoomd voor createExcelList()
   Local $resultCheck = _ArrayToString($aMembers)	;check of array $groupMembers wel of niet leeg is.
   If $resultCheck <> "0" Then						;als niet leeg, dan onderstaande
	  Local $aCN[0]
	  Local $aOU[0]
	  Local $aUsers[0][2]
	  Local $aFullNames[0]

	  For $i = 0 To UBound($aMembers)-1
		 Local $temp = $aMembers[$i]
		 Local $iPos = StringInStr($temp, ",")
		 Local $tempCN = StringLeft($temp, $iPos-1)
		 Local $cn = StringTrimLeft($tempCN, 3)
		 _ArrayAdd($aCN, $cn)					;gooit cn in lokale array. Nodig voor createExcelList()
		 Local $OU = StringMid($temp, $iPos+1)	;OU-pad dat nodig is voor het v/d naam. Wss ook nodig voor het pakken v/ rechten	;mss naar een Global Array smijten?
		 _ArrayAdd($aOU, $OU)

		 Local $aFullNameUser = _AD_GetObjectsInOU($OU, "(sAMAccountName=" & $cn & ")", 2, "GivenName, Sn")	;genereert array van 1 rij en 2 kolommen met respectievelijk voor- en achternaam
		 If @error Then ConsoleWrite("Fout bij pakken van volledige naam - " & @error & @CRLF)
		 _ArrayDelete($aFullNameUser, 0)								;verwijdert eerste rij (bevat enkel een integer met hoeveelheid resultaten)
		 Local $sFullName = _ArrayToString($aFullNameUser)				;converteert array naar string
		 Local $fullName = StringReplace($sFullName, "|", " ")			;vervangt "|" met een spatie
		 _ArrayAdd($aFullNames, $fullName)								;gooit voor- en achternaam in lokale array. Nodig voor createExcelList()
		 Local $displayName = _StringInsert($fullName, $cn & " - ", 0)	;genereert string met [cn] - [volledige naam]
		 _ArrayAdd($aUsers, $displayName)								;;en gooit die in array $aUsers
	  Next

	  $gaOU = $aOU
	  $gaCN = $aCN										;invulling van globale CN-array, nodig voor createExcelList()
	  $gaFullNames = $aFullNames						;invulling van globale array, nodig voor createExcelList()
	  updateView($usersView, $aUsers)					;vult overzicht met gebruikers uit geselecteerde groep
	  GUICtrlSetState($currentGroup, $GUI_SHOW)			;weergeeft label "Huidige groep"
	  GUICtrlSetData($selectedGroupLabel, $selection)	;weergeeft de geselecteerde groep
   Else
	  GUICtrlSetState($statusMembers, $GUI_HIDE)
	  MsgBox($MB_ICONWARNING, "Fout", "Geen members gevonden.")	;als geen member-attribuut is gevonden, dan is $groupMembers een enkele rij en kolom met 0.
   EndIf

   ;;; 3: uiteindelijke array wordt gesorteerd en weergegeven
   _ArraySort($aUsers)
   updateView($usersView, $aUsers)
   GUICtrlSetState($getMembers, $GUI_ENABLE)
   GUICtrlSetState($statusMembers, $GUI_HIDE)
   GUICtrlSetState($countUsers, $GUI_SHOW)
   GUICtrlSetState($countUsersLabel, $GUI_SHOW)
EndFunc

;haalt groepen op uit geselecteerde AD-domein
Func getGroups($domainMsg)
   Local $DNS[2]	= ["-1", "1-"]
   Local $host[2]	= ["-2", "2-"]
   Local $config[2]	= ["-3", "3-"]

   If $domainMsg == "1" Then
	  _AD_Open("", "", ($DNS)[0], ($host)[0], ($config)[0])
	  Local $path = "path"
	  ;;-- 1 --;;
	  Local $arrayGroups = _AD_GetObjectsInOU($path)
	  _ArrayDelete($arrayGroups, "0")			;verwijdert regel met hoeveelheid resultaten
	  _ArrayTrim($arrayGroups, 15, 0, 0, 904)	;trimt [tekst] || Als 1, dan rij 0-904
	  _ArraySort($arrayGroups)
	  $a1 = $arrayGroups
	  For $i = 0 To UBound($arrayGroups)-1
		 Local $temp = $arrayGroups[$i]
		 Local $tempCheck = StringInStr($temp, "tekst")
		 If $tempCheck <> 0 Then
			$newTemp = StringTrimLeft($temp, 8)	;als [tekst] gevonden, dan wordt [tekst] en het eerstvolgende teken ernaast ("_","-"," ") eruit gehaald
			_ArrayAdd($a2, $newTemp)			;en gooit $newTemp in $a2
		 Else
			_ArrayAdd($a3, $temp)			;anders, gooit $temp in $a3. Bevat geen [tekst], dus bevindt zich niet in $a2
		 EndIf
	  Next
	  _ArrayDelete($a3, "0")	;verwijdert lege regel
	  ;;-- 2 --;;
	  Local $path2 = "path2"
	  $ga2 = _AD_GetObjectsInOU($path2)
	  _ArrayDelete($ga2, "0;29")
	  ;;-- 3 --;;
	  Local $path3 = "path3"
	  $ga3 = _AD_GetObjectsInOU($path3)
	  _ArrayDelete($ga3, "0;366;367")
	  ;;-- 4 --;;
	  Local $path4 = "path4"
	  Local $aTemp4 = _AD_GetObjectsInOU($path4)
	  For $i = 0 To UBound($aTemp4)-1
		 Local $temp = $aTemp4[$i]
		 Local $division = _AD_GetObjectAttribute($temp, "division")
		 If $division == "[division]" Then
			_ArrayAdd($ga4, $temp)
		 EndIf
	  Next
	  ;;-- 5 --;;
	  Local $path5 = "path5"
	  $ga5 = _AD_GetObjectsInOU($path5)
	  _ArrayDelete($ga5, "0;578")
	  _ArrayTrim($ga5, 12)	;trimt [tekst] van elke rij.

   ElseIf $domainMsg == "2" Then
	  _AD_Open("", "", ($DNS)[1], ($host)[1], ($config)[1])
   EndIf

   $adCheck = True
EndFunc

;converteert lijst met informatie naar Excel
Func createExcelList($type)
   ;;; onderstaande kan mss schoner
   If $type == "user" Then				;wordt aangeroepen als lijst van GEBRUIKERS wordt opgevraagd
	  Local $nameSheet1 = "[users]"
	  Local $nameSheet2 = "[group]"
	  Local $groupName = $prefixArray[$prefixCheck][1] & GUICtrlRead($groupView)
	  Local $cnList = $gaCN
	  Local $fullNamesList = $gaFullNames
	  _ArrayInsert($cnList, 0, "CN")			;voegt rij bovenaan in array met waarde "CN". Dat wordt dan de kolomnaam in Excel.
	  _ArrayInsert($fullNamesList, 0, "Naam")	;voegt rij bovenaan in array met waarde "Naam". Dat wordt dan de kolomnaam in Excel.

	  Local $oExcel = _Excel_Open()
	  If @error = 0 Then
		 Local $oWorkbook = _Excel_BookNew($oExcel, 2)
		 $oWorkbook.Sheets(1).Name = $nameSheet1
		 $oWorkbook.Sheets(2).Name = $nameSheet2
		 _Excel_RangeWrite($oWorkbook, $nameSheet1, $cnList, "A1")			;CN
		 _Excel_RangeWrite($oWorkbook, $nameSheet1, $fullNamesList, "B1")	;voor- en achternaam
		 _Excel_RangeWrite($oWorkbook, $nameSheet2, $groupName, "A1")		;volledige naam v/d geselecteerde AD-groep, gooit die in cel A1 v/h tweede werkblad
		 $oWorkbook.Activesheet.Range("A:B").Columns.Autofit
		 $oWorkbook.Activesheet.Range("A1").EntireRow.Font.Bold = True
		 MsgBox($MB_SYSTEMMODAL, "Actie voltooid", "Excel-lijst met gebruikers aangemaakt.")
	  Else
		 MsgBox($MB_SYSTEMMODAL, "Fout", "Fout bij het maken van een Excel-object, conversie wordt stopgezet.")
	  EndIf

   ElseIf $type == "rights" Then			;wordt aangeroepen als lijst van RECHTEN wordt opgevraagd
	  Local $user = GUICtrlRead($selectedUserLabel)
	  Local $nameSheet1 = "Rechten van " & $user
	  Local $rightsList = $gaRights

	  Local $oExcel = _Excel_Open()
	  If @error = 0 Then
		 Local $oWorkbook = _Excel_BookNew($oExcel)
		 $oWorkbook.Sheets(1).Name = $nameSheet1
		 _Excel_RangeWrite($oWorkbook, $nameSheet1, $rightsList, "A1")		;rechten v/d gebruiker
		 MsgBox($MB_SYSTEMMODAL, "Actie voltooid", "Excel-lijst met rechten van gebruiker " & $user & " aangemaakt.")
	  Else
		 MsgBox($MB_SYSTEMMODAL, "Fout", "Fout bij het maken van een Excel-object, conversie wordt stopgezet.")
	  EndIf
   EndIf

   ;Excel --> Data --> Text to Columns
   ;Delimited --> Next
   ;Vink 'Comma' aan
   ;Finish
   ;evt Filter op alle kolommen zetten. Is hendig jonguh
   ;dan is alles in aparte kolommen
EndFunc

;vult box/list in kwestie met geselecteerde array
Func updateView($view, $array = "")
   emptyView($view)
   Local $list = _ArrayToString($array)
   GUICtrlSetData($view, $list)
EndFunc

;leegt box/list in kwestie
Func emptyView($view)
   GUICtrlSetData($view, "")
EndFunc

;wordt niet meer aangesproken
#cs Func updateBtnStyle($btn)
   For $i = $btnDummyStart To $btnDummyEnd
	  GUICtrlSetFont($i, 8.5, 400)	;zet alle knoppen naar originele staat
   Next
   GUICtrlSetFont($btn, 9, 800)	;maakt tekst in geselecteerde knop groter en vetgedrukt ter feedback
EndFunc
#ce
