package pe.edu.upc.spring.controller;

import java.util.Map;
import java.util.TreeMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/chart")
public class ChartController {

	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
		
	
	@GetMapping("/see")
	public String getPieChart(Model model) {
        Map<String, Integer> graphData = new TreeMap<>();
        graphData.put("2016", 147);
        graphData.put("2017", 1256);
        graphData.put("2018", 3856);
        graphData.put("2019", 19807);
        model.addAttribute("chartData", graphData);
        return "charts";
    }
	
	
}
