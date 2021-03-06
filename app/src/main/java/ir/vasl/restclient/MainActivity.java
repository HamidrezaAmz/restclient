package ir.vasl.restclient;


import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArrayMap;
import ir.vasl.library.RestClient;

import ir.vasl.library.enums.AuthType;

import ir.vasl.library.enums.RequestBodyType;
import ir.vasl.library.response.ResponseJsonHandler;
import ir.vasl.library.utils.RequestParams;


public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private RestClient restClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRestClient();

        findViewById(R.id.text).setOnClickListener(view -> {
            RequestParams params = new RequestParams(RequestBodyType.FormData);
            params.put("page", "1");





            restClient.POST("test url", "", new RequestParams(), new ResponseJsonHandler() {
                        @Override
                        protected void onSuccess(JSONObject result) {
                            Log.d(TAG, "response " + result);
                        }

                        @Override
                        protected void onSuccess(JSONArray result) {

                        }

                        @Override
                        protected void onSuccess(String result) {

                        }

                        @Override
                        public void onFailure(int errorCode, String errorMsg) {
                            Log.d(TAG, "onFailure " + errorMsg);
                        }
                    }
            );
        });
    }

    private void initRestClient() {
        ArrayMap<String, String> header = new ArrayMap<>();
        header.put("appid", "c3bdf6c5-508f-48ae-9af4-243a24072e31");
        header.put("accept-language", "en");
        restClient = new RestClient.Builder(this)
                .setAuthorization("http://sandbox.vaslapp.com/oauth/token",
                        "c3bdf6c5-508f-48ae-9af4-243a24072e31", "LnDbEo3yDDcswKMC3h4H", AuthType.BASIC_AUTH)
                .setUserInfo("android-XoaM8ODAYVcKnB16ob8N", "DOI0qOIa0KT6ViYmS1k6")
                .setHeader(header)
                .build();


    }
}
