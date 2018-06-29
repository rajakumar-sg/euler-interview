package com.rakalab.eulertriangle;

/**
 * Each value in EulerTriangle is represented by Node.
 *
 * Node additionally holds highest sum and reference to parent Node which makes highest-sum path.
 */
public class Node {
    private int value;

    private int     highestSum;
    private Node    pathToHighest;

    Node(int value) {
        this.value = value;
        this.highestSum = value;
        this.pathToHighest = null;
    }

    Node(int value, int highestSum, Node pathToHighest) {
        this.value = value;
        this.highestSum = highestSum;
        this.pathToHighest = pathToHighest;
    }

    public int getValue() {
        return value;
    }

    public int getHighestSum() {
        return highestSum;
    }

    public Node getPathToHighest() {
        return pathToHighest;
    }

    @Override
    public String toString() {
        return "[" + String.valueOf(value) + ", " + highestSum + "]";
    }
}