import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Alvarp on 10/11/2015.
 */
public class RealMain
{
    public static final int TIME = 2000;
    public static final int CASHNUM = 2;
    Utiler ute = new Utiler();

    public RealMain()
    {


        Cashier[] cashiers = new Cashier[CASHNUM];//number of cashiers
        Queue EQ = new LinkedList<EventItem>();

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

        EQ.add(makeNewArrival(0, 3, 2));
        EQ.add(new EventItem(500, 0, -2));

        for(int x = 0; x < CASHNUM; x++)
        {
            cashiers[x] = new Cashier();
        }


        for(int clock = 0; clock < TIME; clock = clock)
        {
            EventItem temp = (EventItem)EQ.poll();

            if(temp.type_of_event == -2)
            {
                System.out.println("Stats!");
                EQ.add(new EventItem(temp.time_of_day + 500, 0, -2));
            }
            else if(temp.type_of_event == -1)
            {
                int t = Shortest(cashiers);

                cashiers[t].addItem(temp); //makes new cashiers. Adds a customer to the shoertest cashier's line
                if(cashiers[t].getLength() == 1)
                {
                    EQ.add(new EventItem(clock + temp.service_time, temp.service_time, t));//adds departure node that takes 0 time, at end of service of last one, type t, or cashier number.
                }
                EQ.add(makeNewArrival(clock, 3, 2));
            }
            else
            {
                EventItem fred = cashiers[temp.type_of_event].pop();

                if(EQ.size() != 0)
                {
                    EQ.add(new EventItem(clock + ute.uniform(2,3), ute.uniform(2, 3), temp.type_of_event));
                }
            }
            clock = temp.time_of_day;
        }
    }



    private EventItem makeNewArrival(int clock, int uniforA, int uniformB)
    {
        return new EventItem(clock + ute.uniform(uniforA, uniformB), ute.uniform(8, 3), -1);
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
