package view;

import javax.swing.*;

import com.mindfusion.common.DateTime;
import com.mindfusion.scheduling.ThemeType;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;

public class EditEventWindow extends JFrame {

    private com.mindfusion.scheduling.Calendar cal;
    private DateTextBox startDate;
    private DateTextBox endDate;
    private JLabel startDateLabel;
    private JLabel endDateLabel;
    private JLabel nameLabel;
    private JTextField name;
    private JLabel locationLabel;
    private JTextField location;
    private JLabel descrLabel;
    private JTextArea descr;
    private JButton createEvent;
    private HourComboBox startHour;
    private HourComboBox endHour;
    
    public EditEventWindow(){
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        setSize(850,300);
        setTitle("Edit Event");
        setLocationRelativeTo(null);

        Container cp = getContentPane();
        cp.setLayout(null);

        cal = new com.mindfusion.scheduling.Calendar();
        cal.setLocation(0,0);
        cal.setSize(400,200);
        cal.setTheme(ThemeType.Light);

        startDateLabel = new JLabel("Start Date: ");
        startDateLabel.setLocation(410,40);
        startDateLabel.setSize(50,20);

        startDate = new DateTextBox();
        startDate.setLocation(500,40);
        startDate.setSize(100,20);

        startHour = new HourComboBox();
        startHour.setLocation(610,40);
        startHour.setSize(100,20);

        endHour = new HourComboBox();
        endHour.setLocation(610,70);
        endHour.setSize(100,20);

        endDateLabel = new JLabel("End Date: ");
        endDateLabel.setLocation(410,70);
        endDateLabel.setSize(50,20);

        endDate = new DateTextBox();
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

        createEvent = new JButton("Save");
        createEvent.setLocation(650,200);
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
        cp.add(createEvent);
        cp.add(endDate);
        cp.add(endDateLabel);
        cp.add(startHour);
        cp.add(endHour);

    }

    public com.mindfusion.scheduling.Calendar getCal() {
        return cal;
    }

    @Override
    public String getName() {

        return name.getText();
    }

    public String getDescriptionText(){
        return descr.getText();
    }

    public DateTime getStartDate(){
        return startDate.getDate();
    }

    public DateTime getEndDate(){
        return endDate.getDate();
    }

    public String getLocationName(){
        return location.getText();
    }

    public DateTime getStartHour() {
        return startHour.getTime();
    }

    public DateTime getEndHour() {
        return endHour.getTime();
    }

    public void setDescr(String descr) {
        this.descr.setText(descr);
    }

    public void setStartDate(DateTime date) {
        this.startDate.setDate(date);
    }

    public void setEndDate(DateTime date) {
        this.endDate.setDate(date);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setLocation(String location) {
        this.location.setText(location);
    }

    public void setStartHour(DateTime time) {
        this.startHour.setTime(time);
    }

    public void setEndHour(DateTime time) {
        this.endHour.setTime(time);
    }

    public void setSelectedStartDate(java.sql.Date newDate){

        //TODO: Si può togliere?

    }

    public void setSelectedEndDate(java.sql.Date newDate){

        //TODO: si può togliere?
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        endDate.setText(myFormat.format(newDate));
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