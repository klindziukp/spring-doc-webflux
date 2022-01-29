/*
 * Copyright (c) 2022. Dandelion tutorials
 */

package com.klindziuk.flux.springdoc.api.anno;

import com.klindziuk.flux.springdoc.model.ErrorResponse;
import com.klindziuk.flux.springdoc.model.Player;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@RouterOperations({
  @RouterOperation(
      method = RequestMethod.GET,
      operation =
          @Operation(
              description = "Get all players ",
              operationId = "getAllPlayers",
              tags = "player",
              responses = {
                @ApiResponse(
                    responseCode = "200",
                    description = "Get all players endpoint",
                    content = {
                      @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          array = @ArraySchema(schema = @Schema(implementation = Player.class)))
                    }),
                @ApiResponse(
                    responseCode = "400",
                    description = "Not found response",
                    content = {
                      @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          schema = @Schema(implementation = ErrorResponse.class))
                    })
              }))
})
public @interface GetAllPlayersApiInfo {}
