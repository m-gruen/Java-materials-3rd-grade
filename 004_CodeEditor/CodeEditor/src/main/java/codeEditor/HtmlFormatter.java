package codeEditor;

public class HtmlFormatter implements CodeFormatter {

    private static final char EOF = (char) -1;
    private int pos;
    private char ch;
    private String text;

    @Override
    public String format(String code) {
        text = code;

        StringBuilder formattedCode = new StringBuilder();
        pos = 0;
        nextChar();

        while (ch != EOF) {
            if (isHtmlLetter()) {
                formattedCode.append(readAndFormatTag());
            } else {
                formattedCode.append(ch);
                nextChar();
            }
        }
        return formattedCode.toString();
    }

    private void nextChar() {
        try {
            ch = text.charAt(pos++);
        } catch (StringIndexOutOfBoundsException e) {
            ch = EOF;
        }
    }

    private String readStringLiteral() {
        StringBuilder string = new StringBuilder();
        string.append(ch);
        nextChar();

        while (ch != '"' && ch != EOF) {
            string.append(ch);
            nextChar();
        }

        string.append(ch);
        nextChar();

        return ConsoleColor.ANSI_GREEN + string.toString() + ConsoleColor.ANSI_RESET;
    }

    private String readAndFormatTag() {
        StringBuilder tag = new StringBuilder();
        tag.append(ch);
        nextChar();

        boolean isFirst = true;

        while (ch != '>' && ch != EOF) {
            if (Character.isLetter(ch) || Character.isDigit(ch)) {
                String word = readWord();

                if (isFirst) {
                    tag.append(ConsoleColor.ANSI_RED + word + ConsoleColor.ANSI_RESET);
                    isFirst = false;
                } else {
                    tag.append(ConsoleColor.ANSI_PURPLE + word + ConsoleColor.ANSI_RESET);
                }
            } else if (ch == '"') {
                tag.append(readStringLiteral());
            } else {
                tag.append(ch);
                nextChar();
            }
        }

        return tag.toString();
    }

    private String readWord() {
        StringBuilder variable = new StringBuilder();
        while (Character.isLetter(ch)|| Character.isDigit(ch) || ch == '-') {
            variable.append(ch);
            nextChar();
        }

        return variable.toString();
    }

    private boolean isHtmlLetter() {
        return ch == '<';
    }

}
