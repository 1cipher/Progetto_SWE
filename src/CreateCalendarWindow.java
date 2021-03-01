import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CreateCalendarWindow extends JFrame {

    JLabel calendarName;
    JTextField calendarText;
    JButton createCalendar;
    Container c;

    public CreateCalendarWindow(){

        setName("Create Calendar");
        setSize(300,200);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        c = getContentPane();
        c.setLayout(null);

        calendarName = new JLabel("Calendar: ",SwingConstants.CENTER);
        calendarName.setLocation(30,50);
        calendarName.setSize(100,20);

        calendarText = new JTextField();
        calendarText.setLocation(180,50);
        calendarText.setSize(100,20);

        createCalendar = new JButton("Create");
        createCalendar.setLocation(100,120);
        createCalendar.setSize(100,20);
    }

    public void addCreateCalendarListener(ActionListener createCalendarListener){

        this.createCalendar.addActionListener(createCalendarListener);
    }
}
