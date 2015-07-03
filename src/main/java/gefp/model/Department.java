package gefp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Department implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	Long id;

	String name;

	@OneToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "plan_id")
	Plan currentPlan;

	@OneToMany(cascade=CascadeType.REFRESH)
	@JoinTable(name = "department_plans", joinColumns = @JoinColumn(name = "department_id"), inverseJoinColumns = @JoinColumn(name = "plan_id"))
	List<Plan> plans;

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

	public Plan getCurrentPlan() {
		return currentPlan;
	}

	public void setCurrentPlan(Plan currentPlan) {
		this.currentPlan = currentPlan;
	}

	public List<Plan> getPlans() {
		return plans;
	}

	public void setPlans(List<Plan> plans) {
		this.plans = plans;
	}

}
