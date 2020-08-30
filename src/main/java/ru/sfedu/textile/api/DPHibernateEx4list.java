package ru.sfedu.textile.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ru.sfedu.textile.HibernateUtil;
import ru.sfedu.textile.classes.TextileProductEx4;
import ru.sfedu.textile.classes.ex4list.Products;
import ru.sfedu.textile.classes.ex4list.Sales;
import ru.sfedu.textile.constants.Category;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

import static ru.sfedu.textile.constants.Constants.*;
import static ru.sfedu.textile.constants.Constants.NOT_DELETED;

public class DPHibernateEx4list implements IDPHibernateEx4 {

    private static Logger log = LogManager.getLogger(DPHibernateEx4list.class);
    private Session openSession() {
        return HibernateUtil.getSessionFactory(SCHEMA_EX4_LIST).openSession();
    }

    @Override
    public void addProduct(String article, String name, int price, int quantity, Category category) {
        Session session = openSession();
        try {
            TextileProductEx4 product = new TextileProductEx4(article,name,price,quantity,category);
            session.beginTransaction();
            long id = (long)session.save(product);
            session.getTransaction().commit();
            session.close();
            log.debug(GEN_ID+id);
        } catch (Exception e) { log.error(e); log.debug(NOT_ADDED); session.close();}
    }

    @Override
    public TextileProductEx4 getProductByArticle(String article) {
        Session session = openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<TextileProductEx4> criteria = builder.createQuery(TextileProductEx4.class);
            Root<TextileProductEx4> root = criteria.from(TextileProductEx4.class);
            TextileProductEx4 tp = session.createQuery(criteria.select(root).where(builder.equal(root.get(ARTICLE), article))).uniqueResult();
            if (tp!=null) log.debug(RECEIVED_ENTITY+tp);
            else log.debug(NOT_RECEIVED);
            session.close();
            return tp;
        } catch (Exception exception) { log.error(exception); log.debug(NOT_RECEIVED); session.close(); return null; }
    }

    @Override
    public void changeProduct(TextileProductEx4 product) {
        Session session = openSession();
        try {
            String article = product.getArticle();
            TextileProductEx4 o = getProductByArticle(article);
            product.setId(o.getId());
            session.beginTransaction();
            session.update(product);
            session.getTransaction().commit();
            session.close();
            log.debug(UPDATED+PRODUCT+WITH_ARTICLE+article);
        } catch (Exception e) { log.error(e); log.debug(NOT_UPDATED); session.close(); }
    }

    @Override
    public void changeProduct(String article, String name, int price, int quantity, Category category) {
        Session session = openSession();
        try {
            TextileProductEx4 product = new TextileProductEx4(article,name,price,quantity,category);
            TextileProductEx4 o = getProductByArticle(article);
            product.setId(o.getId());
            session.beginTransaction();
            session.update(product);
            session.getTransaction().commit();
            session.close();
            log.debug(UPDATED+PRODUCT+WITH_ARTICLE+article);
        } catch (Exception e) { log.error(e); log.debug(NOT_UPDATED); session.close(); }
    }



    @Override
    public void issueSale(String[] articles) {
        try {
            List<Products> products = new ArrayList<>();
            for (String article : articles) {
                TextileProductEx4 p = getProductByArticle(article);
                if (p != null) {
                    Products pr = new Products(p.getArticle(), p.getName(), p.getPrice());
                    products.add(pr);
                }
            }
            Sales sale = new Sales(products);
            if (sale.getProducts().size()!=0) {
                Session session = openSession();
                List<Products> setToRemove = new ArrayList<>();
                sale.getProducts().forEach(p -> {
                    TextileProductEx4 prod = getProductByArticle(p.getArticle());
                    int newQty = prod.getQuantity()-1;
                    if (newQty < 0 ) {
                        log.debug(NOT_ENOUGH_QTY+WITH_ARTICLE+p.getArticle());
                        setToRemove.add(p);
                        sale.setSum(sale.getSum()-p.getPrice());
                    }
                    else { prod.setQuantity(newQty); changeProduct(prod); }
                });
                sale.getProducts().removeAll(setToRemove);
                if (sale.getProducts().size()!=0)
                    try {
                        session.beginTransaction();
                        long id = (long)session.save(sale);
                        session.getTransaction().commit();
                        session.close();
                        log.debug(GEN_ID+id);
                    } catch (Exception e) { log.error(e); log.debug(NOT_ISSUED); session.close();}
                else log.debug(NOT_ISSUED);
            }
            else log.debug(NOT_ISSUED);
        }
        catch (Exception e) { log.error(e); log.debug(WRONG_DATA); }
    }

    @Override
    public Object getSale(long id) {
        Session session = openSession();
        try {
            session.beginTransaction();
            Sales sale = session.get(Sales.class,id);
            session.getTransaction().commit();
            session.close();
            if (sale!=null) log.debug(RECEIVED_ENTITY+sale);
            else log.debug(NOT_RECEIVED);
            return sale;
        } catch (Exception e) { log.error(e); log.debug(NOT_RECEIVED); session.close(); return null; }
    }

    @Override
    public void updateSale(String[] articles, long id) {
        Session session = openSession();
        try {
            List<Products> products = new ArrayList<>();
            for (String article : articles) {
                TextileProductEx4 p = getProductByArticle(article);
                if (p != null) {
                    Products pr = new Products(p.getArticle(), p.getName(), p.getPrice());
                    products.add(pr);
                }
            }
            Sales newsale = new Sales(products);
            newsale.setId(id);
            Sales oldsale = (Sales) getSale(id);
            session.beginTransaction();
            oldsale.getProducts().forEach(p -> {
                long id_textile_prod = getProductByArticle(p.getArticle()).getId();
                TextileProductEx4 prod = session.get(TextileProductEx4.class,id_textile_prod);
                prod.setQuantity(prod.getQuantity()+1);
            });
            List<Products> setToRemove = new ArrayList<>();
            newsale.getProducts().forEach(p -> {
                long id_textile_prod = getProductByArticle(p.getArticle()).getId();
                TextileProductEx4 prod = session.get(TextileProductEx4.class,id_textile_prod);
                int newQty = prod.getQuantity()-1;
                if (newQty < 0 ) {
                    log.debug(NOT_ENOUGH_QTY+WITH_ARTICLE+p.getArticle());
                    setToRemove.add(p);
                    newsale.setSum(newsale.getSum()-p.getPrice());
                }
                else { prod.setQuantity(newQty); }
            });
            newsale.getProducts().removeAll(setToRemove);
            if (newsale.getProducts().size()!=0){
                session.update(newsale);
                session.getTransaction().commit();
                session.close();
                log.debug(UPDATED+SALE+WITH_ID+id);
            }
            else {log.debug(NOT_UPDATED); session.getTransaction().rollback(); session.close(); }
        } catch (Exception e) { log.error(e); log.debug(NOT_UPDATED); session.getTransaction().rollback(); session.close(); }
    }

    @Override
    public void deleteSale(long id) {
        Session session = openSession();
        Sales sale = (Sales) getSale(id);
        if (sale!=null) {

            sale.getProducts().forEach(p -> {
                TextileProductEx4 prod = getProductByArticle(p.getArticle());
                int newQty = prod.getQuantity()+1;
                prod.setQuantity(newQty);
                changeProduct(prod);
            });

            try {
                session.beginTransaction();
                session.delete(sale);
                session.getTransaction().commit();
                session.close();
                log.debug(DELETED+WITH_ID+id);
                return;
            } catch (Exception e) { log.error(e); log.debug(NOT_DELETED); session.close(); }
        }
        log.debug(NOT_DELETED);
    }
}
