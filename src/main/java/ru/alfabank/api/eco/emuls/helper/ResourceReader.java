package ru.alfabank.api.eco.emuls.helper;

import com.google.common.io.Resources;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.org.apache.commons.io.FileUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class ResourceReader {

    @SneakyThrows
    @Cacheable("resources")
    public String readResource(String fileName) {
        return FileUtils.readFileToString(
                new File(Resources.getResource(fileName)
                        .toURI()),
                StandardCharsets.UTF_8
        );
    }
}
