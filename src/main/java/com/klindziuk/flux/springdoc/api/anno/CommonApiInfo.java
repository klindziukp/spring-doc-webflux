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
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
      method = RequestMethod.POST,
      path = "/players",
      operation =
          @Operation(
              description = "Add player common router",
              operationId = "addPlayer",
              tags = "player",
              requestBody =
                  @RequestBody(
                      description = "Player to add",
                      required = true,
                      content = @Content(schema = @Schema(implementation = Player.class,
                          requiredProperties = {"name", "age", "club", "nationality"}))),
              responses = {
                  @ApiResponse(
                      responseCode = "200",
                      description = "Add player response common router",
                      content = {
                        @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Player.class))
                      }),
                  @ApiResponse(
                      responseCode = "400",
                      description = "Bad Request response",
                      content = {
                          @Content(
                              mediaType = MediaType.APPLICATION_JSON_VALUE,
                              schema = @Schema(implementation = ErrorResponse.class))
                      })
              })),
    @RouterOperation(
        method = RequestMethod.DELETE,
        path = "/players/{id}",
        operation =
        @Operation(
            description = "Delete player by id common router",
            operationId = "deletePlayerById",
            tags = "player",
            responses = {
                @ApiResponse(
                    responseCode = "200",
                    description = "Delete player by id response",
                    content = {
                        @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Player.class))
                    }),
                @ApiResponse(
                    responseCode = "404",
                    description = "Not found response",
                    content = {
                        @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class))
                    })
            })),
    @RouterOperation(
        method = RequestMethod.GET,
        path = "/players",
        operation =
        @Operation(
            description = "Get all players common router",
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
            })),
    @RouterOperation(
        method = RequestMethod.GET,
        path = "/players/{id}",
        operation =
        @Operation(
            description = "Get player by id common router",
            operationId = "getPlayerById",
            tags = "player",
            responses = {
                @ApiResponse(
                    responseCode = "200",
                    description = "Get player by id response",
                    content = {
                        @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Player.class))
                    }),
                @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request response",
                    content = {
                        @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class))
                    }),
                @ApiResponse(
                    responseCode = "404",
                    description = "Not found response",
                    content = {
                        @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class))
                    })
            }))
})
public @interface CommonApiInfo {}
