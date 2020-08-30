package ru.sfedu.textile.api;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ru.sfedu.textile.classes.TextileProductEx4;
import ru.sfedu.textile.classes.ex4list.Products;
import ru.sfedu.textile.classes.ex4list.Sales;
import ru.sfedu.textile.constants.Category;

import java.util.*;

import static ru.sfedu.textile.constants.Category.*;
import static ru.sfedu.textile.constants.Category.pillowcase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DPHibernateEx4listTest {

    public static DPHibernateEx4list dp = new DPHibernateEx4list();
    private static long id;
    private static int i;
    private static String article;
    private static String[] articles;
    private static String name;
    private static int price;
    private static int quantity;
    private static Category category;

    private static void setTextileProduct(int i){
        String articleLetterArr[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t"};
        Category categoryArr[] = {bathrobe, shirt, socks, tights, underpants, linen, bathrobe, shirt, socks, tights,
                pillow, blanket, mattress, pillowcase, sheet, duvetcover, pillow, blanket, mattress, pillowcase
            };
        article=i*10+"-"+articleLetterArr[i-1];
        name="name"+i;
        price=i*100;
        if (i%5==0) quantity=0;
        else quantity=i*10;
        category=categoryArr[i-1];
    }
    private static void setRandomId(){
        int idArr[] = {1,2,3,4,5,6,7,8,9,10};
        Random random = new Random();
        int num = random.nextInt(10);
        id = idArr[num];
    }
    private static void setRandomArticle(){
        String articleLetterArr[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t"};
        String articleArr[] = new String[20];
        for (int i=0; i<20; i++) articleArr[i]=(i+1)*10+"-"+articleLetterArr[i];
        Random random = new Random();
        int num = random.nextInt(20);
        article = articleArr[num];
    }
    private static void setRandomI(){
        int iArr[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        Random random = new Random();
        int num = random.nextInt(20);
        i = iArr[num];
    }
    private static void setRandomIsale(){
        int iArr[] = {1,2,3,4,5};
        Random random = new Random();
        int num = random.nextInt(5);
        i = iArr[num];
    }
    private static void setRandomArrayOfArticles(int count){
        articles=new String[count];
        for (int i = 0; i < count; i++) {
            setRandomArticle();
            articles[i]=article;
        }
    }

    @Test
    public void addProduct() {
        for (int i=1; i<=20; i++) {
            setTextileProduct(i);
            dp.addProduct(article,name,price,quantity,category);
        }
    }

    @Test
    public void getProductByArticle() {
        setRandomArticle();
        dp.getProductByArticle(article);
    }

    @Test
    public void changeProduct() {
        setRandomI(); setTextileProduct(i);
        setRandomArticle();
        dp.changeProduct(article,name,price,quantity,category);
    }


    @Test
    public void b_issueSale() {
        for (int j = 0; j < 10; j++) {
            setRandomIsale();
            setRandomArrayOfArticles(i);
            dp.issueSale(articles);
        }
    }

    @Test
    public void getSale() {
        setRandomId();
        dp.getSale(id);
    }

    @Test
    public void updateSale() {
        setRandomIsale();
        setRandomArrayOfArticles(i);
        setRandomId();
        dp.updateSale(articles,id);
    }

    @Test
    public void deleteSale() {
        setRandomId();
        dp.deleteSale(id);
    }
}