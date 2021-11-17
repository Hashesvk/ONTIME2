package pe.edu.upc.spring.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Pendiente;
import pe.edu.upc.spring.model.Reporte;
import pe.edu.upc.spring.repository.IPendienteRepository;
import pe.edu.upc.spring.service.IReporteService;

@Service
public class ReporteServiceImpl implements IReporteService {

	@Autowired
	private IPendienteRepository dPendiente;
	
	@Override
	@Transactional(readOnly = true)
    public List<Reporte> ListarStatusPendientes() {
		List<Reporte> ListaR = new ArrayList<Reporte>();
		List<Pendiente> ListaP = dPendiente.findAll();
		ListaR.add(new Reporte ("Pendientes Incompletos", 0));
		ListaR.add(new Reporte ("Pendientes En Proceso", 0));
		ListaR.add(new Reporte ("Pendientes Completos", 0));		
		
		for (int i = 0; i < ListaR.size(); i++) {
			for (int j = 0; j < ListaP.size(); j++) {
				if(ListaP.get(j).getNameStatus().equals("Incompleto") && ListaR.get(i).getName().equals("Pendientes Incompletos")) {
					ListaR.get(i).setAmount(ListaR.get(i).getAmount() + 1);
				}	
				if(ListaP.get(j).getNameStatus().equals("En proceso") && ListaR.get(i).getName().equals("Pendientes En Proceso")) {
					ListaR.get(i).setAmount(ListaR.get(i).getAmount() + 1);
				}	
				if(ListaP.get(j).getNameStatus().equals("Completo") && ListaR.get(i).getName().equals("Pendientes Completos")) {
					ListaR.get(i).setAmount(ListaR.get(i).getAmount() + 1);
				}	
			}			
		}
		
	
		
		return ListaR;
	}

}
