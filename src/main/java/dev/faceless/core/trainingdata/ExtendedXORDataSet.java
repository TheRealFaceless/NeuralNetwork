package dev.faceless.core.trainingdata;

import dev.faceless.core.MLDataSet;

@SuppressWarnings("unused")
public class ExtendedXORDataSet extends MLDataSet {

    public ExtendedXORDataSet() {
        super(EXTENDED_XOR_INPUT, EXTENDED_XOR_IDEAL);
    }

    private static final double[][] EXTENDED_XOR_INPUT = {
            {1, 1},
            {1, 0},
            {0, 1},
            {0, 0},
            {0.9, 1},
            {1, 0.9},
            {0, 0.1},
            {0.1, 0},
            {0.5, 0.5}
    };

    private static final double[][] EXTENDED_XOR_IDEAL = {
            {0},
            {1},
            {1},
            {0},
            {1},
            {1},
            {0},
            {0},
            {0.5}
    };
}
