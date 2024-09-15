package ip.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ip.project.dto.CategoryDTO;
import ip.project.util.ConnectionPool;
import ip.project.util.ResourceCloser;
import ip.project.util.SQLQueries;

public class CategoryDAO {
	public List<CategoryDTO> getAllCategories() {
		List<CategoryDTO> categories = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionPool.getInstance(null).checkOut();
			stmt = conn.prepareStatement(SQLQueries.SELECT_ALL_CATEGORIES);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				categories.add(new CategoryDTO(id, name, description));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceCloser.closeResources(rs, stmt, conn);
		}

		return categories;
	}

	public CategoryDTO getCategoryById(int categoryId) {
		CategoryDTO category = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionPool.getInstance(null).checkOut();
			stmt = conn.prepareStatement(SQLQueries.SELECT_CATEGORY_BY_ID);
			stmt.setInt(1, categoryId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				category = new CategoryDTO(rs.getInt("id"), rs.getString("name"), rs.getString("description"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceCloser.closeResources(rs, stmt, conn);
		}

		return category;
	}

	public void createCategory(CategoryDTO category) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionPool.getInstance(null).checkOut();
			stmt = conn.prepareStatement(SQLQueries.CREATE_CATEGORY);

			stmt.setString(1, category.getName());
			stmt.setString(2, category.getDescription());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceCloser.closeResources(null, stmt, conn);
		}
	}

	public void updateCategory(CategoryDTO category) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionPool.getInstance(null).checkOut();

			stmt = conn.prepareStatement(SQLQueries.UPDATE_CATEGORY);

			stmt.setString(1, category.getName());
			stmt.setString(2, category.getDescription());
			stmt.setInt(3, category.getId());

			stmt.executeUpdate();

		} finally {
			ResourceCloser.closeResources(null, stmt, conn);
		}
	}

	public void deleteCategory(int categoryId) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionPool.getInstance(null).checkOut();
			conn.setAutoCommit(false); // Make it a transaction

			stmt = conn.prepareStatement(SQLQueries.NULLIFY_CATEGORY_IN_PROGRAMS);
			stmt.setInt(1, categoryId);
			stmt.executeUpdate();
			stmt.close();

			stmt = conn.prepareStatement(SQLQueries.DELETE_PROGRAM_ATTRIBUTES_BY_ATTRIBUTE_VALUE);
			stmt.setInt(1, categoryId);
			stmt.executeUpdate();
			stmt.close();

			stmt = conn.prepareStatement(SQLQueries.DELETE_ATTRIBUTE_VALUES_BY_ATTRIBUTE);
			stmt.setInt(1, categoryId);
			stmt.executeUpdate();
			stmt.close();

			stmt = conn.prepareStatement(SQLQueries.DELETE_ATTRIBUTES_BY_CATEGORY);
			stmt.setInt(1, categoryId);
			stmt.executeUpdate();
			stmt.close();

			stmt = conn.prepareStatement(SQLQueries.DELETE_CATEGORY);
			stmt.setInt(1, categoryId);
			stmt.executeUpdate();

			conn.commit();

		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException rollbackEx) {
					rollbackEx.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			ResourceCloser.closeResources(null, stmt, conn);
		}
	}

}
