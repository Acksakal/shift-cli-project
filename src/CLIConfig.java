import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class CLIConfig {
    public boolean appendMode = false;
    public boolean collectShortStats = false;
    public boolean collectFullStats = false;
    public List<String> inputFiles = new ArrayList<>();

    public Path outputDir = PathUtils.CURRENT;
    public String outputPrefix = "";

    public static CLIConfig parseArgs(String[] args) {
        CLIConfig config = new CLIConfig();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-a":
                    config.appendMode = true;
                    break;
                case "-s":
                    config.collectShortStats = true;
                    break;
                case "-f":
                    config.collectFullStats = true;
                    break;
                case "-o":
                    // If the path starts with '/', resolve it relative to the user's home directory
                    // Example: "/out" becomes "<home>/out" => C:\Users\KoronaPayDev\out
                    // Otherwise, resolve it relative to the current working directory
                    // Example: "out" becomes "<current_dir>/out"
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        String outPath = args[++i];
                        if (outPath.startsWith("/")) {
                            config.outputDir = PathUtils.HOME.resolve(outPath.substring(1)).normalize();
                        } else {
                            config.outputDir = PathUtils.CURRENT.resolve(outPath).normalize();
                        }
                    } else {
                        System.out.println("\u001B[31mMissing path after -o flag\u001B[0m");
                    }
                    break;
                case "-p":
                    if (i + 1 < args.length && !args[i + 1].startsWith("-") && !args[i + 1].startsWith("/")) {
                        config.outputPrefix = args[++i];
                    } else {
                        System.out.println("\u001B[31mMissing prefix after -p flag\u001B[0m");
                    }
                    break;
                default:
                    config.inputFiles.add(args[i]);
            }
        }

        return config;
    }
}
