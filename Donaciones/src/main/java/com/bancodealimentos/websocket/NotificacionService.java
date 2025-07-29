// NotificacionService.java
package com.bancodealimentos.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificacionService {

    private final SocketIOService socketIOService;
    private final ObjectMapper objectMapper;

    @Autowired
    public NotificacionService(SocketIOService socketIOService, ObjectMapper objectMapper) {
        this.socketIOService = socketIOService;
        this.objectMapper = objectMapper;
    }

    public void notificarDonanteCreado(Object donante) {
        enviarNotificacion("nuevo-donante", donante, "Nuevo donante registrado");
    }

    public void notificarDonanteEliminado(Long id) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        enviarNotificacion("donante-eliminado", data, "Donante eliminado");
    }

    public void notificarReceptorCreado(Object receptor) {
        enviarNotificacion("nuevo-receptor", receptor, "Nuevo receptor registrado");
    }

    public void notificarReceptorEliminado(Long id) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        enviarNotificacion("receptor-eliminado", data, "Receptor eliminado");
    }

    private void enviarNotificacion(String tipoEvento, Object datos, String mensaje) {
        try {
            // Crear el objeto de datos
            Map<String, Object> payload = new HashMap<>();
            payload.put("tipo", tipoEvento);  // ej: "nuevo-donante"
            payload.put("datos", datos);      // el objeto completo
            payload.put("mensaje", mensaje);  // mensaje descriptivo
            payload.put("timestamp", System.currentTimeMillis());
            
            System.out.println("📝 Enviando notificación: " + payload);
            
            // Enviar el evento "notificacion" con el payload completo
            socketIOService.sendEvent("notificacion", payload);
            
        } catch (Exception e) {
            System.err.println("❌ Error al enviar notificación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}