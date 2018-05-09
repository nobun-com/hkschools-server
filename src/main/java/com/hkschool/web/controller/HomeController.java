package com.hkschool.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hkschool.bean.jpa.KGEntity;
import com.hkschool.bean.jpa.PSEntity;
import com.hkschool.bean.jpa.SSEntity;
import com.hkschool.business.service.KinderGartenService;
import com.hkschool.business.service.PrimarySchoolService;
import com.hkschool.business.service.SecondarySchoolService;
import com.hkschool.rest.common.BaseController;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController {

	@Resource
	private KinderGartenService kinderGartenService;
	
	@Resource
	private PrimarySchoolService primaryService;
	
	@Resource
	private SecondarySchoolService secondaryservice;
	
	@RequestMapping("/")
	public String admin(Model model) {
		model.addAttribute("kgDistricts", kinderGartenService.getDistricts());
		model.addAttribute("psDistricts", primaryService.getDistrict());
		model.addAttribute("ssDistricts", secondaryservice.getDistrict());
		return "index";
	}

	@RequestMapping("/map")
	public String map(Model model) {
		return "map-view";
	}

	@RequestMapping("/kindergarten-list")
	public String kindergartenList(HttpServletRequest request, Model model) {
		String district = request.getParameter("district");
		model.addAttribute("district", district);
		model.addAttribute("kgDistricts", kinderGartenService.getDistricts());
		model.addAttribute("psDistricts", primaryService.getDistrict());
		model.addAttribute("ssDistricts", secondaryservice.getDistrict());
		return "kindergarten-list";
	}
	
	@RequestMapping("/primary-list")
	public String primaryList(HttpServletRequest request, Model model) {
		String district = request.getParameter("district");
		model.addAttribute("district", district);
		model.addAttribute("kgDistricts", kinderGartenService.getDistricts());
		model.addAttribute("psDistricts", primaryService.getDistrict());
		model.addAttribute("ssDistricts", secondaryservice.getDistrict());
		return "primary-list";
	}
	
	@RequestMapping("/secondary-list")
	public String secondaryList(HttpServletRequest request, Model model) {
		String district = request.getParameter("district");
		model.addAttribute("district", district);
		model.addAttribute("kgDistricts", kinderGartenService.getDistricts());
		model.addAttribute("psDistricts", primaryService.getDistrict());
		model.addAttribute("ssDistricts", secondaryservice.getDistrict());
		return "secondary-list";
	}
	
	@RequestMapping("/home")
	public String home(Model model) {
		model.addAttribute("kgDistricts", kinderGartenService.getDistricts());
		model.addAttribute("psDistricts", primaryService.getDistrict());
		model.addAttribute("ssDistricts", secondaryservice.getDistrict());
		return "index";
	}
	
	@RequestMapping("/kinderGarten-detail")
	public String kindergartenDetail(HttpServletRequest request, Model model) {
		KGEntity kGEntity =kinderGartenService.findById(Long.parseLong(request.getParameter("schoolId")));
		model.addAttribute("kGEntity",kGEntity);
		return "kindergarten-detail";
	}
	
	@RequestMapping("/primary-detail")
	public String primaryDetail(HttpServletRequest request,Model model) {
		PSEntity pSEntity =primaryService.findById(Long.parseLong(request.getParameter("schoolId")));
		model.addAttribute("pSEntity",pSEntity);
		return "primary-detail";
	}
	@RequestMapping("/secondary-detail")
	public String secondaryDetail(HttpServletRequest request,Model model) {
		SSEntity sSEntity =secondaryservice.findById(Long.parseLong(request.getParameter("schoolId")));
		model.addAttribute("sSEntity",sSEntity);
		return "secondary-detail";
	}
	
}
