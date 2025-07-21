document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('donacionForm');
    const tabla = document.getElementById('cuerpoTabla');

    // Cargar donaciones al iniciar
    cargarDonaciones();

    // Manejar el envío del formulario
    form.addEventListener('submit', function(e) {
        e.preventDefault();
        
        const donacion = {
            monto: parseFloat(document.getElementById('monto').value),
            donante: document.getElementById('donante').value,
            fecha: document.getElementById('fecha').value
        };

        // Aquí iría la llamada a tu API para guardar la donación
        // Por ahora solo lo agregamos a la tabla
        agregarDonacionATabla(donacion);
        
        // Limpiar el formulario
        form.reset();
    });

    function cargarDonaciones() {
        // Aquí iría la llamada a tu API para obtener las donaciones
        // Por ahora usamos datos de ejemplo
        const donacionesEjemplo = [
            { id: 1, monto: 100.50, donante: "Ejemplo S.A.", fecha: "2023-05-15" },
            { id: 2, monto: 200.75, donante: "Fundación Ayuda", fecha: "2023-05-16" }
        ];

        donacionesEjemplo.forEach(donacion => {
            agregarDonacionATabla(donacion);
        });
    }

    function agregarDonacionATabla(donacion) {
        const fila = document.createElement('tr');
        
        fila.innerHTML = `
            <td>${donacion.id || ''}</td>
            <td>$${donacion.monto.toFixed(2)}</td>
            <td>${donacion.donante}</td>
            <td>${new Date(donacion.fecha).toLocaleDateString()}</td>
        `;
        
        tabla.appendChild(fila);
    }
});