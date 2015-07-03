package gefp.model.dao.jpa;

import java.util.List;

import gefp.model.Department;
import gefp.model.dao.DepartmentDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Department getDepartment(Long departmentId) {
		return entityManager.find(Department.class, departmentId);
	}

	@Override
	public List<Department> getDepartments() {
		return entityManager.createQuery( "from Department order by id asc",
	            Department.class ).getResultList();
	}

	@Override
	@Transactional
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Department saveDepartment(Department department) {
		return entityManager.merge( department );
	}

	
}
