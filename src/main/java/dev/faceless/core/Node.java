package dev.faceless.core;

import java.util.Random;

public class Node {
    private double[] weights;
    private double bias;
    private final Functions function;

    public Node(Functions function, int numInputs) {
        this.function = function;
        weights = new double[numInputs];
        for (int i = 0; i < numInputs; i++) {
            weights[i] = new Random().nextGaussian() * 2 - 1;
        }
        bias = new Random().nextGaussian() * 2 - 1;
    }

    public double calculateOutput(double[] inputs) {
        if (inputs.length != weights.length) {
            throw new IllegalArgumentException("Number of inputs must match number of weights");
        }
        double sum = bias;
        for (int i = 0; i < inputs.length; i++) {
            sum += inputs[i] * weights[i];
        }
        return function.apply(sum);
    }

    public double[] getWeights() {
        return weights;
    }

    public void setWeights(double[] weights) {
        if (weights.length != this.weights.length) {
            throw new IllegalArgumentException("Number of weights must match current weights size");
        }
        this.weights = weights;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }
}
