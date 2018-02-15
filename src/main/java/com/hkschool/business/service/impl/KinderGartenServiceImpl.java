package com.hkschool.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	KGJpaRepository kGJpaRepository;

	@Override
	public KGEntity findById(Long id) {
		return kGJpaRepository.findById(id);
	}

	@Override
	public List<KGEntity> findAll(String schoolCategoury, String schoolDistrict, String withchildcareservicesforchildrenagedunder2,String time) {
		if(schoolCategoury == null || schoolCategoury.isEmpty()) {
			schoolCategoury =  "%";
		}
		if(schoolDistrict == null || schoolDistrict.isEmpty()) {
			schoolDistrict =  "%";
		}
		if(withchildcareservicesforchildrenagedunder2 == null || withchildcareservicesforchildrenagedunder2.isEmpty()) {
			withchildcareservicesforchildrenagedunder2 =  "%";
		}
		
		if(time == null || time.isEmpty()) {
			time =  "%";
		}
		Iterable<KGEntity> entities = kGJpaRepository.findAllConditional(schoolCategoury, schoolDistrict,withchildcareservicesforchildrenagedunder2,time);
		List<KGEntity> beans = new ArrayList<KGEntity>();

		for (KGEntity kgjEntity : entities) {
			beans.add(kgjEntity);
		}
		return beans;
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
