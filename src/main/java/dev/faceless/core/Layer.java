package dev.faceless.core;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    private final List<Node> nodes;

    public Layer(Functions function, int numNodes, int numInputsPerNode) {
        nodes = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            nodes.add(new Node(function, numInputsPerNode));
        }
    }

    public double[] feedForward(double[] inputs) {
        double[] outputs = new double[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            outputs[i] = nodes.get(i).calculateOutput(inputs);
        }
        return outputs;
    }
    public List<Node> getNodes() {
        return nodes;
    }
}
