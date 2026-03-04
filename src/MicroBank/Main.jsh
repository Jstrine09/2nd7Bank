package MicroBank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;



class Transaction {
    String Date;
    String TypeOfTransaction;
    double Amount;
    
    public Transaction(String date, String typeOfTransaction, double amount) {
        this.Date = date;
        this.TypeOfTransaction = typeOfTransaction;
        this.Amount = amount;
    }
    
    public String toString() {
        return String.format("%s, %s, %.2f", this.Date, this.TypeOfTransaction, this.Amount);
    }
}

class AccountBalance {
    String Date;
    double Balance;
    
    public AccountBalance(String date, double balance) {
        this.Date = date;
        this.Balance = balance;
    }
    
    public String toString() {
        return String.format("%s, %.2f", this.Date, this.Balance);
    }
}
class ReadData {
    public static ArrayList<Transaction> readData(String inputFileName) {
        try {
            File file = new File(inputFileName);
            Scanner scanner = new Scanner(file);
            ArrayList<Transaction> data = new ArrayList<>();
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String date = parts[0].trim();
                String typeOfTransaction = parts[1].trim();
                double amount = Double.parseDouble(parts[2].trim());
                data.add(new Transaction(date, typeOfTransaction, amount));
            }
            scanner.close();
            return data;
        } 
            catch (FileNotFoundException e) {
            System.err.println("File not found: " + inputFileName);
            return new ArrayList<>();
        }
    }
}

public class Main{
    public static void main(String[] args) {
        String inputFileName = "input.data";
        ArrayList<Transaction> transactions = ReadData.readData("./src/MicroBank/input.data");
        
        // compute running balance; start at zero and update for each record
        double balance = 0.0;
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
            if (transaction.TypeOfTransaction.equalsIgnoreCase("deposit")) {
                balance += transaction.Amount;
            } else if (transaction.TypeOfTransaction.equalsIgnoreCase("withdrawal")) {
                balance -= transaction.Amount;
            } else {
                System.err.println("Unknown transaction type: " + transaction.TypeOfTransaction);
            }
            System.out.printf("Current Balance: %.2f%n", balance);
        }
        // print final balance at the end
        System.out.printf("Final Balance: %.2f%n", balance);
    }
}