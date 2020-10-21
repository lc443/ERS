package com.leron.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leron.models.Reimbursement;
import com.leron.repositories.ReimbursementRepository;

@RestController
@RequestMapping("/reimbs")
public class ReimbursementController {

	@Autowired
	private ReimbursementRepository dao;
	
	@GetMapping
	public ResponseEntity<List<Reimbursement>> getAll(){
		return ResponseEntity.ok(dao.findAll());
	}
	
}
