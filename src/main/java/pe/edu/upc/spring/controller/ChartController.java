package pe.edu.upc.spring.controller;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.spring.model.Evento;
import pe.edu.upc.spring.model.TipoEvento;
import pe.edu.upc.spring.service.IEventoService;
import pe.edu.upc.spring.service.IReporteService;
import pe.edu.upc.spring.service.ITipoEventoService;

@Controller
@RequestMapping("/chart")
public class ChartController {

	@Autowired
	private IEventoService eService;
	
	@Autowired
	private ITipoEventoService tService;
	
	@Autowired
	private IReporteService rService;	
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
	
	@GetMapping("/see")
	public String getPieChart(Map<String, Object> model) {
		Map<String, Integer> graphData = new TreeMap<>();		
        Map<String, Integer> graphData2 = new TreeMap<>();		
        Map<String, Integer> graphData3 = new TreeMap<>();		
		Map<String, Integer> graphData4 = new TreeMap<>();	
        
		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

		List<Evento> listaEventos = eService.buscarporUsername(currentUserName);
		List<TipoEvento> listaTipoEventos = tService.buscarporUsername(currentUserName);

		int cont = 0;
		for(int i = 0; i < listaTipoEventos.size(); i++) {
			for(int j = 0; j < listaEventos.size(); j++) {
				if(listaTipoEventos.get(i).getIdTipoEvento() == listaEventos.get(j).getTipoEvento().getIdTipoEvento()) {
					cont++;
				}
			}
			graphData.put( listaTipoEventos.get(i).getNombreTipoEvento() , cont);
			cont = 0;
		}
		
		
		for(int j = 0; j < listaEventos.size(); j++) {
			graphData2.put( listaEventos.get(j).getNombreEvento(),listaEventos.get(j).getNumcomplejidad());									
		}
			
		for(int j = 0; j < listaEventos.size(); j++) {
			graphData3.put( listaEventos.get(j).getNombreEvento(),listaEventos.get(j).getNumimportancia());									
		}		
			
		for(int i = 0; i < rService.ListarStatusPendientes().size(); i++) {
			graphData4.put(rService.ListarStatusPendientes().get(i).getName(), rService.ListarStatusPendientes().get(i).getAmount());
		}	
			
        model.put("chartData", graphData);
        model.put("chartData2", graphData2);
        model.put("chartData3", graphData3);
		model.put("chartData4", graphData4);
        return "charts";
    }
	
	
}
