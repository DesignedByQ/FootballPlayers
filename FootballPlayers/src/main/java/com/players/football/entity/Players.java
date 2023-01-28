package com.players.football.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="players")
public class Players {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//using identity starts from 1 and auto increments
	private Integer squadNumber;
	private String name;
	private int age;
	private String position;
	private long wage;
	private long value;
	private Integer teamId;
	
	
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeam(Integer teamId) {
		this.teamId = teamId;
	}
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
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
	
	

	@Override
	public String toString() {
		return "Player [squadNumber=" + squadNumber + ", name=" + name + ", age=" + age + ", position=" + position
				+ ", wage=" + wage + ", value=" + value + ", teamId=" + teamId + "]";
	}
	

}
