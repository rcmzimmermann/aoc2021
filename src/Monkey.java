import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Monkey {

    private int number;
    private List<BigInteger> items = new ArrayList<>();
    private String operation;
    private String operationValue;
    private BigInteger testDivideBy;
    private int toMonkeyTrue;
    private int toMonkeyFalse;

    private long numInspections;

    public Monkey(int number) {
        this.number = number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<BigInteger> getItems() {
        return items;
    }

    public void setItems(List<BigInteger> items) {
        this.items = items;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperationValue() {
        return operationValue;
    }

    public void setOperationValue(String operationValue) {
        this.operationValue = operationValue;
    }

    public BigInteger getTestDivideBy() {
        return testDivideBy;
    }

    public void setTestDivideBy(BigInteger testDivideBy) {
        this.testDivideBy = testDivideBy;
    }

    public int getToMonkeyTrue() {
        return toMonkeyTrue;
    }

    public void setToMonkeyTrue(int toMonkeyTrue) {
        this.toMonkeyTrue = toMonkeyTrue;
    }

    public int getToMonkeyFalse() {
        return toMonkeyFalse;
    }

    public void setToMonkeyFalse(int toMonkeyFalse) {
        this.toMonkeyFalse = toMonkeyFalse;
    }

    public long getNumInspections() {
        return numInspections;
    }

    public BigInteger inspect(BigInteger item) {
        BigInteger value = operationValue.equals("old") ? item : new BigInteger(operationValue);
        this.numInspections++;
        switch (operation) {
            case "*": return item.multiply(value);
            case "+": return item.add(value);
            default: return new BigInteger("0");
        }
    }

    public long relief(long item) {
        return item / 3;
    }

    public int test(BigInteger item) {
        return item.mod(testDivideBy).equals(BigInteger.valueOf(0L)) ? toMonkeyTrue : toMonkeyFalse;
    }

    @Override
    public String toString() {
        return "Monkey{" +
                "number=" + number +
                ", operation='" + operation + '\'' +
                ", operationValue='" + operationValue + '\'' +
                ", testDivideBy=" + testDivideBy +
                ", toMonkeyTrue=" + toMonkeyTrue +
                ", toMonkeyFalse=" + toMonkeyFalse +
                ", numInspections=" + numInspections +
                '}';
    }
}
