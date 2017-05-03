package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.CartItem;
import entities.Equipment;

@Transactional
@Repository
public class EquipmentDAOImpl implements EquipmentDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Equipment> index() {
		String q = "SELECT e FROM EQUIPMENT e";
		return em.createQuery(q, Equipment.class).getResultList();
	}
	
	@Override
	public Equipment show(Integer id) {
		return em.find(Equipment.class, id);
	}

	@Override
	public Equipment update(Integer id, Equipment e) {
		Equipment equip = em.find(Equipment.class, id);
		equip.setDescription(e.getDescription());
		equip.setImage(e.getImage());
		equip.setRate(e.getRate());
		equip.setMake(e.getMake());
		equip.setModel(e.getModel());
		equip.setType(e.getType());
		return equip;
	}

	@Override
	public Equipment create(Integer id, Equipment e) {
		em.persist(e);
		em.flush();
		return e;
	}

	@Override
	public Boolean destroy(Integer id) {
		try {
			em.remove(em.find(CartItem.class, id));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
