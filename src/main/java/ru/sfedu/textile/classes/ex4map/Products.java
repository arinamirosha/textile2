package ru.sfedu.textile.classes.ex4map;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.io.Serializable;

import static ru.sfedu.textile.constants.Constants.INT_UNSIGNED;
import static ru.sfedu.textile.constants.Constants.MAP_PRODUCTS;

@Embeddable
@Table(name=MAP_PRODUCTS)
public class Products implements Serializable {

  // Fields
  private String name;
  @Column(columnDefinition = INT_UNSIGNED)
  private int price;

  // Constructor
  public Products() {}

  public Products(String name, int price) {
    this.name = name;
    this.price = price;
  }

  // Methods

  public void setName(String newVar) {
    name = newVar;
  }

  public String getName() { return name; }

  public void setPrice(int newVar) {
    price = newVar;
  }

  public int getPrice() {
    return price;
  }

  public String toString() {
    return "Name:" + getName() +
            ", Price:" + getPrice();
  }

}
