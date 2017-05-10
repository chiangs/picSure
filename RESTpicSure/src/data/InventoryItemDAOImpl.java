package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Equipment;
import entities.Inventory;
import entities.InventoryItem;
import entities.User;

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
	public List<InventoryItem> index() {
		String q = "SELECT i FROM InventoryItem i WHERE i.active = true";
		List<InventoryItem> items = em.createQuery(q, InventoryItem.class).getResultList();
		return items;
	}
	
	@Override
	public List<InventoryItem> indexEquipmentType(String equipmentType) {
		String q = "SELECT i FROM InventoryItem i WHERE i.active = true and i.equipment.type = :type";
		List<InventoryItem> items = em.createQuery(q, InventoryItem.class).setParameter("type", equipmentType).getResultList();
		return items;
	}
	
	@Override
	public List<InventoryItem> index(Integer id) {
		String q = "SELECT i FROM InventoryItem i WHERE i.inventory.id = :id";
		return em.createQuery(q, InventoryItem.class).setParameter("id", id).getResultList();
	}

	@Override
	public InventoryItem update(Integer id, InventoryItem i) {
		InventoryItem item = em.find(InventoryItem.class, id);
		item.setRentalRate(i.getRentalRate());
		return item;
	}

	@Override
	public InventoryItem create(Integer userId, Integer equipmentId) {
		User u = em.find(User.class, userId);
		
		String q = "SELECT i from Inventory i WHERE i.store.id = :id";
		Inventory inventory = em.createQuery(q, Inventory.class).setParameter("id", u.getStore().get(0).getId()).getSingleResult();
		
		InventoryItem i = new InventoryItem();
		i.setActive(true);
		i.setEquipment(em.find(Equipment.class, equipmentId));
		i.setRentalRate(i.getEquipment().getRate());
		i.setInventory(inventory);
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

	@Override
	public List <InventoryItem> updateItems(Integer inventoryId, InventoryItem item) {
		String q = "SELECT i FROM InventoryItem i WHERE i.inventory.id = :inventoryId AND i.equipment.id = :equipmentId ";
		List<InventoryItem> items = em.createQuery(q, InventoryItem.class).setParameter("inventoryId", inventoryId).setParameter("equipmentId", item.getEquipment().getId()).getResultList();
		try{
			for (InventoryItem i : items) {
				i.setActive(item.getActive());
			}
			return items;
		}catch (Exception e) {
			return null;
		}
	}

}
