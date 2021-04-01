package com.bignerdranch.android.debtorwallet.moduls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Debtor {
    private int debtorId;
    private String name;
    private int debtAmount;

    public Debtor(JSONObject jsonObject) throws JSONException {
        debtorId = jsonObject.getInt("debtorId");
        name = jsonObject.getString("name");
        debtAmount = jsonObject.getInt("debtAmount");
    }

    public int getDebtorId() {
        return debtorId;
    }
    public String getName() {
        return name;
    }
    public int getDebtAmount() {
        return debtAmount;
    }
    public static List<Debtor> fromJsonArray(JSONArray debtorJsonArray) throws JSONException {
        List<Debtor> debtors = new ArrayList<>();
        for (int i = 0; i < debtorJsonArray.length(); i++) {
            debtors.add(new Debtor(debtorJsonArray.getJSONObject(i)));
        }
        return debtors;
    }



}
