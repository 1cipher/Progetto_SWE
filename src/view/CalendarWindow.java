package view;

import javax.swing.*;

import com.mindfusion.scheduling.ThemeType;
import model.Calendar;
import model.CalendarCollection;
import utils.CustomRenderer;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CalendarWindow extends JFrame {

    private com.mindfusion.scheduling.Calendar cal;
    private JTextField startDate;
    private JTextField endDate;
    private JLabel startDateLabel;
    private JLabel endDateLabel;
    private JLabel nameLabel;
    private JLabel calendarLabel;
    private JTextField name;
    private JLabel locationLabel;
    private JTextField location;
    private JComboBox<String> color;
    private JLabel colorLabel;
    private JLabel descrLabel;
    private JTextArea descr;
    private JButton createEvent;
    private JComboBox<String> startHour;
    private JComboBox<String> endHour;
    private JComboBox<Calendar> selectedCalendarMenu;


    public CalendarWindow(CalendarCollection calendars){
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        setSize(850,300);

        Container cp = getContentPane();
        cp.setLayout(null);

        cal = new com.mindfusion.scheduling.Calendar();
        cal.setLocation(0,0);
        cal.setSize(400,200);
        cal.setTheme(ThemeType.Light);

        selectedCalendarMenu = new JComboBox<>();
        selectedCalendarMenu.setLocation(500,230);
        selectedCalendarMenu.setSize(150,20);
        selectedCalendarMenu.setRenderer(new CustomRenderer());
        setCalendars(calendars);

        startDateLabel = new JLabel("Start Date: ");
        startDateLabel.setLocation(410,40);
        startDateLabel.setSize(50,20);

        startDate = new JTextField();
        startDate.setLocation(500,40);
        startDate.setSize(100,20);

        startHour = new JComboBox<>(createModel());
        startHour.setLocation(610,40);
        startHour.setSize(100,20);

        endHour = new JComboBox<>(createModel());
        endHour.setLocation(610,70);
        endHour.setSize(100,20);

        endDateLabel = new JLabel("End Date: ");
        endDateLabel.setLocation(410,70);
        endDateLabel.setSize(50,20);

        endDate = new JTextField();
        endDate.setLocation(500,70);
        endDate.setSize(100,20);


        nameLabel = new JLabel("Name: ");
        nameLabel.setLocation(410,10);
        nameLabel.setSize(50,20);

        name = new JTextField();
        name.setLocation(500,10);
        name.setSize(300,20);
        name.setText("Merenda nonna");

        locationLabel = new JLabel("Location: ");
        locationLabel.setLocation(410,110);
        locationLabel.setSize(50,20);

        location = new JTextField();
        location.setLocation(500,110);
        location.setSize(300,20);
        location.setText("Casa nonna");

        descrLabel = new JLabel("Description: ");
        descrLabel.setLocation(410,140);
        descrLabel.setSize(60,20);

        descr = new JTextArea();
        descr.setLocation(500,140);
        descr.setSize(300,50);
        descr.setText("Sarà deliziosa!");

        colorLabel = new JLabel("Colour: ");
        colorLabel.setLocation(410,200);
        colorLabel.setSize(50,20);

        calendarLabel = new JLabel("model.Calendar: ");
        calendarLabel.setLocation(410,230);
        calendarLabel.setSize(50,20);

        color = new JComboBox<String>();
        color.setLocation(500,200);
        color.setSize(100,20);
        color.addItem("Black");
        color.addItem("Red");
        color.addItem("Green");
        color.addItem("Blue");

        createEvent = new JButton("Create model.Event");
        createEvent.setLocation(700,200);
        createEvent.setSize(150,20);

        cp.add(cal);
        cp.add(startDateLabel);
        cp.add(startDate);
        cp.add(name);
        cp.add(nameLabel);
        cp.add(locationLabel);
        cp.add(location);
        cp.add(descr);
        cp.add(descrLabel);
        cp.add(colorLabel);
        cp.add(color);
        cp.add(createEvent);
        cp.add(endDate);
        cp.add(endDateLabel);
        cp.add(startHour);
        cp.add(endHour);
        cp.add(selectedCalendarMenu);
        cp.add(calendarLabel);

    }

    public com.mindfusion.scheduling.Calendar getCal() {
        return cal;
    }

    public String getName(){
        return name.getText();
    }

    public String getDescriptionText(){
        return descr.getText();
    }

    public String getStartDate(){
        return startDate.getText();
    }

    public String getEndDate(){
        return endDate.getText();
    }

    public String getLocationName(){
        return location.getText();
    }

    public String getStartHour() {
        return (String) startHour.getSelectedItem();
    }

    public String getEndHour() {
        return (String) endHour.getSelectedItem();
    }

    public model.Calendar getCurrentCalendar(){
        return (model.Calendar) selectedCalendarMenu.getSelectedItem();
    }

    public DefaultComboBoxModel<String> createModel(){

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);
        calendar.set(java.util.Calendar.MINUTE, 0);

        java.util.Calendar end = java.util.Calendar.getInstance();
        end.set(java.util.Calendar.HOUR_OF_DAY, 23);
        end.set(java.util.Calendar.MINUTE, 59);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        do {
            DateFormat format = new SimpleDateFormat("HH:mm");
            model.addElement(format.format(calendar.getTime()));
            calendar.add(java.util.Calendar.MINUTE, 15);
        } while (calendar.getTime().before(end.getTime()));

        return model;
    }

    public void setCalendars(CalendarCollection list){
        ArrayList<model.Calendar> calendars_list = list.getCalendars();
        for (model.Calendar calendar:
                calendars_list) {
            selectedCalendarMenu.addItem(calendar);
        }
    }

    public void setSelectedStartDate(java.sql.Date newDate){

        java.sql.Date selectedDate = newDate;

        startDate.setText((selectedDate.toString()));
    }

    public void setSelectedEndDate(java.sql.Date newDate){

        java.sql.Date selectedDate = newDate;

        endDate.setText((selectedDate.toString()));
    }


    public void addCreateEventListener(ActionListener createEventListener){

        this.createEvent.addActionListener(createEventListener);
    }

    public void addCalendarPressListener(MouseListener calendarPressedListener){

        this.cal.addMouseListener(calendarPressedListener);
    }
    public void close(){
        this.setVisible(false);
        this.dispose();
    }

}