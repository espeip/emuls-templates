package ru.alfabank.api.eco.emuls.mock_creators.some_mock;

import org.springframework.stereotype.Component;
import ru.alfabank.api.eco.emuls.mock_creators.AbstractWSMockCreator;

/**
    Пример класса-мока, принимающего запрос из Rest-контроллера.
    Метод fillInResponseTemplate переопределяется для параметризации(подставлении из запроса в шаблон ответа)
    Вызов super.mock необходим для отрабатывания задержки и подгрузки шаблона ответа
 */

@Component
public class SomeMock extends AbstractWSMockCreator {
    @Override
    protected String fillInResponseTemplate(String requestBody, String responseTemplate) {
        return super.fillInResponseTemplate(requestBody, responseTemplate);
    }

    @Override
    public String mock(String requestBody) {
        return super.mock(requestBody);
    }

}
