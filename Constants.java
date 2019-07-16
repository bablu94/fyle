package IndianBanks;


public class Constants {
    public static final String SQL_QUERY_1 = "SELECT * FROM BANK_BRANCHES WHERE IFSC='%s'";
    public static final String SQL_QUERY_2 = "SELECT * FROM BANK_BRANCHES WHERE BANK_NAME='%s' AND CITY='%s'";
    public static final String SQL_QUERY_3 = "SELECT NAME FROM BANKS";
}
