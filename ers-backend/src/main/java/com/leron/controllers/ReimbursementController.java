package com.leron.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leron.exceptions.ReimbursementNotFoundException;
import com.leron.models.Reimbursement;
import com.leron.repositories.ReimbursementRepository;

@RestController
@RequestMapping("/reimbs")
@CrossOrigin(origins = "htp://localhost:4200")
public class ReimbursementController {

	@Autowired
	private ReimbursementRepository dao;
	
	@GetMapping
	public ResponseEntity<List<Reimbursement>> getAll(){
		return ResponseEntity.ok(dao.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Reimbursement>> findById(@PathVariable(name ="id") Long id) throws ReimbursementNotFoundException{
		Optional<Reimbursement> rb = dao.findById(id);
		if(rb == null ) {
			throw new ReimbursementNotFoundException("Could not find Reimbursement");
		}
		return ResponseEntity.ok(rb);
	}
	
	@PostMapping("/insert")
	public ResponseEntity<Reimbursement> insert(@RequestBody Reimbursement rb){
		if (dao.existsById(rb.getId())) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.accepted().body(dao.save(rb));
	}
	
	@DeleteMapping("/{id}") 
	public ResponseEntity<Void> delete(@PathVariable(name = "id") long id) {
		if(dao.existsById(id)) {
			dao.deleteById(id);
			return ResponseEntity.accepted().build();
		}
		return ResponseEntity.notFound().build();
	}
}
