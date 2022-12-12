public class Tree {
    private int height;
    private boolean visibleFromLeft;
    private boolean visibleFromRight;
    private boolean visibleFromTop;
    private boolean visibleFromBottom;

    public Tree(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isVisibleFromLeft() {
        return visibleFromLeft;
    }

    public void setVisibleFromLeft(boolean visibleFromLeft) {
        this.visibleFromLeft = visibleFromLeft;
    }

    public boolean isVisibleFromRight() {
        return visibleFromRight;
    }

    public void setVisibleFromRight(boolean visibleFromRight) {
        this.visibleFromRight = visibleFromRight;
    }

    public boolean isVisibleFromTop() {
        return visibleFromTop;
    }

    public void setVisibleFromTop(boolean visibleFromTop) {
        this.visibleFromTop = visibleFromTop;
    }

    public boolean isVisibleFromBottom() {
        return visibleFromBottom;
    }

    public void setVisibleFromBottom(boolean visibleFromBottom) {
        this.visibleFromBottom = visibleFromBottom;
    }

    @Override
    public String toString() {
        return String.valueOf(height);
    }
}
