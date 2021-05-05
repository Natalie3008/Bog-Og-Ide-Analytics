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

		}
    	return true;
		
	}
	
	public void updateTargetedCategory(TargetedCategory targetedCategory) throws SQLException {
		try {
			targetedCategoryDb.updateTargetedCategory(targetedCategory);
		} catch (Exception e) {

		}

		
	}

	public void deleteTargetedCategory(int targetedCategoryID) throws SQLException {
		targetedCategoryDb.deleteTargetedCategory(targetedCategoryID);	
	}
	
	
	public ArrayList<TargetedCategory> getAllCategories() throws SQLException {
		ArrayList<TargetedCategory> foundCategories = new ArrayList<>();
		foundCategories = targetedCategoryDb.getAllTargetedCategories();
		return foundCategories;
	}
	
}
