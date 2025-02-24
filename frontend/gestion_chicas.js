document.addEventListener("DOMContentLoaded", function () {
    obtenerChicasMagicas();
});

async function obtenerChicasMagicas() {
    try {
        const response = await fetch("http://localhost:8000/chicas-magicas/Chicas");
        if (!response.ok) throw new Error("Error en la petición");

        const chicas = await response.json();
        console.log("Chicas mágicas:", chicas); // Verifica los datos en la consola

        const listaChicas = document.getElementById("listaChicas");
        listaChicas.innerHTML = ""; // Limpiar la lista antes de agregar nuevos elementos

        chicas.forEach(chica => {
            const li = document.createElement("li");
            li.classList.add("chica-card");

            li.innerHTML = `
                <div class="chica-imagen">
                    <img src="imgs/default.jpg" alt="${chica.name}">
                </div>
                <div class="chica-info">
                    <h2 class="chica-nombre">${chica.name}</h2>
                    <p class="chica-ciudad"><strong>Ciudad:</strong> ${chica.ciudadDeOrigen}</p>
                    <p class="chica-edad"><strong>Edad:</strong> ${chica.edad} años</p>
                    <p class="chica-estado"><strong>Estado:</strong> ${chica.estadoActual}</p>
                    <p class="chica-fecha"><strong>Fecha de contrato:</strong> ${chica.fechaDeContrato}</p>
                </div>
            `;
            listaChicas.appendChild(li);
        });

    } catch (error) {
        console.error("Error obteniendo datos:", error);
    }
}
