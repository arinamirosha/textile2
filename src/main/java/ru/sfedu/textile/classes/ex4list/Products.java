package ru.sfedu.textile.classes.ex4list;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.io.Serializable;

import static ru.sfedu.textile.constants.Constants.INT_UNSIGNED;
import static ru.sfedu.textile.constants.Constants.LIST_PRODUCTS;

@Embeddable
@Table(name=LIST_PRODUCTS)
public class Products implements Serializable {

  // Fields
  @Column(nullable = false)
  private String article;
  @Column(nullable = false)
  private String name;
  @Column(columnDefinition = INT_UNSIGNED, nullable = false)
  private int price;

  // Constructor
  public Products() {}

  public Products(String article, String name, int price) {
    this.article = article;
    this.name = name;
    this.price = price;
  }

  // Methods
  public void setArticle(String newVar) {
    article = newVar;
  }

  public String getArticle() {
    return article;
  }

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
    return "Article:" + getArticle() +
            ", Name:" + getName() +
            ", Price:" + getPrice();
  }

}
