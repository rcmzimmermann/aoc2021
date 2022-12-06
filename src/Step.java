public class Step {
    private int amount;
    private int fromStack;
    private int toStack;

    public Step(int amount, int fromStack, int toStack) {
        this.amount = amount;
        this.fromStack = fromStack;
        this.toStack = toStack;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getFromStack() {
        return fromStack;
    }

    public void setFromStack(int fromStack) {
        this.fromStack = fromStack;
    }

    public int getToStack() {
        return toStack;
    }

    public void setToStack(int toStack) {
        this.toStack = toStack;
    }
}
