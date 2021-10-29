package pe.edu.upc.spring.controller;

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

import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.service.IPersonaService;

@Controller
@RequestMapping("/persona")
public class PersonaController {
	
	@Autowired
	private IPersonaService pService;
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "login";
	}

	@RequestMapping("/")
	public String irPaginaListadoPersonas(Map<String, Object> model) {
		model.put("listaPersonas", pService.listar());
		return "listPersona";
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("persona", new Persona());
		return "persona";

	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Persona objPersona, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors())
			return "persona";
		else {			
			boolean flag = pService.registrar(objPersona);
			
			if (flag)
				return "redirect:/persona/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/persona/irRegistrar";
				
			}
		}
	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Persona> objPersona = pService.listarId(id);
		if (objPersona == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/persona/listar";
		}
		else {
			model.addAttribute("persona", objPersona);
			return "persona";
		}
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				pService.eliminar(id);
				model.put("listaPersonas", pService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un roche");
			model.put("listaPersonas", pService.listar());
		}
		return "listPersona";

	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaPersonas", pService.listar());
		return "listPersona";
	}		
	


}