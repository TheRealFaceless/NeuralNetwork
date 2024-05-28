package dev.faceless.core.trainingdata;

import dev.faceless.core.MLDataSet;

public class TripleXORDataSet extends MLDataSet {

    public TripleXORDataSet() {
        super(TRIPLE_XOR_INPUT, TRIPLE_XOR_IDEAL);
    }

    private static final double[][] TRIPLE_XOR_INPUT = {
            {1, 1, 1},
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1},
            {0, 0, 0}
    };

    private static final double[][] TRIPLE_XOR_IDEAL = {
            {0, 0},
            {1, 1},
            {1, 1},
            {1, 1},
            {0, 0}
    };
}
