public class Move {
    private Direction direction;
    private int amount;

    public Move(Direction direction, int amount) {
        this.direction = direction;
        this.amount = amount;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Move{" +
                "direction=" + direction +
                ", amount=" + amount +
                '}';
    }
}
