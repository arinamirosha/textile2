package ru.sfedu.textile.classes.ex5manyone;

import ru.sfedu.textile.constants.Category;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static ru.sfedu.textile.constants.Constants.INT_UNSIGNED;
import static ru.sfedu.textile.constants.Constants.MANYONE_TEXTILE_PRODUCT;

@Entity(name=MANYONE_TEXTILE_PRODUCT)
@Table(name=MANYONE_TEXTILE_PRODUCT)
public class TextileProduct implements Serializable {

  // Fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(unique = true)
  private String article;
  private String name;
  @Column(columnDefinition = INT_UNSIGNED)
  private int price;
  @Column(columnDefinition = INT_UNSIGNED)
  private int quantity;
  @Enumerated(value = EnumType.STRING)
  private Category category;
  @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
//  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
  private List<Document> documents = new ArrayList<>();

  // Constructor
  public TextileProduct() {}

  public TextileProduct(String article, String name, int price, int quantity, Category category) {
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

  public void setDocuments(List<Document> newVar) {
    documents = newVar;
  }

  public List<Document> getDocuments() {
    return documents;
  }

  public String toString() {
    return "Id:" + getId() +
            ", Article:" + getArticle() +
            ", Name:" + getName() +
            ", Price:" + getPrice() +
            ", Quantity:" + getQuantity() +
            ", Category:" + getCategory() +
            ", Documents:" + getDocuments();
  }
}
