package com.epsilon.coders.blf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MonthyReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthy_report);
        TextView textView=(TextView)findViewById(R.id.textView);
        textView.setTextSize(35);
        Bundle bundle=getIntent().getExtras();
       String s= bundle.getString("key");
        textView.setText("REPORT OF "+s +" COMES HARE");

    }
}
