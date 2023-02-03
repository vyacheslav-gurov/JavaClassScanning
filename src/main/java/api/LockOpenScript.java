package api;

public final class LockOpenScript {
    private final static String SQL = "DECLARE\n" +
        "  n NUMBER;\n" +
        "BEGIN\n" +
        "  select count(1) into n from all_objects where status='VALID'" +
        "    and object_type='PACKAGE BODY' and object_name='EXECUTOR' and owner='%s';\n" +
        "  if n>0 then\n" +
        "    execute immediate 'declare n NUMBER; begin n := %s.EXECUTOR.lock_open(null, DBMS_SESSION.UNIQUE_SESSION_ID); end';\n" +
        "  end if;\n" +
        "END;";

    public static String get(String schema) {
        return String.format(SQL, schema, schema);
    }
}

