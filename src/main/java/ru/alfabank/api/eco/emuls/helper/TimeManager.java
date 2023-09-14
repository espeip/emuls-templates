package ru.alfabank.api.eco.emuls.helper;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.alfabank.api.eco.emuls.mock_creators.AbstractWSMockCreator;

import java.util.concurrent.TimeUnit;

@Slf4j
@SuppressWarnings("all")
@Component
public class TimeManager {
    @SneakyThrows
    public static void sleep(AbstractWSMockCreator mock, long tStart) {
        long sleepTime = mock.getCurrentResponseType().getTimeoutInMilliseconds();
        long tPassed = System.currentTimeMillis() - tStart;
        if ((sleepTime - tPassed) > 0) {
            TimeUnit.MILLISECONDS.sleep(sleepTime - tPassed);
        } else {
            //log.error("Response time exceeded on mock {} on {}%", mock.getClass().getName(),
                    //((double)(tPassed - sleepTime) / sleepTime) * 100);
        }
    }
}
