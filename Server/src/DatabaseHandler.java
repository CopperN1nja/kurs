import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler {
    Connection databaseCnct;
    private Statement statement;
    String insertStr = "";

    public DatabaseHandler() {
        dbConnection();
    }

    public void dbConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            databaseCnct = DriverManager.getConnection("jdbc:mysql://localhost/kurs", "root", "root");

            statement = databaseCnct.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void authorization (String[] action, ObjectOutputStream soos){
        ResultSet users = null;
        try {
            users = statement.executeQuery("SELECT * FROM users");


            String answer = "Error";
            while (users.next()) {
                if (users.getString("login").equals(action[1]) && users.getString("password").equals(action[2])) {

                    if (users.getString("role").equals("0")) answer = "User";
                    else answer = "Admin";
                }
            }
            users.close();
            soos.writeObject(answer);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void registration(String[] tmp, ObjectOutputStream soos) {
        int i = 0;
        int countId = 1;
        try {
            ResultSet users = statement.executeQuery("SELECT * FROM users ");
            while(users.next()){countId=Integer.parseInt(users.getString("id_user"));
            }
            countId ++;
            users.close();
            i = countId;
            insertStr = "INSERT INTO users VALUES ("
                    + quotate(Integer.toString(countId)) + ","
                    + quotate(tmp[1]) + ","
                    + quotate(tmp[2]) + ","
                    + quotate("0") + ")";
            statement.executeUpdate(insertStr);
            soos.writeObject("SUCCESS");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void createBusinessman(String[] tmp, ObjectOutputStream soos) {
        try {
            insertStr = "INSERT INTO `businessman`(`firm_name`, `firm_unp`, `business_branch`, `income`, `status`)" +
                    " VALUES ("
                    +quotate(tmp[1]) + ","
                    +quotate(tmp[2]) + ","
                    +quotate(tmp[3]) + ","
                    +quotate(tmp[4]) + ","
                    +quotate("0") + ")";
            statement.executeUpdate(insertStr);
            soos.writeObject("SUCCESS");
        }
        catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void showAllAcc(String[] tmp, ObjectOutputStream soos) {
        ArrayList<String> list = new ArrayList<>();
        try{
            ResultSet users = statement.executeQuery("SELECT * FROM users");
            while (users.next())
                list.add(users.getString("id_user") +
                        " " + users.getString("login") +
                        " " + users.getString("password") +
                        " " + users.getString("role"));
            users.close();
            soos.writeObject(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editAcc(String[] tmp, ObjectOutputStream soos) {
        try {
            System.out.println(tmp[0] + tmp[1] + tmp[2] + tmp[3]);
            PreparedStatement preparedStatement = databaseCnct.prepareStatement("UPDATE users SET " + tmp[2] + "=? WHERE id_user=?");
            preparedStatement.setString(1,tmp[3]);
            preparedStatement.setInt(2, Integer.parseInt(tmp[1]));
            preparedStatement.executeUpdate();
            soos.writeObject("123");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteAc(String[] tmp, ObjectOutputStream soos) {
        try{
            insertStr = "DELETE FROM users WHERE `id_user` = (" + quotate(tmp[1]) + ")";
            statement.executeUpdate(insertStr);
            soos.writeObject("SUCCESS");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void srchAc(String[] tmp, ObjectOutputStream soos) {
        ArrayList<String> list = new ArrayList<>();
        try{
            ResultSet users = statement.executeQuery("SELECT * FROM users WHERE `id_user` = " + quotate(tmp[1]));
            while (users.next()){
                list.add(users.getString("id_user") +
                        " " + users.getString("login") +
                        " " + users.getString("password") +
                        " " + users.getString("role"));
            }
            users.close();
            soos.writeObject(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAllBusinessman(String[] tmp, ObjectOutputStream soos) {
        ArrayList<String> list = new ArrayList<>();
        try{
            ResultSet users = statement.executeQuery("SELECT * FROM businessman WHERE status != 0 ");
            while (users.next())
                list.add(users.getString("id_businessman") +
                        " " + users.getString("firm_name") +
                        " " + users.getString("firm_unp") +
                        " " + users.getString("business_branch") +
                        " " + users.getString("income") +
                        " " + users.getString("tax") +
                        " " + users.getString("tax_sum") +
                        " " + users.getString("profit") +
                        " " + users.getString("status"));
            users.close();
            soos.writeObject(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAllTax(String[] tmp, ObjectOutputStream soos) {
        ArrayList<String> list = new ArrayList<>();
        try{
            ResultSet users = statement.executeQuery("SELECT * FROM tax");
            while (users.next())
                list.add(users.getString("id_tax") +
                        " " + users.getString("tax_name") +
                        " " + users.getString("tax_value"));

            users.close();
            soos.writeObject(list);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAllBusinessBranch(String[] tmp, ObjectOutputStream soos) {
        ArrayList<String> list = new ArrayList<>();
        try{
            ResultSet users = statement.executeQuery("SELECT * FROM business_branch");
            while (users.next())
                list.add(users.getString("id_branch") +
                        " " + users.getString("name"));
            users.close();
            soos.writeObject(list);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAllTaxBranch(String[] tmp, ObjectOutputStream soos) {
        ArrayList<String> list = new ArrayList<>();
        try{
            ResultSet users = statement.executeQuery("SELECT name, tax_name from business_branch JOIN tax_branch on" +
                    " business_branch.id_branch = tax_branch.id_branch JOIN tax ON tax_branch.id_tax = tax.id_tax");
            while (users.next())
                list.add(users.getString("name") +
                        " " + users.getString("tax_name"));
            users.close();
            soos.writeObject(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTax(String[] tmp, ObjectOutputStream soos) {
        try {
            insertStr = "INSERT INTO `tax`(`tax_name`, `tax_value`) VALUES ("
                    +quotate(tmp[1]) + ","
                    +quotate(tmp[2]) + ")";
            statement.executeUpdate(insertStr);
            soos.writeObject("SUCCESS");
        }
        catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTax(String[] tmp, ObjectOutputStream soos) {
        try{
            insertStr = "DELETE FROM `tax` WHERE id_tax = " + quotate(tmp[1]);
            statement.executeUpdate(insertStr);
            soos.writeObject("SUCCESS");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addBuisBranch(String[] tmp, ObjectOutputStream soos) {
        try {
            insertStr = "INSERT INTO `business_branch`( `name`) VALUES ("
                    +quotate(tmp[1]) + ")";
            statement.executeUpdate(insertStr);
            soos.writeObject("SUCCESS");
        }
        catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBuisBranch(String[] tmp, ObjectOutputStream soos) {
        try{
            insertStr = "DELETE FROM `business_branch` WHERE id_branch = " + quotate(tmp[1]);
            statement.executeUpdate(insertStr);
            soos.writeObject("SUCCESS");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTaxBranch(String[] tmp, ObjectOutputStream soos) {
        try {
            insertStr = "INSERT INTO `tax_branch`(`id_branch`, `id_tax`) VALUES ("
                    +quotate(tmp[1]) + ","
                    +quotate(tmp[2]) + ")";
            statement.executeUpdate(insertStr);
            soos.writeObject("SUCCESS");
        }
        catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTaxBranch(String[] tmp, ObjectOutputStream soos) {
        try{
            insertStr = "DELETE FROM `tax_branch` WHERE id_branch = " +quotate(tmp[1]) +
                    "  and id_tax = " + quotate(tmp[2]);
            statement.executeUpdate(insertStr);
            soos.writeObject("SUCCESS");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editTax(String[] tmp, ObjectOutputStream soos) {
        try {
            System.out.println(tmp[0] + tmp[1] + tmp[2] + tmp[3]);
            PreparedStatement preparedStatement = databaseCnct.prepareStatement("UPDATE tax SET " + tmp[2] + "=? WHERE id_tax=?");
            preparedStatement.setString(1,tmp[3]);
            preparedStatement.setInt(2, Integer.parseInt(tmp[1]));
            preparedStatement.executeUpdate();
            soos.writeObject("123");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showNewBusinessman(String[] tmp, ObjectOutputStream soos) {
        ArrayList<String> list = new ArrayList<>();
        try{
            ResultSet users = statement.executeQuery("SELECT id_businessman,firm_name,firm_unp,business_branch,income,status FROM businessman WHERE status = 0 ");
            while (users.next())
                list.add(users.getString("id_businessman") +
                        " " + users.getString("firm_name") +
                        " " + users.getString("firm_unp") +
                        " " + users.getString("business_branch") +
                        " " + users.getString("income") +
                        " " + users.getString("status"));
            users.close();
            soos.writeObject(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showBusinessmanForRecalculation(String[] tmp, ObjectOutputStream soos) {
        ArrayList<String> list = new ArrayList<>();
        try{
            ResultSet users = statement.executeQuery("SELECT * FROM businessman WHERE status = 2 ");
            while (users.next())
                list.add(users.getString("id_businessman") +
                        " " + users.getString("firm_name") +
                        " " + users.getString("firm_unp") +
                        " " + users.getString("business_branch") +
                        " " + users.getString("income") +
                        " " + users.getString("tax") +
                        " " + users.getString("tax_sum") +
                        " " + users.getString("profit") +
                        " " + users.getString("status"));
            users.close();
            soos.writeObject(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteBusinessman(String[] tmp, ObjectOutputStream soos) {
        try{
            insertStr = "DELETE FROM `businessman` WHERE id_businessman = " + quotate(tmp[1]);
            statement.executeUpdate(insertStr);
            soos.writeObject("SUCCESS");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void calculateBusinesTax(String[] tmp, ObjectOutputStream soos) {
        double count = 0;
        double income = 0;
        try {
            ResultSet rs = statement.executeQuery("SELECT SUM(col) FROM ( SELECT name, tax_name, tax_value as col FROM business_branch" +
                    " JOIN tax_branch on business_branch.id_branch = tax_branch.id_branch" +
                    " JOIN tax ON tax.id_tax = tax_branch.id_tax" +
                    " AND name = (SELECT business_branch FROM businessman WHERE id_businessman = " + quotate(tmp[1]) + "))t1");
            rs.next();
            count = rs.getInt("SUM(col)");
            rs.close();
            ResultSet rs2 = statement.executeQuery("SELECT income FROM businessman WHERE id_businessman = " + quotate(tmp[1]));
            rs2.next();
            income = rs2.getInt("income");
            rs2.close();
            double tax = count * income / 100;
            double profit = income - tax;
            System.out.println(tax);
            System.out.println(profit);
            insertStr = "UPDATE `businessman` SET `tax`= " + quotate(String.valueOf(tax))
                    + ",`tax_sum`= " + quotate(String.valueOf(tax))
                    + ",`profit`= " + quotate(String.valueOf(profit))
                    + ",`status`='1' WHERE id_businessman = " + quotate(tmp[1]);
            statement.executeUpdate(insertStr);
            soos.writeObject("SUCCESS");
        }
        catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void showBusinessmanCalculated(String[] tmp, ObjectOutputStream soos) {
        ArrayList<String> list = new ArrayList<>();
        try{
            ResultSet users = statement.executeQuery("SELECT * FROM businessman WHERE status = 1 ");
            while (users.next())
                list.add(users.getString("id_businessman") +
                        " " + users.getString("firm_name") +
                        " " + users.getString("firm_unp") +
                        " " + users.getString("business_branch") +
                        " " + users.getString("income") +
                        " " + users.getString("tax") +
                        " " + users.getString("tax_sum") +
                        " " + users.getString("profit") +
                        " " + users.getString("status"));
            users.close();
            soos.writeObject(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRecalculate(String[] tmp, ObjectOutputStream soos) {
        try {
            insertStr = "UPDATE `businessman` SET `income`= "
                    +quotate(tmp[2]) + ",`status`= 2 WHERE firm_unp ="
                    +quotate(tmp[1]);
            statement.executeUpdate(insertStr);
            soos.writeObject("SUCCESS");
        }
        catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void recalculateTax(String[] tmp, ObjectOutputStream soos) {
        double taxpercent = 0;
        double income = 0;
        double old_profit = 0;
        double old_tax = 0;
        try {
            ResultSet rs = statement.executeQuery("SELECT SUM(col) FROM ( SELECT name, tax_name, tax_value as col FROM business_branch" +
                    " JOIN tax_branch on business_branch.id_branch = tax_branch.id_branch" +
                    " JOIN tax ON tax.id_tax = tax_branch.id_tax" +
                    " AND name = (SELECT business_branch FROM businessman WHERE id_businessman = " +quotate(tmp[1]) + "))t1");
            rs.next();
            taxpercent = rs.getInt("SUM(col)");
            System.out.println(taxpercent);
            rs.close();
            ResultSet rs3 = statement.executeQuery("SELECT income FROM businessman WHERE id_businessman = " +quotate(tmp[1]));
            rs3.next();
            income = rs3.getInt("income");
            rs3.close();
            System.out.println(income);
            ResultSet rs2 = statement.executeQuery("SELECT profit FROM businessman WHERE id_businessman = " +quotate(tmp[1]));
            rs2.next();
            old_profit = rs2.getInt("profit");
            rs2.close();
            System.out.println(old_profit);
            ResultSet rs4 = statement.executeQuery("SELECT tax_sum FROM businessman WHERE id_businessman = " +quotate(tmp[1]));
            rs4.next();
            old_tax = rs4.getInt("tax_sum");
            rs4.close();
            double tax = taxpercent * income / 100;
            System.out.println(tax);
            double new_profit = income - tax;
            System.out.println(new_profit);
            double result_profit = old_profit + new_profit;
            System.out.println(result_profit);
            double new_tax = old_tax + tax;
            System.out.println(new_tax);
            insertStr = "UPDATE `businessman` SET `tax`= " + quotate(String.valueOf(tax))
                    + ",`tax_sum`= " + quotate(String.valueOf(new_tax))
                    + ",`profit`= " + quotate(String.valueOf(result_profit))
                    + ",`status`='1' WHERE id_businessman = " + quotate(tmp[1]);
            System.out.println(insertStr);
            statement.executeUpdate(insertStr);
            soos.writeObject("SUCCESS");
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            }

    public void allProfit(String[] tmp, ObjectOutputStream soos) {
        try {
            ResultSet rs = statement.executeQuery("SELECT SUM(profit) FROM businessman WHERE status = 1");
            while (rs.next()) {
                double  count = +rs.getDouble("SUM(profit)");
                System.out.println(statement);
                soos.writeObject(count);
                System.out.println(count);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void allIncome(String[] tmp, ObjectOutputStream soos) {
        try {
            ResultSet rs = statement.executeQuery("SELECT SUM(income) FROM businessman");
            while (rs.next()) {
                double  count = +rs.getDouble("SUM(income)");
                System.out.println(statement);
                soos.writeObject(count);
                System.out.println(count);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void taxAtBranch(String[] tmp, ObjectOutputStream soos) {
        try {
            ResultSet rs = statement.executeQuery("SELECT SUM(col) FROM ( SELECT tax_value as col FROM tax" +
                    " JOIN tax_branch ON tax.id_tax = tax_branch.id_tax and id_branch = " +quotate(tmp[1]) + ")t1");
            while (rs.next()) {
                int  count = +rs.getInt("SUM(col)");
                System.out.println(statement);
                soos.writeObject(count);
                System.out.println(count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sumAllTax(String[] tmp, ObjectOutputStream soos) {
        try {
            ResultSet rs = statement.executeQuery("SELECT SUM(tax_sum) FROM businessman WHERE status != 0");
            while (rs.next()) {
                double  count = +rs.getDouble("SUM(tax_sum)");
                System.out.println(statement);
                soos.writeObject(count);
                System.out.println(count);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String quotate(String content) {
        return "'" + content + "'";
    }

    public void sumNewTax(String[] tmp, ObjectOutputStream soos) {
        try {
            ResultSet rs = statement.executeQuery("SELECT SUM(tax) FROM businessman WHERE status != 0");
            while (rs.next()) {
                double count = +rs.getDouble("SUM(tax)");
                System.out.println(statement);
                soos.writeObject(count);
                System.out.println(count);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showBranchOfTax(String[] tmp, ObjectOutputStream soos) {
        ArrayList<String> list = new ArrayList<>();
        try{
            ResultSet users = statement.executeQuery("SELECT * FROM business_branch WHERE id_branch" +
                    " IN (SELECT id_branch FROM tax_branch WHERE id_tax = " +quotate(tmp[1]) + ")");
            while (users.next())
                list.add(users.getString("id_branch") +
                        " " + users.getString("name"));
            users.close();
            soos.writeObject(list);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showFirmProfHigther(String[] tmp, ObjectOutputStream soos) {
        ArrayList<String> list = new ArrayList<>();
        try{
            ResultSet users = statement.executeQuery("SELECT * FROM businessman WHERE profit > " +quotate(tmp[1]));
            while (users.next())
                list.add(users.getString("id_businessman") +
                        " " + users.getString("firm_name") +
                        " " + users.getString("firm_unp") +
                        " " + users.getString("business_branch") +
                        " " + users.getString("income") +
                        " " + users.getString("tax") +
                        " " + users.getString("tax_sum") +
                        " " + users.getString("profit") +
                        " " + users.getString("status"));
            users.close();
            soos.writeObject(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showFirmProfLower(String[] tmp, ObjectOutputStream soos) {
        ArrayList<String> list = new ArrayList<>();
        try{
            ResultSet users = statement.executeQuery("SELECT * FROM businessman WHERE profit < " +quotate(tmp[1]));
            while (users.next())
                list.add(users.getString("id_businessman") +
                        " " + users.getString("firm_name") +
                        " " + users.getString("firm_unp") +
                        " " + users.getString("business_branch") +
                        " " + users.getString("income") +
                        " " + users.getString("tax") +
                        " " + users.getString("tax_sum") +
                        " " + users.getString("profit") +
                        " " + users.getString("status"));
            users.close();
            soos.writeObject(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAllTaxOfFitrm(String[] tmp, ObjectOutputStream soos) {
        ArrayList<String> list = new ArrayList<>();
        try{
            ResultSet users = statement.executeQuery("SELECT tax.id_tax, tax_name, tax_value FROM tax join" +
                    " tax_branch on tax.id_tax = tax_branch.id_tax JOIN" +
                    " business_branch on tax_branch.id_branch = business_branch.id_branch and name =" +
                    " (SELECT business_branch FROM businessman WHERE id_businessman = " +quotate(tmp[1]) + ")");
            while (users.next())
                list.add(users.getString("id_tax") +
                        " " + users.getString("tax_name") +
                        " " + users.getString("tax_value"));

            users.close();
            soos.writeObject(list);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
