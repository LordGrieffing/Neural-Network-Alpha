
/**
 * Write a description of class MainNeuro here.
 *
 * @author (Jacob Dent)
 * @version (1/5/2022)
 */
public class MainNeuro
{
    public static void main(String[] args)
    {
        Network net = new Network(4, 32, 16, 12, 8, 4);
        
        
        TrainSet set = new TrainSet(4, 4);
        
        double[] input = new double[]{1, 0, 1, 0};
        double[] target = new double[]{1, 0, 1, 0};
        set.addSet(input,target);
        
        double[] inputTwo = new double[]{1, 1, 1, 1};
        double[] targetTwo = new double[]{1, 1 ,1 , 1};
        set.addSet(inputTwo,targetTwo);
        
        double[] inputThree = new double[]{0, 0, 0, 0};
        double[] targetThree = new double[]{0, 0, 0, 0};
        set.addSet(inputThree,targetThree);
        
        double[] inputFour = new double[]{1, 0, 0, 0};
        double[] targetFour = new double[]{1, 0, 0, 0};
        set.addSet(inputFour,targetFour);
        
        double[] inputFive = new double[]{1, 1, 0, 0};
        double[] targetFive = new double[]{1, 1, 0, 0};
        set.addSet(inputFive,targetFive);
        
        double[] inputSix = new double[]{1, 0, 0, 1};
        double[] targetSix = new double[]{1, 0, 0, 1};
        set.addSet(inputSix,targetSix);
        
        double[] inputSeven = new double[]{0, 1, 0, 1};
        double[] targetSeven = new double[]{0, 1, 0, 1};
        set.addSet(inputSeven,targetSeven);
        
        double[] inputEight = new double[]{0,1,1,1};
        double[] targetEight = new double[]{0,1,1,1};
        set.addSet(inputEight,targetEight);
        
        double[] inputNine = new double[]{0,1,1,0};
        double[] targetNine = new double[]{0,1,1,0};
        set.addSet(inputNine,targetNine);
        
        double[] inputTen = new double[]{0,0,1,1};
        double[] targetTen = new double[]{0,0,1,1};
        set.addSet(inputTen,targetTen);
        
        for(int i = 0; i < 100000; i++)
        {
            net.trainABunch(set);
        }
        
        double[] test = new double[]{0,0,0,1};
        System.out.println("=========================================");
        System.out.print("The numbers you inputed were: ");
        for(int i = 0; i < test.length; i++)
        {
            System.out.print((int)test[i] + " ");
        }
        System.out.println("");
        System.out.print("The numbers I thought you inputed were: ");
        double[] testOutput = net.calculate(test);
        
        for(int i = 0; i < testOutput.length; i++)
        {
            if(testOutput[i] >= 0.5)
            {
                System.out.print("1 ");
            }
            else if(testOutput[i] < 0.5)
            {
                System.out.print("0 ");
            }
        }
        System.out.println("");
        System.out.println("=========================================");
        /**
        double[] turnIn = net.calculate(input);
        //double[] turnInTwo = net.calculate(inputTwo);
        //double[] test = net.calculate(testInput);
        
        System.out.println("=========================================");
        for(int i = 0; i < turnIn.length; i++)
        {
            System.out.print(turnIn[i]);
            System.out.print(" ");
        }
        System.out.println("");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        double[] turnInThree = net.calculate(inputThree);
        for(int i = 0; i < turnInThree.length; i++)
        {
            System.out.print(turnInThree[i]);
            System.out.print(" ");
        }
        System.out.println("");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        double[] turnInTwo = net.calculate(inputTwo);
        for(int i = 0; i < turnInTwo.length; i++)
        {
            System.out.print(turnInTwo[i]);
            System.out.print(" ");
        }
        System.out.println("");
        System.out.println("=========================================");
        double[] realTest = net.calculate(0, 0, 0, 1);
        for(int i = 0; i < realTest.length; i++)
        {
            System.out.print(realTest[i]);
            System.out.print(" ");
        }
        System.out.println("");
        System.out.println("=========================================");
        */
    }
}


































































































