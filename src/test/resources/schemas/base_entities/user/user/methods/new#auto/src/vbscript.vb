Private Function say_them_it(s)
    If Mid(s, 1, 1) <> "?" Then
        MsgBox s, vbInformation, "Ошибка ввода"
        say_them_it = ""
    Else
    	If InStr(1,s,"Продолжить") <> 0 Then    	
    		tmp = vbDefaultButton2
    	Else 
    		tmp = 0
    	End If
        If MsgBox(Mid(s, 2, InStr(2, s, "?") - 1), vbQuestion + vbYesNo + tmp, "Подтверждение ввода") = vbYes Then        
			pos = InStr(2, s, "?")+1
            Value1 = Mid(s, pos)
            say_them_it = Value1
        End If
    End If
End Function

Public Function Main(LastControl)
' [DVP-5617] Этот код не работал т.к. клиент валидатор был отключен
'	Check3.Enabled = (Check1.Value <> 0)
'	Check4.Enabled = (Check1.Value <> 0)
'	Label3.Enabled = (Check3.Value <> 0)
'	Label5.Enabled = (Check3.Value <> 0)
'	Text6.Locked = (Check1.Value = 0) or (Check3.Value = 0)
'	Text7.Locked = (Check1.Value = 0) or (Check3.Value = 0)
	If LastControl Is Nothing Then	
	else
		If LastControl Is OK Then  
			If message.text <> "" Then
				Form1.ScriptBeep
				num = say_them_it(message.text)
				if num<> "" then	
					LastControl.SetFocus
				else	' Останавливаем ввод...
					message.text = ""				
					Main = null
		            Exit Function
				end if
			end if
		end if
	end if
	Main = True ' Результирующее значение валидатора (True или False)	
End Function
