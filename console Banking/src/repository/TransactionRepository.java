package repository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import Domain.Transaction;


public class TransactionRepository {

    private final Map<String ,List<Transaction>> txnByAccount = new HashMap<>();

    public void add(Transaction transaction) {
        List<Transaction> list = txnByAccount.computeIfAbsent(transaction.getAccountNumber(),
                k-> new ArrayList<>());
        list.add(transaction);

    }

    public List<Transaction> findByNumber(String account) {

        return new ArrayList<>(txnByAccount.getOrDefault(account,Collections.emptyList()));
    }





}