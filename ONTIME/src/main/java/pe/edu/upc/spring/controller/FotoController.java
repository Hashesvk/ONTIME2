package pe.edu.upc.spring.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Foto;

import pe.edu.upc.spring.model.TipoEvento;

import pe.edu.upc.spring.service.IFotoService;
import pe.edu.upc.spring.service.ITipoEventoService;
import pe.edu.upc.spring.service.IUploadFileService;


@Controller
@RequestMapping("/foto")
public class FotoController {
	@Autowired
	private IFotoService fService;
	
	@Autowired
	private ITipoEventoService tpService;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
		
	@RequestMapping("/")
	public String irPaginaListadoFotos(Map<String, Object> model) {
		model.put("listaFotos", fService.listar());
		return "listFoto";
	}
	
	
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Foto objft, BindingResult binRes, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status)
			throws ParseException
	{
		if(binRes.hasErrors()) {
			
		model.addAttribute("listaTipoEventos",tpService.listar());
		return "foto";
		} // cambiar el return 
		else {
			if(!foto.isEmpty()) {
				if(objft.getIdFoto()>0 && objft.getImage() !=null && objft.getImage().length()>0) {
					uploadFileService.delete(objft.getImage());
				}
				String uniqueFilename = null;
				
				try {
					uniqueFilename=uploadFileService.copy(foto);
				}  catch (IOException e) {
					e.printStackTrace();
				}
				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				objft.setImage(uniqueFilename);

			}
			boolean flag=fService.registrar(objft);
			if (flag) {
				return "redirect:/foto/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/foto/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		model.addAttribute("listaFotos", fService.listar());
		model.addAttribute("foto", new Foto());
		Optional<Foto> objFoto = fService.buscarId(id);
		if (objFoto == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/foto/listar";
		}
		else {
			model.addAttribute("listaTipoEventos", tpService.listar());
				
					
			if (objFoto.isPresent())
				objFoto.ifPresent(o -> model.addAttribute("foto", o));
			
			return "listFoto";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		
		model.put("listaFotos", fService.listar());
		model.put("foto", new Foto());

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
		return "listFoto"; // cambiar el return 
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaFotos", fService.listar());
		model.put("foto", new Foto());
		model.put("listaTipoEventos",tpService.listar());
		model.put("tipoEvento", new TipoEvento());
		return "listFoto";
	}	
	
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}
	@GetMapping(value = "/view/{id}")
	public String view(@PathVariable(value = "id") int id, Map<String, Object> model, RedirectAttributes flash) {

		Foto foto = fService.listarId(id);

		if (foto == null) {
			flash.addFlashAttribute("error", "El producto no existe en la base de datos");
			return "product/listProducts";
		}

		model.put("foto", foto);
		model.put("titulo", "Detalle de producto: " + foto.getNamephoto());
 
		return "ver";
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
