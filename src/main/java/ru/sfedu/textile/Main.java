package ru.sfedu.textile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.textile.api.*;
import ru.sfedu.textile.classes.TextileProductEx4;
import ru.sfedu.textile.classes.ex4list.Products;
import ru.sfedu.textile.classes.ex5manyone.Document;
import ru.sfedu.textile.classes.ex5oneone.Sizes;
import ru.sfedu.textile.constants.Category;
import ru.sfedu.textile.constants.TypeOfDoc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static ru.sfedu.textile.constants.Constants.*;

public class Main {
    private static DPHibernateEx1info dp1 = new DPHibernateEx1info();
    private static DPHibernateEx2crud dp2 = new DPHibernateEx2crud();;
    private static IDPHibernateEx3 dp3;
    private static IDPHibernateEx4 dp4;
    private static DPHibernateEx5manyone dp5manyone = new DPHibernateEx5manyone();
    private static DPHibernateEx5oneone dp5oneone = new DPHibernateEx5oneone();
    private static List<Document> docs = new ArrayList<>();

    private static Logger log;
    static {
        if (Files.exists(Paths.get(CONFIG_LOG_JAR)))
            System.setProperty(CONFIG_LOG_KEY,CONFIG_LOG_JAR);
        else System.setProperty(CONFIG_LOG_KEY,CONFIG_LOG_PATH);
        log = LogManager.getLogger(Main.class);
    }


