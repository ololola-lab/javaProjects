import java.io.*;

class MyScanner implements AutoCloseable{
    private Reader reader;
    private char separator = System.lineSeparator().charAt(System.lineSeparator().length() - 1);
    private char[] buffer = new char[10];
    private boolean endReached = false;
    private int currentBlockCapacity = 0;
    private int currentIndex = 0;
    private int closerInt = 0;
    private String closerLine = "";
    private boolean closerIntWasRead = false;
    private boolean closerLineWasRead = false;


    public MyScanner(InputStream is) {
        this.reader = new InputStreamReader(is);
        readInBuffer();
    }

    public MyScanner(String string) {
        this.reader = new StringReader(string);
        readInBuffer();
    }

    public MyScanner(File file, String encoding) throws IOException {
        this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
        readInBuffer();
    }

    private boolean readInBuffer() {
        try {
            currentBlockCapacity = reader.read(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (currentBlockCapacity == -1) {
            endReached = true;
            return false;
        }

        currentIndex = 0;
        return true;
    }

    private boolean isSeparator(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == '\u2028' || c == '\u2029' || c == '\u0085';
    }

    public int nextInt() {
        if (closerIntWasRead) {
            closerIntWasRead = false;
            return closerInt;
        } else {
            if (hasNextInt()) {
                return nextInt();
            } else throw new RuntimeException();
        }
    }

    public boolean hasNextInt() {
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            if (!streamIsAvailable()) {
                if (stringBuilder.length() != 0) {
                    break;
                } else return false;

            }
            if (isSeparator(buffer[currentIndex]) || Character.isWhitespace(buffer[currentIndex])) {
                currentIndex++;
                if (stringBuilder.length() != 0) {
                    break;
                } else {
                    continue;
                }
            }
            if (Character.isDigit(buffer[currentIndex])) {
                stringBuilder.append(buffer[currentIndex++]);
            } else if(buffer[currentIndex] == Character.DASH_PUNCTUATION || buffer[currentIndex] == 45){
                if(stringBuilder.length() == 0){
                    stringBuilder.append(buffer[currentIndex++]);
                } else return false;
            } else return false;
        }

        closerInt = Integer.parseInt(stringBuilder.toString());
        closerIntWasRead = true;
        return true;
    }

    public String nextLine() {
        if (closerLineWasRead) {
            closerLineWasRead = false;
            return closerLine;
        } else {
            if (hasNextLine()) {
                return nextLine();
            } else throw new RuntimeException();
        }
    }

    public boolean hasNextLine() {
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            if (!streamIsAvailable()) {
                if (stringBuilder.length() != 0) {
                    break;
                } else return false;

            }
            if (buffer[currentIndex] == separator) {
                currentIndex++;
                break;
            }
            stringBuilder.append(buffer[currentIndex++]);
        }
        closerLine = stringBuilder.toString();
        closerLineWasRead = true;
        return true;
    }

    public boolean hasNextWord() {
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            if (!streamIsAvailable()) {
                if (stringBuilder.length() != 0) {
                    break;
                } else return false;

            }
            if (!(buffer[currentIndex] == '\'' || Character.getType(buffer[currentIndex]) == Character.DASH_PUNCTUATION || Character.isLetter(buffer[currentIndex]))) {
                currentIndex++;
                if (stringBuilder.length() != 0) {
                    break;
                }
            } else{
                stringBuilder.append(Character.toLowerCase(buffer[currentIndex++]));
            }
        }

        closerLine = stringBuilder.toString();
        closerLineWasRead = true;
        return true;
    }

    public String nextWord() {
        if (closerLineWasRead) {
            closerLineWasRead = false;
            return closerLine;
        } else {
            if (hasNextWord()) {
                return nextWord();
            } else throw new RuntimeException();
        }
    }


    private boolean streamIsAvailable() {
        if(endReached) return false;

        boolean isAvailable;
        if (currentIndex == currentBlockCapacity) {
            isAvailable = readInBuffer();
            if (!isAvailable) {
                return false;
            }
        }
        return true;
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
        }
    }


}
