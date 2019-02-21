package de.auli.weatherapp.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.List;

public class Result extends Model {
    public static final String NAME = "result";

    private String id;
    private String name;
    private String cod;
    private String dt;
    private String base;
    private String visibility;

    private CoordMod coord;
    private List<WeatherMod> weather;
    private MainMod main;
    private WindMod wind;
    private CloudsMod clouds;
    private SysMod sys;

    private Bitmap bmp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public CoordMod getCoord() {
        return coord;
    }

    public void setCoord(CoordMod coord) {
        this.coord = coord;
    }

    public List<WeatherMod> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherMod> weather) {
        this.weather = weather;
    }

    public MainMod getMain() {
        return main;
    }

    public void setMain(MainMod main) {
        this.main = main;
    }

    public WindMod getWind() {
        return wind;
    }

    public void setWind(WindMod wind) {
        this.wind = wind;
    }

    public CloudsMod getClouds() {
        return clouds;
    }

    public void setClouds(CloudsMod clouds) {
        this.clouds = clouds;
    }

    public SysMod getSys() {
        return sys;
    }

    public void setSys(SysMod sys) {
        this.sys = sys;
    }

    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", cod='" + cod + '\'' +
                ", dt='" + dt + '\'' +
                ", base='" + base + '\'' +
                ", visibility='" + visibility + '\'' +
                ", coord=" + coord +
                ", weather=" + weather +
                ", main=" + main +
                ", wind=" + wind +
                ", clouds=" + clouds +
                ", sys=" + sys +
                '}';
    }
}
