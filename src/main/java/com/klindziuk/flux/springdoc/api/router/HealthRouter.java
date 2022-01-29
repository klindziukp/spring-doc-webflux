/*
 * Copyright (c) 2022. Dandelion tutorials
 */

package com.klindziuk.flux.springdoc.api.router;

import com.klindziuk.flux.springdoc.api.anno.HealthApiInfo;
import com.klindziuk.flux.springdoc.api.handler.HealthHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class HealthRouter {

  @Bean
  @HealthApiInfo
  public RouterFunction<ServerResponse> healthRouterFunction(HealthHandler healthHandler) {
    RequestPredicate healthRoute =
        RequestPredicates.GET("/health")
            .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

    return RouterFunctions.route(healthRoute, healthHandler::health);
  }
}


