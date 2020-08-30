package ru.sfedu.textile.api;

import ru.sfedu.textile.classes.TextileProductEx4;
import ru.sfedu.textile.constants.Category;

public interface IDPHibernateEx4 {

    void addProduct(String article, String name, int price, int quantity, Category category);
    TextileProductEx4 getProductByArticle(String article);
    void changeProduct(TextileProductEx4 product);
    void changeProduct(String article, String name, int price, int quantity, Category category);

    void issueSale(String[] articles);
    Object getSale(long id);
    void updateSale(String[] articles, long id);
    void deleteSale(long id);

}
