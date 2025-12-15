package Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import Domain.Account;
import Domain.Customer;
import Domain.Transaction;
import Domain.Type;
import repository.AccountRepository;
import repository.TransactionRepository;
import util.*;
import repository.CustomerRepository;
import exceptions.*;

public  class BankingServiceImp implements BankingService {
    private final AccountRepository accountRepository = new AccountRepository();
    private final TransactionRepository transactionRepository = new TransactionRepository();
    private final CustomerRepository customerRepository = new CustomerRepository();

    private final Validation<String> validateName = name -> {
        if(name == null || name.isBlank()) throw new ValidationException("enter your name");
    };

    private final Validation<String> validateEmail = Email -> {
        if(Email == null || !Email.contains("@")) throw new ValidationException("enter correct email");

    };

    private final Validation<String> validateType = Type -> {

        if(Type == null || !(Type.equalsIgnoreCase("SAVINGS") || Type.equalsIgnoreCase("CURRENT")))
            throw new ValidationException("enter Type from give account type");
    };

    private final Validation<Double> validateAmount = amount -> {

        if(amount == null || amount < 0)
            throw new ValidationException("enter Type from give account type");
    };


    @Override
    public String openAccount(String name, String email, String accountType){
        validateName.Validate(name);
        validateEmail.Validate(email);
        validateType.Validate(accountType);


        String customerId = UUID.randomUUID().toString();

        // customer creation
        Customer c = new Customer(customerId,name,email);
        customerRepository.save(c);

        String accountNumber = generateAccountNumber();
        Account account = new Account(accountNumber, customerId,(double) 0,accountType);
        accountRepository.save(account);
        return accountNumber;
    }

    private String generateAccountNumber() {
        // 10 + 1 ---> 11   AC%06d
        int size = accountRepository.findAll().size() +1;
        String accountNumber = String.format("AC%06d", size);
        return accountNumber;
    }

    @Override
    public List<Account> listAccounts() {
        return accountRepository.findAll().stream()
                .sorted((a, b) -> a.getAccountNumber().compareTo(b.getAccountNumber()))
                .collect(Collectors.toList());

    }

    @Override
    public void deposit(String accountNumber, Double amount,String note) {
        validateAmount.Validate(amount);
        Account account = accountRepository.findByNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"+accountNumber));
        account.setBalance(account.getBalance() + amount);

        Transaction transaction = new Transaction(UUID.randomUUID().toString(),account.getAccountNumber()
                ,amount,LocalDateTime.now(),note,Type.DEPOSIT);

        transactionRepository.add(transaction);




    }

    @Override
    public void withdraw(String accountNumber, Double amount, String note) {

        Account account = accountRepository.findByNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("account not found"+accountNumber));
        if (account.getBalance().compareTo(amount)<0)
            throw new InsufficientFundsException("Insufficient Balance");
        account.setBalance(account.getBalance() - amount);
        Transaction transaction = new Transaction(UUID.randomUUID().toString(),accountNumber,
                amount,LocalDateTime.now(),note,Type.WITHDRAW);
        transactionRepository.add(transaction);

    }

    @Override
    public void transfer(String fromAcc, String toAcc, Double amount, String note) {
        validateAmount.Validate(amount);
        if (fromAcc.equals(toAcc))
            throw new RuntimeException("accounts shouldn't be same---");
        Account from = accountRepository.findByNumber(fromAcc)
                .orElseThrow(() -> new AccountNotFoundException("account not found"+fromAcc));

        Account to = accountRepository.findByNumber(toAcc)
                .orElseThrow(() -> new AccountNotFoundException("account not found--"+toAcc));

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        Transaction transactionfrom = new Transaction(UUID.randomUUID().toString(),fromAcc,amount
                ,LocalDateTime.now(),note,Type.TRANSFER_OUT);
        transactionRepository.add(transactionfrom);

        Transaction transactionto = new Transaction(UUID.randomUUID().toString(),toAcc,amount
                ,LocalDateTime.now(),note,Type.TRANSFER_IN);
        transactionRepository.add(transactionto);

    }

    @Override
    public List<Transaction> statment(String account) {

        return  transactionRepository.findByNumber(account).stream()
                .sorted((a,b) -> a.getTimestamp().compareTo(b.getTimestamp()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Account> searchAccountByCustomerName(String q) {
        String query = (q == null) ? "":q.toLowerCase();
        List<Account> result = new ArrayList<>();
        for(Customer c : customerRepository.findAll()) {
            if (c.getName().toLowerCase().contains(query)) {
                result.addAll( accountRepository.findByCustomerId(c.getId()));
            }

        }
        result.sort(Comparator.comparing(Account::getAccountNumber));
        return result;
    }












}