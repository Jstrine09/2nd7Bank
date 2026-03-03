import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;


// static class Timecard {
//     String Name;
//     double HoursWorked;
//     double HourlyRate;

//     public Timecard(String name, double hoursWorked, double hourlyRate) {
//         this.Name = name;
//         this.HoursWorked = hoursWorked;
//         this.HourlyRate = hourlyRate;
//     }
// }

public class ReadData {
    public static ArrayList<Timecard> readData(String fileName) {
        try {
            File file = new File(fileName);
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

ArrayList<Timecard> input_data = ReadData.readData("input.data");

