package ru.sfedu.textile.classes.ex2;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static ru.sfedu.textile.constants.Constants.*;

@Entity
@Table(name=TEST_ENTITY)
public class TestEntity implements Serializable {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private Date datecreated;
    @Column(name=CHECK)
    private boolean check;
    @Embedded
    private Address address;

    // Constructor
    public TestEntity () {}

    public TestEntity (String name, String description, boolean check, Address address) {
        this.name = name;
        this.description = description;
        this.datecreated = new Date();
        this.check = check;
        this.address = address;
    }

    // Methods
    public void setId(long newVar) {
        id = newVar;
    }

    public long getId() {
        return id;
    }

    public void setName(String newVar) {
        name = newVar;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String newVar) {
        description = newVar;
    }

    public String getDescription() {
        return description;
    }

    public void setDatecreated(Date newVar) {
        datecreated = newVar;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setCheck(boolean newVar) {
        check = newVar;
    }

    public boolean getCheck() {
        return check;
    }

    public void setAddress(Address newVar) {
        address = newVar;
    }

    public Address getAddress() {
        return address;
    }


    public String toString() {
        return "Id:" + id +
                ", Name:" + name +
                ", Description:" + description +
                ", Datecreated:" + datecreated +
                ", Check:" + check +
                ", Address: " + address;
    }
}
