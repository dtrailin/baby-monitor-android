package com.example.mathew.baby_monitor;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

/**
 * Created by denis on 28/02/16.
 */
public class BabyEventAdapter  extends CursorAdapter {

    public static final String REASON = "reason";

    public BabyEventAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.baby_event_item, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, final Cursor cursor) {
        // Find fields to populate in inflated template
        TextView reason = (TextView) view.findViewById(R.id.eventName);
        TextView date = (TextView) view.findViewById(R.id.tvEventTime);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.hasBeenSeen);
        // Extract properties from cursor
        String name = cursor.getString(cursor.getColumnIndexOrThrow(REASON));
        final String priority = cursor.getString(cursor.getColumnIndexOrThrow("timestamp"));
        boolean responded = cursor.getInt(cursor.getColumnIndexOrThrow("responded")) > 0;

        checkBox.setPressed(responded);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test","did the thing");
               CheckBox checkBox = (CheckBox)v;
                new Update(BabyEvent.class).set("responded",checkBox.isPressed()).where("timestamp="+priority);
            }
        });
        // Populate fields with extracted properties
        reason.setText(name);
        date.setText(String.valueOf(priority));

    }
}