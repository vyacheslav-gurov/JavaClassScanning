package api;

public final class LockOpenScript {
    private final static String SQL = "DECLARE \n" +
            "        n   VARCHAR2(30); \n" +
            "        t   NUMBER;\n" +
            "        loop_counter INTEGER;\n" +
            "        NOT_OPENED_LOCK_INFO EXCEPTION;\n" +
            "     BEGIN \n" +
            "       select status into n from all_objects where object_type='PACKAGE BODY' and object_name='EXECUTOR' and owner='%s';\n" +
            "       if n='VALID' then" +
            "         FOR loop_counter IN 1 .. 5\n" +
            "         LOOP\n" +
            "             BEGIN\n" +
            "               execute immediate 'select %s.executor.lock_open( null, DBMS_SESSION.UNIQUE_SESSION_ID ) from dual' into t;\n" +
            "               exit;\n" +
            "             EXCEPTION\n" +
            "               WHEN OTHERS THEN begin\n" +
            "                 sleep(1);\n" +
            "                 continue;\n" +
            "               end;\n" +
            "             END;\n" +
            "         END LOOP;\n" +
            "         IF loop_counter = 5 THEN\n" +
            "             RAISE NOT_OPENED_LOCK_INFO;\n" +
            "         END IF;\n" +
            "       end if;\n" +
            "     END;";

    public static String get(String schema) {
        return String.format(SQL, schema, schema);
    }
}

