package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Address;
import entities.User;

@Transactional
@Repository
public class AddressDAOImpl implements AddressDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Address show(Integer id) {
		String q = "SELECT a FROM Address a WHERE a.store.id = :id";
		return em.createQuery(q, Address.class).setParameter("id", id).getSingleResult();
	}

	@Override
	public Address update(Integer id, Address a) {
		Address address = em.find(Address.class, id);
		address.setStreet(a.getStreet());
		address.setStreet2(a.getStreet2());
		address.setCity(a.getCity());
		address.setState(a.getState());
		address.setZip(a.getZip());
		return address;
	}
	
	@Override
	public Address createAddress(Address a) {
		em.persist(a);
		em.flush();
		return a;
	}

	@Override
	public Boolean destroy(Integer id) {
		Address a = em.find(Address.class, id);
		try {
			em.remove(a);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
