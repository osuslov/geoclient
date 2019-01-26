package gov.nyc.doitt.gis.geoclient.gradle;

import java.io.File;
import java.util.Objects;

public class FormatUtils {

    public static String format(PropertySource s) {
        return format("%16s %-16s: %-32s", "[" + s.getType().toString().toUpperCase() + "]", s.getName(), s.getValue());	
    }

    public static String format(String template, Object... args) {
        return String.format(template, args);
    }

    public static String format(RuntimeProperty p) {
        p.getSources().get().stream().forEach(e -> String.format("%s -> %s", e, e.getName()));
    }


    public static String normalize(File parentDir, String subPath) {
        return normalize(String.format("%s/%s", parentDir, subPath));
    }

    public static String normalize(String path) {
        Objects.requireNonNull(path, "Path string cannot be null");
        return path.replaceAll("\\\\", "/");
    }

    private FormatUtils() {}
}
