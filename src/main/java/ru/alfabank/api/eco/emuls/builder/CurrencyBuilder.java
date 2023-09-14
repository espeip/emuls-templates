package ru.alfabank.api.eco.emuls.builder;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.alfabank.api.eco.emuls.supplier.RandomSupplier;
import ru.alfabank.thrift.entities.Currency;

@Component
public class CurrencyBuilder {

    @Autowired
    private RandomSupplier randomSupplier;

    public Currency build() {
        return new Currency()
                .setCode(1)
                .setFullName("RUBLE")
                .setMinorUnits(randomSupplier.getRandomNumber(1, 1000))
                .setUnicodeSymbol("R")
                .setMnemonicCode("RUR");
    }
}
