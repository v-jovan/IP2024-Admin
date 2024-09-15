package ip.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ip.project.dto.AttributeDTO;
import ip.project.dto.AttributeValueDTO;
import ip.project.util.ConnectionPool;
import ip.project.util.ResourceCloser;
import ip.project.util.SQLQueries;

public class AttributeValueDAO {
	public List<AttributeValueDTO> getAllAttributeValues() {
		List<AttributeValueDTO> attributeValues = new ArrayList<>();
		AttributeDAO attributeDAO = new AttributeDAO();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionPool.getInstance(null).checkOut();
			stmt = conn.prepareStatement(SQLQueries.SELECT_ALL_ATTRIBUTE_VALUES);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int attributeId = rs.getInt("attribute_id");

				AttributeDTO attribute = attributeDAO.getAttributeById(attributeId);

				String name = rs.getString("name");

				attributeValues.add(new AttributeValueDTO(id, attribute, name));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceCloser.closeResources(rs, stmt, conn);
		}

		return attributeValues;
	}

	public AttributeValueDTO getAttributeValueById(int attributeValueId) {
		AttributeValueDTO attributeValue = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionPool.getInstance(null).checkOut();
			stmt = conn.prepareStatement(SQLQueries.SELECT_ATTRIBUTE_VALUE_BY_ID);
			stmt.setInt(1, attributeValueId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				AttributeDTO attribute = new AttributeDAO().getAttributeById(rs.getInt("attribute_id"));
				attributeValue = new AttributeValueDTO(rs.getInt("id"), attribute, rs.getString("name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceCloser.closeResources(rs, stmt, conn);
		}

		return attributeValue;
	}

	public void createAttributeValue(AttributeValueDTO attributeValue) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionPool.getInstance(null).checkOut();
			stmt = conn.prepareStatement(SQLQueries.CREATE_ATTRIBUTE_VALUE);

			stmt.setInt(1, attributeValue.getAttribute().getId());
			stmt.setString(2, attributeValue.getName());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceCloser.closeResources(null, stmt, conn);
		}
	}

	public void updateAttributeValue(AttributeValueDTO attributeValue) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionPool.getInstance(null).checkOut();

			stmt = conn.prepareStatement(SQLQueries.UPDATE_ATTRIBUTE_VALUE);

			stmt.setString(1, attributeValue.getName());
			stmt.setInt(2, attributeValue.getAttribute().getId());
			stmt.setInt(3, attributeValue.getId());

			stmt.executeUpdate();

		} finally {
			ResourceCloser.closeResources(null, stmt, conn);
		}
	}

	public void deleteAttributeValue(int attributeValueId) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionPool.getInstance(null).checkOut();
			conn.setAutoCommit(false); // Make it a transaction

			stmt = conn.prepareStatement(SQLQueries.DELETE_PROGRAM_ATTRIBUTES_BY_SPECIFIC_ATTRIBUTE_VALUE);
			stmt.setInt(1, attributeValueId);
			stmt.executeUpdate();
			stmt.close();

			stmt = conn.prepareStatement(SQLQueries.DELETE_ATTRIBUTE_VALUE);
			stmt.setInt(1, attributeValueId);
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
