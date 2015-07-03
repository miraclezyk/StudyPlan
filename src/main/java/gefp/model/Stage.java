package gefp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Stage implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	Long id;

	String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Stage clone(){
		Stage stage = new Stage();
		stage.setId(id);
		stage.setName(name);
		return stage;
	}

}
