/*
 * Copyright (c) 2022. Dandelion tutorials
 */

package com.klindziuk.flux.springdoc.api.router;

import com.klindziuk.flux.springdoc.api.anno.AddPlayerApiInfo;
import com.klindziuk.flux.springdoc.api.anno.DeletePlayerByIdApiInfo;
import com.klindziuk.flux.springdoc.api.anno.GetAllPlayersApiInfo;
import com.klindziuk.flux.springdoc.api.anno.GetPlayerByIdApiInfo;
import com.klindziuk.flux.springdoc.api.handler.PlayerHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@ConditionalOnProperty("com.klindziuk.flux.springdoc.api.router.separate.enabled")
public class PlayerRouter {

  @Bean
  @GetAllPlayersApiInfo
  public RouterFunction<ServerResponse> getAllPlayersRouterFunction(PlayerHandler playerHandler){
    RequestPredicate getPlayersRoute =
        RequestPredicates.GET("/players")
            .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

    return RouterFunctions.route(getPlayersRoute, playerHandler::getAllPlayers);
  }

  @Bean
  @GetPlayerByIdApiInfo
  public RouterFunction<ServerResponse> getPlayerByIdRouterFunction(PlayerHandler playerHandler){
    RequestPredicate getPlayersRoute =
        RequestPredicates.GET("/players/{id}")
            .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

    return RouterFunctions.route(getPlayersRoute, playerHandler::getPlayerById);
  }

  @Bean
  @AddPlayerApiInfo
  public RouterFunction<ServerResponse> addPlayerRouterFunction(PlayerHandler playerHandler){
    RequestPredicate getPlayersRoute =
        RequestPredicates.POST("/players")
            .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

    return RouterFunctions.route(getPlayersRoute, playerHandler::savePlayer);
  }

  @Bean
  @DeletePlayerByIdApiInfo
  public RouterFunction<ServerResponse> deletePlayerRouterFunction(PlayerHandler playerHandler){
    RequestPredicate getPlayersRoute =
        RequestPredicates.DELETE("/players/{id}")
            .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

    return RouterFunctions.route(getPlayersRoute, playerHandler::deletePlayerById);
  }
}
