package ru.alfabank.api.eco.emuls.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Offers {
    private String organizationId;
    private String accountNumber;
    private String name = "Проценты на остаток";
    private String type = "ABIO";
    private String startDate = "2020-11-01";
    private String expireDate = "2021-11-01";
    private boolean isActive = true;
    private boolean isHidden;
    private String offerId = "ar00000109";
    private String campaignCode = "";
    private int responseCode;
    private Map<String, String> params = new HashMap<>();
}
