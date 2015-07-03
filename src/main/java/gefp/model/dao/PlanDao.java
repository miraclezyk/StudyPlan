package gefp.model.dao;

import gefp.model.Plan;

public interface PlanDao {

	Plan getPlan(Long planId);
	
	Plan savePlan(Plan plan);

}
