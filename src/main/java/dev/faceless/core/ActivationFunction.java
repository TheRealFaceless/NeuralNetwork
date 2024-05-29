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
    ELU {
        private final double alpha = 1.0;

        @Override
        public double output(double x) {
            return x >= 0 ? x : alpha * (Math.exp(x) - 1);
        }

        @Override
        public double outputDerivative(double x) {
            return x >= 0 ? 1 : output(x) + alpha;
        }
    },
    RELU {
        @Override
        public double output(double x) {
            return Math.max(0, x);
        }

        @Override
        public double outputDerivative(double x) {
            return x > 0 ? 1 : 0;
        }
    },
    SELU {
        private final double lambda = 1.0507;
        private final double alpha = 1.67326;

        @Override
        public double output(double x) {
            return x >= 0 ? lambda * x : lambda * alpha * (Math.exp(x) - 1);
        }

        @Override
        public double outputDerivative(double x) {
            return x >= 0 ? lambda : lambda * alpha * Math.exp(x);
        }
    },
    GELU {
        @Override
        public double output(double x) {
            return 0.5 * x * (1 + Math.tanh(Math.sqrt(2 / Math.PI) * (x + 0.044715 * Math.pow(x, 3))));
        }

        @Override
        public double outputDerivative(double x) {
            double cdf = 0.5 * (1 + Math.tanh(Math.sqrt(2 / Math.PI) * (x + 0.044715 * Math.pow(x, 3))));
            double pdf = Math.exp(-0.5 * Math.pow(x + 0.044715 * Math.pow(x, 3), 2)) / Math.sqrt(2 * Math.PI);
            return cdf + x * pdf;
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
    },
    LINEAR {
        @Override
        public double output(double x) {
            return x;
        }

        @Override
        public double outputDerivative(double x) {
            return 1;
        }
    };
    public abstract double output(double z);
    public abstract double outputDerivative(double z);
}

