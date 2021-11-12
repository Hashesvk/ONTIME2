package pe.edu.upc.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.service.IPersonaService;

@Controller
public class LoginController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IPersonaService pService;
	
	@GetMapping("/login/ingresar")
	public String login(Model model) {
		model.addAttribute("persona", new Persona());
		return "login";
	}
	
	@RequestMapping("/login/registrar")
	public String registrar(@ModelAttribute Persona objPersona, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors())
			return "login";
		else {
			objPersona.setPassword(passwordEncoder.encode(objPersona.getPassword()));
			boolean flag = pService.registrar(objPersona);
			
			if (flag)
				return "redirect:/login/ingresar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/login/ingresar";
				
			}
		}
	}
	
	@GetMapping("/")
	public String irLogin() {
		return "redirect:/login/ingresar";
	}
}
