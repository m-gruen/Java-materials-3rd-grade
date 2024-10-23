package codeEditor;

import java.util.Arrays;
import java.util.List;

public class JavaFormatter implements CodeFormatter {

    private static final char EOF = (char) -1;
    private static final List<String> JAVA_KEYWORDS = Arrays.asList(
            "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class",
            "const", "continue", "default", "do", "double", "else", "enum", "extends", "final",
            "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int",
            "interface", "long", "native", "new", "package", "private", "protected", "public",
            "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this",
            "throw", "throws", "transient", "try", "void", "volatile", "while", "module", "open",
            "opens", "permits", "provides", "record", "requires", "sealed", "to", "transitive",
            "uses", "var", "with", "yield");

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
            switch (ch) {
                case 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                        'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                        'w', 'x', 'y', 'z',
                        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                        'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
                        'W', 'X', 'Y', 'Z':
                    formattedCode.append(readAndFormatWord());
                    break;
                case '/':
                    formattedCode.append(readAndFormatComment());
                    break;
                case '"':
                    formattedCode.append(readAndFormatString());
                    break;
                default:
                    formattedCode.append(ch);
                    nextChar();
                    break;
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

    private String readAndFormatWord() {
        StringBuilder variable = new StringBuilder();
        while (Character.isLetter(ch)) {
            variable.append(ch);
            nextChar();
        }

        // check if variable is a function or a variable and format it accordingly
        if (JAVA_KEYWORDS.contains(variable.toString())) {
            return ConsoleColor.ANSI_BLUE + variable.toString() + ConsoleColor.ANSI_RESET;
        } else {
            return variable.toString();
        }

    }

    private String readAndFormatComment() {
        StringBuilder comment = new StringBuilder();

        while (ch != '\n' && ch != EOF) {
            comment.append(ch);
            nextChar();
        }

        return ConsoleColor.ANSI_GREY + comment.toString() + ConsoleColor.ANSI_RESET;
    }

    private String readAndFormatString() {
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

}   
