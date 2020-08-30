package ru.sfedu.textile.classes.ex3joinedTables;

import ru.sfedu.textile.constants.Category;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;

import static ru.sfedu.textile.constants.Constants.*;

@Entity
@PrimaryKeyJoinColumn(name = PRODUCT_ID)
@Table(name=JOINED_CLOTHES)
public class Clothes extends TextileProduct implements Serializable {

  // Fields
  private String size;

  // Constructor
  public Clothes () {}

  public Clothes (String article, String name, int price, int quantity, Category category, String size) {
    super(article,name,price,quantity,category);
    this.size = size;

  }

  // Methods
  public void setSize(String newVar) {
    size = newVar;
  }

  public String getSize() {
    return size;
  }

  @Override
  public String toString() {
        return super.toString() + ", Size:" + getSize();
    }

}
