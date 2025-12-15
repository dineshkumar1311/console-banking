package app;

import java.util.Scanner;

import Service.BankingService;
import Service.BankingServiceImp;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankingService BankingService = new BankingServiceImp();
        System.out.println("welcome to console Bank");
        boolean running = true;
        while(running) {
            System.out.println("""
					1) Open Account
					2) Deposit
					3) Withdraw
					4) Transfer
					5) Account Statment
					6) List Accounts
					7) Search Account By customer Name
					0) Exit	
					""");
            System.out.println("CHOOSE: ");
            String choice=sc.nextLine().trim();
            System.out.println("your choice is: "+choice);

            switch(choice) {
                case "1" ->  openAccount(sc,BankingService);
                case "2" ->  deposit(sc,BankingService);
                case "3" ->  withdraw(sc,BankingService);
                case "4" ->  transfer(sc,BankingService);
                case "5" ->  statment(sc,BankingService);
                case "6" ->  listAccounts(sc,BankingService);
                case "7" ->  searchAccounts(sc,BankingService);
                case  "0" -> running=false;

            }

        }


    }

    private static void openAccount(Scanner sc, BankingService BankingService) {
        System.out.println("customer name:--");
        String name = sc.nextLine().trim();
        System.out.println("customer email:--");
        String email = sc.nextLine().trim();
        System.out.println("account type (savings / current):--");
        String accountType = sc.nextLine().trim();
        System.out.println("initial amount(optional, enter 0):--");
        String amount = sc.nextLine().trim();
        if (amount.isBlank()) amount = "0";
        Double initial = Double.valueOf(amount);
        String accountNumber =BankingService.openAccount(name, email, accountType);
        System.out.println("Deposited"+ accountNumber);

        if (initial > 0)
            BankingService.deposit(accountNumber,initial,"INITIAL DEPOSIT");
        System.out.println("Account is opened"+ accountNumber);
    }

    private static void deposit(Scanner sc, BankingService BankingService) {
        System.out.println("enter your account number:--");
        String accountNumber =sc.nextLine().trim();
        System.out.println("enter amount:--");
        Double amount = Double.valueOf(sc.nextLine().trim());
        System.out.println("deposited");
        BankingService.deposit(accountNumber,amount,"deposit");


    }

    private static void withdraw(Scanner sc,BankingService BankingService) {
        System.out.println("enter your account number:--");
        String accountNumber =sc.nextLine().trim();
        System.out.println("enter amount:--");
        Double amount = Double.valueOf(sc.nextLine().trim());
        BankingService.withdraw(accountNumber,amount,"withdrawl");
        System.out.println("withdrawn");



    }

    private static void transfer(Scanner sc,BankingService BankingService) {
        System.out.println("From account:--");
        String fromAcc = sc.nextLine().trim();
        System.out.println("To Account:--");
        String toAcc = sc.nextLine().trim();
        System.out.println("Amount:--");
        Double amount = Double.valueOf(sc.nextLine().trim());
        BankingService.transfer(fromAcc,toAcc,amount, "transfered");
        System.out.println("Transfered successfully");


    }

    private static void statment(Scanner sc,BankingService BankingService) {
        System.out.println("enter your  account number:--");
        String account = sc.nextLine().trim();
        BankingService.statment(account).forEach(a ->{
            System.out.println(a.getTimestamp()+ "|"+a.getType()+ " | "+ a.getAmount() + " |" + a.getNote());
        });

    }

    private static void listAccounts(Scanner sc,BankingService BankingService) {
        BankingService.listAccounts().forEach(a ->{
            System.out.println(a.getAccountNumber() + "|" + a.getAccountType() + "|"+ a.getBalance());
        });
    }

    private static void searchAccounts(Scanner sc,BankingService BankingService) {
        System.out.println("customer name contains: --");
        String q = sc.nextLine().trim();
        BankingService.searchAccountByCustomerName(q).forEach(account ->{
            System.out.println(account.getAccountNumber()+"|"+account.getAccountType()+"|"+account.getBalance());
        });;


    }




}

