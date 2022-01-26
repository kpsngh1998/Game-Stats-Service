package com.service.game.GameService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.game.GameService.model.Player;
import com.service.game.GameService.repository.PlayerScoreRepository;

@Service
public class ScoreLoadServiceImpl implements ScoreLoadService {

	@Autowired
	private PlayerScoreRepository playerScoreRepository;
	
	@Override
	public List<Player> getTop5PlayersByScore() {
		return playerScoreRepository.findTop5ByOrderByScoreDesc();
	}

	@Override
	public List<Player> getTop5PlayersByUniqueScore() {
		List<Long> scores = playerScoreRepository.findDistinctScore();
		List<Player> players = playerScoreRepository.findAllPlayersByScoreIn(scores);
		return players;
	}
	
	@Override
	public Long getScore(Player player) {
		Long score = playerScoreRepository.findById(player.getPlayerGameCompositeKey()).map(Player::getScore).orElse(0L);
		return score;
	}

	@Override
	public void saveAllPlayers(List<? extends Player> players) {
		playerScoreRepository.saveAll(players);
	}

	@Override
	public void savePlayer(Player player) {
		playerScoreRepository.save(player);
	}

	@Override
	public List<Player> getTopKPlayersByScore(Integer k) {
		//return playerScoreRepository.findTopKByOrderByScoreDesc(k);
		return null;
	}
	
}
