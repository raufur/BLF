package com.epsilon.coders.blf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        clockwise();
        move();

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intWelcome = new Intent(Welcome.this, MainActivity.class);
                    startActivity(intWelcome);
                    finish();
                }

            }
        });
        th.start();
    }

    public void clockwise() {
//        ImageView image = (ImageView)findViewById(R.id.imageView);
        TextView textView = (TextView) findViewById(R.id.textView);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
        textView.startAnimation(animation);

    }

    public void move() {
        ImageView image = (ImageView) findViewById(R.id.imageView);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        image.startAnimation(animation1);

    }
}