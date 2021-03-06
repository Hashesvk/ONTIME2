package pe.edu.upc.spring.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Prueba;

import pe.edu.upc.spring.model.TipoEvento;

import pe.edu.upc.spring.service.IPruebaService;
import pe.edu.upc.spring.service.ITipoEventoService;

@Controller
@RequestMapping("/prueba")
public class PruebaController {
	@Autowired
	private IPruebaService pService;
	
	@Autowired
	private ITipoEventoService tpService;
	
	
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
			
	@PostMapping("/registrar")
	public String registrar(@Valid @ModelAttribute Prueba objPrueba, BindingResult binRes, Model model)
			throws ParseException
	{		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

		if (binRes.hasErrors()) 
			{
			model.addAttribute("listaTipoEventos", tpService.buscarporUsername(currentUserName));
				return "redirect:/prueba/listar";
			}
		else {
			boolean flag = pService.registrar(objPrueba);
			if (flag) {
				
				
				return "redirect:/prueba/listar";
				
				}
			else {
				model.addAttribute("listaPruebas", pService.listar());

				model.addAttribute("mensaje", "Ocurrio un error");
				return "listPrueba";
			}
		}
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		model.addAttribute("prueba", new Prueba());
		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

		model.addAttribute("listaPruebas", pService.buscarporUsername(currentUserName));

		Optional<Prueba> objPrueba = pService.buscarId(id);
		if (objPrueba == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/prueba/listar";
		}
		else {
			model.addAttribute("listaTipoEventos", tpService.buscarporUsername(currentUserName));
				
					
			if (objPrueba.isPresent())
				objPrueba.ifPresent(o -> model.addAttribute("prueba", o));
			
			return "listPrueba";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		model.put("prueba", new Prueba());
		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

		try {
			if (id!=null && id>0) {
				pService.eliminar(id);
				model.put("listaPruebas", pService.buscarporUsername(currentUserName));
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaPruebas", pService.buscarporUsername(currentUserName));
			
		}
		return "listPrueba"; // cambiar el return 
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("prueba", new Prueba());
		model.put("tipoEvento", new TipoEvento());
		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		model.put("listaPruebas", pService.buscarporUsername(currentUserName));
		model.put("listaTipoEventos", tpService.buscarporUsername(currentUserName));
		
		return "listPrueba"; // cambiar el return 
	}		
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Prueba prueba) 
	throws ParseException
	{
		pService.listarId(prueba.getIdPrueba());
		return "listPrueba";
	}	
	

	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Prueba prueba)
			throws ParseException
	{
		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		
		model.put("prueba", new Prueba());
		
		model.put("listaPruebas", pService.buscarporUsername(currentUserName));
		model.put("listaTipoEventos", tpService.buscarporUsername(currentUserName));
		List<Prueba> listaPruebas;
		prueba.setNamePrueba(prueba.getNamePrueba());
		listaPruebas = pService.buscarNombre(prueba.getNamePrueba(),currentUserName);
		if(listaPruebas.isEmpty()) {
			listaPruebas =pService.buscarNombre(prueba.getNamePrueba(),currentUserName);
		}
		if(listaPruebas.isEmpty()) {
			listaPruebas =pService.buscarTevento(prueba.getNamePrueba(),currentUserName);
		}
		if (listaPruebas.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		model.put("listaPruebas", listaPruebas);		
		return "listPrueba";//cambiar el return
	}		
}
