package databaseLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelLayer.TargetedCategory;

public interface TargetedCategoryDBIF {

	 TargetedCategory createTargetedCategory(TargetedCategory targetedCategory) throws SQLException;
	 TargetedCategory updateTargetedCategory(TargetedCategory targetedCategory) throws SQLException;
	 ArrayList<TargetedCategory> getAllTargetedCategories() throws SQLException;
	 boolean deleteTargetedCategory(int targetedCategoryID) throws SQLException;
	 TargetedCategory buildCategory(ResultSet resultSet) throws SQLException;
	 ArrayList<TargetedCategory> buildCategories(ResultSet resultSet) throws SQLException;
}