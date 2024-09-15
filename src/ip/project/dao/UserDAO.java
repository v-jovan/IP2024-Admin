package ip.project.dao;

import ip.project.dto.CityDTO;
import ip.project.dto.UserDTO;
import ip.project.util.ConnectionPool;
import ip.project.util.ResourceCloser;
import ip.project.util.SQLQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {

	public UserDTO authenticate(String username, String rawPassword) {
		UserDTO user = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionPool.getInstance(null).checkOut();
			stmt = conn.prepareStatement(SQLQueries.SELECT_USER_BY_USERNAME);
			stmt.setString(1, username);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				String storedPassword = rs.getString("password");
				String role = rs.getString("role");

				if (BCrypt.checkpw(rawPassword, storedPassword)) {
					user = new UserDTO(id, username, storedPassword, role);
				} else {
					System.out.println("Invalid password.");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceCloser.closeResources(rs, stmt, conn);
		}

		return user;
	}

	public List<UserDTO> getAllUsers() {
		List<UserDTO> users = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionPool.getInstance(null).checkOut();
			stmt = conn.prepareStatement(SQLQueries.SELECT_ALL_USERS);
			rs = stmt.executeQuery();

			CityDAO cityDAO = new CityDAO();

			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String role = rs.getString("role");
				String biography = rs.getString("biography");
				int cityId = rs.getInt("city_id");

				CityDTO city = cityDAO.getCityById(cityId);
				String cityName = (city != null) ? city.getName() : "N/A";

				users.add(new UserDTO(id, email, username, firstName, lastName, role, cityId, biography, cityName));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceCloser.closeResources(rs, stmt, conn);
		}

		return users;
	}

	public void createUser(UserDTO user) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionPool.getInstance(null).checkOut();
			stmt = conn.prepareStatement(SQLQueries.CREATE_USER);

			String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

			stmt.setString(1, user.getUsername());
			stmt.setString(2, hashedPassword);
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getFirstName());
			stmt.setString(5, user.getLastName());
			stmt.setString(6, user.getRole());
			stmt.setInt(7, user.getCityId());
			stmt.setString(8, user.getBiography());

			stmt.executeUpdate();

		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				throw new Exception("Korisničko ime ili email već postoje.");
			} else {
				e.printStackTrace();
				throw new Exception("Došlo je do greške prilikom kreiranja korisnika.");
			}
		} finally {
			ResourceCloser.closeResources(null, stmt, conn);
		}
	}

	public void updateUser(UserDTO user, boolean updatePassword) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionPool.getInstance(null).checkOut();

			String checkSql = "SELECT id FROM user WHERE (username = ? OR email = ?) AND id != ?";
			stmt = conn.prepareStatement(checkSql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getEmail());
			stmt.setInt(3, user.getId());
			rs = stmt.executeQuery();

			if (rs.next()) {
				throw new SQLException("Korisničko ime ili email već postoje.");
			}

			StringBuilder sql = new StringBuilder(SQLQueries.UPDATE_USER);

			if (updatePassword) {
				sql.append(", password = ?");
			}

			sql.append(" WHERE id = ?");

			stmt = conn.prepareStatement(sql.toString());

			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getFirstName());
			stmt.setString(4, user.getLastName());
			stmt.setString(5, user.getRole());
			stmt.setInt(6, user.getCityId());
			stmt.setString(7, user.getBiography());

			if (updatePassword) {
				String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
				stmt.setString(8, hashedPassword);
				stmt.setInt(9, user.getId());
			} else {
				stmt.setInt(8, user.getId());
			}

			stmt.executeUpdate();

		} finally {
			ResourceCloser.closeResources(rs, stmt, conn);
		}
	}

	public UserDTO getUserById(int userId) {
		UserDTO user = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionPool.getInstance(null).checkOut();
			stmt = conn.prepareStatement(SQLQueries.SELECT_USER_BY_ID);
			stmt.setInt(1, userId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String email = rs.getString("email");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String role = rs.getString("role");
				int cityId = rs.getInt("city_id");
				String biography = rs.getString("biography");

				user = new UserDTO(id, email, username, firstName, lastName, role, cityId, biography);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceCloser.closeResources(rs, stmt, conn);
		}

		return user;
	}

	public void deleteUser(int userId) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionPool.getInstance(null).checkOut();
			stmt = conn.prepareStatement(SQLQueries.DELETE_USER);
			stmt.setInt(1, userId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceCloser.closeResources(null, stmt, conn);
		}
	}

}
