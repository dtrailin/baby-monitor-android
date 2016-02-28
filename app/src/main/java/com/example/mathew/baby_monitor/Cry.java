package com.example.mathew.baby_monitor;

import java.util.Date;

/**
 * Created by denis on 28/02/16.
 */
public class Cry {

    private String reason;
    private Date timeReceived;
    private boolean responded;


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getTimeReceived() {
        return timeReceived;
    }

    public void setTimeReceived(Date timeReceived) {
        this.timeReceived = timeReceived;
    }

    public boolean isResponded() {
        return responded;
    }

    public void setResponded(boolean responded) {
        this.responded = responded;
    }

    public Cry(String reason, Date timeReceived) {

        this.reason = reason;
        this.timeReceived = timeReceived;
    }
}
