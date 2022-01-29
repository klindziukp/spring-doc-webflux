/*
 * Copyright (c) 2022. Dandelion tutorials
 */

package com.klindziuk.flux.springdoc.api.router;

import com.klindziuk.flux.springdoc.api.anno.CommonApiInfo;
import com.klindziuk.flux.springdoc.api.anno.DeletePlayerByIdApiInfo;
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
public class PlayerCommonRouter {

  @Bean
  @ConditionalOnProperty("com.klindziuk.flux.springdoc.api.router.common.enabled")
  @CommonApiInfo
  public RouterFunction<ServerResponse> playersRouter(PlayerHandler playerHandler){
    RequestPredicate getPlayersRoute =
        RequestPredicates.GET("/players")
            .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

    RequestPredicate getPlayerByIdRoute =
        RequestPredicates.GET("/players/{id}")
            .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

    RequestPredicate addPlayerRoute =
        RequestPredicates.POST("/players")
            .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

    RequestPredicate deletePlayerRoute =
        RequestPredicates.DELETE("/players/{id}")
            .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

    return RouterFunctions.route(getPlayersRoute, playerHandler::getAllPlayers)
        .andRoute(getPlayerByIdRoute, playerHandler::getPlayerById)
        .andRoute(addPlayerRoute, playerHandler::savePlayer)
        .andRoute(deletePlayerRoute, playerHandler::deletePlayerById);
  }
}
