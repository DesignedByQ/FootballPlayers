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
import org.springframework.web.client.RestTemplate;

import com.players.football.dto.PlayersDTO;
import com.players.football.dto.TeamsDTO;
import com.players.football.entity.Players;
import com.players.football.exception.PlayersException;
import com.players.football.repo.PlayersRepo;


@Service(value="serviceDAO")
public class ServiceDAOImpl implements ServiceDAO {
	
	@Autowired
	private PlayersRepo playersRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public PlayersDTO addPlayersService(Integer id, PlayersDTO playersDTO) {

		Players p = playersRepo.saveAndFlush(modelMapper.map(playersDTO, Players.class));
		
		PlayersDTO pdto = modelMapper.map(p, PlayersDTO.class);
		
		TeamsDTO tdto = restTemplate.getForObject("http://localhost:8081/api/team/"+id, TeamsDTO.class);
		
		pdto.setTeamsDTO(tdto);
		
		return pdto;
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

		Optional<Players> p = playersRepo.findById(sqnum);
		
		//Optional<Teams> t = teamRepo.findById(playersDTO.getTeamId()); rest t
		
		if(p.isPresent()) {
			
			p.get().setName(playersDTO.getName());
			p.get().setAge(playersDTO.getAge());
			p.get().setPosition(playersDTO.getPosition());
			p.get().setWage(playersDTO.getWage());
			p.get().setValue(playersDTO.getValue());
			p.get().setTeams(playersDTO.getTeamsDTO().getTeamId());
			
		}
		
		return modelMapper.map(playersRepo.save(p.get()), PlayersDTO.class);
		
		//rest t to turn p teamid to actual team then return as dto
		
	}


}
