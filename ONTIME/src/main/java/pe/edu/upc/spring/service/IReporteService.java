package pe.edu.upc.spring.service;

import java.util.List;

import pe.edu.upc.spring.model.Reporte;

public interface IReporteService {
	public List<Reporte> ListarStatusPendientes();
}