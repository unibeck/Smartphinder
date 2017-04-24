package com.unibeck.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Inventory {

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @NotNull
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "smartphone_id", nullable = false)
    private Smartphone smartphone;

    @NotNull
    @OneToOne
    private Location location;

    @NotNull
    private int stock;

    public Inventory() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Smartphone getSmartphone() {
        return smartphone;
    }

    public void setSmartphone(Smartphone smartphone) {
        this.smartphone = smartphone;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getNumInStock() {
        return stock;
    }

    public void setNumInStock(int stock) {
        this.stock = stock;
    }

    public Inventory withSmartphone(Smartphone smartphone) {
        this.smartphone = smartphone;
        return this;
    }

    public Inventory withLocation(Location location) {
        this.location = location;
        return this;
    }

    public Inventory withStock(int stock) {
        this.stock = stock;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Inventory inventory = (Inventory) o;

        if (stock != inventory.stock) return false;
        if (id != null ? !id.equals(inventory.id) : inventory.id != null) return false;
        if (smartphone != null ? !smartphone.equals(inventory.smartphone) : inventory.smartphone != null) return false;
        return location != null ? location.equals(inventory.location) : inventory.location == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (smartphone != null ? smartphone.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + stock;
        return result;
    }
}
