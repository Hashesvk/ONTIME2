package pe.edu.upc.spring.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import pe.edu.upc.spring.model.Notificaciones;
import pe.edu.upc.spring.service.IEventoService;
import pe.edu.upc.spring.service.INotificacionesService;

@Controller
@RequestMapping("/notificaciones")
public class NotificacionesController {

	@Autowired
	private INotificacionesService nService;
	
	@Autowired
	private IEventoService eService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
			
	@RequestMapping("/")
	public String irPaginaListadonotificacioness(Map<String, Object> model) {
		model.put("listanotificacioness", nService.listar());
		return "listnotificaciones";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("notificaciones", new Notificaciones());
		model.addAttribute("evento", new Evento());
		
		model.addAttribute("listaEventos", eService.listar());
		
		return "notificaciones";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Notificaciones objnotificaciones, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) 
			{
				model.addAttribute("listaEventos", eService.listar());
				return "notificaciones";
			}
		else {
			boolean flag = nService.registrar(objnotificaciones);
			if (flag)
				return "redirect:/notificaciones/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/notificaciones/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Notificaciones> objnotificaciones = nService.buscarId(id);
		if (objnotificaciones == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/notificaciones/listar";
		}
		else {
			model.addAttribute("listaEventos", eService.listar());
				
					
			if (objnotificaciones.isPresent())
				objnotificaciones.ifPresent(o -> model.addAttribute("notificaciones", o));
			
			return "notificaciones";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				nService.eliminar(id);
				model.put("listanotificacioness", nService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listanotificaciones", nService.listar());
			
		}
		return "listnotificaciones"; // cambiar el return 
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listanotificaciones", nService.listar());
		return "listnotificaciones"; // cambiar el return 
	}		
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Notificaciones notificaciones) 
	throws ParseException
	{
		nService.listarId(notificaciones.getIdNotificaciones());
		return "listnotificaciones";
	}	
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) 
	{
		model.addAttribute("notificaciones", new Notificaciones());
		return "buscar";//cambiar el return
	}	
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Notificaciones notificaciones)
			throws ParseException
	{
		List<Notificaciones> listanotificaciones;
		notificaciones.setNombreNotificacion(notificaciones.getNombreNotificacion());
		listanotificaciones = nService.buscarNombre(notificaciones.getNombreNotificacion());
		if(listanotificaciones.isEmpty()) {
			listanotificaciones =nService.buscarNombre(notificaciones.getNombreNotificacion());
		}
		if (listanotificaciones.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		model.put("listanotificaciones", listanotificaciones);		
		return "buscar";//cambiar el return
	}		
}