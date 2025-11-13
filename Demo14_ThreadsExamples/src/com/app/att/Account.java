package com.app.att;

class Account {
    private int balance = 1000;

    public int getBalance() {
        return balance;
    }

    // synchronized ensures only one thread updates at a time
    public synchronized void withdraw(String user, int amount) {
        System.out.println(user + " is trying to withdraw " + amount);

        if (balance >= amount) {
            System.out.println(user + " proceeding with withdrawal...");
            try {
                Thread.sleep(1000); // simulate processing time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance -= amount;
            System.out.println(user + " completed withdrawal. Remaining balance: " + balance);
        } else {
            System.out.println("Insufficient funds for " + user);
        }
    }
}

class TransactionThread extends Thread {
    private Account account;
    private String user;
    private int amount;

    public TransactionThread(Account account, String user, int amount) {
        this.account = account;
        this.user = user;
        this.amount = amount;
    }

    public void run() {
        account.withdraw(user, amount);
    }
}
