package org.cwb.pi4androidapp.model;

/**
 * Created by valter.franco on 11/12/2017.
 */

public class Exam {

    private int examId;
    private int examPatient;
    private String examReason;
    private String examObs;
    private String examMedia;
    private String examDate;
    private String examMedico;
    private String examType;
    private String examPlace;

    public int getExamId() {
        return examId;
    }


    public String getExamPlace() {

        return examPlace;

    }


    public void setExamPlace(String examPlace) {

        this.examPlace = examPlace;

    }


    public void setExamId(int examId) {

        this.examId = examId;

    }


    public int getExamPatient() {

        return examPatient;

    }


    public void setExamPatient(int examPatient) {

        this.examPatient = examPatient;

    }


    public String getExamReason() {

        return examReason;

    }


    public void setExamReason(String examReason) {

        this.examReason = examReason;

    }


    public String getExamObs() {

        return examObs;

    }


    public void setExamObs(String examObs) {

        this.examObs = examObs;

    }


    public String getExamMedia() {

        return examMedia;

    }


    public void setExamMedia(String examMedia) {

        this.examMedia = examMedia;

    }


    public String getExamDate() {

        return examDate;

    }


    public void setExamDate(String examDate) {

        this.examDate = examDate;

    }


    public String getExamMedico() {

        return examMedico;

    }


    public void setExamMedico(String examMedico) {

        this.examMedico = examMedico;

    }


    public String getExamType() {

        return examType;

    }


    public void setExamType(String examType) {

        this.examType = examType;

    }


}