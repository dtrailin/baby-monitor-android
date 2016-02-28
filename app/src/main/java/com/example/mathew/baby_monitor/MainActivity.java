package com.example.mathew.baby_monitor;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Socket mSocket;
    ListView mListView;

    {
        try {
//            mSocket = IO.socket("http://baby-monitor.azurewebsites.net:7777");
            mSocket = IO.socket("http://45.79.134.17:7777");
        } catch (URISyntaxException e) {}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent registrationIntent = new Intent(this, RegistrationService.class);
        startService(registrationIntent);

        mSocket.on("cry", onCry);
        mSocket.connect();


        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("button pressed");
            }
        });
// Find ListView to populate
        mListView= (ListView) findViewById(R.id.babyEventList);
// Get data cursor
        Cursor todoCursor = BabyEvent.fetchResultCursor();
// Setup cursor adapter
        BabyEventAdapter todoAdapter = new BabyEventAdapter(this, todoCursor);
// Attach cursor adapter to ListView
        mListView.setAdapter(todoAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Emitter.Listener onCry = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d("gotsuff","a");
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    String message;
                    try {
                        int temperature = data.getInt("temp");
                        message = data.getString("reason");

                        BabyEvent babyEvent = new BabyEvent(message,new java.util.Date().toString());
                        babyEvent.setTemp(temperature);
                        babyEvent.save();
                        mListView.invalidateViews();
                    } catch (JSONException e) {
                        return;
                    }

                    // add the message to view

                }
            });
        }
    };


}
