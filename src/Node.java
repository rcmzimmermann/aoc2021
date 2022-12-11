import java.util.HashMap;
import java.util.Map;

public class Node {
    private Type type;
    private Long size = 0L;
    private String name;
    private Map<String, Node> children = new HashMap<>();
    private Map<String, Node> dirChildren = new HashMap<>();
    private Map<String, Node> fileChildren = new HashMap<>();
    private Node parent;
    private boolean seen;

    public Node(Type type, String name) {
        this.type = type;
        this.name = name;
    }

    public Node(Type type, String name, Node parent) {
        this.type = type;
        this.name = name;
        this.parent = parent;
    }

    public Node(Type type, Long size, String name, Node parent) {
        this.type = type;
        this.size = size;
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public Long getSize() {
        return size;
    }

    public void updateSize() {
        if (this.type.equals(Type.DIR)) {
            children.forEach((k,v) -> {
                this.size += v.getSize();
            });
        }
        System.out.printf("Updated size for node %s: %s \n", getName(), getSize());
    }

    public Map<String, Node> getChildren() {
        return children;
    }

    public void setChildren(Map<String, Node> children) {
        this.children = children;
    }

    public int getAmountOfDirChildren() {
        return dirChildren.size();
    }

    public boolean hasDirChildren() {
        return !this.dirChildren.isEmpty();
    }

    public Map<String, Node> getDirChildren() {
        return dirChildren;
    }

    public void printDirChildren() {
        getDirChildren().forEach((k, v) -> System.out.println(v));
    }

    public int getAmountOfFileChildren() {
        return fileChildren.size();
    }

    public boolean hasFileChildren() {
        return !fileChildren.isEmpty();
    }

    public Map<String, Node> getFileChildren() {
        return fileChildren;
    }

    public void printFileChildren() {
        System.out.println("File children:");
        fileChildren.forEach((k, v) -> System.out.println(v));
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public void addChild(Node node) {
        this.children.put(node.getName(), node);
        if (node.getType().equals(Type.DIR)) {
            System.out.println("Adding dir " + node.getName() + " to node: " + this.name);
            this.dirChildren.put(node.getName(), node);
        }
        
        if (node.getType().equals(Type.FILE)) {
            System.out.println("Adding file " + node.getName() + " with size: " + node.getSize() + " to node: " + this.name);
            this.fileChildren.put(node.getName(), node);
        }
    }

    public Node moveToChild(String name) {
        System.out.println("Moving to node: " + this.dirChildren.get(name).getName());
        return this.dirChildren.get(name);
    }

    public Node moveToParent() {
        System.out.println("Moving to parent node: " + this.parent.getName());
        return this.parent;
    }

    @Override
    public String toString() {
        return "Node{" +
                "type=" + type +
                ", size=" + size +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                '}';
    }
}
