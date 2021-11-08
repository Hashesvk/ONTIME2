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

import pe.edu.upc.spring.model.Nota;
import pe.edu.upc.spring.model.Persona;

import pe.edu.upc.spring.service.INotaService;
import pe.edu.upc.spring.service.IPersonaService;

@Controller
@RequestMapping("/nota")
public class NotaController {
	@Autowired
	private INotaService nService;
	
	@Autowired
	private IPersonaService pService;	
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
		
	@RequestMapping("/")
	public String irPaginaListadoNotas(Map<String, Object> model) {
		model.put("listaNotas", nService.listar());
		return "listNota";
	}
	
	
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Nota objNota, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) 
			{
				model.addAttribute("listaPersonas", pService.listar());
				return "nota";
			}
		else {
			boolean flag = nService.registrar(objNota);
			if (flag)
				return "redirect:/nota/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/nota/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		model.addAttribute("nota",new Nota());
		model.addAttribute("listaNotas", nService.listar());
		Optional<Nota> objNota = nService.buscarId(id);
		if (objNota == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/nota/listar";
		}
		else {		
			model.addAttribute("listaPersonas", pService.listar());
				
			if (objNota.isPresent())
				objNota.ifPresent(o -> model.addAttribute("nota", o));
			
			return "listNota";
		}
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				nService.eliminar(id);
				model.put("listaNotas", nService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un roche");
			model.put("listaNotas", nService.listar());
			
		}
		return "listNota";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaNotas", nService.listar());
		model.put("nota", new Nota());
		model.put("persona", new Persona());
		model.put("listaPersonas", pService.listar());
		return "listNota"; 
	}		
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Nota nota) 
	throws ParseException
	{
		nService.listarId(nota.getIdNota());
		return "listNota";
	}	
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Nota nota)
			throws ParseException
	{
		List<Nota> listaNotas;
		nota.setNameNota(nota.getNameNota());
		listaNotas = nService.buscarNombre(nota.getNameNota());
		if (listaNotas.isEmpty()) {
			listaNotas = nService.buscarNombre(nota.getNameNota());
		}
		if (listaNotas.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		model.put("listaNotas", listaNotas);		
		return "buscar";
	}		
}
