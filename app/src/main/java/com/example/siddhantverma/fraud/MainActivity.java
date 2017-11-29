package com.example.siddhantverma.fraud;

import android.content.Context;
import android.icu.text.DateFormat;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button pol;
    Button low;
    Button med;
    Button high;
    String sData;
    TextView tv;
    String Data = "";
    String result;
    InputStream isr;
    Context con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pol=(Button)findViewById(R.id.pollution);
        low=(Button)findViewById(R.id.low);
        med=(Button)findViewById(R.id.medium);
        high=(Button)findViewById(R.id.high);
        tv=(TextView)findViewById(R.id.textV);

        pol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                Log.d("time: ", String.valueOf(currentDateTimeString));
                Toast.makeText(getApplicationContext(),currentDateTimeString,Toast.LENGTH_SHORT).show();
                new getData().execute("");

            }
        });

        low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                Log.d("time: ", String.valueOf(currentDateTimeString));
                Toast.makeText(getApplicationContext(),currentDateTimeString,Toast.LENGTH_SHORT).show();
                new getData().execute("");

            }
        });
        med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                Log.d("time: ", String.valueOf(currentDateTimeString));
                Toast.makeText(getApplicationContext(),currentDateTimeString,Toast.LENGTH_SHORT).show();
                new getData().execute("");

            }
        });
        high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                Log.d("time: ", String.valueOf(currentDateTimeString));
                Toast.makeText(getApplicationContext(),currentDateTimeString,Toast.LENGTH_SHORT).show();
                new getData().execute("");

            }
        });

    }
    private class getData extends AsyncTask<String, Void, String> {
        String name;

        @Override
        protected String doInBackground(String... params) {
             result = "";
            isr = null;
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://tashfik.com/NotificationGetData.php"); //YOUR PHP SCRIPT ADDRESS
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                isr = entity.getContent();
            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection " + e.toString());

            }




            //convert response to string
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(isr, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                isr.close();

                result = sb.toString();
            } catch (Exception e) {
                Log.e("log_tag", "Error  converting result " + e.toString());
            }


            try {


                JSONArray jArray = new JSONArray(result);

                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json = jArray.getJSONObject(i);


                    Data=Data+"\n"+  json.getString("Head");

                }

            } catch (Exception e) {
                // TODO: handle exception
                Log.e("log_tag", "Error Parsing Data " + e.toString());
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            tv.setText(""+"sda"+Data);

        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

}
