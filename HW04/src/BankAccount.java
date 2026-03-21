public class BankAccount{
    //Constants
    public static final double MIN_BALANCE = 50000;
    public static final double TRANSFER_FEE_RATE = 0.02;
    //Attributes (Encapsulation)
    private String accountNumber;
    private String ownerName;
    private double balance;
    //Constructor
    public BankAccount(String accountNumber, String ownerName, double initialBalance){
        if(accountNumber == null || accountNumber.isEmpty()){
            throw new IllegalArgumentException("Account number cannot be empty");
        }
        if(ownerName == null || ownerName.isEmpty()){
            throw new IllegalArgumentException("Owner name cannot be empty");
        }
        if(initialBalance < MIN_BALANCE){
            throw new IllegalArgumentException("Initial balance must be at least " + MIN_BALANCE);
        }

        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance;
    }

    //Getter (read-only)
    public double getBalance(){
        return balance;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public String getOwnerName(){
        return ownerName;
    }

    //Deposit
    public void deposit(double amount){
        if(amount <= 0) {
            System.out.println("Deposit failed: amount must be > 0");
            return;
        }
        balance += amount;
        System.out.println("Deposit successful: +" + amount);
    }

    //Withdraw
    public void withdraw(double amount){
        if(amount <= 0){
            System.out.println("Withdraw failed: amount must be > 0");
            return;
        }
        if(balance - amount < MIN_BALANCE){
            System.out.println("Withdraw failed: below minimum balance");
            return;
        }
        balance -= amount;
        System.out.println("Withdraw successful: -" + amount);
    }
    //Transfer
    public void transfer(BankAccount receiver, double amount){
        if(amount <= 0){
            System.out.println("Transfer failed: amount must be > 0");
            return;
        }

        double fee = amount * TRANSFER_FEE_RATE;
        double total = amount + fee;

        if(balance < total){
            System.out.println("Transfer failed: insufficient balance (including fee)");
            return;
        }

        if(balance - total < MIN_BALANCE){
            System.out.println("Transfer failed: below minimum balance");
            return;
        }
       //Perform transfer
        balance -= total;
        receiver.balance += amount;
        //Receipt
        System.out.println("=== Transfer Receipt ===");
        System.out.println("From: " + this.accountNumber);
        System.out.println("To: " + receiver.accountNumber);
        System.out.println("Amount: " + amount);
        System.out.println("Fee (2%): " + fee);
        System.out.println("========================");
    }
    //Pay Bill
    public void payBill(String billName, double amount){
        if(billName == null || billName.isEmpty()){
            System.out.println("Bill payment failed: invalid bill name");
            return;
        }

        if(amount <= 0){
            System.out.println("Bill payment failed: amount must be > 0");
            return;
        }

        if(balance - amount < MIN_BALANCE){
            System.out.println("Bill payment failed: below minimum balance");
            return;
        }

        balance -= amount;
        System.out.println("Paid bill [" + billName + "]: -" + amount);
    }
    //Display info
    public void displayInfo(){
        System.out.println("Account: " + accountNumber +
                " | Owner: " + ownerName +
                " | Balance: " + balance);
    }
}