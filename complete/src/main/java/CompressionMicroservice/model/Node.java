package CompressionMicroservice.model;

import java.io.Serializable;
import java.util.Objects;

public class Node implements Serializable {

    private Node left;
    private Node right;
    private String value;
    private Integer frequency;

    public Node() {}

    public Node(String value, Integer frequency) {
        this.value = value;
        this.frequency = frequency;
    }

    public boolean isLeap() {
        return this.value != null;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Node getLeft() {
        return this.left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Integer getFrequency() { return frequency; }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(left, node.left) &&
                Objects.equals(right, node.right) &&
                Objects.equals(value, node.value) &&
                Objects.equals(frequency, node.frequency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, value, frequency);
    }

}
