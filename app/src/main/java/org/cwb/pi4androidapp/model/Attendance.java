package org.cwb.pi4androidapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by valter.franco on 11/18/2017.
 */

public class Attendance implements Parcelable {

    private int attendanceId;
    private int attendancePatientId;
    private int attendanceDocId;
    private String attendanceDate;
    private String attendanceHour;
    private String attendancePatientName;
    private String attendanceDoctorName;

    public Attendance() {
    }

    public String getAttendancePatientName() {
        return attendancePatientName;
    }

    public void setAttendancePatientName(String attendancePatientName) {
        this.attendancePatientName = attendancePatientName;
    }

    public String getAttendanceDoctorName() {
        return attendanceDoctorName;
    }

    public void setAttendanceDoctorName(String attendanceDoctorName) {
        this.attendanceDoctorName = attendanceDoctorName;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getAttendancePatientId() {
        return attendancePatientId;
    }

    public void setAttendancePatientId(int attendancePatientId) {
        this.attendancePatientId = attendancePatientId;
    }

    public int getAttendanceDocId() {
        return attendanceDocId;
    }

    public void setAttendanceDocId(int attendanceDocId) {
        this.attendanceDocId = attendanceDocId;
    }

    public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getAttendanceHour() {
        return attendanceHour;
    }

    public void setAttendanceHour(String attendanceHour) {
        this.attendanceHour = attendanceHour;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.attendanceId);
        dest.writeInt(this.attendancePatientId);
        dest.writeInt(this.attendanceDocId);
        dest.writeString(this.attendanceDate);
        dest.writeString(this.attendanceHour);
        dest.writeString(this.attendancePatientName);
        dest.writeString(this.attendanceDoctorName);
    }

    protected Attendance(Parcel in) {
        this.attendanceId = in.readInt();
        this.attendancePatientId = in.readInt();
        this.attendanceDocId = in.readInt();
        this.attendanceDate = in.readString();
        this.attendanceHour = in.readString();
        this.attendancePatientName = in.readString();
        this.attendanceDoctorName = in.readString();
    }

    public static final Creator<Attendance> CREATOR = new Creator<Attendance>() {
        @Override
        public Attendance createFromParcel(Parcel source) {
            return new Attendance(source);
        }

        @Override
        public Attendance[] newArray(int size) {
            return new Attendance[size];
        }
    };
}
