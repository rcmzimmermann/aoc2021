import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        int resultPartOne = 0;
        int resultPartTwo = 0;
        try {
//            resultPartOne = main.resultDayOnePartOne(main.getIntegerInputFromList("day-one.txt"));
//            resultPartTwo = main.resultDayOnePartTwo(main.getIntegerInputFromList("day-one.txt"));
            resultPartOne = main.resultDayTwoPartOne(main.getStringInputFromList("day-two.txt"));
            resultPartTwo = main.resultDayTwoPartTwo(main.getStringInputFromList("day-two.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }

        System.out.println(resultPartOne);
        System.out.println(resultPartTwo);
    }

    public ArrayList<Integer> getIntegerInputFromList(String fileName) throws FileNotFoundException {
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

    public ArrayList<String> getStringInputFromList(String fileName) throws FileNotFoundException {
        ArrayList<String> inputList = new ArrayList<>();
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            inputList.add(line);
        }
        scanner.close();
        return inputList;
    }

    public int resultDayOnePartOne(ArrayList<Integer> inputList) {
        int counter = 0;
        for (int i = 1; i < inputList.size(); i++) {
            if (inputList.get(i) > inputList.get(i - 1)) {
                counter++;
            }
        }
        return counter;
    }

    public int resultDayOnePartTwo(ArrayList<Integer> inputList) {
        int counter = 0;
        int restWaarde = (inputList.size() % 3) + 1;
        for (int i = 0; i < inputList.size() - restWaarde; i++) {
            int sum1 = inputList.get(i) + inputList.get(i + 1) + inputList.get(i + 2);
            int sum2 = inputList.get(i + 1) + inputList.get(i + 2) + inputList.get(i + 3);
            if (sum1 < sum2) {
                counter++;
            }
        }
        return counter;
    }

    public int resultDayTwoPartOne(ArrayList<String> inputList) {
        String[] array;
        int horizontal = 0;
        int depth = 0;
        for (String s : inputList) {
            array = s.split(" ");
            switch (array[0]) {
                case "forward":
                    horizontal += Integer.parseInt(array[1]);
                    break;
                case "down":
                    depth += Integer.parseInt(array[1]);
                    break;
                case "up":
                    depth -= Integer.parseInt(array[1]);
                    break;
                default:
                    System.out.println("Can only go forward, down or up!");
            }
        }
        return horizontal * depth;
    }

    public int resultDayTwoPartTwo(ArrayList<String> inputList) {
        String[] array;
        int aim = 0;
        int horizontal = 0;
        int depth = 0;
        for (String s : inputList) {
            array = s.split(" ");
            switch (array[0]) {
                case "forward":
                    horizontal += Integer.parseInt(array[1]);
                    if (aim != 0) {
                        depth += Integer.parseInt(array[1]) * aim;
                    }
                    break;
                case "down":
                    aim += Integer.parseInt(array[1]);

                    break;
                case "up":
                    aim -= Integer.parseInt(array[1]);
                    break;
                default:
                    System.out.println("Can only go forward, down or up!");
            }
        }
        return horizontal * depth;
    }
}
