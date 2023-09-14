package ru.alfabank.api.eco.emuls.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.alfabank.api.eco.emuls.supplier.RandomDateSupplier;
import ru.alfabank.api.eco.emuls.supplier.RandomSupplier;
import ru.alfabank.thrift.entities.TMulticurrencyAmount;
import ru.alfabank.thrift.transactions.TPrintDocument;
import ru.alfabank.thrift.utils.DatePeriod;

@Component
public class TPrintDocumentBuilder {

    @Autowired
    private RandomSupplier randomSupplier;

    @Autowired
    private RandomDateSupplier randomDateSupplier;

    @Autowired
    private AmountBuilder amountBuilder;

    public TPrintDocument build(DatePeriod datePeriod) {
        return new TPrintDocument()
                .setId(randomSupplier.getRandomStringNumber(12))
                .setAmount(
                        new TMulticurrencyAmount(
                                amountBuilder.build(),
                                amountBuilder.build()
                        ))
                .setDateExecute(randomDateSupplier.getDate(datePeriod))
                .setParentDocumentDate(randomDateSupplier.getDate(datePeriod))
                .setPurpose("Payment purpose")
                .setDocType("01")
                .setNumber(randomSupplier.getRandomStringNumber(12))
                .setKbk(randomSupplier.getRandomStringNumber(20))
                .setTaxPeriod("КВ.01.2021")
                .setTaxPaymentReason("ТП")
                .setTaxPaymentNumber(randomSupplier.getRandomStringNumber(6))
                .setTaxPaymentDate(randomDateSupplier.getDate(datePeriod))
                .setDocumentUin("RUIN")
                .setPayerExpenses("1")
                .setOktmo(randomSupplier.getRandomStringNumber(11)
                );
    }
}