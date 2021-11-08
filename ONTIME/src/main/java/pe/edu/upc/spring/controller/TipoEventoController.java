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

import pe.edu.upc.spring.model.TipoEvento;
import pe.edu.upc.spring.service.ITipoEventoService;

@Controller
@RequestMapping("/tipoevento")
public class TipoEventoController {
	
	@Autowired
	private ITipoEventoService tService;
	@RequestMapping("/bienvenido")
	
	public String irPaginaBienvenida() {
		return "bienvenido";
	}

	@RequestMapping("/")
	public String irPaginaListadoTipoEventos(Map<String, Object> model) {
		model.put("listaTipoEventos", tService.listar());
		return "listTipoEvento";
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("tipoevento", new TipoEvento());
		return "tipoevento";

	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute TipoEvento objTipoEvento, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors())
			return "tipoevento";
		else {			
			boolean flag = tService.registrar(objTipoEvento);
			
			if (flag)
				return "redirect:/tipoevento/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/tipoevento/irRegistrar";
				
			}
		}
	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<TipoEvento> objTipoEvento = tService.listarId(id);
		if (objTipoEvento == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/tipoevento/listar";
		}
		else {
			model.addAttribute("tipoevento", objTipoEvento);
			return "tipoevento";
		}
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				tService.eliminar(id);
				model.put("listaTipoEventos", tService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un roche");
			model.put("listaTipoEventos", tService.listar());
		}
		return "listTipoEvento";

	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaTipoEventos", tService.listar());
		return "listTipoEvento";
	}		
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute TipoEvento TipoEvento) 
	throws ParseException
	{
		tService.listarId(TipoEvento.getIdTipoEvento());
		return "listTipoEvento";
	}	
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute TipoEvento tipoevento)
			throws ParseException
	{
		List<TipoEvento> listaTipoEventos;
		tipoevento.setNombreTipoEvento(tipoevento.getNombreTipoEvento());
		listaTipoEventos = tService.buscarNombre(tipoevento.getNombreTipoEvento());
		if (listaTipoEventos.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		model.put("listaTipoEventos", listaTipoEventos);		
		return "buscar";
	}		
}
