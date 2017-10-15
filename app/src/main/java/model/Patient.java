package model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by valter.franco on 10/8/2017.
 */

public class Patient implements Parcelable{

        // FIELDS
         int patientId;
         int patientCod;
         String patientName;
         String patientPhone;
         String patientEmail;
         Date patientBirthDate;
         int PatientAge;
         String patientBloodType;
         String partnerBloodType;
         String patientLastPeriod;
         Double pregnancyWeek;
         boolean active;
         boolean planned;
         boolean risk;
         Historic historic;

        // PROPERTIES


    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getPatientCod() {
        return patientCod;
    }

    public void setPatientCod(int patientCod) {
        this.patientCod = patientCod;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public Date getPatientBirthDate() {
        return patientBirthDate;
    }

    public void setPatientBirthDate(Date patientBirthDate) {
        this.patientBirthDate = patientBirthDate;
    }

    public int getPatientAge() {
        return PatientAge;
    }

    public void setPatientAge(int patientAge) {
        PatientAge = patientAge;
    }

    public String getPatientBloodType() {
        return patientBloodType;
    }

    public void setPatientBloodType(String patientBloodType) {
        this.patientBloodType = patientBloodType;
    }

    public String getPartnerBloodType() {
        return partnerBloodType;
    }

    public void setPartnerBloodType(String partnerBloodType) {
        this.partnerBloodType = partnerBloodType;
    }

    public String getPatientLastPeriod() {
        return patientLastPeriod;
    }

    public void setPatientLastPeriod(String patientLastPeriod) {
        this.patientLastPeriod = patientLastPeriod;
    }

    public Double getPregnancyWeek() {
        return pregnancyWeek;
    }

    public void setPregnancyWeek(Double pregnancyWeek) {
        this.pregnancyWeek = pregnancyWeek;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isPlanned() {
        return planned;
    }

    public void setPlanned(boolean planned) {
        this.planned = planned;
    }

    public boolean isRisk() {
        return risk;
    }

    public void setRisk(boolean risk) {
        this.risk = risk;
    }

    public Historic getHistoric() {
        return historic;
    }

    public void setHistoric(Historic historic) {
        this.historic = historic;
    }

    public String getPatientImage() {
        return "http://www.geonames.org/flags/x/" + patientCod + ".jpg";
    }

    //
    // INTERFACE: Parcelable

    public static final Creator<Patient> CREATOR = new Creator<Patient>() {
        @Override
        public Patient createFromParcel(Parcel parcel) {
            Patient p = new Patient();
            p.patientCod = parcel.readInt();
            p.patientName = parcel.readString();
            p.patientEmail = parcel.readString();
            return p;
        }

        @Override
        public Patient[] newArray(int i) {
            return new Patient[i];
        }
    };

    //-------

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(patientCod);
        parcel.writeString(patientName);
        parcel.writeString(patientEmail);
    }
}
