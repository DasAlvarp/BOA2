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
}
