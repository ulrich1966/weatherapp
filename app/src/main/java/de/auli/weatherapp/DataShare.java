package de.auli.weatherapp;

import de.auli.weatherapp.model.Result;

public class DataShare {
    private static DataShare dataShare = new DataShare();
    private Result result;

    private DataShare(){}

    public static DataShare getInstance(){
        if(dataShare == null){
            dataShare = new DataShare();
        }
        return dataShare;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
