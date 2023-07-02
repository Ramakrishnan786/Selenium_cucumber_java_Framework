
package myUtility;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCCon {
	
	public static void updateMethod() throws Exception {
		String url ="jdbc:sql://localhost:3306/mydata";
		String username ="root";
		String password ="Ramakrishnan@123";
	
	}
	
	public static void getMethod() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/mydata";
		String username = "root";
		String password = "Ramakrishnan@123";
		String query = "select * from Student";
		Connection con = DriverManager.getConnection(url,username,password);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
//		System.out.print("Account number       ");
//		System.out.print("Customer Name is     ");
//		System.out.print("Bank Balance is      ");
//		System.out.print("Pass code is          ");
					
//		while(rs.next()) {
//			
////			System.out.println(rs.getInt("ac_no")+"");
////			System.out.print(rs.getString("cname")+"");
////			System.out.print(" "+rs.getString("balance"));
////			System.out.print("  "+rs.getInt("pass_code"));
////			System.out.println(rs.getString(1));
////			System.out.println(rs.getInt(0));
//			if(rs.getInt(4)>=90) {
//			System.out.println(rs.getString("Name"));
//			System.out.println(rs.getInt("RollNumber"));
//			System.out.println(rs.getString("courseName"));
//			System.out.println(rs.getInt(4));
//			}
//		}
		
		Map<String, List<Object>> resultMap = new HashMap<>();

		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();

		while (rs.next()) {
		    for (int i = 1; i <= columnCount; i++) {
		        String columnName = metaData.getColumnName(i);
		        Object value = rs.getObject(i);
		        if (!resultMap.containsKey(columnName)) {
		            resultMap.put(columnName, new ArrayList<>());
		        }
		        resultMap.get(columnName).add(value);
		    }
		}
		
		System.out.println(resultMap);
		
	}
	
	
	
	public static void insertMethod() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/mydata";
		String username = "root";
		String password = "Ramakrishnan@123";
		int accountNUmber = 6;
		String name ="Prasanth";
		String balance = "500000";
		int passcode = 2000;
		String query = "insert into customer values ("+accountNUmber+",'"+name+"','"+balance+"',"+passcode+");";
		Connection con = DriverManager.getConnection(url,username,password);
		Statement st = con.createStatement();
		int rs = st.executeUpdate(query);
		System.out.println("NUmber of Rows UPdated : "+rs);
		System.out.println(query);
		
		
	}
	
	public static void insertMethodps() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/mydata";
		String username = "root";
		String password = "Ramakrishnan@123";
		String name = "Vinish";
		int rollNumber =0027;
		String courseName ="Maths";
		int score = 97;
		String query = "insert into Student values (?,?,?,?)";
		Connection con = DriverManager.getConnection(url,username,password);
		PreparedStatement ps =  con.prepareStatement(query);
		ps.setString(1,name);
		ps.setInt(2,rollNumber);
		ps.setString(3,courseName);
		ps.setInt(4,score);
		
		int rs= ps.executeUpdate();
		System.out.println("NUmber of Rows UPdated : "+rs);
		System.out.println(query);
		
		
	}
	
	public static void deleteMethod() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/mydata";
		String username = "root";
		String password = "Ramakrishnan@123";
		int accountNUmber = 7;
		String name ="Virat Kohli ";
		String balance = "500000";
		int passcode = 2000;
		String query = "delete from customer where ac_no = ?";
		Connection con = DriverManager.getConnection(url,username,password);
		PreparedStatement ps =  con.prepareStatement(query);
		ps.setInt(1,accountNUmber);
//		ps.setString(2,name);
//		ps.setString(3,balance);
//		ps.setInt(4,passcode);
		
		int rs= ps.executeUpdate();
		System.out.println("NUmber of Rows UPdated : "+rs);
		System.out.println(query);
	}
	
	public static void storedProcedure() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/mydata";
		String username = "root";
		String password = "Ramakrishnan@123";
		
		int acn =1;
		Connection con = DriverManager.getConnection(url,username,password);
		
		CallableStatement cs =  con.prepareCall("{call GetCustomerByAcNum(?)}");
		cs.setInt(1,acn);
		ResultSet rs = cs.executeQuery();
		
		while(rs.next()) {

			System.out.print(rs.getInt("ac_no")+" ");
			System.out.print(rs.getString("cname")+"");
			System.out.print(" "+rs.getString("balance"));
			System.out.print("  "+rs.getInt("pass_code"));
			System.out.println();
		}
		
	}
	
	
	public static void storedProcedure2parameters() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/mydata";
		String username = "root";
		String password = "Ramakrishnan@123";
		
		int acn =3;
		Connection con = DriverManager.getConnection(url,username,password);
		
		CallableStatement cs =  con.prepareCall("{call GetCustomerByAcNum2parameters(?,?)}");
		cs.setInt(1,acn);
		cs.registerOutParameter(2,Types.VARCHAR);
		cs.executeUpdate();
		
		System.out.println(cs.getString(2));
		con.close();
		
	}
	
	public static void commit() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/mydata";
		String username = "root";
		String password = "Ramakrishnan@123";
		
		int acn =3;
		String query1 = "update customer set pass_code = 2000  where ac_no =1";
		String query2 = "update customer set pass_code = 2001  where ac_no =2";
		
		Connection con = DriverManager.getConnection(url,username,password);
		
		Statement cs =  con.createStatement();
		int row1 = cs.executeUpdate(query1);
		int row2 = cs.executeUpdate(query2);
		System.out.println("The number off rows affected "+(row1+row2));
		con.close();
		
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//insertMethodps();
		//deleteMethod();
		//storedProcedure();
		//storedProcedure2parameters();
		//commit();
		getMethod();
		//insertMethodps();
	}

}
