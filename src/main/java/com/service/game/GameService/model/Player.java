package com.service.game.GameService.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Player")
public class Player {
	
	@EmbeddedId
    private PlayerGameCompositeKey playerGameCompositeKey;
    private String playerName;
    private Long score;
	
    public Player() {}
	
	public Player(PlayerGameCompositeKey playerGameCompositeKey, String playerName, Long score) {
		this.playerGameCompositeKey = playerGameCompositeKey;
		this.playerName = playerName;
		this.score = score;
	}
	
	public PlayerGameCompositeKey getPlayerGameCompositeKey() {
		return playerGameCompositeKey;
	}
	public void setPlayerGameCompositeKey(PlayerGameCompositeKey playerGameCompositeKey) {
		this.playerGameCompositeKey = playerGameCompositeKey;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Player [playerGameCompositeKey=" + playerGameCompositeKey + ", playerName=" + playerName + ", score="
				+ score + "]";
	}
	
    
    
    
}
