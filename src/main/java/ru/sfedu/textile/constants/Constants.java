package ru.sfedu.textile.constants;

public class Constants {

    // CONFIGURATIONS

    public static final String H_CFG_PATH = "src/main/resources/hibernate.cfg.xml";
    public static final String H_CFG_JAR = "hibernate.cfg.xml";

    public static final String CONFIG_LOG_KEY = "log4j2.configurationFile";
    public static final String CONFIG_LOG_PATH = "src/main/resources/log4j2.properties";
    public static final String CONFIG_LOG_JAR = "log4j2.properties";

    // SCHEMAS

    public static final String SCHEMA_EX1 = "ex1";
    public static final String SCHEMA_EX2 = "ex2";
    public static final String SCHEMA_EX3_SINGLE_TABLE = "ex3_single_table";
    public static final String SCHEMA_EX3_TABLE_PER_CLASS = "ex3_table_per_class";
    public static final String SCHEMA_EX3_JOINED_TABLES = "ex3_joined_tables";
    public static final String SCHEMA_EX3_MAPPED_SUPERCLASS = "ex3_mapped_superclass";
    public static final String SCHEMA_EX4_SET = "ex4_set";
    public static final String SCHEMA_EX4_LIST = "ex4_list";
    public static final String SCHEMA_EX4_MAP = "ex4_map";
    public static final String SCHEMA_EX5_MANYONE = "ex5_manyone";
    public static final String SCHEMA_EX5_ONEONE = "ex5_oneone";

    // TABLES

    // lab 2

    public static final String TEST_ENTITY = "testentity";
    public static final String ADDRESS = "address";

    // lab 3

    public static final String JOINED_TEXTILE_PRODUCT = "joined_textile_product";
    public static final String JOINED_BEDDDING = "joined_bedding";
    public static final String JOINED_CLOTHES = "joined_clothes";
    public static final String JOINED_DOCUMENT = "joined_document";

    public static final String MAPPED_TEXTILE_PRODUCT = "mapped_textile_product";
    public static final String MAPPED_BEDDDING = "mapped_bedding";
    public static final String MAPPED_CLOTHES = "mapped_clothes";
    public static final String MAPPED_DOCUMENT = "mapped_document";

    public static final String SINGLE_TEXTILE_PRODUCT = "single_textile_product";
    public static final String SINGLE_BEDDDING = "single_bedding";
    public static final String SINGLE_CLOTHES = "single_clothes";
    public static final String SINGLE_DOCUMENT = "single_document";

    public static final String PERCLASS_TEXTILE_PRODUCT = "perclass_textile_product";
    public static final String PERCLASS_BEDDDING = "perclass_bedding";
    public static final String PERCLASS_CLOTHES = "perclass_clothes";
    public static final String PERCLASS_DOCUMENT = "perclass_document";

    // lab 4

    public static final String TEXTILE_PRODUCT_EX4 = "textile_product_ex4";

    public static final String LIST_PRODUCTS = "list_products";
    public static final String LIST_SALES = "list_sales";

    public static final String MAP_PRODUCTS = "map_products";
    public static final String MAP_SALES = "map_sales";

    public static final String SET_PRODUCTS = "set_products";
    public static final String SET_SALES = "set_sales";

    // lab 5

    public static final String MANYONE_TEXTILE_PRODUCT = "manyone_textile_product";
    public static final String MANYONE_DOCUMENT = "manyone_document";

    public static final String ONEONE_BEDDING_PRODUCT = "oneone_bedding";
    public static final String ONEONE_SIZES = "oneone_sizes";

    // COLUMNS
    public static final String INT_UNSIGNED = "INT UNSIGNED";
    public static final String CHECK = "`check`";
    public static final String PRODUCT_ID = "product_id";

    public static final String DISCRIMINATOR_COLUMN_NAME = "data_type";
    public static final String DISCR_VALUE_CLO = "c";
    public static final String DISCR_VALUE_BED = "b";

    // QUERIES

