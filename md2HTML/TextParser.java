package md2html;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class TextParser {
    //occurrence

    private StringBuilder text;
    private static Map<String, String> md2Html = new HashMap();
    private Map<String, List<Integer>> tagsOccurrence = new HashMap<>();
    private Map<String, List<Integer>> escapableOccurrence = new HashMap<>();
    private String escapable = "<>&\\";

    static {
        md2Html.put("*", "em>");
        md2Html.put("_", "em>");
        md2Html.put("**", "strong>");
        md2Html.put("__", "strong>");
        md2Html.put("--", "s>");
        md2Html.put("`", "code>");
        md2Html.put("%", "var>");

        md2Html.put("<", "&lt;");
        md2Html.put(">", "&gt;");
        md2Html.put("&", "&amp;");
        md2Html.put("\\", "");
    }

    TextParser(String text) {
        this.text = new StringBuilder(text);

        for (String tag : md2Html.keySet()) {
            if(!escapable.contains(tag)){
                tagsOccurrence.put(tag, new ArrayList<>());
            } else {
                escapableOccurrence.put(tag, new ArrayList<>());
            }
        }
    }

    public String toHtml() {

        for (int i = 0; i < text.length(); i++) {
            char current = text.charAt(i);
            int substringEnd = Math.min(i + 2, text.length());
            String currentSubstring = text.substring(i, substringEnd);

            if (current == '\\') {
                escapableOccurrence.get(currentSubstring.substring(0, 1)).add(i);
                i++;
                continue;
            }

            if (tagsOccurrence.containsKey(currentSubstring)) {
                tagsOccurrence.get(currentSubstring).add(i);
                i++;
            } else if (tagsOccurrence.containsKey(currentSubstring.substring(0, 1))) {
                tagsOccurrence.get(currentSubstring.substring(0, 1)).add(i);
            } else if(escapableOccurrence.containsKey(currentSubstring.substring(0, 1))){
                escapableOccurrence.get(currentSubstring.substring(0, 1)).add(i);
            }
        }

        for (List<Integer> tagPositions : tagsOccurrence.values()) {
            if (tagPositions.size() % 2 != 0 ) {
                tagPositions.remove(tagPositions.size() - 1);
            }
        }

        List<MdTagPosition> allTagsOccurrence = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> tagOccurrence : tagsOccurrence.entrySet()) {
            for (int i = 0; i < tagOccurrence.getValue().size(); i++) {
                int position = tagOccurrence.getValue().get(i);
                allTagsOccurrence.add(new MdTagPosition(tagOccurrence.getKey(), position, i + 1));
            }
        }
        for (Map.Entry<String, List<Integer>> escapableOccurrence : escapableOccurrence.entrySet()) {
            for (int i = 0; i < escapableOccurrence.getValue().size(); i++) {
                int position = escapableOccurrence.getValue().get(i);
                allTagsOccurrence.add(new MdTagPosition(escapableOccurrence.getKey(), position, i + 1));
            }
        }


        Collections.sort(allTagsOccurrence);

        int offset = 0;
        StringBuilder result = new StringBuilder(text);
        for (MdTagPosition tagPosition : allTagsOccurrence) {
            String mdTag = tagPosition.getTag();
            String htmlTag;
            if (!escapable.contains(mdTag)) {
                htmlTag = (tagPosition.getSerial() % 2 == 0) ?
                        "</" + md2Html.get(mdTag) :
                        "<" + md2Html.get(mdTag);
            } else {
                htmlTag = md2Html.get(mdTag);
            }
            int position = tagPosition.getPosition();

            result.replace(position + offset, position + mdTag.length() + offset, htmlTag);

            offset += htmlTag.length() - mdTag.length();
        }

        return result.toString();
    }



    private class MdTagPosition implements Comparable<MdTagPosition> {
        private String tag;
        private int position;
        private int serial;

        MdTagPosition(String tag, int position, int serial) {
            this.tag = tag;
            this.position = position;
            this.serial = serial;
        }

        public String getTag() {
            return tag;
        }

        public int getPosition() {
            return position;
        }

        public int getSerial() {
            return serial;
        }

        @Override
        public int compareTo(MdTagPosition o) {
            return this.position - o.position;
        }
    }
}