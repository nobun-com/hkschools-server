package com.hkschool.data.repository.jpa;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.hkschool.bean.jpa.KGEntity;

public interface KGJpaRepository extends PagingAndSortingRepository<KGEntity, Long> {

	KGEntity findBySchoolId(String schoolId);
	
	KGEntity findById(Long id);

	@Query(nativeQuery=true, value="select distinct KG.school_category from kindergarten_school KG")
	List<String> getSchoolCategorys();

	@Query(nativeQuery=true, value="select distinct KG.district, COUNT(*) from kindergarten_school KG group by KG.district order by KG.district DESC")
	List<Object[]> getSchoolDistrictsAndCount();

	@Query(nativeQuery=true, value="select distinct KG.district from kindergarten_school KG group by KG.district order by KG.district DESC")
	List<Object[]> getSchoolDistricts();
	
	@Query(nativeQuery=true, value="select distinct KG.withchildcareservicesforchildrenagedunder2 from kindergarten_school KG")
	List<String> getwithchildcareservicesforchildrenagedunder2();
	
	@Query(nativeQuery=true, value="select distinct KG.time from kindergarten_school KG")
	List<String> getTime();

	@Query(nativeQuery=true, value="select * from kindergarten_school KG where KG.school_category like :schoolCategoury and KG.district like :schoolDistrict and KG.withchildcareservicesforchildrenagedunder2 like :withchildcareservicesforchildrenagedunder2 and KG.time like :time order by KG.district DESC")
	Iterable<KGEntity> findAllConditional(@Param(value = "schoolCategoury") String schoolCategoury, @Param(value = "schoolDistrict") String schoolDistrict, @Param(value = "withchildcareservicesforchildrenagedunder2") String withchildcareservicesforchildrenagedunder2, @Param(value = "time") String time);

	@Query(nativeQuery=true, value="select * from kindergarten_school KG where KG.id like :id")
	KGEntity getKindergartenSchoolDataById(@Param(value = "id")Long id);
}