/*
 * Copyright (c) 2022. Dandelion tutorials
 */

package com.klindziuk.flux.springdoc.api.handler;

import com.klindziuk.flux.springdoc.model.Player;
import com.klindziuk.flux.springdoc.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
public class PlayerHandler {

  private final PlayerService playerService;

  @Autowired
  public PlayerHandler(PlayerService playerService) {
    this.playerService = playerService;
  }

  @NonNull
  public Mono<ServerResponse> getAllPlayers(ServerRequest request) {
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(playerService.getAllPlayers(), Player.class);
  }

  @NonNull
  public Mono<ServerResponse> getPlayerById(ServerRequest request) {
    final long id = Long.parseLong(request.pathVariable("id"));
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(playerService.getPlayerById(id), Player.class);
  }

  @NonNull
  public Mono<ServerResponse> savePlayer(ServerRequest request) {
    return request
        .bodyToMono(Player.class)
        .doOnNext(playerService::addPlayer)
        .flatMap(
            itemInfo ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Mono.just(itemInfo), Player.class));
  }

  @NonNull
  public Mono<ServerResponse> deletePlayerById(ServerRequest request) {
    final long id = Long.parseLong(request.pathVariable("id"));
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(playerService.deletePlayerById(id), Player.class);
  }
}
