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
import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.model.TipoEvento;
import pe.edu.upc.spring.service.IEventoService;
import pe.edu.upc.spring.service.IPersonaService;
import pe.edu.upc.spring.service.ITipoEventoService;

@Controller
@RequestMapping("/evento")
public class EventoController {
	@Autowired
	private IEventoService eService;

	@Autowired
	private IPersonaService pService;
	
	@Autowired
	private ITipoEventoService tService;
	
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
		
	@RequestMapping("/")
	public String irPaginaListadoEventos(Map<String, Object> model) {
		model.put("listaEventos", eService.listar());
		return "listEvento"; //html de eventos
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("evento", new Evento());
		model.addAttribute("tipoEvento", new TipoEvento());
		model.addAttribute("listaTipoEventos", tService.listar());
		model.addAttribute("persona", new Persona());
		model.addAttribute("listaPersonas", pService.listar());
		
		return "evento";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Evento objEvento, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) 
			{
				model.addAttribute("listaTipoEventos", tService.listar());
				model.addAttribute("listaPersonas", pService.listar());
				return "Evento";
			}
		else {
			boolean flag = eService.registrar(objEvento);
			if (flag)
				return "redirect:/evento/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/evento/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		model.addAttribute("listaEventos", eService.listar());		
		model.addAttribute("tipoEvento", new TipoEvento());
		model.addAttribute("listaTipoEventos", tService.listar());
		model.addAttribute("persona", new Persona());
		model.addAttribute("listaPersonas", pService.listar());
		
		Optional<Evento> objEvento = eService.buscarId(id);
		if (objEvento == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/evento/listar";
		}
		else {
			model.addAttribute("listaTipoEventos", tService.listar());
			model.addAttribute("listaPersonas", pService.listar());
					
			if (objEvento.isPresent())
				objEvento.ifPresent(o -> model.addAttribute("Evento", o));
			
			return "evento";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		model.put("listaPersonas", pService.listar());
		
		
		try {
			if (id!=null && id>0) {
				eService.eliminar(id);
				model.put("listaEventos", eService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaEventos", eService.listar());
			
		}
		return "listEvento"; // cambiar el return 
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("evento", new Evento());
		model.put("listaEventos", eService.listar());		
		model.put("tipoEvento", new TipoEvento());
		model.put("listaTipoEventos", tService.listar());
		model.put("persona", new Persona());
		model.put("listaPersonas", pService.listar());
		
		return "listEventoPag"; // cambiar el return 
	}		
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Evento Evento) 
	throws ParseException
	{
		eService.listarId(Evento.getIdEvento());
		return "listEventoPag";
	}	
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) 
	{
		model.addAttribute("Evento", new Evento());
		return "buscar";
	}	
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Evento Evento)
			throws ParseException
	{
		List<Evento> listaEventos;
		Evento.setNombreEvento(Evento.getNombreEvento());
		listaEventos = eService.buscarNombre(Evento.getNombreEvento());
		if(listaEventos.isEmpty()) {
			listaEventos =eService.buscarNombre(Evento.getNombreEvento());
		}
		if (listaEventos.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		model.put("listaEventos", listaEventos);		
		return "buscar";
	}	
}
