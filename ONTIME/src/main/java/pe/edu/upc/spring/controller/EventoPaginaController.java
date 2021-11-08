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
@RequestMapping("/eventopagina")
public class EventoPaginaController {
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
	public String irPaginaListado(Map<String, Object> model) {
		model.put("listaEventos", eService.listar());
		model.put("listaTipoEventos", tService.listar());
		return "listEventoPag"; //html de eventos
	}
	
	@RequestMapping("/irRegistrarEvento")
	public String irRegistrarEvento(Model model) {
		
		model.addAttribute("evento", new Evento());
		model.addAttribute("tipoEvento", new TipoEvento());
		model.addAttribute("listaTipoEventos", tService.listar());
		model.addAttribute("persona", new Persona());
		model.addAttribute("listaPersonas", pService.listar());
		
		return "evento";
	}
	
	@RequestMapping("/irRegistrarTipoEvento")
	public String irRegistrarTipoEvento(Model model) {
		
		model.addAttribute("evento", new Evento());
		model.addAttribute("tipoEvento", new TipoEvento());
		model.addAttribute("listaTipoEventos", tService.listar());
		model.addAttribute("persona", new Persona());
		model.addAttribute("listaPersonas", pService.listar());
		
		return "tipoevento";

	}
	
	@RequestMapping("/registrarEvento")
	public String registrarEvento(@ModelAttribute Evento objEvento, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) 
			{
				model.addAttribute("listaPersonas", pService.listar());
				return "listEventoPag";
			}
		else {
			boolean flag = eService.registrar(objEvento);
			if (flag)
				return "redirect:/eventopagina/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/eventopagina/listar";
			}
		}
	}
	
	@RequestMapping("/registrarTipoEvento")
	public String registrarTipoEvento(@ModelAttribute TipoEvento objTipoEvento, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			
			model.addAttribute("listaPersonas", pService.listar());
			return "listEventoPag";
		}
		else {			
			boolean flag = tService.registrar(objTipoEvento);
			
			if (flag)
				return "redirect:/eventopagina/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/eventopagina/listar";
				
			}
		}
	}
	
	@RequestMapping("/modificarEvento/{id}")
	public String modificarEvento(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Evento> objEvento = eService.buscarId(id);
		if (objEvento == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/eventopagina/listar";
		}
		else {
			model.addAttribute("listaTipoEventos", tService.listar());
			model.addAttribute("listaPersonas", pService.listar());
					
			if (objEvento.isPresent())
				objEvento.ifPresent(o -> model.addAttribute("evento", o));
			
			return "evento";
		}
	}
	
	@RequestMapping("/modificarTipoEvento/{id}")
	public String modificarTipoEvento(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<TipoEvento> objTipoEvento = tService.listarId(id);
		if (objTipoEvento == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/eventopagina/listar";
		}
		else {
			model.addAttribute("listaTipoEventos", tService.listar());
			model.addAttribute("listaPersonas", pService.listar());
					
			if (objTipoEvento.isPresent())
				objTipoEvento.ifPresent(o -> model.addAttribute("tipoevento", o));
			
			return "tipoevento";
		}
	}
	
	@RequestMapping("/eliminarEvento")
	public String eliminarEvento(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		
		model.put("tipoevento", new TipoEvento());
		model.put("evento", new Evento());
		model.put("listaPersonas", pService.listar());
		model.put("listaEventos", eService.listar());
		model.put("listaTipoEventos", tService.listar());
		
		try {
			if (id!=null && id>0) {
				eService.eliminar(id);
				model.put("listaEventos", eService.listar());
				model.put("listaTipoEventos", tService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaEventos", eService.listar());
			model.put("listaTipoEventos", tService.listar());
			
		}
		return "listEventoPag"; // cambiar el return 
	}
	
	@RequestMapping("/eliminarTipoEvento")
	public String eliminarTipoEvento(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		
		model.put("tipoevento", new TipoEvento());
		model.put("evento", new Evento());
		model.put("listaPersonas", pService.listar());
		model.put("listaEventos", eService.listar());
		model.put("listaTipoEventos", tService.listar());
		
		try {
			if (id!=null && id>0) {
				tService.eliminar(id);
				model.put("listaEventos", eService.listar());
				model.put("listaTipoEventos", tService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un roche");
			model.put("listaEventos", eService.listar());
			model.put("listaTipoEventos", tService.listar());
		}
		return "listEventoPag";

	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {

		model.put("evento", new Evento());
		model.put("tipoevento", new TipoEvento());
		model.put("persona", new Persona());
		
		
		model.put("listaEventos", eService.listar());
		model.put("listaTipoEventos", tService.listar());
		model.put("listaPersonas", pService.listar());
		
		return "listEventoPag";
	}		
	
	@RequestMapping("/listarIdEvento")
	public String listarId(Map<String, Object> model, @ModelAttribute Evento Evento) 
	throws ParseException
	{
		eService.listarId(Evento.getIdEvento());
		return "listEventoPag";
	}	
	
	@RequestMapping("/listarIdTipoEvento")
	public String listarIdTipoEvento(Map<String, Object> model, @ModelAttribute TipoEvento TipoEvento) 
	throws ParseException
	{
		tService.listarId(TipoEvento.getIdTipoEvento());
		return "listEventoPag";
	}
	
	@RequestMapping("/irBuscarEvento")
	public String irBuscarEvento(Model model) 
	{
		model.addAttribute("evento", new Evento());
		return "buscarEvento";
	}	
	
	@RequestMapping("/buscarEvento")
	public String buscarEvento(Map<String, Object> model, @ModelAttribute Evento Evento)
			throws ParseException
	{
		model.put("evento", new Evento());
		model.put("tipoevento", new TipoEvento());

		List<Evento> listaEventos;
		Evento.setNombreEvento(Evento.getNombreEvento());
		listaEventos = eService.buscarNombre(Evento.getNombreEvento());
		if(listaEventos.isEmpty()) {
			listaEventos =eService.buscarNombre(Evento.getNombreEvento());
		}
		if(listaEventos.isEmpty()) {
			listaEventos =eService.buscarComple(Evento.getNombreEvento());
		}
		if(listaEventos.isEmpty()) {
			listaEventos =eService.buscarTevento(Evento.getNombreEvento());
		}
		
		if (listaEventos.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		model.put("listaEventos", listaEventos);		
		return "listEventoPag";
	}	
	
	@RequestMapping("/irBuscarTipoEvento")
	public String irBuscarTipoEvento(Model model) 
	{
		model.addAttribute("tipoevento", new TipoEvento());
		return "buscarTipoEvento";
	}	
	
	@RequestMapping("/buscarTipoEvento")
	public String buscarTipoEvento(Map<String, Object> model, @ModelAttribute TipoEvento tipoevento)
			throws ParseException
	{
		model.put("tipoevento", new TipoEvento());
		model.put("evento", new Evento());

		List<TipoEvento> listaTipoEventos;
		tipoevento.setNombreTipoEvento(tipoevento.getNombreTipoEvento());
		listaTipoEventos = tService.buscarNombre(tipoevento.getNombreTipoEvento());
		if (listaTipoEventos.isEmpty()) {
			listaTipoEventos=tService.buscarNombre(tipoevento.getNombreTipoEvento());
		}
		if (listaTipoEventos.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		model.put("listaTipoEventos", listaTipoEventos);		
		return "listEventoPag";
	}		
}
