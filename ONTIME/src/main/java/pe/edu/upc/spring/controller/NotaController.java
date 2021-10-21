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
import pe.edu.upc.spring.model.Estado;
import pe.edu.upc.spring.model.Persona;

import pe.edu.upc.spring.service.INotaService;
import pe.edu.upc.spring.service.IEstadoService;
//import pe.edu.upc.spring.service.IPersonaService;

@Controller
@RequestMapping("/pet")
public class NotaController {
	@Autowired
	private INotaService nService;
	
	@Autowired
	private IEstadoService eService;
	
	//@Autowired
	//private IPersonaService pService;	
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
		
	/*@RequestMapping("/")
	public String irPaginaListadoMascotas(Map<String, Object> model) {
		model.put("listaMascotas", pService.listar());
		return "listPet";
	}*/
	
	/*@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("nota", new Nota());
		model.addAttribute("dueno", new Dueno());
		model.addAttribute("pet", new Pet());
		
		model.addAttribute("listaRazas", rService.listar());
		model.addAttribute("listaDuenos", dService.listar());		
		
		return "pet";
	}*/
	
	/*@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Pet objPet, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) 
			{
				model.addAttribute("listaRazas", rService.listar());
				model.addAttribute("listaDuenos", dService.listar());			
				return "pet";
			}
		else {
			boolean flag = pService.registrar(objPet);
			if (flag)
				return "redirect:/pet/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/pet/irRegistrar";
			}
		}
	}*/
	
	/*
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Nota> objNota = nService.buscarId(id);
		if (objNota == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/nota/listar";
		}
		else {
			model.addAttribute("listaEstado", eService.listar());
			model.addAttribute("listaDuenos", dService.listar());			
					
			if (objPet.isPresent())
				objPet.ifPresent(o -> model.addAttribute("pet", o));
			
			return "pet";
		}
	}*/
	
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
		return "listnota"; // cambiar el return 
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaNotas", nService.listar());
		return "listNota"; // cambiar el return 
	}		
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Nota nota) 
	throws ParseException
	{
		nService.listarId(nota.getIdNota());
		return "listPet";
	}	
	
	/*@RequestMapping("/irBuscar")
	public String irBuscar(Model model) 
	{
		model.addAttribute("nota", new Nota());
		return "buscar";//cambiar el return
	}	*/
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Nota nota)
			throws ParseException
	{
		List<Nota> listaNotas;
		nota.setNombreNota(nota.getNombreNota());
		listaNotas = nService.buscarNombre(nota.getNombreNota());
		if (listaNotas.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		model.put("listaNotas", listaNotas);		
		return "buscar";//cambiar el return
	}		
}
