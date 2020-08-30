package ru.sfedu.textile.classes.ex2;

import javax.persistence.*;
import java.io.Serializable;

import static ru.sfedu.textile.constants.Constants.*;

@Embeddable
@Table(name=ADDRESS)
public class Address implements Serializable {

    // Fields
    private String country;
    private String city;
    @Column(columnDefinition = INT_UNSIGNED)
    private int postcode;

    // Constructor
    public Address () {}

    public Address (String country, String city, int postcode) {
        this.country = country;
        this.city = city;
        this.postcode = postcode;
    }

    // Methods
    public void setCountry(String newVar) {
        country = newVar;
    }

    public String getCountry() {
        return country;
    }

    public void setCity(String newVar) {
        city = newVar;
    }

    public String getCity() {
        return city;
    }

    public void setPostcode(int newVar) {
        postcode = newVar;
    }

    public int getPostcode() {
        return postcode;
    }


    public String toString() {
        return "Country:" + country +
                ", City:" + city +
                ", Postcode:" + postcode;
    }
}
