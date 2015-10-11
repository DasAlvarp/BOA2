import java.util.Random;

/**
 * Created by Alvarp on 10/10/2015.
 */
public class Utiler
{
    Random randy = new Random();

    public int uniform(int ave, int variant)
    {
        int small = ave - variant;
        int range = 2 * variant + 1;
        return small + randy.nextInt(range);
    }

    public int shortest(Cashier[] cashiers)
    {
        int sh = 0;
        int shV = cashiers[0].getLength();
        for(int x = 1; x < cashiers.length; x++)
        {
            if(cashiers[x].getLength() < shV)
            {
                shV = cashiers[x].getLength();
                sh = x;
            }
        }
        return sh;
    }
}
