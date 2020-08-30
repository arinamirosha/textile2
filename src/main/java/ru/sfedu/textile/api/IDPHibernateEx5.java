package ru.sfedu.textile.api;

import java.util.List;

public interface IDPHibernateEx5 {

    List summaryNativeSQL();
    List summaryCriteria();
    List summaryHQL();

    void compareRuntime();

}
