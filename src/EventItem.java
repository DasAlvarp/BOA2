/**
 * Created by Alvarp on 10/11/2015.
 */
public class EventItem implements Comparable
{
    public int time_of_day;
    public int service_time;
    public int type_of_event;//if -1 - arrival, 0-9 is a departure from a teller.

    public EventItem(int tod, int st, int toe)
    {
        time_of_day = tod;
        service_time = st;
        type_of_event = toe;
    }

    public String toString()
    {
        return new String(time_of_day + "\n" + service_time + "\n" + type_of_event);
    }


    @Override
    public int compareTo(Object o)
    {
        EventItem temp = (EventItem)o;
        if(time_of_day > temp.time_of_day)
        {
            return 1;
        }
        else if(time_of_day == temp.time_of_day)
        {
            return  0;
        }
        return -1;
    }
}
