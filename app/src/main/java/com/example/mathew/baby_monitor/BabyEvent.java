package com.example.mathew.baby_monitor;

import android.database.Cursor;

import com.activeandroid.Cache;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.Date;

/**
 * Created by denis on 28/02/16.
 */
@Table(name = "BabyEvent")
public class BabyEvent extends Model {

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

    public BabyEvent(String reason, String timeReceived) {

        this.reason = reason;
        this.timeReceived = timeReceived;
    }

    public static Cursor fetchResultCursor() {
        String tableName = Cache.getTableInfo(BabyEvent.class).getTableName();
        // Query all items without any conditions
        String resultRecords = new Select(tableName + ".*, " + tableName + ".Id as _id").
                from(BabyEvent.class).toSql();
        // Execute query on the underlying ActiveAndroid SQLite database
        Cursor resultCursor = Cache.openDatabase().rawQuery(resultRecords, null);
        return resultCursor;
    }
}

