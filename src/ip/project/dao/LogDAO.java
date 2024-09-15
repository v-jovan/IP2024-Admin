package ip.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ip.project.dto.LogDTO;
import ip.project.util.ConnectionPool;
import ip.project.util.ResourceCloser;
import ip.project.util.SQLQueries;

public class LogDAO {

	public List<LogDTO> getAllLogs() {
		List<LogDTO> logs = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionPool.getInstance(null).checkOut();
			stmt = conn.prepareStatement(SQLQueries.SELECT_ALL_LOGS);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String user = rs.getString("user");
				String action = rs.getString("action");
				Timestamp timestamp = rs.getTimestamp("timestamp");
				logs.add(new LogDTO(id, user, action, timestamp));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceCloser.closeResources(rs, stmt, conn);
		}

		return logs;
	}

}
