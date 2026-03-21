public class Main{
    public static void main(String[] args){
        //Create accounts
        BankAccount acc1 = new BankAccount("001", "Khoi", 100000);
        BankAccount acc2 = new BankAccount("002", "An", 200000);

        System.out.println("=== Initial Accounts ===");
        acc1.displayInfo();
        acc2.displayInfo();
        //Deposit
        System.out.println("\n=== Deposit ===");
        acc1.deposit(50000);   //success
        acc1.deposit(-10);     //fail
        //Withdraw
        System.out.println("\n=== Withdraw ===");
        acc1.withdraw(30000);  //success
        acc1.withdraw(100000); //fail (min balance)
        //Transfer
        System.out.println("\n=== Transfer ===");
        acc1.transfer(acc2, 20000); //success
        acc1.transfer(acc2, 100000); //fail
        //Pay Bill
        System.out.println("\n=== Pay Bill ===");
        acc2.payBill("Electricity", 50000); //success
        acc2.payBill("Water", 200000);      //fail
        //Final balances
        System.out.println("\n=== Final Accounts ===");
        acc1.displayInfo();
        acc2.displayInfo();
    }
}