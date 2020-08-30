package ru.sfedu.textile.classes.ex5oneone;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

import static ru.sfedu.textile.constants.Constants.INT_UNSIGNED;
import static ru.sfedu.textile.constants.Constants.ONEONE_BEDDING_PRODUCT;

@Entity(name=ONEONE_BEDDING_PRODUCT)
@Table(name=ONEONE_BEDDING_PRODUCT)
public class Bedding implements Serializable {

  // Fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(unique = true)
  private String article;
  private String name;
  @Column(columnDefinition = INT_UNSIGNED)
  private int price;
  @OneToOne(fetch = FetchType.EAGER,
//          fetch = FetchType.LAZY, optional = false,
          cascade = CascadeType.PERSIST)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(unique = true)
  private Sizes sizes;

  // Constructor
  public Bedding() {}

  public Bedding(String article, String name, int price) {
    this.article = article;
    this.name = name;
    this.price = price;
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

  public void setSizes(Sizes newVar) {
    sizes = newVar;
  }

  public Sizes getSizes() {
    return sizes;
  }


  @Override
  public String toString() {
      return "Id:" + getId() +
              ", Article:" + getArticle() +
              ", Name:" + getName() +
              ", Price:" + getPrice() +
              ", Sizes:" + getSizes();
  }

}
