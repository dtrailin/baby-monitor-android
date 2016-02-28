package com.example.mathew.baby_monitor;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

/**
 * Created by denis on 28/02/16.
 */
@Table(name = "BabyEvents")
public class BabyEvents extends Model {

    @Column(name = "reason")
    private String reason;
    @Column(name = "timestamp")
    private String timeReceived;
    @Column(name = "responded")
    private boolean responded;


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTimeReceived() {
        return timeReceived;
    }

    public void setTimeReceived(String timeReceived) {
        this.timeReceived = timeReceived;
    }

    public boolean isResponded() {
        return responded;
    }

    public void setResponded(boolean responded) {
        this.responded = responded;
    }

    public BabyEvents(String reason, String timeReceived) {

        this.reason = reason;
        this.timeReceived = timeReceived;
    }
}