    public static void main(String[] args) {

        try {

            if (args.length==0) { log.info(EMPTY_COMMAND); return; }

            if (args.length==1) {
                if (args[0].toUpperCase().equals(DBINFO)) {
                    dp1.showDatabaseInfo(); System.exit(0);
                } else { log.info(WRONG_COMMAND); log.info(args); return; }
            }

            switch (args[0].toUpperCase()){
                case JOINED_TABLES: dp3 = new DPHibernateEx3JoinedTables(); break;
                case MAPPED_SUPERCLASS: dp3 = new DPHibernateEx3MappedSuperclass(); break;
                case SINGLE_TABLE: dp3 = new DPHibernateEx3SingleTable(); break;
                case TABLE_PER_CLASS: dp3 = new DPHibernateEx3TablePerClass(); break;
                case LIST: dp4 = new DPHibernateEx4list(); break;
                case MAP: dp4 = new DPHibernateEx4map(); break;
                case SET: dp4 = new DPHibernateEx4set(); break;
            }

            switch (args[0].toUpperCase()){

                case TESTENTITY:
                    switch (args[1].toUpperCase()){
                        case CREATE:
                            if (args.length != 8) { log.info(WRONG_QTY_PARAMS); return; }
                            if (!checkTestEntity(args[2],args[3],args[4],args[5],args[6],args[7])) return;
                            dp2.create(args[2],args[3], args[4].equals(ONE),args[5],args[6],Integer.parseInt(args[7])); System.exit(0);
                        case READ:
                            if (args.length != 3) { log.info(WRONG_QTY_PARAMS); return; }
                            if (!check(ID,args[2])) return;
                            dp2.read(Long.parseLong(args[2])); System.exit(0);
                        case UPDATE:
                            if (args.length != 9) { log.info(WRONG_QTY_PARAMS); return; }
                            if (!check(ID,args[2])) return;
                            if (!checkTestEntity(args[3],args[4],args[5],args[6],args[7],args[8])) return;
                            dp2.update(Long.parseLong(args[2]),args[3], args[4],args[5].equals(ONE),args[6],args[7],Integer.parseInt(args[8])); System.exit(0);
                        case DELETE:
                            if (args.length != 3) { log.info(WRONG_QTY_PARAMS); return; }
                            if (!check(ID,args[2])) return;
                            dp2.delete(Long.parseLong(args[2])); System.exit(0);
                        default: log.info(WRONG_COMMAND); return;
                    }

                case JOINED_TABLES:
                case MAPPED_SUPERCLASS:
                case SINGLE_TABLE:
                case TABLE_PER_CLASS:
                    switch (args[1].toUpperCase()){
                        case ADD:
                            switch (args.length){
                                case 8:
                                    if (!checkClothes(args[2],args[3],args[4],args[5], args[6],args[7])) return;
                                    dp3.addProduct(args[2],args[3],Integer.parseInt(args[4]),Integer.parseInt(args[5]), Category.valueOf(args[6].toLowerCase()),args[7]); System.exit(0);
                                case 10:
                                    if (!checkBedding(args[2],args[3],args[4],args[5], args[6],args[7],args[8],args[9])) return;
                                    dp3.addProduct(args[2],args[3],Integer.parseInt(args[4]),Integer.parseInt(args[5]), Category.valueOf(args[6].toLowerCase()),
                                            Integer.parseInt(args[7]),Integer.parseInt(args[8]),Integer.parseInt(args[9])); System.exit(0);
                                default: log.info(WRONG_QTY_PARAMS); return;
                            }

                        case GETBYID:
                            if (args.length != 3) { log.info(WRONG_QTY_PARAMS); return; }
                            if (!check(ID,args[2])) return;
                            dp3.getProductById(Long.parseLong(args[2])); System.exit(0);

                        case GETBYARTICLE:
                            if (args.length != 3) { log.info(WRONG_QTY_PARAMS); return; }
                            if (!check(ARTICLE,args[2])) return;
                            dp3.getProductByArticle(args[2]); System.exit(0);

                        case DELBYID:
                            if (args.length != 3) { log.info(WRONG_QTY_PARAMS); return; }
                            if (!check(ID,args[2])) return;
                            dp3.deleteProductById(Long.parseLong(args[2])); System.exit(0);

                        case DELBYARTICLE:
                            if (args.length != 3) { log.info(WRONG_QTY_PARAMS); return; }
                            if (!check(ARTICLE,args[2])) return;
                            dp3.deleteProductByArticle(args[2]); System.exit(0);

                        case CHANGE:
                            switch (args.length){
                                case 8:
                                    if (!checkClothes(args[2],args[3],args[4],args[5], args[6],args[7])) return;
                                    dp3.changeProduct(args[2],args[3],Integer.parseInt(args[4]),Integer.parseInt(args[5]), Category.valueOf(args[6].toLowerCase()),args[7]); System.exit(0);
                                case 10:
                                    if (!checkBedding(args[2],args[3],args[4],args[5], args[6],args[7],args[8],args[9])) return;
                                    dp3.changeProduct(args[2],args[3],Integer.parseInt(args[4]),Integer.parseInt(args[5]), Category.valueOf(args[6].toLowerCase()),
                                            Integer.parseInt(args[7]),Integer.parseInt(args[8]),Integer.parseInt(args[9])); System.exit(0);
                                default: log.info(WRONG_QTY_PARAMS); return;
                            }

                        case SHOWALL:
                            if (args.length != 2) { log.info(WRONG_QTY_PARAMS); return; }
                            dp3.showAllLists(); System.exit(0);

                        case SHOWZERO:
                            if (args.length != 2) { log.info(WRONG_QTY_PARAMS); return; }
                            dp3.showProductsWithZeroBalance(); System.exit(0);

                        case SHOWBYNAME:
                            if (args.length != 3) { log.info(WRONG_QTY_PARAMS); return; }
                            if (!check(NAMEPART,args[2])) return;
                            dp3.showProductsByName(args[2]); System.exit(0);

                        case DOC:
                            if (args.length != 5) { log.info(WRONG_QTY_PARAMS); return; }
                            if (!checkDocument(args[2],args[3],args[4])) return;
                            dp3.issueSaleWriteoffPosting(args[2],Integer.parseInt(args[3]), TypeOfDoc.valueOf(args[4].toLowerCase())); System.exit(0);

                        default: log.info(WRONG_COMMAND); return;
                    }


                case LIST:
                case MAP:
                case SET:
                    switch (args[1].toUpperCase()){
                        case ADD:
                            if (args.length != 7) { log.info(WRONG_QTY_PARAMS); return; }
                            if (!checkTextileProduct(args[2],args[3],args[4],args[5],args[6])) return;
                            dp4.addProduct(args[2],args[3],Integer.parseInt(args[4]),Integer.parseInt(args[5]), Category.valueOf(args[6].toLowerCase())); System.exit(0);

                        case GETBYARTICLE:
                            if (args.length != 3) { log.info(WRONG_QTY_PARAMS); return; }
                            if (!check(ARTICLE,args[2])) return;
                            dp4.getProductByArticle(args[2]); System.exit(0);

                        case CHANGE:
                            if (args.length != 7) { log.info(WRONG_QTY_PARAMS); return; }
                            if (!checkTextileProduct(args[2],args[3],args[4],args[5],args[6])) return;
                            dp4.changeProduct(args[2],args[3],Integer.parseInt(args[4]),Integer.parseInt(args[5]), Category.valueOf(args[6].toLowerCase())); System.exit(0);

                        case ISSUESALE:
                            if (args.length != 3) { log.info(WRONG_QTY_PARAMS); return; }
                            String[] articles = args[2].split(COMMA);
                            if (!checkArticles(articles)) return;
                            dp4.issueSale(articles); System.exit(0);

                        case GETSALE:
                            if (args.length != 3) { log.info(WRONG_QTY_PARAMS); return; }
                            if (!check(ID,args[2])) return;
                            dp4.getSale(Long.parseLong(args[2])); System.exit(0);

                        case CHANGESALE:
                            if (args.length != 4) { log.info(WRONG_QTY_PARAMS); return; }
                            articles = args[2].split(COMMA);
                            if (!checkArticles(articles)) return;
                            if (!check(ID,args[3])) return;
                            dp4.updateSale(articles,Long.parseLong(args[3])); System.exit(0);

                        case DELSALE:
                            if (args.length != 3) { log.info(WRONG_QTY_PARAMS); return; }
                            if (!check(ID,args[2])) return;
                            dp4.deleteSale(Long.parseLong(args[2])); System.exit(0);

                        default: log.info(WRONG_COMMAND); return;
                    }

                case MANYONE:
                    switch (args[1].toUpperCase()){
                        case ADD:
                            if (args.length != 7) { log.info(WRONG_QTY_PARAMS); return; }
                            if (!checkTextileProduct(args[2],args[3],args[4],args[5],args[6])) return;
                            dp5manyone.addProduct(args[2],args[3],Integer.parseInt(args[4]),Integer.parseInt(args[5]), Category.valueOf(args[6].toLowerCase())); System.exit(0);

                        case GETBYARTICLE:
                            if (args.length != 3) { log.info(WRONG_QTY_PARAMS); return; }
                            if (!check(ARTICLE,args[2])) return;
                            dp5manyone.getProductByArticle(args[2]); System.exit(0);

                        case DOC:
                            if (args.length < 4) { log.info(WRONG_QTY_PARAMS); return; }
                            if (!setDocs(args)) return;
                            dp5manyone.issueSaleWriteoffPosting(args[2],docs); System.exit(0);

                        case RUNTIME:
                            if (args.length != 2) { log.info(WRONG_QTY_PARAMS); return; }
                            dp5manyone.compareRuntime(); System.exit(0);

                        default: log.info(WRONG_COMMAND); return;
                    }

                case ONEONE:
                    switch (args[1].toUpperCase()){
                        case ADD:
                            if (args.length != 8) { log.info(WRONG_QTY_PARAMS); return; }
                            if (!checkBed(args[2],args[3],args[4],args[5],args[6],args[7])) return;
                            dp5oneone.addProduct(args[2],args[3],Integer.parseInt(args[4]),Integer.parseInt(args[5]),Integer.parseInt(args[6]),Integer.parseInt(args[7])); System.exit(0);

                        case RUNTIME:
                            if (args.length != 2) { log.info(WRONG_QTY_PARAMS); return; }
                            dp5oneone.compareRuntime(); System.exit(0);

                        default: log.info(WRONG_COMMAND); return;
                    }

                default: log.info(WRONG_COMMAND);
            }

        } catch (Exception e) { log.error(e); }

    }


