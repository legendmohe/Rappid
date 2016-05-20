package com.legendmohe.rappid.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by legendmohe on 16/4/28.
 */
public class AreaDataProvider {
//    private AreaDataProvider() {
//    }
//
//    private static class LazyHolder {
//        private static final AreaDataProvider INSTANCE = new AreaDataProvider();
//    }
//
//    public static AreaDataProvider getInstance() {
//        return LazyHolder.INSTANCE;
//    }

    private static final String TAG = "AreaDataProvider";

    protected static Map<String, Map<String, Map<String, List<String>>>> sDataMap = new LinkedHashMap<>();

    public void initProvinceDatas(Context context) {

        if (sDataMap.size() != 0)
            return;

        String jsonString = loadJsonStringFromAsset(context);
        if (!TextUtils.isEmpty(jsonString)) {
            try {
                JSONObject countryObject = new JSONObject(jsonString);
                Iterator<String> countryIter = countryObject.keys();
                while (countryIter.hasNext()) {
                    String countryName = countryIter.next();

                    Map<String, Map<String, List<String>>> provinceMap = sDataMap.get(countryName);
                    if (provinceMap == null) {
                        provinceMap = new LinkedHashMap<>();
                        sDataMap.put(countryName, provinceMap);
                    }

                    JSONObject provinceObject = countryObject.getJSONObject(countryName);
                    Iterator<String> provinceIter = provinceObject.keys();
                    while (provinceIter.hasNext()) {
                        String provinceName = provinceIter.next();

                        Map<String, List<String>> cityMap = provinceMap.get(provinceName);
                        if (cityMap == null) {
                            cityMap = new LinkedHashMap<>();
                            provinceMap.put(provinceName, cityMap);
                        }

                        JSONObject cityObject = provinceObject.getJSONObject(provinceName);
                        Iterator<String> cityIter = cityObject.keys();
                        while (cityIter.hasNext()) {
                            String cityName = cityIter.next();

                            List<String> districtData = cityMap.get(cityName);
                            if (districtData == null) {
                                districtData = new ArrayList<>();
                                cityMap.put(cityName, districtData);
                            }

                            JSONArray districtObject = cityObject.getJSONArray(cityName);
                            for (int i = 0; i < districtObject.length(); i++) {
                                String districtName = districtObject.getString(i);
                                districtData.add(districtName);
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                Log.e(TAG, "initProvinceDatas: ", e);
            }
        }
    }

    private String loadJsonStringFromAsset(Context context) {
        AssetManager asset = context.getAssets();
        try {
            InputStream ins = asset.open("province_data.json");
            int size = ins.available();
            byte[] buffer = new byte[size];
            ins.read(buffer);
            ins.close();
            return new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, Map<String, Map<String, List<String>>>> getDataMap() {
        return sDataMap;
    }
}
