package com.epsilon.coders.blf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class CheckBalance extends AppCompatActivity {
TextView cash_blf,cash_morning,cash_evening,cash_total,bank_190,bank_261,bank_351,bank_total,cash_date,bank_date;
String cas_blf,cas_morning,cas_evening,ban_190,ban_261,ban_351;
    String cas_total,ban_total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_balance);
        //Bundle b = this.getIntent().getExtras();
        cash_blf=(TextView)findViewById(R.id.tv_cash_blf);
        cash_morning=(TextView)findViewById(R.id.tv_cash_morning);
        cash_evening=(TextView)findViewById(R.id.tv_cash_ecening);
        cash_total=(TextView)findViewById(R.id.tv_cash_total);

        bank_190=(TextView)findViewById(R.id.bank_190);
        bank_261=(TextView)findViewById(R.id.bank_261);
        bank_351=(TextView)findViewById(R.id.bank_351);
        bank_total=(TextView)findViewById(R.id.bank_total);
        cash_date=(TextView)findViewById(R.id.cash_date_);
        bank_date=(TextView)findViewById(R.id.bank_date);



        String c_date = getIntent().getStringExtra("date");

        String m_blf = getIntent().getStringExtra("m_blf");
        String m_total = getIntent().getStringExtra("m_total");
        String e_total = getIntent().getStringExtra("e_total");
        String cashTotal = getIntent().getStringExtra("cashTotal");
        String ban_190 = getIntent().getStringExtra("bank_190");
        String ban_261 = getIntent().getStringExtra("bank_261");
        String ban_351 = getIntent().getStringExtra("bank_351");
        String ban_total = getIntent().getStringExtra("bank_total");
        cash_date.setText("Cash Collection as on "+c_date);
        bank_date.setText("Bank Balance  as on "+c_date);
        cash_blf.setText(m_blf);
        cash_morning.setText(m_total);
        cash_evening.setText(e_total);
        cash_total.setText(cashTotal);
        bank_190.setText(ban_190);
        bank_261.setText(ban_261);
        bank_351.setText(ban_351);
        bank_total.setText(ban_total);

       // String total = getIntent().getStringExtra("m_total");


        Toast.makeText(getApplicationContext(),cashTotal,Toast.LENGTH_LONG).show();
    }
}
