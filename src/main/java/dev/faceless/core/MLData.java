package dev.faceless.core;

import java.util.Arrays;

@SuppressWarnings("unused")
public class MLData {

    private double[] inputs;
    private double[] targets;

    public MLData(double[] inputs, double[] targets) {
        this.inputs = inputs;
        this.targets = targets;
    }

    public double[] getInputs() {
        return inputs;
    }

    public void setInputs(double[] inputs) {
        this.inputs = inputs;
    }

    public double[] getTargets() {
        return targets;
    }

    public void setTargets(double[] targets) {
        this.targets = targets;
    }

    @Override
    public String toString() {
        return "Input: " + Arrays.toString(inputs) + "\nTarget: " + Arrays.toString(targets);
    }
}