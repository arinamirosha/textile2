package ru.sfedu.textile.api;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ru.sfedu.textile.classes.ex3tablePerClass.Bedding;
import ru.sfedu.textile.classes.ex3tablePerClass.Clothes;
import ru.sfedu.textile.classes.ex3tablePerClass.Document;
import ru.sfedu.textile.constants.Category;
import ru.sfedu.textile.constants.TypeOfDoc;

import java.util.Date;
import java.util.Random;

import static ru.sfedu.textile.constants.Category.*;
import static ru.sfedu.textile.constants.Category.pillowcase;
import static ru.sfedu.textile.constants.TypeOfDoc.*;
import static ru.sfedu.textile.constants.TypeOfDoc.sale;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DPHibernateEx3TablePerClassTest {

    public static DPHibernateEx3TablePerClass dp = new DPHibernateEx3TablePerClass();
    private static long id;
    private static int i;
    private static String article;
    private static String name;
    private static int price;
    private static int quantity;
    private static Category category;
    private static String size;
    private static int length;
    private static int width;
    private static int height;
    private static String product;
    private static int qty;
    private static TypeOfDoc type;

    private static void setClothes(int i){
        String articleLetterArr[] = {"a","b","c","d","e","f","g","h","i","j"};
        String sizeArr[] = {"44","S","38-40","M-L","36","37-39","L","40","nosize","XS"};
        Category categoryArr[] = {bathrobe, shirt, socks, tights, underpants, linen, bathrobe, shirt, socks, tights};
        article=i*10+"-"+articleLetterArr[i-1];
        name="name"+i;
        price=i*100;
        if (i%5==0) quantity=0;
        else quantity=i*10;
        category=categoryArr[i-1];
        size=sizeArr[i-1];
    }
    private static void setBedding(int i){
        String articleLetterArr[] = {"k","l","m","n","o","p","q","r","s","t"};
        int lengthArr[] = {60,220,90,110,200,50,60,80,60,220};
        int widthArr[] = {40,60,70,80,90,30,40,50,40,60};
        int heightArr[] = {0,10,0,15,30,5,20,0,3,5};
        Category categoryArr[] = {pillow, blanket, mattress, pillowcase, sheet, duvetcover, pillow, blanket, mattress, pillowcase};
        article=i*10+i+"-"+articleLetterArr[i-1];
        name="name"+i+i;
        price=i*100;
        if (i%5==0) quantity=0;
        else quantity=i*10;
        category=categoryArr[i-1];
        length = lengthArr[i-1];
        width = widthArr[i-1];
        height = heightArr[i-1];
    }
    private static void setDocument(int i){
        TypeOfDoc typeArr[] = {sale,writeoff,posting,sale,writeoff,posting,sale,writeoff,posting,sale};
        setRandomArticle();
        product=article;
        qty=i+i;
        type=typeArr[i-1];
    }
    private static void setRandomId(){
        int idArr[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        Random random = new Random();
        int num = random.nextInt(20);
        id = idArr[num];
    }
    private static void setRandomArticle(){
        String articleLetterArr[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t"};
        String articleArr[] = new String[20];
        for (int i=0; i<10; i++) articleArr[i]=(i+1)*10+"-"+articleLetterArr[i];
        for (int i=10; i<20; i++) articleArr[i]=(i-9)*10+(i-9)+"-"+articleLetterArr[i];
        Random random = new Random();
        int num = random.nextInt(20);
        article = articleArr[num];
    }
    private static void setRandomClothesArticle(){
        String articleLetterArr[] = {"a","b","c","d","e","f","g","h","i","j"};
        String articleArr[] = new String[10];
        for (int i=0; i<10; i++) articleArr[i]=(i+1)*10+"-"+articleLetterArr[i];
        Random random = new Random();
        int num = random.nextInt(10);
        article = articleArr[num];
    }
    private static void setRandomBeddingArticle(){
        String articleLetterArr[] = {"k","l","m","n","o","p","q","r","s","t"};
        String articleArr[] = new String[10];
        for (int i=0; i<10; i++) articleArr[i]=(i+1)*10+(i+1)+"-"+articleLetterArr[i];
        Random random = new Random();
        int num = random.nextInt(10);
        article = articleArr[num];
    }
    private static void setRandomName(){
        Random random = new Random();
        int num = random.nextInt(10);
        if (num==0) num=1;
        name = "name"+num;
    }
    private static void setRandomI(){
        int iArr[] = {1,2,3,4,5,6,7,8,9,10};
        Random random = new Random();
        int num = random.nextInt(10);
        i = iArr[num];
    }

    @Test
    public void addProduct() {
        for (int i=1; i<=10; i++) {
            setClothes(i);
            dp.addProduct(article,name,price,quantity,category,size);
        }
        for (int i=1; i<=10; i++) {
            setBedding(i);
            dp.addProduct(article,name,price,quantity,category,length,width,height);
        }
    }

    @Test
    public void getProductById() {
        setRandomId();
        dp.getProductById(id);
    }

    @Test
    public void getProductByArticle() {
        setRandomArticle();
        dp.getProductByArticle(article);
    }

    @Test
    public void deleteProductById() {
        setRandomId();
        dp.deleteProductById(id);
    }

    @Test
    public void deleteProductByArticle() {
        setRandomArticle();
        dp.deleteProductByArticle(article);
    }

    @Test
    public void changeProduct() {
        setRandomI(); setClothes(i);
        setRandomClothesArticle();
        dp.changeProduct(article,name,price,quantity,category,size);
        setRandomI(); setBedding(i);
        setRandomBeddingArticle();
        dp.changeProduct(article,name,price,quantity,category,length,width,height);
    }

    @Test
    public void showAllLists() {
        dp.showAllLists();
    }

    @Test
    public void showProductsWithZeroBalance() {
        dp.showProductsWithZeroBalance();
    }

    @Test
    public void showProductsByName() {
        setRandomName();
        dp.showProductsByName(name);
    }

    @Test
    public void issueSaleWriteoffPosting() {
        for (int i=1; i<=10; i++) {
            setDocument(i);
            dp.issueSaleWriteoffPosting(product,qty,type);
        }
    }

}