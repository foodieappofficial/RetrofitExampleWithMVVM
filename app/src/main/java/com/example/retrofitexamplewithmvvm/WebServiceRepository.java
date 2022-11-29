package com.example.retrofitexamplewithmvvm;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class WebServiceRepository {

    Application application;
    private static OkHttpClient providesOkHttpClientBuilder(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        return httpClient.readTimeout(1200, TimeUnit.SECONDS)
                .connectTimeout(1200, TimeUnit.SECONDS).build();

    }


    List<ResultModel> webserviceResponseList = new ArrayList<>();
    public LiveData<List<ResultModel>> providesWebService() {
        final MutableLiveData<List<ResultModel>> data = new MutableLiveData<>();

        String response = "";
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(APIUrl.BASE_URL)

                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();

//            .addConverterFactory(ScalarsConverterFactory.create())

            //Defining retrofit api service
            APIService service = retrofit.create(APIService.class);
            //  response = service.makeRequest().execute().body();
            service.makeRequest().enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.isSuccessful()){
                        Log.d("Repository","Response::::"+response.body());
                        StaticJavaClass.variable = "ghgfh"+ response.body();

                        webserviceResponseList = parseJson(response.body());
                        data.setValue(webserviceResponseList);
                    }
                    else{
                        Log.e("responsefailed", "hahahahahah");
                    }

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d("Repository","Failed:::" + t.getMessage());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

        //  return retrofit.create(ResultModel.class);
        return  data;

    }


    private List<ResultModel> parseJson(String response) {

        List<ResultModel> apiResults = new ArrayList<>();

        JSONObject jsonObject;

        JSONArray jsonArray;

        try {
            jsonArray = new JSONArray(response);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                ResultModel mMovieModel = new ResultModel();
                //mMovieModel.setId(object.getString("id"));
                mMovieModel.setId(Integer.parseInt(object.getString("id")));
                mMovieModel.setTitle(object.getString("title"));
                mMovieModel.setBody(object.getString("body"));
                StaticJavaClass.variable = "ghgfh"+ apiResults.size();

                apiResults.add(mMovieModel);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i(getClass().getSimpleName(), String.valueOf(apiResults.size()));

        return apiResults;

    }
}
