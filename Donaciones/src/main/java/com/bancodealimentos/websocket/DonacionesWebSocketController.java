package com.bancodealimentos.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class DonacionesWebSocketController {

    @MessageMapping("/nueva-donacion")
    @SendTo("/topic/donaciones")
    public String notificarNuevaDonacion(String mensaje) {
        return mensaje;
    }
}
