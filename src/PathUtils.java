import java.nio.file.Path;
import java.nio.file.Paths;

class PathUtils {
    public static final Path HOME = Paths.get(System.getProperty("user.home"));
    public static final Path CURRENT = Paths.get(System.getProperty("user.dir"));

    public static Path resolveInputFilePath(String input) {
        if (input.startsWith("/")) {
            return HOME.resolve(input.substring(1)).normalize();
        } else {
            return CURRENT.resolve(input).normalize();
        }
    }
}
