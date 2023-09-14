package ru.alfabank.api.eco.emuls.configuration;

import lombok.Data;
import lombok.NonNull;
import ru.alfabank.api.eco.emuls.dto.ResponseType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties
public class ResponseParametersProperties {
    @NonNull
    private Map<String, List<ResponseType>> responseParameters;
}
