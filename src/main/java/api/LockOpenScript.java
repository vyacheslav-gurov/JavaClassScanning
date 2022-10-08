package api;

public final class LockOpenScript {
    private final static String SQL = "DECLARE \n" +
            "        t   INTEGER; \n" +
            "        loop_counter INTEGER;\n" +
            "        NOT_OPENED_LOCK_INFO EXCEPTION;\n" +
            "     BEGIN \n" +
            "         FOR loop_counter IN 1 .. 5\n" +
            "         LOOP\n" +
            "             BEGIN\n" +
            "                t :=   %s.executor.lock_open( null, DBMS_SESSION.UNIQUE_SESSION_ID );\n" +
            "                exit;" +
            "             EXCEPTION\n" +
            "                 WHEN OTHERS THEN\n" +
            "                 continue;\n" +
            "             END;\n" +
            "         END LOOP;\n" +
            "         IF loop_counter = 5 THEN\n" +
            "             RAISE NOT_OPENED_LOCK_INFO;\n" +
            "         END IF;\n" +
            "     END;";

    public static String get(String schema) {
        return String.format(SQL, schema);
    }
}

