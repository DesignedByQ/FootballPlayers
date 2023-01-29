package com.players.football.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import com.players.football.dto.PlayersDTO;
import com.players.football.entity.Players;
import com.players.football.exception.PlayersException;
import com.players.football.repo.PlayersRepo;


@Service(value="serviceDAO")
public class ServiceDAOImpl implements ServiceDAO {
	
	@Autowired
	private PlayersRepo playersRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PlayersDTO addPlayersService(PlayersDTO playersDTO) {

		Players pdto = playersRepo.saveAndFlush(modelMapper.map(playersDTO, Players.class));
		
		return modelMapper.map(pdto, PlayersDTO.class);
	}



	@Override
	public List<PlayersDTO> getPlayersService() {
		
		List<Players> p = playersRepo.findAll();
		
		List<PlayersDTO> pl = new ArrayList();
		
		for(Players a: p) {
			
			pl.add(modelMapper.map(a, PlayersDTO.class));
			
		}
		
		return pl;
				
	}



	@Override
	public String deletePlayersService(Integer sqnum) {

		playersRepo.deleteById(sqnum);
		
		return "Player number " + sqnum + " has been sold!";
		
	}
	
	

	@Override
	public PlayersDTO getPlayersServiceById(Integer sqnum) throws PlayersException {

		Optional<Players> op = playersRepo.findById(sqnum);//.orElseThrow(TeamNotFoundException::new);
		
		PlayersDTO pdto = null;
		
		if(!op.isPresent()) {		
			
			throw new PlayersException("No player is present at this ID");
			
		} else {
			
			Players p = op.get();
			
			pdto = modelMapper.map(p, PlayersDTO.class);
			
		}
		
		return pdto;
		
	}



	@Override
	public PlayersDTO updatePlayersService(Integer sqnum, Map<String, Object> fields) {

		Optional<Players> p = playersRepo.findById(sqnum);
		
		if(p.isPresent()) {
			
			fields.forEach((key, value)->{
				
				Field field = ReflectionUtils.findRequiredField(Players.class, key);
				
				field.setAccessible(true);
				
				ReflectionUtils.setField(field, p.get(), value);
				
			});
			
			return modelMapper.map(playersRepo.save(p.get()), PlayersDTO.class);
			
		}
	
		return null;
		
	}



	@Override
	public PlayersDTO replacePlayersService(Integer sqnum, PlayersDTO playersDTO) {

		Optional<Players> p = playerRepo.findById(sqnum);
		
		Optional<Team> t = teamRepo.findById(playersDTO.getTeamId());
		
		if(p.isPresent() & t.isPresent()) {
			
			p.get().setName(playerDTO.getName());
			p.get().setAge(playerDTO.getAge());
			p.get().setPosition(playerDTO.getPosition());
			p.get().setWage(playerDTO.getWage());
			p.get().setValue(playerDTO.getValue());
			p.get().setTeam(t.get());
			
		}
		
		return modelMapper.map(playersRepo.save(p.get()), PlayersDTO.class);
		
	}


}
