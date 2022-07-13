Public Function Main(LastControl)
	If LastControl Is Nothing Then
		' Действия при загрузке формы

    Else ' ВАЛИДАЦИЯ

	End If
	
	if Check6 then
		Check10.Enabled  = true
		Check5.Enabled   = true
		Variant1.Enabled = true
		Variant2.Enabled = true
		Variant3.Enabled = true
		if Check5 then
			Check11.Enabled = true
			Check12.Enabled = true
			Number3.Enabled = true
			Number4.Enabled = true
		else
			Check11.Enabled = false
			Check12.Enabled = false
			Number3.Enabled = false
			Number4.Enabled = false
		end if	
	else
		Check10.Enabled  = false
		Check5.Enabled   = false
		Variant1.Enabled = false
		Variant2.Enabled = false
		Variant3.Enabled = false
		Frame2.Enabled   = false
		Check11.Enabled  = false
		Check12.Enabled  = false
		Number3.Enabled  = false
		Number4.Enabled  = false
	end if	

   	Main = True
End Function