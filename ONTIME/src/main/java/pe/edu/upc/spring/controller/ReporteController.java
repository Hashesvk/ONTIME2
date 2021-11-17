package pe.edu.upc.spring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.spring.model.Reporte;
import pe.edu.upc.spring.service.IReporteService;

@Controller
@RequestMapping("/reporte")
public class ReporteController {
	@Autowired
	private IReporteService rService;	
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
	
	@RequestMapping("/listar")
	public String listarStatusPendientes(Map<String, Object> model) {
		model.put("reportes", rService.ListarStatusPendientes());
		model.put("reporte", new Reporte());
		return "reportes"; 
	}		
	
}
