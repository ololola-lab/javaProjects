public class SumDoubleSpace {
    public static void main(String[] args) {
        double sum = 0.0;
        String str = "";

        for (String s : args) {
            for (int i = 0; i < s.length(); i++) {
                if (!Character.isSpaceChar(s.charAt(i))) {
                    str += s.charAt(i);
                } else {
                    if (str != "") {
                        sum += Double.parseDouble(str);
                        str = "";
                    }
                }
            }
            if ( str != "" ) {
                sum += Double.parseDouble(str);
                str = "";
            }
        }
        System.out.println(sum);
    }
}