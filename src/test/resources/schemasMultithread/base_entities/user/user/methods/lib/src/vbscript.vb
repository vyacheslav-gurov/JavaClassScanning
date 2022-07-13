Public Function Main(LastControl)
	If LastControl Is Nothing Then
		' Действия при загрузке формы
		        
	Else
		' Действия при потере фокуса валидируемого контрола LastControl

	End If
	Main = True ' Результирующее значение валидатора (True или False)	
End Function
