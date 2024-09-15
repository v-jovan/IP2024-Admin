package ip.project.util;

public class SQLQueries {

	public static final String SELECT_USER_BY_USERNAME = "SELECT * FROM user WHERE username = ?";
	public static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
	public static final String SELECT_ALL_USERS = "SELECT * FROM user";
	public static final String CREATE_USER = "INSERT INTO user (username, password, email, first_name, last_name, role, city_id, biography, is_activated) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 1)";
	public static final String UPDATE_USER = "UPDATE user SET username = ?, email = ?, first_name = ?, last_name = ?, role = ?, city_id = ?, biography = ?";
	public static final String DELETE_USER = "DELETE FROM user WHERE id = ?";

	public static final String SELECT_ALL_CITIES = "SELECT * FROM city";
	public static final String SELECT_CITY_BY_ID = "SELECT id, name FROM city WHERE id = ?";

	public static final String SELECT_ALL_CATEGORIES = "SELECT * FROM category";
	public static final String SELECT_CATEGORY_BY_ID = "SELECT * FROM category WHERE id = ?";
	public static final String CREATE_CATEGORY = "INSERT INTO category (name, description) VALUES (?, ?)";
	public static final String UPDATE_CATEGORY = "UPDATE category SET name = ?, description = ? WHERE id = ?";
	public static final String DELETE_CATEGORY = "DELETE FROM category WHERE id = ?";

	public static final String SELECT_ALL_ATTRIBUTES = "SELECT * FROM attribute";
	public static final String SELECT_ATTRIBUTE_BY_ID = "SELECT * FROM attribute WHERE id = ?";
	public static final String CREATE_ATTRIBUTE = "INSERT INTO attribute (category_id, name, description) VALUES (?, ?, ?)";
	public static final String UPDATE_ATTRIBUTE = "UPDATE attribute SET name = ?, description = ?, category_id = ? WHERE id = ?";
	public static final String DELETE_ATTRIBUTE = "DELETE FROM attribute WHERE id = ?";

	public static final String SELECT_ALL_ATTRIBUTE_VALUES = "SELECT * FROM attribute_value";
	public static final String SELECT_ATTRIBUTE_VALUE_BY_ID = "SELECT * FROM attribute_value WHERE id = ?";
	public static final String CREATE_ATTRIBUTE_VALUE = "INSERT INTO attribute_value (attribute_id, name) VALUES (?, ?)";
	public static final String UPDATE_ATTRIBUTE_VALUE = "UPDATE attribute_value SET name = ?, attribute_id = ? WHERE id = ?";
	public static final String DELETE_ATTRIBUTE_VALUE = "DELETE FROM attribute_value WHERE id = ?";

	public static final String SELECT_ALL_LOGS = "SELECT * FROM log ORDER BY timestamp DESC";

	// Special
	public static final String DELETE_PROGRAM_ATTRIBUTES_BY_SPECIFIC_ATTRIBUTE_VALUE = "DELETE FROM program_attribute WHERE attribute_value_id = ?";
	public static final String DELETE_PROGRAM_ATTRIBUTES_BY_ATTRIBUTE_VALUE = "DELETE FROM program_attribute WHERE attribute_value_id IN (SELECT id FROM attribute_value WHERE attribute_id = ?)";
	public static final String DELETE_ATTRIBUTE_VALUES_BY_ATTRIBUTE = "DELETE FROM attribute_value WHERE attribute_id = ?";
	public static final String DELETE_ATTRIBUTES_BY_CATEGORY = "DELETE FROM attribute WHERE category_id = ?";
	public static final String NULLIFY_CATEGORY_IN_PROGRAMS = "UPDATE fitness_program SET category_id = NULL WHERE category_id = ?";

}
