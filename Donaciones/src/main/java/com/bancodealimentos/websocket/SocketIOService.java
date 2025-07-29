package com.bancodealimentos.websocket;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SocketIOService {
    @Value("${socketio.host:localhost}")
    private String host;

    @Value("${socketio.port:4005}")
    private Integer port;

    private Socket socket;
    private boolean isConnected = false;
    private final List<SocketEventListener> listeners = new CopyOnWriteArrayList<>();

    public interface SocketEventListener {
        void onEventReceived(String event, JSONObject data);
    }

    @PostConstruct
    public void init() {
        try {
            String serverUrl = "http://" + host + ":" + port;
            IO.Options options = IO.Options.builder()
                    .setForceNew(true)
                    .setReconnection(true)
                    .setReconnectionAttempts(Integer.MAX_VALUE)
                    .setReconnectionDelay(1000)
                    .setReconnectionDelayMax(5000)
                    .setRandomizationFactor(0.5)
                    .build();

            socket = IO.socket(serverUrl, options);

            // Manejador de conexión
            socket.on(Socket.EVENT_CONNECT, args -> {
                isConnected = true;
                System.out.println("✅ Conectado al servidor Socket.IO en " + serverUrl);
                
                // Unirse a la sala 'admin' usando el formato que espera el servidor
                JSONObject joinData = new JSONObject()
                    .put("event", "join")
                    .put("data", new JSONObject().put("room", "admin"));
                
                socket.emit("message", joinData);
                System.out.println("🚀 Solicitando unión a la sala 'admin'");
                
                // Escuchar confirmación de unión a la sala
                socket.on("joined_room", data -> {
                    System.out.println("✅ Confirmación de unión a sala: " + data);
                });
                
                // Suscribirse a eventos específicos de la sala admin
                socket.on("admin:notification", this::handleEvent);
                socket.on("admin:user_created", this::handleEvent);
                socket.on("admin:user_updated", this::handleEvent);
                socket.on("admin:user_deleted", this::handleEvent);
                
                // Suscribirse a todos los eventos
                socket.on("message", this::handleEvent);
                socket.on("notificacion", this::handleEvent);
                socket.on("notification", this::handleEvent);
                socket.on("nuevo-donante", this::handleEvent);
                socket.on("donante-eliminado", this::handleEvent);
                socket.on("nuevo-receptor", this::handleEvent);
                socket.on("receptor-eliminado", this::handleEvent);
                socket.on("nuevo-inventario", this::handleEvent);
                socket.on("inventario-actualizado", this::handleEvent);
                socket.on("inventario-eliminado", this::handleEvent);
                socket.on("organizacion-actualizada", this::handleEvent);
                socket.on("organizacion-eliminada", this::handleEvent);
                socket.on("nueva-solicitud", this::handleEvent);
                socket.on("solicitude_actualizada", this::handleEvent);
                socket.on("solicitude_eliminada", this::handleEvent);
                socket.on("solicitude-actualizada", this::handleEvent);
                socket.on("solicitude-eliminada", this::handleEvent);
                socket.on("nueva-organizacion", this::handleEvent);
                socket.on("nuevo_usuario", this::handleEvent);
                socket.on("usuario-actualizado", this::handleEvent);
                socket.on("usuario-eliminado", this::handleEvent);
                socket.on("nuevo_inventario", this::handleEvent);
                socket.on("inventario-actualizado", this::handleEvent);
                socket.on("inventario-eliminado", this::handleEvent);
                socket.on("nuevo_evento", this::handleEvent);
                socket.on("evento-actualizado", this::handleEvent);
                socket.on("evento-eliminado", this::handleEvent);
                socket.on("nuevo_voluntario", this::handleEvent);
                socket.on("voluntario-actualizado", this::handleEvent);
                socket.on("voluntario-eliminado", this::handleEvent);

                
                // Suscribirse a errores específicos
                socket.on("error", error -> {
                    System.err.println("❌ Error en el socket: " + error);
                });
                
                System.out.println("👂 Escuchando todos los eventos...");
            });

            socket.on(Socket.EVENT_DISCONNECT, args -> {
                isConnected = false;
                System.out.println("❌ Desconectado del servidor Socket.IO");
            });

            socket.on(Socket.EVENT_CONNECT_ERROR, args -> {
                System.err.println("Error de conexión: " + Arrays.toString(args));
            });

            System.out.println("🔄 Conectando al servidor Socket.IO...");
            socket.connect();

        } catch (Exception e) {
            System.err.println("❌ Error al conectar con Socket.IO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Maneja todos los eventos recibidos del servidor Socket.IO
     */
    private void handleEvent(Object... args) {
        String eventType = null;
        JSONObject eventData = null;
        
        try {
            // Log raw arguments for debugging
            System.out.println("\n📥 Raw event data: " + Arrays.toString(args));
            
            // Determinar el tipo de evento
            if (args.length > 0) {
                // Si el primer argumento es un String, es el nombre del evento
                if (args[0] instanceof String) {
                    eventType = (String) args[0];
                    System.out.println("🔍 Event type from string: " + eventType);
                    
                    if (args.length > 1) {
                        if (args[1] instanceof JSONObject) {
                            eventData = (JSONObject) args[1];
                        } else {
                            // Si no es un JSONObject, convertirlo a uno
                            eventData = new JSONObject().put("data", args[1]);
                            System.out.println("🔍 Converted non-JSON data to JSON object");
                        }
                    }
                } 
                // Si es un JSONObject, extraer el tipo de evento
                else if (args[0] instanceof JSONObject) {
                    JSONObject data = (JSONObject) args[0];
                    eventType = data.optString("event", data.optString("type", "unknown"));
                    System.out.println("🔍 Event type from JSON: " + eventType);
                    
                    eventData = data.optJSONObject("data");
                    if (eventData == null) {
                        eventData = data;
                        System.out.println("🔍 Using full data as event data");
                    }
                }
            }
            
            if (eventType == null) {
                eventType = "unknown";
                System.out.println("⚠️ Could not determine event type, using 'unknown'");
            }
            
            if (eventData == null) {
                eventData = new JSONObject();
                if (args.length > 0) {
                    eventData.put("rawData", args[0]);
                }
                System.out.println("ℹ️ No event data, created empty JSON object");
            }
            
            System.out.println("\n📥 Evento recibido");
            System.out.println("🔔 Tipo: " + eventType);
            System.out.println("📦 Datos: " + eventData);
            
            // Notificar a todos los listeners
            if (!listeners.isEmpty()) {
                System.out.println("👥 Notificando a " + listeners.size() + " listeners");
                for (SocketEventListener listener : new ArrayList<>(listeners)) {
                    try {
                        System.out.println("  ➡️ Enviando a listener: " + listener.getClass().getSimpleName());
                        listener.onEventReceived(eventType, eventData);
                    } catch (Exception e) {
                        System.err.println("❌ Error en el manejador de eventos (" + 
                            listener.getClass().getSimpleName() + "): " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("⚠️ No hay listeners registrados para manejar el evento");
            }
        } catch (Exception e) {
            System.err.println("❌ Error al procesar evento: " + e.getMessage());
            e.printStackTrace();
            System.out.println("Datos del evento: " + Arrays.toString(args));
        }
    }

    public void addEventListener(SocketEventListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
            System.out.println("👂 Listener registrado: " + listener.getClass().getSimpleName());
        }
    }

    public void removeEventListener(SocketEventListener listener) {
        listeners.remove(listener);
    }

    @PreDestroy
    public void stop() {
        if (socket != null) {
            socket.off();
            socket.disconnect();
            isConnected = false;
            System.out.println("🔌 Conexión Socket.IO cerrada");
        }
    }

    public void sendEvent(String event, Object data) {
        if (!isConnected) {
            System.err.println("⚠️ No conectado al servidor Socket.IO");
            return;
        }

        try {
            // Crear el mensaje con la estructura que espera el servidor
            Map<String, Object> message = new HashMap<>();
            message.put("event", event);
            message.put("data", data);
            message.put("timestamp", System.currentTimeMillis());

            System.out.println("📤 Enviando mensaje: " + new JSONObject(message).toString());
            
            // Enviar el mensaje
            socket.emit("message", message);
                
        } catch (Exception e) {
            System.err.println("❌ Error al enviar evento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return isConnected;
    }
}