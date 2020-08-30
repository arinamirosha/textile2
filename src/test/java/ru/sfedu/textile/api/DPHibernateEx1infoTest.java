package ru.sfedu.textile.api;

import org.junit.Test;

public class DPHibernateEx1infoTest {

    public static DPHibernateEx1info dp = new DPHibernateEx1info();

    @Test
    public void showDatabaseInfo() {
        dp.showDatabaseInfo();
    }

}