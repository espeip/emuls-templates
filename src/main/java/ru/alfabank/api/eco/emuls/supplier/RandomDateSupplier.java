package ru.alfabank.api.eco.emuls.supplier;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.alfabank.thrift.utils.DatePeriod;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Component
public class RandomDateSupplier {
    private Instant startDate;
    private Instant endDate;
    private DateTimeFormatter formatter;

    @Autowired
    private RandomSupplier randomSupplier;

    public RandomDateSupplier() {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
    }

    public String getDate(DatePeriod datePeriod) {
        startDate = getDateInstant(datePeriod.getFromDate());
        endDate = getDateInstant(datePeriod.getToDate());
        return formatter.format(
                startDate.plus(
                        getDaysMinus(
                                ChronoUnit.DAYS.between(startDate, endDate)
                        ),
                        ChronoUnit.DAYS
                )
        );
    }

    private int getDaysMinus(Long bound) {
        return randomSupplier.getRandomNumber(bound.intValue());
    }

    private Instant getDateInstant(String date) {
        return LocalDate.parse(date).atStartOfDay(ZoneId.systemDefault()).toInstant();
    }
}
