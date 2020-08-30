package ru.sfedu.textile.classes;

import ru.sfedu.textile.constants.Category;

import javax.persistence.*;
import java.io.Serializable;

import static ru.sfedu.textile.constants.Constants.INT_UNSIGNED;
import static ru.sfedu.textile.constants.Constants.TEXTILE_PRODUCT_EX4;

@Entity
@Table(name=TEXTILE_PRODUCT_EX4)
public class TextileProductEx4 implements Serializable {

  // Fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected long id;
  @Column(unique = true)
  protected String article;
  protected String name;
  @Column(columnDefinition = INT_UNSIGNED)
  protected int price;
  @Column(columnDefinition = INT_UNSIGNED)
  protected int quantity;
  @Enumerated(value = EnumType.STRING)
  protected Category category;

  // Constructor
  public TextileProductEx4() {}

  public TextileProductEx4(String article, String name, int price, int quantity, Category category) {
    this.article = article;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.category = category;
  }

  // Methods
  public void setId(long newVar) {
    id = newVar;
  }

  public long getId() {
    return id;
  }

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

  public void setQuantity(int newVar) {
    quantity = newVar;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setCategory(Category newVar) {
    category = newVar;
  }

  public Category getCategory() {
    return category;
  }

  public String toString() {
    return "Id:" + getId() +
            ", Article:" + getArticle() +
            ", Name:" + getName() +
            ", Price:" + getPrice() +
            ", Quantity:" + getQuantity() +
            ", Category:" + getCategory();
  }
}
