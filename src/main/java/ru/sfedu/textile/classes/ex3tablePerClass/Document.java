package ru.sfedu.textile.classes.ex3tablePerClass;


import ru.sfedu.textile.constants.TypeOfDoc;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static ru.sfedu.textile.constants.Constants.*;

@Entity
@Table(name=PERCLASS_DOCUMENT)
public class Document implements Serializable {

  // Fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String product;
  @Column(columnDefinition = INT_UNSIGNED)
  private int qty;
  private Date date;
  @Enumerated(value = EnumType.STRING)
  private TypeOfDoc type;

  // Constructor
  public Document () {}

  public Document (String product, int qty, TypeOfDoc type) {
    this.product = product;
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

  public void setProduct(String newVar) {
    product = newVar;
  }

  public String getProduct() {
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
            ", Product:" + getProduct() +
            ", Qty:" + getQty() +
            ", Date:" + getDate() +
            ", Type:" + getType();
  }

}
