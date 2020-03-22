package de.letsdev.products.dico.scanner.backend.ws.dto;

import de.letsdev.products.dico.scanner.backend.db.Device;

public class Position {

    float lon;

    float lat;

    float accuracy;

    String timestamp;

    boolean HasPositiveResult;

    Device device;

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isHasPositiveResult() {
        return HasPositiveResult;
    }

    public void setHasPositiveResult(boolean hasPositiveResult) {
        HasPositiveResult = hasPositiveResult;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
