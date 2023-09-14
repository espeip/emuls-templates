package ru.alfabank.api.eco.emuls;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        value = {
                "spring.cloud.bootstrap.enabled=false",
                "spring.jackson.serialization.indent_output=true",
                "spring.sleuth.feign.enabled=false"
        }
)
class ApplicationContextIT {

    @Autowired
    private Application application;

    @Test
    void checkContextStarts() {
        Assertions.assertNotNull(application);
    }
}
