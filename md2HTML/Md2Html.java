package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Md2Html {
    public static void main(String[] args) {
        String md2FileName = args[0];

        String htmlFileName = args[1];

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(md2FileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<String> block = new ArrayList<>();
        List<String> blocks = new ArrayList<>();

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.isEmpty()){
                if(!block.isEmpty()){
                    blocks.add(String.join("\n", block));
                    block.clear();
                }
            } else {
                block.add(line);
            }
            if(!scanner.hasNextLine())
                blocks.add(String.join("\n", block));
        }

        BlockParser blockParser = new BlockParser(blocks);

        StringBuilder page = new StringBuilder();

        blockParser.renderPage(page);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFileName)));){
            bufferedWriter.write(page.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scanner.close();

    }
}
