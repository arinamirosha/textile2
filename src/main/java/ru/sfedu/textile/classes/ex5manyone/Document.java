package ru.sfedu.textile.classes.ex5manyone;


import ru.sfedu.textile.constants.TypeOfDoc;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static ru.sfedu.textile.constants.Constants.INT_UNSIGNED;
import static ru.sfedu.textile.constants.Constants.MANYONE_DOCUMENT;

@Entity
@Table(name=MANYONE_DOCUMENT)
public class Document implements Serializable {

  // Fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @ManyToOne(fetch = FetchType.EAGER)
//  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  private TextileProduct product;
  @Column(columnDefinition = INT_UNSIGNED)
  private int qty;
  private Date date;
  @Enumerated(value = EnumType.STRING)
  private TypeOfDoc type;

  // Constructor
  public Document() {}

  public Document(int qty, TypeOfDoc type) {
    this.qty = qty;
    this.date = new Date();
    this.type = type;
  }

  // Methods
  public void setId(long newVar) {
    id = newVar;
  }

  public long getId() {
    return id;
  }

  public void setProduct(TextileProduct newVar) {
    product = newVar;
  }

  public TextileProduct getProduct() {
    return product;
  }

  public void setQty(int newVar) {
    qty = newVar;
  }

  public int getQty() {
    return qty;
  }

  public void setDate(Date newVar) {
    date = newVar;
  }

  public Date getDate() {
    return date;
  }

  public void setType(TypeOfDoc newVar) {
    type = newVar;
  }

  public TypeOfDoc getType() {
    return type;
  }

  public String toString() {
    return "Id:" + getId() +
//            ", Product:" + getProduct().getArticle() +
            ", Qty:" + getQty() +
            ", Date:" + getDate() +
            ", Type:" + getType();
  }

}
