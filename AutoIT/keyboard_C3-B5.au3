#include <Misc.au3>
#include <MsgBoxConstants.au3>
#include <Array.au3>

;Behold, a lil' keyboard with ranges C3 through B5!

GUICreate("The played note", 100, 100)
GUISetState(@SW_SHOW)

Local $hDLL = DllOpen("user32.dll")

Global $keyboardArr[21][3] = [["31", "C3", "130.81"] _	; 1
			   , ["32", "D3", "146.83"] _	; 2
			   , ["33", "E3", "164.81"] _	; 3
			   , ["34", "F3", "174.61"] _	; 4
			   , ["35", "G3", "196.00"] _	; 5
			   , ["36", "A3", "220.00"] _	; 6
			   , ["37", "B3", "246.94"] _	; 7
			   , ["51", "C4", "261.63"] _	; Q
			   , ["57", "D4", "293.66"] _	; W
			   , ["45", "E4", "329.63"] _	; E
			   , ["52", "F4", "349.23"] _	; R
			   , ["54", "G4", "392.00"] _	; T
			   , ["59", "A4", "440.00"] _	; Y
			   , ["55", "B4", "493.88"] _	; U
			   , ["41", "C5", "523.25"] _	; A
			   , ["53", "D5", "587.33"] _	; S
			   , ["44", "E5", "659.25"] _	; D
			   , ["46", "F5", "698.46"] _	; F
			   , ["47", "G5", "783.99"] _	; G
			   , ["48", "A5", "880.00"] _	; H
			   , ["4A", "B5", "987.77"]]	; J
$escKey = "1B"
$noteDuration = 250
$sleepDuration = 250

While 1
   For $i = 0 To (UBound($keyboardArr)-1)
	  If _IsPressed($keyboardArr[$i][0], $hDLL) Then
	  Beep($keyboardArr[$i][2], $noteDuration)
	  GUICtrlCreateLabel($keyboardArr[$i][1], 45, 45)
	  While _IsPressed($keyboardArr[$i][0], $hDLL)
		 Sleep($sleepDuration)
	  WEnd
	  ElseIf _IsPressed($escKey, $hDLL) Then
		 MsgBox($MB_SYSTEMMODAL, "Shutting down", "Aborting program.")
		 GUIDelete()
		 DllClose($hDLL)
		 Exit 0
	  EndIf
   Next
WEnd
