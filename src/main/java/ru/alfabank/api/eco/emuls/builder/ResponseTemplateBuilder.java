package ru.alfabank.api.eco.emuls.builder;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import ru.alfabank.api.eco.emuls.dto.ResponseType;
import ru.alfabank.api.eco.emuls.helper.ResourceReader;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

import static org.apache.commons.lang3.StringUtils.replace;
import static ru.alfabank.api.eco.emuls.constants.SearchStrings.RESPONSE;

@Component
@AllArgsConstructor
public class ResponseTemplateBuilder {

    private final ResourceReader resourceReader;

    public String build(ResponseType currentResponseType, String envelopeResource, String resultSetRowResource) {
        int totalBytesOut = currentResponseType.getTotalBytesOut();
        String resultSetRow = resourceReader.readResource(resultSetRowResource);
        String envelope = resourceReader.readResource(envelopeResource);
        String resultSet = getResultSet(totalBytesOut, resultSetRow, envelope);
        return replace(envelope, RESPONSE, resultSet);
    }

    public String buildSharedSettings(String applicationUrl, String result) {
        String replacedApplicationUrl = StringUtils.replace(applicationUrl, "/", "");
        return StringUtils.replace(
                result,
                replacedApplicationUrl + "\", \"selected\": false",
                replacedApplicationUrl + "\", \"selected\": true"
        );
    }

    private String getResultSet(int totalBytesOut, String resultSetRow, String envelope) {
        int repeatTimes = getRepeatTimes(totalBytesOut, resultSetRow, envelope);
        String resultSet = resultSetRow.repeat(Math.max(0, repeatTimes));
        return rightTrim(addLineSeparator(resultSet));
    }

    private String addLineSeparator(String resultSet) {
        return resultSet.isEmpty() ? resultSet : System.lineSeparator() + resultSet;
    }

    private String rightTrim(String s) {
        int i = s.length() - 1;
        while (i >= 0 && Character.isWhitespace(s.charAt(i))) {
            i--;
        }
        return s.substring(0, i + 1);
    }

    private int getRepeatTimes(int totalBytesOut, String resultSetRow, String envelope) {
        int envelopeBytesOut = getBytesOut(envelope) - getBytesOut(RESPONSE);
        int resultSetRowBytesOut = getBytesOut(resultSetRow);
        return resultSetRowBytesOut != 0 ? (totalBytesOut - envelopeBytesOut) / resultSetRowBytesOut : 0;
    }

    @SneakyThrows
    private int getBytesOut(String envelope) {
        return envelope.getBytes(StandardCharsets.UTF_8).length;
    }
}
