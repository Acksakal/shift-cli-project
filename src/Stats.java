import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Map;

class Stats {
    int count = 0;

    BigInteger intSum = BigInteger.ZERO;
    BigInteger intMin = null;
    BigInteger intMax = null;

    BigDecimal floatSum = BigDecimal.ZERO;
    BigDecimal floatMin = null;
    BigDecimal floatMax = null;

    int shortestLength = Integer.MAX_VALUE;
    int longestLength = Integer.MIN_VALUE;

    void add(String line, String type) {
        count++;

        switch (type) {
            case "integers":
                try {
                    BigInteger val = new BigInteger(line);
                    intSum = intSum.add(val);
                    intMin = (intMin == null || val.compareTo(intMin) < 0) ? val : intMin;
                    intMax = (intMax == null || val.compareTo(intMax) > 0) ? val : intMax;
                } catch (NumberFormatException e) {
                    System.out.println("\u001B[31mInvalid integer: " + line + "\u001B[0m");
                }
                break;

            case "floats":
                try {
                    BigDecimal val = new BigDecimal(line);
                    floatSum = floatSum.add(val);
                    floatMin = (floatMin == null || val.compareTo(floatMin) < 0) ? val : floatMin;
                    floatMax = (floatMax == null || val.compareTo(floatMax) > 0) ? val : floatMax;
                } catch (NumberFormatException e) {
                    System.out.println("\u001B[31mInvalid float: " + line + "\u001B[0m");
                }
                break;

            case "text":
                int len = line.length();
                shortestLength = Math.min(shortestLength, len);
                longestLength = Math.max(longestLength, len);
                break;
        }
    }

    String shortSummary() {
        return "Count: " + count;
    }

    String fullSummary(String type) {
        return switch (type) {
            case "text" -> shortSummary() +
                    ", Min length: " + shortestLength +
                    ", Max length: " + longestLength;
            case "integers" -> shortSummary() +
                    ", Min: " + (intMin != null ? intMin.toString() : "N/A") +
                    ", Max: " + (intMax != null ? intMax.toString() : "N/A") +
                    ", Sum: " + intSum.toString() +
                    ", Avg: " + (count > 0
                    ? new BigDecimal(intSum).divide(BigDecimal.valueOf(count), 7, RoundingMode.HALF_UP).toPlainString()
                    : "0");
            case "floats" -> shortSummary() +
                    ", Min: " + (floatMin != null ? floatMin.toPlainString() : "N/A") +
                    ", Max: " + (floatMax != null ? floatMax.toPlainString() : "N/A") +
                    ", Sum: " + floatSum.toPlainString() +
                    ", Avg: " + (count > 0
                    ? floatSum.divide(BigDecimal.valueOf(count), 7, RoundingMode.HALF_UP).toPlainString()
                    : "0");
            default -> shortSummary();
        };
    }

    public static void printStats(Map<String, Stats> statsMap, boolean full) {
        System.out.println("\u001B[34mStatistics:\u001B[0m");
        for (Map.Entry<String, Stats> entry : statsMap.entrySet()) {
            String type = entry.getKey();
            Stats stats = entry.getValue();
            System.out.println("[" + type + "] " +
                    (full ? stats.fullSummary(type) : stats.shortSummary()));
        }
        System.out.print("\n");
    }
}
