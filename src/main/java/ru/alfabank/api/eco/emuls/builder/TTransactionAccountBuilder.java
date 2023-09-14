package ru.alfabank.api.eco.emuls.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.alfabank.api.eco.emuls.supplier.RandomSupplier;
import ru.alfabank.thrift.transactions.TTransactionAccount;
import ru.alfabank.thrift.transactions.TTransactionBank;
import ru.alfabank.thrift.transactions.TtransactionOrganization;

@Component
public class TTransactionAccountBuilder {

    @Autowired
    private RandomSupplier randomSupplier;

    @Autowired
    private CurrencyBuilder currencyBuilder;

    public TTransactionAccount build(String accountNumber) {
        return new TTransactionAccount()
                .setCurrency(currencyBuilder.build())
                .setBank(getBank())
                .setNumber(getNumber(accountNumber))
                .setOrganization(getOrganization());
    }

    private String getNumber(String accountNumber) {
        if (accountNumber == null) {
            return randomSupplier.getRandomStringNumber(21);
        }
        return accountNumber;
    }

    private TtransactionOrganization getOrganization() {
        return new TtransactionOrganization()
                .setInn(randomSupplier.getRandomStringNumber(12))
                .setKpp(randomSupplier.getRandomStringNumber(9));
    }

    private TTransactionBank getBank() {
        return new TTransactionBank()
                .setBic("04" + randomSupplier.getRandomStringNumber(7))
                .setFilialName("АО \"АЛЬФА-БАНК\" МОСКВА");
    }
}