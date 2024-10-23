package codeEditor;

public class CodeEditor {

    private CodeFormatter codeFormatter;

    private StringBuilder text;

    public CodeEditor(CodeType codeType) {
        text = new StringBuilder();
        setCodeType(codeType);
    }

    public void setCodeType(CodeType codeType) {
        switch (codeType) {
            case FORMULA:
                codeFormatter = new FormulaFormatter();
                break;
            case HTML:
                codeFormatter = new HtmlFormatter();
                break;
            case JAVA:
                codeFormatter = new JavaFormatter();
                break;
            default:
                codeFormatter = new DefaultFormatter();
                break;
        }
    }

    public void appendLine(String line) {
        text.append(line);
        text.append("\n");
    }

    public void deleteAll() {
        text.delete(0, text.length());
    }

    public void print() {
        System.out.println(codeFormatter.format(text.toString()));
    }

}
