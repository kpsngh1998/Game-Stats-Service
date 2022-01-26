package com.service.game.GameService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.service.game.GameService.model.Player;
import com.service.game.GameService.model.PlayerGameCompositeKey;

@Repository
public interface PlayerScoreRepository extends JpaRepository<Player, PlayerGameCompositeKey>{
	
	public List<Player> findTop5ByOrderByScoreDesc();
	
	@Query(value = "select distinct score from player order by score desc limit 5", nativeQuery = true)
	public List<Long> findDistinctScore();
	
	public List<Player> findAllPlayersByScoreIn(List<Long> scores);
	
	//@Query(value = "SELECT TOP :k FROM Player ORDER BY score DESC")
	//public List<Player> findTopKByOrderByScoreDesc(@Param("k") Integer k);
	
	
}
