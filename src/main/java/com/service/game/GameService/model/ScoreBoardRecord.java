package com.service.game.GameService.model;

public class ScoreBoardRecord {
	
	private String playerName;
	private String playerId;
	private String gameId;
	private Long score;
	public ScoreBoardRecord() {}
	
	public ScoreBoardRecord(String playerName, String playerId, String gameId, Long score) {
		this.playerName = playerName;
		this.playerId = playerId;
		this.gameId = gameId;
		this.score = score;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
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
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	
	
}
