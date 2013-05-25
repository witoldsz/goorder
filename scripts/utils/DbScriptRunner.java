import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This script reads STDIN (as UTF-8), splits by semicolon, and executes command by command.
 * Three arguments are required: database URL, username and password.
 * Remember to set classpath for database driver.
 * @author Witold Szczerba
 */
public class DbScriptRunner {

    public static void main(String[] args) throws Exception {
        String url = args[0];
        String user = args[1];
        String pass = args[2];
        String script;

        try (InputStreamReader reader = new InputStreamReader(System.in, "UTF-8")) {
            StringBuilder sb = new StringBuilder();
            char[] buffer = new char[1000];
            int n;
            while (-1 != (n = reader.read(buffer))) {
                sb.append(buffer, 0, n);
            }
            script = sb.toString();
        }

        String[] commands = script.split(";\\s*\n"); //semicolon must be at the end of line

        System.out.println("sql> connecting '" + user + "' to " + url);
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            conn.setAutoCommit(true);
            for (String command : commands) {
                command = command.replaceFirst("[\\s\\n]*", ""); //remove leading empty lines and spaces
                if (command.isEmpty()) {
                    continue;
                }
                System.out.println("sql> " + command);
                try {
                    conn.prepareStatement(command).execute();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    System.exit(1);
                }
            }
        }
    }
}