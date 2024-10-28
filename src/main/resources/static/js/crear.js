document.getElementById("categoria").addEventListener("change", function () {
    const categoria = this.value;
    
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
    hhd: [
        "Capacidad", "Velocidad de rotación", "Interfaz", "Caché",
        "Formato", "Tamaño físico", "Consumo de energía", "Durabilidad",
        "Tiempo medio entre fallos (MTBF)", "Tecnología de grabación", "Ruido"
    ],
    tmadre: [
        "Socket", "Chipset", "Factores de forma", "Número de slots de RAM",
        "Número de puertos USB", "Puertos SATA", "Compatibilidad con PCIe",
        "Audio integrado", "Red integrada", "Capacidad de overclocking", "BIOS/UEFI"
    ],
    tgrafica: [
        "VRAM", "Tipo de memoria", "Frecuencia del núcleo", "Velocidad de la memoria",
        "Núcleos CUDA/Stream processors", "Interfaz", "TDP", "Salidas de video",
        "Soporte para ray tracing", "Overclocking", "Dimensiones"
    ],
    disipador: [
        "Tipo (aire/líquido)", "Dimensiones", "Compatibilidad", "Rendimiento térmico",
        "Nivel de ruido", "Ventiladores incluidos", "Material del radiador",
        "Base de contacto", "RGB", "Facilidad de instalación", "Peso"
    ],
    fpoder: [
        "Potencia total (W)", "Certificación (80 Plus)", "Número de rieles",
        "Conectores disponibles", "Tipo de ventilador", "Tamaño físico",
        "Protecciones (OVP, OCP, SCP)", "Modularidad", "Eficiencia",
        "Ruido", "MTBF"
    ],
    case: [
        "Tipo de caja (ATX, microATX, etc.)", "Dimensiones", "Soporte para refrigeración",
        "Espacio para GPU", "Número de bahías para discos", "Materiales",
        "Ventiladores incluidos", "Puertos frontales", "Gestión de cables",
        "Filtro de polvo", "RGB"
    ]
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
