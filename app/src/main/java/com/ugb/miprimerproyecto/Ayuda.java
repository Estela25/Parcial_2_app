package com.ugb.miprimerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

public class Ayuda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        String path = "android.resource://"+ getPackageName() + "/" +
                R.raw.rawr;
        videoView.setVideoURI(Uri.parse(path));
        videoView.start();
    }

    public void regresar(View view) {
        Intent volver = new Intent(this, MainActivity.class);
        startActivity(volver);
    }
}