    private static boolean setDocs(String[] args){
        try {
            boolean check = true;
            for (int i = 3; i < args.length; i++) {
                String[] docparams = args[i].split(COMMA);
                if (!check(QUANTITY,docparams[0])) check = false;
                if (!check(TYPE,docparams[1])) check = false;
                if (!check) continue;
                Document doc = new Document(Integer.parseInt(docparams[0]),TypeOfDoc.valueOf(docparams[1].toLowerCase()));
                docs.add(doc);
            }
            return check;
        } catch (Exception e) { log.error(e); return false; }
    }
    private static boolean check(String key, String value) {
        switch (key){
            case CATEGORY:
                if(Arrays.stream(Category.values()).anyMatch(c -> c.toString().equals(value.toLowerCase()))) return true;
                log.info(WRONG_CATEGORY+value); break;

            case TYPE:
                if(Arrays.stream(TypeOfDoc.values()).anyMatch(t -> t.toString().equals(value.toLowerCase()))) return true;
                log.info(WRONG_TYPE+value); break;

            case ID:
                if (value.matches(PATTERN_ID)) return true;
                log.info(WRONG_ID+value); break;

            case DESCRIPTION:
                if (value.matches(PATTERN_DESCRIPTION)&&!value.matches(PATTERN_ONLY_NUMS)) return true;
                log.info(WRONG_DESCRIPTION+value); break;

            case CHK:
                if (value.matches(PATTERN_CHECK)) return true;
                log.info(WRONG_CHECK+value); break;

            case COUNTRY:
                if (value.matches(PATTERN_COUNTRY)) return true;
                log.info(WRONG_COUNTRY+value); break;

            case CITY:
                if (value.matches(PATTERN_CITY)) return true;
                log.info(WRONG_CITY+value); break;

            case POSTCODE:
                if (value.matches(PATTERN_POSTCODE)) return true;
                log.info(WRONG_POSTCODE+value); break;

            case ARTICLE:
                if (value.matches(PATTERN_ARTICLE)) return true;
                log.info(WRONG_ARTICLE+value); break;

            case PRICE:
                if (value.matches(PATTERN_PRICE)) return true;
                log.info(WRONG_PRICE+value); break;

            case QUANTITY:
                if (value.matches(PATTERN_QTY)) return true;
                log.info(WRONG_QTY+value); break;

            case NAME:
                if (value.matches(PATTERN_NAME)&&!value.matches(PATTERN_ONLY_NUMS)) return true;
                log.info(WRONG_NAME+value); break;

            case NAMEPART:
                if (value.matches(PATTERN_NAME)) return true;
                log.info(WRONG_NAME+value); break;

            case SIZE:
                if (value.matches(PATTERN_SIZE)) return true;
                log.info(WRONG_SIZE+value); break;

            case LENGTH:
                if (value.matches(PATTERN_BED_SIZES)) return true;
                log.info(WRONG_LENGTH+value); break;

            case WIDTH:
                if (value.matches(PATTERN_BED_SIZES)) return true;
                log.info(WRONG_WIDTH+value); break;

            case HEIGHT:
                if (value.matches(PATTERN_BED_SIZES)) return true;
                log.info(WRONG_HEIGHT+value); break;
        }
        return false;
    }
    private static boolean checkTestEntity(String name, String description, String chk, String country, String city, String postcode){
        boolean check=true;
        if (!check(NAME,name)) check=false;
        if (!check(DESCRIPTION,description)) check=false;
        if (!check(CHK,chk)) check=false;
        if (!check(COUNTRY,country)) check=false;
        if (!check(CITY,city)) check=false;
        if (!check(POSTCODE,postcode)) check=false;
        return check;
    }
    private static boolean checkTextileProduct(String article, String name, String price, String quantity, String category){
        boolean check=true;
        if (!check(ARTICLE,article)) check=false;
        if (!check(NAME,name)) check=false;
        if (!check(PRICE,price)) check=false;
        if (!check(QUANTITY,quantity)) check=false;
        if (!check(CATEGORY,category)) check=false;
        return check;
    }
    private static boolean checkClothes(String article, String name, String price, String quantity, String category, String size){
        boolean check=true;
        if (!checkTextileProduct(article, name, price, quantity, category)) check=false;
        if (!check(SIZE,size)) check=false;
        return check;
    }
    private static boolean checkBedding(String article, String name, String price, String quantity, String category, String length, String width, String height){
        boolean check=true;
        if (!checkTextileProduct(article, name, price, quantity, category)) check=false;
        if (!checkBeddingSizes(length,width,height)) check=false;;
        return check;
    }
    private static boolean checkBed(String article, String name, String price, String length, String width, String height){
        boolean check=true;
        if (!check(ARTICLE,article)) check=false;
        if (!check(NAME,name)) check=false;
        if (!check(PRICE,price)) check=false;
        if (!checkBeddingSizes(length,width,height)) check=false;;
        return check;
    }
    private static boolean checkBeddingSizes(String length, String width, String height){
        boolean check=true;
        if (!check(LENGTH,length)) check=false;
        if (!check(WIDTH,width)) check=false;
        if (!check(HEIGHT,height)) check=false;
        return check;
    }
    private static boolean checkDocument(String article, String qty, String type){
        boolean check=true;
        if (!check(ARTICLE,article)) check=false;
        if (!check(QUANTITY,qty)) check=false;
        if (!check(TYPE,type)) check=false;
        return check;
    }
    private static boolean checkArticles(String[] articles){
        boolean check=true;
        for (String article : articles) if (!check(ARTICLE, article)) check = false;
        return check;
    }
}