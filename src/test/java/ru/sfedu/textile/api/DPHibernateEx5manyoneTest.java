package ru.sfedu.textile.api;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ru.sfedu.textile.classes.ex5manyone.Document;
import ru.sfedu.textile.classes.ex5manyone.TextileProduct;
import ru.sfedu.textile.constants.Category;
import ru.sfedu.textile.constants.TypeOfDoc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static ru.sfedu.textile.constants.Category.*;
import static ru.sfedu.textile.constants.Category.pillowcase;
import static ru.sfedu.textile.constants.TypeOfDoc.*;
import static ru.sfedu.textile.constants.TypeOfDoc.sale;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DPHibernateEx5manyoneTest {

    public static DPHibernateEx5manyone dp = new DPHibernateEx5manyone();
    private static Document doc = new Document();
    private static List<Document> docs = new ArrayList<>();
    private static int i;
    private static String article;
    private static String name;
    private static int price;
    private static int quantity;
    private static Category category;
    private static TypeOfDoc type;

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
    private static void setRandomArticle(){
        String articleLetterArr[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t"};
        String articleArr[] = new String[20];
        for (int i=0; i<20; i++) articleArr[i]=(i+1)*10+"-"+articleLetterArr[i];
        Random random = new Random();
        int num = random.nextInt(20);
        article = articleArr[num];
    }
    private static void setRandomIdoc(){
        int iArr[] = {1,2,3,4,5};
        Random random = new Random();
        int num = random.nextInt(5);
        i = iArr[num];
    }
    private static void setRandomTypeOfDoc(){
        TypeOfDoc typeArr[] = {sale,writeoff,posting};
        Random random = new Random();
        int num = random.nextInt(3);
        type = typeArr[num];
    }
    private static void setListOfDocuments(int count){
        if (docs.size()!=0) docs.clear();
        for (int i = 0; i < count; i++) {
            setRandomTypeOfDoc();
            doc = new Document(i+1,type);
            docs.add(doc);
        }
    }

    @Test
    public void addProduct() {
        for (int i=1; i<=20; i++) {
            setTextileProduct(i);
            dp.addProduct(article, name, price, quantity, category);
        }
    }

    @Test
    public void getProductByArticle() {
        setRandomArticle();
        dp.getProductByArticle(article);
    }

    @Test
    public void issueSaleWriteoffPosting() {
        for (int j = 0; j < 10; j++) {
            setRandomIdoc();
            setListOfDocuments(i);
            setRandomArticle();
            dp.issueSaleWriteoffPosting(article,docs);
        }
    }


    @Test
    public void compareRuntime() {
        dp.compareRuntime();
    }
}