    public static final String SELECT_ALL_FROM = "SELECT * FROM ";
    public static final String FROM = "FROM ";

    public static final String GET_DB_SCHEMAS = "SELECT table_schema FROM information_schema.TABLES GROUP BY table_schema";
    public static final String GET_DB_SIZES = "SELECT ROUND(SUM(data_length + index_length) / 1024 / 1024, 4) FROM information_schema.tables GROUP BY table_schema";
    public static final String GET_LIST_USERS = "SELECT User FROM mysql.user";
    public static final String SHOW_TABLES = "SELECT table_name FROM information_schema.tables WHERE table_schema=";
    public static final String GET_COUNT_OF_TABLES = "SELECT count(table_name) FROM information_schema.tables WHERE table_schema=";

    // FOR MAIN CLI

    public static final String EMPTY_COMMAND = "You are trying to enter an empty command. Try again";
    public static final String WRONG_QTY_PARAMS = "Wrong quantity of parameters";
    public static final String WRONG_COMMAND = "Wrong command";

    public static final String WRONG_ID = "Wrong id: ";
    public static final String WRONG_DESCRIPTION = "Wrong description: ";
    public static final String WRONG_CHECK = "Wrong check value: ";
    public static final String WRONG_COUNTRY = "Wrong country: ";
    public static final String WRONG_CITY = "Wrong city: ";
    public static final String WRONG_POSTCODE = "Wrong postcode: ";
    public static final String WRONG_ARTICLE = "Wrong article: ";
    public static final String WRONG_NAME = "Wrong name: ";
    public static final String WRONG_PRICE = "Wrong price: ";
    public static final String WRONG_QTY = "Wrong quantity: ";
    public static final String WRONG_SIZE = "Wrong size: ";
    public static final String WRONG_LENGTH = "Wrong length: ";
    public static final String WRONG_WIDTH = "Wrong width: ";
    public static final String WRONG_HEIGHT = "Wrong height: ";
    public static final String WRONG_CATEGORY = "Wrong category: ";
    public static final String WRONG_TYPE = "Wrong type of document: ";
    public static final String WRONG_DATA = "Wrong data: ";

    public static final String DBINFO = "DBINFO";
    public static final String TESTENTITY = "TESTENTITY";
    public static final String JOINED_TABLES = "JOINED";
    public static final String MAPPED_SUPERCLASS = "MAPPED";
    public static final String SINGLE_TABLE = "SINGLE";
    public static final String TABLE_PER_CLASS = "PERCLASS";
    public static final String LIST = "LIST";
    public static final String MAP = "MAP";
    public static final String SET = "SET";
    public static final String MANYONE = "MANYONE";
    public static final String ONEONE = "ONEONE";

    public static final String CREATE = "CREATE";
    public static final String READ = "READ";
    public static final String UPDATE = "UPDATE";
    public static final String DELETE = "DELETE";
    public static final String ADD = "ADD";
    public static final String GETBYID = "GETBYID";
    public static final String GETBYARTICLE = "GETBYARTICLE";
    public static final String DELBYID = "DELBYID";
    public static final String DELBYARTICLE = "DELBYARTICLE";
    public static final String CHANGE = "CHANGE";
    public static final String SHOWALL = "SHOWALL";
    public static final String SHOWZERO = "SHOWZERO";
    public static final String SHOWBYNAME = "SHOWBYNAME";
    public static final String DOC = "DOC";
    public static final String RUNTIME = "RUNTIME";
    public static final String ISSUESALE = "SALE";
    public static final String GETSALE = "GETSALE";
    public static final String CHANGESALE = "CHANGESALE";
    public static final String DELSALE = "DELSALE";

    public static final String CATEGORY = "category";
    public static final String TYPE = "type";
    public static final String ARTICLE = "article";
    public static final String NAME = "name";
    public static final String NAMEPART = "namepart";
    public static final String PRICE = "price";
    public static final String QUANTITY = "quantity";
    public static final String SIZE = "size";
    public static final String LENGTH = "length";
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";
    public static final String ID = "id";
    public static final String DESCRIPTION = "description";
    public static final String CHK = "check";
    public static final String COUNTRY = "country";
    public static final String CITY =  "city";
    public static final String POSTCODE = "postcode";

