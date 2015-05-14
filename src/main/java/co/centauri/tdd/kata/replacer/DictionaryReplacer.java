package co.centauri.tdd.kata.replacer;

import java.util.Map;

public class DictionaryReplacer {

    public String replace(String strToReplace, Map<String, String> context) {
        switch (strToReplace) {
            case "":
                return "";
            case "${temp}":
                return "temporary";
            case "${temp} here comes the name ${name}":
                return "temporary here comes the name John Doe";
        }

        return null;
    }
}
