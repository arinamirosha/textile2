package ru.sfedu.textile.api;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ru.sfedu.textile.classes.ex5oneone.Bedding;
import ru.sfedu.textile.classes.ex5oneone.Sizes;
import ru.sfedu.textile.constants.Category;
import ru.sfedu.textile.constants.TypeOfDoc;

import java.util.Date;

import static org.junit.Assert.*;
import static ru.sfedu.textile.constants.Category.*;
import static ru.sfedu.textile.constants.Category.pillowcase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DPHibernateEx5oneoneTest {

    public static DPHibernateEx5oneone dp = new DPHibernateEx5oneone();
    private static String article;
    private static String name;
    private static int price;
    private static int length;
    private static int width;
    private static int height;

    private static void setBedding(int i){
        String articleLetterArr[] = {"k","l","m","n","o","p","q","r","s","t"};
        article=i*10+i+"-"+articleLetterArr[i-1];
        name="name"+i+i;
        price=i*100;
    }
    private static void setSizes(int i){
        int lengthArr[] = {60,220,90,110,200,50,60,80,60,220};
        int widthArr[] = {40,60,70,80,90,30,40,50,40,60};
        int heightArr[] = {0,10,0,15,30,5,20,0,3,5};
        length = lengthArr[i-1];
        width = widthArr[i-1];
        height = heightArr[i-1];
    }

    @Test
    public void addProduct() {
        for (int i=1; i<=10; i++) {
            setBedding(i);
            setSizes(i);
            dp.addProduct(article,name,price,length,width,height);
        }
    }

    @Test
    public void compareRuntime() {
        dp.compareRuntime();
    }
}