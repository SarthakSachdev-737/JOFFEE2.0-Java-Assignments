import java.util.Scanner;

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class IneligibleForLoanException extends Exception {
    public IneligibleForLoanException(String message) {
        super(message);
    }
}

interface LoanEligibility {
    void checkEligibility() throws IneligibleForLoanException;
}

abstract class BankAccount implements LoanEligibility {
    protected String accountHolder;
    protected double balance;

    public BankAccount(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount) throws InsufficientFundsException;

    public double getBalance() {
        return balance;
    }
}

class SavingsAccount extends BankAccount {
    private static final double LOAN_THRESHOLD = 5000;

    public SavingsAccount(String accountHolder, double balance) {
        super(accountHolder, balance);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited " + amount + ". New balance: " + balance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (balance - amount < 0) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal.");
        }
        balance -= amount;
        System.out.println("Withdrew " + amount + ". New balance: " + balance);
    }

    @Override
    public void checkEligibility() throws IneligibleForLoanException {
        if (balance < LOAN_THRESHOLD) {
            throw new IneligibleForLoanException("Account balance is below loan threshold of " + LOAN_THRESHOLD);
        }
        System.out.println("Loan eligibility check passed.");
    }
}

class CurrentAccount extends BankAccount {
    private static final double LOAN_THRESHOLD = 10000;

    public CurrentAccount(String accountHolder, double balance) {
        super(accountHolder, balance);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited " + amount + ". New balance: " + balance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (balance - amount < 0) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal.");
        }
        balance -= amount;
        System.out.println("Withdrew " + amount + ". New balance: " + balance);
    }

    @Override
    public void checkEligibility() throws IneligibleForLoanException {
        if (balance < LOAN_THRESHOLD) {
            throw new IneligibleForLoanException("Account balance is below loan threshold of " + LOAN_THRESHOLD);
        }
        System.out.println("Loan eligibility check passed.");
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = null;

        try {
            System.out.println("Enter account type (Savings/Current): ");
            String accountType = scanner.nextLine();

            System.out.println("Enter account holder's name: ");
            String name = scanner.nextLine();

            System.out.println("Enter initial balance: ");
            double balance = scanner.nextDouble();

            if (accountType.equalsIgnoreCase("Savings")) {
                account = new SavingsAccount(name, balance);
            } else if (accountType.equalsIgnoreCase("Current")) {
                account = new CurrentAccount(name, balance);
            } else {
                System.out.println("Invalid account type!");
                return;
            }

            boolean continueTransactions = true;
            while (continueTransactions) {
                System.out.println("\nChoose an action: 1. Deposit 2. Withdraw 3. Check Loan Eligibility 4. Exit");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1: 
                        System.out.println("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                        break;

                    case 2: 
                        System.out.println("Enter withdrawal amount: ");
                        double withdrawAmount = scanner.nextDouble();
                        try {
                            account.withdraw(withdrawAmount);
                        } catch (InsufficientFundsException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 3: 
                        try {
                            account.checkEligibility();
                        } catch (IneligibleForLoanException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 4: 
                        continueTransactions = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } finally {
            if (account != null) {
                System.out.println("Thank you, " + account.accountHolder + ". Final balance: " + account.getBalance());
            }
            scanner.close();
        }
    }
}
