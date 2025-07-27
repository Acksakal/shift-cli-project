import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

class FileProcessor {
    public static void processInputFiles(
            CLIConfig config,
            Map<String, Stats> statsMap,
            Set<Path> initializedOutputFiles
    ) {
        // Track types that had missing/unwritable output files
        Set<String> failedTypes = new HashSet<>();

        for (String input : config.inputFiles) {
            Path inputFile = PathUtils.resolveInputFilePath(input);

            try {
                List<String> lines = Files.readAllLines(inputFile);
                System.out.println("\u001B[32mReading file: " + inputFile + "\u001B[0m");

                for (String line : lines) {
                    line = line.trim();
                    if (line.isEmpty()) continue;

                    String type = StringUtils.typeOf(line);

                    // If we've already seen that this type failed, skip silently
                    if (failedTypes.contains(type)) continue;

                    Path outputFile = FileWriterHelper.resolveOutputFilePath(
                            config.outputDir, config.outputPrefix, type
                    );

                    boolean canWrite = true;

                    if (!config.appendMode && initializedOutputFiles.add(outputFile)) {
                        FileWriterHelper.createEmptyFile(outputFile);
                        canWrite = Files.exists(outputFile);
                    }

                    if (canWrite && Files.exists(outputFile)) {
                        FileWriterHelper.appendLine(outputFile, line);

                        if (config.collectShortStats || config.collectFullStats) {
                            statsMap.computeIfAbsent(type, _ -> new Stats()).add(line, type);
                        }

                    } else {
                        failedTypes.add(type);
                        System.out.println(
                                "\u001B[31m" + outputFile + " was not found. Skipping all lines of this type.\u001B[0m"
                        );
                    }
                }

            } catch (IOException e) {
                System.out.println(
                        "\u001B[31mCould not read file: " + inputFile +
                                ". Please check the spelling\u001B[0m"
                );
            }
        }
    }
}
