/*
 * Copyright (c) 2022. Dandelion tutorials
 */

package com.klindziuk.flux.springdoc.api.anno;

import com.klindziuk.flux.springdoc.model.Health;
import io.swagger.v3.oas.annotations.Operation;
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
              description = "Health Check",
              operationId = "healthCheck",
              tags = "health",
              responses =
                  @ApiResponse(
                      responseCode = "200",
                      description = "Health Check response",
                      content = {
                        @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Health.class))
                      })))
})
public @interface HealthApiInfo {}
