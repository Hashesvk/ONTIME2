package pe.edu.upc.spring.controller;

import java.util.Map;
import java.util.TreeMap;

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
		Map<String, Integer> graphData = new TreeMap<>();	
		
		for(int i = 0; i < rService.ListarStatusPendientes().size(); i++) {
			graphData.put(rService.ListarStatusPendientes().get(i).getName(), rService.ListarStatusPendientes().get(i).getAmount());
		}		
		
		model.put("reportes", rService.ListarStatusPendientes());
		model.put("chartData", graphData);
		model.put("reporte", new Reporte());
		return "reportes"; 
	}		
	
}
