package ru.sfedu.textile.classes.ex4map;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static ru.sfedu.textile.constants.Constants.*;

@Entity
@Table(name=MAP_SALES)
public class Sales implements Serializable {

  // Fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name=MAP_PRODUCTS)
  @MapKeyColumn(name=ARTICLE)
  private Map<String, Products> products = new HashMap<>();
  private Date date;
  private int sum;

  // Constructor
  public Sales() {}

  public Sales(Map<String, Products> products) {
    this.products = products;
    this.date = new Date();
    for(Map.Entry<String, Products> entry: products.entrySet())
      this.sum += entry.getValue().getPrice();
  }


  // Methods
  public void setId(long newVar) {
    id = newVar;
  }

  public long getId() {
    return id;
  }

  public void setProducts(Map<String, Products> newVar) {
    for(Map.Entry<String, Products> entry: newVar.entrySet())
      sum += entry.getValue().getPrice();
    products = newVar;
  }

  public Map<String, Products> getProducts() {
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
