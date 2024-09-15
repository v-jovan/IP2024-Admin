package ip.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ip.project.dto.AttributeDTO;
import ip.project.dto.CategoryDTO;
import ip.project.util.ConnectionPool;
import ip.project.util.ResourceCloser;
import ip.project.util.SQLQueries;

public class AttributeDAO {
	public List<AttributeDTO> getAllAttributes() {
		List<AttributeDTO> attributes = new ArrayList<>();
		CategoryDAO categoryDAO = new CategoryDAO();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionPool.getInstance(null).checkOut();
			stmt = conn.prepareStatement(SQLQueries.SELECT_ALL_ATTRIBUTES);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int categoryId = rs.getInt("category_id");

				CategoryDTO category = categoryDAO.getCategoryById(categoryId);

				String name = rs.getString("name");
				String description = rs.getString("description");

				attributes.add(new AttributeDTO(id, category, name, description));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceCloser.closeResources(rs, stmt, conn);
		}

		return attributes;
	}

	public AttributeDTO getAttributeById(int attributeId) {
		AttributeDTO attribute = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionPool.getInstance(null).checkOut();
			stmt = conn.prepareStatement(SQLQueries.SELECT_ATTRIBUTE_BY_ID);
			stmt.setInt(1, attributeId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				CategoryDTO category = new CategoryDAO().getCategoryById(rs.getInt("category_id"));
				attribute = new AttributeDTO(rs.getInt("id"), category, rs.getString("name"),
						rs.getString("description"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceCloser.closeResources(rs, stmt, conn);
		}

		return attribute;
	}

	public void createAttribute(AttributeDTO attribute) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionPool.getInstance(null).checkOut();
			stmt = conn.prepareStatement(SQLQueries.CREATE_ATTRIBUTE);

			stmt.setInt(1, attribute.getCategory().getId());
			stmt.setString(2, attribute.getName());
			stmt.setString(3, attribute.getDescription());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceCloser.closeResources(null, stmt, conn);
		}
	}

	public void updateAttribute(AttributeDTO attribute) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionPool.getInstance(null).checkOut();

			stmt = conn.prepareStatement(SQLQueries.UPDATE_ATTRIBUTE);

			stmt.setString(1, attribute.getName());
			stmt.setString(2, attribute.getDescription());
			stmt.setInt(3, attribute.getCategory().getId());
			stmt.setInt(4, attribute.getId());

			stmt.executeUpdate();

		} finally {
			ResourceCloser.closeResources(null, stmt, conn);
		}
	}

	public void deleteAttribute(int attributeId) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionPool.getInstance(null).checkOut();
			conn.setAutoCommit(false); // Make it a transaction

			stmt = conn.prepareStatement(SQLQueries.DELETE_PROGRAM_ATTRIBUTES_BY_ATTRIBUTE_VALUE);
			stmt.setInt(1, attributeId);
			stmt.executeUpdate();
			stmt.close();

			stmt = conn.prepareStatement(SQLQueries.DELETE_ATTRIBUTE_VALUES_BY_ATTRIBUTE);
			stmt.setInt(1, attributeId);
			stmt.executeUpdate();
			stmt.close();

			stmt = conn.prepareStatement(SQLQueries.DELETE_ATTRIBUTE);
			stmt.setInt(1, attributeId);
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
