import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Alvarp on 10/11/2015.
 */
public class RealMain
{
    public static final int TIME = 2000;


    public RealMain()
    {

        Queue EQ = new LinkedList<EventItem>();
        Utiler ute = new Utiler();

        int customers = 0;
        double averageInterArrive = 0;
        double averageServiceTime = 0;
        double averageWaitPerCustomer = 0;
        double[] percentIdleTime;
        int maxWaitTime = 0;
        int maxQueueLength = 0;
        int totalRemainingPeople = 0;

        EQ.add(new EventItem(ute.uniform(3, 2), ute.uniform(8, 3), 1 ));
        for(int clock = 0; clock < TIME; clock++)
        {

        }
    }
}
