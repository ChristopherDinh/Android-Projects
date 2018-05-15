package com.cvd.purchaserequestsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

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

public class UserActivity extends AppCompatActivity implements PurchaseRequestAdapter.OnClickListener {

    RecyclerView recyclerView;
    PurchaseRequestAdapter prAdapter;
    List<PurchaseRequest> prList;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        prList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }


    private void parseJSON() {
        String url = "http://10.0.2.2:8080/PurchaseRequests/List";
        String username = "dinher";
        String password = "time";

        EditText mUsername;
        //username = mUsername.getText().toString();
        String login = "http://10.0.2.2/Users/Authenticate?uName=" + username + "&password=" + password;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject pr = response.getJSONObject(i);
                        int Id = pr.getInt("Id");

                        JSONObject User = pr.getJSONObject("User");
                        int pid = User.getInt("Id");
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

                        prList.add(new PurchaseRequest(Id, Description, Justification, Status, Total,UserName,FirstName,LastName,PhoneNumber,Email));
                    }
                    prAdapter = new PurchaseRequestAdapter(UserActivity.this, prList);
                    recyclerView.setAdapter(prAdapter);
                    prAdapter.setOnClickListener(UserActivity.this);

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

    @Override
    public void onItemCLick(int position) {
        Intent detailIntent = new Intent(this, Detail.class);
//        PurchaseRequest clickedItem = prList.get(position).getId();

        detailIntent.putExtra("prListID", prList.get(position).getId());
        PrliAdapter.primaryId = prList.get(position).getId();

        startActivity(detailIntent);
    }
}

