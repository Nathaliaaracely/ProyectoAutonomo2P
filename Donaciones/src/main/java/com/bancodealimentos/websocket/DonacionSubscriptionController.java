package com.bancodealimentos.websocket;

import com.bancodealimentos.model.Donaciones;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Controlador para manejar suscripciones WebSocket de donaciones.
 * Permite a los clientes suscribirse a actualizaciones en tiempo real de nuevas donaciones.
 */
@Controller
public class DonacionSubscriptionController {
    private static final Logger logger = LoggerFactory.getLogger(DonacionSubscriptionController.class);
    
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public DonacionSubscriptionController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
        logger.info("DonacionSubscriptionController inicializado");
    }
    
    /**
     * Endpoint para recibir nuevas donaciones y notificar a los suscriptores
     */
    @MessageMapping("/donaciones/nueva")
    @SendTo("/topic/donaciones")
    public Donaciones nuevaDonacion(Donaciones donacion) {
        logger.info("Nueva donación recibida: {}", donacion.getId());
        return donacion;
    }
    
    /**
     * Método para notificar sobre una nueva donación
     */
    public void notificarNuevaDonacion(Donaciones donacion) {
        if (donacion != null) {
            logger.info("Notificando nueva donación: {}", donacion.getId());
            messagingTemplate.convertAndSend("/topic/donaciones", donacion);
        }
    }
}