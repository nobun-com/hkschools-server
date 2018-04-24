package com.hkschool.business.service;

import java.util.List;


import java.util.Map;

import com.hkschool.bean.jpa.PSEntity;

public interface PrimarySchoolService {
	/**
	 * Loads an entity from the database using its Primary Key
	 * 
	 * @param id
	 * @return entity
	 */
	PSEntity findById(Long id);

	/**
	 * Loads all entities.
	 * 
	 * @return all entities
	 */
	List<PSEntity> findAll();

	/**
	 * @param email
	 * @return entity
	 */
	PSEntity findByEmail(String email);

	/**
	 * Saves the given entity in the database (create or update)
	 * 
	 * @param entity
	 * @return entity
	 */
	PSEntity save(PSEntity entity);

	/**
	 * Updates the given entity in the database
	 * 
	 * @param entity
	 * @return
	 */
	PSEntity update(PSEntity entity);

	/**
	 * Creates the given entity in the database
	 * 
	 * @param entity
	 * @return
	 */
	PSEntity create(PSEntity entity);

	/**
	 * Deletes an entity using its Primary Key
	 * 
	 * @param id
	 */
	void delete(Long id);

	Map<String, Object> initFilter();

	List<Map<String, Object>> findAllPrimary(String schoolCategory, String schoolDistrict,String religion,String studentGender);

	List<Map<String, Object>> getDistrict();
}
