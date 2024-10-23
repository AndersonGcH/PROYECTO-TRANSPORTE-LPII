package com.transporte.app.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import com.transporte.app.entity.Usuario;
import com.transporte.app.entity.VentaPasaje;
import com.transporte.app.entity.Viaje;
import com.transporte.app.services.DestinoService;
import com.transporte.app.services.DetalleVentaPasajeService;
import com.transporte.app.services.UsuarioService;
import com.transporte.app.services.VentaPasajeService;
import com.transporte.app.services.ViajeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ClienteController {

    private final Logger log = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private DestinoService destinoService;

    @Autowired
    private ViajeService viajeService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private VentaPasajeService ventaPasajeService;
    
    @SuppressWarnings("unused")
    @Autowired
    private DetalleVentaPasajeService detalleVentaPasajeService;

    // Lista de detalles de compra
    List<DetalleVentaPasaje> detalles = new ArrayList<>();
    VentaPasaje venta = new VentaPasaje();

    // 1. Mostrar destinos sin autenticación (acceso público)
   

    // 2. Mostrar viajes por destino (acceso público)
    @GetMapping("/destinohome/{idDestino}")
    public String listarViajesPorDestino(@PathVariable Integer idDestino, Model model) {
        List<Viaje> viajes = viajeService.findViajesByDestinoId(idDestino);
        Destino destino = destinoService.findById(idDestino);
        model.addAttribute("viajes", viajes);
        model.addAttribute("nombreDestino", destino.getNombre());
        return "cliente/destinohome"; // Vista con los viajes del destino
    }

    // 3. Detalles de un viaje (acceso público)
    @GetMapping("/viaje/{idViaje}")
    public String infoviaje(@PathVariable Integer idViaje, Model model) {
        log.info("Id Viaje enviado como parametro: {}", idViaje);
        Viaje viaje = viajeService.findViajeById(idViaje);
        if (viaje != null) {
            model.addAttribute("viaje", viaje);
            return "cliente/infoviaje"; // Vista con la información del viaje
        } else {
            return "error"; // En caso de que no se encuentre el viaje
        }
    }

    // 4. Agregar viaje al carrito (requiere autenticación)
    @PostMapping("/infoviaje")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model, HttpSession session) {
        Long idUsuario = (Long) session.getAttribute("idusuario");

        // Verificar si el usuario está autenticado
        if (idUsuario == null) {
            return "redirect:/login"; // Redirigir a login si no está autenticado
        }

        DetalleVentaPasaje detalleVenta = new DetalleVentaPasaje();
        double sumaTotal = 0;

        Optional<Viaje> optionalViaje = Optional.ofNullable(viajeService.findViajeById(id));
        if (optionalViaje.isPresent()) {
            Viaje viaje = optionalViaje.get();
            log.info("Viaje añadido: {}", viaje);
            log.info("Cantidad: {}", cantidad);

            boolean viajeExistente = false;
            for (DetalleVentaPasaje detalle : detalles) {
                if (detalle.getViaje().getIdViaje().equals(viaje.getIdViaje())) {
                    detalle.setCantidad(detalle.getCantidad() + cantidad);
                    detalle.setTotal(detalle.getPrecio() * detalle.getCantidad());
                    viajeExistente = true;
                    break;
                }
            }

            if (!viajeExistente) {
                detalleVenta.setViaje(viaje);
                detalleVenta.setCantidad(cantidad);
                detalleVenta.setPrecio(viaje.getPrecio());
                detalleVenta.setTotal(detalleVenta.getPrecio() * cantidad);
                detalles.add(detalleVenta);
            }

            sumaTotal = detalles.stream()
                    .mapToDouble(dt -> dt.getTotal() != 0.0 ? dt.getTotal() : 0.0)
                    .sum();

            venta.setTotal(sumaTotal);
        } else {
            log.error("No se encontró el viaje con ID: {}", id);
        }

        model.addAttribute("cart", detalles);
        model.addAttribute("venta", venta);
        return "cliente/carrito"; // Mostrar el carrito de compras
    }
 
    @GetMapping("/getCart")
    public String getCart(Model model) {
    	  model.addAttribute("cart", detalles);
          model.addAttribute("venta", venta);
        return "cliente/carrito";
    }
    
    // 5. Finalizar compra (requiere autenticación)
    @GetMapping("/order")
    public String order(Model model, HttpSession session) {
        Long idUsuario = (Long) session.getAttribute("idusuario");

        // Verificar si el usuario está autenticado
        if (idUsuario == null) {
            return "redirect:/login"; // Redirigir a login si no está autenticado
        }

        Usuario pasajero = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString()));
        model.addAttribute("cart", detalles);
        model.addAttribute("venta", venta);
        model.addAttribute("pasajero", pasajero);
        return "cliente/resumenorden"; // Mostrar resumen de la orden
    }

    // 6. Guardar venta (requiere autenticación)
    @GetMapping("/saveVenta")
    public String saveVenta(HttpSession session) {
        Long idUsuario = (Long) session.getAttribute("idusuario");

        // Verificar si el usuario está autenticado
        if (idUsuario == null) {
            return "redirect:/login"; // Redirigir a login si no está autenticado
        }

        Date fechaCreacion = new Date();
        venta.setUsuario(usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())));
        venta.setFechaVenta(fechaCreacion);
        venta.setNumero(ventaPasajeService.generarNumeroOrden());

        double total = 0;
        for (DetalleVentaPasaje dt : detalles) {
            if (dt.getPrecio() != 0 && dt.getCantidad() != 0) {
                total += dt.getPrecio() * dt.getCantidad();
                dt.setVentaPasaje(venta);
            }
        }
        venta.setTotal(total);
        venta.setDetalles(detalles);

        ventaPasajeService.save(venta);
        venta = new VentaPasaje();
        detalles.clear();

        return "redirect:/";
    }
    
    @GetMapping("/delete/cart/{id}")
    public String deleteViaje(@PathVariable Integer id, Model model) {
        // Verifica si la lista de detalles no es nula
        if (detalles != null) {
            // Filtra y excluye el viaje que se desea eliminar
            detalles = detalles.stream()
                    .filter(detalleVenta -> !detalleVenta.getViaje().getIdViaje().equals(id))
                    .collect(Collectors.toList());

            // Calcula el total de los detalles restantes
            double sumaTotal = detalles.stream()
                    .mapToDouble(DetalleVentaPasaje::getTotal)
                    .sum();

            // Actualiza el total de la venta
            if (venta == null) {
                venta = new VentaPasaje();
            }
            venta.setTotal(sumaTotal);

            // Agrega los detalles y el total actualizado al modelo
            model.addAttribute("cart", detalles);
            model.addAttribute("venta", venta);
        } else {
            // Si la lista de detalles es nula, muestra un carrito vacío
            model.addAttribute("cart", Collections.emptyList());
        }

        // Redirige a la vista del carrito
        return "cliente/carrito"; // Asegúrate de que esta vista esté configurada correctamente
    }

    // 7. Ver compras del usuario (requiere autenticación)
    @GetMapping("/compras")
    public String obtenerCompras(Model model, HttpSession session) {
        Long idUsuario = (Long) session.getAttribute("idusuario");

        // Verificar si el usuario está autenticado
        if (idUsuario == null) {
            return "redirect:/login"; // Redirigir a login si no está autenticado
        }

        return "cliente/compras"; // Mostrar compras del usuario
    }
}