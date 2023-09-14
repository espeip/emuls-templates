package ru.alfabank.api.eco.emuls.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.alfabank.api.eco.emuls.supplier.RandomSupplier;
import ru.alfabank.thrift.entities.Amount;

@Component
public class AmountBuilder {

    @Autowired
    private RandomSupplier randomSupplier;

    @Autowired
    private CurrencyBuilder currencyBuilder;

    public Amount build() {
        return new Amount()
                .setAmount(randomSupplier.getRandomNumber(100000, Integer.MAX_VALUE))
                .setCurrency(currencyBuilder.build());
    }
}
