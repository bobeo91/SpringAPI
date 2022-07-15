package com.vti.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.DepartmentDTO;
import com.vti.entity.Department;
import com.vti.filter.DepartmentFilterForm;
import com.vti.form.CreateDepartmentForm;
import com.vti.service.IDepartmentService;

@RestController
@RequestMapping(value = "v1/api/departments")
@CrossOrigin("*")
@Validated
public class DepartmentController {
	@Autowired
	private IDepartmentService service;
	@Autowired
	ModelMapper mapper;

	@GetMapping()
	public Page<DepartmentDTO> getAll(Pageable pageable, DepartmentFilterForm filterForm) {
		Page<Department> departmentPages = service.getAllDepartments(pageable, filterForm);
		List<Department> listDepartments = departmentPages.getContent();
		List<DepartmentDTO> Dpdtos = mapper.map(listDepartments, new TypeToken<List<DepartmentDTO>>() {
		}.getType());
		Page<DepartmentDTO> dtoPages = new PageImpl<>(Dpdtos, pageable, departmentPages.getTotalElements());

		return dtoPages;
	}

	// resful api
	@GetMapping(value = "/{idDepartment}")
	public DepartmentDTO getById(@PathVariable(value = "idDepartment") int id) {
		Department department = service.getDepartmentById(id);
		DepartmentDTO departmentDTO = mapper.map(department, DepartmentDTO.class);
//		departmentDTO.add(linkTo(methodOn(DepartmentController.class).getById(id)).withSelfRel());
		return departmentDTO;

	}

//	@GetMapping(value = "/{departmentName}")
//	public Department getByName(@PathVariable(value = "departmentName") String name) {
//		return service.getDepartmentByName(name);
//	}

	@PostMapping()
	public String createDepartment( @RequestBody @Valid CreateDepartmentForm form) {

		try {
			if (service.createDepartment(form) != null) {
				return " Create Success ";
			}
		} catch (Exception e) {

			return " Create Failed: " + e.getMessage();
		}
		return null;

	}

	@PutMapping(value = "/{id}")
	public String updateDepartment(@PathVariable(name = "id") int id, @RequestBody CreateDepartmentForm form) {

		try {
			if (service.updateDepartment(id, form) != null) {
				return "Update Success";
			}
		} catch (Exception e) {
			return "Update Failed: " + e.getMessage();
		}
		return null;
	}

	@DeleteMapping(value = "/{id}")
	public String deleteById(@PathVariable(name = "id") int id) throws Exception {
		service.deleteDepartment(id);
		return "Delete Success ";
	}
}
