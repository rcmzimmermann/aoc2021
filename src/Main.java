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
//            resultPartOne = main.resultDayThreePartOne(main.getStringInputFromList("day-three.txt"));
//            resultPartTwo = main.resultDayThreePartTwo(main.getStringInputFromList("day-three.txt"));

            ArrayList<Integer> calledNumbers = main.getCalledNumbers(main.getStringInputFromList("day-four.txt"));
            ArrayList<BingoCard> cards = main.makeBingoCards(main.getStringInputFromList("day-four.txt"));
//            BingoCard winningCard = main.callNumber(cards, calledNumbers, "win");
//            System.out.println(winningCard);
//            resultPartOne = main.calculateScore(winningCard);
            BingoCard losingCard = main.callNumber(cards, calledNumbers, "lose");
            System.out.println(losingCard);
            resultPartTwo = main.calculateScore(losingCard);

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }

//        System.out.println("Result 1: " + resultPartOne);
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

        inputList.removeIf(string -> string.charAt(stringIndex) != mostCommon);

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

        inputList.removeIf(string -> string.charAt(stringIndex) != lessCommon);

        return inputList;
    }

    public ArrayList<Integer> getCalledNumbers(ArrayList<String> input) {
        ArrayList<Integer> calledNumbers = new ArrayList<>();
        String[] numbers = input.get(0).split(",");
        for (String number : numbers) {
            calledNumbers.add(Integer.parseInt(number));
//            System.out.println(Integer.parseInt(number));
        }
        return calledNumbers;
    }

    public ArrayList<BingoCard> makeBingoCards(ArrayList<String> input) {
        input.removeIf(string -> string.contains(",") || string.equals(""));
        ArrayList<BingoCard> cards = new ArrayList<>();

        for (int i = 0; i < input.size(); i = i + 5) {
            ArrayList<Integer> row1 = getRow(input.get(i));
            ArrayList<Integer> row2 = getRow(input.get(i + 1));
            ArrayList<Integer> row3 = getRow(input.get(i + 2));
            ArrayList<Integer> row4 = getRow(input.get(i + 3));
            ArrayList<Integer> row5 = getRow(input.get(i + 4));

            BingoCard bingoCard = new BingoCard(row1, row2, row3, row4, row5);
            cards.add(bingoCard);

        }

        return cards;
    }

    public ArrayList<Integer> getRow(String string) {
        ArrayList<Integer> row = new ArrayList<>();
        String[] rowArr = string.split(" ");

        for (String str : rowArr) {
            if (!str.equals("")) {
                row.add(Integer.parseInt(str));
            }
        }

        return row;
    }

    public BingoCard callNumber(ArrayList<BingoCard> cards, ArrayList<Integer> calledNumbers, String winOrLose) {

        switch (winOrLose) {
            case "win":
                for (int i = 0; i < calledNumbers.size(); i++) {
                    System.out.println("Calling number: " + calledNumbers.get(i));
                    for (BingoCard card : cards) {
                        card.setLastNumCalled(calledNumbers.get(i));
                        card.callNumber(calledNumbers.get(i));
                        boolean bingo = card.checkBingo();
                        if (bingo) {
                            return card;
                        }
                    }
                }
            case "lose":
                int numCardsBingo = 0;
                int lastCardBingoIndex = 0;
                ArrayList<Integer> indexToSkip = new ArrayList<>();
                for (int i = 0; i < calledNumbers.size(); i++) {
                    System.out.println("Calling number: " + calledNumbers.get(i));

                    for (int j = 0; j < cards.size() - 1; j++) {
                        if (indexToSkip.contains(j)) {
                            continue;
                        } else {
                            BingoCard card = cards.get(j);
                            card.setLastNumCalled(calledNumbers.get(i));
                            card.callNumber(calledNumbers.get(i));
                            boolean bingo = card.checkBingo();
                            if (bingo) {
                                System.out.println("Bingo on card: " + cards.indexOf(cards.get(j)));
                                lastCardBingoIndex = j;
                                indexToSkip.add(lastCardBingoIndex);
                                numCardsBingo++;
                                System.out.println("Num cards won: " + numCardsBingo);
                            }
                        }
                    }

                }
                return cards.get(lastCardBingoIndex);
        }

        return null;
    }


    public int calculateScore(BingoCard card) {
        int total = 0;
        for (int i = 0; i < card.getRow1Boolean().size(); i++) {
            if (!card.getRow1Boolean().get(i)) {
                total += card.getRow1().get(i);
            }
        }

        for (int i = 0; i < card.getRow2Boolean().size(); i++) {
            if (!card.getRow2Boolean().get(i)) {
                total += card.getRow2().get(i);
            }
        }

        for (int i = 0; i < card.getRow3Boolean().size(); i++) {
            if (!card.getRow3Boolean().get(i)) {
                total += card.getRow3().get(i);
            }
        }

        for (int i = 0; i < card.getRow4Boolean().size(); i++) {
            if (!card.getRow4Boolean().get(i)) {
                total += card.getRow4().get(i);
            }
        }

        for (int i = 0; i < card.getRow5Boolean().size(); i++) {
            if (!card.getRow5Boolean().get(i)) {
                total += card.getRow5().get(i);
            }
        }

        System.out.println("Nums added up: " + total);
        System.out.println("Last number called: " + card.getLastNumCalled());

        return total * card.getLastNumCalled();
    }

}
