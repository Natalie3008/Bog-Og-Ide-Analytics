package databaseLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelLayer.TargetedCategory;

public class TargetedCategoryDB implements TargetedCategoryDBIF {

	private static final String INSERT_INTO_TARGETED_CATEGORY = "INSERT INTO TargetedCategory (ID, title, minimumAge, maximumAge, gender, other, version) VALUES(?,?,?,?,?,?,?)";
	private static final String UPDATE_TARGETED_CATEGORY = "UPDATE TargetedCategory SET title = ?, minimumAge = ?, maximumAge = ?, gender = ?, other = ? , version = ? WHERE ID = ? AND version = ?";
	private static final String DELETE_TARGETED_CATEGORY_WITH_ID = "DELETE FROM TargetedCategory WHERE ID = ?";
	private static final String SELECT_VERSION = "SELECT version FROM TargetedCategory WHERE ID = ?";

	private PreparedStatement psInsertIntoTargetedCategory;
	private PreparedStatement psUpdateTargetedCategory;
	private PreparedStatement psDeleteTargetedCategoryWithID;
	private PreparedStatement psSelectVersion;

	public TargetedCategoryDB() {
		initPreparedStatement();
	}

	private void initPreparedStatement() {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			psInsertIntoTargetedCategory = connection.prepareStatement(INSERT_INTO_TARGETED_CATEGORY);
			psUpdateTargetedCategory = connection.prepareStatement(UPDATE_TARGETED_CATEGORY);
			psDeleteTargetedCategoryWithID = connection.prepareStatement(DELETE_TARGETED_CATEGORY_WITH_ID);
			psSelectVersion = connection.prepareStatement(SELECT_VERSION);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public TargetedCategory createTargetedCategory(TargetedCategory targetedCategory) throws SQLException {
		int resultCategory = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			psInsertIntoTargetedCategory.setInt(1, targetedCategory.getID());
			psInsertIntoTargetedCategory.setString(2, targetedCategory.getTitle());
			psInsertIntoTargetedCategory.setInt(3, targetedCategory.getMinimumAge());
			psInsertIntoTargetedCategory.setInt(4, targetedCategory.getMaximumAge());
			psInsertIntoTargetedCategory.setString(5, targetedCategory.getGender());
			psInsertIntoTargetedCategory.setString(6, targetedCategory.getOther());
			psInsertIntoTargetedCategory.setInt(7, 0);
			resultCategory = psInsertIntoTargetedCategory.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
			throw e;
		}
		return resultCategory == 1 ? targetedCategory : null;
	}

	public TargetedCategory updateTargetedCategory(TargetedCategory targetedCategory) throws SQLException {
		int resultCategory = 0;
		int version = getVersion(targetedCategory.getID());
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			psUpdateTargetedCategory.setString(1, targetedCategory.getTitle());
			psUpdateTargetedCategory.setInt(2, targetedCategory.getMinimumAge());
			psUpdateTargetedCategory.setInt(3, targetedCategory.getMaximumAge());
			psUpdateTargetedCategory.setString(4, targetedCategory.getGender());
			psUpdateTargetedCategory.setString(5, targetedCategory.getOther());
			psUpdateTargetedCategory.setInt(6, version + 1);
			psUpdateTargetedCategory.setInt(7, targetedCategory.getID());
			psUpdateTargetedCategory.setInt(8, version);
			resultCategory = psUpdateTargetedCategory.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
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
		int result = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			psDeleteTargetedCategoryWithID.setInt(1, targetedCategoryID);
			result = psDeleteTargetedCategoryWithID.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
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

	private int getVersion(int targetedCategoryID) throws SQLException {
		int version = -1;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			psSelectVersion.setInt(1, targetedCategoryID);
			ResultSet resultSet = psSelectVersion.executeQuery();
			if (resultSet.next()) {
				version = resultSet.getInt(1);
			}
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return version;
	}
}