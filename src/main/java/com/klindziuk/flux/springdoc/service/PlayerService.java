/*
 * Copyright (c) 2022. Dandelion tutorials
 */

package com.klindziuk.flux.springdoc.service;

import com.klindziuk.flux.springdoc.model.Player;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlayerService {

  Mono<Player> getPlayerById(Long id);
  Flux<Player> getAllPlayers();
  Mono<Player> addPlayer(Player playerInput);
  Mono<Player> deletePlayerById(Long id);
}
