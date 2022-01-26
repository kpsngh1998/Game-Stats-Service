package com.service.game.GameService.service;

import java.util.List;

import com.service.game.GameService.model.Player;

public interface ScoreLoadService {
	
	public List<Player> getTop5PlayersByScore();
	public List<Player> getTopKPlayersByScore(Integer k);
	public Long getScore(Player player);
	public void saveAllPlayers(List<? extends Player> players);
	public void savePlayer(Player player);
	public List<Player> getTop5PlayersByUniqueScore();
}
