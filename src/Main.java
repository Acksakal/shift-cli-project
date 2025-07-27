import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        // Parse command-line arguments into a configuration object
        CLIConfig config = CLIConfig.parseArgs(args);


        // Ensure output directory exists and disallow patterns like "-o file.txt"
        config.outputDir = FileWriterHelper.ensureOutputDirectory(config.outputDir);


        // Tracks which output files have already been initialized (e.g., cleared if not appending)
        Set<Path> initializedOutputFiles = new HashSet<>();


        // Tracks statistics per input type (integer, float, text)
        Map<String, Stats> statsMap = new HashMap<>();


        // Process input files and write to output files accordingly
        FileProcessor.processInputFiles(config, statsMap, initializedOutputFiles);


        // Print collected statistics if requested via flags
        if ((config.collectShortStats || config.collectFullStats) && !statsMap.isEmpty()) {
            Stats.printStats(statsMap, config.collectFullStats);
        }
    }
}
