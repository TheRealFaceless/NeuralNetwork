package dev.faceless;

import dev.faceless.core.Functions;
import dev.faceless.core.NeuralNetwork;

public class Main {
    public static void main(String[] args) {
        NeuralNetwork neuralNetwork = new NeuralNetwork(Functions.SIGMOID, 2, 4, 1);

        double[][] trainingData = new double[][]{{0,0}, {0,1}, {1,0}, {1,1}};
        double[][] trainingTargets = new double[][]{{0}, {1}, {1}, {0}};

        double learningRate = 0.01;
        long epochs = 1000000;


        for(int epoch = 0; epoch < epochs; epoch++) {
            for(int i = 0; i < trainingData.length; i++) {
                double[] data = trainingData[i];
                double[] target = trainingTargets[i];
                neuralNetwork.backpropagate(data, target, learningRate);
            }
            if (epoch % 10000 == 0) {
                System.out.println("Epoch " + epoch + " completed.");
            }
        }

        for (double[] input : trainingData) {
            double[] output = neuralNetwork.feedForward(input);

            System.out.println(input[0] + "-XOR-" + input[1] + " = " + output[0]);
        }

    }

}
