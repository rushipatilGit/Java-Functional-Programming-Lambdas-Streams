package com.eazybytes.java25.flexconstructor;

public class SavingsAccount extends Account {

    private String accountNumber;

    public SavingsAccount(String number) {
        this.accountNumber = number;
        super();
    }

    @Override
    public void check() {
        if (accountNumber.length() == 0) {
            throw new RuntimeException("Invalid account");
        }
    }

    public static void main(String[] args) {
        SavingsAccount account = new SavingsAccount("1234567890");
    }


}
