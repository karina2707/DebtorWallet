package com.bignerdranch.android.debtorwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bignerdranch.android.debtorwallet.moduls.Debtor;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {
    private static final String WALLET_URL = "http://127.0.0.1:8000/";
    private static final String TAG = "MainActivity";
    private static String currentDebtor;
    Button bOk;
    EditText edPutId;

    List<Debtor> debtors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        debtors = new ArrayList<>();

        bOk = findViewById(R.id.bOk);
        edPutId = findViewById(R.id.etPutId);
        bOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                currentDebtor = edPutId.getText().toString(); // getting id
            }

        });
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(WALLET_URL, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray wallet = jsonObject.getJSONArray("wallet"); //[JsonObject, JsonObject, ...
                    Log.i(TAG, "Wallet: " + wallet.toString());
                    debtors.addAll(Debtor.fromJsonArray(wallet));
                    Log.i(TAG, "Debtors: " + debtors.size());
                } catch (JSONException e) {
                    Log.d(TAG, "Hit json exception", e);
                }

            }

            @Override

            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });


    }


}