package com.rakalab.eulertriangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparingInt;

/**
 * EulerTriangle solver :
 * Add data to triangle as an int array for each row.
 * EulerTriangle can be queried for maximum sum after each row is added.
 */
public class EulerTriangle {

    private List<Node[]> triangleData;
    private int height;

    public EulerTriangle() {
        triangleData = new ArrayList<>();
        height = 0;
    }

    /**
     * Adds a new row at the bottom of this EulerTriangle
     *
     * @param data int array of numbers of newly added row
     */
    public void addRow(int[] data) {
        if (data == null || data.length == 0)
            throw new IllegalArgumentException("Invalid input data or size:" + data);

        Node[] newRow;
        if (height == 0) {
            newRow = buildRow(data, null);
        } else {
            newRow = buildRow(data, triangleData.get(height - 1));
        }

        height ++;
        triangleData.add(newRow);
    }

    /**
     * Returns highst sum that this EulerTriangle can arrive by traversing elements from top to bottom
     */
    public int getMaximumSum() {
        return getMaximumSumNode().getHighestSum();
    }

    /**
     * Returns path which is the maximum sum of this EulerTriangle
     */
    public String getHighestSumPath() {
        Node maxSumNode = getMaximumSumNode();
        StringBuilder path = new StringBuilder(String.valueOf(maxSumNode.getValue()));
        while ( (maxSumNode = maxSumNode.getPathToHighest()) != null) {
            path.insert(0, maxSumNode.getValue() + " + ");
        }

        return path.toString();
    }

    private Node getMaximumSumNode() {
        if (height == 0) throw new IllegalStateException("Nothing in the EulerTriangle to find maximum!");

        Node[] leafNodes = triangleData.get(height - 1);
        return Arrays.stream(leafNodes).max(comparingInt(Node::getHighestSum)).get();
    }

    /**
     * Builds new row of Nodes of this EulerTriangle.
     * Highest sum and path to highest sum are calculated and populated on newly created Nodes
     * based on given parent nodes.
     *
     * @param data   values to add to the bottom of the triangle
     * @param parent last row of the triangle which is the parent for new row
     * @return Array of newly created Nodes.
     */
    Node[] buildRow(int[] data, Node[] parent) {
        Node[] newRow;

        if (parent == null) {
            // first row of the triangle.
            if (data.length != 1) {
                throw new IllegalArgumentException("First row of the EulerTriangle must have exactly 1 value");
            }
            newRow = new Node[1];
            newRow[0] = buildNode(data[0], null, null);
        } else {
            // 2nd and subsequent row of the triangle
            if (data.length != parent.length + 1) {
                throw new IllegalArgumentException("Incorrect number of values. newRow.length should be equal to parentRow.length + 1.");
            }

            newRow = new Node[data.length];

            for (int i = 0; i < data.length; i++) {
                Node leftParent = (i == 0) ? null : parent[i - 1];
                Node rightParent = (i == data.length - 1) ? null : parent[i];

                newRow[i] = buildNode(data[i], leftParent, rightParent);
            }
        }

        return newRow;
    }

    /**
     * Creates Node, calculate highest-sum and store reference to parent node which makes highest sum.
     *
     * @param value       value of the new Node
     * @param leftParent  left-parent Node from parent row or null if none
     * @param rightParent right-parent Node from parent row or null if none
     * @return newly created Node
     */
    Node buildNode(int value, Node leftParent, Node rightParent) {
        Node highestSumNode;

        if (leftParent == null && rightParent == null) {
            // root node;
            return new Node(value);
        } else if (leftParent == null) {
            // left most node - only rightParent is available
            highestSumNode = rightParent;
        } else if (rightParent == null) {
            // right most node - only leftParent is available
            highestSumNode = leftParent;
        } else if (leftParent.getHighestSum() + value > rightParent.getHighestSum() + value) {
            // current node value + leftParent's highest makes highest path
            highestSumNode = leftParent;
        } else {
            // rightParent is highest
            highestSumNode = rightParent;
        }

        return new Node(value, highestSumNode.getHighestSum() + value, highestSumNode);
    }
}
