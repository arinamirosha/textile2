package ru.sfedu.textile.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.sfedu.textile.HibernateUtil;

import java.util.Collections;
import java.util.List;

import static ru.sfedu.textile.constants.Constants.*;

public class DPHibernateEx1info {

    private static Logger log = LogManager.getLogger(DPHibernateEx1info.class);
    private Session openSession() { return HibernateUtil.getSessionFactory(SCHEMA_EX1).openSession(); }


    public void showDatabaseInfo(){
        List list_schemas = getDBschemas();
        List list_sizes = getDBsizes();
        List list_users = getListUsers();

        String str = DATABASES;
        for (Object list_schema : list_schemas) str += list_schema + NEW_LINE;

        str += DB_SIZES;
        for (int i=0; i<list_schemas.size(); i++) str += list_schemas.get(i)+SPACE+list_sizes.get(i)+MB+NEW_LINE;

        str += LIST_USERS;
        for (Object list_user : list_users) str += list_user+NEW_LINE;

        str += LIST_TABLES;
        for (Object list_schema : list_schemas) str += list_schema + SPACE + getListTables(list_schema.toString()).toString() + NEW_LINE;

        str += COUNT_OF_TABLES;
        for (Object list_schema : list_schemas) str += list_schema + SPACE + getListCountsOfTables(list_schema.toString()).toString() + NEW_LINE;

        log.debug(str);
    }


    private List getDBschemas(){
        Session session = openSession();
        try {
            Query query = session.createNativeQuery(GET_DB_SCHEMAS);
            List list = query.getResultList();
            session.close();
            return list;
        } catch (Exception exception) { log.error(exception); session.close(); return Collections.emptyList(); }
    }

    private List getDBsizes(){
        Session session = openSession();
        try {
            Query query = session.createNativeQuery(GET_DB_SIZES);
            List list = query.getResultList();
            session.close();
            return list;
        } catch (Exception exception) { log.error(exception); session.close(); return Collections.emptyList(); }
    }

    private List getListUsers(){
        Session session = openSession();
        try {
            Query query = session.createNativeQuery(GET_LIST_USERS);
            List list = query.getResultList();
            session.close();
            return list;
        } catch (Exception exception) { log.error(exception); session.close(); return Collections.emptyList(); }
    }

    private List getListTables(String dbName){
        Session session = openSession();
        try {
            Query query = session.createNativeQuery(SHOW_TABLES+DOUBLE_QUOTE+dbName+DOUBLE_QUOTE);
            List list = query.getResultList();
            session.close();
            return list;
        } catch (Exception exception) { log.error(exception); session.close(); return Collections.emptyList(); }
    }

    private List getListCountsOfTables(String dbName){
        Session session = openSession();
        try {
            Query query = session.createNativeQuery(GET_COUNT_OF_TABLES+DOUBLE_QUOTE+dbName+DOUBLE_QUOTE);
            List list = query.getResultList();
            session.close();
            return list;
        } catch (Exception exception) { log.error(exception); session.close(); return Collections.emptyList(); }
    }


}
