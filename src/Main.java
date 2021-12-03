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
//            resultPartOne = main.resultDayTwoPartOne(main.getStringInputFromList("day-two.txt"));
//            resultPartTwo = main.resultDayTwoPartTwo(main.getStringInputFromList("day-two.txt"));
            resultPartOne = main.resultDayThreePartOne(main.getStringInputFromList("day-three.txt"));
            resultPartTwo = main.resultDayThreePartTwo(main.getStringInputFromList("day-three.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }

        System.out.println("Result 1: " + resultPartOne);
        System.out.println("Result 2: " + resultPartTwo);
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

    public int resultDayThreePartOne(ArrayList<String> inputList) {
        int numInputs = inputList.size();
        int[] mostCommon = new int[inputList.get(0).length()];
        for (String string : inputList) {
            for (int j = 0; j < string.length(); j++) {
                if (string.charAt(j) == '1') {
                    mostCommon[j]++;
                }
            }
        }

        String gammaStr = "";
        String epsilonStr = "";
        for (int i : mostCommon) {

            if (i > numInputs / 2) {
                gammaStr += 1;
                epsilonStr += 0;
            } else {
                gammaStr += 0;
                epsilonStr += 1;
            }
        }

        int gammaRate = Integer.parseInt(gammaStr, 2);
        int epsilonRate = Integer.parseInt(epsilonStr, 2);

        return gammaRate * epsilonRate;
    }

    public int resultDayThreePartTwo(ArrayList<String> inputList) {
        ArrayList<String> o2List = new ArrayList<>();
        ArrayList<String> co2List = new ArrayList<>();

        for (String string : inputList) {
            o2List.add(string);
            co2List.add(string);
        }

        int o2 = 0;
        int co2 = 0;

        for (int i = 0; i < inputList.get(0).length(); i++) {
            if (o2List.size() > 1) {
                o2List = getNewO2InputList(o2List, i);
            }
        }

        for (int i = 0; i < inputList.get(0).length(); i++) {
            if (co2List.size() > 1) {
                co2List = getNewCO2InputList(co2List, i);
            }
        }

        if (!o2List.isEmpty() && !co2List.isEmpty()) {
            o2 = Integer.parseInt(o2List.get(0), 2);
            co2 = Integer.parseInt(co2List.get(0), 2);
        }

        return o2 * co2;

    }

    public ArrayList<String> getNewO2InputList(ArrayList<String> inputList, int stringIndex) {
        int numOne = 0;

        for (String string : inputList) {
            if (string.charAt(stringIndex) == '1') {
                numOne++;
            }
        }

        char mostCommon;
        mostCommon = numOne >= (inputList.size() / 2 + inputList.size() % 2) ? '1' : '0';

        for (int i = 0; i < inputList.size(); i++) {
            inputList.removeIf(string -> string.charAt(stringIndex) != mostCommon);
        }

        return inputList;
    }

    public ArrayList<String> getNewCO2InputList(ArrayList<String> inputList, int stringIndex) {
        int numOne = 0;

        for (String string : inputList) {
            if (string.charAt(stringIndex) == '1') {
                numOne++;
            }
        }

        char lessCommon;
        lessCommon = numOne >= (inputList.size() / 2 + inputList.size() % 2) ? '0' : '1';

        for (int i = 0; i < inputList.size(); i++) {
            inputList.removeIf(string -> string.charAt(stringIndex) != lessCommon);
        }

        return inputList;
    }

}
