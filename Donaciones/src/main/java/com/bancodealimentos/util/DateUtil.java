package com.bancodealimentos.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    
    /**
     * Parsea una cadena de fecha en formato ISO (yyyy-MM-dd) a LocalDate
     * @param fechaStr La cadena de fecha a parsear
     * @return El objeto LocalDate parseado
     * @throws DateTimeParseException Si la cadena no está en el formato correcto
     * @throws IllegalArgumentException Si la cadena es nula o vacía
     */
    public static LocalDate parseFecha(String fechaStr) {
        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha no puede estar vacía");
        }
        return LocalDate.parse(fechaStr, DATE_FORMATTER);
    }
    
    /**
     * Formatea un LocalDate a una cadena en formato ISO (yyyy-MM-dd)
     * @param fecha La fecha a formatear
     * @return La cadena formateada
     * @throws IllegalArgumentException Si la fecha es nula
     */
    public static String formatFecha(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula");
        }
        return fecha.format(DATE_FORMATTER);
    }
}
