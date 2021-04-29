package controlLayer;

import java.sql.*;
import databaseLayer.*;
import modelLayer.*;

public class SaleCtrl {
	private SaleDBIF saleDb;
	/** private EmployeeCtrl employeeCtrl */
	private ProductCtrl productCtrl;

	public SaleCtrl() {

		saleDb = (SaleDBIF) new SaleDB();
		productCtrl = newProductCtrl();
		/** EmployeeCtrl employerCtrl*/
		

	}

	public void getSaleInformation(int ID, int amount, Date date, String ageCategory, String paymentMethod, Employee employee) {
		
		
	}

}
