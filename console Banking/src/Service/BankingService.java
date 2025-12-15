package Service;


import java.util.List;

import Domain.Account;
import Domain.Transaction;

public interface BankingService {

    String openAccount(String name,String email,String accountType);
    List<Account> listAccounts();
    void deposit(String accountNumber,Double amount,String note);
    void withdraw(String accountNumber, Double amount,String note);
    void transfer(String fromAcc, String toAcc, Double amount,String note);
    List<Transaction> statment(String account);
    List<Account> searchAccountByCustomerName(String q);

}