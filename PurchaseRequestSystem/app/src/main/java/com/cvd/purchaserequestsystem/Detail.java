package com.cvd.purchaserequestsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Detail extends AppCompatActivity {
    RecyclerView recyclerView;
    PrliAdapter PrliAdapter;
    ArrayList<Prli> PrliList;
    RequestQueue requestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        recyclerView = (RecyclerView) findViewById(R.id.detailView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PrliList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        parseJSON();

        Intent intent = getIntent();
        String id = intent.getStringExtra("prListID");
    }
    private void parseJSON() {
        String url = "http://10.0.2.2:8080/PurchaseRequestLineItems/List";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject prli = response.getJSONObject(i);
                        int Id = prli.getInt("Id");

                        JSONObject pr = prli.getJSONObject("PurchaseRequest");
                        int prId = pr.getInt("Id");

                        JSONObject User = pr.getJSONObject("User");
                        int uid = User.getInt("Id");
                        String UserName = User.getString("UserName");
                        String Password = User.getString("Password");
                        String FirstName = User.getString("FirstName");
                        String LastName = User.getString("LastName");
                        String PhoneNumber = User.getString("PhoneNumber");
                        String Email = User.getString("Email");
                        Boolean Reviewer = User.getBoolean("Reviewer");
                        Boolean Admin = User.getBoolean("Admin");


                        String Description = pr.getString("Description");
                        String Justification = pr.getString("Justification");

                        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
                        Date dateNeeded = format.parse(pr.getString("DateNeeded"));

                        String DeliveryMode = pr.getString("DeliveryMode");
                        String Status = pr.getString("Status");
                        double Total = pr.getInt("Total");

                        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS");
                        Date SubmittedDate = myFormat.parse(pr.getString("SubmittedDate"));

                        String ReasonForRejection = pr.getString("ReasonForRejection");

                        JSONObject p = prli.getJSONObject("Product");
                        String pid = p.getString("Id");

                        JSONObject v = p.getJSONObject("Vendor");
                        int vId = v.getInt("Id");
                        String code = v.getString("Code");
                        String vendorName = v.getString("Name");
                        String address = v.getString("Address");
                        String city = v.getString("City");
                        String state = v.getString("State");
                        String zip = v.getString("Zip");
                        String phonenumber = v.getString("PhoneNumber");
                        String email = v.getString("Email");
                        Boolean preapproved = v.getBoolean("PreApproved");
                        String summary = v.getString("Summary");

                        //String PartNumber = p.getString("PartNumber");
                        String name = p.getString("Name");
                        Double price = p.getDouble("Price");
                        String unit = p.getString("Unit");
                        String photoPath = p.getString("PhotoPath");

                        int quantity = prli.getInt("Quantity");

                        Double lineTotal = quantity * price;

                        PrliList.add(new Prli(name, quantity, price, lineTotal, prId));

                    }
                    PrliAdapter = new PrliAdapter(Detail.this, PrliList);
                    recyclerView.setAdapter(PrliAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);



    }


}
