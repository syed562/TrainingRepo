package com.app.att;


public class BankTransferDemo {
    public static void main(String[] args) {
        Account account = new Account();

        TransactionThread t1 = new TransactionThread(account, "User1", 700);
        TransactionThread t2 = new TransactionThread(account, "User2", 500);

        t1.start();
        t2.start();
    }
}
