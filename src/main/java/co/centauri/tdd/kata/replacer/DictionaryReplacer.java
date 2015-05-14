package co.centauri.tdd.kata.replacer;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class DictionaryReplacer {

    private Map<String, String> context;
    private String result;

    public String replace(String strToReplace, Map<String, String> context) {

        this.context = context;
        this.result = strToReplace;

        if (nothingToDo()) {
            return strToReplace;
        }

        int begin = result.indexOf("${");
        while (thereIs(begin)) {
            replaceNext(begin);
            begin = result.indexOf("${", begin + 2);
        }
        return result;
    }

    private boolean nothingToDo() {
        return StringUtils.isBlank(result) || isContextEmpty();
    }

    private boolean isContextEmpty() {
        return context == null || context.isEmpty();
    }

    private boolean thereIs(int begin) {
        return begin != -1;
    }

    private void replaceNext(int begin) {
        int end = result.indexOf("}", begin);
        if (thereIs(end)) {
            String key = result.substring(begin + 2, end);
            replace(key);
        }
    }

    private void replace(String key) {
        String value = this.context.get(key);
        if (value != null) {
            result = result.replaceAll("\\$\\{" + key + "\\}", value);
        }
    }
}
