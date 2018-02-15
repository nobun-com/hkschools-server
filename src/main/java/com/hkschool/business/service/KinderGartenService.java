package com.hkschool.business.service;

import java.util.List;
import java.util.Map;

import com.hkschool.bean.jpa.KGEntity;

public interface KinderGartenService {
	/**
	 * Loads an entity from the database using its Primary Key
	 * 
	 * @param id
	 * @return entity
	 */
	KGEntity findById(Long id);

	KGEntity findByEmail(String email);

	/**
	 * Saves the given entity in the database (create or update)
	 * 
	 * @param entity
	 * @return entity
	 */
	KGEntity save(KGEntity entity);

	/**
	 * Updates the given entity in the database
	 * 
	 * @param entity
	 * @return
	 */
	KGEntity update(KGEntity entity);

	/**
	 * Creates the given entity in the database
	 * 
	 * @param entity
	 * @return
	 */
	KGEntity create(KGEntity entity);

	/**
	 * Deletes an entity using its Primary Key
	 * 
	 * @param id
	 */
	void delete(Long id);

	Map<String, Object> initFilter();

	List<KGEntity> findAll(String schoolCategoury, String schoolDistrict, String withchildcareservicesforchildrenagedunder2,String time);

	List<Map<String, Object>> getDistricts();
}