    public static final String PATTERN_ID = "[\\d]{1,19}";
    public static final String PATTERN_DESCRIPTION = ".{1,150}";
    public static final String PATTERN_CHECK = "[1|0]";
    public static final String PATTERN_COUNTRY = "[a-zA-Zа-яА-я_]{1,19}";
    public static final String PATTERN_CITY = "[a-zA-Zа-яА-я_]{1,19}";
    public static final String PATTERN_POSTCODE = "[\\d]{1,10}";
    public static final String PATTERN_ARTICLE = "[\\wа-яА-я-]{1,10}";
    public static final String PATTERN_NAME = ".{1,50}";
    public static final String PATTERN_PRICE = "[\\d]{1,7}";
    public static final String PATTERN_QTY = "[\\d]{1,5}";
    public static final String PATTERN_SIZE = "[\\wа-яА-я-]{1,10}";
    public static final String PATTERN_BED_SIZES = "[\\d]{1,3}";
    public static final String PATTERN_ONLY_NUMS = "[\\d]+";


    // FOR DATA PROVIDERS

    public static final String SUCCESS_ADD_DOCUMENT = "SUCCESSFULLY ADDED TO DOCUMENT";
    public static final String NOT_ISSUED = "NOT ISSUED";
    public static final String NOT_ENOUGH_QTY = "YOU HAVE NOT ENOUGH QUANTITY OF THE PRODUCT";
    public static final String NO_ZERO = "NO PRODUCTS WITH ZERO BALANCE";
    public static final String NO_PRODUCT_NAME = "NO PRODUCTS WITH SUCH NAME";
    public static final String NO_DATA = "No data";
    public static final String NO_ENTITY = "NO SUCH ENTITY WITH ID ";
    public static final String GEN_ID = "GENERATED ID: ";
    public static final String NOT_ADDED = "OBJECT IS NOT ADDED";
    public static final String RECEIVED_ENTITY = "RECEIVED ENTITY: ";
    public static final String NOT_RECEIVED = "OBJECT IS NOT RECEIVED";
    public static final String UPDATED = "UPDATED";
    public static final String NOT_UPDATED = "NOT UPDATED";
    public static final String DELETED = "DELETED";
    public static final String NOT_DELETED = "NOT DELETED";
    public static final String NO_PRODUCT = ", NO SUCH PRODUCT";
    public static final String PRODUCT_EXISTS = ", PRODUCT ALREADY EXISTS";

    public static final String WITH_ARTICLE = " WITH ARTICLE ";
    public static final String WITH_ID = " WITH ID ";
    public static final String SALE = " SALE";
    public static final String PRODUCT = " PRODUCT";

    public static final String CRITERIA = "CRITERIA";
    public static final String NATIVE_SQL = "NATIVE SQL";
    public static final String HQL = "HQL";
    public static final String CRITERIA_RUNTIME = "CRITERIA RUNTIME: ";
    public static final String NATIVE_SQL_RUNTIME = "NATIVE SQL RUNTIME: ";
    public static final String HQL_RUNTIME = "HQL RUNTIME: ";
    public static final String SECONDS = " seconds";

    public static final String DB_SIZES = "\nDATABASE SIZES: \n";
    public static final String MB = " MB";
    public static final String LIST_USERS = "\nLIST OF USERS: \n";
    public static final String LIST_TABLES = "\nLIST OF TABLES: \n";
    public static final String DATABASES = "\nDATABASES: \n";
    public static final String COUNT_OF_TABLES = "\nCOUNT OF TABLES: \n";

    // OTHER

    public static final String SPACE = " ";
    public static final String NEW_LINE = "\n";
    public static final String DOUBLE_QUOTE = "\"";
    public static final String PERCENT = "%";
    public static final String COMMA = ",";
    public static final String ONE = "1";
}
