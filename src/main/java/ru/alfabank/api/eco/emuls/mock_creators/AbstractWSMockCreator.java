package ru.alfabank.api.eco.emuls.mock_creators;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alfabank.api.eco.emuls.builder.ResponseTemplateBuilder;
import ru.alfabank.api.eco.emuls.dto.ResponseType;
import ru.alfabank.api.eco.emuls.helper.ResourceReader;
import ru.alfabank.api.eco.emuls.supplier.RandomResponseTypeSupplier;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Slf4j
public class AbstractWSMockCreator {

    @Getter
    protected ResponseType currentResponseType;
    @Autowired
    protected ResponseTemplateBuilder responseBuilder;
    @Autowired
    private RandomResponseTypeSupplier randomResponseTypeSupplier;
    @Autowired
    private ResourceReader resourceReader;

    public String mock(String requestBody) {
        currentResponseType = getResponseType();
        String responseTemplate = getResponse(currentResponseType);
        return fillInResponseTemplate(requestBody, responseTemplate);
    }

    protected String createResponseTemplate(ResponseType currentResponseType) {
        log.error("createResponseTemplate(ResponseType currentResponseType) is not overridden");
        return "Необходимо переопределить метод createResponseTemplate(ResponseType currentResponseType) в классе Вашего мока.";
    }

    protected String fillInResponseTemplate(String requestBody, String responseTemplate) {
        return responseTemplate;
    }

    private String getResponse(ResponseType currentResponseType) {
        String pathToResponse = currentResponseType.getPathToResponse();
        if (isNotEmpty(pathToResponse)) {
            return resourceReader.readResource(pathToResponse);
        }
        return createResponseTemplate(currentResponseType);
    }

    private ResponseType getResponseType() {
        return randomResponseTypeSupplier.getRandomResponseType(this.getClass().getSimpleName());
    }
}
