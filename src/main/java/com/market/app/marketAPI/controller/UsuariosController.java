package com.market.app.marketAPI.controller;

import com.market.app.marketAPI.entity.Usuarios;
import com.market.app.marketAPI.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UsuariosController {

	@Autowired
	private UsuariosService usuariosService;


	@GetMapping("/")
	public ResponseEntity<List<Usuarios>> getAllUsers() {
		return ResponseEntity.ok().body(usuariosService.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Usuarios>> geUserById(@PathVariable BigInteger id) {
		return ResponseEntity.ok().body(usuariosService.getById(id));
	}

	@PostMapping("/")
	public ResponseEntity<Boolean> createUser(@RequestBody Usuarios user) {
		return ResponseEntity.ok().body(usuariosService.create(user));
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Boolean> updateUserById(@RequestBody Usuarios user, BigInteger id) {
		return ResponseEntity.ok().body(usuariosService.update(user, id));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deleteUserById(BigInteger id) {
		return ResponseEntity.ok().body(usuariosService.delete(id));
	}
}
