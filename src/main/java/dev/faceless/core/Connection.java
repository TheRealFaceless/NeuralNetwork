package dev.faceless.core;

import dev.faceless.core.utils.RandomGenerator;

import java.util.UUID;

@SuppressWarnings("unused")
public class Connection {

    private UUID connectionId;
    private Neuron from;
    private Neuron to;
    private double synapticWeight;
    private double synapticWeightDelta;

    public Connection(Neuron from, Neuron to) {
        this.connectionId = UUID.randomUUID();
        this.from = from;
        this.to = to;
        this.synapticWeight = RandomGenerator.randomValue(-2, 2);
    }

    public void updateSynapticWeight(double synapticWeight) {
        this.synapticWeight += synapticWeight;
    }

    public UUID getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(UUID connectionId) {
        this.connectionId = connectionId;
    }

    public Neuron getFrom() {
        return from;
    }

    public void setFrom(Neuron from) {
        this.from = from;
    }

    public Neuron getTo() {
        return to;
    }

    public void setTo(Neuron to) {
        this.to = to;
    }

    public double getSynapticWeight() {
        return synapticWeight;
    }

    public void setSynapticWeight(double synapticWeight) {
        this.synapticWeight = synapticWeight;
    }

    public double getSynapticWeightDelta() {
        return synapticWeightDelta;
    }

    public void setSynapticWeightDelta(double synapticWeightDelta) {
        this.synapticWeightDelta = synapticWeightDelta;
    }
}