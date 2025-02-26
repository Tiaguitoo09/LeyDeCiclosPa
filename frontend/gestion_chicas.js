// URL de la API donde se almacenan los datos de las chicas mágicas
const API_URL = "http://localhost:8080/chicas-magicas";

// Cuando el documento se haya cargado completamente, ejecuta estas funciones
document.addEventListener("DOMContentLoaded", () => {
    obtenerChicasMagicas(); // Carga la lista de chicas mágicas al inicio
    document.getElementById("chicaForm").addEventListener("submit", guardarChicaMagica); // Maneja el formulario
    document.getElementById("btnBuscar").addEventListener("click", mostrarBusqueda); // Muestra la sección de búsqueda
    document.getElementById("btnAgregar").addEventListener("click", mostrarFormulario); // Muestra el formulario de agregar
    document.getElementById("btnVolver").addEventListener("click", volverAlMenu); // Vuelve al menú principal
    document.getElementById("btnFiltrar").addEventListener("click", filtrarPorEstado); // Filtra chicas por estado
});

// Función para obtener todas las chicas mágicas desde la API
async function obtenerChicasMagicas() {
    try {
        const response = await fetch(API_URL); // Realiza una petición GET a la API
        if (!response.ok) throw new Error("Error al obtener datos");
        const chicas = await response.json(); // Convierte la respuesta a JSON
        mostrarChicasMagicas(chicas); // Muestra los datos en la tabla
    } catch (error) {
        console.error(error);
    }
}

// Función para guardar (crear o actualizar) una chica mágica
async function guardarChicaMagica(event) {
    event.preventDefault(); // Evita que el formulario recargue la página
    
    let chicaId = document.getElementById("chicaId").value.trim(); // Obtiene el ID (si existe)

    const estadoActual = document.getElementById("estadoActual").value.trim().toLowerCase();

    // Validar que el estado sea correcto
    const estadosValidos = ["activa", "desaparecida", "rescatada"];
    if (!estadosValidos.includes(estadoActual)) {
        alert("Argumento inválido: el estado debe ser 'activa', 'desaparecida' o 'rescatada'.");
        return;
    }

    // Crea un objeto con los datos del formulario
    const chica = {
        name: document.getElementById("name").value,
        edad: parseInt(document.getElementById("edad").value, 10),
        ciudadDeOrigen: document.getElementById("ciudadDeOrigen").value,
        estadoActual: estadoActual,
        fechaDeContrato: document.getElementById("fechaDeContrato").value
    };

    // Define el método y la URL según si es creación o actualización
    const method = chicaId ? "PUT" : "POST";
    const url = chicaId ? `${API_URL}/${chicaId}` : API_URL;

    try {
        // Envía la información al servidor
        const response = await fetch(url, {
            method,
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(chica)
        });

        if (!response.ok) throw new Error("Error al guardar la chica mágica.");

        alert(chicaId ? "Chica mágica actualizada con éxito." : "Chica mágica creada con éxito.");

        obtenerChicasMagicas(); // Recarga la lista de chicas mágicas
        document.getElementById("chicaForm").reset(); // Limpia el formulario
        document.getElementById("chicaId").value = ""; // Resetea el campo de ID
    } catch (error) {
        console.error(error);
    }
}

// Función para buscar una chica mágica por ID
async function buscarPorId() {
    const id = document.getElementById("buscarId").value.trim(); // Obtiene el ID ingresado
    if (!id) return alert("Por favor, ingresa un ID válido."); // Valida el campo
    
    try {
        const response = await fetch(`${API_URL}/${id}`); // Realiza una petición GET
        if (!response.ok) throw new Error("Chica mágica no encontrada.");
        const chica = await response.json();
        mostrarChicasMagicas([chica]); // Muestra solo la chica encontrada
    } catch (error) {
        console.error(error);
        alert(error.message);
    }
}

// Función para eliminar una chica mágica por ID
async function eliminarChicaMagica(id) {
    if (!confirm("¿Estás seguro de eliminar esta chica mágica?")) return; // Confirmación antes de eliminar

    try {
        const response = await fetch(`${API_URL}/${id}`, { method: "DELETE" }); // Realiza una petición DELETE
        if (!response.ok) throw new Error("Error al eliminar.");
        obtenerChicasMagicas(); // Recarga la lista de chicas mágicas
    } catch (error) {
        console.error(error);
    }
}

// Función para mostrar la lista de chicas mágicas en la tabla
function mostrarChicasMagicas(chicas) {
    const tbody = document.querySelector("#chicasTable tbody");
    tbody.innerHTML = ""; // Limpia el contenido de la tabla

    chicas.forEach(chica => {
        const fila = document.createElement("tr");
        fila.innerHTML = `
            <td>${chica.id}</td>
            <td>${chica.name}</td>
            <td>${chica.ciudadDeOrigen}</td>
            <td>${chica.estadoActual}</td>
            <td>${chica.edad}</td>
            <td>${chica.fechaDeContrato}</td>
            <td>
                <button onclick="editarChicaMagica(${chica.id}, '${chica.name}', ${chica.edad}, '${chica.ciudadDeOrigen}', '${chica.estadoActual}', '${chica.fechaDeContrato}')">Editar</button>
                <button onclick="eliminarChicaMagica(${chica.id})">Eliminar</button>
            </td>
        `;
        tbody.appendChild(fila);
    });
}

// Función para editar una chica mágica (rellena el formulario con sus datos)
function editarChicaMagica(id, name, edad, ciudadDeOrigen, estadoActual, fechaDeContrato) {
    document.getElementById("chicaId").value = id;
    document.getElementById("name").value = name;
    document.getElementById("edad").value = edad;
    document.getElementById("ciudadDeOrigen").value = ciudadDeOrigen;
    document.getElementById("estadoActual").value = estadoActual;
    document.getElementById("fechaDeContrato").value = fechaDeContrato;
    mostrarFormulario(); // Muestra el formulario de edición
}

// Función para mostrar la sección de búsqueda
function mostrarBusqueda() {
    document.getElementById("seccionBusqueda").classList.remove("oculto");
    document.getElementById("seccionAgregar").classList.add("oculto");
}

// Función para mostrar el formulario de agregar/editar
function mostrarFormulario() {
    document.getElementById("seccionAgregar").classList.remove("oculto");
    document.getElementById("seccionBusqueda").classList.add("oculto");
}

// Función para volver al menú principal ocultando las secciones activas
function volverAlMenu() {
    document.getElementById("seccionBusqueda").classList.add("oculto");
    document.getElementById("seccionAgregar").classList.add("oculto");
    obtenerChicasMagicas();
}

// Función para filtrar chicas mágicas por estado
async function filtrarPorEstado() {
    const estado = document.getElementById("filtrarEstado").value.trim(); // Obtiene el estado seleccionado
    if (!estado) {
        obtenerChicasMagicas(); // Si no hay estado seleccionado, carga todas
        return;
    }
    
    try {
        const response = await fetch(`${API_URL}/estado/${estado}`); // Realiza una petición GET con filtro
        if (!response.ok) throw new Error("Error al filtrar");
        const chicas = await response.json();
        mostrarChicasMagicas(chicas);
    } catch (error) {
        console.error(error);
    }
}
