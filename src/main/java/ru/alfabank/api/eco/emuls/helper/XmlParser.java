package ru.alfabank.api.eco.emuls.helper;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class XmlParser {
    @SneakyThrows
    public String getFirstValue(String requestBody, String tagName) {
        if (!requestBody.contains(tagName)) {
            return "";
        }
        InputStream inputStream = new ByteArrayInputStream(requestBody.getBytes(StandardCharsets.UTF_8));

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);

        document.getDocumentElement().normalize();

        return document.getElementsByTagName(tagName).item(0).getTextContent();
    }
}
