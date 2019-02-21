package de.auli.weatherapp.model;

public class MainMod extends Model {
    public static final String NAME = "main";

    private String temp;
    private String pressure;
    private String humidity;
    private String temp_min;
    private String temp_max;

    public String getTemp() {
        return this.convert(temp);
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTemp_min() {
        return this.convert(temp_min);
    }

    public void setTemp_min(String temp_min) {
        this.temp_min = temp_min;
    }

    public String getTemp_max() {
        return this.convert(temp_max);
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }

    private String convert(String value){
        Double temp = new Double(value) - 273.15;
        return ((Integer) temp.intValue()).toString();
    }

    @Override
    public String toString() {
        return "Main{" +
                "temp='" + temp + '\'' +
                ", pressure='" + pressure + '\'' +
                ", humidity='" + humidity + '\'' +
                ", temp_min='" + temp_min + '\'' +
                ", temp_max='" + temp_max + '\'' +
                '}';
    }
}
