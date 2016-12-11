package com.navigeto.navigeto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.navigeto.navigeto.MainActivity.AllWayPoints;
import static com.navigeto.navigeto.MainActivity.CurrentTripID;
import static com.navigeto.navigeto.MainActivity.DataLoadOnStart;
import static com.navigeto.navigeto.MainActivity.LoggedinStatus;
import static com.navigeto.navigeto.MainActivity.MyEmailID;
import static com.navigeto.navigeto.MainActivity.MyUID;
import static com.navigeto.navigeto.MainActivity.TripID;
import static com.navigeto.navigeto.MainActivity.UserID;
import static com.navigeto.navigeto.MainActivity.UsersOnTrip;
import static com.navigeto.navigeto.MainActivity.editSharedpreferences;
import static com.navigeto.navigeto.MainActivity.loggedIn;
import static com.navigeto.navigeto.MainActivity.sharedpreferences;
import static java.lang.Thread.sleep;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //Creating views
    private android.support.v7.widget.AppCompatEditText editTextUserEmail;
    private android.support.v7.widget.AppCompatEditText editTextPassword;

    private AppCompatButton buttonLogin;
    private AppCompatButton linkResetPass;
    private AppCompatButton linkRegister;

    //Volley RequestQueue
    private RequestQueue requestQueue;

    //String variables to hold username password and phone
    private String useremail;
    private String password;
    private AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView myTextView = (TextView) findViewById(R.id.textView);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "ARESSENCE.ttf");
        myTextView.setTypeface(typeface);

   /*     Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        editTextUserEmail = (AppCompatEditText) findViewById(R.id.editTextUserEmail);
        editTextPassword = (AppCompatEditText) findViewById(R.id.editTextPassword);

        buttonLogin = (AppCompatButton) findViewById(R.id.buttonLogin);
        linkResetPass = (AppCompatButton) findViewById(R.id.linkResetPass);
        linkRegister = (AppCompatButton) findViewById(R.id.linkRegister);

        buttonLogin.setOnClickListener(LoginActivity.this);
        linkResetPass.setOnClickListener(LoginActivity.this);
        linkRegister.setOnClickListener(LoginActivity.this);

    }

    public void onClick(View v) {
        useremail = editTextUserEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        requestQueue = Volley.newRequestQueue(LoginActivity.this);

        if (v == linkResetPass) {
                Intent intent = new Intent(LoginActivity.this, ResetPassActivity.class);
                startActivity(intent);
        }

        if (v == linkRegister) {
            Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(myIntent);
        }

        if (v == buttonLogin) {
            awesomeValidation.addValidation(this, R.id.editTextUserEmail, Patterns.EMAIL_ADDRESS, R.string.emailerror);
            awesomeValidation.addValidation(this, R.id.editTextPassword, "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}", R.string.passworderror);

            if (awesomeValidation.validate()) {
                final ProgressDialog loading = ProgressDialog.show(this, "Logging in", "Please wait...", false, false);
                //Displaying a progress dialog

                //Again creating the string request
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.LOGIN_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                loading.dismiss();
                                try {
                                    //Creating the json object from the response
                                    JSONObject jsonResponse = new JSONObject(response);
                                    //If it is successful
                                    if (jsonResponse.getString(Config.TAG_RESPONSE).substring(0,7).equalsIgnoreCase("Success")) {

                                        Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_LONG).show();

                                        // Get and set MyUserID
                                        MyUID=Long.valueOf(jsonResponse.getString(Config.TAG_RESPONSE).substring(7));
                                        //Store UserID in Shared Preferences
                                        editSharedpreferences=sharedpreferences.edit();
                                        editSharedpreferences.putLong(UserID, MyUID);
                                        editSharedpreferences.putString(useremail, MyEmailID);
                                        editSharedpreferences.apply();

                                        loggedIn=Boolean.TRUE;
                                        //Store login Status in Shared Preferences
                                        editSharedpreferences= sharedpreferences.edit();
                                        editSharedpreferences.putBoolean(LoggedinStatus, loggedIn);
                                        editSharedpreferences.apply();

                                        // Load Data
                                        DataLoadOnStart();
                                        AllWayPoints();

                                        try {
                                            sleep(5000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }

                                        //Load MapsActivity if user is on an active trip else move him to trip set up activity
                                       for (int i = 0; i < MainActivity.masterTable.getMasterTable().size(); i++){
                                           String TripStage = MainActivity.masterTable.getMasterTable().get(i).getTripStage();
                                           if (TripStage.equalsIgnoreCase("Active")) {
                                               if (CurrentTripID == 0) {
                                                   CurrentTripID = MainActivity.masterTable.getMasterTable().get(i).getTripID();
                                                   //Save Active Trip in Shared Preferences
                                                   editSharedpreferences = sharedpreferences.edit();
                                                   editSharedpreferences.putLong(TripID, CurrentTripID);
                                                   editSharedpreferences.apply();
                                               }
                                               Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                                               startActivity(intent);
                                               break;
                                           } else {
                                               if (CurrentTripID != 0) {
                                                   String nameofTrip = MainActivity.masterTable.getMasterTable().get(i).getTripName();
                                                   Toast.makeText(LoginActivity.this, "Trip " + nameofTrip + " is over", Toast.LENGTH_SHORT).show();
                                               }
                                               Intent intent = new Intent(LoginActivity.this, TripInitActivity.class);
                                               startActivity(intent);
                                               break;
                                           }
                                       }
                                    } else {
                                        //If not successful user may already have registered
                                        Toast.makeText(LoginActivity.this, "Email ID / Password does not match OR User not verified. Please try again", Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                loading.dismiss();
                                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        //Adding the parameters to the request
                        params.put(Config.KEY_USEREMAIL, useremail);
                        params.put(Config.KEY_PASSWORD, password);
                        return params;
                    }
                };
                //Adding request the the queue
                requestQueue.add(stringRequest);
            }
        }
    }
}