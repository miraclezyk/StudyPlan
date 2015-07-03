package gefp.model.dao.jpa;

import java.util.List;

import gefp.model.User;
import gefp.model.dao.UserDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User getUser(String username) {
		TypedQuery<User> tq = entityManager.createQuery(
				"from User where username=:username", User.class);
		tq.setParameter("username", username);
		if (tq.getResultList().size() > 0) {
			User user = (User) tq.getSingleResult();
			return user;
		} else {
			return null;
		}

	}
	
	@Override
	public List<User> getUserByName(String name) {
		TypedQuery<User> tq = entityManager.createQuery(
				"from User where lower(firstName)=:name or lower(lastName)=:name", User.class);
		tq.setParameter("name", name);
		if (tq.getResultList().size() > 0) {
			return tq.getResultList();
		}else{
			return null;
		}
	}

	@Override
	public User getUserByCin(String cin) {
		TypedQuery<User> tq = entityManager.createQuery(
				"from User where cin=:cin", User.class);
		tq.setParameter("cin", cin);
		if (tq.getResultList().size() > 0) {
			User user = (User) tq.getSingleResult();
			return user;
		} else {
			return null;
		}
	}

	@Override
	public User getUserByEmail(String email) {
		TypedQuery<User> tq = entityManager.createQuery(
				"from User where email=:email", User.class);
		tq.setParameter("email", email);
		if (tq.getResultList().size() > 0) {
			User user = (User) tq.getSingleResult();
			return user;
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	@PreAuthorize("hasRole('ROLE_ADVISOR') or principal.username == #user.username")
	public User saveUser(User user) {
		return entityManager.merge(user);
	}

}