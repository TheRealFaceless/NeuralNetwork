package dev.faceless.core;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class MLDataSet {

    private double[][] inputs;
    private double[][] targets;
    private List<MLData> data;

    public MLDataSet() {
        data = new ArrayList<>();
    }

    public MLDataSet(double[][] inputs, double[][] targets) {
        this.data = new ArrayList<>();
        this.inputs = inputs;
        this.targets = targets;
        for (int i = 0; i < this.inputs.length; i++) {
            this.data.add(new MLData(inputs[i], targets[i]));
        }
    }

    public void addMLData(MLData mlData) {
        this.data.add(mlData);
    }

    public double[][] getInputs() {
        return inputs;
    }

    public void setInputs(double[][] inputs) {
        this.inputs = inputs;
    }

    public double[][] getTargets() {
        return targets;
    }

    public void setTargets(double[][] targets) {
        this.targets = targets;
    }

    public List<MLData> getData() {
        return data;
    }

    public void setData(List<MLData> data) {
        this.data = data;
    }
}