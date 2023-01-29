package com.players.football.service;

import java.util.List;
import java.util.Map;

import com.players.football.dto.PlayersDTO;
import com.players.football.exception.PlayersException;



public interface ServiceDAO {
	
	PlayersDTO addPlayersService(Integer id, PlayersDTO playersDTO);
	
	List<PlayersDTO> getPlayersService();
	
	String deletePlayersService(Integer sqnum);
	
	PlayersDTO getPlayersServiceById(Integer sqnum) throws PlayersException;
	
	PlayersDTO updatePlayersService(Integer sqnum, Map<String, Object> field);
	
	PlayersDTO replacePlayersService(Integer sqnum, PlayersDTO playersDTO);

}
