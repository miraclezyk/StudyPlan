package gefp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cell implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	Long id;

	@ManyToOne(cascade=CascadeType.REFRESH)
	Plan plan;

	@OneToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="runway_id")
	Runway runway;

	@OneToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="stage_id")
	Stage stage;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE },fetch=FetchType.EAGER)
	@JoinTable(name = "cell_checkpoints", joinColumns = @JoinColumn(name = "cell_id"), inverseJoinColumns = @JoinColumn(name = "checkpoint_id"))
	List<Checkpoint> checkpoints;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Runway getRunway() {
		return runway;
	}

	public void setRunway(Runway runway) {
		this.runway = runway;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public List<Checkpoint> getCheckpoints() {
		return checkpoints;
	}

	public void setCheckpoints(List<Checkpoint> checkpoints) {
		this.checkpoints = checkpoints;
	}

}
