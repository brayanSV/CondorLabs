package com.user.brayan.condorlabs.caseuses;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class CustomGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        BufferedReader reader = new BufferedReader(value.charStream());
        JSONArray jsonResult = new JSONArray();
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        try {
            JSONObject json = new JSONObject(sb.toString());

            if (!json.isNull("teams")) {
                jsonResult = json.getJSONArray("teams");
            } else if (!json.isNull("results")) {
                jsonResult = json.getJSONArray("results");
            }
        } catch (JSONException e) {
            Log.e("datos", e.getMessage().toString());
        }

        try {
            T result = adapter.fromJson(jsonResult.toString());

            /*T result = adapter.read(jsonReader);
            Log.e("datos", result.toString());

            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw new JsonIOException("JSON document was not fully consumed.");
            }*/

            //T result = null;
            return result;
        } finally {
            value.close();
        }
    }
}
