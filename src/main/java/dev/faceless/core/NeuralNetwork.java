package dev.faceless.core;

import dev.faceless.core.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class NeuralNetwork {

    private final int inputSize;
    private final int hiddenSize;
    private final int outputSize;

    private final List<Neuron> inputLayer;
    private final List<Neuron> hiddenLayer;
    private final List<Neuron> outputLayer;

    private double learningRate = 0.01;
    private double momentum = 0.5;
    private ActivationFunction activationFunction = ActivationFunction.SIGMOID;
    private boolean initialized = false;

    private final Logger logger = Logger.getLogger();

    public NeuralNetwork(int inputSize, int hiddenSize, int outputSize) {
        this.inputSize = inputSize;
        this.hiddenSize = hiddenSize;
        this.outputSize = outputSize;
        this.inputLayer = new ArrayList<>();
        this.hiddenLayer = new ArrayList<>();
        this.outputLayer = new ArrayList<>();
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    public void setMomentum(double momentum) {
        this.momentum = momentum;
    }

    public void setActivationFunction(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

    public void init() {
        for (int i = 0; i < inputSize; i++) {
            this.inputLayer.add(new Neuron());
        }
        for (int i = 0; i < hiddenSize; i++) {
            this.hiddenLayer.add(new Neuron(this.inputLayer, activationFunction));
        }
        for (int i = 0; i < outputSize; i++) {
            this.outputLayer.add(new Neuron(this.hiddenLayer, activationFunction));
        }
        this.initialized = true;
        logger.logInfo("Network Initialized.");
    }

    public void train(MLDataSet set, int epoch) {
        if (!initialized){
            this.init();
        }
        logger.logInfo("Training Started");
        for (int i = 0; i < epoch; i++) {
            Collections.shuffle(set.getData());

            for (MLData datum : set.getData()) {
                forward(datum.getInputs());
                backward(datum.getTargets());
            }
            if (i % 1000 == 0) logger.logInfo("Epoch " + i + " completed.");
        }
        logger.logInfo("Training Finished.");
    }

    private void backward(double[] targets) {
        if(outputSize != targets.length) {
            logger.logError("Output mismatch, output size is " + outputSize + " but " + targets.length + " is provided.");
            throw new RuntimeException();
        }

        logger.logInfo("Backward pass started.");

        int i = 0;
        for (Neuron neuron : outputLayer) {
            neuron.calculateGradient(targets[i++]);
        }
        logger.logInfo("Gradients calculated for output layer.");

        for (Neuron neuron : hiddenLayer) {
            neuron.calculateGradient();
        }
        logger.logInfo("Gradients calculated for hidden layer.");

        for (Neuron neuron : hiddenLayer) {
            neuron.updateConnections(learningRate, momentum);
        }
        logger.logInfo("Connections updated for hidden layer.");

        for (Neuron neuron : outputLayer) {
            neuron.updateConnections(learningRate, momentum);
        }
        logger.logInfo("Connections updated for output layer.");

        logger.logInfo("Backward pass finished.");
    }

    private void forward(double[] inputs) {
        if(inputSize != inputs.length) {
            logger.logError("Input mismatch, input size is " + inputSize + " but " + inputs.length + " is provided.");
            throw new RuntimeException();
        }

        logger.logInfo("Forward pass started.");

        int i = 0;
        for (Neuron neuron : inputLayer) {
            neuron.setOutput(inputs[i++]);
        }
        logger.logInfo("Outputs set for input layer.");

        for (Neuron neuron : hiddenLayer) {
            neuron.calculateOutput();
        }
        logger.logInfo("Outputs calculated for hidden layer.");

        for (Neuron neuron : outputLayer) {
            neuron.calculateOutput();
        }
        logger.logInfo("Outputs calculated for output layer.");

        logger.logInfo("Forward pass finished.");
    }

    public double[] predict(double... inputs) {
        forward(inputs);
        double[] output = new double[outputLayer.size()];
        for (int i = 0; i < output.length; i++) {
            output[i] = outputLayer.get(i).getOutput();
        }
        logger.logInfo("Input : " + Arrays.toString(inputs) + " Predicted : " + Arrays.toString(output));
        return output;
    }

    public double[] predict(boolean round, double... inputs) {
        forward(inputs);
        double[] output = new double[outputLayer.size()];
        for (int i = 0; i < output.length; i++) {
            if(round) output[i] = Math.round(outputLayer.get(i).getOutput());
            else output[i] = outputLayer.get(i).getOutput();
        }
        logger.logInfo("Input : " + Arrays.toString(inputs) + " Predicted : " + Arrays.toString(output));
        return output;
    }
}