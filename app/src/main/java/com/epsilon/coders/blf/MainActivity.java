package com.epsilon.coders.blf;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    //DecimalFormat df = new DecimalFormat("#.##");
    NumberFormat numberFormat  = new DecimalFormat("##");
    String str = numberFormat.format(-01234.567);





    private String jsonResponse;
    private String jsonResponse2;
    Button buttonb,sms;
    private static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonb = (Button) findViewById(R.id.btn);
        sms = (Button) findViewById(R.id.btn_sms);
        String htmlText = " %s ";
        WebView f = (WebView)findViewById(R.id.wv_function_of_bard);
        f.loadData(String.format(htmlText, getString(R.string.aboutBLF)), "text/html", "utf-8");


        //String urlJsonObj = "http://resaps.teletalk.com.bd/new.php?";
       final String sms_body = "http://resaps.teletalk.com.bd/new.php?";

        buttonb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlJsonObj = "http://cleaninlife.com/blf/get_cash_details.php";
                makeJsonObjectRequest(urlJsonObj);
            }
        });
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(getBaseContext(),MonthlyBalance.class);
                startActivity(intent);
            }
        });

    }

    private void makeJsonObjectRequest(String urlJsonObj) {

        // showpDialog();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlJsonObj, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                double m_total = 0.0;
                double e_total = 0.0;
                double cashTotal=0.0;
                double bank_total=0.0;
                String m_blf="";
                String bank_190="";
                String bank_261="";
                String bank_351="";
                String date ="";


                try {
                    // Parsing json object response
                    // response will be a json object
                    String success = response.getString("success");


                    if (success.equalsIgnoreCase("0")) {


                        String message = response.getString("message");
                        /*AlertDialog.Builder builder = new AlertDialog.Builder(getBaseContext());

                       // if (title != null) builder.setTitle(title);

                        builder.setMessage(message);
                        builder.setPositiveButton("OK", null);
                        builder.setNegativeButton("Cancel", null);
                        builder.show();*/
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Bangladesh Lions Foundation");
                        builder.setMessage(message)
                                .setCancelable(false)
                                .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //SmsActivity.this.finish();
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();

                    }

                       if (success.equalsIgnoreCase("1")) {

                        JSONArray jsonarrayobj = response.getJSONArray("cash_collection");


                        for (int i = 0; i < jsonarrayobj.length(); i++) {

                            JSONObject jobj = jsonarrayobj.getJSONObject(i);

                            //   date`, `m_opd`, `m_pharmacy`, `m_optics`, `m_other`, `m_blf`, `e_opd`, `e_pharmacy`, `e_optics
                             date = jobj.getString("date");
                            String m_opd = jobj.getString("m_opd");
                            String m_pharmacy = jobj.getString("m_pharmacy");
                            String m_optics = jobj.getString("m_optics");
                            String m_other = jobj.getString("m_other");
                             m_blf = jobj.getString("m_blf");
                            String e_opd = jobj.getString("e_opd");
                            String e_pharmacy = jobj.getString("e_pharmacy");

                            String e_optics = jobj.getString("e_optics");
                             bank_190 = jobj.getString("bank_190");
                             bank_261 = jobj.getString("bank_261");
                             bank_351 = jobj.getString("bank_351");

                            m_total = Double.parseDouble(m_opd) + Double.parseDouble(m_pharmacy) + Double.parseDouble(m_optics) +
                                    Double.parseDouble(m_other);
                            e_total = Double.parseDouble(e_opd) + Double.parseDouble(e_pharmacy) + Double.parseDouble(e_optics);
                            double d_m_blf = Double.parseDouble(m_blf);
                            cashTotal=m_total+e_total+ d_m_blf;

                            bank_total = Double.parseDouble(bank_190) + Double.parseDouble(bank_261) + Double.parseDouble(bank_351);

                           // System.out.print(df.format(d));
                                /*code[i] = jobj.getString("CODE");
                                subject[i] = jobj.getString("SUBJECT");
                                grade[i] = jobj.getString("GRADE");*/

                        }


                        Intent in = new Intent(getApplicationContext(), CheckBalance.class);

                        in.putExtra("date", String.valueOf(date));
                        in.putExtra("m_blf", String.valueOf(m_blf));
                        in.putExtra("m_total", String.valueOf(m_total));
                        in.putExtra("e_total", String.valueOf(e_total));
                        in.putExtra("bank_190", String.valueOf(bank_190));
                        in.putExtra("bank_261", String.valueOf(bank_261));
                        in.putExtra("bank_351", String.valueOf(bank_351));
                        in.putExtra("bank_total", String.valueOf(String.format("%.2f",bank_total)));
                        in.putExtra("cashTotal", String.valueOf(String.format("%.2f", cashTotal)));

                        startActivity(in);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                // hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                // hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
}
