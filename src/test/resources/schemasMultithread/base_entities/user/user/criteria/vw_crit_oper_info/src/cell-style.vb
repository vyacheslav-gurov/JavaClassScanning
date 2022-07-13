Private Sub SetDaughter
	cVal = frm.CurRowAlias("GROUP_AUDM")
	cVal2 = frm.CurRowAlias("OWN_GR")  
	if cVal <> cVal2 Then
		frm.CurCellStyle.Font.Italic = True
		frm.CurCellStyle.ForeColor = vbBlue
	end if
End Sub

Public Sub Begin(sAlias)
	select case sAlias
		case "GROUP_AUDM"
			SetDaughter
		case "PARENT_GR"
			SetDaughter
		case "OWN_GR"
			SetDaughter
	end select
End Sub