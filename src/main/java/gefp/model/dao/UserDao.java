package gefp.model.dao;

import java.util.List;

import gefp.model.User;

public interface UserDao {

	User getUser(String username);
	
	List<User> getUserByName(String name);
	
	User getUserByCin(String cin);
	
	User getUserByEmail(String email);
	
	User saveUser(User user);
	
}
