DECLARE c varchar2(30);
BEGIN c := 'USER';
if not inserting then
	if not ((:NEW.C_NAME is null and :OLD.C_NAME is null) or NVL((:NEW.C_NAME = :OLD.C_NAME),false)) or deleting then
		rtl.log_vals(:OLD.ID, 'NAME', 'STRING', :OLD.C_NAME, c);
	end if;
	if not ((:NEW.C_USERNAME is null and :OLD.C_USERNAME is null) or NVL((:NEW.C_USERNAME = :OLD.C_USERNAME),false)) or deleting then
		rtl.log_vals(:OLD.ID, 'USERNAME', 'STRING', :OLD.C_USERNAME, c);
	end if;
	if not ((:NEW.C_DEPART is null and :OLD.C_DEPART is null) or NVL((:NEW.C_DEPART = :OLD.C_DEPART),false)) or deleting then
		rtl.log_vals(:OLD.ID, 'DEPART', 'REFERENCE', :OLD.C_DEPART||'.'||Z#DEPART#INTERFACE.class$(:OLD.C_DEPART,false)||'.'||Z#DEPART#INTERFACE.g#code(:OLD.C_DEPART), c);
	end if;
end if;
if valmgr.trigger_flag then
	if inserting then rtl.log_vals(:new.id,'#NEWOBJ#','STRUCTURE',nvl(:new.C_NAME,c),c);
	elsif deleting then rtl.log_vals(:old.id,'#DELOBJ#','STRUCTURE',nvl(:old.C_NAME,c),c); end if;
end if;
END;








