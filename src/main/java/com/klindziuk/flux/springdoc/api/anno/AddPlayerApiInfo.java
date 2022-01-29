/*
 * Copyright (c) 2022. Dandelion tutorials
 */

package com.klindziuk.flux.springdoc.api.anno;

import com.klindziuk.flux.springdoc.model.ErrorResponse;
import com.klindziuk.flux.springdoc.model.Player;
import io.swagger.v3.oas.annotations.Operation;
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
      operation =
          @Operation(
              description = "Add player",
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
                      description = "Add player response",
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
              }))
})
public @interface AddPlayerApiInfo {}
