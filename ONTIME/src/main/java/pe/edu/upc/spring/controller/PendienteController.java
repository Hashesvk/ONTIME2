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

import pe.edu.upc.spring.model.Pendiente;
import pe.edu.upc.spring.service.IPendienteService;
import pe.edu.upc.spring.service.IPersonaService;

@Controller
@RequestMapping("/pendiente")
public class PendienteController {
	@Autowired
	private IPendienteService pService;

	@Autowired
	private IPersonaService eService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
		
	@RequestMapping("/")
	public String irPaginaListadoEventos(Map<String, Object> model) {
		model.put("listaPendientes", pService.listar());
		return "listPendiente";
	}

	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Pendiente objPendiente, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) 
			{
				model.addAttribute("listaPersonas", eService.listar());
				return "pendiente";
			}
		else {
			boolean flag = pService.registrar(objPendiente);
			if (flag)
				return "redirect:/pendiente/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/pendiente/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Pendiente> objPendiente = pService.buscarId(id);
		if (objPendiente == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/pendiente/listar";
		}
		else {
			model.addAttribute("listaPersonas", eService.listar());
			
			if (objPendiente.isPresent())
				objPendiente.ifPresent(o -> model.addAttribute("pendiente", o));
			
			return "pendiente";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				pService.eliminar(id);
				model.put("listaPendientes", pService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaPendientes", pService.listar());
			
		}
		return "listPendiente";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaPendientes", pService.listar());
		return "listPendiente"; 
	}		
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Pendiente pendiente) 
	throws ParseException
	{
		pService.listarId(pendiente.getIdPendiente());
		return "listEvento";
	}	
	
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Pendiente pendiente)
			throws ParseException
	{
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
		return "buscar";
	}	
}
