package de.auli.weatherapp.model;

public class CoordMod extends Model {
    public static final String NAME = "coord";

    private String lon;
    private String lat;

    public void setLon(String lon) {
        this.lon = lon;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public String getLat() {
        return lat;
    }

    @Override
    public String toString() {
        return "CoordMod{" +
                "lon='" + lon + '\'' +
                ", lat='" + lat + '\'' +
                '}';
    }
}
