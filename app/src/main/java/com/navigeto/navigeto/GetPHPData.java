package com.navigeto.navigeto;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by meena on 5-12-16.
 */

public class GetPHPData{

public static String getData(final Context context, final String strurl)
        {

        StringRequest stringRequest = new StringRequest(strurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String result=response;
                     //   Toast.makeText(this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                      //  Toast.makeText(context,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return "";
    }
}
