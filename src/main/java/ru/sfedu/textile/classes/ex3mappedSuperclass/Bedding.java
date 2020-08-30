package ru.sfedu.textile.classes.ex3mappedSuperclass;

import ru.sfedu.textile.constants.Category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

import static ru.sfedu.textile.constants.Constants.*;

@Entity
@Table(name=MAPPED_BEDDDING)
public class Bedding extends TextileProduct implements Serializable {

  // Fields
  @Column(columnDefinition = INT_UNSIGNED)
  private int length;
  @Column(columnDefinition = INT_UNSIGNED)
  private int width;
  @Column(columnDefinition = INT_UNSIGNED)
  private int height;

  // Constructor
  public Bedding () {}

  public Bedding (String article, String name, int price, int quantity, Category category, int length, int width, int height) {
    super(article,name,price,quantity,category);
    this.length = length;
    this.width = width;
    this.height = height;
  }


  // Methods
  public void setLength(int newVar) {
    length = newVar;
  }

  public int getLength() {
    return length;
  }

  public void setWidth(int newVar) {
    width = newVar;
  }

  public int getWidth() {
    return width;
  }

  public void setHeight(int newVar) {
    height = newVar;
  }

  public int getHeight() {
    return height;
  }

  @Override
  public String toString() {
      return super.toString() +
              ", Length:" + getLength() +
              ", Width:" + getWidth() +
              ", Height:" + getHeight();
  }

}
