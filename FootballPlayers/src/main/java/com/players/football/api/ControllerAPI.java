package com.players.football.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.players.football.dto.PlayersDTO;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Validated
public class ControllerAPI {
	
	@Autowired
	private ServiceDAOImpl serviceDAOImpl;
	
	@PostMapping(value = "/transfer", consumes = {MediaType.ALL_VALUE})
	public ResponseEntity<PlayersDTO> addPlayers(@Valid @RequestBody PlayersDTO playersDTO) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(serviceDAOImpl.addPlayersService(playersDTO));
		
	}
	
	@GetMapping(value = "/allplayers", consumes = {MediaType.ALL_VALUE}, produces = {"application/json", "application/xml"})
	public ResponseEntity<List<PlayersDTO>> getPlayers(){
		
		return ResponseEntity.status(HttpStatus.OK).body(serviceDAOImpl.getPlayersService());
		
	}
	
	@DeleteMapping(value = "/sell/{sqnum}", consumes = {MediaType.ALL_VALUE})
	public ResponseEntity<String> deletePlayers(@PathVariable("sqnum") Integer sqnum) {
		
		return ResponseEntity.status(HttpStatus.OK).body(serviceDAOImpl.deletePlayersService(sqnum));
		
	}
	
	@GetMapping(value = "/player/{sqnum}", consumes = {MediaType.ALL_VALUE})
	public ResponseEntity<PlayersDTO> getPlayers(@PathVariable Integer sqnum){
		
		return ResponseEntity.status(HttpStatus.OK).body(serviceDAOImpl.getPlayersServiceById(sqnum));
		
	}
	
	@PatchMapping(value ="/update/{sqnum}")
	public ResponseEntity<PlayersDTO> updatePlayers(@PathVariable Integer sqnum, @RequestBody Map<String, Object> fields) {
		
		return ResponseEntity.status(HttpStatus.OK).body(serviceDAOImpl.updatePlayersService(sqnum, fields));
		
	}
	
	@PutMapping(value ="/replace/{sqnum}")
	public ResponseEntity<PlayersDTO> replacePlayers(@PathVariable Integer sqnum, @RequestBody PlayersDTO playersDTO) {
		
		return ResponseEntity.status(HttpStatus.OK).body(serviceDAOImpl.replacePlayersService(sqnum, playersDTO));
	}


}
