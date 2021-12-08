import java.util.ArrayList;

public class BingoCard {

    private ArrayList<Integer> row1;
    private ArrayList<Integer> row2;
    private ArrayList<Integer> row3;
    private ArrayList<Integer> row4;
    private ArrayList<Integer> row5;

    private ArrayList<Boolean> row1Boolean;
    private ArrayList<Boolean> row2Boolean;
    private ArrayList<Boolean> row3Boolean;
    private ArrayList<Boolean> row4Boolean;
    private ArrayList<Boolean> row5Boolean;

    private boolean bingo;
    private int lastNumCalled;

    public BingoCard(ArrayList<Integer> row1, ArrayList<Integer> row2, ArrayList<Integer> row3, ArrayList<Integer> row4, ArrayList<Integer> row5) {
        this.row1 = row1;
        this.row2 = row2;
        this.row3 = row3;
        this.row4 = row4;
        this.row5 = row5;

        row1Boolean = new ArrayList<>();
        row2Boolean = new ArrayList<>();
        row3Boolean = new ArrayList<>();
        row4Boolean = new ArrayList<>();
        row5Boolean = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            row1Boolean.add(false);
            row2Boolean.add(false);
            row3Boolean.add(false);
            row4Boolean.add(false);
            row5Boolean.add(false);
        }
    }

    public void callNumber(int number) {
        boolean inRow1 = row1.contains(number);
        if (inRow1) {
            int index = row1.indexOf(number);
            row1Boolean.set(index, true);
        }

        boolean inRow2 = row2.contains(number);
        if (inRow2) {
            int index = row2.indexOf(number);
            row2Boolean.set(index, true);
        }

        boolean inRow3 = row3.contains(number);
        if (inRow3) {
            int index = row3.indexOf(number);
            row3Boolean.set(index, true);
        }

        boolean inRow4 = row4.contains(number);
        if (inRow4) {
            int index = row4.indexOf(number);
            row4Boolean.set(index, true);
        }

        boolean inRow5 = row5.contains(number);
        if (inRow5) {
            int index = row5.indexOf(number);
            row5Boolean.set(index, true);
        }
    }

    public boolean checkBingo() {
        if (row1Boolean.contains(false) || row2Boolean.contains(false) || row3Boolean.contains(false) ||
                row4Boolean.contains(false) || row5Boolean.contains(false)) {
            setBingo(false);
        } else {
            setBingo(true);
        }

        for (int i = 0; i < 5; i++) {
            if (row1Boolean.get(i) && row2Boolean.get(i) && row3Boolean.get(i) && row4Boolean.get(i) && row5Boolean.get(i)) {
                setBingo(true);
                break;
            }
        }
        return bingo;
    }

    // GETTERS


    public ArrayList<Integer> getRow1() {
        return row1;
    }

    public ArrayList<Integer> getRow2() {
        return row2;
    }

    public ArrayList<Integer> getRow3() {
        return row3;
    }

    public ArrayList<Integer> getRow4() {
        return row4;
    }

    public ArrayList<Integer> getRow5() {
        return row5;
    }

    public ArrayList<Boolean> getRow1Boolean() {
        return row1Boolean;
    }

    public ArrayList<Boolean> getRow2Boolean() {
        return row2Boolean;
    }

    public ArrayList<Boolean> getRow3Boolean() {
        return row3Boolean;
    }

    public ArrayList<Boolean> getRow4Boolean() {
        return row4Boolean;
    }

    public ArrayList<Boolean> getRow5Boolean() {
        return row5Boolean;
    }

    public void setBingo(boolean bingo) {
        this.bingo = bingo;
    }

    public int getLastNumCalled() {
        return lastNumCalled;
    }

    public void setLastNumCalled(int lastNumCalled) {
        this.lastNumCalled = lastNumCalled;
    }

    @Override
    public String toString() {

        String str = "Card: \n";

        for (int num : row1) {
            str += num + " | ";
        }

        str += "\n";

        for (int num : row2) {
            str += num + " | ";
        }

        str += "\n";

        for (int num : row3) {
            str += num + " | ";
        }

        str += "\n";

        for (int num : row4) {
            str += num + " | ";
        }

        str += "\n";

        for (int num : row5) {
            str += num + " | ";
        }

        str += "\n";

        for (boolean bool : row1Boolean) {
            str += bool + " | ";
        }

        str += "\n";

        for (boolean bool : row2Boolean) {
            str += bool + " | ";
        }

        str += "\n";

        for (boolean bool : row3Boolean) {
            str += bool + " | ";
        }

        str += "\n";

        for (boolean bool : row4Boolean) {
            str += bool + " | ";
        }

        str += "\n";

        for (boolean bool : row5Boolean) {
            str += bool + " | ";
        }

        str += "\n";

        str += "Last number called: " + getLastNumCalled();

        return str;
    }

}
