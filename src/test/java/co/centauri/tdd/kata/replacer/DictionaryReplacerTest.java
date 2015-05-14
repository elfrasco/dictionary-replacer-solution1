package co.centauri.tdd.kata.replacer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DictionaryReplacerTest {

    private DictionaryReplacer replacer;

    @Before
    public void init() {
        replacer = new DictionaryReplacer();
    }

    @Test
    public void emptyParams() {
        String strToReplace = "";
        Map<String, String> context = Collections.emptyMap();

        String result = replacer.replace(strToReplace, context);

        Assert.assertEquals("", result);
    }

    @Test
    public void oneParam() {
        String strToReplace = "${temp}";
        Map<String, String> context = createContextWithOneParam();

        String result = replacer.replace(strToReplace, context);

        Assert.assertEquals("temporary", result);
    }

    private Map<String, String> createContextWithOneParam() {
        Map<String, String> context = new HashMap<>();
        context.put("temp", "temporary");
        return context;
    }

    @Test
    public void twoParams() {
        String strToReplace = "${temp} here comes the name ${name}";
        Map<String, String> context = createContextWithTwoParams();

        String result = replacer.replace(strToReplace, context);

        Assert.assertEquals("temporary here comes the name John Doe", result);
    }

    private Map<String, String> createContextWithTwoParams() {
        Map<String, String> context = createContextWithOneParam();
        context.put("name", "John Doe");
        return context;
    }

    @Test
    public void invalidPlaceholder() {
        String strToReplace = "Hello ${Mr. Jones! How are you?";
        Map<String, String> context = createContextWithTwoParams();

        String result = replacer.replace(strToReplace, context);

        Assert.assertEquals("Hello ${Mr. Jones! How are you?", result);
    }

    @Test
    public void variableNotFound() {
        String strToReplace = "Hello ${name}! How are you?";
        Map<String, String> context = Collections.emptyMap();

        String result = replacer.replace(strToReplace, context);

        Assert.assertEquals("Hello ${name}! How are you?", result);
    }

    @Test
    public void oneVariableFoundAndAnotherNotFound() {
        String strToReplace = "Hello ${name}! How are you? You are the ${role} in this project.";
        Map<String, String> context = createContextWithTwoParams();

        String result = replacer.replace(strToReplace, context);

        Assert.assertEquals("Hello John Doe! How are you? You are the ${role} in this project.", result);
    }

    @Test
    public void nullStrToReplace() {
        String strToReplace = null;
        Map<String, String> context = createContextWithTwoParams();

        String result = replacer.replace(strToReplace, context);

        Assert.assertNull(result);
    }

    @Test
    public void nullContext() {
        String strToReplace = "Invalid ${context}";
        Map<String, String> context = null;

        String result = replacer.replace(strToReplace, context);

        Assert.assertEquals("Invalid ${context}", result);
    }

}
