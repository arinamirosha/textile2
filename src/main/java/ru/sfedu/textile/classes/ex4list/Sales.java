package ru.sfedu.textile.classes.ex4list;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static ru.sfedu.textile.constants.Constants.LIST_PRODUCTS;
import static ru.sfedu.textile.constants.Constants.LIST_SALES;

@Entity
@Table(name=LIST_SALES)
public class Sales implements Serializable {

  // Fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name=LIST_PRODUCTS)
  @OrderColumn
  private List<Products> products = new ArrayList<>();
  private Date date;
  private int sum;

  // Constructor
  public Sales() {}

  public Sales(List<Products> products) {
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

  public void setProducts(List<Products> newVar) {
    sum = newVar.stream().mapToInt(Products::getPrice).sum();
    products = newVar;
  }

  public List<Products> getProducts() {
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
