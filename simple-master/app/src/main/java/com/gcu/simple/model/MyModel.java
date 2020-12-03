package com.gcu.simple.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MyModel extends ViewModel {
    private MutableLiveData<String> msg;
    private MutableLiveData<Integer> count;

    private  MutableLiveData<List<ImageInfo>> data;

    public MutableLiveData<Integer> getCount() {
        if (count == null) {
            count = new MutableLiveData<>();
        }
        return count;
    }

    public MutableLiveData<String> getMsg() {
        if (msg == null) {
            msg = new MutableLiveData<>();
        }
        return msg;
    }

    public MutableLiveData<List<ImageInfo>> getData() {
       if(data == null){
           data = new MutableLiveData<>();
       }
        return data;
    }
}
