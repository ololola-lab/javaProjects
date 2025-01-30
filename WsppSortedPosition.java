import java.io.*;
import java.util.*;

public class WsppSortedPosition {
    public static void main(String[] args){
        Map<String, Map<Integer, List<Integer>>> wordcount = new TreeMap<>(); // :NOTE: Map of Map?
        Map<String, Integer> wordCounts = new TreeMap<>();
        Map<Integer, Integer> lineWordCounts = new HashMap<>();
        try (MyScanner in = new MyScanner(new File(args[0]), "UTF-8")) { // :NOTE: StandardCharset.UTF_8
            int lineIndex = 1;
            while (in.hasNextLine()) {
                String line = in.nextLine();
                int lineWordCount = 0; 
                MyScanner inline = new MyScanner(line);
                while (inline.hasNextWord()) {
                    lineWordCount++;
                    String str = inline.nextWord();
                    if (!wordcount.containsKey(str)) { // :NOTE: getOrDefault / putIfAbsent / computeIfAbsent
                        wordcount.put(str, new TreeMap<>());
                        wordCounts.put(str, 1);
                    } else {
                        int count = wordCounts.get(str);
                        wordCounts.put(str, count + 1);
                    }
                    if (!wordcount.get(str).containsKey(lineIndex)) {
                        wordcount.get(str).put(lineIndex, new ArrayList<>());
                    }
                    wordcount.get(str).get(lineIndex).add(lineWordCount);
                }
                lineWordCounts.put(lineIndex, lineWordCount);
                lineIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            // :NOTE: return
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "UTF-8"))) { // :NOTE: try-with-resources theory
            for (Map.Entry<String, Map<Integer, List<Integer>>> entry : wordcount.entrySet()) {
                writer.write(entry.getKey() + " " + wordCounts.get(entry.getKey()));
                for (Map.Entry<Integer, List<Integer>> indexes : entry.getValue().entrySet()) {
                    int lineNumber = indexes.getKey();
                    int lineWordCount = lineWordCounts.get(lineNumber);
                    for (Integer wordIndex : indexes.getValue()) {
                        writer.write(" " + lineNumber + ":" + (lineWordCount - wordIndex + 1));
                    }
                }
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
