package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import entities.User;

@Transactional
@Repository
@RequestMapping("auth")
public class AuthenticationDAOImpl implements AuthenticationDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public User register(User u) {
		String passwordSha = encoder.encode(u.getPassword());
		u.setPassWord(passwordSha);
		em.persist(u);
		em.flush();
		return u;
	}

	@Override
	public User authenticateUser(User u) {
		User managedUser = null;
		try {
			String query = "SELECT u from User u WHERE u.username = :username";
			managedUser = em.createQuery(query, User.class).setParameter("username", u.getUsername()).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
//			String q = "SELECT u from User u WHERE u.email = :email";
//			managedUser = em.createQuery(q, User.class).setParameter("email", u.getEmail()).getSingleResult();
//			managedUser.setTodos(new HashSet<Todo>());
        }
        System.out.println(managedUser);
        return managedUser;
	}

}
