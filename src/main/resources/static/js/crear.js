document.getElementById("categoria").addEventListener("change", function () {
    const categoria = this.value;
    
    // Detalles según la categoría
        const detalles = {
            procesador: [
                "Núcleos", "Hilos", "Frecuencia base", "Frecuencia turbo máxima", "Cache",
                "Tipo de memoria", "TDP/TDP predeterminado", "Memoria máxima",
                "Gráficos integrados", "Overclocking", "Versión de PCI Express"
            ],
            ram: [
                "Capacidad", "Velocidad", "Tipo de memoria", "Latencia CAS",
                "Voltaje", "Latencia SPD", "Factor de forma", "Formato", "Temperatura de operación",
                "Frecuencia máxima", "RGB"
            ],
            ssd: [
                "Capacidad", "Interfaz", "Velocidad de lectura", "Velocidad de escritura",
                "Tipo de memoria NAND", "Durabilidad (TBW)", "Consumo de energía",
                "Factor de forma", "Encriptación", "MTBF", "Garantía"
            ],
            hhd:[""],
            tmadre:[""],
            tgrafica:[""],
            disipador:[""],
            fpoder:[""],
            case:[""]
            // Agrega más categorías y detalles según sea necesario
        };

    // Selecciona todos los labels correspondientes a los detalles
    const labels = document.querySelectorAll(".formularios__detalleContainer .formularios__label");

    // Limpia el texto de los labels antes de llenarlos
    labels.forEach((label, index) => {
        if (detalles[categoria] && detalles[categoria][index]) {
            label.textContent = detalles[categoria][index];
        } else {
            label.textContent = "Detalle " + (index + 1);
        }
    });
});
