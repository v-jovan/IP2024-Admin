package ip.project.dto;

public class AttributeValueDTO {
	private Integer id;
	private AttributeDTO attribute;
	private String name;

	public AttributeValueDTO() {
	}

	public AttributeValueDTO(AttributeDTO attribute, String name) {
		super();
		this.attribute = attribute;
		this.name = name;
	}

	public AttributeValueDTO(Integer id, AttributeDTO attribute, String name) {
		super();
		this.id = id;
		this.attribute = attribute;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AttributeDTO getAttribute() {
		return attribute;
	}

	public void setAttribute(AttributeDTO attribute) {
		this.attribute = attribute;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
