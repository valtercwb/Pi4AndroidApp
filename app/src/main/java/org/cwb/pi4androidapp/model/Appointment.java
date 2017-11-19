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

     int appointmentId;
     User user;
     Date date;
     long time;

     int appointId;
     Patient appPatient;
     Doctor appDoctor;
     String appDate;
     String appDescr;
     int appPatWeight;
     String appPatBloodPres;
     String appPatUterusHeight;
     String appBabyHeartBeat;
     String appPatImc;

    //public Appointment() {
    //}

    public int getAppointId() {
        return appointId;
    }

    public void setAppointId(int appointId) {
        this.appointId = appointId;
    }

    public Patient getAppPatient() {
        return appPatient;
    }

    public void setAppPatient(Patient appPatient) {
        this.appPatient = appPatient;
    }

    public Doctor getAppDoctor() {
        return appDoctor;
    }

    public void setAppDoctor(Doctor appDoctor) {
        this.appDoctor = appDoctor;
    }

    public String getAppDate() {
        return this.appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public String getAppDescr() {
        return appDescr;
    }

    public void setAppDescr(String appDescr) {
        this.appDescr = appDescr;
    }

    public int getAppPatWeight() {
        return appPatWeight;
    }

    public void setAppPatWeight(int appPatWeight) {
        this.appPatWeight = appPatWeight;
    }

    public String getAppPatBloodPres() {
        return appPatBloodPres;
    }

    public void setAppPatBloodPres(String appPatBloodPres) {
        this.appPatBloodPres = appPatBloodPres;
    }

    public String getAppPatUterusHeight() {
        return appPatUterusHeight;
    }

    public void setAppPatUterusHeight(String appPatUterusHeight) {
        this.appPatUterusHeight = appPatUterusHeight;
    }

    public String getAppBabyHeartBeat() {
        return appBabyHeartBeat;
    }

    public void setAppBabyHeartBeat(String appBabyHeartBeat) {
        this.appBabyHeartBeat = appBabyHeartBeat;
    }

    public String getAppPatImc() {
        return appPatImc;
    }

    public void setAppPatImc(String appPatImc) {
        this.appPatImc = appPatImc;
    }

    public Appointment(User user, long time){
        this.user = user;
        this.time = time;
    }

    public Appointment(){
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setAppointmentId(final int id) {
        this.appointmentId = id;
    }

    public int getAppointmentId() {
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
