package databaseLayer;

import java.sql.SQLException;
import java.util.List;

import modelLayer.*;

public interface SaleDBIF {

	List<Sale> getSaleInformation() throws SQLException;
}
