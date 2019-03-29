package com.adepagroup.lyricsfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
//import android.app.DownloadManager.Request;
import com.android.volley.Request;
import com.android.volley.toolbox.*;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import android.widget.TextView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    EditText showSongName;
    EditText showSongArtist;
    String songName;
    String songArtist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void search(View view){


        showSongName = (EditText) findViewById(R.id.songName);
        songName = showSongName.getText().toString();

        showSongArtist = (EditText) findViewById(R.id.songArtist);
        songArtist = showSongArtist.getText().toString();

        if(songName.equals("") || songArtist.equals("")){
            Toast.makeText(this, "You can't leave either fields empty!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Fasten up! Searching for lyrics.", Toast.LENGTH_SHORT).show();

            lyricsPage(view);

        }
    }
     //call scrolling page
    public void lyricsPage (View view){
        Intent intent = new Intent (this, ScrollingActivity.class);
        intent.putExtra("songName", songName);
        intent.putExtra("songArtist", songArtist);
        startActivity(intent);
    }


}
