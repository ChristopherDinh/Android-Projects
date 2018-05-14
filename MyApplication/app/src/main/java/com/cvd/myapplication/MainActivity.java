package com.cvd.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;

    private static String url = "http://10.0.2.2:8080/PurchaseRequests/List";

    ArrayList<HashMap<String,String>> pr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pr = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);
        new GetPurchaseRequest().execute();
    }
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(), PurchaseRequestList.class));
    }

    private class GetPurchaseRequest extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    //JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray purchaseRequest = new JSONArray(jsonStr);

                    // looping through All Contacts
                    for (int i = 0; i < purchaseRequest.length(); i++) {
                        JSONObject c = purchaseRequest.getJSONObject(i);



                        JSONObject User = c.getJSONObject("User");
                        String UserName = User.getString("UserName");
                        String Password = User.getString("Password");
                        String FirstName = User.getString("FirstName");
                        String LastName = User.getString("LastName");
                        String PhoneNumber = User.getString("PhoneNumber");
                        String Email = User.getString("Email");


                        String Description = c.getString("Description");
                        String Justification = c.getString("Justification");
                        String DateNeeded = c.getString("DateNeeded");
                        String DeliveryMode = c.getString("DeliveryMode");
                        String Status = c.getString("Status");
                        String Total = c.getString("Total");
                        String ReasonForRejection = c.getString("ReasonForRejection");



                        HashMap<String, String> users = new HashMap<>();
                        users.put("UserName", UserName);
                        users.put("FirstName", FirstName);
                        users.put("Password", Password);
                        users.put("LastName", LastName);
                        users.put("PhoneNumber", PhoneNumber);
                        users.put("ReasonForRejection", ReasonForRejection);
                        users.put("Email", Email);
                        users.put("Description", Description);
                        users.put("Justification", Justification);
                        users.put("DateNeeded", DateNeeded);
                        users.put("DeliveryMode", DeliveryMode);
                        users.put("Status", Status);
                        users.put("Total", Total);
                        pr.add(users);

                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, pr, R.layout.list_items,
                    new String[] {"UserName", "FirstName","LastName", "PhoneNumber","Email","Description","Justification","Status","Total"},
                    new int[]{R.id.UserName, R.id.FirstName, R.id.LastName,R.id.PhoneNumber, R.id.Email, R.id.description, R.id.justification, R.id.status, R.id.total});

            lv.setAdapter(adapter);
        }

    }


}