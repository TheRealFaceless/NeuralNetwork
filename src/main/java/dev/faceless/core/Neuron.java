package dev.faceless.core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
public class Neuron {

    private UUID neuronId;
    private List<Connection> incomingConnections;
    private List<Connection> outgoingConnections;
    private double bias;
    private double gradient;
    private double output;
    private double outputBeforeActivation;
    private ActivationFunction activationFunction;

    public Neuron() {
        this.neuronId = UUID.randomUUID();
        this.incomingConnections = new ArrayList<>();
        this.outgoingConnections = new ArrayList<>();
        this.bias = 1.0;
    }

    public Neuron(List<Neuron> neurons, ActivationFunction activationFunction) {
        this();
        this.activationFunction = activationFunction;
        for (Neuron neuron : neurons) {
            Connection connection = new Connection(neuron, this);
            neuron.getOutgoingConnections().add(connection);
            this.incomingConnections.add(connection);
        }
    }

    public void calculateOutput() {
        this.outputBeforeActivation = 0.0;
        for (Connection connection : incomingConnections) {
            this.outputBeforeActivation += connection.getSynapticWeight() * connection.getFrom().getOutput();
        }
        this.output = activationFunction.output(this.outputBeforeActivation + bias);
    }

    public double error(double target) {
        return target - output;
    }

    public void calculateGradient(double target) {
        this.gradient = error(target) * activationFunction.outputDerivative(output);
    }

    public void calculateGradient() {
        this.gradient = outgoingConnections.stream().mapToDouble(connection -> connection.getTo().getGradient() * connection.getSynapticWeight()).sum()
                * activationFunction.outputDerivative(output);
    }

    public void updateConnections(double lr, double mu) {
        for (Connection connection : incomingConnections) {
            double prevDelta = connection.getSynapticWeightDelta();
            connection.setSynapticWeightDelta(lr * gradient * connection.getFrom().getOutput());
            connection.updateSynapticWeight(connection.getSynapticWeightDelta() + mu * prevDelta);
        }
    }

    public UUID getNeuronId() {
        return neuronId;
    }

    public void setNeuronId(UUID neuronId) {
        this.neuronId = neuronId;
    }

    public List<Connection> getIncomingConnections() {
        return incomingConnections;
    }

    public void setIncomingConnections(List<Connection> incomingConnections) {
        this.incomingConnections = incomingConnections;
    }

    public List<Connection> getOutgoingConnections() {
        return outgoingConnections;
    }

    public void setOutgoingConnections(List<Connection> outgoingConnections) {
        this.outgoingConnections = outgoingConnections;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public double getGradient() {
        return gradient;
    }

    public void setGradient(double gradient) {
        this.gradient = gradient;
    }

    public double getOutput() {
        return output;
    }

    public void setOutput(double output) {
        this.output = output;
    }

    public double getOutputBeforeActivation() {
        return outputBeforeActivation;
    }

    public void setOutputBeforeActivation(double outputBeforeActivation) {
        this.outputBeforeActivation = outputBeforeActivation;
    }

    public ActivationFunction getActivationFunction() {
        return activationFunction;
    }

    public void setActivationFunction(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }
}

