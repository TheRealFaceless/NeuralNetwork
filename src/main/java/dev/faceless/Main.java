package dev.faceless;

import dev.faceless.core.ActivationFunction;
import dev.faceless.core.MLDataSet;
import dev.faceless.core.NeuralNetwork;
import dev.faceless.core.trainingdata.TripleXORDataSet;

@SuppressWarnings("unused")
public class Main {

    public static void main(String[] args) {

        NeuralNetwork neuralNetwork = new NeuralNetwork(3, 20, 2);
        neuralNetwork.init();
        neuralNetwork.setLearningRate(0.01);
        neuralNetwork.setMomentum(0.5);
        neuralNetwork.setActivationFunction(ActivationFunction.SWISH);

        MLDataSet dataSet = new TripleXORDataSet();
        neuralNetwork.train(dataSet, 100000);

        neuralNetwork.predict(1.0, 1.0, 1.0);
    }
}
