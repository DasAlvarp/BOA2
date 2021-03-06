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

        int[] specProc = new int[cashnum];

        int serviceTime = 0;
        int interArrive = 0;
        int arrival = 0;
        int prevArrive = 0;
        int maxWait = 0;
        int[] totalIdleTime = new int[cashnum];
        int totalwait = 0;

        for(int x = 0; x < cashnum; x++)
        {
            totalIdleTime[x] = 0;
            specProc[x] = 0;
            cashiers[x] = new Cashier();
        }

        EQ.add(makeNewArrival(0, arrivalMean, arrivalVariance));
        EQ.add(new EventItem(500, 0, -2));

        for(int clock = 0; clock <= timeLimit; clock = clock)//was originally for loop. Bad form, but eh.
        {
            EventItem temp = EQ.remove();

            for(int x = 0; x < cashnum; x++)
            {
                if (cashiers[x].getLength() == 0)
                {
                    totalIdleTime[x] += temp.time_of_day - clock;
                }
            }

            /*
            output needeed
            For each sim:
            Number of customers in each line
            clock time
            */
            if(temp.type_of_event == -2)
            {
                System.out.println("Stats on Clock " + temp.time_of_day);
                for(int x = 0; x < cashnum; x++)
                {
                    System.out.println("Cashier " + (x + 1) + " line: " + cashiers[x].getLength());
                }
                System.out.println("Event Queue size: " + EQ.size());
                System.out.println("\n\n");
                EQ.add(new EventItem(temp.time_of_day + 500, 0, -2));
            }
            else if(temp.type_of_event == -1)//interArrival time
            {
                int t = ute.shortest(cashiers);

                cashiers[t].addItem(temp); //makes new cashiers. Adds a customer to the shortest cashier's line

                if(cashiers[t].getLength() == 1)
                {
                    EQ.add(new EventItem(temp.time_of_day + temp.service_time, temp.service_time, t));//adds departure node that takes 0 time, at end of service of last one, type t, or cashier number.
                }

                interArrive += temp.time_of_day - prevArrive;
                prevArrive = temp.time_of_day;
                arrival++;
                EQ.add(makeNewArrival(temp.time_of_day, arrivalMean, arrivalVariance));
            }
            else
            {
                customers++;

                specProc[temp.type_of_event]++;

                EventItem fred = cashiers[temp.type_of_event].pop();
                serviceTime += fred.service_time;

                int wait = temp.time_of_day - fred.time_of_day - fred.service_time;

                totalwait += wait;
                if(wait > maxWait)
                {
                    maxWait = wait;
                }

                if(cashiers[temp.type_of_event].getLength() != 0)
                {
                    EQ.add(new EventItem(temp.time_of_day + temp.service_time, temp.service_time, temp.type_of_event));
                }
            }
            clock = temp.time_of_day;
        }

        /*
            @end
            Customers processed
            Average inter-arrival time
            average service time
            % cashier idle time
            max wait time
            max queue length.
         */
        System.out.println("Final Stats:");
        System.out.println("Customers Processed: " + customers);
        System.out.println("The average inter-arrival time was " + (double)interArrive / arrival);
        System.out.println("The average service time was " + ((double)serviceTime / (double)customers));
        System.out.println("The average wait time per customer was " + ((double)totalwait / (double)customers) + "\n");

        int maxLength = 0;
        for(int x = 0; x < cashnum; x++)
        {
            System.out.println("Cashier " + (x + 1) + " was idle " + ((double)totalIdleTime[x] * 100 / timeLimit) + "% of the time, or " + totalIdleTime[x] + " time unit(s)\n");
            if(cashiers[x].maxLength > maxLength)//finds longest length
            {
                maxLength = cashiers[x].maxLength;
            }
        }
        System.out.println("The max wait time was " + maxWait);
        System.out.println("The longest length of a cashier line was " + maxLength + ".");
        System.out.println("There were " + (arrival - customers) + " people left in the simulation.");
    }

    //creates new arrival.
    private EventItem makeNewArrival(int clock, int aMean, int aVar)
    {
        return new EventItem(clock + ute.uniform(aMean, aVar), ute.uniform(serviceMean, serviceVariance), -1);
    }
}
