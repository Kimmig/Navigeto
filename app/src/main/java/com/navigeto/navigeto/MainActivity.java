package com.navigeto.navigeto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {


    TextView textView;
    TextView output;
    String strRole = "";

    DBHandler db = new DBHandler(this);
    String data;

    // User Preferences
    public static final String MyPREFERENCES = "MyPrefs";

    public static String MyUserName;
    public static Long MyUID;
    public static String MyEmailID;
    public static Long CurrentTripID;
    public static Boolean loggedIn = Boolean.FALSE;
    public static String MyPhone;
    public static boolean verified;
    public static String MyPicPath;
    public static boolean battSaver;

    public static final String Name = "nameKey";
    public static final String UserID = "myUserIDKey";
    public static final String Email = "emailKey";
    public static final String TripID = "tripIDKey";
    public static final String LoggedinStatus = "loggedinStatusKey";
    public static final String Phone = "PhoneNokey";

    public static SharedPreferences sharedpreferences;
    public static SharedPreferences.Editor editSharedpreferences;

    //    Trip data
    public static MasterTable masterTable;
    public static WayPoints wayPoints;
    public static Gson gson = new Gson();

    private static String readStream(InputStream is) {
        String str = "";
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while (i != -1) {
                bo.write(i);
                i = is.read();
            }
            str = bo.toString();
            return str;
        } catch (IOException e) {
            return "";
        }
    }


    public static String GetPHPData(String strurl) {
        URL url;
        String sss = "";
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(strurl);
            urlConnection = (HttpURLConnection) url
                    .openConnection();
            InputStream in = urlConnection.getInputStream();
            sss = readStream(in);
            return (sss);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return "";
    }



/*public static String GetPHPData(Context context,String strurl) {

        StringRequest stringRequest = new StringRequest(strurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    String result=response;
                         //Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                     }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(MainActivity.this,error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
return "";
    }*/


    public static void DataLoadOnStart() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String strJson = GetPHPData("http://ec2-54-235-56-230.compute-1.amazonaws.com/Beacon_S2D_On_Start.php?User_ID=" + Long.valueOf(MyUID));
                    //Log.d("mainact1", strJson);
                    masterTable = gson.fromJson(strJson, MasterTable.class);
                   /* for (int i = 0; i < masterTable.getMasterTable().size(); i++) {
                        Log.d("mainact1", masterTable.toString());
                    }*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public static void UsersOnTrip() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String strJson = GetPHPData("http://ec2-54-235-56-230.compute-1.amazonaws.com/Beacon_S2D_Users_On_Trip?User_ID=" + Long.valueOf(MyUID));
                    Log.d("mainact1", strJson);
                    UpdateLocation updateLocation = gson.fromJson(strJson, UpdateLocation.class);
                    List<MasterTable_> updateLocation_ = updateLocation.getUpdateLocation();
                    for (int i = 0; i < masterTable.getMasterTable().size(); i++) {
                        for (int i1 = 0; i1 < updateLocation_.size(); i1++) {
                            if ((masterTable.getMasterTable().get(i).getUserID().equals(updateLocation_.get(i1).getUserID())) &&
                                    (masterTable.getMasterTable().get(i).getTripID().equals(updateLocation_.get(i1).getTripID()))) {


                        /*        List<MasterTable_> mTable = masterTable.getMasterTable();
                                MasterTable_  masterTable_ =mTable[0];


                                List<UpdateLocation_> location = updateLocation.getUpdateLocation();

                                mTable.get(i).setTripName()=


                                mTable.set(I, location);

                                masterTable.getMasterTable().set(i, updateLocation_.get(i1));*/

                                //masterTable.getMasterTable().set(i, updateLocation_.get(i1));
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public static void AllWayPoints() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String strJson = GetPHPData("http://ec2-54-235-56-230.compute-1.amazonaws.com/Beacon_S2D_WP.php?User_ID=" + Long.valueOf(MyUID));
                    wayPoints = gson.fromJson(strJson, WayPoints.class);
                   /*for (int i = 0; i < wayPoints.getWayPoints().size(); i++) {
                        Log.d("mainact3", wayPoints.toString());
                    }*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView myTextView = (TextView) findViewById(R.id.textView);

        // Set Font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "ARESSENCE.ttf");
        myTextView.setTypeface(typeface);

        // Retrieve and apply settings
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Name)) {
            MyUserName = sharedpreferences.getString(Name, "");
        }
        if (sharedpreferences.contains(UserID)) {
            MyUID = sharedpreferences.getLong(UserID, 0);
        }
        if (sharedpreferences.contains(Email)) {
            MyEmailID = sharedpreferences.getString(Email, "");
        }
        if (sharedpreferences.contains(TripID)) {
            CurrentTripID = sharedpreferences.getLong(TripID, 0);
        }
        if (sharedpreferences.contains(LoggedinStatus)) {
            loggedIn = sharedpreferences.getBoolean(LoggedinStatus, false);
        }
        if (sharedpreferences.contains(Phone)) {
            MyPhone = sharedpreferences.getString(Phone, "");
        }

//To be removed in production.
        loggedIn = true;
        MyUID = Long.valueOf(1);


        if (loggedIn == true) {
            DataLoadOnStart();
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            AllWayPoints();
           // UsersOnTrip();


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
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(intent);
                    break;
                } else {
                    if (CurrentTripID != 0) {
                        String nameofTrip = MainActivity.masterTable.getMasterTable().get(i).getTripName();
                        Toast.makeText(this, "Trip " + nameofTrip + " is over", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(MainActivity.this, TripInitActivity.class);
                    startActivity(intent);
                    break;
                }
            }
        } else {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

    }
}


   /* Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
//                 Intent intent = new Intent(MainActivity.this, SetupActivity.class);
//                    startActivity(intent);
                }
            }
        };
        timerThread.start();*/
//        byte[] imageBytes = baos.toByteArray();
//      String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
//for (int i = 0; i < MainActivity.masterTable.getMasterTable().size(); i++) {

/*            String imageBytes = MainActivity.masterTable.getMasterTable().get(2).getLoRes();
           // Toast.makeText(this, imageBytes, Toast.LENGTH_SHORT).show();
            byte[] imageByteArray = Base64.decode(imageBytes, Base64.DEFAULT);
           Bitmap bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
            //byte[] data = Base64.decode(MainActivity.masterTable.getMasterTable().get(1).getHiRes(), Base64.DEFAULT);
            // String Image1= new String(data, "UTF-8");
          //  Toast.makeText(this, "Image " + i, Toast.LENGTH_SHORT).show();
            Glide
                    .with(this)
                    .load(bitmap)
                    .asBitmap()
                    .into(imageView);
*/
//        Glide.with(this).load(MainActivity.masterTable.getMasterTable().get(1).getHiRes()).into(imageView);
//        Glide.with(this).load(String.valueOf(filePath)).into(imageView);


