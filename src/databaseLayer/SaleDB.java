package databaseLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;

import modelLayer.Sale;

public abstract class SaleDB implements SaleDBIF {

		//I've added the same name for the return methods, bad practice?
	
	public List<Sale> getSaleInformation() throws SQLException {
		//Little unsure whether I should've placed these in or out of the try method. It seems unnecessary to have it outside of...
			ResultSet res = null;
			List<Sale> saleInformation = null;	
			Statement statement = DBConnection.getInstance().getConnection().createStatement();	
			try {
				res = statement.executeQuery("SELECT * FROM Sale");
				saleInformation = buildObjects(res);	
			}
				catch (SQLException e) {
					e.printStackTrace();
				}
			return saleInformation;
			}

	private List<Sale> buildObjects(ResultSet res) throws SQLException {
		ArrayList<Sale> saleInformation = new ArrayList<>();
		//This should be a while loop right? In case of while, I don't have to end it since next() does that once it is at the end? 
		if (res.next()) {
			Sale info = buildObject(res);
			saleInformation.add(info);
		}
		return saleInformation;
	}
	
	private Sale buildObject(ResultSet res) throws SQLException {
		Sale saleInformation = null;
		//Employee is null?
		saleInformation = new Sale(res.getInt("ID"), res.getDouble("totalPrice"), res.getDate("transactionDate"), res.getString("ageCategory"), res.getString("paymentMethod"), null);
		return saleInformation;
	}	
}



