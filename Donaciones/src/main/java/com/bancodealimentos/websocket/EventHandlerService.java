package com.bancodealimentos.websocket;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class EventHandlerService implements SocketIOService.SocketEventListener {
    
    @Override
    public void onEventReceived(String eventType, JSONObject data) {
        if (data == null) {
            System.out.println("⚠️ Evento sin datos: " + eventType);
            return;
        }

        switch (eventType) {
            // Eventos de inventario
            case "nuevo-inventario":
                System.out.println("📦 Nuevo inventario: " + data);
                // Aquí puedes actualizar la UI o la lógica de negocio
                break;
                
            case "inventario-actualizado":
                System.out.println("🔄 Inventario actualizado: " + data);
                break;
                
            case "inventario-eliminado":
                System.out.println("🗑️ Inventario eliminado: " + data);
                break;
                
            // Eventos de donantes
            case "nuevo-donante":
                System.out.println("👤 Nuevo donante: " + data);
                break;
                
            case "donante-eliminado":
                System.out.println("👤❌ Donante eliminado: " + data);
                break;
                
            // Eventos de receptores
            case "nuevo-receptor":
                System.out.println("🏠 Nuevo receptor: " + data);
                break;
                
            case "receptor-eliminado":
                System.out.println("🏠❌ Receptor eliminado: " + data);
                break;
                
            // Eventos de organizaciones
            case "organizacion-actualizada":
                System.out.println("🏢 Organización actualizada: " + data);
                break;
                
            case "organizacion-eliminada":
                System.out.println("🏢❌ Organización eliminada: " + data);
                break;
                
            // Eventos de solicitudes
            case "nueva-solicitude":
                System.out.println("📝 Nueva solicitud: " + data);
                break;
                
            case "solicitud-actualizada":
                System.out.println("📝✏️ Solicitud actualizada: " + data);
                break;
                
            case "solicitud-eliminada":
                System.out.println("📝❌ Solicitud eliminada: " + data);
                break;
                
            default:
                System.out.println("📨 Evento no manejado (" + eventType + "): " + data);
        }
    }
}