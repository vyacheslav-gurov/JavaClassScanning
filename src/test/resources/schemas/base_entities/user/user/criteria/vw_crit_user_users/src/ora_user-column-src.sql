DECODE((
			select /*+ rule */  1 A$1
			from ALL_USERS e1
			where e1.USERNAME = b1.USERNAME
		),1,'Да','Нет')