
/**
 * Write a description of class Network here.
 *
 * @author (Jacob Dent)
 * @version (1/5/2022)
 */
public class Network
{
    private double[][] output;
    private double[][][] weights;
    private double[][] bias;
    
    private double[][] error_signal;
    private double[][] output_derivative;
    
    public final int[] NETWORK_LAYER_SIZES;
    public final int INPUT_SIZE;
    public final int OUTPUT_SIZE;
    public final int NETWORK_SIZE;
    
    public Network(int... NETWORK_LAYER_SIZES)
    {
        this.NETWORK_LAYER_SIZES = NETWORK_LAYER_SIZES;
        this.INPUT_SIZE = NETWORK_LAYER_SIZES[0];
        this.NETWORK_SIZE = NETWORK_LAYER_SIZES.length;
        this.OUTPUT_SIZE = NETWORK_LAYER_SIZES[NETWORK_SIZE - 1];
        
        this.output = new double[NETWORK_SIZE][];
        this.weights = new double[NETWORK_SIZE][][];
        this.bias = new double[NETWORK_SIZE][];
        
        this.error_signal = new double[NETWORK_SIZE][];
        this.output_derivative = new double[NETWORK_SIZE][];
        
        for(int i = 0; i < NETWORK_SIZE; i++)
        {
            this.output[i] = new double[NETWORK_LAYER_SIZES[i]];
            this.error_signal[i] = new double[NETWORK_LAYER_SIZES[i]];
            this.output_derivative[i] = new double[NETWORK_LAYER_SIZES[i]];
            //this.bias[i] = new double[NETWORK_LAYER_SIZES[i]];
            this.bias[i] = NetworkTools.createRandomArray(NETWORK_LAYER_SIZES[i],0.3,0.7);
            
            if(i > 0)
            {
                //this.weights[i] = new double[NETWORK_LAYER_SIZES[i]][NETWORK_LAYER_SIZES[i-1]];
                this.weights[i] = NetworkTools.createRandomArray(NETWORK_LAYER_SIZES[i],NETWORK_LAYER_SIZES[i-1],-0.8,0.7);
                
            }
        }
    }
    
    public double[] calculate(double... input)
    {
        if(input.length != this.INPUT_SIZE)
        {
            return null;
        }
        
        this.output[0] = input;
        for(int layer = 1; layer < NETWORK_SIZE; layer++)
        {
            for(int neuron = 0; neuron < NETWORK_LAYER_SIZES[layer]; neuron++)
            {
                double sum = bias[layer][neuron];
                for(int prevNeuron = 0; prevNeuron < NETWORK_LAYER_SIZES[layer-1]; prevNeuron++)
                {
                    sum += output[layer-1][prevNeuron] * weights[layer][neuron][prevNeuron];
                }
                output[layer][neuron] = sigmoid(sum);
                output_derivative[layer][neuron] = (output[layer][neuron] * (1 - output[layer][neuron]));
            }
        }
        return output[NETWORK_SIZE - 1];
    }
    
    public void train(double[] input, double[] target, double eta)
    {
        if(input.length != INPUT_SIZE || target.length != OUTPUT_SIZE)
        {
            System.out.println("incorrect input size, or output size");
            return;
        }
        
        calculate(input);
        backpropError(target);
        updateWeights(eta);
    }
    
    public void trainABunch(TrainSet set)
    {
        double[][] tempArray;
        double[] tempInput;
        double[] tempOutput;
        int numOfSets = set.getNumSets();
        
        for(int i = 0; i < numOfSets; i++)
        {
            tempArray = set.getSet(i);
            tempInput = tempArray[0];
            tempOutput = tempArray[1];
            
            this.train(tempInput, tempOutput, 0.3);
            
        }
    }
    public void backpropError(double[] target)
    {
        for(int neuron = 0; neuron < NETWORK_LAYER_SIZES[NETWORK_SIZE - 1]; neuron++)
        {
            error_signal[NETWORK_SIZE - 1][neuron] = ((output[NETWORK_SIZE - 1][neuron] - target[neuron]) * 
            output_derivative[NETWORK_SIZE - 1][neuron]);
        }
        for(int layer = NETWORK_SIZE - 2; layer > 0; layer--)
        {
            for(int neuron = 0; neuron < NETWORK_LAYER_SIZES[layer]; neuron++)
            {
                double sum = 0;
                for(int nextNeuron = 0; nextNeuron < NETWORK_LAYER_SIZES[layer + 1]; nextNeuron++)
                {
                    sum += weights[layer + 1][nextNeuron][neuron] * error_signal[layer + 1][nextNeuron];
                }
                this.error_signal[layer][neuron] = sum * output_derivative[layer][neuron];
                
            }
        }
    }
    
    public void updateWeights(double eta)
    {
        for(int layer = 1; layer < NETWORK_SIZE; layer++)
        {
            for(int neuron = 0; neuron < NETWORK_LAYER_SIZES[layer]; neuron++)
            {
                double delta = - eta * error_signal[layer][neuron];
                bias[layer][neuron] += delta;
                for(int prevNeuron = 0; prevNeuron < NETWORK_LAYER_SIZES[layer - 1]; prevNeuron++)
                {
                    // weights of the current layer, of the current neuron, connected to the previous neuron
                    weights[layer][neuron][prevNeuron] += delta * output[layer -1][prevNeuron];
                }
            }
        }
    }
    private double sigmoid(double x)
    {
        return 1d / (1 + Math.exp(-x));
    }
}



























































































