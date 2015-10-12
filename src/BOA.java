import java.util.Scanner;

/**
 * Created by Alvarp on 10/11/2015.
 */
public class BOA
{
    public static void main(String[] args)
    {
        Scanner scanman = new Scanner(System.in);
        System.out.println("Welcome to the Bank of Alvaro. How many cashiers/tellers are there?");
        int cash = scanman.nextInt();
        System.out.println("What is the mean arrival time?");
        int arrivalM = scanman.nextInt();
        System.out.println("What is the arrival time variance?");
        int arrivalV = scanman.nextInt();
        System.out.println("What is the mean service time?");
        int serviceM = scanman.nextInt();
        System.out.println("What is the service time variance?");
        int serviceV = scanman.nextInt();
        System.out.println("What is the time limit of the simulation?");
        int simT = scanman.nextInt();
       RealMain rm = new RealMain(cash, arrivalM, arrivalV, serviceM, serviceV, simT);
    }
}
