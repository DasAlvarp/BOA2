import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Alvarp on 10/10/2015.
 */
public class Cashier
{
    private Queue customers;
    public int maxLength;


    public Cashier()
    {
        customers = new LinkedList<EventItem>();
    }

    public void addItem(EventItem e)
    {
        customers.add(e);
        if(getLength() > maxLength)
        {
            maxLength = getLength();
        }
    }
    public void removeItem(EventItem e)
    {
        customers.remove();
    }

    public EventItem pop()
    {
        return (EventItem)customers.poll();
    }

    public int getLength()
    {
        return customers.size();
    }

}
