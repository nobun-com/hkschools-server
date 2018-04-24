package com.hkschool.business.service;

import java.util.List;
import java.util.Map;

import com.hkschool.bean.jpa.SSEntity;

public interface SecondarySchoolService {
	/**
	 * Loads an entity from the database using its Primary Key
	 * 
	 * @param id
	 * @return entity
	 */
	SSEntity findById(Long id);

	/**
	 * Loads all entities.
	 * 
	 * @return all entities
	 */
	List<SSEntity> findAll();

	/**
	 * @param email
	 * @return entity
	 */
	SSEntity findByEmail(String email);

	/**
	 * Saves the given entity in the database (create or update)
	 * 
	 * @param entity
	 * @return entity
	 */
	SSEntity save(SSEntity entity);

	/**
	 * Updates the given entity in the database
	 * 
	 * @param entity
	 * @return
	 */
	SSEntity update(SSEntity entity);

	/**
	 * Creates the given entity in the database
	 * 
	 * @param entity
	 * @return
	 */
	SSEntity create(SSEntity entity);

	/**
	 * Deletes an entity using its Primary Key
	 * 
	 * @param id
	 */
	void delete(Long id);

	Map<String, Object> initFilter();

	List<Map<String, Object>> findAllSecondary(String schoolCategory, String schoolDistrict,String religion, String studentGender, String sponsoringBody);

	List<Map<String, Object>> getDistrict();

}
