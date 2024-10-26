document.addEventListener('DOMContentLoaded', () => {
    const carritoItems = document.querySelectorAll('.carrito__item');
    const totalProductosElem = document.querySelector('.total__productos');
    const subtotalElem = document.querySelector('.subtotal');
    const totalElem = document.querySelector('.total');

    carritoItems.forEach(item => {
        const menosBtn = item.querySelector('.menos');
        const masBtn = item.querySelector('.mas');
        const cantidadInput = item.querySelector('.cantidad');
        const precioElem = item.querySelector('.carrito__precio');
        const totalElem = item.querySelector('.carrito__total');

        const precio = parseFloat(precioElem.textContent);

        function actualizarTotal() {
            const cantidad = parseInt(cantidadInput.value);
            const total = precio * cantidad;
            totalElem.textContent = total.toFixed(2);
            actualizarResumen();
        }

        menosBtn.addEventListener('click', () => {
            let cantidad = parseInt(cantidadInput.value);
            if (cantidad > 1) {
                cantidadInput.value = --cantidad;
                actualizarTotal();
            }
        });

        masBtn.addEventListener('click', () => {
            let cantidad = parseInt(cantidadInput.value);
            cantidadInput.value = ++cantidad;
            actualizarTotal();
        });

        actualizarTotal();
    });

    function actualizarResumen() {
        let totalProductos = 0;
        let subtotal = 0;

        carritoItems.forEach(item => {
            const cantidad = parseInt(item.querySelector('.cantidad').value);
            const total = parseFloat(item.querySelector('.carrito__total').textContent);
            totalProductos += cantidad;
            subtotal += total;
        });

        totalProductosElem.textContent = totalProductos;
        subtotalElem.textContent = subtotal.toFixed(2);
        totalElem.textContent = (subtotal).toFixed(2);
    }
});