package ru.alfabank.api.eco.emuls.dto;

import lombok.Data;

@Data
public class ResponseType {
    private int timeoutInMilliseconds;
    private int totalBytesOut;
    private double probabilityInPercents;
    private String pathToResponse;
}
