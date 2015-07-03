package gefp.model.dao;

import java.util.List;

import gefp.model.Department;

public interface DepartmentDao {

	Department getDepartment(Long departmentId);
	
	List<Department> getDepartments();
	
	Department saveDepartment(Department department);

}
