import java.util.HashMap;
import java.util.Map;

public class Normaliser {
    private Map<String, String> normalizedTitles;

    public Normaliser() {
        normalizedTitles = new HashMap<>();
        normalizedTitles.put("architect", "Architect");
        normalizedTitles.put("software engineer", "Software Engineer");
        normalizedTitles.put("quantity surveyor", "Quantity Surveyor");
        normalizedTitles.put("accountant", "Accountant");
    }

    public String normalise(String inputTitle) {
      String lowerInputTitle = inputTitle.toLowerCase();
      double bestScore = Double.MAX_VALUE;
      String bestMatch = "Unknown";

      for (Map.Entry<String, String> entry : normalizedTitles.entrySet()) {
        String key = entry.getKey();
        double score = computeLevenshteinDistance(lowerInputTitle, key);
        if (score < bestScore) {
          bestScore = score;
          bestMatch = entry.getValue();
        }
      }

      return bestMatch;
    }

    public String normaliseWithData(String inputTitle){
      String lowerInputTitle = inputTitle.toLowerCase();
      double bestScore = Double.MAX_VALUE;
      String bestMatch = "Unknown";

      for (Map.Entry<String, String> entry : normalizedTitles.entrySet()) {
        String key = entry.getKey();
        double score = computeLevenshteinDistance(lowerInputTitle, key);
        if (score < bestScore) {
          bestScore = score;
          bestMatch = entry.getValue();
        }
      }
      double quality = (lowerInputTitle.length() - bestScore) / lowerInputTitle.length();
      quality = Math.round(quality * 100.0) / 100.0;
      return inputTitle + " => " + bestMatch + " (" + quality + ")";
    }

    private int computeLevenshteinDistance(String input, String key) {
        int[] costs = new int[key.length() + 1];
        
        for (int inputIndex = 0; inputIndex <= input.length(); inputIndex++) {
            int lastValue = inputIndex;
            for (int keyIndex = 0; keyIndex <= key.length(); keyIndex++) {
                if (inputIndex == 0) {
                    costs[keyIndex] = keyIndex;
                } else {
                    if (keyIndex > 0) {
                        int newValue = costs[keyIndex - 1];
                        if (input.charAt(inputIndex - 1) != key.charAt(keyIndex - 1)) {
                            newValue = Math.min(Math.min(newValue, lastValue), costs[keyIndex]) + 1;
                        }
                        costs[keyIndex - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (inputIndex > 0) {
                costs[key.length()] = lastValue;
            }
        }
        return costs[key.length()];
    }
}
