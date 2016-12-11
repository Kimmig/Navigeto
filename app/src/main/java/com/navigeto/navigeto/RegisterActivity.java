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

import static com.navigeto.navigeto.MainActivity.Email;
import static com.navigeto.navigeto.MainActivity.LoggedinStatus;
import static com.navigeto.navigeto.MainActivity.MyEmailID;
import static com.navigeto.navigeto.MainActivity.MyPhone;
import static com.navigeto.navigeto.MainActivity.MyUID;
import static com.navigeto.navigeto.MainActivity.Phone;
import static com.navigeto.navigeto.MainActivity.UserID;
import static com.navigeto.navigeto.MainActivity.editSharedpreferences;
import static com.navigeto.navigeto.MainActivity.loggedIn;
import static com.navigeto.navigeto.MainActivity.sharedpreferences;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    //Creating views
    private android.support.v7.widget.AppCompatEditText editTextUserEmail;
    private android.support.v7.widget.AppCompatEditText editTextPassword;
    private android.support.v7.widget.AppCompatEditText editTextPhone;
    private android.support.v7.widget.AppCompatEditText editTextConfirmOtp;

    private AppCompatButton buttonRegister;
    private AppCompatButton buttonConfirm;
    private AppCompatButton linktoLogin;

    //Volley RequestQueue
    private RequestQueue requestQueue;

    //String variables to hold username password and phone
    private String useremail;
    private String password;
    private String phone;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView myTextView = (TextView) findViewById(R.id.textView);
        Typeface typeface= Typeface.createFromAsset(getAssets(), "ARESSENCE.ttf");
        myTextView.setTypeface(typeface);

        //Initializing Views
        editTextUserEmail = (AppCompatEditText) findViewById(R.id.editTextUserEmail);
        editTextPassword = (AppCompatEditText) findViewById(R.id.editTextPassword);
        editTextPhone = (AppCompatEditText) findViewById(R.id.editTextPhone);

        buttonRegister = (AppCompatButton) findViewById(R.id.buttonRegister);
        linktoLogin = (AppCompatButton) findViewById(R.id.linktoLogin);

        //Initializing the RequestQueue
        requestQueue = Volley.newRequestQueue(RegisterActivity.this);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Adding a listener to button
        buttonRegister.setOnClickListener(RegisterActivity.this);
        //linktoLogin.setOnClickListener(RegisterActivity.this);

        linktoLogin.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(myIntent);
            }
        });
    }

    //This method would confirm the otp
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
                final ProgressDialog loading = ProgressDialog.show(RegisterActivity.this, "Authenticating", "Please wait while we check the entered code", false,false);

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
                                }else{
                                    //Displaying a toast if the otp entered is wrong
                                    Toast.makeText(RegisterActivity.this,"Wrong OTP Please Try Again",Toast.LENGTH_LONG).show();
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
                                Toast.makeText(RegisterActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
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

    //this method will register the user
    private void register() {
        //Getting user data
        useremail = editTextUserEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();
        phone = editTextPhone.getText().toString().trim();

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.editTextUserEmail, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.editTextPhone, "^[0-9]{10}$", R.string.mobileerror);
        awesomeValidation.addValidation(this, R.id.editTextPassword, "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}", R.string.passworderror);

        if (awesomeValidation.validate()) {
            //Displaying a progress dialog
            final ProgressDialog loading = ProgressDialog.show(this, "Registering", "Please wait...", false, false);

            //Again creating the string request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.REGISTER_URL,
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
                                    MyEmailID=useremail;
                                    MyPhone=phone;
                                    //Store UserID in Shared Preferences
                                    editSharedpreferences=sharedpreferences.edit();
                                    editSharedpreferences.putLong(UserID, MyUID);
                                    editSharedpreferences.putString(Email, MyEmailID);
                                    editSharedpreferences.putString(Phone, MyPhone);
                                    editSharedpreferences.apply();
                                    //Asking user to confirm otp
                                    confirmOtp();
                                    Intent intent = new Intent(RegisterActivity.this, UpdateProfileActivity.class);
                                    startActivity(intent);

                                }else{
                                    //If not successful user may already have registered
                                    Toast.makeText(RegisterActivity.this, "Email ID or Phone number is already in use", Toast.LENGTH_LONG).show();
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
                            Toast.makeText(RegisterActivity.this, error.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //Adding the parameters to the request
                    params.put(Config.KEY_USEREMAIL, useremail);
                    params.put(Config.KEY_PASSWORD, password);
                    params.put(Config.KEY_PHONE, phone);
                    return params;
                }
            };
            //Adding request the the queue
            requestQueue.add(stringRequest);
        }
            }

    @Override
    public void onClick(View v) {
        //Calling register method on register button click
        register();
            }
}