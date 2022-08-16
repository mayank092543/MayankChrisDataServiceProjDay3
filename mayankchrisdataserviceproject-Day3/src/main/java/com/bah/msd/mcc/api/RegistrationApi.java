package com.bah.msd.mcc.api;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.bah.msd.mcc.domain.Registration;
import com.bah.msd.mcc.repository.RegistrationRepository;

@RestController
@RequestMapping("/registrations")
public class RegistrationApi {
	
	@Autowired
	RegistrationRepository repo;
	
	@GetMapping
	public Iterable<Registration> getAll() {
		return repo.findAll();
	}
	
	@GetMapping("/{registrationId}")
	public Optional<Registration> getRegisrationById(@PathVariable("registrationId") long id) {
		return repo.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<?> addRegistration(@RequestBody Registration newRegistration, UriComponentsBuilder uri) {
		if(newRegistration.getId() != 0 || newRegistration.getCustomer_id() == null || newRegistration.getEvent_id() == null) {
			return ResponseEntity.badRequest().build();
		}
		newRegistration = repo.save(newRegistration);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newRegistration.getId()).toUri();
		ResponseEntity<?> response = ResponseEntity.created(location).build();
		return response;
	}
	
	@PutMapping("/{registrationId}")
	public ResponseEntity<?> updateRegistration(@RequestBody Registration newRegistration, @PathVariable("registrationId") long regisrationId){
		if(newRegistration.getId() != regisrationId || newRegistration.getCustomer_id() == null || newRegistration.getEvent_id() == null) {
			return ResponseEntity.badRequest().build();
		}
		newRegistration = repo.save(newRegistration);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{eventId}")
	public ResponseEntity<?> deleteRegisrationById(@PathVariable("eventId") long id){
		repo.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
