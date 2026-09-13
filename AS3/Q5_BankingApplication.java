import java.util.Scanner;


class InsufficientBalanceException extends Exception {

    public InsufficientBalanceException(String message) {
        super(message);
    }
}


class AccountNotFoundException extends Exception {

    public AccountNotFoundException(String message) {
        super(message);
    }
}

class BankAccount {

    private int accountNumber;
    private double balance;

    public BankAccount(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {

        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }

        balance += amount;
        System.out.println("Amount deposited successfully.");
        System.out.println("Current balance = " + balance);
    }

    public void withdraw(double amount)
            throws InsufficientBalanceException {

        if (amount > balance) {
            throw new InsufficientBalanceException(
                    "Insufficient balance. Available balance = " + balance
            );
        }

        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }

        balance -= amount;

        System.out.println("Amount withdrawn successfully.");
        System.out.println("Current balance = " + balance);
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}

public class Q5_BankingApplication {

    static BankAccount findAccount(int accountNumber)
            throws AccountNotFoundException {

        
        if (accountNumber == 101) {
            return new BankAccount(101, 5000);
        }

        throw new AccountNotFoundException(
                "Account " + accountNumber + " not found."
        );
    }

    
    static void performWithdrawal(int accountNumber, double amount)
            throws AccountNotFoundException,
                   InsufficientBalanceException {

        BankAccount account = findAccount(accountNumber);

        
        account.withdraw(amount);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            System.out.print("Enter account number: ");
            int accountNumber = sc.nextInt();

            BankAccount account = findAccount(accountNumber);

            System.out.println("Account found.");
            System.out.println("Current balance = "
                    + account.getBalance());

            System.out.print("Enter deposit amount: ");
            double deposit = sc.nextDouble();

            account.deposit(deposit);

            System.out.print("Enter withdrawal amount: ");
            double withdrawal = sc.nextDouble();

            
            performWithdrawal(accountNumber, withdrawal);

        } catch (AccountNotFoundException e) {

            System.out.println("Error: " + e.getMessage());

        } catch (InsufficientBalanceException e) {

            System.out.println("Error: " + e.getMessage());

        } finally {

            sc.close();
            System.out.println("Banking operation completed.");
        }
    }
}