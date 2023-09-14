package ru.alfabank.api.eco.emuls.helper;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RegexExtractor {

    public String get(String body, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(body);
        String value = "";
        while (matcher.find()) {
            value = matcher.group(1);
        }
        return value;
    }

    public String getFirst(String body, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(body);
        String value = "";
        if (matcher.find()) {
            value = matcher.group(1);
        }
        return value;
    }
}
