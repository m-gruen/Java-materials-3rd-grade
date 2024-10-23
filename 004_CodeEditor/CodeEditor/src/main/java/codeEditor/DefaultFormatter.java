package codeEditor;

public class DefaultFormatter implements codeEditor.CodeFormatter {

    @Override
    public String format(String code) {
        return code;
    }

}
