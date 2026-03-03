import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

class Timecard {
    String Name;
    double HoursWorked;
    double HourlyRate;
    
    public Timecard(String name, double hoursWorked, double hourlyRate) {
        this.Name = name;
        this.HoursWorked = hoursWorked;
        this.HourlyRate = hourlyRate;
}
    
    public String toString() {
        return String.format("%s, %.1f, %.1f", this.Name, this.HoursWorked, this.HourlyRate);
    }
}

class PayrollEvent {
    String Name;
    double HoursWorked;
    double HourlyRate;
    double GrossPay;
    
    public PayrollEvent(String name, double hoursWorked, double hourlyRate, double grossPay) {
        this.Name = name;
        this.HoursWorked = hoursWorked;
        this.HourlyRate = hourlyRate;
        this.GrossPay = grossPay;
    }
    
    public String toString() {
        return String.format("%s, %.1f, %.1f, %.2f", this.Name, this.HoursWorked, this.HourlyRate, this.GrossPay);
    }
}

class ReadData {
    public static ArrayList<Timecard> readData(String inputFileName) {
        try {
            File file = new File(inputFileName);
            Scanner scanner = new Scanner(file);
            ArrayList<Timecard> data = new ArrayList<>();
            
            // Skip header line
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String name = parts[0].trim();
                double hoursWorked = Double.parseDouble(parts[1].trim());
                double hourlyRate = Double.parseDouble(parts[2].trim());
                data.add(new Timecard(name, hoursWorked, hourlyRate));
            }
            scanner.close();
            return data;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return null;
        }
    }
}

public class Main{ 
public static void main(String[] args) {
        ArrayList<Timecard> input_data = ReadData.readData("./src/Payroll/MainFolder/input.data");
        System.out.println(input_data);

        for (Timecard t : input_data) {
            System.out.println(t);
            double grossPay = t.HoursWorked * t.HourlyRate;
            PayrollEvent payrollEvent = new PayrollEvent(t.Name, t.HoursWorked, t.HourlyRate, grossPay);
            System.out.println(payrollEvent);
        }
    }
}
