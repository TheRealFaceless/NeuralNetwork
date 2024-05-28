package dev.faceless;

import dev.faceless.core.ActivationFunction;
import dev.faceless.core.NeuralNetwork;
import dev.faceless.core.trainingdata.XORDataSet;

@SuppressWarnings("unused")
public class Main {

    public static void main(String[] args) {

        NeuralNetwork neuralNetwork = new NeuralNetwork(2, 10, 1);
        neuralNetwork.init();
        neuralNetwork.setLearningRate(0.01);
        neuralNetwork.setMomentum(0.5);
        neuralNetwork.setActivationFunction(ActivationFunction.TANH);

        XORDataSet dataSet = new XORDataSet();
        neuralNetwork.train(dataSet, 100000);

        neuralNetwork.predict(true, 1, 1);
        neuralNetwork.predict(true, 1, 0);
        neuralNetwork.predict(true, 0, 1);
        neuralNetwork.predict(true, 0, 0);
    }
}
