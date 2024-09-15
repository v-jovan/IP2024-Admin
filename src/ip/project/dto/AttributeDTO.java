package ip.project.dto;

public class AttributeDTO {
	private Integer id;
	private CategoryDTO category;
	private String name;
	private String description;

	public AttributeDTO() {
	}

	public AttributeDTO(CategoryDTO category, String name, String description) {
		super();
		this.category = category;
		this.name = name;
		this.description = description;
	}

	public AttributeDTO(Integer id, CategoryDTO category, String name, String description) {
		super();
		this.id = id;
		this.category = category;
		this.name = name;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
