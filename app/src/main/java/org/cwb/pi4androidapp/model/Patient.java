package org.cwb.pi4androidapp.model;

import android.content.Context;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.cwb.pi4androidapp.activity.PatientDetailsActivity;
import org.cwb.pi4androidapp.ws.Paths;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by valter.franco on 10/8/2017.
 */

public class Patient implements Parcelable {

    // FIELDS
    int patientId;
    int patientCod;
    String patientName;
    String patientPhone;
    String patientEmail;
    Date patientBirthDate;
    int patientAge;
    String patientBloodType;
    String partnerBloodType;
    String patientLastPeriod;
    Double pregnancyWeek;
    boolean active;
    boolean planned;
    boolean risk;
    boolean sifilis;
    boolean gonorreia;
    boolean clamidia;
    boolean diabetes;
    boolean doencaMental;
    boolean anomalia;
    boolean neoplasia;
    boolean tromboembo;
    boolean cardiopatia;
    boolean hiv;

    Context mContext;

    public Patient() {
    }

    public boolean isHiv() {
        return hiv;
    }

    public void setHiv(boolean hiv) {
        this.hiv = hiv;
    }

    public boolean isSifilis() {
        return sifilis;
    }

    public void setSifilis(boolean sifilis) {
        this.sifilis = sifilis;
    }

    public boolean isGonorreia() {
        return gonorreia;
    }

    public void setGonorreia(boolean gonorreia) {
        this.gonorreia = gonorreia;
    }

    public boolean isClamidia() {
        return clamidia;
    }

    public void setClamidia(boolean clamidia) {
        this.clamidia = clamidia;
    }

    public boolean isDiabetes() {
        return diabetes;
    }

    public void setDiabetes(boolean diabetes) {
        this.diabetes = diabetes;
    }

    public boolean isDoencaMental() {
        return doencaMental;
    }

    public void setDoencaMental(boolean doencaMental) {
        this.doencaMental = doencaMental;
    }

    public boolean isAnomalia() {
        return anomalia;
    }

    public void setAnomalia(boolean anomalia) {
        this.anomalia = anomalia;
    }

    public boolean isNeoplasia() {
        return neoplasia;
    }

    public void setNeoplasia(boolean neoplasia) {
        this.neoplasia = neoplasia;
    }

    public boolean isTromboembo() {
        return tromboembo;
    }

    public void setTromboembo(boolean tromboembo) {
        this.tromboembo = tromboembo;
    }

    public boolean isCardiopatia() {
        return cardiopatia;
    }

    public void setCardiopatia(boolean cardiopatia) {
        this.cardiopatia = cardiopatia;
    }

    public Patient(Context context) {
        mContext = context;
    }

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
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
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

