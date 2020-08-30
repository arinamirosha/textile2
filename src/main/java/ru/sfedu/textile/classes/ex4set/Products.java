package ru.sfedu.textile.classes.ex4set;

import javax.persistence.*;
import java.io.Serializable;

import static ru.sfedu.textile.constants.Constants.INT_UNSIGNED;
import static ru.sfedu.textile.constants.Constants.SET_PRODUCTS;

@Embeddable
@Table(name=SET_PRODUCTS)
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


  @Override
  public boolean equals(Object obj) {

    if (this == obj) return true;
    if (obj == null || this.getClass()!=obj.getClass()) return false;

    Products prods = (Products) obj;

    return (article == prods.article || (article != null && article.equals(prods.article))) &&
            (name == prods.name || (name != null && name.equals(prods.name))) &&
            (price == prods.price);
  }

  @Override
  public int hashCode(){
      final int prime = 31;
      int result = 1;
      result = prime * result + ((article == null) ? 0 : article.hashCode());
      result = prime * result +((name == null) ? 0 : name.hashCode());
      result = prime * result + price;
      return result;
  }

}
