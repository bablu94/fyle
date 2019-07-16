package IndianBanks;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class IndianBanksDBService {
    private static Connection connection;

    private IndianBanksDBService(){
    }

    private static Connection getConnection(){
        if(connection == null){
            try {
                URI dbUri = new URI(System.getenv("DATABASE_URL"));
                String username = dbUri.getUserInfo().split(":")[0];
                String password = dbUri.getUserInfo().split(":")[1];
                String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
                connection = DriverManager.getConnection(dbUrl, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static BranchDetails getBranchDetailsFromIfsc(String ifsc){
        try {
            IndianBanksDBService indianBanksDBService = new IndianBanksDBService();
            Statement st = indianBanksDBService.getConnection().createStatement();
            ResultSet rs = st.executeQuery(String.format(Constants.SQL_QUERY_1, ifsc));
            if (rs.next()) {
                BranchDetails branchDetails = convertResultSetToBranchDetails(rs);
                return branchDetails;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<BranchDetails> getAllBankBranchesInCity(String bank_name, String city){
        try {
            IndianBanksDBService indianBanksDBService = new IndianBanksDBService();
            Statement st = indianBanksDBService.getConnection().createStatement();
            ResultSet rs = st.executeQuery(String.format(Constants.SQL_QUERY_2, bank_name, city));
            List <BranchDetails> branchDetailsList = new ArrayList<>();
            while (rs.next()) {
                BranchDetails branchDetails = convertResultSetToBranchDetails(rs);
                branchDetailsList.add(branchDetails);
            }
            return branchDetailsList;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getAllBankNames(){
        try {
            IndianBanksDBService indianBanksDBService = new IndianBanksDBService();
            Statement st = indianBanksDBService.getConnection().createStatement();
            ResultSet rs = st.executeQuery(Constants.SQL_QUERY_3);
            List<String> branches = new ArrayList<>();
            while (rs.next()) {
                branches.add(rs.getObject(1).toString());
            }
            return branches;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private static BranchDetails convertResultSetToBranchDetails(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        Integer numOfCol = rsmd.getColumnCount();
        HashMap<String,String> row = new HashMap<>();
        for(int i = 1; i <= numOfCol; i++)
        {
            row.put(rsmd.getColumnName(i), rs.getObject(i).toString());
        }
        BranchDetails branchDetails = new BranchDetails(row);
        return branchDetails;
    }
}
