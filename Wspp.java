import java.io.*;
import java.util.*;

public class Wspp {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Map<String, Collection<Integer>> wordcount = new LinkedHashMap();
        try (MyScanner in = new MyScanner(new File(args[0]), "UTF-8")) {
            int index = 1;
            while(in.hasNextWord()) {
                    String str = in.nextWord();
                    if (!wordcount.containsKey(str)) {
                        Collection<Integer> values = new ArrayList<>();
                        values.add(index);
                        wordcount.put(str, values);
                    } else {
                        wordcount.get(str).add(index);
                    }
                    index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "UTF-8"))) {
            for (String key : wordcount.keySet()) {
                writer.write(key + " " + wordcount.get(key).size());
                for (Integer index1 : wordcount.get(key)) {
                    writer.write(" " + index1);
                }
                writer.write(System.lineSeparator());

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
