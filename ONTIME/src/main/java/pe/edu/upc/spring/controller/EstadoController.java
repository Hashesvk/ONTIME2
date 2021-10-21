package pe.edu.upc.spring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Estado;
import pe.edu.upc.spring.service.IEstadoService;

@Controller
@RequestMapping("/estado")
public class EstadoController {
	
	@Autowired
	private IEstadoService eService;
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Estado objEstado, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors())
			return "race";
		else {
			boolean flag = eService.registrar(objEstado);
			if (flag)
				return "redirect:/estado/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/estado/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/listar")
	public void listar(Map<String, Object> model) {
		model.put("listaEstados", eService.listar());
	}	
}
