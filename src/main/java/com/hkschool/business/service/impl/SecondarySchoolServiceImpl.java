package com.hkschool.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hkschool.bean.jpa.SSEntity;
import com.hkschool.business.service.SecondarySchoolService;
import com.hkschool.data.repository.jpa.SSJpaRepository;

@Component
@Transactional
public class SecondarySchoolServiceImpl implements SecondarySchoolService {

	@Autowired
	SSJpaRepository sSJpaRepository;

	@Override
	public SSEntity findById(Long id) {
		return sSJpaRepository.findById(id);
	}

	@Override
	public List<SSEntity> findAll() {
		Iterable<SSEntity> entities = sSJpaRepository.findAll();
		List<SSEntity> beans = new ArrayList<SSEntity>();

		for (SSEntity ssjEntity : entities) {
			beans.add(ssjEntity);
		}
		return beans;
	}

	
	@Override
	public List<SSEntity> findAllSecondary(String schoolCategory, String schoolDistrict, String religion, String studentGender, String sponsoringBody) {
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
		if(sponsoringBody == null || sponsoringBody.isEmpty()) {
			sponsoringBody =  "%";
		}
		
		Iterable<SSEntity> entities = sSJpaRepository.findAllConditional(schoolCategory,schoolDistrict,religion,studentGender,sponsoringBody);
		List<SSEntity> beans = new ArrayList<SSEntity>();

		for (SSEntity ssjEntity : entities) {
			beans.add(ssjEntity);
		}
		return beans;
	}
	
	@Override
	public SSEntity findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SSEntity save(SSEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SSEntity update(SSEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SSEntity create(SSEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public List<Map<String, Object>> getDistrict() {
		List<Object[]> records = sSJpaRepository.getSchoolDistrictAndCount();

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for(Object[] record : records) {
		HashMap<String, Object> sale = new HashMap<String, Object>();
		sale.put("district", record[0]);
		sale.put("count", record[1]);
		result.add(sale);
		}
		return result;

	}
	
	public Map<String, Object> initFilter() {
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("schoolCategory", sSJpaRepository.getSchoolCategory());
		result.put("schoolDistricts", sSJpaRepository.getSchoolDistrict());
		result.put("religion", sSJpaRepository.getReligion());
		result.put("studentGender", sSJpaRepository.getStudentGender());
		result.put("sponsoringBody",sSJpaRepository.getSponsoringBody());
		return result;
	}


}
