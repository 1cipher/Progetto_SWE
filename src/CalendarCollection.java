import java.util.ArrayList;

public class CalendarCollection {
    ArrayList<Calendar> collection;

    public ArrayList<String> getIds(){
        ArrayList<String> ids=new ArrayList<>();
        for (Calendar calendar :
             collection) {
            ids.add(calendar.getId());
        }
        return ids;
    }

    public Calendar getCalendar(String id){
        for (Calendar calendar:collection
             ) {
            if (calendar.getId().equals(id))
                return calendar;
        }
        return null;
    }

    public CalendarCollection() {
        this.collection = new ArrayList<Calendar>();

    }

    public void removeCalendar(Calendar c){

        collection.remove(collection.indexOf(c));
    }

    public void addCalendarToCollection(Calendar c){

        collection.add(c);
    }
}
