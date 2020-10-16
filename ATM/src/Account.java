import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Account {
    Scanner input = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
    private double checkingBalance;
    // LinkedHashMap retains the order in which each entry is placed there in
    private LinkedHashMap<Integer, Integer> notes;

    Account(){
        this.notes = setupNotes();
        checkingBalance = 0;
    }
    // sending for a default payload of US 20,10,5,1
    private LinkedHashMap<Integer, Integer> setupNotes(){
        return setupNotes("US20");
    }


    private LinkedHashMap<Integer, Integer> setupNotes(String curType){
        LinkedHashMap<Integer, Integer> notes = new LinkedHashMap<Integer, Integer>();

        switch(curType){
            case "US20":
                notes.put(20, 0);
                notes.put(10, 0);
                notes.put(5, 0);
                notes.put(1,0);
                break;
            case "US100":
                notes.put(100,0);
                notes.put(50,0);
                notes.put(20, 0);
                notes.put(10, 0);
                notes.put(5, 0);
                notes.put(1,0);
                break;
            default:
                notes.put(20, 0);
                notes.put(10, 0);
                notes.put(5, 0);
                notes.put(2, 0);
                notes.put(1,0);
                break;
        }
        return notes;
    }

    public double getCheckingBalance() {
        return checkingBalance;
    }

    public double calcCheckingWithdraw(double amount) {
        checkingBalance = (checkingBalance - amount);
        return checkingBalance;
    }

    public double calcCheckingDeposit(double amount) {
        checkingBalance = (checkingBalance + amount);
        return checkingBalance;
    }

    private boolean depositSanityCheck(Double amount){
        if(amount > checkingBalance) {
            return false;
        }
        return equalToZero(amount);
    }

    private boolean equalToZero(Double value) {
        return (value <= 0)? false : true ;
    }

    public void getCheckingWithdrawInput() {
        System.out.println("Checking Account Balance: " + moneyFormat.format(checkingBalance));
        System.out.print("Amount you want to withdraw from Checking Account: ");
        double amount = input.nextDouble();
        if(!depositSanityCheck(amount)){
            System.out.println("Incorrect or insufficient funds");
            return;
        }
        AtomicReference<LinkedHashMap> dispensedAmount = new AtomicReference<>( new LinkedHashMap<Integer, Integer>() );
        AtomicReference<Double> promisedAmount = new AtomicReference<>(checkingBalance);
        AtomicInteger keyTrack = new AtomicInteger(0);
        promisedAmount.set(amount);

        notes.forEach((key, value) -> {
           if(promisedAmount.get() == 0){
               return;
           }

           int numberOfBillToBePull = (int)Math.floor(promisedAmount.get().intValue() / key);
           // This code will "try" to exchange a larger bill for a small equal amount of notes
           if(value < numberOfBillToBePull) {
               value = exchangeForSmallerNotes(keyTrack.get(), key);
           }
           /* If is here for a check after the above code has run, if the value has increased over the size
             of numberOfBillToBePull then it is good. If not then clearing what is left in value
            */
           numberOfBillToBePull = (value < numberOfBillToBePull) ? value : numberOfBillToBePull;
           if(numberOfBillToBePull != 0)
                dispensedAmount.get().put(key, numberOfBillToBePull); // save for dispensed amount
           notes.put(key, value - numberOfBillToBePull);
           promisedAmount.set(promisedAmount.get() - (numberOfBillToBePull * key));
            // tracking last key if there are any bills left
           if(notes.get(key) > 0) {
                keyTrack.set(key);
           }
        });
        printWithdrawTransactionUI(dispensedAmount.get());
        calcCheckingWithdraw(amount);
        printNoteCountsAndTotal();
    }

    public void getCheckingDepositInput() {
        System.out.println("Checking Account Balance: " + moneyFormat.format(checkingBalance));
        AtomicReference<Double> promisedAmount = new AtomicReference<>(0.0);
        notes.forEach((key, value) -> {
            System.out.print("The amount of " + key +"s you offer to deposit : ");
            int valueCLI = input.nextInt();

            while(valueCLI < 0){
                System.out.println("Incorrect deposit amount");
                System.out.print("The amount of " + key +"s you offer to deposit : ");
                valueCLI = input.nextInt();
            }
            notes.put(key, value + valueCLI);
            promisedAmount.set(promisedAmount.get() + Double.valueOf(key * valueCLI));
        });
        if(!equalToZero(promisedAmount.get())) {
            System.out.println("Deposit amount cannot be zero");
            return;
        }
        calcCheckingDeposit(promisedAmount.get());
        printNoteCountsAndTotal();
    }

    private void printNoteCountsAndTotal(){
        System.out.print("\nBalance: ");
        notes.forEach((key, value) -> {
            System.out.print(" " + key +"s="+ value + " , " );
        });
        System.out.print(" Total = " + moneyFormat.format(checkingBalance) + "\n");
    }

    private void printWithdrawTransactionUI(LinkedHashMap dispensedAmount){
        System.out.print("Dispensed :");
        dispensedAmount.forEach((key, value) -> {
                System.out.print(" " + key + "s=" + value + " , ");
        });
    }

    private int exchangeForSmallerNotes(int keyTrack, int key ){
        if(!notes.containsKey(keyTrack)){
            return notes.get(key);
        }
        if(notes.get(keyTrack) > 0) {
            notes.put(keyTrack, notes.get(keyTrack) - 1);
            notes.put(key, notes.get(key) +  (keyTrack / key) );
        }
        return notes.get(key);
    }
}