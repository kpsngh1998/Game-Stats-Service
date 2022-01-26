package com.service.game.GameService.model;

import java.util.List;
import java.util.stream.Collectors;

public class TopScorePlayers {

	
	private List<ScoreBoardRecord> topPlayersScoreBoard;

	public TopScorePlayers() {	}

	public TopScorePlayers(List<Player> topPlayers) {
		topPlayersScoreBoard = topPlayers.stream().map(a-> convert(a)).collect(Collectors.toList());

	}
	public List<ScoreBoardRecord> getTopPlayersScoreBoard() {
		return topPlayersScoreBoard;
	}
	public void setTopPlayersScoreBoard(List<ScoreBoardRecord> topPlayersScoreBoard) {
		this.topPlayersScoreBoard = topPlayersScoreBoard;
	}

	private static ScoreBoardRecord convert(Player p){
		ScoreBoardRecord sbr = new ScoreBoardRecord(p.getPlayerName(),p.getPlayerGameCompositeKey().getPlayerId(), p.getPlayerGameCompositeKey().getGameId(), p.getScore());
		return sbr;
	}
	
	
}
