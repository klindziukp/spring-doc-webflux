/*
 * Copyright (c) 2022. Dandelion tutorials
 */

package com.klindziuk.flux.springdoc.repository;

import com.klindziuk.flux.springdoc.model.Player;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface PlayerRepository extends ReactiveCrudRepository<Player, Long> {

  Flux<Player> findByClub(String club);
  Flux<Player> findByNationality(String nationality);

}