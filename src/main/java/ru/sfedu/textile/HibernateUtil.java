package ru.sfedu.textile;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.sfedu.textile.classes.ex2.Address;
import ru.sfedu.textile.classes.ex2.TestEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.sfedu.textile.constants.Constants.*;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    // Создание фабрики

    public static SessionFactory getSessionFactory(String schema) {
        if (sessionFactory == null) {

            // loads configuration and mappings
            File file;
            if (Files.exists(Paths.get(H_CFG_JAR))) file = new File(H_CFG_JAR);
            else file = new File(H_CFG_PATH);

            Configuration configuration = new Configuration().configure(file);
//            Configuration configuration = new Configuration().configure();

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            MetadataSources metadataSources = new MetadataSources(serviceRegistry);

            // Аннотированные сущности
            switch (schema){
                case SCHEMA_EX1: break;
                case SCHEMA_EX2:
                    metadataSources.addAnnotatedClass(TestEntity.class);
                    metadataSources.addAnnotatedClass(Address.class);
                    break;
                case SCHEMA_EX3_SINGLE_TABLE:
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex3singleTable.TextileProduct.class);
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex3singleTable.Clothes.class);
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex3singleTable.Bedding.class);
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex3singleTable.Document.class);
                    break;
                case SCHEMA_EX3_TABLE_PER_CLASS:
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex3tablePerClass.TextileProduct.class);
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex3tablePerClass.Clothes.class);
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex3tablePerClass.Bedding.class);
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex3tablePerClass.Document.class);
                    break;
                case SCHEMA_EX3_JOINED_TABLES:
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex3joinedTables.TextileProduct.class);
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex3joinedTables.Clothes.class);
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex3joinedTables.Bedding.class);
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex3joinedTables.Document.class);
                    break;
                case SCHEMA_EX3_MAPPED_SUPERCLASS:
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex3mappedSuperclass.TextileProduct.class);
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex3mappedSuperclass.Clothes.class);
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex3mappedSuperclass.Bedding.class);
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex3mappedSuperclass.Document.class);
                    break;
                case SCHEMA_EX4_SET:
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.TextileProductEx4.class);
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex4set.Products.class);
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex4set.Sales.class);
                    break;
                case SCHEMA_EX4_LIST:
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.TextileProductEx4.class);
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex4list.Products.class);
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex4list.Sales.class);
                    break;
                case SCHEMA_EX4_MAP:
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.TextileProductEx4.class);
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex4map.Products.class);
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex4map.Sales.class);
                    break;
                case SCHEMA_EX5_MANYONE:
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex5manyone.TextileProduct.class);
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex5manyone.Document.class);
                    break;
                case SCHEMA_EX5_ONEONE:
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex5oneone.Bedding.class);
                    metadataSources.addAnnotatedClass(ru.sfedu.textile.classes.ex5oneone.Sizes.class);
                    break;
            }

//            metadataSources.addResource("named-queries.hbm.xml");// Именованные запросы
            sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
        }

        return sessionFactory;
    }

}
