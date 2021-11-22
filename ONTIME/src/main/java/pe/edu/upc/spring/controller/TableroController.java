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

import pe.edu.upc.spring.model.Nota;
import pe.edu.upc.spring.model.Pendiente;
import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.service.INotaService;
import pe.edu.upc.spring.service.IPendienteService;
import pe.edu.upc.spring.service.IPersonaService;

@Controller
@RequestMapping("/tablero")
public class TableroController {
	
	@Autowired
	private INotaService nService;
	
	@Autowired
	private IPendienteService pService;

	@Autowired
	private IPersonaService eService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
		
	@RequestMapping("/registrarPendiente")
	public String registrarPendiente(@ModelAttribute Pendiente objPendiente, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) 
			{
				final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
				model.addAttribute("listaPersonas", eService.listarporUsername(currentUserName));
				return "listTablero";
			}
		else {
			boolean flag = pService.registrar(objPendiente);
			if (flag)
				return "redirect:/tablero/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/tablero/listar";
			}
		}
	}
	
	@RequestMapping("/registrarNota")
	public String registrarNota(@ModelAttribute Nota objNota, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) 
			{
				final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
				model.addAttribute("listaPersonas", eService.listarporUsername(currentUserName));
				return "listTablero";
			}
		else {
			boolean flag = nService.registrar(objNota);
			if (flag)
				return "redirect:/tablero/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/tablero/listar";
			}
		}
	}
	
	@RequestMapping("/modificarPendiente/{id}")
	public String modificarPendiente(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		model.addAttribute("nota",new Nota());
		model.addAttribute("listaNotas", nService.listar());
		model.addAttribute("pendiente",new Pendiente());
		model.addAttribute("listaPendientes", pService.listar());

		Optional<Pendiente> objPendiente = pService.buscarId(id);
		if (objPendiente == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/tablero/listar";
		}
		else {
			final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
			model.addAttribute("listaPersonas", eService.listarporUsername(currentUserName));
			
			if (objPendiente.isPresent())
				objPendiente.ifPresent(o -> model.addAttribute("pendiente", o));
			
			return "listTablero";
		}
	}
	
	@RequestMapping("/modificarNota/{id}")
	public String modificarNota(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		model.addAttribute("nota",new Nota());
		model.addAttribute("listaNotas", nService.listar());
		model.addAttribute("pendiente",new Pendiente());
		model.addAttribute("listaPendientes", pService.listar());

		Optional<Nota> objNota = nService.buscarId(id);
		if (objNota == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/tablero/listar";
		}
		else {		
			final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
			model.addAttribute("listaPersonas", eService.listarporUsername(currentUserName));
				
			if (objNota.isPresent())
				objNota.ifPresent(o -> model.addAttribute("nota", o));
			
			return "listTablero";
		}
	}
	
	@RequestMapping("/eliminarPendiente")
	public String eliminarPendiente(Map<String, Object> model, @RequestParam(value="id") Integer id) {		

		model.put("nota",new Nota());
		model.put("pendiente",new Pendiente());
		model.put("listaPersonas", eService.listar());
		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

		try {
			if (id!=null && id>0) {
				pService.eliminar(id);
				model.put("listaPendientes", pService.buscarporUsername(currentUserName));
				model.put("listaNotas", nService.buscarporUsername(currentUserName));
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaPendientes", pService.buscarporUsername(currentUserName));
			model.put("listaNotas", nService.buscarporUsername(currentUserName));
			
		}
		return "listTablero";
	}
	
	@RequestMapping("/eliminarNota")
	public String eliminarNota(Map<String, Object> model, @RequestParam(value="id") Integer id) {

		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		model.put("nota",new Nota());
		model.put("pendiente",new Pendiente());
		model.put("listaPersonas", eService.listar());
		try {
			if (id!=null && id>0) {
				nService.eliminar(id);
				model.put("listaPendientes", pService.buscarporUsername(currentUserName));
				model.put("listaNotas", nService.buscarporUsername(currentUserName));
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un roche");
			model.put("listaPendientes", pService.buscarporUsername(currentUserName));
			model.put("listaNotas", nService.buscarporUsername(currentUserName));
			
		}
		return "listTablero";
	}	
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("nota", new Nota());
		model.put("pendiente", new Pendiente());
		model.put("persona", new Persona());
		
		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		model.put("listaPendientes", pService.buscarporUsername(currentUserName));
		model.put("listaNotas", nService.buscarporUsername(currentUserName));
		model.put("listaPersonas", eService.listarporUsername(currentUserName));
		
		return "listTablero"; 
	}		
	/*
	@RequestMapping("/listarStatusI")
	public String listarPendientesI(Map<String, Object> model) {
		model.put("nota", new Nota());
		model.put("pendiente", new Pendiente());
		model.put("persona", new Persona());
		
		model.put("listaPendientesI", pService.listarPendientesI());
		model.put("listaNotas", nService.listar());
		model.put("listaPersonas", eService.listar());
		return "listTablero";
	}
	
	@RequestMapping("/listarStatusE")
	public String listarPendientesE(Map<String, Object> model) {
		model.put("nota", new Nota());
		model.put("pendiente", new Pendiente());
		model.put("persona", new Persona());
		
		model.put("listaPendientesI", pService.listarPendientesE());
		model.put("listaNotas", nService.listar());
		model.put("listaPersonas", eService.listar());
		return "listTablero";
	}
	
	@RequestMapping("/listarStatusC")
	public String listarPendientesC(Map<String, Object> model) {
		model.put("nota", new Nota());
		model.put("pendiente", new Pendiente());
		model.put("persona", new Persona());
		
		model.put("listaPendientesC", pService.listarPendientesC());
		model.put("listaNotas", nService.listar());
		model.put("listaPersonas", eService.listar());
		return "listTablero";
	}
		*/
	@RequestMapping("/listarIdPendiente")
	public String listarIdPendiente(Map<String, Object> model, @ModelAttribute Pendiente pendiente) 
	throws ParseException
	{
		pService.listarId(pendiente.getIdPendiente());
		return "listTablero";
	}
	
	@RequestMapping("/listarIdNota")
	public String listarIdNota(Map<String, Object> model, @ModelAttribute Nota nota) 
	throws ParseException
	{
		nService.listarId(nota.getIdNota());
		return "listTablero";
	}	
	
	
	@RequestMapping("/buscarPendiente")
	public String buscarPendiente(Map<String, Object> model, @ModelAttribute Pendiente pendiente)
			throws ParseException
	{
		model.put("nota", new Nota());
		model.put("pendiente", new Pendiente());
		model.put("persona", new Persona());
		
		model.put("listaPendientes", pService.listar());
		model.put("listaNotas", nService.listar());
		model.put("listaPersonas", eService.listar());


		List<Pendiente> listaPendientes;
		pendiente.setNamePendiente(pendiente.getNamePendiente());
		listaPendientes = pService.buscarNombre(pendiente.getNamePendiente());
		
		if(listaPendientes.isEmpty()) {
			listaPendientes = pService.buscarNombre(pendiente.getNamePendiente());
		}
		if (listaPendientes.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		model.put("listaPendientes", listaPendientes);		
		return "listTablero";
	}	
	
	@RequestMapping("/buscarNota")
	public String buscarNota(Map<String, Object> model, @ModelAttribute Nota nota)
			throws ParseException
	{
		model.put("nota", new Nota());
		model.put("pendiente", new Pendiente());
		model.put("persona", new Persona());
		
		model.put("listaPendientes", pService.listar());
		model.put("listaNotas", nService.listar());
		model.put("listaPersonas", eService.listar());

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
		return "listTablero";
	}	
}
