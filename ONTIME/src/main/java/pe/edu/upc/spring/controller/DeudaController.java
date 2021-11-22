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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Deuda;

import pe.edu.upc.spring.model.Persona;

import pe.edu.upc.spring.service.IDeudaService;
import pe.edu.upc.spring.service.IPersonaService;

@Controller
@RequestMapping("/deuda")
public class DeudaController {
	@Autowired
	private IDeudaService dService;
	
	@Autowired
	private IPersonaService pService;	
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}

	@RequestMapping("/registrar")
	public String registrar(@Valid @ModelAttribute Deuda objDeuda, BindingResult binRes, Model model)
			throws ParseException
	{
		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

		if (binRes.hasErrors()) 
			{
				model.addAttribute("listaPersonas", pService.listarporUsername(currentUserName));
				return "redirect:/deuda/listar";
			}
		else {
			boolean flag = dService.registrar(objDeuda);
			if (flag)
				return "redirect:/deuda/listar";
			else {
				model.addAttribute("listaDeudas",dService.buscarporUsername(currentUserName));
				model.addAttribute("mensaje", "Ocurrio un error");
				return "listDeuda";
			}
		}
	}
		
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{		
		Optional<Deuda> objDeuda = dService.buscarId(id);
		if (objDeuda == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/deuda/listar";
		}
		else {
			final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
			model.addAttribute("listaDeudas", dService.buscarporUsername(currentUserName));
			model.addAttribute("listaPersonas", pService.listarporUsername(currentUserName));			
					
			if (objDeuda.isPresent())
				objDeuda.ifPresent(o -> model.addAttribute("deuda", o));
			
			return "listDeuda";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		model.put("deuda", new Deuda());
		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();	
		try {
			if (id!=null && id>0) {
				dService.eliminar(id);
				model.put("listaDeudas", dService.buscarporUsername(currentUserName));	
				model.put("listaPersonas", pService.listarporUsername(currentUserName));
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaDeudas", dService.buscarporUsername(currentUserName));
			
		}
		return "listDeuda"; 
	}
	
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {

		model.put("deuda", new Deuda());
		model.put("persona", new Persona());
		
		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		model.put("listaDeudas", dService.buscarporUsername(currentUserName));
		model.put("listaPersonas", pService.listarporUsername(currentUserName));
		
		return "listDeuda"; 
	}		
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Deuda deuda) 
	throws ParseException
	{
		dService.listarId(deuda.getIdDeuda());
		return "listDeuda";
	}	
		
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Deuda deuda)
			throws ParseException
	{
		model.put("deuda", new Deuda());
		List<Deuda> listaDeudas;
		deuda.setNameCreditor(deuda.getNameCreditor());
		listaDeudas = dService.buscarNombre(deuda.getNameCreditor());
		if(listaDeudas.isEmpty()) {
			listaDeudas =dService.buscarNombre(deuda.getNameCreditor());
		}
		if (listaDeudas.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		model.put("listaDeudas", listaDeudas);		
		return "listDeuda";
	}		
}
