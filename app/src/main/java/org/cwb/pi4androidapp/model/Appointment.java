package org.cwb.pi4androidapp.model;

import android.text.format.DateFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Appointment
 */
public class Appointment implements Serializable, Comparable<Appointment>{
    private String appointmentId;
    private User user;
    private Date date;
    private long time;

    public Appointment(User user, long time){
        this.user = user;
        this.time = time;
    }

    private Appointment(){
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setAppointmentId(final String id) {
        this.appointmentId = id;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public long getTime() {
        return time;
    }

    /**
     * Gets a formatted time
     * @return - a nicely formatted time to display
     */
    public String getFormattedLocalTime(){
        String formattedTime = null;
        date = new Date(time);

        DateFormat df = new DateFormat();
        formattedTime = (String) df.format("MM-dd hh:mm aaa", new Date(time));

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
        String dayOfWeek = dateFormat.format(date);

        formattedTime = dayOfWeek + " "  + formattedTime;

        return formattedTime;
    }


    @Override
    public String toString() {
        return "Appointment{" +
                "mId='" + appointmentId + '\'' +
                ", mUser=" + user +
                ", mTime=" + date +
                '}';
    }

    @Override
    public int compareTo(Appointment a) {
        return (int) (time - a.time);
    }
}
