package de.auli.weatherapp;

class WeatherData {
    private String name;
    private String description;
    private String icon;
    private Double temp;

    WeatherData(String name, String description, String icon, Double temp) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.temp = temp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public Double getTemp() {
        return temp;
    }
}
