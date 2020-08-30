package ru.sfedu.textile.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ru.sfedu.textile.HibernateUtil;
import ru.sfedu.textile.classes.ex3mappedSuperclass.Bedding;
import ru.sfedu.textile.classes.ex3mappedSuperclass.Clothes;
import ru.sfedu.textile.classes.ex3mappedSuperclass.Document;
import ru.sfedu.textile.classes.ex3mappedSuperclass.TextileProduct;
import ru.sfedu.textile.constants.Category;
import ru.sfedu.textile.constants.TypeOfDoc;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static ru.sfedu.textile.constants.Constants.*;

public class DPHibernateEx3MappedSuperclass implements IDPHibernateEx3 {

    private static Logger log = LogManager.getLogger(DPHibernateEx3MappedSuperclass.class);
    private Session openSession() { return HibernateUtil.getSessionFactory(SCHEMA_EX3_MAPPED_SUPERCLASS).openSession(); }

    @Override
    public void addProduct(String article, String name, int price, int quantity, Category category, String size) {
        Clothes obj = new Clothes(article,name,price,quantity,category,size);
        if (getProductByArticle(obj.getArticle()) == null){
            Session session = openSession();
            try {
                session.beginTransaction();
                long id = (long)session.save(obj);
                session.getTransaction().commit();
                session.close();
                log.debug(GEN_ID+id);
            } catch (Exception e) { log.error(e); log.debug(NOT_ADDED); session.close();}
        } else log.debug(NOT_ADDED+PRODUCT_EXISTS+WITH_ARTICLE+article);
    }

    @Override
    public void addProduct(String article, String name, int price, int quantity, Category category, int length, int width, int height) {
        Bedding obj = new Bedding(article,name,price,quantity,category,length,width,height);
        if (getProductByArticle(obj.getArticle()) == null){
            Session session = openSession();
            try {
                session.beginTransaction();
                long id = (long)session.save(obj);
                session.getTransaction().commit();
                session.close();
                log.debug(GEN_ID+id);
            } catch (Exception e) { log.error(e); log.debug(NOT_ADDED); session.close();}
        } else log.debug(NOT_ADDED+PRODUCT_EXISTS+WITH_ARTICLE+article);
    }

    @Override
    public Object getProductById(long id) {
        Session session = openSession();
        try {
            session.beginTransaction();
            Clothes clo = session.get(Clothes.class,id);
            if (clo!=null) {
                session.getTransaction().commit();
                session.close();
                log.debug(RECEIVED_ENTITY+clo);
                return clo;
            }
            Bedding bed = session.get(Bedding.class,id);
            session.getTransaction().commit();
            session.close();
            if (bed!=null) log.debug(RECEIVED_ENTITY+bed);
            else log.debug(NOT_RECEIVED);
            return bed;
        } catch (Exception e) { log.error(e); log.debug(NOT_RECEIVED); session.close(); return null; }
    }

    @Override
    public Object getProductByArticle(String article) {
        Session session = openSession();
        try {
            Clothes clo = (Clothes) loadProductByArticle(Clothes.class,session,article);
            if (clo!=null) {
                session.close();
                log.debug(RECEIVED_ENTITY+clo);
                return clo;
            }
            Bedding bed = (Bedding) loadProductByArticle(Bedding.class,session,article);
            if (bed!=null) log.debug(RECEIVED_ENTITY+bed);
            else log.debug(NOT_RECEIVED);
            session.close();
            return bed;
        } catch (Exception exception) { log.error(exception); log.debug(NOT_RECEIVED); session.close(); return null; }
    }

    @Override
    public void deleteProductById(long id) {
        Session session = openSession();
        Object obj = getProductById(id);
        if (obj!=null) {
            try {
                session.beginTransaction();
                session.delete(obj);
                session.getTransaction().commit();
                session.close();
                log.debug(DELETED+WITH_ID+id);
                return;
            } catch (Exception e) { log.error(e); log.debug(NOT_DELETED); session.close(); }
        }
        log.debug(NOT_DELETED+NO_PRODUCT+WITH_ID+id);
    }

    @Override
    public void deleteProductByArticle(String article) {
        Session session = openSession();
        Object obj = getProductByArticle(article);
        if (obj!=null) {
            try {
                session.beginTransaction();
                session.delete(obj);
                session.getTransaction().commit();
                session.close();
                log.debug(DELETED+WITH_ARTICLE+article);
                return;
            } catch (Exception e) { log.error(e); log.debug(NOT_DELETED); session.close(); }
        }
        log.debug(NOT_DELETED+NO_PRODUCT+WITH_ARTICLE+article);
    }

    @Override
    public void changeProduct(Object obj) {
        Session session = openSession();
        String article=((TextileProduct) obj).getArticle();
        Object o = getProductByArticle(article);
        if (o!=null){
            long id = ((TextileProduct) o).getId();
            ((TextileProduct) obj).setId(id);
            try {
                session.beginTransaction();
                session.update(obj);
                session.getTransaction().commit();
                session.close();
                log.debug(UPDATED+WITH_ID+id+WITH_ARTICLE+article);
            } catch (Exception e) { log.error(e); log.debug(NOT_UPDATED); session.close(); }
        } else log.debug(NOT_UPDATED+NO_PRODUCT+WITH_ARTICLE+article);
    }

