import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Struct;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    final String ROCK = "Rock";
    final String PAPER = "Paper";
    final String SCISSORS = "Scissors";
    final String LOSE = "Lose";
    final String DRAW = "Draw";
    final String WIN = "WIN";

    Map<Integer, Stack> stacks = new HashMap<>();

    public static void main(String[] args) {
        Main main = new Main();
        main.fillStacks();
        String stringResult = "";
        int intResult = 0;
        try {
            main.resultDayEight(main.getStringInputFromList("C:\\Users\\robizimm\\Documents\\AOC\\aoc2021\\day-eight.txt"));

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }

//        System.out.println("Result 1: " + intResult);
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


    public void resultDayEight(List<String> inputList) {
        Map<Integer, List<Tree>> treeGrid = new HashMap<>();
        for (int i = 0; i < inputList.size(); i++) {
            List<String> row = List.of(inputList.get(i).split(""));
            List<Tree> treeRow = new ArrayList<>();
            row.forEach(t -> treeRow.add(new Tree(Integer.parseInt(t))));
            treeGrid.put(i, treeRow);
        }

        treeGrid.forEach((k, row) -> {
            if (k == 0) {
                row.forEach(t -> t.setVisibleFromTop(true));
            } else if (k == treeGrid.size() - 1) {
                row.forEach(t -> t.setVisibleFromBottom(true));
            }

            row.get(0).setVisibleFromLeft(true);
            row.get(row.size() - 1).setVisibleFromRight(true);
        });

        int highestFromLeftIndex = getVisibleFromLeft(treeGrid);
        int highestFromRightIndex = getVisibleFromRight(treeGrid);
        int highestFromTopIndex = getVisibleFromTop(treeGrid);
        int heighestFromBottomIndex = getVisibleFromBottom(treeGrid);

        int numTreesVisible = 0;
        for (Map.Entry<Integer, List<Tree>> entry : treeGrid.entrySet()) {
            Integer k = entry.getKey();
            List<Tree> row = entry.getValue();
            for (Tree t : row) {
                if (t.isVisibleFromBottom() || t.isVisibleFromLeft() || t.isVisibleFromRight() || t.isVisibleFromTop())
                    numTreesVisible += 1;
            }
        }

//        treeGrid.forEach((k,row) -> {
//            row.forEach(t -> System.out.print(t.isVisibleFromLeft() + " "));
//            System.out.println("");
//        });


    }

    public int getVisibleFromLeft(Map<Integer, List<Tree>> treeGrid) {
        for (Map.Entry<Integer, List<Tree>> entry : treeGrid.entrySet()) {
            Integer k = entry.getKey();
            List<Tree> row = entry.getValue();
            System.out.println("\nRow: " + (k) + "\n" + row);
            if (k == 0) {
                row.forEach(t -> {
                    t.setVisibleFromTop(true);
                    t.setViewingDistanceTop(0);
                });
                if (k == treeGrid.size() - 1) {
                    row.forEach(t -> {
                        t.setVisibleFromBottom(true);
                        t.setViewingDistanceBottom(0);
                    });
                }
                // Visible from left
                row.get(0).setVisibleFromLeft(true);
                row.get(0).setViewingDistanceLeft(0);
                row.get(row.size() - 1).setViewingDistanceRight(0);
                int highestFromLeftIndex = 0;
                int previousHeight = 0;
                int currentHeight = 0;
                for (int i = 0; i < row.size(); i++) {
                    currentHeight = row.get(i).getHeight();
                    int numTreesVisibleFromLeft = 0;
                    System.out.println("Previous height: " + previousHeight);
                    System.out.println("Current height: " + currentHeight);
                    if (previousHeight < row.get(i).getHeight()) {
                        previousHeight = row.get(i).getHeight();
                        System.out.println("Trees visible on left side: " + numTreesVisibleFromLeft);
                        row.get(i).setVisibleFromLeft(true);
                        if (row.get(i).getHeight() > row.get(highestFromLeftIndex).getHeight()) {
                            highestFromLeftIndex = i;
                        }
                    } else {
                        previousHeight = row.get(i).getHeight();
                    }
                }
//                System.out.println("Highest from left index: " + highestFromLeftIndex);
            }
        }
        return 0;
    }

    public int getVisibleFromRight(Map<Integer, List<Tree>> treeGrid) {
        for (Map.Entry<Integer, List<Tree>> entry : treeGrid.entrySet()) {
            Integer k = entry.getKey();
            List<Tree> row = entry.getValue();
//            System.out.println("\nRow: " + (k + 1) + "\n" + row);
            if (k == 0) {
                row.forEach(t -> t.setVisibleFromTop(true));
            } else if (k == row.size() - 1) {
                row.forEach(t -> t.setVisibleFromBottom(true));
            } else {
                // Visible from Right
                row.get(row.size() - 1).setVisibleFromRight(true);
                int highestFromRightIndex = row.size() - 1;
                for (int i = row.size() - 2; i > 0; i--) {
                    if (row.get(i + 1).getHeight() < row.get(i).getHeight() && row.get(i).getHeight() > row.get(highestFromRightIndex).getHeight()) {
//                        System.out.println(row.get(i + 1).getHeight() + "<" + row.get(i).getHeight());
                        row.get(i).setVisibleFromRight(true);
                        if (row.get(i).getHeight() > row.get(highestFromRightIndex).getHeight()) {
                            highestFromRightIndex = i;
                        }
                    }
                }
//                System.out.println("Highest from right index: " + highestFromRightIndex);
            }
        }
        return 0;
    }

    public int getVisibleFromTop(Map<Integer, List<Tree>> treeGrid) {

        for (int col = 1; col < treeGrid.get(0).size(); col++) {
            int highestFromTopIndex = 0;
//            System.out.println("\nCol: " + col);
            for (int row = 1; row < treeGrid.size() - 1; row++) {
                treeGrid.get(0).forEach(t -> t.setVisibleFromTop(true));

                if (treeGrid.get(row - 1).get(col).getHeight() < treeGrid.get(row).get(col).getHeight() &&
                        treeGrid.get(row).get(col).getHeight() > treeGrid.get(highestFromTopIndex).get(col).getHeight()) {
                    treeGrid.get(row).get(col).setVisibleFromTop(true);
//                    System.out.println(treeGrid.get(row - 1).get(col).getHeight() + "<" + treeGrid.get(row).get(col).getHeight());
                    if (treeGrid.get(row).get(col).getHeight() > treeGrid.get(highestFromTopIndex).get(col).getHeight()) {
                        highestFromTopIndex = row;
                    }
                }
            }
//            System.out.println("Highest index from top: " + highestFromTopIndex);
        }

        return 0;
    }

    public int getVisibleFromBottom(Map<Integer, List<Tree>> treeGrid) {
        for (int col = 1; col < treeGrid.get(0).size(); col++) {
            int highestFromBottomIndex = treeGrid.size() - 1;
//            System.out.println("\nCol: " + col);
            for (int row = treeGrid.size() - 2; row > 0; row--) {
                treeGrid.get(treeGrid.size() - 1).forEach(t -> t.setVisibleFromBottom(true));

                if (treeGrid.get(row + 1).get(col).getHeight() < treeGrid.get(row).get(col).getHeight() &&
                        treeGrid.get(row).get(col).getHeight() > treeGrid.get(highestFromBottomIndex).get(col).getHeight()) {
                    treeGrid.get(row).get(col).setVisibleFromBottom(true);
//                    System.out.println(treeGrid.get(row + 1).get(col).getHeight() + "<" + treeGrid.get(row).get(col).getHeight());
                    if (treeGrid.get(row).get(col).getHeight() > treeGrid.get(highestFromBottomIndex).get(col).getHeight()) {
                        highestFromBottomIndex = row;
                    }
                }
            }
//            System.out.println("Highest index from bottom: " + highestFromBottomIndex);
        }

        return 0;
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

        int top3Cals = list.get(list.size() - 1) + list.get(list.size() - 2) + list.get(list.size() - 3);
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
        int size = string.length() / 2;
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

    public void fillStacks() {
        Stack<String> stack1 = new Stack<>();
        stack1.push("B");
        stack1.push("Q");
        stack1.push("C");
        stacks.put(1, stack1);

        Stack<String> stack2 = new Stack<>();
        stack2.push("R");
        stack2.push("Q");
        stack2.push("W");
        stack2.push("Z");
        stacks.put(2, stack2);

        Stack<String> stack3 = new Stack<>();
        stack3.push("B");
        stack3.push("M");
        stack3.push("R");
        stack3.push("L");
        stack3.push("V");
        stacks.put(3, stack3);

        Stack<String> stack4 = new Stack<>();
        stack4.push("C");
        stack4.push("Z");
        stack4.push("H");
        stack4.push("V");
        stack4.push("T");
        stack4.push("W");
        stacks.put(4, stack4);

        Stack<String> stack5 = new Stack<>();
        stack5.push("D");
        stack5.push("Z");
        stack5.push("H");
        stack5.push("B");
        stack5.push("N");
        stack5.push("V");
        stack5.push("G");
        stacks.put(5, stack5);

        Stack<String> stack6 = new Stack<>();
        stack6.push("H");
        stack6.push("N");
        stack6.push("P");
        stack6.push("C");
        stack6.push("J");
        stack6.push("F");
        stack6.push("V");
        stack6.push("Q");
        stacks.put(6, stack6);

        Stack<String> stack7 = new Stack<>();
        stack7.push("D");
        stack7.push("G");
        stack7.push("T");
        stack7.push("R");
        stack7.push("W");
        stack7.push("Z");
        stack7.push("S");
        stacks.put(7, stack7);

        Stack<String> stack8 = new Stack<>();
        stack8.push("C");
        stack8.push("G");
        stack8.push("M");
        stack8.push("N");
        stack8.push("B");
        stack8.push("W");
        stack8.push("Z");
        stack8.push("P");
        stacks.put(8, stack8);

        Stack<String> stack9 = new Stack<>();
        stack9.push("N");
        stack9.push("J");
        stack9.push("B");
        stack9.push("M");
        stack9.push("W");
        stack9.push("Q");
        stack9.push("F");
        stack9.push("P");
        stacks.put(9, stack9);
    }

    public void resultDayFivePartOne(List<String> inputList) {

        inputList.forEach(i -> {
            i = i.replace("move ", "");
            i = i.replace(" from ", ":");
            i = i.replace(" to ", ":");
            String[] array = i.split(":");
            Step step = new Step(Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2]));
            moveItemsOnStack(step);
        });
    }

    public void moveItemsOnStack(Step step) {
        int amount = step.getAmount();
        int fromStack = step.getFromStack();
        int toStack = step.getToStack();

        List<String> moves = new ArrayList<>();
        for (int i = 0; i < amount; i++) {

            moves.add((String) stacks.get(fromStack).pop());
        }

        Collections.reverse(moves);
        moves.forEach(m -> stacks.get(toStack).push(m));
    }

    public int resultDaySix(List<String> inputList) {
        String input = inputList.get(0);
        for (int i = 0; i < input.length(); i++) {
            if (i + 14 > input.length()) {
                break;
            }

            String substring = input.substring(i, i + 14);
            if (isUniqueSequence(substring))
                return input.indexOf(substring) + 14;
        }
        return 0;
    }

    public boolean isUniqueSequence(String substring) {
        char[] sequence = substring.toCharArray();
        for (int i = 0; i < sequence.length - 1; i++) {
            for (int j = i + 1; j < sequence.length; j++) {
                if (sequence[i] == sequence[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void resultDaySeven(List<String> inputList) {
        Node root = new Node(Type.DIR, "Root");
        Node currentNode = root;
        for (String i : inputList) {
            String[] array = i.split(" ");
            if (i.startsWith("$ cd")) {
                if (i.contains("..")) {
                    currentNode.updateSize();
                    currentNode = currentNode.moveToParent();
                } else {
                    currentNode = currentNode.moveToChild(array[2]);
                }
            } else if (i.startsWith("$ ls")) {
                System.out.println("list contents");

            } else if (i.startsWith("dir")) {
                currentNode.addChild(new Node(Type.DIR, array[1], currentNode));

            } else if (Pattern.matches("^[0-9]+ [a-z]*.*[a-z]*", i)) {
                currentNode.addChild(new Node(Type.FILE, Long.valueOf(array[0]), array[1], currentNode));

            } else {
                System.out.println("something wrong");
            }
        }

        root.updateSize();

        long totalFileSystemSize = 70000000;
        long requiredUnusedSpace = 30000000;
        long rootSize = root.getSize();
        long difference = totalFileSystemSize - rootSize;
        long extraSpaceNeeded = requiredUnusedSpace - difference;

        long total = 0L;

        List<Node> bigEnough = new ArrayList<>();

        for (Map.Entry<String, Node> entry : root.getChildren().entrySet()) {
            Node b = entry.getValue();
            if (b.getType().equals(Type.DIR)) {
                System.out.println("1. |__ " + b.getName() + ": " + b.getSize());
                if (b.getSize() < 100000) {
                    total += b.getSize();
                }
                if (b.getSize() > extraSpaceNeeded) {
                    bigEnough.add(b);
                }
            } else {
                System.out.println("1. ___ " + b.getName() + ": " + b.getSize());
            }
            for (Map.Entry<String, Node> mapEntry : b.getChildren().entrySet()) {
                Node d = mapEntry.getValue();
                if (d.getType().equals(Type.DIR)) {
                    System.out.println("\t2. |__ " + d.getName() + ": " + d.getSize());
                    if (d.getSize() < 100000) {
                        total += d.getSize();
                    }
                    if (d.getSize() > extraSpaceNeeded) {
                        bigEnough.add(d);
                    }
                } else {
                    System.out.println("\t2. ___ " + d.getName() + ": " + d.getSize());
                }
                for (Map.Entry<String, Node> e : d.getChildren().entrySet()) {
                    Node f = e.getValue();
                    if (f.getType().equals(Type.DIR)) {
                        System.out.println("\t\t3. |__ " + f.getName() + ": " + f.getSize());
                        if (f.getSize() < 100000) {
                            total += f.getSize();
                        }
                        if (f.getSize() > extraSpaceNeeded) {
                            bigEnough.add(f);
                        }
                    } else {
                        System.out.println("\t\t3. ___ " + f.getName() + ": " + f.getSize());
                    }
                    for (Map.Entry<String, Node> stringNodeEntry : f.getChildren().entrySet()) {
                        Node h = stringNodeEntry.getValue();
                        if (h.getType().equals(Type.DIR)) {
                            System.out.println("\t\t\t4. |__ " + h.getName() + ": " + h.getSize());
                            if (h.getSize() < 100000) {
                                total += h.getSize();
                            }
                            if (h.getSize() > extraSpaceNeeded) {
                                bigEnough.add(h);
                            }
                        } else {
                            System.out.println("\t\t\t4. ___ " + h.getName() + ": " + h.getSize());
                        }
                        for (Map.Entry<String, Node> nodeEntry : h.getChildren().entrySet()) {
                            Node j = nodeEntry.getValue();
                            if (j.getType().equals(Type.DIR)) {
                                System.out.println("\t\t\t\t5. |__ " + j.getName() + ": " + j.getSize());
                                if (j.getSize() < 100000) {
                                    total += j.getSize();
                                }
                                if (j.getSize() > extraSpaceNeeded) {
                                    bigEnough.add(j);
                                }
                            } else {
                                System.out.println("\t\t\t\t5. ___ " + j.getName() + ": " + j.getSize());
                            }
                            for (Map.Entry<String, Node> entry1 : j.getChildren().entrySet()) {
                                Node l = entry1.getValue();
                                if (l.getType().equals(Type.DIR)) {
                                    System.out.println("\t\t\t\t\t6. |__ " + l.getName() + ": " + l.getSize());
                                    if (l.getSize() < 100000) {
                                        total += l.getSize();
                                    }
                                    if (l.getSize() > extraSpaceNeeded) {
                                        bigEnough.add(l);
                                    }
                                } else {
                                    System.out.println("\t\t\t\t\t6. ___ " + l.getName() + ": " + l.getSize());
                                }
                                for (Map.Entry<String, Node> e1 : l.getChildren().entrySet()) {
                                    Node n = e1.getValue();
                                    if (n.getType().equals(Type.DIR)) {
                                        System.out.println("\t\t\t\t\t\t7. |__ " + n.getName() + ": " + n.getSize());
                                        if (n.getSize() < 100000) {
                                            total += n.getSize();
                                        }
                                        if (n.getSize() > extraSpaceNeeded) {
                                            bigEnough.add(n);
                                        }
                                    } else {
                                        System.out.println("\t\t\t\t\t\t7. ___ " + n.getName() + ": " + n.getSize());
                                    }
                                    for (Map.Entry<String, Node> mapEntry1 : n.getChildren().entrySet()) {
                                        Node p = mapEntry1.getValue();
                                        if (p.getType().equals(Type.DIR)) {
                                            System.out.println("\t\t\t\t\t\t\t8. |__ " + p.getName() + ": " + p.getSize());
                                            if (p.getSize() < 100000) {
                                                total += p.getSize();
                                            }
                                            if (p.getSize() > extraSpaceNeeded) {
                                                bigEnough.add(p);
                                            }
                                        } else {
                                            System.out.println("\t\t\t\t\t\t\t8. ___ " + p.getName() + ": " + p.getSize());
                                        }
                                        for (Map.Entry<String, Node> stringNodeEntry1 : p.getChildren().entrySet()) {
                                            Node r = stringNodeEntry1.getValue();
                                            if (r.getType().equals(Type.DIR)) {
                                                System.out.println("\t\t\t\t\t\t\t\t9. |__ " + r.getName() + ": " + r.getSize());
                                                if (r.getSize() < 100000) {
                                                    total += r.getSize();
                                                }
                                                if (r.getSize() > extraSpaceNeeded) {
                                                    bigEnough.add(r);
                                                }
                                            } else {
                                                System.out.println("\t\t\t\t\t\t\t\t9. ___ " + r.getName() + ": " + r.getSize());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println("\n\nTotal: " + total);


        System.out.println("\nRoot size: " + rootSize);
        System.out.println("Total filesystem size: " + totalFileSystemSize);
        System.out.println("Difference: " + difference);
        System.out.println("Extra space required: " + extraSpaceNeeded + "\n\n");

        bigEnough.sort(Comparator.comparing(Node::getSize));
        bigEnough.forEach(n -> System.out.println(n));

    }
}
