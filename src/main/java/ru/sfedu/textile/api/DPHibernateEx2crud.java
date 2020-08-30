package ru.sfedu.textile.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ru.sfedu.textile.HibernateUtil;
import ru.sfedu.textile.classes.ex2.Address;
import ru.sfedu.textile.classes.ex2.TestEntity;

import static ru.sfedu.textile.constants.Constants.*;

public class DPHibernateEx2crud {

    private static Logger log = LogManager.getLogger(DPHibernateEx2crud.class);
    private Session openSession() {
        return HibernateUtil.getSessionFactory(SCHEMA_EX2).openSession();
    }

    public void create(String name, String description, boolean check, String country, String city, int postcode) {
        Session session = openSession();
        try {
            Address address = new Address(country, city, postcode);
            TestEntity obj = new TestEntity(name,description,check,address);
            session.beginTransaction();
            long id = (long)session.save(obj);
            session.getTransaction().commit();
            session.close();
            log.debug(GEN_ID+id);
        } catch (Exception e) { log.error(e); log.debug(NOT_ADDED); session.close();}
    }

    public void read(long id) {
        Session session = openSession();
        try {
            session.beginTransaction();
            TestEntity testentity = session.get(TestEntity.class,id);
            session.getTransaction().commit();
            session.close();
            if (testentity!=null) log.debug(RECEIVED_ENTITY+testentity);
            else log.debug(NOT_RECEIVED);
        } catch (Exception e) { log.error(e); log.debug(NOT_RECEIVED); session.close(); }
    }

    public void update(long id, String name, String description, boolean check, String country, String city, int postcode) {
        Session session = openSession();
        try {
            Address address = new Address(country, city, postcode);
            TestEntity obj = new TestEntity(name,description,check,address);
            obj.setId(id);
            session.beginTransaction();
            session.update(obj);
            session.getTransaction().commit();
            session.close();
            log.debug(UPDATED+WITH_ID+id);
        } catch (Exception e) { log.error(e); log.debug(NOT_UPDATED); session.close(); }
    }

    public void delete(long id) {
        Session session = openSession();
        try {
            session.beginTransaction();
            TestEntity obj = session.get(TestEntity.class,id);
            if (obj==null) { log.debug(NO_ENTITY+id); session.close(); return;}
            session.delete(obj);
            session.getTransaction().commit();
            session.close();
            log.debug(DELETED+WITH_ID+id);
        } catch (Exception e) { log.error(e); log.debug(NOT_DELETED); session.close(); }
    }

}
