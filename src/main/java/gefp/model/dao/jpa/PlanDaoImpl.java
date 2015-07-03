package gefp.model.dao.jpa;

import gefp.model.Plan;
import gefp.model.dao.PlanDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PlanDaoImpl implements PlanDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@PostAuthorize("hasRole('ROLE_ADVISOR') or hasRole('ROLE_ADMIN') or (returnObject.id == principal.plan.id)")
	public Plan getPlan(Long planId) {
		return entityManager.find(Plan.class, planId);
	}

	@Override
	@Transactional
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Plan savePlan(Plan plan) {

		return entityManager.merge( plan );
		
	}

}
