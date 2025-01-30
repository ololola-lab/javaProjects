package md2html;

import java.util.*;

public class BlockParser {
    private List<String> blocks;
    private List<String> parsedBlocks = new ArrayList<>();
    private int closerHeaderLevel;

    BlockParser(List<String> blocks) {
        this.blocks = blocks;
        parse();
    }

    private void parse(){
        for(String block : blocks){
            parseBlock(block);
        }
    }

    private void parseBlock(String block){
        if(isHeader(block)){
            parseHeader(block);
        } else parseParagraph(block);
    }

    private boolean isHeader(String block){
        if(!block.startsWith("#")) return false;

        int headerLevel = 1;
        for(int i = 1; i < block.length(); i++){
            char current = block.charAt(i);
            if(Character.isWhitespace(current)){
                closerHeaderLevel = headerLevel;
                return true;
            }
            if(current == '#'){
                headerLevel++;
            } else {
                return false;
            }
        }
        return false;
    }

    private void parseHeader(String block){
        String content = parseContent(block.substring(closerHeaderLevel + 1));
        StringBuilder header = new StringBuilder();
        header.append("<h").append(closerHeaderLevel).append(">")
                .append(content)
                .append("</h").append(closerHeaderLevel).append(">");
        parsedBlocks.add(header.toString());
    }

    private void parseParagraph(String block){
        String content = parseContent(block);
        StringBuilder paragraph = new StringBuilder("<p>").append(content).append("</p>");
        parsedBlocks.add(paragraph.toString());
    }

    private String parseContent(String content){
        TextParser textParser = new TextParser(content);
        return textParser.toHtml();

    }

    void renderPage(StringBuilder page){
        for (String block : parsedBlocks){
            page.append(block).append("\n");
        }
    }
}
