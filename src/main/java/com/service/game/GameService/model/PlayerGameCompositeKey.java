package com.service.game.GameService.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PlayerGameCompositeKey implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String playerId;
	private String gameId;
	public PlayerGameCompositeKey() {}
	
	public PlayerGameCompositeKey(String playerId, String gameId) {
		super();
		this.playerId = playerId;
		this.gameId = gameId;
	}
	
	public String getPlayerId() {
		return playerId;
	}
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gameId == null) ? 0 : gameId.hashCode());
		result = prime * result + ((playerId == null) ? 0 : playerId.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayerGameCompositeKey other = (PlayerGameCompositeKey) obj;
		if (gameId == null) {
			if (other.gameId != null)
				return false;
		} else if (!gameId.equals(other.gameId))
			return false;
		if (playerId == null) {
			if (other.playerId != null)
				return false;
		} else if (!playerId.equals(other.playerId))
			return false;
		return true;
	}
	
	
}
