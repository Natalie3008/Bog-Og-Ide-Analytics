package databaseLayer;

import java.sql.SQLException;
import java.util.ArrayList;
import modelLayer.TargetedCategory;


public interface TargetedCategoryDBIF {
	
	public TargetedCategory createTargetedCategory(TargetedCategory targetedCategory) throws SQLException;
	
	public TargetedCategory updateTargetedCategory(TargetedCategory targetedCategory) throws SQLException;
	
	public ArrayList<TargetedCategory> getAllTargetedCategories() throws SQLException;
	
	public boolean deleteTargetedCategory(int targetedCategoryID) throws SQLException;
}
