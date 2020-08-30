package ru.sfedu.textile.api;

import org.hibernate.Session;
import ru.sfedu.textile.constants.Category;
import ru.sfedu.textile.constants.TypeOfDoc;

import java.util.List;

public interface IDPHibernateEx3 {

    void addProduct(String article, String name, int price, int quantity, Category category, String size);
    void addProduct(String article, String name, int price, int quantity, Category category, int length, int width, int height);

    Object getProductById(long id);
    Object getProductByArticle(String article);

    void deleteProductById(long id);
    void deleteProductByArticle(String article);

    void changeProduct(Object obj);
    void changeProduct(String article, String name, int price, int quantity, Category category, String size);
    void changeProduct(String article, String name, int price, int quantity, Category category, int length, int width, int height);

    void showAllLists();
    void showProductsWithZeroBalance();
    void showProductsByName(String name);

    void issueSaleWriteoffPosting(String product, int qty, TypeOfDoc type);


    // Helpful method

     <T> List<T> loadAllData(Class<T> type, Session session);
}
