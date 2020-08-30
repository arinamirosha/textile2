package ru.sfedu.textile.classes.ex4set;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static ru.sfedu.textile.constants.Constants.SET_PRODUCTS;
import static ru.sfedu.textile.constants.Constants.SET_SALES;

@Entity
@Table(name=SET_SALES)
public class Sales implements Serializable {

  // Fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name=SET_PRODUCTS)
  private Set<Products> products = new HashSet<>();
  private Date date;
  private int sum;

  // Constructor
  public Sales() {}

  public Sales(Set<Products> products) {
    this.products = products;
    this.date = new Date();
    this.sum = products.stream().mapToInt(Products::getPrice).sum();
  }


  // Methods
  public void setId(long newVar) {
    id = newVar;
  }

  public long getId() {
    return id;
  }

  public void setProducts(Set<Products> newVar) {
    sum = newVar.stream().mapToInt(Products::getPrice).sum();
    products = newVar;
  }

  public Set<Products> getProducts() {
    return products;
  }

  public void setDate(Date newVar) {
    date = newVar;
  }

  public Date getDate() {
    return date;
  }

  public void setSum(int newVar) {
    sum = newVar;
  }

  public int getSum() {
    return sum;
  }

  public String toString() {
    return "Id:" + getId() +
            ", Products:" + getProducts() +
            ", Date:" + getDate() +
            ", Sum:" + getSum();
  }

}
