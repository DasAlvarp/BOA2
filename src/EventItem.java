/**
 * Created by Alvarp on 10/11/2015.
 */
public class EventItem
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


}
