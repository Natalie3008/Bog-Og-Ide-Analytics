package controlLayer;

import java.sql.SQLException;
import java.util.ArrayList;

import databaseLayer.TargetedCategoryDB;

import modelLayer.TargetedCategory;

public class TargetedCategoryCtrl {

	TargetedCategoryDB targetedCategoryDb = new TargetedCategoryDB();

	public boolean createTargetedCategory(TargetedCategory targetedCategory) throws SQLException {
		
		try {
			targetedCategoryDb.createTargetedCategory(targetedCategory);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
    	return true;	
	}
	
	public boolean updateTargetedCategory(TargetedCategory targetedCategory) throws SQLException {
		try {
			targetedCategoryDb.updateTargetedCategory(targetedCategory);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}

	public boolean deleteTargetedCategory(int targetedCategoryID) throws SQLException {
		try {
			targetedCategoryDb.deleteTargetedCategory(targetedCategoryID);	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public ArrayList<TargetedCategory> getAllTargetedCategories() throws SQLException {
		ArrayList<TargetedCategory> foundCategories = new ArrayList<>();
		try {
			foundCategories = targetedCategoryDb.getAllTargetedCategories();
		} catch (Exception e ) {
			e.printStackTrace();
		}
		return foundCategories;
	}
	
}
