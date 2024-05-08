package dev.faceless.core;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {
    private final ArrayList<Layer> layers;

    public NeuralNetwork(Functions function, int... numNodesPerLayer) {
        if (numNodesPerLayer.length < 2) {
            throw new IllegalArgumentException("At least two layers are required");
        }
        layers = new ArrayList<>();
        for (int i = 0; i < numNodesPerLayer.length - 1; i++) {
            layers.add(new Layer(function, numNodesPerLayer[i + 1], numNodesPerLayer[i]));
        }
    }

    public double[] feedForward(double[] inputs) {
        double[] intermediateOutput = inputs;
        for (Layer layer : layers) {
            intermediateOutput = layer.feedForward(intermediateOutput);
        }
        return intermediateOutput;
    }

    public void backpropagate(double[] inputs, double[] targets, double learningRate) {
        double[][] layerOutputs = new double[layers.size()][];
        double[] intermediateOutput = inputs;
        for (int i = 0; i < layers.size(); i++) {
            intermediateOutput = layerOutputs[i] = layers.get(i).feedForward(intermediateOutput);
        }

        double[][] layerErrors = new double[layers.size()][];
        for (int i = layers.size() - 1; i >= 0; i--) {
            Layer layer = layers.get(i);
            double[] errors = new double[layer.getNodes().size()];
            if (i == layers.size() - 1) {
                List<Node> nodes = layer.getNodes();
                for (int j = 0; j < nodes.size(); j++) {
                    double output = layerOutputs[i][j];
                    double target = targets[j];
                    double error = output * (1 - output) * (target - output);
                    errors[j] = error;
                }
            } else {
                Layer nextLayer = layers.get(i + 1);
                List<Node> nodes = layer.getNodes();
                for (int j = 0; j < nodes.size(); j++) {
                    double output = layerOutputs[i][j];
                    double sumOfErrorsTimesWeights = 0;
                    double[] nextLayerErrors = layerErrors[i + 1];
                    for (int k = 0; k < nextLayer.getNodes().size(); k++) {
                        sumOfErrorsTimesWeights += nextLayer.getNodes().get(k).getWeights()[j] * nextLayerErrors[k];
                    }
                    double error = output * (1 - output) * sumOfErrorsTimesWeights;
                    errors[j] = error;
                }
            }
            layerErrors[i] = errors;
        }

        // Set
        for (int i = 0; i < layers.size(); i++) {
            Layer layer = layers.get(i);
            List<Node> nodes = layer.getNodes();
            double[] errors = layerErrors[i];
            double[] inputToUse = (i == 0) ? inputs : layerOutputs[i - 1];
            for (int j = 0; j < nodes.size(); j++) {
                Node node = nodes.get(j);
                double[] weights = node.getWeights();
                double bias = node.getBias();
                for (int k = 0; k < weights.length; k++) {
                    double newWeight = weights[k] + learningRate * errors[j] * inputToUse[k];
                    weights[k] = newWeight;
                }
                double newBias = bias + learningRate * errors[j];
                node.setBias(newBias);
            }
        }
    }
}
