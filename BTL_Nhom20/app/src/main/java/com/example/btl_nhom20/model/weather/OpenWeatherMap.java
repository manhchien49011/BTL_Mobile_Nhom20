package com.example.btl_nhom20.model.weather;

import java.util.List;

public class OpenWeatherMap {
    private Coord coord;
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private  Sys sys;
    private String base;
    private int visibility;
    private int dt;
    private int id;
    private int timezone;
    private String name;
    private int cod;

    public OpenWeatherMap(Coord coord, List<Weather> weather, Main main, Wind wind, Sys sys, String base, int visibility, int dt, int id, int timezone, String name, int cod) {
        this.coord = coord;
        this.weather = weather;
        this.main = main;
        this.wind = wind;
        this.sys = sys;
        this.base = base;
        this.visibility = visibility;
        this.dt = dt;
        this.id = id;
        this.timezone = timezone;
        this.name = name;
        this.cod = cod;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
}
