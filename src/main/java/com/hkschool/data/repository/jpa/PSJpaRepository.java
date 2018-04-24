package com.hkschool.data.repository.jpa;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.hkschool.bean.jpa.PSEntity;


public interface PSJpaRepository extends PagingAndSortingRepository<PSEntity, Long> {

	PSEntity findBySchoolId(String schoolId);
	
	PSEntity findById(Long id);
	
	@Query(nativeQuery=true, value="select distinct PS.school_category from primary_school PS")
	List<String> getSchoolCategory();
	
	@Query(nativeQuery=true, value="select distinct PS.district, COUNT(*) from primary_school PS group by PS.district order by PS.district DESC")
	List<Object[]> getSchoolDistrictAndCount();
	
	@Query(nativeQuery=true, value="select distinct PS.district from primary_school PS group by PS.district order by PS.district DESC")
	List<Object[]> getSchoolDistrict();
	
	@Query(nativeQuery=true, value="select distinct PS.religion from primary_school PS")
	List<String> getReligion();
	
	@Query(nativeQuery=true, value="select distinct PS.student_gender from primary_school PS")
	List<String> getStudentGender();
	
	@Query(nativeQuery=true, value="select distinct PS.school_id from primary_school PS")
	List<String> getSchoolId();

}