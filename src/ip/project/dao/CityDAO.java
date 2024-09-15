package ip.project.dao;

import ip.project.dto.CityDTO;
import ip.project.util.ConnectionPool;
import ip.project.util.SQLQueries;
import ip.project.util.ResourceCloser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDAO {

	public List<CityDTO> getAllCities() {
		List<CityDTO> cities = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionPool.getInstance(null).checkOut();
			stmt = conn.prepareStatement(SQLQueries.SELECT_ALL_CITIES);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				cities.add(new CityDTO(id, name));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceCloser.closeResources(rs, stmt, conn);
		}

		return cities;
	}

	public CityDTO getCityById(int cityId) {
		CityDTO city = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionPool.getInstance(null).checkOut();
			stmt = conn.prepareStatement(SQLQueries.SELECT_CITY_BY_ID);
			stmt.setInt(1, cityId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				city = new CityDTO(rs.getInt("id"), rs.getString("name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceCloser.closeResources(rs, stmt, conn);
		}

		return city;
	}
}
