package controlLayer;

import java.sql.*;
import databaseLayer.*;
import modelLayer.*;

public class SaleCtrl {
	private SaleDB saleDb;
	/** private EmployeeCtrl employeeCtrl */
	private ProductCtrl productCtrl;

	public SaleCtrl() {

		saleDb = new SaleDB();
		productCtrl = new ProductCtrl();
		/** EmployeeCtrl employerCtrl*/
		

	}

	public void getSaleInformation(int ID, int amount, Date date, String ageCategory, String paymentMethod, Employee employee) {
		
		
	}

}
