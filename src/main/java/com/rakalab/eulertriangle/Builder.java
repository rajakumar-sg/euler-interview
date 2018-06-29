package com.rakalab.eulertriangle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;

/**
 * Helper Builder class for convinence.
 * 1. Builds EulerTriangle from String source
 * 2. And builds from Reader source
 * Both input should have space separated values and new-line separated rows of EulerTriangle
 */
public class Builder {

    /**
     * Helps build triangle from String input source
     */
    public EulerTriangle buildTriangle(String input) {
        try {
            return buildTriangle(new StringReader(input));
        } catch (IOException ioEx) {
            // unlikely
            ioEx.printStackTrace();
            return null;
        }
    }

    /**
     * Helps build triangle from Reader input source
     */
    public EulerTriangle buildTriangle(Reader input) throws IOException {
        EulerTriangle eulerTriangle = new EulerTriangle();
        try (BufferedReader reader = new BufferedReader(input)) {
            String inputLine;
            while((inputLine = reader.readLine()) != null) {
                eulerTriangle.addRow(buildArray(inputLine));
            }
        }

        return eulerTriangle;
    }

    /**
     * Splits given string by spaces and converts it to int[].
     * package private method for testability.
     */
    int[] buildArray(String values) {
        return Arrays.stream(values.trim().split("\\s+"))
                .map(String::trim)
                .mapToInt(Integer::new)
                .toArray();
    }
}
