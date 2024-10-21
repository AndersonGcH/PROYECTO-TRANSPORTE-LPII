package com.transporte.app.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.transporte.app.entity.Destino;
import com.transporte.app.entity.DetalleVentaPasaje;
import com.transporte.app.entity.Pasajero;
import com.transporte.app.entity.VentaPasaje;
import com.transporte.app.entity.Viaje;
import com.transporte.app.services.DestinoService;
import com.transporte.app.services.PasajeroService;
import com.transporte.app.services.ViajeService;

@Controller
public class ClienteController {
	
	private final Logger log = LoggerFactory.getLogger(ClienteController.class);
    @Autowired
    private DestinoService destinoService;
    @Autowired
    private ViajeService viajeService;
    @Autowired
    private PasajeroService pasajeroService;
    
    List<DetalleVentaPasaje> detalles=new ArrayList<DetalleVentaPasaje>();

    VentaPasaje venta = new VentaPasaje();
    @GetMapping("/cliente")
    public String cliente(Model model) {
        List<Destino> destinos = destinoService.getAllDestinos();
        model.addAttribute("destinos", destinos);
        return "cliente/home";
	}

    @GetMapping("/destinohome/{idDestino}")
    public String listarViajesPorDestino(@PathVariable Integer idDestino, Model model) {
        List<Viaje> viajes = viajeService.findViajesByDestinoId(idDestino);
        Destino destino = destinoService.findById(idDestino); // Asegúrate de tener un servicio para obtener el destino por ID
        model.addAttribute("viajes", viajes);
        model.addAttribute("nombreDestino", destino.getNombre());
        return "cliente/destinohome"; 
    }
    
    @GetMapping("/viaje/{idViaje}") // Cambia {id} por {idViaje}
    public String infoviaje(@PathVariable Integer idViaje, Model model) {
        log.info("Id Viaje enviado como parametro: {}", idViaje); // Usa {} para el log
        Viaje viaje = viajeService.findViajeById(idViaje); // No es necesario crear un nuevo objeto Viaje
        if (viaje != null) { // Verifica si el viaje existe
            model.addAttribute("viaje", viaje);
            return "cliente/infoviaje";
        } else {
            // Manejar el caso donde el viaje no se encuentra
            return "error"; // Redirige a una página de error o muestra un mensaje apropiado
        }
    }
    
    @PostMapping("/infoviaje")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
        DetalleVentaPasaje detalleVenta = new DetalleVentaPasaje();
        double sumaTotal = 0;

        // Obtener el viaje opcionalmente
        Optional<Viaje> optionalViaje = Optional.ofNullable(viajeService.findViajeById(id));

        // Verificar si el viaje está presente
        if (optionalViaje.isPresent()) {
            Viaje viaje = optionalViaje.get(); // Obtener el viaje

            log.info("Viaje añadido: {}", viaje); // Aquí registras el objeto completo
            log.info("Cantidad: {}", cantidad);

            // Verificar si el viaje ya está en el carrito
            boolean viajeExistente = false;
            for (DetalleVentaPasaje detalle : detalles) {
                if (detalle.getViaje().getIdViaje().equals(viaje.getIdViaje())) {
                    detalle.setCantidad(detalle.getCantidad() + cantidad); // Aumentar la cantidad
                    detalle.setTotal(detalle.getPrecio() * detalle.getCantidad());
                    viajeExistente = true;
                    break;
                }
            }

            // Si el viaje no estaba en el carrito, agregarlo como nuevo
            if (!viajeExistente) {
                detalleVenta.setViaje(viaje);
                detalleVenta.setCantidad(cantidad);
                detalleVenta.setPrecio(viaje.getPrecio());
                detalleVenta.setTotal(detalleVenta.getPrecio() * cantidad); // Multiplicación directa
                detalles.add(detalleVenta);
            }

            // Actualizar sumaTotal
            sumaTotal = detalles.stream()
                    .mapToDouble(dt -> dt.getTotal() != 0.0 ? dt.getTotal() : 0.0) // Asegúrate de que getTotal() devuelve un double
                    .sum();

            // Asignar el total a la venta
            venta.setTotal(sumaTotal);
        } else {
            log.error("No se encontró el viaje con ID: {}", id);
            // Manejo de error: podrías redirigir a una página de error o mostrar un mensaje
        }

        model.addAttribute("cart", detalles);
        model.addAttribute("venta", venta);
        return "cliente/carrito";
    }
    
    @GetMapping("/delete/cart/{id}")
    public String deleteViaje(@PathVariable Integer id, Model model) {
        // Verifica que detalles esté inicializado y no sea nulo
        if (detalles != null) {
            // Filtra los detalles, excluyendo el que se desea eliminar
            List<DetalleVentaPasaje> detalleventaPasaje = detalles.stream()
                    .filter(detalleVenta -> !detalleVenta.getViaje().getIdViaje().equals(id))
                    .collect(Collectors.toList());

            // Actualiza la lista de detalles
            detalles = detalleventaPasaje;

            // Calcula el total
            double sumaTotal = detalles.stream()
                    .mapToDouble(DetalleVentaPasaje::getTotal)
                    .sum();

            // Asigna el total a la venta
            if (venta != null) {
                venta.setTotal(sumaTotal);
            } else {
                // Si 'venta' es null, crea una nueva instancia o maneja el caso
                venta = new VentaPasaje();
                venta.setTotal(sumaTotal);
            }

            // Agrega los detalles y la venta al modelo
            model.addAttribute("cart", detalles);
            model.addAttribute("venta", venta);
        } else {
            // Manejo si detalles es nulo (opcional)
            model.addAttribute("cart", Collections.emptyList());
        }

        // Redirige a la vista del carrito
        return "cliente/carrito"; // Asegúrate de que esta vista esté correctamente configurada
    }

 

    
    @GetMapping("/order")
    public String order(Model model) {
    	Pasajero pasajero = pasajeroService.findById(1);

        model.addAttribute("cart", detalles);
        model.addAttribute("venta", venta);
        model.addAttribute("pasajero",pasajero);
        return "cliente/resumenorden";
    }
    
    


}