import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVParser {
    private static String[] columns;

    public static void parse(String filePath) throws IOException {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);

                if (!line.endsWith("\"")) {
                    continue;
                }

                String[] columns = removeStart(
                        removeEnd(sb.toString()))
                        .split("\",\"");

                System.out.println(String.join("|", columns));

                sb.setLength(0);
            }
        }
    }

    private static String removeStart(String input) {
        if (input.startsWith("\"")) {
            return input.substring("\"".length());
        }
        return input;
    }

    private static String removeEnd(String input) {
        if (input.endsWith("\"")) {
            return input.substring(0, input.length() - "\"".length());
        }
        return input;
    }
}
