package com.hkschool.data.repository.jpa;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.hkschool.bean.jpa.SSEntity;

public interface SSJpaRepository extends PagingAndSortingRepository<SSEntity, Long> {

	SSEntity findBySchoolId(String schoolId);
	
	SSEntity findById(Long id);

	@Query(nativeQuery=true, value="select distinct SS.school_type from secondary_school SS")
	List<String> getSchoolCategory();
	
	@Query(nativeQuery=true, value="select distinct SS.district, COUNT(*) from secondary_school SS group by SS.district order by SS.district DESC")
	List<Object[]> getSchoolDistrictAndCount();
	
	@Query(nativeQuery=true, value="select distinct SS.district from secondary_school SS group by SS.district order by SS.district DESC")
	List<Object[]> getSchoolDistrict();
	
	@Query(nativeQuery=true, value="select distinct SS.religion from secondary_school SS")
	List<String> getReligion();
	
	@Query(nativeQuery=true, value="select distinct SS.student_gender from secondary_school SS")
	List<String> getStudentGender();
	
	@Query(nativeQuery=true, value="select distinct SS.sponsoring_body from secondary_school SS")
	List<String> getSponsoringBody();
		
	@Query(nativeQuery=true, value="select * from secondary_school SS where SS.school_type like :schoolCategory and SS.district like :schoolDistrict  and SS.religion like :religion  and SS.student_gender like :studentGender  and SS.sponsoring_body like :sponsoringBody order by SS.district DESC")
	Iterable<SSEntity> findAllConditional(@Param(value = "schoolCategory") String schoolCategory, @Param(value = "schoolDistrict") String schoolDistrict, @Param(value = "religion") String religion, @Param(value = "studentGender") String studentGender, @Param(value = "sponsoringBody") String sponsoringBody);
}