import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Alvarp on 10/10/2015.
 */
public class Cashier
{
    private Queue customers;
    public int maxLength;

    //constructor
    public Cashier()
    {
        customers = new LinkedList<EventItem>();
    }

    //adds ited E to customrers
    public void addItem(EventItem e)
    {
        customers.add(e);
        if(getLength() > maxLength)
        {
            maxLength = getLength();
        }
    }

    //removes top item from customers
    public void removeItem(EventItem e)
    {
        customers.remove();
    }

    //pops customers
    public EventItem pop()
    {
        return (EventItem)customers.poll();
    }

    //gets lenght of customrers
    public int getLength()
    {
        return customers.size();
    }
}
