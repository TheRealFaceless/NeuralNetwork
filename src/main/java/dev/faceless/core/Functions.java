package dev.faceless.core;

public enum Functions {
    SIGMOID {
        @Override
        public double apply(double x) {
            return (1/(1+Math.pow(Math.E, -x)));
        }
        @Override
        public double applyDerivative(double x) {
            double sigmoid = apply(x);
            return sigmoid * (1 - sigmoid);
        }
    },
    RELU {
        @Override
        public double apply(double x) {
            return Math.max(0, x);
        }
        @Override
        public double applyDerivative(double x) {
            return x > 0 ? 1 : 0;
        }
    },
    TANH {
        @Override
        public double apply(double x) {
            return Math.tanh(x);
        }
        @Override
        public double applyDerivative(double x) {
            double tanh = Math.tanh(x);
            return 1 - tanh * tanh;
        }
    };

    public abstract double apply(double x);

    public abstract double applyDerivative(double x);

    public static double squaredError(double output, double target) {
        return (float) (0.5*Math.pow(2,(target-output)));
    }

    public static double sumSquaredError(double[] outputs,double[] targets) {
        double sum = 0;
        for(int i = 0; i < outputs.length; i++) {
            sum += squaredError(outputs[i],targets[i]);
        }
        return sum;
    }

    public static double randomDouble(double min, double max) {
        if (min > max) {
            throw new IllegalArgumentException("Min must be less than or equal to Max");
        }
        double num = min + (Math.random() * (max - min));
        return Math.random() < 0.5? num : -num;
    }

}
