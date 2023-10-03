import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0); // Initial account balance
        ATM atmMachine = new ATM();
        atmMachine.login(userAccount);
        atmMachine.run();
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double checkBalance() {
        return balance;
    }
}

class ATM {
    private BankAccount account;

    public void login(BankAccount account) {
        this.account = account;
    }

    public void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (account == null) {
                System.out.println("Please login to your account.");
                return;
            }

            displayMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter the withdrawal amount: ");
                    double withdrawAmount = Double.parseDouble(scanner.nextLine());
                    if (account.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful. Remaining balance: $" + account.checkBalance());
                    } else {
                        System.out.println("Withdrawal failed. Insufficient balance or invalid amount.");
                    }
                    break;

                case "2":
                    System.out.print("Enter the deposit amount: ");
                    double depositAmount = Double.parseDouble(scanner.nextLine());
                    if (account.deposit(depositAmount)) {
                        System.out.println("Deposit successful. New balance: $" + account.checkBalance());
                    } else {
                        System.out.println("Deposit failed. Invalid amount.");
                    }
                    break;

                case "3":
                    double balance = account.checkBalance();
                    System.out.println("Your account balance is $" + balance);
                    break;

                case "4":
                    System.out.println("Exiting ATM. Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }
}


