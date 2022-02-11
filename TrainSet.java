
/**
 * Write a description of class TrainSet here.
 *
 * @author (Jacob Dent)
 * @version (1/12/2022)
 */

import java.util.ArrayList;

public class TrainSet
{
    private ArrayList<double[][]> answers = new ArrayList<double[][]>();
    final private int INPUT_SIZE;
    final private int OUTPUT_SIZE;
    private int biggestVar;
    
    public TrainSet(int input, int output)
    {
        this.INPUT_SIZE = input;
        this.OUTPUT_SIZE = output;
        
        if(INPUT_SIZE >= OUTPUT_SIZE)
        {
            biggestVar = INPUT_SIZE;
        }
        else
        {
            biggestVar = OUTPUT_SIZE;
        }
    }
    
    public void addSet(double[] input, double[] output)
    {
        double[][] tempArray = new double[2][biggestVar];
        tempArray[0] = input;
        tempArray[1] = output;
        this.answers.add(tempArray);
    }
    
    public int getNumSets()
    {
        return this.answers.size();
    }
    
    public double[][] getSet(int index)
    {
        return this.answers.get(index);
    }
    
}































































