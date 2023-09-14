package ru.alfabank.api.eco.emuls.dto;

import lombok.Data;

import java.util.List;

@Data
public class OffersResponse {
    private List<Offers> offers;
    private String errorText;
}
