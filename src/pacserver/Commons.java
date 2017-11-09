package pacserver;

import java.text.SimpleDateFormat;

public class Commons {
    /**
     * <p>
     * <b> Define constant values. </b>
     * </p>
     */
    public static final String ERR_ARGUMENT = "Only accept 2 arguments.";
    public static final String ERR_FILE_NOT_FOUND = "File Not Found: ";
    public static final String ERR_READ_FILE_ERROR = "Read File Error: ";

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String pacPath;

    public static String getJavaPath() {
        String strJavaPath = System.getProperty("java.class.path");
        if (strJavaPath.endsWith(".jar")) {
            strJavaPath = strJavaPath.substring(0, strJavaPath.lastIndexOf('/') + 1);
        }
        return strJavaPath;
    }
}
