package pe.edu.upc.spring.model;

public class Reporte {
	
	private String Name;
	private int Amount;	
	
	public Reporte() {
		super();
	}
	
	public Reporte(String name, int amount) {
		super();
		Name = name;
		Amount = amount;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getAmount() {
		return Amount;
	}

	public void setAmount(int amount) {
		Amount = amount;
	}	
	
}
