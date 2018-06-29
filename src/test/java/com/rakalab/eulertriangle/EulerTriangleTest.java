package com.rakalab.eulertriangle;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.junit.Assert.assertThat;

public class EulerTriangleTest {

    EulerTriangle eulerTriangle = new EulerTriangle();

    @Test
    public void should_build_root_node_with_highest_parent_as_null() {
        Node root = eulerTriangle.buildNode(1, null, null);
        assertThat(root.getPathToHighest(), is(nullValue()));
    }

    @Test
    public void should_build_root_node_with_highest_sum_as_node_value() {
        Node root = eulerTriangle.buildNode(10, null, null);
        assertThat(root.getHighestSum(), is(10));
    }

    @Test
    public void should_use_rightParent_as_highest_when_leftParent_is_null() {
        // given
        Node rightParent = new Node(2, 10, new Node(10));

        // when
        Node node = eulerTriangle.buildNode(10, null, rightParent);

        // then
        assertThat(node.getHighestSum(), is(20));
        assertThat(node.getPathToHighest(), is(rightParent));
    }

    @Test
    public void should_use_leftParent_as_highest_when_rightParent_is_null() {
        // given
        Node leftParent = new Node(2, 15, new Node(15));

        // when
        Node node = eulerTriangle.buildNode(10, leftParent, null);

        // then
        assertThat(node.getHighestSum(), is(25));
        assertThat(node.getPathToHighest(), is(leftParent));
    }

    @Test
    public void should_calculate_highest_as_leftParent() {
        // given
        Node leftParent = new Node(2, 15, new Node(15));
        Node rightParent = new Node(2, 10, new Node(15));

        // when
        Node node = eulerTriangle.buildNode(10, leftParent, rightParent);

        // then
        assertThat(node.getHighestSum(), is(25));
        assertThat(node.getPathToHighest(), is(leftParent));
    }

    @Test
    public void should_calculate_highest_as_rightParent() {
        // given
        Node leftParent = new Node(2, 15, new Node(15));
        Node rightParent = new Node(2, 30, new Node(15));

        // when
        Node node = eulerTriangle.buildNode(10, leftParent, rightParent);

        // then
        assertThat(node.getHighestSum(), is(40));
        assertThat(node.getPathToHighest(), is(rightParent));
    }

    @Test
    public void should_build_root_row() {
        // given parent = null and
        // first element = 3

        // when
        Node[] rootRow = eulerTriangle.buildRow(new int[]{3}, null);

        // then
        assertThat(rootRow, arrayWithSize(1));
        assertThat(rootRow[0].getHighestSum(), is(3));
        assertThat(rootRow[0].getPathToHighest(), is(nullValue()));
    }

    @Test
    public void should_build_second_row() {
        // given
        //
        //      3
        //    7   4
        //
        Node[] parent = eulerTriangle.buildRow(new int[]{3}, null);

        // when
        Node[] secondRow = eulerTriangle.buildRow(new int[]{7, 4}, parent);

        // then
        assertThat(secondRow, arrayWithSize(2));

        assertThat(secondRow[0].getHighestSum(), is(10));
        assertThat(secondRow[0].getPathToHighest(), is(parent[0]));

        assertThat(secondRow[1].getHighestSum(), is(7));
        assertThat(secondRow[1].getPathToHighest(), is(parent[0]));
    }

    @Test
    public void should_build_third_row() {
        // given
        //
        //      3
        //    7   4
        //
        //
        Node[] parent = eulerTriangle.buildRow(new int[]{3}, null);
        Node[] secondRow = eulerTriangle.buildRow(new int[]{7, 4}, parent);

        // when add
        //
        //  2   4   6
        //
        Node[] thirdRow = eulerTriangle.buildRow(new int[]{2, 4, 6}, secondRow);

        // then
        assertThat(thirdRow, arrayWithSize(3));

        assertThat(thirdRow[0].getHighestSum(), is(12));
        assertThat(thirdRow[0].getPathToHighest(), is(secondRow[0]));

        assertThat(thirdRow[1].getHighestSum(), is(14));
        assertThat(thirdRow[1].getPathToHighest(), is(secondRow[0]));

        assertThat(thirdRow[2].getHighestSum(), is(13));
        assertThat(thirdRow[2].getPathToHighest(), is(secondRow[1]));
    }

    @Test
    public void should_build_fourth_row() {
        // given
        //
        //      3
        //     7 4
        //    2 4 6
        //
        Node[] parent = eulerTriangle.buildRow(new int[]{3}, null);
        Node[] secondRow = eulerTriangle.buildRow(new int[]{7, 4}, parent);
        Node[] thirdRow = eulerTriangle.buildRow(new int[]{2, 4, 6}, secondRow);

        // when add
        //   8 5 9 3
        //
        Node[] fourthRow = eulerTriangle.buildRow(new int[]{8, 5, 9, 3}, thirdRow);

        // then
        assertThat(fourthRow, arrayWithSize(4));

        assertThat(fourthRow[0].getHighestSum(), is(20));
        assertThat(fourthRow[0].getPathToHighest(), is(thirdRow[0]));

        assertThat(fourthRow[1].getHighestSum(), is(19));
        assertThat(fourthRow[1].getPathToHighest(), is(thirdRow[1]));

        assertThat(fourthRow[2].getHighestSum(), is(23));
        assertThat(fourthRow[2].getPathToHighest(), is(thirdRow[1]));

        assertThat(fourthRow[3].getHighestSum(), is(16));
        assertThat(fourthRow[3].getPathToHighest(), is(thirdRow[2]));
    }

    @Test
    public void should_return_maximum_sum_23() {
        // given
        //
        //      3
        //     7 4
        //    2 4 6
        //   8 5 9 3
        //
        eulerTriangle.addRow(new int[]{3});
        eulerTriangle.addRow(new int[]{7, 4});
        eulerTriangle.addRow(new int[]{2, 4, 6});
        eulerTriangle.addRow(new int[]{8, 5, 9, 3});

        // when add
        int maxSum = eulerTriangle.getMaximumSum();

        // then
        assertThat(maxSum, is(23));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_for_null_data() {
        eulerTriangle.addRow(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_for_empty_data() {
        eulerTriangle.addRow(new int[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannot_build_root_row_with_more_than_one_element() {
        eulerTriangle.buildRow(new int[]{1, 2}, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannot_build_root_row_with_zero_element() {
        eulerTriangle.buildRow(new int[]{}, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannot_build_new_row_with_more_elements_than_parent_length_plus_1() {
        eulerTriangle.buildRow(new int[]{1, 2, 3, 4}, new Node[2]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannot_build_new_row_with_less_elements_than_parent_length_plus_1() {
        eulerTriangle.buildRow(new int[]{1, 2, 3}, new Node[3]);
    }
}