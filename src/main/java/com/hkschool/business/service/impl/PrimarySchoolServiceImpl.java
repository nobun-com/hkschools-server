package com.hkschool.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hkschool.bean.jpa.PSEntity;
import com.hkschool.business.service.PrimarySchoolService;
import com.hkschool.data.repository.jpa.PSJpaRepository;

@Component
@Transactional
public class PrimarySchoolServiceImpl implements PrimarySchoolService {

	@Autowired
	PSJpaRepository pSJpaRepository;

	@Override
	public PSEntity findById(Long id) {
		return pSJpaRepository.findById(id);
	}

	
	@Override
	public List<PSEntity> findAllPrimary(String schoolCategory, String schoolDistrict,String religion,String studentGender,String schoolId) {
		if(schoolCategory == null || schoolCategory.isEmpty()) {
			schoolCategory =  "%";
		}
		if(schoolDistrict == null || schoolDistrict.isEmpty()) {
			schoolDistrict =  "%";
		}
		if(religion == null || religion.isEmpty()) {
			religion =  "%";
		}
		
		if(studentGender == null || studentGender.isEmpty()) {
			studentGender =  "%";
		}
		if(schoolId == null || schoolId.isEmpty()) {
			schoolId =  "%";
		}
		Iterable<PSEntity> entities = pSJpaRepository.findAllConditional(schoolCategory,schoolDistrict,religion,studentGender,schoolId);
		List<PSEntity> beans = new ArrayList<PSEntity>();

		for (PSEntity psjEntity : entities) {
			beans.add(psjEntity);
		}
		return beans;
	}
	@Override
	public List<PSEntity> findAll() {
		Iterable<PSEntity> entities = pSJpaRepository.findAll();
		List<PSEntity> beans = new ArrayList<PSEntity>();

		for (PSEntity psjEntity : entities) {
			beans.add(psjEntity);
		}
		return beans;
	}

	@Override
	public PSEntity findByEmail(String email) {
		return null;
	}

	@Override
	public PSEntity save(PSEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PSEntity update(PSEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PSEntity create(PSEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public List<Map<String, Object>> getDistrict(){
		List<Object[]> records = pSJpaRepository.getSchoolDistrictAndCount();

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for(Object[] record : records) {
		HashMap<String, Object> sale = new HashMap<String, Object>();
		sale.put("district", record[0]);
		sale.put("count", record[1]);
		result.add(sale);
		}
		return result;

	}
	
	@Override
	public Map<String, Object> initFilter() {
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("schoolCategory", pSJpaRepository.getSchoolCategory());
		result.put("schoolDistricts", pSJpaRepository.getSchoolDistrict());
		result.put("religion", pSJpaRepository.getReligion());
		result.put("studentGender", pSJpaRepository.getStudentGender());
		result.put("schoolId",pSJpaRepository.getSchoolId());
		return result;
	}
	

}
