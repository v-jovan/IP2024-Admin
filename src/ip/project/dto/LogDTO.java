package ip.project.dto;

import java.sql.Timestamp;

public class LogDTO {

	private Integer id;
	private String user;
	private String action;
	private Timestamp timestamp;

	public LogDTO() {
		super();
	}

	public LogDTO(Integer id, String user, String action, Timestamp timestamp) {
		super();
		this.id = id;
		this.user = user;
		this.action = action;
		this.timestamp = timestamp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}
