package dev.faceless.core.trainingdata;

import dev.faceless.core.MLDataSet;

@SuppressWarnings("unused")
public class XORDataSet extends MLDataSet {

    public XORDataSet() {
        super(XOR_INPUT, XOR_IDEAL);
    }

    private static final double[][] XOR_INPUT = {
            {1, 1},
            {1, 0},
            {0, 1},
            {0, 0}
    };

    private static final double[][] XOR_IDEAL = {
            {0},
            {1},
            {1},
            {0}
    };
}
