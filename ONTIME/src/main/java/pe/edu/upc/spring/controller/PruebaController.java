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
		
	@RequestMapping("/")
	public String irPaginaListadoPruebas(Map<String, Object> model) {
		model.put("listaPruebas", pService.listar());
		return "listPrueba";
	}
	
	
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Prueba objPrueba, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) 
			{
				model.addAttribute("listaTipoEventos", tpService.listar());
				return "prueba";
			}
		else {
			boolean flag = pService.registrar(objPrueba);
			if (flag)
				return "redirect:/prueba/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/prueba/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		model.addAttribute("prueba", new Prueba());
		model.addAttribute("listaPruebas", pService.listar());
		Optional<Prueba> objPrueba = pService.buscarId(id);
		if (objPrueba == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/prueba/listar";
		}
		else {
			model.addAttribute("listaTipoEventos", tpService.listar());
				
					
			if (objPrueba.isPresent())
				objPrueba.ifPresent(o -> model.addAttribute("prueba", o));
			
			return "listPrueba";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		model.put("listaPruebas", pService.listar());
		model.put("prueba", new Prueba());
		try {
			if (id!=null && id>0) {
				pService.eliminar(id);
				model.put("listaPruebas", pService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaPruebas", pService.listar());
			
		}
		return "listPrueba"; // cambiar el return 
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaPruebas", pService.listar());
		model.put("prueba", new Prueba());
		model.put("tipoEvento", new TipoEvento());
		model.put("listaTipoEventos", tpService.listar());
		
		return "listPrueba"; // cambiar el return 
	}		
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Prueba prueba) 
	throws ParseException
	{
		pService.listarId(prueba.getIdPrueba());
		return "listPrueba";
	}	
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) 
	{
		model.addAttribute("prueba", new Prueba());
		return "buscar";//cambiar el return
	}	
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Prueba prueba)
			throws ParseException
	{
		model.put("prueba", new Prueba());
		List<Prueba> listaPruebas;
		prueba.setNamePrueba(prueba.getNamePrueba());
		listaPruebas = pService.buscarNombre(prueba.getNamePrueba());
		if(listaPruebas.isEmpty()) {
			listaPruebas =pService.buscarNombre(prueba.getNamePrueba());
		}
		if(listaPruebas.isEmpty()) {
			listaPruebas =pService.buscarTevento(prueba.getNamePrueba());
		}
		if (listaPruebas.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		model.put("listaPruebas", listaPruebas);		
		return "listPrueba";//cambiar el return
	}		
}
