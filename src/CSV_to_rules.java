import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSV_to_rules {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("Parse Table (unf).csv"));
        scanner.useDelimiter(",");

        String firstRow = scanner.nextLine();
        String[] terminals = firstRow.split(",");

        while (scanner.hasNextLine()) {
            String[] things = scanner.nextLine().split(",");
            for (int i = 1; i < things.length; i++) {
                if (things[i].matches("\\d+"))
                    System.out.println(things[0] + "," + terminals[i] + "\t" + things[i]);
            }
        }
        scanner.close();
    }
}
