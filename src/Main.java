import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    final String ROCK = "Rock";
    final String PAPER = "Paper";
    final String SCISSORS = "Scissors";
    final String LOSE = "Lose";
    final String DRAW = "Draw";
    final String WIN = "WIN";

    public static void main(String[] args) {
        Main main = new Main();
        int resultPartOne = 0;
        try {
            resultPartOne = main.resultDayFourPartOne(main.getStringInputFromList("C:\\Users\\Robii\\Documents\\Programming\\aoc2021\\day-four.txt"));

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }

        System.out.println("Result 1: " + resultPartOne);
    }

    public ArrayList<Integer> getIntegerInputFromList(String fileName) throws FileNotFoundException {
        ArrayList<Integer> inputList = new ArrayList<>();
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("")) {
                inputList.add(null);
            } else {
                inputList.add(Integer.valueOf(line));
            }
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

    public int resultDayOne(ArrayList<Integer> inputList) {
        List<Integer> list = new ArrayList<>();
        int totalCalsPerElf = 0;

        for (Integer integer : inputList) {
            if (integer != null) {
                totalCalsPerElf += integer;
            } else {
                list.add(totalCalsPerElf);
                totalCalsPerElf = 0;
            }
        }

        Collections.sort(list);
        System.out.println(list);

        int top3Cals = list.get(list.size()-1) + list.get(list.size()-2) + list.get(list.size()-3);
        System.out.println(top3Cals);

        return 0;
    }

    public int resultDayTwo(ArrayList<String> inputList) {
        Map<String, Integer> scoreMap = new HashMap<>();
        scoreMap.put(ROCK, 1);
        scoreMap.put(PAPER, 2);
        scoreMap.put(SCISSORS, 3);

        List<String> newMoves = new ArrayList<>();
        List<Integer> choiceScore = new ArrayList<>();
        List<Integer> resultScore = new ArrayList<>();

        for (String move : inputList) {
            move = move.replace("A", ROCK);
            move = move.replace("B", PAPER);
            move = move.replace("C", SCISSORS);
            move = move.replace("X", LOSE);
            move = move.replace("Y", DRAW);
            move = move.replace("Z", WIN);

            String otherChoice = move.split(" ")[0];
            newMoves.add(otherChoice + " " + getRightMove(move));
        }

        for (String move : newMoves) {
            String otherChoice = move.split(" ")[0];
            String myChoice = move.split(" ")[1];

            choiceScore.add(scoreMap.get(myChoice));

            if (myChoice.equals(otherChoice)) {
                resultScore.add(3);
            } else if (myChoice.equals(ROCK) && otherChoice.equals(PAPER)) {
                resultScore.add(0);
            } else if (myChoice.equals(ROCK) && otherChoice.equals(SCISSORS)) {
                resultScore.add(6);
            } else if (myChoice.equals(PAPER) && otherChoice.equals(ROCK)) {
                resultScore.add(6);
            } else if (myChoice.equals(PAPER) && otherChoice.equals(SCISSORS)) {
                resultScore.add(0);
            } else if (myChoice.equals(SCISSORS) && otherChoice.equals(ROCK)) {
                resultScore.add(0);
            } else if (myChoice.equals(SCISSORS) && otherChoice.equals(PAPER)) {
                resultScore.add(6);
            }
        }

        int totalScore = 0;
        for (int i = 0; i < choiceScore.size(); i++) {
            totalScore += choiceScore.get(i);
            totalScore += resultScore.get(i);
        }

        System.out.println(choiceScore);

        return totalScore;
    }

    private String getRightMove(String input) {
        String rightChoice = input.split(" ")[1];
        String otherChoice = input.split(" ")[0];

        switch (rightChoice) {
            case DRAW:
                if (otherChoice.equals(ROCK)) return ROCK;
                if (otherChoice.equals(PAPER)) return PAPER;
                if (otherChoice.equals(SCISSORS)) return SCISSORS;
            case LOSE:
                if (otherChoice.equals(ROCK)) return SCISSORS;
                if (otherChoice.equals(PAPER)) return ROCK;
                if (otherChoice.equals(SCISSORS)) return PAPER;
            case WIN:
                if (otherChoice.equals(ROCK)) return PAPER;
                if (otherChoice.equals(PAPER)) return SCISSORS;
                if (otherChoice.equals(SCISSORS)) return ROCK;
            default:
                return null;
        }
    }

    public int resultDayThreePartOne(ArrayList<String> inputList) {
        String letters = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
        List<String> list = Arrays.asList(letters.split(","));
        int totalScore = 0;
        for (int i = 0; i < inputList.size(); i++) {
            String[] subString = getSubString(inputList.get(i));
            String doubleChar = getCharInBothStrings(subString);
            totalScore += (list.indexOf(doubleChar) + 1);
        }
        return totalScore;
    }

    public int resultDayThreePartTwo(ArrayList<String> inputList) {
        String letters = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
        List<String> letterlist = Arrays.asList(letters.split(","));
        int totalScore = 0;
        List<String> list = getGroupsOfThree(inputList);
        for (int i = 0; i < list.size(); i++) {
            String[] array = list.get(i).split(";");
            String tripleChar = getCharInThreeStrings(array);
            totalScore += (letterlist.indexOf(tripleChar) + 1);
        }
        return totalScore;
    }

    private String[] getSubString(String string) {
        int size = string.length()/2;
        StringBuilder sb = new StringBuilder(string);
        sb.insert(size, " ");
        String[] array = sb.toString().split(" ");
        return array;
    }

    private String getCharInBothStrings(String[] array) {
        String one = array[0];
        String two = array[1];

        for (int i = 0; i < one.length(); i++) {
            String chr = String.valueOf(one.charAt(i));
            if (two.contains(chr)) {
                return chr;
            }
        }
        return null;
    }

    private String getCharInThreeStrings(String[] array) {
        String one = array[0];
        String two = array[1];
        String three = array[2];

        for (int i = 0; i < one.length(); i++) {
            String chr = String.valueOf(one.charAt(i));
            if (two.contains(chr) && three.contains(chr)) {
                System.out.println(chr);
                return chr;
            }
        }
        return null;
    }

    private List<String> getGroupsOfThree(ArrayList<String> inputList) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String groupString = inputList.remove(0) + ";" + inputList.remove(0) + ";" + inputList.remove(0);

            list.add(getLongest(groupString));
        }
        return list;
    }

    private String getLongest(String str) {
        Comparator c = (Comparator<String>) (s1, s2) -> Integer.compare(s2.length(), s1.length());
        List<String> list = Arrays.asList(str.split(";"));
        Collections.sort(list, c);

        return list.get(0) + ";" + list.get(1) + ";" + list.get(2);
    }

    public int resultDayFourPartOne(ArrayList<String> inputList) {

        int totalOverlaps = 0;
        for (String input : inputList) {
            List<Integer> list1 = new ArrayList<>();
            List<Integer> list2 = new ArrayList<>();
            String[] array = input.split(",");
            String one = array[0];
            String two = array[1];

            String[] arrayOne = one.split("-");
            String[] arrayTwo = two.split("-");

            int oneOne = Integer.parseInt(arrayOne[0]);
            int oneTwo = Integer.parseInt(arrayOne[1]);
            int twoOne = Integer.parseInt(arrayTwo[0]);
            int twoTwo = Integer.parseInt(arrayTwo[1]);

            for (int i = oneOne; i < oneTwo + 1; i++) {
                list1.add(i);
            }
            for (int i = twoOne; i < twoTwo + 1; i++) {
                list2.add(i);
            }

            if (list1.size() > list2.size()) {
                for (int i = 0; i < list1.size(); i++) {
                    if (list2.contains(list1.get(i))) {
                        totalOverlaps++;
                        break;
                    }
                }
            } else {
                for (int i = 0; i < list2.size(); i++) {
                    if (list1.contains(list2.get(i))) {
                        totalOverlaps++;
                        break;
                    }
                }
            }

        }

        return totalOverlaps;
    }

}
