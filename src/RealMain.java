import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Alvarp on 10/11/2015.
 */
public class RealMain
{
    public static final int TIME = 2000;
    public static final int CASHNUM = 2;

    public RealMain()
    {


        Cashier[] cashiers = new Cashier[CASHNUM];//number of cashiers
        Queue EQ = new LinkedList<EventItem>();
        Utiler ute = new Utiler();

        int customers = 0;

        int totalinterArrive = 0;
        double averageInterArrive = 0;

        int totalServiceTime = 0;
        double averageServiceTime = 0;

        int totalCustomerWait = 0;
        double averageWaitPerCustomer = 0;
        int maxWaitTime = 0;

        double[] percentIdleTime = new double[CASHNUM];
        int[] totalIdleTime = new int[CASHNUM];
        for(int x = 0; x < CASHNUM; x++)
        {
            percentIdleTime[x] = 0;
            totalIdleTime[x] = 0;
        }

        int maxQueueLength = 0;
        int totalRemainingPeople = 0;

        EQ.add(new EventItem(0, ute.uniform(8, 3), 1 ));
        for(int clock = 0; clock < TIME; clock++)
        {
            EventItem temp = (EventItem)EQ.poll();
            for(int x = 0; x < cashiers.length; x++)
            {
                if(cashiers[x].maxLength == 0)
                {
                    totalIdleTime[x] += temp.time_of_day - clock;
                }
            }
            if(temp.type_of_event == -1)
            {
                int t = Shortest(cashiers);
                cashiers[t].addItem(temp); //makes new cashiers. Adds a customer to the shoertest cashier's line
                EQ.add(new EventItem(clock + temp.service_time, temp.service_time, t));//adds departure node that takes 0 time, at end of service of last one, type t, or cashier number.
                if(EQ.size() / 2 > maxQueueLength)
                {
                    maxQueueLength = EQ.size();
                }
            }
            else
            {
                customers++;
                int customerWait = clock - (temp.time_of_day + temp.service_time);
                if(customerWait > maxWaitTime)
                {
                    maxWaitTime = customerWait;
                }
                totalCustomerWait += customerWait;
                if(EQ.size() != 0)
                {
                    EQ.add(new EventItem(clock + (EventItem)EQ.peek().service_time, ));
                }
            }
        }
    }

    private int Shortest(Cashier[] cashiers)
    {
        int sh = 0;
        int shV = 0;
        for(int x = 0; x < cashiers.length; x++)
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
