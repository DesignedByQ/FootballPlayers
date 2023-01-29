package com.players.football.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.players.football.entity.Players;

@Repository
public interface PlayersRepo extends JpaRepository<Players, Integer>{

}
