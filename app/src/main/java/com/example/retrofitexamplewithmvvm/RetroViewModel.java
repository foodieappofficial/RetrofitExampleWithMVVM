package com.example.retrofitexamplewithmvvm;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RetroViewModel extends AndroidViewModel {
    private final LiveData<List<ResultModel>>  retroObservable;
    WebServiceRepository webServiceRepository ;
    public  RetroViewModel(Application application){
        super(application);
        webServiceRepository = new WebServiceRepository();
        retroObservable = webServiceRepository.providesWebService();
    }



    public LiveData<List<ResultModel>> getProjectRetroListObservable() {
        return retroObservable;
    }


}