package ru.sfedu.textile.api;

import org.junit.Test;
import ru.sfedu.textile.classes.ex2.Address;
import ru.sfedu.textile.classes.ex2.TestEntity;

import java.util.Date;
import java.util.Random;

public class DPHibernateEx2crudTest {

    public static DPHibernateEx2crud dp = new DPHibernateEx2crud();
    private static long id;
    private static String name, description, country, city;
    private static boolean check;
    private static int postcode, i;

    private static void setTestEntity(int i){
        name = "name"+i;
        description = "decription"+i;
        check = i % 2 == 0;
        country = "country"+i;
        city = "city"+i;
        postcode = i*100000+i*10000+i*100;
    }
    private static void setRandomId(){
        int idArr[] = {1,2,3,4,5,6,7,8,9,10};
        Random random = new Random();
        int num = random.nextInt(10);
        id = idArr[num];
    }
    private static void setRandomI(){
        int iArr[] = {1,2,3,4,5,6,7,8,9,10};
        Random random = new Random();
        int num = random.nextInt(10);
        i = iArr[num];
    }

    @Test
    public void create() {
        for (int i=1; i<=10; i++) {
            setTestEntity(i);
            dp.create(name, description, check, country, city, postcode);
        }
    }

    @Test
    public void read() {
        setRandomId();
        dp.read(id);
    }

    @Test
    public void update() {
        setRandomId();
        setRandomI();
        setTestEntity(i);
        dp.update(id, name, description, check, country, city, postcode);
    }

    @Test
    public void delete() {
        setRandomId();
        dp.delete(1);
    }
}