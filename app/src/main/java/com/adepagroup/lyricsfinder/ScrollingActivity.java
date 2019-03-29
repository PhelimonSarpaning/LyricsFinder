package com.adepagroup.lyricsfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.net.Uri;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       System.out.println("In scrolling Activity");
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
           //get text from first activity;
//
            final TextView textView = (TextView) findViewById(R.id.songLyrics);

            Intent intent = getIntent();
            String songName = intent.getStringExtra("songName");
            String songArtist = intent.getStringExtra("songArtist");
            System.out.print("songArtist is " + songArtist);
// ...

            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("https")
                .authority("api.lyrics.ovh")
                .appendPath("v1")
                .appendPath(String.valueOf(songArtist))
                    .appendPath(String.valueOf(songName));
            String url = builder.build().toString();
            //String url ="https://api.lyrics.ovh/v1/{songArtcoldplayist}/{songName}";

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            String mus_lyrics = null;
                            try {
                                JSONObject lyrics = new JSONObject(response);
                                mus_lyrics = lyrics.getString("lyrics");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            textView.setText(mus_lyrics);
                            Log.d("response", response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    textView.setText("That didn't work! Couldn't find lyrics.");
                }
            });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
