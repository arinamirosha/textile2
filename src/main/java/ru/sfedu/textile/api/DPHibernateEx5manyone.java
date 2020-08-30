package ru.sfedu.textile.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.sfedu.textile.HibernateUtil;
import ru.sfedu.textile.classes.ex5manyone.Document;
import ru.sfedu.textile.classes.ex5manyone.TextileProduct;
import ru.sfedu.textile.constants.Category;
import ru.sfedu.textile.constants.TypeOfDoc;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static ru.sfedu.textile.constants.Constants.*;

public class DPHibernateEx5manyone implements IDPHibernateEx5 {

    private static Logger log = LogManager.getLogger(DPHibernateEx5manyone.class);
    private Session openSession() {
        return HibernateUtil.getSessionFactory(SCHEMA_EX5_MANYONE).openSession();
    }


    public void addProduct(String article, String name, int price, int quantity, Category category) {
        TextileProduct obj = new TextileProduct(article,name,price,quantity,category);
        Session session = openSession();
        try {
            session.beginTransaction();
            long id = (long)session.save(obj);
            session.getTransaction().commit();
            session.close();
            log.debug(GEN_ID+id);
        } catch (Exception e) { log.error(e); log.debug(NOT_ADDED); session.close();}
    }
    public TextileProduct getProductByArticle(String article) {
        Session session = openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<TextileProduct> criteria = builder.createQuery(TextileProduct.class);
            Root<TextileProduct> root = criteria.from(TextileProduct.class);
            TextileProduct tp = session.createQuery(criteria.select(root).where(builder.equal(root.get(ARTICLE), article))).uniqueResult();
            if (tp!=null) log.debug(RECEIVED_ENTITY+tp);
            else log.debug(NOT_RECEIVED);
            session.close();
            return tp;
        } catch (Exception exception) { log.error(exception); log.debug(NOT_RECEIVED); session.close(); return null; }
    }

    public void issueSaleWriteoffPosting(String article, List<Document> docs) {
        Session session = openSession();
        try {
            long product_id = getProductByArticle(article).getId();
            session.beginTransaction();
            TextileProduct product = session.get(TextileProduct.class,product_id);
            if (product!=null){
                for (Document doc : docs) {
                    int newQty;
                    if (TypeOfDoc.valueOf(doc.getType().toString().toLowerCase()).equals(TypeOfDoc.posting))
                        newQty = product.getQuantity() + doc.getQty();
                    else {
                        newQty = product.getQuantity() - doc.getQty();
                        if (newQty < 0) { log.debug(NOT_ENOUGH_QTY); continue; }
                    }
                    doc.setProduct(product);
                    product.setQuantity(newQty);
                    product.getDocuments().add(doc);
                    session.persist(doc);
                }
                session.getTransaction().commit();
                session.close();
                log.debug(SUCCESS_ADD_DOCUMENT);
            }
            else log.debug(NOT_ISSUED);
        } catch (Exception e) { log.error(e); log.debug(NOT_ISSUED); session.close(); return; }
    }

    @Override
    public List summaryNativeSQL() {
        Session session = openSession();
        try {
            Query query = session.createNativeQuery(SELECT_ALL_FROM+MANYONE_TEXTILE_PRODUCT,TextileProduct.class);
            List list = query.getResultList();
            session.close();
            return list;
        } catch (Exception exception) { log.error(exception); session.close(); return Collections.emptyList();}
    }

    @Override
    public List summaryCriteria() {
        Session session = openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<TextileProduct> criteria = builder.createQuery(TextileProduct.class);
            criteria.from(TextileProduct.class);
            List list = session.createQuery(criteria).getResultList();
            session.close();
            return list;
        } catch (Exception exception) { log.error(exception); session.close(); return Collections.emptyList();}
    }

    @Override
    public List summaryHQL() {
        Session session = openSession();
        try {
            Query query = session.createQuery(FROM+MANYONE_TEXTILE_PRODUCT);
            List list = query.list();
            session.close();
            return list;
        } catch (Exception exception) { log.error(exception); session.close(); return Collections.emptyList(); }
    }

    @Override
    public void compareRuntime() {

        List list_criteria, list_sql, list_hql;
        Date start_time,end_time;
        long runtime_criteria, runtime_sql, runtime_hql;

        start_time = new Date();
        list_criteria = summaryCriteria();
        end_time = new Date();
        runtime_criteria = end_time.getTime() - start_time.getTime();

        start_time = new Date();
        list_sql = summaryNativeSQL();
        end_time = new Date();
        runtime_sql = end_time.getTime() - start_time.getTime();

        start_time = new Date();
        list_hql = summaryHQL();
        end_time = new Date();
        runtime_hql = end_time.getTime() - start_time.getTime();


        log.debug(CRITERIA);
        if (!list_criteria.isEmpty()) for (Object o : list_criteria) log.debug(o);
        else log.debug(NO_DATA);

        log.debug(NATIVE_SQL);
        if (!list_sql.isEmpty()) for (Object o : list_sql) log.debug(o);
        else log.debug(NO_DATA);

        log.debug(HQL);
        if (!list_hql.isEmpty()) for (Object o : list_hql) log.debug(o);
        else log.debug(NO_DATA);

        log.debug(CRITERIA_RUNTIME+runtime_criteria/1000.0+SECONDS);
        log.debug(NATIVE_SQL_RUNTIME+runtime_sql/1000.0+SECONDS);
        log.debug(HQL_RUNTIME+runtime_hql/1000.0+SECONDS);

    }
}
