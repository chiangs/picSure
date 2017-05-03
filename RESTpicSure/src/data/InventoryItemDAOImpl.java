package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Equipment;
import entities.InventoryItem;
import entities.Store;

@Transactional
@Repository
public class InventoryItemDAOImpl implements InventoryItemDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public InventoryItem show(Integer id) {
		return em.find(InventoryItem.class, id);
	}

	@Override
	public InventoryItem update(Integer id, InventoryItem i) {

		return null;
	}

	@Override
	public InventoryItem create(Integer storeId, Integer equipmentId, InventoryItem i) {
		Store store = em.find(Store.class, storeId);
		i.setActive(true);
		i.setEquipment(em.find(Equipment.class, equipmentId));
		i.setInventory(store.getInventory());
		em.persist(i);
		em.flush();
		return i;
	}

	@Override
	public Boolean destroy(Integer id) {
		try {
			em.remove(em.find(InventoryItem.class, id));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
