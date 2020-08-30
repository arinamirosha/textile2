package ru.sfedu.textile.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.sfedu.textile.HibernateUtil;
import ru.sfedu.textile.classes.ex5oneone.Bedding;
import ru.sfedu.textile.classes.ex5oneone.Sizes;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static ru.sfedu.textile.constants.Constants.*;

public class DPHibernateEx5oneone implements IDPHibernateEx5 {

    private static Logger log = LogManager.getLogger(DPHibernateEx5oneone.class);
    private Session openSession() {
        return HibernateUtil.getSessionFactory(SCHEMA_EX5_ONEONE).openSession();
    }


    public void addProduct(String article, String name, int price, int length, int width, int height) {
        Session session = openSession();
        try {
            Sizes sizes = new Sizes(length,width,height);
            Bedding bed = new Bedding(article,name,price);
            bed.setSizes(sizes);
            session.beginTransaction();
            session.persist(bed);
            session.getTransaction().commit();
            session.close();
            log.debug(GEN_ID+bed.getId());
        } catch (Exception e) { log.error(e); log.debug(NOT_ADDED); session.getTransaction().rollback(); session.close(); }
    }

    @Override
    public List summaryNativeSQL() {
        Session session = openSession();
        try {
            Query query = session.createNativeQuery(SELECT_ALL_FROM+ONEONE_BEDDING_PRODUCT, Bedding.class);
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
            CriteriaQuery<Bedding> criteria = builder.createQuery(Bedding.class);
            criteria.from(Bedding.class);
            List list = session.createQuery(criteria).getResultList();
            session.close();
            return list;
        } catch (Exception exception) { log.error(exception); session.close(); return Collections.emptyList();}
    }

    @Override
    public List summaryHQL() {
        Session session = openSession();
        try {
            Query query = session.createQuery(FROM+ONEONE_BEDDING_PRODUCT);
            List list = query.list();
            session.close();
            return list;
        } catch (Exception exception) { log.error(exception); session.close(); return Collections.emptyList();}
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
