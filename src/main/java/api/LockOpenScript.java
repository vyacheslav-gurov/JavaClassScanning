package api;

public final class LockOpenScript {
    private final static String SQL = "DECLARE\n" +
        "  n VARCHAR2(30);\n" +
        "  t NUMBER;\n" +
        "BEGIN\n" +
        "  select status into n from all_objects where object_type='PACKAGE BODY' and object_name='EXECUTOR' and owner='%s';\n" +
        "  if n='VALID' then\n" +
        "    execute immediate 'select %s.EXECUTOR.lock_open(null, DBMS_SESSION.UNIQUE_SESSION_ID) from dual' into t;\n" +
        "  end if;\n" +
        "END;";

    public static String get(String schema) {
        return String.format(SQL, schema, schema);
    }
}

