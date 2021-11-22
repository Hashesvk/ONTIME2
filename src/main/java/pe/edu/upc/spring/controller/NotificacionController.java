package pe.edu.upc.spring.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Evento;
import pe.edu.upc.spring.model.Notificacion;
import pe.edu.upc.spring.service.IEventoService;
import pe.edu.upc.spring.service.INotificacionService;

@Controller
@RequestMapping("/notificacion")
public class NotificacionController {

	@Autowired
	private INotificacionService nService;
	
	@Autowired
	private IEventoService eService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Notificacion objNotificacion, BindingResult binRes, Model model)
			throws ParseException
	{
		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		if (binRes.hasErrors()) 
			{
				model.addAttribute("listaEventos", eService.buscarporUsername(currentUserName));
				return "listNotificacion";
			}
		else {
			boolean flag = nService.registrar(objNotificacion);
			if (flag)
				return "redirect:/notificacion/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/notificacion/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("notificacion", new Notificacion());
		model.addAttribute("listaNotificaciones", nService.buscarporUsername(currentUserName));
		
		Optional<Notificacion> objNotificacion = nService.buscarId(id);
		if (objNotificacion == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/notificacion/listar";
		}
		else {
			model.addAttribute("listaEventos", nService.buscarporUsername(currentUserName));				
					
			if (objNotificacion.isPresent())
				objNotificacion.ifPresent(o -> model.addAttribute("notificacion", o));
			
			return "listNotificacion";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		model.put("notificacion", new Notificacion());
		try {
			if (id!=null && id>0) {
				nService.eliminar(id);
				model.put("listaNotificaciones", nService.buscarporUsername(currentUserName));
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaNotificaciones", nService.buscarporUsername(currentUserName));
			
		}
		return "listNotificacion";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("notificacion", new Notificacion());
		model.put("evento", new Evento());
		
		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		model.put("listaNotificaciones", nService.buscarporUsername(currentUserName));
		model.put("listaEventos", eService.buscarporUsername(currentUserName));
		
		return "listNotificacion"; 
	}		
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Notificacion notificaciones) 
	throws ParseException
	{
		nService.listarId(notificaciones.getIdNotificacion());
		return "listNotificacion";
	}	
	
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Notificacion notificaciones)
			throws ParseException
	{
		
		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		model.put("notificacion", new Notificacion());
		
		model.put("listaNotificaciones", nService.buscarporUsername(currentUserName));
		model.put("listaEventos", eService.buscarporUsername(currentUserName));

		List<Notificacion> listanotificaciones;
		notificaciones.setNameNotificacion(notificaciones.getNameNotificacion());
		listanotificaciones = nService.buscarNombre(notificaciones.getNameNotificacion(),currentUserName);
		if(listanotificaciones.isEmpty()) {
			listanotificaciones =nService.buscarNombre(notificaciones.getNameNotificacion(),currentUserName);
		}
		if(listanotificaciones.isEmpty()) {
			listanotificaciones =nService.buscarDescripcion(notificaciones.getNameNotificacion(),currentUserName);
		}
		if(listanotificaciones.isEmpty()) {
			listanotificaciones =nService.buscarNevento(notificaciones.getNameNotificacion(),currentUserName);
		}
		if (listanotificaciones.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		model.put("listaNotificaciones", listanotificaciones);		
		return "listNotificacion";
	}		
}