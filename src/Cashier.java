import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Alvarp on 10/10/2015.
 */
public class Cashier
{
    private Queue customers;
    private int length; //instead of getting queue lengths, I'm doing a lazy way and just using a variable. bad form, I know. (lazy code is lazy)
    public int maxLength;
    public int idle;
    public int working;

    public Cashier()
    {
        customers = new LinkedList<EventItem>();
        length = 0;
    }

    public void addItem(EventItem e)
    {
        customers.add(e);
        length += 1;
        if(length > maxLength)
        {
            maxLength = length;
        }
    }
    public void removeItem(EventItem e)
    {
        customers.remove();
        length -= 1;
    }

    public EventItem pop()
    {
        length--;
        return (EventItem)customers.poll();
    }

    public int getLength() {
        return length;
    }

}
