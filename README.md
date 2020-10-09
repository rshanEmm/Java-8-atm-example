# Java-8-atm-example
An ATM widget for a code challenge 

The challenge is below

Develop a program called ATM. 

It allows customers to deposit and withdraw in these denominations: 20, 10, 5, and 1 dollar bills.

 

Deposit: Customer inputs the number of currency notes in each denomination

 

D.1) If any input values are negative, print "Incorrect deposit amount".

D.2) If all the input values are zero, print "Deposit amount cannot be zero".

D.3) If the input values are valid, increment the balances of corresponding dollar bills and print the available new balances in each denomination and the total balance.

 

Withdraw: Customer input the amount to withdraw. ATM dispenses the 20, 10, 5, and 1 dollar bills as needed. 

 

W.1) If the input amount is zero, negative, or over the current balance, print "Incorrect or insufficient funds".

W.2) If the input amount is in valid range, print the number of current notes dispensed in each denomination. Use the available higher denomination first. Also, print the available new balances in each denomination and the total balance.

 

For example, 

 

Deposit 1: 10s: 8 , 5s: 20

---------------------------------

 

Balance: 20s=0 , 10s=8 , 5s=20 , 1s=0 , Total=180

 

Deposit 2: 20s: 3 , 5s: 18 , 1s: 4

-----------------------------------------

 

Balance: 20s=3 , 10s=8 , 5s=38 , 1s=4 , Total=334

 

Withdraw 1: 75

---------------------

 

Dispensed: 20s=3 , 10s=1 , 5s=1 ,

Balance: 20s=0 , 10s=7 , 5s=37 , 1s=4 , Total=259

 

Withdraw 2: 122

----------------------

 

Dispensed: 10s=7 , 5s=10 , 1s=2 ,

Balance: 20s=0 , 10s=0 , 5s=27 , 1s=2 , Total=137

 

Withdraw 3: 253

----------------------

 

Output: "Incorrect or insufficient funds"

 

Withdraw 4: 0

-------------------

 

Output: "Incorrect or insufficient funds"

 

Withdraw 5: -25

----------------------

 

Output: "Incorrect or insufficient funds"

 

Tips: This program should be expandable to support 50s and 100s in future. Please allow the program to support any denominations with little or no code change.

 

public class ATM {

   public void deposit(...) {

   }

 

   public void withdraw(...) {

   }

}

**********
