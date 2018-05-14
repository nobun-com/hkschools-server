/*
 * Created on 17 Aug 2017 ( Time 15:17:01 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.hkschool.rest.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hkschool.business.service.KinderGartenService;
import com.hkschool.business.service.PrimarySchoolService;
import com.hkschool.business.service.SecondarySchoolService;
import com.hkschool.rest.common.BaseController;

@Controller
public class HomeRestController extends BaseController {

	@Resource
	private KinderGartenService kinderGartenService;

	@RequestMapping(value = "/getAllKinderGarten", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, List<Map<String, Object>>> findAll(HttpServletRequest req) {
		String schoolCategoury = req.getParameter("schoolCategoury");
		String schoolDistrict = req.getParameter("schoolDistrict");
		String withchildcareservicesforchildrenagedunder2 = req.getParameter("withchildcareservicesforchildrenagedunder2");
		String time = req.getParameter("time");
		Map<String, List<Map<String, Object>>> data = new HashMap<String, List<Map<String, Object>>>();
		data.put("data", kinderGartenService.findAll(schoolCategoury, schoolDistrict,withchildcareservicesforchildrenagedunder2,time));
		return data;
	}

	@RequestMapping(value = "/getKGFilters", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, Object> initKGFilters(HttpServletRequest req) {
		return kinderGartenService.initFilter();
	}

	@Resource
	private PrimarySchoolService primaryService;

	@RequestMapping(value = "/getPSFilters", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, Object> initPSFilters(HttpServletRequest req) {
		return primaryService.initFilter();
	}
	
	@RequestMapping(value = "/getAllPrimarySchool", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, List<Map<String, Object>>> findAllPrimary(HttpServletRequest req) {
		String schoolCategory = req.getParameter("schoolCategory");
		String schoolDistrict = req.getParameter("schoolDistrict");
		String religion = req.getParameter("religion");
		String studentGender = req.getParameter("studentGender");
		Map<String, List<Map<String, Object>>> data = new HashMap<String, List<Map<String, Object>>>();
		data.put("data", primaryService.findAllPrimary(schoolCategory,schoolDistrict,religion,studentGender));
		return data;
	}
	
	@Resource
	private SecondarySchoolService secondaryservice;
	
	@RequestMapping(value = "/getSSFilters", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, Object> initgetSSFilters(HttpServletRequest req) {
		return secondaryservice.initFilter();
	}

	@RequestMapping(value = "/getAllSecondarySchool", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, List<Map<String, Object>>> findAllSecondary(HttpServletRequest req) {
		String schoolCategory = req.getParameter("schoolCategory");
		String schoolDistrict = req.getParameter("schoolDistrict");
		String religion = req.getParameter("religion");
		String studentGender = req.getParameter("studentGender");
		String sponsoringBody =req.getParameter("sponsoringBody");
		Map<String, List<Map<String, Object>>> data = new HashMap<String, List<Map<String, Object>>>();
		data.put("data",secondaryservice.findAllSecondary(schoolCategory,schoolDistrict,religion,studentGender,sponsoringBody));
		return data;
	}
	
	@RequestMapping(value = "/getLocations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Object[]> getLocations(HttpServletRequest req) {
		String schoolType = req.getParameter("schoolType");
		String district = req.getParameter("district");
		return secondaryservice.getLocations(schoolType, district);
	}
	
	@RequestMapping(value = "/getDistricts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, List<Map<String, Object>>> getDistricts(HttpServletRequest req) {
		HashMap<String, List<Map<String, Object>>> result = new HashMap<String, List<Map<String, Object>>>();
		result.put("kindergarten", kinderGartenService.getDistricts());
		result.put("primary", primaryService.getDistrict());
		result.put("secondary", secondaryservice.getDistrict());
		return result;
	}
	
}
