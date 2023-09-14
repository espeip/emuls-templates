package ru.alfabank.api.eco.emuls.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.alfabank.api.eco.emuls.supplier.RandomSupplier;

import static org.apache.commons.lang.StringUtils.replace;

@Component
@RequiredArgsConstructor
public class StringReplacer {

    private static final String UPIN = "U";
    private static final String XPIN = "^X.";

    private final RegexExtractor regexExtractor;
    private final RandomSupplier randomNumberSupplier;

    public String replaceRegex(String xml, String responseTemplate, String regex, String searchString) {
        String extracted = regexExtractor.get(xml, regex);
        if (!extracted.isEmpty()) {
            return replace(responseTemplate, searchString, extracted);
        }
        return responseTemplate;
    }
}
