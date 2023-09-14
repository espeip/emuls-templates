package ru.alfabank.api.eco.emuls.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import ru.alfabank.api.eco.emuls.annotations.ThriftTimeControl;
import ru.alfabank.thrift.CompositeException;
import ru.alfabank.thrift.auth.entity.UserData;
import ru.alfabank.thrift.transactions.TFilterSchemaProjection;
import ru.alfabank.thrift.transactions.TListingFilter;
import ru.alfabank.thrift.transactions.TListingFilterSchema;
import ru.alfabank.thrift.transactions.TListingTotalCount;
import ru.alfabank.thrift.transactions.TMergeRules;
import ru.alfabank.thrift.transactions.TPrintListing;
import ru.alfabank.thrift.transactions.TPrintTransactions;
import ru.alfabank.thrift.transactions.TransactionsApiServiceV2;
import ru.alfabank.thrift.utils.DatePeriod;
import ru.alfabank.thrift.utils.TCollectionFilter;
import ru.alfabank.thrift.utils.TCursor;
import ru.alfabank.thrift.utils.TSortFilter;
import ru.alfabank.thrift.utils.UnixEpoch;
import ru.trylogic.spring.boot.thrift.annotation.ThriftController;

import java.util.List;

/**
 * пример трифт-контроллера
 */
@Slf4j
@ThriftController("/url_to_controller")
@SuppressWarnings({"checkstyle:all"})
public class TransactionsApiThriftController implements TransactionsApiServiceV2.Iface {

    @ThriftTimeControl
    @Override
    public TPrintListing getPrintListing(UserData userData, TListingFilter filter, TSortFilter sort, TCursor cursor,
                                         TMergeRules mergeRules) throws CompositeException, TException {
        return null;
    }

    @ThriftTimeControl
    @Override
    public TPrintTransactions getPrintListingByIdsV2(UserData userData, TCollectionFilter transactionsIds, TCollectionFilter accounts,
                                                     String idType, DatePeriod datePeriod) throws CompositeException, TException {
        return null;
    }

    @Override
    public TListingFilterSchema getListingFilterSchema(UserData userData, String organizationId, TFilterSchemaProjection projection) throws CompositeException, TException {
        return null;
    }

    @ThriftTimeControl
    @Override
    public TListingTotalCount getListingTotalCount(UserData userData, TListingFilter filter, TMergeRules mergeRules)
            throws CompositeException, TException {
        return null;
    }

    @Override
    public UnixEpoch getLastAccountMovementDate(UserData userData, String account, UnixEpoch beforeDate)
            throws CompositeException, TException {
        return null;
    }

    @Override
    public UnixEpoch getLastListingMovementDate(UserData userData, List<String> accounts, UnixEpoch beforeDate) throws CompositeException, TException {
        return null;
    }
}
