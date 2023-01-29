package com.players.football.dto;

import org.springframework.context.annotation.PropertySource;

import jakarta.validation.constraints.NotNull;

//@PropertySource("classpath:vsdvsv.ssffs.fsfsfs")
public class PlayersDTO {
	
	private Integer squadNumber;
	private String name;
	private Integer age;
	private String position;
	private long wage;
	private long value;
	//@NotNull(message="teams ref ID must not be null")
	private TeamsDTO teamsDTO;

	
	public Integer getSquadNumber() {
		return squadNumber;
	}
	public void setSquadNumber(Integer squadNumber) {
		this.squadNumber = squadNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public long getWage() {
		return wage;
	}
	public void setWage(long wage) {
		this.wage = wage;
	}
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}
	public TeamsDTO getTeamsDTO() {
		return teamsDTO;
	}
	public void setTeamsDTO(TeamsDTO teamsDTO) {
		this.teamsDTO = teamsDTO;
	}
	
	@Override
	public String toString() {
		return "PlayerDTO [squadNumber=" + squadNumber + ", name=" + name + ", age=" + age + ", position=" + position
				+ ", wage=" + wage + ", value=" + value + ", teamsDTO=" + teamsDTO + "]";
	}
	
	
	
}
