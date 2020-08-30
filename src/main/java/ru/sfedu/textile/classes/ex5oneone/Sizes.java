package ru.sfedu.textile.classes.ex5oneone;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static ru.sfedu.textile.constants.Constants.INT_UNSIGNED;
import static ru.sfedu.textile.constants.Constants.ONEONE_SIZES;

@Entity
@Table(name=ONEONE_SIZES)
public class Sizes {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(columnDefinition = INT_UNSIGNED)
    private int length;
    @NotNull
    @Column(columnDefinition = INT_UNSIGNED)
    private int width;
    @NotNull
    @Column(columnDefinition = INT_UNSIGNED)
    private int height;

    // Constructor
    public Sizes() {}

    public Sizes(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }


    // Methods
    public void setId(long newVar) {
        id = newVar;
    }

    public long getId() {
        return id;
    }

    public void setLength(int newVar) {
        length = newVar;
    }

    public int getLength() {
        return length;
    }

    public void setWidth(int newVar) {
        width = newVar;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int newVar) {
        height = newVar;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Length:" + getLength() +
                ", Width:" + getWidth() +
                ", Height:" + getHeight();
    }

}
