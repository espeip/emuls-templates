package ru.alfabank.api.eco.emuls.builder;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.alfabank.api.eco.emuls.supplier.RandomSupplier;
import ru.alfabank.thrift.transactions.TPrintListing;
import ru.alfabank.thrift.transactions.TPrintTransaction;
import ru.alfabank.thrift.utils.DatePeriod;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class TPrintListingBuilder {

    private final RandomSupplier randomSupplier;
    private final TTransactionAccountBuilder tTransactionAccountBuilder;
    private final TPrintDocumentBuilder tPrintDocumentBuilder;

    public TPrintListing build(List<String> accounts,
                               DatePeriod datePeriod,
                               int cursorSize) {
        return new TPrintListing()
                .setTransactions(
                        getTPrintListing(accounts, datePeriod, cursorSize)
                );
    }


    private List<TPrintTransaction> getTPrintListing(List<String> accounts,
                                                     DatePeriod datePeriod,
                                                     int cursorSize) {
        List<TPrintTransaction> transactions = new ArrayList<>();
        accounts.forEach(account ->
                        getTransaction(
                                account,
                                datePeriod,
                                randomSupplier.getRandomNumber(1, 100),
                                transactions,
                                cursorSize
                        )
                );
        return transactions;
    }

    private void getTransaction(String accountNumber,
                                DatePeriod datePeriod,
                                int numberOfTransactions,
                                List<TPrintTransaction> transactions,
                                int cursorSize
                                ) {
        for (int i = 0; i < numberOfTransactions; i++) {
            if (checkLength(transactions, cursorSize)) {
                break;
            }
            transactions.add(new TPrintTransaction()
                    .setId(randomSupplier.getRandomStringNumber(12))
                    .setPrintingInfo(tPrintDocumentBuilder.build(datePeriod))
                    .setAccountFrom(tTransactionAccountBuilder.build(accountNumber))
                    .setAccountTo(tTransactionAccountBuilder.build(null))
            );
        }
    }

    private boolean checkLength(List<TPrintTransaction> transactions, int cursorSize) {
        return transactions.size() >= cursorSize;
    }
}