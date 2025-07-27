package com.bancodealimentos.controller;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

@RestController
@RestControllerAdvice
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public ResponseEntity<Map<String, Object>> error(ServerRequest request) {
        Map<String, Object> errorAttributes = getErrorAttributes(request);
        HttpStatus status = getStatus(errorAttributes);
        
        // Personalizar la respuesta de error
        Map<String, Object> response = Map.of(
            "status", status.value(),
            "error", errorAttributes.getOrDefault("error", "Error desconocido"),
            "message", errorAttributes.getOrDefault("message", "Ocurrió un error inesperado"),
            "path", errorAttributes.getOrDefault("path", ""),
            "timestamp", errorAttributes.getOrDefault("timestamp", "")
        );
        
        return new ResponseEntity<>(response, status);
    }

    private HttpStatus getStatus(Map<String, Object> errorAttributes) {
        int statusCode = (int) errorAttributes.getOrDefault("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    private Map<String, Object> getErrorAttributes(ServerRequest request) {
        return new DefaultErrorAttributes().getErrorAttributes(
            request,
            ErrorAttributeOptions.of(
                ErrorAttributeOptions.Include.EXCEPTION,
                ErrorAttributeOptions.Include.MESSAGE,
                ErrorAttributeOptions.Include.BINDING_ERRORS,
                ErrorAttributeOptions.Include.STACK_TRACE
            )
        );
    }

    @RequestMapping("/fallback")
    public ResponseEntity<Map<String, Object>> fallback() {
        return ResponseEntity
            .status(HttpStatus.SERVICE_UNAVAILABLE)
            .body(Map.of(
                "status", HttpStatus.SERVICE_UNAVAILABLE.value(),
                "error", "Servicio no disponible",
                "message", "El servicio solicitado no está disponible en este momento. Por favor, intente nuevamente más tarde.",
                "timestamp", System.currentTimeMillis()
            ));
    }

    public String getErrorPath() {
        return ERROR_PATH;
    }
}
