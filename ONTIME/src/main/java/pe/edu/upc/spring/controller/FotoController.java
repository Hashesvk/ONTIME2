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

import pe.edu.upc.spring.model.Foto;

import pe.edu.upc.spring.model.TipoEvento;

import pe.edu.upc.spring.service.IFotoService;
import pe.edu.upc.spring.service.ITipoEventoService;

@Controller
@RequestMapping("/foto")
public class FotoController {
	@Autowired
	private IFotoService fService;
	
	@Autowired
	private ITipoEventoService tpService;
	
	
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
		
	@RequestMapping("/")
	public String irPaginaListadoFotos(Map<String, Object> model) {
		model.put("listaFotos", fService.listar());
		return "listFoto";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("foto", new Foto());
		model.addAttribute("tipopersona", new TipoEvento());
		
		model.addAttribute("listaTipoEventos", tpService.listar());
		
		return "foto";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Foto objFoto, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) 
			{
				model.addAttribute("listaTipoEventos", tpService.listar());
				return "foto";
			}
		else {
			boolean flag = fService.registrar(objFoto);
			if (flag)
				return "redirect:/foto/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/foto/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Foto> objFoto = fService.buscarId(id);
		if (objFoto == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/foto/listar";
		}
		else {
			model.addAttribute("listaTipoEventos", tpService.listar());
				
					
			if (objFoto.isPresent())
				objFoto.ifPresent(o -> model.addAttribute("foto", o));
			
			return "foto";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				fService.eliminar(id);
				model.put("listaFotos", fService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaFotos", fService.listar());
			
		}
		return "listPrueba"; // cambiar el return 
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaFotos", fService.listar());
		return "listFoto"; // cambiar el return 
	}		
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Foto foto) 
	throws ParseException
	{
		fService.listarId(foto.getIdFoto());
		return "listFoto";
	}	
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) 
	{
		model.addAttribute("foto", new Foto());
		return "buscar";//cambiar el return
	}	
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Foto foto)
			throws ParseException
	{
		List<Foto> listaFotos;
		foto.setNamephoto(foto.getNamephoto());
		listaFotos = fService.buscarNombre(foto.getNamephoto());
		if(listaFotos.isEmpty()) {
			listaFotos =fService.buscarNombre(foto.getNamephoto());
		}
		if (listaFotos.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		model.put("listaPruebas", listaFotos);		
		return "buscar";//cambiar el return
	}		
}
