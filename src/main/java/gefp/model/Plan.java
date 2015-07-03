package gefp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

@Entity
public class Plan implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	Long id;

	String name;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@OrderColumn(name = "stage_order")
	@JoinTable(name = "plan_stages", joinColumns = @JoinColumn(name = "plan_id"), inverseJoinColumns = @JoinColumn(name = "stage_id"))
	List<Stage> stages;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@OrderColumn(name = "runway_order")
	@JoinTable(name = "plan_runways", joinColumns = @JoinColumn(name = "plan_id"), inverseJoinColumns = @JoinColumn(name = "runway_id"))
	List<Runway> runways;

	@OneToMany(mappedBy = "plan",cascade = { CascadeType.MERGE, CascadeType.PERSIST },fetch=FetchType.EAGER)
	Set<Cell> cells;

	Date publishedDate;

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

	public List<Stage> getStages() {
		return stages;
	}

	public void setStages(List<Stage> stages) {
		this.stages = stages;
	}

	public List<Runway> getRunways() {
		return runways;
	}

	public void setRunways(List<Runway> runways) {
		this.runways = runways;
	}

	public Set<Cell> getCells() {
		return cells;
	}

	public void setCells(Set<Cell> cells) {
		this.cells = cells;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

}