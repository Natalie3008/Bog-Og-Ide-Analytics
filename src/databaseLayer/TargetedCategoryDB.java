package databaseLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelLayer.TargetedCategory;

public class TargetedCategoryDB implements TargetedCategoryDBIF {

	public TargetedCategory createTargetedCategory(TargetedCategory targetedCategory) throws SQLException {
		String sqlCategory = "INSERT INTO TargetedCategory (ID, title, minimumAge, maximumAge, gender, other) VALUES(?,?,?,?,?,?)";
		int resultCategory = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementCopy = DBConnection.getInstance().getConnection().prepareStatement(sqlCategory);
			statementCopy.setInt(1, targetedCategory.getID());			
			statementCopy.setString(2, targetedCategory.getTitle());
			statementCopy.setInt(3, targetedCategory.getMinimumAge());
			statementCopy.setInt(4, targetedCategory.getMaximumAge());
			statementCopy.setString(5, targetedCategory.getGender());
			statementCopy.setString(6, targetedCategory.getOther());
			resultCategory = statementCopy.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			statementCopy.close();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
			throw e;
		}
		return resultCategory == 1 ? targetedCategory : null;
	}

	public TargetedCategory updateTargetedCategory(TargetedCategory targetedCategory) throws SQLException {
		String sqlCategory = "UPDATE TargetedCategory SET title = ?, minimumAge = ?, maximumAge = ?, gender = ?, other = ? WHERE ID = ?)";
		int resultCategory = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementCopy = DBConnection.getInstance().getConnection().prepareStatement(sqlCategory);
			statementCopy.setString(1, targetedCategory.getTitle());
			statementCopy.setInt(2, targetedCategory.getMinimumAge());
			statementCopy.setInt(3, targetedCategory.getMaximumAge());
			statementCopy.setString(4, targetedCategory.getGender());
			statementCopy.setString(5, targetedCategory.getOther());
			statementCopy.setInt(6, targetedCategory.getID());
			resultCategory = statementCopy.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			statementCopy.close();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
			throw e;
		}
		return resultCategory == 1 ? targetedCategory : null;
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
		String sqlDeleteCategory = "DELETE FROM TargetedCategory WHERE ID = ?";
		int result = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statement = DBConnection.getInstance().getConnection()
					.prepareStatement(sqlDeleteCategory);
			statement.setInt(1, targetedCategoryID);
			result = statement.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			statement.close();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
			throw e;
		}
		return result > 1;
	}

	public TargetedCategory buildCategory(ResultSet resultSet) throws SQLException {
		TargetedCategory builtCategory = null;
		builtCategory = new TargetedCategory(resultSet.getInt("ID"), resultSet.getString("title"),
				resultSet.getInt("minimumAge"), resultSet.getInt("maximumAge"), resultSet.getString("gender"),
				resultSet.getString("other"));
		return builtCategory;
	}

	public ArrayList<TargetedCategory> buildCategories(ResultSet resultSet) throws SQLException {
		ArrayList<TargetedCategory> foundCategories = new ArrayList<>();
		while (resultSet.next()) {
			TargetedCategory category = buildCategory(resultSet);
			foundCategories.add(category);
		}
		return foundCategories;
	}
}