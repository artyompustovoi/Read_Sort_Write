package Artem_Pustovoy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EventSorting {

    public static List<Event> sortByDate(List<Event> students) {
        List<Event> mutableList = new ArrayList<>(students);
        mutableList.sort(new Comparator<Event>() {
            @Override
            public int compare(Event e1, Event e2) {
                return e1.date().compareTo(e2.date());
            }
        });
        return mutableList;
    }
}
