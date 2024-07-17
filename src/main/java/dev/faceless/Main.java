package dev.faceless;

import dev.faceless.core.ActivationFunction;
import dev.faceless.core.MLDataSet;
import dev.faceless.core.NeuralNetwork;
import dev.faceless.core.trainingdata.ImageDataSet;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Main {

    public static void main(String[] args) {
        NeuralNetwork neuralNetwork = new NeuralNetwork(784, 100, 8);
        neuralNetwork.init();
        neuralNetwork.setLearningRate(0.01);
        neuralNetwork.setMomentum(0.5);
        neuralNetwork.setActivationFunction(ActivationFunction.SWISH);

        MLDataSet dataSet = new ImageDataSet("dataset");
        //neuralNetwork.train(dataSet, 100000);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter image path (or 'exit' to quit):");
            String imagePath = scanner.nextLine();
            if (imagePath.equalsIgnoreCase("exit")) {
                break;
            }
            BufferedImage image = ImageDataSet.loadImage(imagePath);
            if (image != null) {
                double[] inputs = ImageDataSet.getBinaryValue(image);
                double[] output = neuralNetwork.predict(inputs);
                System.out.println("Output: " + Arrays.toString(output));
            } else {
                System.out.println("Invalid image path.");
            }
        }
        scanner.close();
    }
}