    public void LoadPatientData(final RefreshHandlerPatient handler, int patientId) {
        final Patient p = new Patient();
        String patPath = Paths.PATIENT_URL + String.valueOf(patientId);
        RequestQueue queue = Volley.newRequestQueue(mContext);

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, patPath , null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            p.setPatientId(response.getInt("patientId"));
                            p.setPatientCod(response.getInt("patientCod"));
                            p.setPatientName(response.getString("patientName"));
                            p.setPatientEmail(response.getString("patientEmail"));
                            p.setPatientPhone(response.getString("patientPhone"));
                            p.setPatientAge(response.getInt("patientAge"));
                            p.setActive(response.getBoolean("active"));
                            p.setPatientBloodType(response.getString("patientBloodType"));
                            p.setPartnerBloodType(response.getString("partnerBloodType"));
                            p.setPatientLastPeriod(response.getString("patientLastPeriod"));
                            p.setRisk(response.getBoolean("risk"));
                            p.setPlanned(response.getBoolean("planned"));
                            p.setPatientLastPeriod(response.getString("patientLastPeriod"));
                            p.setCardiopatia(response.getBoolean("cardiopatia"));
                            p.setDiabetes(response.getBoolean("diabetes"));
                            p.setNeoplasia(response.getBoolean("neoplasia"));
                            p.setAnomalia(response.getBoolean("anomalia"));
                            p.setDoencaMental(response.getBoolean("doencaMental"));
                            p.setGonorreia(response.getBoolean("gonorreia"));
                            p.setSifilis(response.getBoolean("sifilis"));
                            p.setClamidia(response.getBoolean("clamidia"));
                            p.setHiv(response.getBoolean("hiv"));
                            p.setTromboembo(response.getBoolean("tromboembo"));
                            p.setPregnancyWeek(response.getDouble("pregnancyWeek"));

                            handler.onRefreshCompletedPatient(true,p);
                        } catch (JSONException e) {
                            handler.onRefreshCompletedPatient(false,p);
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handler.onRefreshCompletedPatient(false,p);
                Log.i("Erro","Response error");
            }

        });
        queue.add(jsonObjectRequest);
    }

    public interface RefreshHandlerPatient {
        void onRefreshCompletedPatient(Boolean success,Patient patient);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.patientId);
        dest.writeInt(this.patientCod);
        dest.writeString(this.patientName);
        dest.writeString(this.patientPhone);
        dest.writeString(this.patientEmail);
        dest.writeLong(this.patientBirthDate != null ? this.patientBirthDate.getTime() : -1);
        dest.writeInt(this.patientAge);
        dest.writeString(this.patientBloodType);
        dest.writeString(this.partnerBloodType);
        dest.writeString(this.patientLastPeriod);
        dest.writeValue(this.pregnancyWeek);
        dest.writeByte(this.active ? (byte) 1 : (byte) 0);
        dest.writeByte(this.planned ? (byte) 1 : (byte) 0);
        dest.writeByte(this.risk ? (byte) 1 : (byte) 0);
        dest.writeByte(this.sifilis ? (byte) 1 : (byte) 0);
        dest.writeByte(this.gonorreia ? (byte) 1 : (byte) 0);
        dest.writeByte(this.clamidia ? (byte) 1 : (byte) 0);
        dest.writeByte(this.diabetes ? (byte) 1 : (byte) 0);
        dest.writeByte(this.doencaMental ? (byte) 1 : (byte) 0);
        dest.writeByte(this.anomalia ? (byte) 1 : (byte) 0);
        dest.writeByte(this.neoplasia ? (byte) 1 : (byte) 0);
        dest.writeByte(this.tromboembo ? (byte) 1 : (byte) 0);
        dest.writeByte(this.cardiopatia ? (byte) 1 : (byte) 0);
        dest.writeByte(this.hiv ? (byte) 1 : (byte) 0);
    }

    protected Patient(Parcel in) {
        this.patientId = in.readInt();
        this.patientCod = in.readInt();
        this.patientName = in.readString();
        this.patientPhone = in.readString();
        this.patientEmail = in.readString();
        long tmpPatientBirthDate = in.readLong();
        this.patientBirthDate = tmpPatientBirthDate == -1 ? null : new Date(tmpPatientBirthDate);
        this.patientAge = in.readInt();
        this.patientBloodType = in.readString();
        this.partnerBloodType = in.readString();
        this.patientLastPeriod = in.readString();
        this.pregnancyWeek = (Double) in.readValue(Double.class.getClassLoader());
        this.active = in.readByte() != 0;
        this.planned = in.readByte() != 0;
        this.risk = in.readByte() != 0;
        this.sifilis = in.readByte() != 0;
        this.gonorreia = in.readByte() != 0;
        this.clamidia = in.readByte() != 0;
        this.diabetes = in.readByte() != 0;
        this.doencaMental = in.readByte() != 0;
        this.anomalia = in.readByte() != 0;
        this.neoplasia = in.readByte() != 0;
        this.tromboembo = in.readByte() != 0;
        this.cardiopatia = in.readByte() != 0;
        this.hiv = in.readByte() != 0;
    }

    public static final Creator<Patient> CREATOR = new Creator<Patient>() {
        @Override
        public Patient createFromParcel(Parcel source) {
            return new Patient(source);
        }

        @Override
        public Patient[] newArray(int size) {
            return new Patient[size];
        }
    };
}