import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main (String[] args) {
        Main main = new Main();
        int result = 0;
        try {
            System.out.println(main.resultDayOnePartOne(main.getInputFromFile("day-one.txt")));
            System.out.println(main.resultDayOnePartTwo(main.getInputFromFile("day-one.txt")));
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }

        System.out.println(result);
    }

    public ArrayList<Integer> getInputFromFile(String fileName) throws FileNotFoundException {
        ArrayList<Integer> inputList = new ArrayList<>();
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            inputList.add(Integer.valueOf(line));
        }
        scanner.close();
        return inputList;
    }

    public int resultDayOnePartOne(ArrayList<Integer> inputList) {
        int counter = 0;
        for (int i = 1; i < inputList.size(); i++) {
            if (inputList.get(i) > inputList.get(i-1)) {
                counter++;
            }
        }
        return counter;
    }
    
    public int resultDayOnePartTwo(ArrayList<Integer> inputList) {
        int counter = 0;
        int restWaarde = (inputList.size() % 3) + 1;
        for (int i = 0; i < inputList.size()-restWaarde; i++) {
            int sum1 = inputList.get(i) + inputList.get(i+1) + inputList.get(i+2);
            int sum2 = inputList.get(i+1) + inputList.get(i+2) + inputList.get(i+3);
            if (sum1 < sum2) {
                counter++;
            }
        }
        return counter;
    }
}
