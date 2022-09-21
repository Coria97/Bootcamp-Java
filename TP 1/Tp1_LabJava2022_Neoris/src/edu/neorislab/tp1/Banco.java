package edu.neorislab.tp1;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Banco {
	
	private String nombre;
	private ArrayList<CuentaBancaria> cuentas = new ArrayList<CuentaBancaria>();
	
	public Banco(String nombre, ArrayList<CuentaBancaria> cuentas) {
		//Metodo constructor de la clase
		this.nombre = nombre;
		this.cuentas.addAll(cuentas);
	}
	
	public void addCuenta(CuentaBancaria c) {
		//Metodo que añade una cuenta al banco
		this.cuentas.add(c);
	}
	
	public String getNombre() {
		//Metodo que retorna el nombre del banco
		return this.nombre;
	}
	
	public ArrayList<String> obtenerTitularesAptosParaPrestamos(){
		//Metodo encargado de obtener titulares para los prestamos de acuerdo a ciertas condiciones
		return (ArrayList<String>) this.cuentas.stream()
				.filter(e-> e.getSaldoTotal() > 10000 && e.getHabilitada() == true)		
				.map(el -> el.getTitular().toUpperCase())
				.distinct()
				.collect(Collectors.toList());
	}
	
	public boolean algunasCuentasPuedenSerHackeada(){
		//Metodo encargado de comprobar si existen cuentas vulnerables
		ArrayList<CuentaBancaria> l = (ArrayList<CuentaBancaria>) this.cuentas.stream()
				.filter(e-> e.getSaldoTotal() > 50000 && e.getTitular().length() > 15 && e.getNroCuenta()%2 == 0)
				.collect(Collectors.toList());
		if(l.size() > 0)
			return  true;
		return false;
	}
}
