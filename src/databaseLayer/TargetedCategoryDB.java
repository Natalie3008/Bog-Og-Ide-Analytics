package databaseLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelLayer.TargetedCategory;

public class TargetedCategoryDB implements TargetedCategoryDBIF {

	public TargetedCategory createTargetedCategory(TargetedCategory targetedCategory) throws SQLException {
		String selectCategories = String.format(
				"INSERT INTO TargetedCategory VALUES(" + targetedCategory.getID() + ", '" + targetedCategory.getTitle()
						+ "', " + targetedCategory.getMinimumAge() + ", " + targetedCategory.getMaximumAge() + ", '"
						+ targetedCategory.getGender() + "', '" + targetedCategory.getOther() + "')");
		int res = -1;
		try {
			res = DBConnection.getInstance().executeUpdate(selectCategories);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res == 1 ? targetedCategory : null;
	}

	public TargetedCategory updateTargetedCategory(TargetedCategory targetedCategory) {
		String selectCategories = String.format("UPDATE TargetedCategory SET ID= " + targetedCategory.getID()
				+ ", title= '" + targetedCategory.getTitle() + "', minimumAge= " + targetedCategory.getMinimumAge()
				+ ", maximumAge= " + targetedCategory.getMaximumAge() + ", gender= '" + targetedCategory.getGender()
				+ "', other= '" + targetedCategory.getOther() + "'WHERE ID=" + targetedCategory.getID());
		int res = -1;
		try {
			res = DBConnection.getInstance().executeUpdate(selectCategories);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res == 1 ? targetedCategory : null;
	}

	public ArrayList<TargetedCategory> getAllTargetedCategories() throws SQLException {
		ArrayList<TargetedCategory> foundTargetedCategories = new ArrayList<TargetedCategory>();
		String selectCategories = "SELECT * FROM TargetedCategory";
		try {
			Statement statement = DBConnection.getInstance().getConnection().createStatement();
			ResultSet rsCategory = statement.executeQuery(selectCategories);
			foundTargetedCategories.addAll(buildCategories(rsCategory));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundTargetedCategories;
	}

	public boolean deleteTargetedCategory(int targetedCategoryID) throws SQLException {
		String deleteCategory = "DELETE FROM TargetedCategory WHERE ID = " + targetedCategoryID;
		int result = DBConnection.getInstance().executeUpdate(deleteCategory);
		return result > 1;
	}

	private TargetedCategory buildCategory(ResultSet resultSet) throws SQLException {
		TargetedCategory builtCategory = null;
		builtCategory = new TargetedCategory(resultSet.getInt("ID"), resultSet.getString("title"),
				resultSet.getInt("minimumAge"), resultSet.getInt("maximumAge"), resultSet.getString("gender"),
				resultSet.getString("other"));
		return builtCategory;
	}

	private ArrayList<TargetedCategory> buildCategories(ResultSet resultSet) throws SQLException {
		ArrayList<TargetedCategory> foundCategories = new ArrayList<>();
		while (resultSet.next()) {
			TargetedCategory category = buildCategory(resultSet);
			foundCategories.add(category);
		}
		return foundCategories;
	}
}