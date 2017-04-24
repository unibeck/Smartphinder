package com.unibeck.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Location {

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private double longitude;

    @NotNull
    private double latitude;

    public Location() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Location withCity(String city) {
        this.city = city;
        return this;
    }

    public Location withState(String state) {
        this.state = state;
        return this;
    }

    public Location withLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Location withLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (Double.compare(location.longitude, longitude) != 0) return false;
        if (Double.compare(location.latitude, latitude) != 0) return false;
        if (id != null ? !id.equals(location.id) : location.id != null) return false;
        if (city != null ? !city.equals(location.city) : location.city != null) return false;
        return state != null ? state.equals(location.state) : location.state == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
