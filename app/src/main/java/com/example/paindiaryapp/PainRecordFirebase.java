package com.example.paindiaryapp;

public class PainRecordFirebase {

    String email;
    String moodF;
    int pintensityF;
    int setgoalF;
    int dailystepF;
    String painLocationF;

    public PainRecordFirebase ( String email, String moodF, int pintensityF, int setgoalF, int dailystepF, String painLocationF ) {
        this.email = email;
        this.moodF = moodF;
        this.pintensityF = pintensityF;
        this.setgoalF = setgoalF;
        this.dailystepF = dailystepF;
        this.painLocationF = painLocationF;
    }

    public String getEmail ( ) {
        return email;
    }

    public String getMoodF ( ) {
        return moodF;
    }

    public int getPintensityF ( ) {
        return pintensityF;
    }

    public int getSetgoalF ( ) {
        return setgoalF;
    }

    public int getDailystepF ( ) {
        return dailystepF;
    }

    public String getPainLocationF ( ) {
        return painLocationF;
    }
}
