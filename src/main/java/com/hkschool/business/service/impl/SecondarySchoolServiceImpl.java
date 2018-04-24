package com.hkschool.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

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
	EntityManager em;

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

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findAllSecondary(String schoolCategory, String schoolDistrict, String religion, String studentGender, String sponsoringBody) {

		String sql = "select SS.id, SS.school_name, SS.school_type, SS.district, SS.sponsoring_body, SS.student_gender, SS.religion from secondary_school SS where SS.id > 0";

		if(schoolCategory != null && !schoolCategory.isEmpty()) {
			sql = sql + " and SS.school_type = '" + schoolCategory + "'" ;
		}
		if(schoolDistrict != null && !schoolDistrict.isEmpty()) {
			sql = sql + " and SS.district = '" + schoolDistrict + "'" ;
		}
		if(religion != null && !religion.isEmpty()) {
			sql = sql + " and SS.religion = '" + religion + "'" ;
		}
		if(studentGender != null && !studentGender.isEmpty()) {
			sql = sql + " and SS.student_gender = '" + studentGender + "'" ;
		}
		if(sponsoringBody != null && !sponsoringBody.isEmpty()) {
			sql = sql + " and SS.sponsoring_body = '" + sponsoringBody + "'" ;
		}
		
		List<Object[]> schoolsData = em.createNativeQuery(sql).getResultList();
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		for(Object[] schoolData : schoolsData) {
			Map<String, Object> school = new HashMap<String, Object>();
			school.put("id", schoolData[0]);
			school.put("schoolName", schoolData[1]);
			school.put("schoolType", schoolData[2]);
			school.put("district", schoolData[3]);
			school.put("sponsoringBody", schoolData[4]);
			school.put("studentGender", schoolData[5]);
			school.put("religion", schoolData[6]);
			result.add(school);
		}
		return result;
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
