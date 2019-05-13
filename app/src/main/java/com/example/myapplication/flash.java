package com.example.myapplication;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

//SplashScreen.xml
//MainActivity.java

public class flash extends AppCompatActivity {
    private ProgressBar mProgress;
    private int progressStatus=0;
    private Handler handler=new Handler();//Handler is a class which is used to Handle/Manage  Thead's.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Makes an Activity Fullscreen OR Removes Titlebar.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Hiding the status bar.


        super.onCreate(savedInstanceState);
        //show the Splashscreen
        setContentView(R.layout.activity_flash);

        mProgress = (ProgressBar) findViewById(R.id.progressBar2);

        // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }

    private void doWork() {
        for (int progress=0; progress<100; progress+=5) {
            try {
                Thread.sleep(250);
                mProgress.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();

            }

        }
    }

    private void startApp() {
        Intent intent = new Intent(flash.this,Main2Activity.class);
        startActivity(intent);
    }
}