package com.innovify.innofyapp;

import android.location.Location;

import java.io.Serializable;

/**
 * Serializable object for defining route attributes
 */
public class LocationBean implements Serializable{

    private String id = "0";

    private double lat;

    private double lon;

    private long timeStamp;

    public LocationBean(Location location) {
        lat = location.getLatitude();
        lon = location.getLongitude();
        timeStamp = System.currentTimeMillis();
    }

    public LocationBean(String id, double lat, double lon, long timeStamp) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.timeStamp = timeStamp;
    }

    public LocationBean() {
        this.id = "0";
        this.lat = 0.0;
        this.lon = 0.0;
        this.timeStamp = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
