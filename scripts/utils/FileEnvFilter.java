import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class filters file (passed as first argument) with system environment entries to standard out.
 * If file name ends with .xml or .properties it applies proper escaping.
 * @author Witold Szczerba
 */
public class FileEnvFilter {

    private static final Pattern ENV_PATTERN = Pattern.compile("\\$\\{?([a-zA-Z_]+)\\}?");

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            error("Invalid parameters count: " + args.length + ".\n"
                    + "One parameter (source file) is required.");
        }
        String sourceName = args[0];
        BufferedReader src = null;
        try {
            src = new BufferedReader(new FileReader(sourceName));
            new FileEnvFilter(System.getenv(), sourceName, src, System.out).filter();
        } catch (FileNotFoundException ex) {
            error("Cannot find file: " + sourceName);
        } finally {
            src.close();
        }
    }

    private static final void error(String message) {
        System.err.append(message);
        System.exit(1);
    }

    private final Map<String, String> environment;

    private final String sourceName;

    private final BufferedReader source;

    private final PrintStream out;

    public FileEnvFilter(Map<String, String> environment, String sourceName, BufferedReader source, PrintStream out) {
        this.environment = environment;
        this.source = source;
        this.out = out;
        this.sourceName = sourceName;
    }

    public void filter() throws Exception {
        String line;
        while ((line = source.readLine()) != null) {
            int previousEnd = 0;
            Matcher matcher = ENV_PATTERN.matcher(line);
            while (matcher.find()) {
                String key = matcher.group(1);
                String value = valueOf(key);
                out.append(line, previousEnd, matcher.start());
                out.append(value);
                previousEnd = matcher.end();
            }
            if (previousEnd < line.length()) {
                out.append(line, previousEnd, line.length());
            }
            out.append('\n');
        }
    }

    private String valueOf(String key) {
        String value = environment.get(key);
        return value == null ? "" : escapeValue(value);
    }

    private String escapeValue(String value) {
        if (sourceName.endsWith(".xml")) {
            return escapeXML(value);
        }
        if (sourceName.endsWith(".properties")) {
            return escapeProperties(value);
        }
        return value;
    }

    private String escapeXML(String value) {
        return value //watch out, the '&' must be first!
                .replace("&", "&amp;")
                .replace("\"", "&quot;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }

    private String escapeProperties(String value) {
        return value.replace("=", "\\=");
    }
}
