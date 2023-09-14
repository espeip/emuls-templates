package ru.alfabank.api.eco.emuls.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.alfabank.api.eco.emuls.mock_creators.AbstractWSMockCreator;

@AllArgsConstructor
public class Wrapper {
    @Getter
    private String response;
    @Getter
    private AbstractWSMockCreator mock;
}
