package ip.project.dto;

public class UserDTO {
	private Integer id;
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String role;
	private String biography;
	private Integer cityId;
	private String cityName;
	private boolean isActivated;

	public UserDTO() {
	}

	public UserDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public UserDTO(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public UserDTO(Integer id, String username, String password, String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public UserDTO(Integer id, String email, String username, String firstName, String lastName, String role,
			Integer cityId, String biography) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.cityId = cityId;
		this.biography = biography;
	}

	public UserDTO(Integer id, String email, String username, String firstName, String lastName, String role,
			Integer cityId, String biography, String cityName) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.cityId = cityId;
		this.cityName = cityName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getFullname() {
		if (this.firstName != null && !this.firstName.isEmpty() && this.lastName != null && !this.lastName.isEmpty()) {
			return this.firstName + " " + this.lastName;
		} else if (this.firstName != null && !this.firstName.isEmpty()) {
			return this.firstName;
		} else if (this.lastName != null && !this.lastName.isEmpty()) {
			return this.lastName;
		} else {
			return this.username;
		}
	}

}
