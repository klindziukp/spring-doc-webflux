/*
 * Copyright (c) 2022. Dandelion tutorials
 */

package com.klindziuk.flux.springdoc.service;

import com.klindziuk.flux.springdoc.exception.EntityMappingException;
import com.klindziuk.flux.springdoc.model.Player;
import com.klindziuk.flux.springdoc.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class PlayerServiceImpl implements PlayerService {

  private final PlayerRepository playerRepository;

  @Autowired
  public PlayerServiceImpl(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  @Override
  public Flux<Player> getAllPlayers() {
    final String errorMessage = "There is an issue getting all of players.";
    return processWithErrorCheck(this.playerRepository.findAll(), errorMessage);
  }

  @Override
  public Mono<Player> getPlayerById(Long id) {
    final String errorMessage = String.format("There is no player with id: '%d'", id);
    return processWithErrorCheck(this.playerRepository.findById(id), errorMessage);
  }

  @Override
  public Mono<Player> addPlayer(Player player) {
    final String errorMessage = "Unable to add player with input:" + player;
    return processWithErrorCheck(this.playerRepository.save(new Player(player)), errorMessage);
  }

  @Override
  public Mono<Player> deletePlayerById(Long id) {
    return getPlayerById(id).map(player -> {
      this.playerRepository.deleteById(id).subscribe();
      return player;
    });
  }

  private <T> Mono<T> processWithErrorCheck(Mono<T> monoToCheck, String errorMessage) {
    return monoToCheck.switchIfEmpty(Mono.defer(() -> {
      log.error(errorMessage);
      return Mono.error(new EntityMappingException(errorMessage));
    }));
  }

  private <T> Flux<T> processWithErrorCheck(Flux<T> fluxToCheck, String errorMessage) {
    return fluxToCheck.switchIfEmpty(Flux.defer(() -> {
      log.error(errorMessage);
      return Flux.error(new EntityMappingException(errorMessage));
    }));
  }
}
