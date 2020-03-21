package de.letsdev.products.dico.scanner.backend.db;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.sql.Timestamp;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Timestamp timestamp;

    private float lon;

    private float lat;

    private float accuracy;

    @ManyToOne
    private Device device;

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public Timestamp getTimestamp() {

        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {

        this.timestamp = timestamp;
    }

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

    public Device getDevice() {

        return device;
    }

    public void setDevice(Device device) {

        this.device = device;
    }
}
