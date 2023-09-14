package ru.alfabank.api.eco.emuls.supplier;

import lombok.AllArgsConstructor;
import ru.alfabank.api.eco.emuls.configuration.ResponseParametersProperties;
import ru.alfabank.api.eco.emuls.dto.ResponseType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
@AllArgsConstructor
public class RandomResponseTypeSupplier {
    private static final int MIN_PERCENTAGE = 0;
    private static final int MAX_PERCENTAGE = 10000;

    private final ResponseParametersProperties properties;
    private final RandomSupplier randomNumberSupplier;

    public ResponseType getRandomResponseType(String wsName) {
        List<ResponseType> scaledResponseTypes = getScaledResponseTypes(wsName);
        int randomNumber = randomNumberSupplier.getRandomNumber(MIN_PERCENTAGE, MAX_PERCENTAGE);
        return scaledResponseTypes.get(randomNumber);
    }

    private List<ResponseType> getScaledResponseTypes(String wsName) {
        List<ResponseType> responseTypes = getResponseTypes(wsName);
        List<ResponseType> scaledResponseTypes = new ArrayList<>();
        responseTypes.forEach(responseType -> {
            int repeatTimes = getRepeatTimes(responseType);
            fillInScaledResponseTypesList(scaledResponseTypes, repeatTimes, responseType);
        });
        return scaledResponseTypes;
    }

    private int getRepeatTimes(ResponseType responseType) {
        double probabilityInPercents = responseType.getProbabilityInPercents();
        return (int) (probabilityInPercents * 100);
    }

    private List<ResponseType> getResponseTypes(String wsName) {
        return this.properties.getResponseParameters().get(wsName);
    }

    private void fillInScaledResponseTypesList(List<ResponseType> scaledResponseTypes,
                                               int repeatTimes,
                                               ResponseType responseType) {
        IntStream.range(0, repeatTimes).mapToObj(i -> responseType).forEach(scaledResponseTypes::add);
    }
}
