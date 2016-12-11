package com.navigeto.navigeto;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.Patterns;
import android.view.LayoutInflater;
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
import static com.navigeto.navigeto.MainActivity.editSharedpreferences;
import static com.navigeto.navigeto.MainActivity.loggedIn;
import static com.navigeto.navigeto.MainActivity.sharedpreferences;
import static java.lang.Thread.sleep;


public class ResetPassActivity extends AppCompatActivity implements View.OnClickListener  {

    private android.support.v7.widget.AppCompatEditText editTextUserEmail;
    private android.support.v7.widget.AppCompatEditText editTextPassword;
    private android.support.v7.widget.AppCompatEditText editTextConfirmOtp;

    private AppCompatButton buttonResetPassword;
    private AppCompatButton buttonConfirm;

    //Volley RequestQueue
    private RequestQueue requestQueue;

    //String variables to hold username password and phone
    private String useremail;
    private String password;
    private AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpass);

        TextView myTextView = (TextView) findViewById(R.id.textView);
        Typeface typeface= Typeface.createFromAsset(getAssets(), "ARESSENCE.ttf");
        myTextView.setTypeface(typeface);

        //Initializing Views
        editTextUserEmail = (AppCompatEditText) findViewById(R.id.editTextUserEmail);
        editTextPassword = (AppCompatEditText) findViewById(R.id.editTextPassword);

        buttonResetPassword= (AppCompatButton) findViewById(R.id.buttonResetPassword);

        //Initializing the RequestQueue
        requestQueue = Volley.newRequestQueue(ResetPassActivity.this);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Adding a listener to button
        buttonResetPassword.setOnClickListener(ResetPassActivity.this);
    }

    private void confirmOtp() throws JSONException {
        //Creating a LayoutInflater object for the dialog box
        LayoutInflater li = LayoutInflater.from(this);
        //Creating a view to get the dialog box
        View confirmDialog = li.inflate(R.layout.dialog_confirm, null);

        //Initizliaing confirm button fo dialog box and edittext of dialog box
        buttonConfirm = (AppCompatButton) confirmDialog.findViewById(R.id.buttonConfirm);
        editTextConfirmOtp = (AppCompatEditText) confirmDialog.findViewById(R.id.editTextOtp);

        //Creating an alertdialog builder
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        //Adding our dialog box to the view of alert dialog
        alert.setView(confirmDialog);

        //Creating an alert dialog
        final AlertDialog alertDialog = alert.create();

        //Displaying the alert dialog
        alertDialog.show();

        //On the click of the confirm button from alert dialog
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hiding the alert dialog
                alertDialog.dismiss();

                //Displaying a progressbar
                final ProgressDialog loading = ProgressDialog.show(ResetPassActivity.this, "Authenticating", "Please wait while we check the entered code", false,false);

                //Getting the user entered otp from edittext
                final String otp = editTextConfirmOtp.getText().toString().trim();

                //Creating an string request
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.CONFIRM_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //if the server response is success
                                if(response.equalsIgnoreCase("success")){
                                    loggedIn=Boolean.TRUE;
                                    //Store login Status in Shared Preferences
                                    editSharedpreferences= sharedpreferences.edit();
                                    editSharedpreferences.putBoolean(LoggedinStatus, loggedIn);
                                    editSharedpreferences.apply();
                                    //dismissing the progressbar
                                    loading.dismiss();
                                    initiateApp();

                                }else{
                                    //Displaying a toast if the otp entered is wrong
                                    Toast.makeText(ResetPassActivity.this,"Wrong OTP Please Try Again",Toast.LENGTH_LONG).show();
                                    try {
                                        //Asking user to enter otp again
                                        confirmOtp();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                alertDialog.dismiss();
                                Toast.makeText(ResetPassActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        //Adding the parameters otp and username
                        params.put(Config.KEY_OTP, otp);
                        params.put(Config.KEY_USEREMAIL, useremail);
                        return params;
                    }
                };

                //Adding the request to the queue
                requestQueue.add(stringRequest);
            }
        });
    }

    private void ResetPass() {
        //Getting user data
        useremail = editTextUserEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.editTextUserEmail, Patterns.EMAIL_ADDRESS, R.string.emailerror);


        if (awesomeValidation.validate()) {
            //Displaying a progress dialog
            Toast.makeText(this, "new OTP will be sent to your registered number", Toast.LENGTH_LONG).show();

            final ProgressDialog loading = ProgressDialog.show(this, "new OTP will be sent to your registered number", "Please wait...", false, false);

            //Again creating the string request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.RESETPASS_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            loading.dismiss();

                            try {
                                //Creating the json object from the response
                                JSONObject jsonResponse = new JSONObject(response);
                                //If it is success
                                if(jsonResponse.getString(Config.TAG_RESPONSE).substring(0,7).equalsIgnoreCase("Success")){
                                    // Get and set MyUserID
                                    MyUID=Long.valueOf(jsonResponse.getString(Config.TAG_RESPONSE).substring(7));
                                    //Store UserID in Shared Preferences
                                    editSharedpreferences=sharedpreferences.edit();
                                    editSharedpreferences.putLong(UserID, MyUID);
                                    editSharedpreferences.putString(useremail, MyEmailID);
                                    editSharedpreferences.apply();

                                    //Asking user to confirm otp
                                    confirmOtp();
                                }else{
                                    //If not successful user may already have registered
                                    Toast.makeText(ResetPassActivity.this, "Email ID or Phone number is already in use", Toast.LENGTH_LONG).show();
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
                            Toast.makeText(ResetPassActivity.this, error.getMessage(),Toast.LENGTH_LONG).show();
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

    private void initiateApp() {
        if (loggedIn == true) {
            DataLoadOnStart();
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            AllWayPoints();
            //UsersOnTrip();

            for (int i = 0; i < MainActivity.masterTable.getMasterTable().size(); i++) {

                String TripStage = MainActivity.masterTable.getMasterTable().get(i).getTripStage();
                if (TripStage.equalsIgnoreCase("Active")) {
                    if (CurrentTripID == 0) {
                        CurrentTripID = MainActivity.masterTable.getMasterTable().get(i).getTripID();
                        //Save Active Trip in Shared Preferences
                        editSharedpreferences = sharedpreferences.edit();
                        editSharedpreferences.putLong(TripID, CurrentTripID);
                        editSharedpreferences.apply();
                    }
                    Intent intent = new Intent(ResetPassActivity.this, MapsActivity.class);
                    startActivity(intent);
                    break;
                } else {
                    if (CurrentTripID != 0) {
                        String nameofTrip = MainActivity.masterTable.getMasterTable().get(i).getTripName();
                        Toast.makeText(this, "Trip " + nameofTrip + " is over", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(ResetPassActivity.this, TripInitActivity.class);
                    startActivity(intent);
                    break;
                }
            }
        } else {
            Intent intent = new Intent(ResetPassActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        //Calling register method on register button click
        ResetPass();
    }

}