    @Override
    public void changeProduct(String article, String name, int price, int quantity, Category category, String size) {
        Clothes obj = new Clothes(article,name,price,quantity,category,size);
        Session session = openSession();
        Object o = getProductByArticle(article);
        if (o!=null){
            long id = ((TextileProduct) o).getId();
            obj.setId(id);
            try {
                session.beginTransaction();
                session.update(obj);
                session.getTransaction().commit();
                session.close();
                log.debug(UPDATED+WITH_ID+id+WITH_ARTICLE+article);
            } catch (Exception e) { log.error(e); log.debug(NOT_UPDATED); session.close(); }
        } else log.debug(NOT_UPDATED+NO_PRODUCT+WITH_ARTICLE+article);
    }

    @Override
    public void changeProduct(String article, String name, int price, int quantity, Category category, int length, int width, int height) {
        Bedding obj = new Bedding(article,name,price,quantity,category,length,width,height);
        Session session = openSession();
        Object o = getProductByArticle(article);
        if (o!=null){
            long id = ((TextileProduct) o).getId();
            obj.setId(id);
            try {
                session.beginTransaction();
                session.update(obj);
                session.getTransaction().commit();
                session.close();
                log.debug(UPDATED+WITH_ID+id+WITH_ARTICLE+article);
            } catch (Exception e) { log.error(e); log.debug(NOT_UPDATED); session.close(); }
        } else log.debug(NOT_UPDATED+NO_PRODUCT+WITH_ARTICLE+article);
    }

    @Override
    public void showAllLists() {
        List list = new ArrayList();
        Session session = openSession();
        try {
            list.addAll(loadAllData(Clothes.class, session));
            list.addAll(loadAllData(Bedding.class, session));
            list.addAll(loadAllData(Document.class, session));
            session.close();
            if (!list.isEmpty()) for (Object o : list) log.debug(o);
            else log.debug(NO_DATA);
        } catch (Exception exception) { log.error(exception); session.close(); }
    }

    @Override
    public void showProductsWithZeroBalance() {
        List list = new ArrayList();
        Session session = openSession();
        try {
            list.addAll(loadProductsWithZeroBalance(Clothes.class,session));
            list.addAll(loadProductsWithZeroBalance(Bedding.class,session));
            session.close();
            if (!list.isEmpty()) for (Object o : list) { log.debug(o); }
            else log.debug(NO_ZERO);
        } catch (Exception exception) { log.error(exception); session.close(); }
    }

    @Override
    public void showProductsByName(String name) {
        List list = new ArrayList();
        Session session = openSession();
        try {
            list.addAll(loadProductsByName(Clothes.class,session,name));
            list.addAll(loadProductsByName(Bedding.class,session,name));
            session.close();
            if (!list.isEmpty()) for (Object o : list) { log.debug(o); }
            else log.debug(NO_PRODUCT_NAME);
        } catch (Exception exception) { log.error(exception); session.close(); }
    }

    @Override
    public void issueSaleWriteoffPosting(String product, int qty, TypeOfDoc type) {
        Document doc = new Document(product,qty,type);
        Object o = getProductByArticle(doc.getProduct());
        if (o!=null) {
            Session session = openSession();
            int newQty;
            if (TypeOfDoc.valueOf(doc.getType().toString().toLowerCase()).equals(TypeOfDoc.posting))
                newQty = ((TextileProduct) o).getQuantity() + doc.getQty();
            else {
                newQty = ((TextileProduct) o).getQuantity() - doc.getQty();
                if (newQty < 0 ) { log.debug(NOT_ENOUGH_QTY); return; }
            }
            ((TextileProduct) o).setQuantity(newQty);
            changeProduct(o);
            try {
                session.beginTransaction();
                long id = (long)session.save(doc);
                session.getTransaction().commit();
                session.close();
                log.debug(GEN_ID+id);
            } catch (Exception e) { log.error(e); log.debug(NOT_ISSUED); session.close();}
            log.debug(SUCCESS_ADD_DOCUMENT);
        }
        else log.debug(NOT_ISSUED+NO_PRODUCT+WITH_ARTICLE+product);
    }


    // Helpful methods

    @Override
    public <T> List<T> loadAllData(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        return session.createQuery(criteria).getResultList();
    }

    private <T> List<T> loadProductsByName(Class<T> type, Session session, String name) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        Root<T> root = criteria.from(type);
        return session.createQuery(criteria.select(root).where(builder.like(root.get(NAME), PERCENT+name+PERCENT))).getResultList();
    }

    private  <T> List<T> loadProductsWithZeroBalance(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        Root<T> root = criteria.from(type);
        return session.createQuery(criteria.select(root).where(builder.equal(root.get(QUANTITY), 0))).getResultList();
    }

    private  <T> Object loadProductByArticle(Class<T> type, Session session, String article) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        Root<T> root = criteria.from(type);
        return session.createQuery(criteria.select(root).where(builder.equal(root.get(ARTICLE), article))).uniqueResult();
    }
}
