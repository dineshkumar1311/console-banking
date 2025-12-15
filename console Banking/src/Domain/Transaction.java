package Domain;

import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private String accountNumber;
    private double amount;
    private LocalDateTime timestamp;
    private String note;
    private Type Type;

    public Transaction(String id,String accountNumber,double amount,LocalDateTime timestamp,String note,Type Type) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.timestamp = timestamp;
        this.note = note;
        this.Type = Type;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type type) {
        Type = type;
    }

}
