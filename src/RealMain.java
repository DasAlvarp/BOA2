import java.util.PriorityQueue;

/**
 * Created by Alvarp on 10/11/2015.
 */
public class RealMain
{
    Utiler ute = new Utiler();
    int serviceVariance;
    int serviceMean;

    //basically does everything.
    public RealMain(int cashnum, int arrivalMean, int arrivalVariance, int serviceMean, int serviceVariance, int timeLimit)
    {
        this.serviceMean = serviceMean;
        this.serviceVariance = serviceVariance;

        Cashier[] cashiers = new Cashier[cashnum];//number of cashiers
        PriorityQueue<EventItem> EQ = new PriorityQueue<EventItem>();

        int customers = 0;

        int[] lastTimes = new int[cashnum];

        double[] percentIdleTime = new double[cashnum];
        int[] totalIdleTime = new int[cashnum];
        for(int x = 0; x < cashnum; x++)
        {
            percentIdleTime[x] = 0;
            totalIdleTime[x] = 0;
            lastTimes[x] = 0;
            cashiers[x] = new Cashier();
        }

        EQ.add(makeNewArrival(0, arrivalMean, arrivalVariance));
        EQ.add(new EventItem(500, 0, -2));


        for(int clock = 0; clock < timeLimit; clock = clock)
        {

            EventItem temp = EQ.remove();


            for(int x = 0; x < cashnum; x++)
            {
                if(cashiers[x].getLength() == 0)
                {
                    totalIdleTime[x] += clock - lastTimes[x];
                }
                lastTimes[x] = clock;
            }




            if(temp.type_of_event == -2)
            {
                System.out.println("Stats on Clock " + temp.time_of_day);
                System.out.println("there have been " + customers + " customers so far.");
                System.out.println("The % idle time for each cashier has been: ");
                for(int x = 0; x < cashnum; x++)
                {
                    System.out.println("Cashier " + (x + 1) + ": " + totalIdleTime[x]);
                }
                System.out.println("\n\n\n");
                EQ.add(new EventItem(temp.time_of_day + 500, 0, -2));
            }
            else if(temp.type_of_event == -1)
            {

                int t = ute.shortest(cashiers);

                cashiers[t].addItem(temp); //makes new cashiers. Adds a customer to the shortest cashier's line

                if(cashiers[t].getLength() == 1)
                {
                    EQ.add(new EventItem(clock + temp.service_time, temp.service_time, t));//adds departure node that takes 0 time, at end of service of last one, type t, or cashier number.
                }
                EQ.add(makeNewArrival(clock, arrivalMean, arrivalVariance));
            }
            else
            {
                customers++;

                EventItem fred = cashiers[temp.type_of_event].pop();

                if(cashiers[temp.type_of_event].getLength() != 0)
                {
                    EQ.add(new EventItem(clock + temp.service_time, temp.service_time, temp.type_of_event));
                }
            }
            clock = temp.time_of_day;
        }
    }



    //creates new arrival.
    private EventItem makeNewArrival(int clock, int uniforA, int uniformB)
    {
        return new EventItem(clock + ute.uniform(uniforA, uniformB), ute.uniform(serviceMean, serviceVariance), -1);
    }


}
