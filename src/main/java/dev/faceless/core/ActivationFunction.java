package dev.faceless.core;

@SuppressWarnings("unused")
public enum ActivationFunction {
    SIGMOID {
        @Override
        public double output(double x) {
            return 1 / (1 + Math.exp(-x));
        }

        @Override
        public double outputDerivative(double x) {
            return x * (1 - x);
        }
    },
    LEAKY_RELU {
        @Override
        public double output(double x) {
            return x >= 0 ? x : x * 0.01;
        }

        @Override
        public double outputDerivative(double x) {
            return x >= 0 ? 1 : 0.01;
        }
    },
    SWISH {
        @Override
        public double output(double x) {
            return x * (1 / (1 + Math.exp(-x)));
        }

        @Override
        public double outputDerivative(double x) {
            return ((1 + Math.exp(-x)) + x * Math.exp(-x)) / Math.pow(1 + Math.exp(-x), 2);
        }
    },
    TANH {
        @Override
        public double output(double x) {
            return Math.tanh(x);
        }

        @Override
        public double outputDerivative(double x) {
            return 1 - Math.pow(Math.tanh(x), 2);
        }
    };

        public abstract double output(double z);
        public abstract double outputDerivative(double z);
    }

