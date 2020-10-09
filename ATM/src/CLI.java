import java.util.Scanner;
    public class CLI extends Account {
        Scanner menuInput = new Scanner(System.in);
        int selection;
        public void getChecking() {
            System.out.println("Checking Account: ");
            System.out.println("Type 1 - View Balance");
            System.out.println("Type 2 - Withdraw Funds");
            System.out.println("Type 3 - Deposit Funds");
            System.out.println("Type 4 - Exit");
            System.out.print("Choice: ");
            selection = menuInput.nextInt();
            switch (selection) {
                case 1:
                    System.out.println("Checking Account Balance: " + moneyFormat.format(getCheckingBalance()) + "\n");
                    getChecking();
                    break;
                case 2:
                    getCheckingWithdrawInput();
                    getChecking();
                    break;
                case 3:
                    getCheckingDepositInput();
                    getChecking();
                    break;
                case 4:
                    System.out.println("Thank You for using this ATM, bye.");
                    break;

                default:
                    System.out.println("\n" + "Invalid choice." + "\n");
                    getChecking();
            }
        }
    }