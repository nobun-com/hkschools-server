package com.hkschool.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hkschool.bean.jpa.KGEntity;
import com.hkschool.business.service.KinderGartenService;
import com.hkschool.data.repository.jpa.KGJpaRepository;

@Component
@Transactional
public class KinderGartenServiceImpl implements KinderGartenService {

	@Autowired
	EntityManager em;

	@Autowired
	KGJpaRepository kGJpaRepository;

	@Override
	public KGEntity findById(Long id) {
		return kGJpaRepository.findById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findAll(String schoolCategoury, String schoolDistrict, String withchildcareservicesforchildrenagedunder2,String time) {

		String sql = "select KG.id, KG.district, KG.school_categoury_title, KG.nurseryamsessionfees, KG.nurserypmsessionfees, KG.nurserywdsessionfees, KG.withchildcareservicesforchildrenagedunder2, "
				+ "KG.total_no_of_permitte_accommodation_of_classroomsinuse, KG.time, KG.school_name from kindergarten_school KG where KG.id > 0";

		if(schoolCategoury != null && !schoolCategoury.isEmpty()) {
			sql = sql + " and KG.school_categoury_title = '" + schoolCategoury + "'" ;
		}
		if(schoolDistrict != null && !schoolDistrict.isEmpty()) {
			sql = sql + " and KG.district = '" + schoolDistrict + "'" ;
		}
		if(withchildcareservicesforchildrenagedunder2 != null && !withchildcareservicesforchildrenagedunder2.isEmpty()) {
			sql = sql + " and KG.withchildcareservicesforchildrenagedunder2 = '" + withchildcareservicesforchildrenagedunder2 + "'" ;
		}
		if(time != null && !time.isEmpty()) {
			sql = sql + " and KG.time = '" + time + "'" ;
		}
		
		List<Object[]> schoolsData = em.createNativeQuery(sql).getResultList();
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		for(Object[] schoolData : schoolsData) {
			String free = ("免費".equals((String)schoolData[3]) || "免費".equals((String)schoolData[4]) || "免費".equals((String)schoolData[5]) ) ? "免費" : "-";
			Map<String, Object> school = new HashMap<String, Object>();
			school.put("id", schoolData[0]);
			school.put("district", schoolData[1]);
			school.put("schoolCategory", schoolData[2]);
			school.put("free", free);
			school.put("withchildcareservicesforchildrenagedunder2", schoolData[6]);
			school.put("totalNoOfPermitteAccommodationOfClassroomsinuse", schoolData[7]);
			school.put("time", schoolData[8]);
			school.put("schoolName", schoolData[9]);
			result.add(school);
		}
				
		return result;
	}

	@Override
	public KGEntity findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KGEntity save(KGEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KGEntity update(KGEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KGEntity create(KGEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Map<String, Object>> getDistricts() {
		List<Object[]> records = kGJpaRepository.getSchoolDistrictsAndCount();

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
		
		result.put("schoolCategorys", kGJpaRepository.getSchoolCategorys());
		result.put("schoolDistricts", kGJpaRepository.getSchoolDistricts());
		result.put("withchildcareservicesforchildrenagedunder2", kGJpaRepository.getwithchildcareservicesforchildrenagedunder2());
		result.put("time", kGJpaRepository.getTime());
		return result;
	}
}
