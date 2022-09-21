package edu.neorislab.tp1;

import java.time.LocalDateTime;

public abstract class CuentaBancaria {
	
	private int nroCuenta;
	private String titular;
	private double saldo = 0;
	private boolean habilitada;
	
	public CuentaBancaria(int nroCuenta, String titular, double saldo){
		//Metodo constructor de la clase
		if (nroCuenta >= 0) {
			this.nroCuenta = nroCuenta;
			this.titular = titular;
			this.habilitada = true;
			if(saldo >= 0)
				this.saldo = saldo;
		}
	}

	public void setSaldo(double monto) {
		//Metodo que setea el saldo
		this.saldo = monto;
	}
	
	public double getSaldo() {
		//Metodo que retorna el saldo
		return this.saldo;
	}
	
	public int getNroCuenta() {
		//Metodo que retorna el numero de cuenta
		return this.nroCuenta;
	}
	
	public void setHabilitada(boolean habilitada){
		//Metodo que setea si la cuenta esta habilitada
		this.habilitada = habilitada;
	}
	
	public boolean getHabilitada(){
		//Metodo que retorna si una cuenta esta habilitada o no
		return this.habilitada;
	}
	
	public String getTitular() {
		//Metodo que retorna el nombre del titular de la cuenta
		return this.titular;
	}
	
	private void printDataDeposito(double monto) {
		//Metodo encargado de imprimir por pantalla la operacion deposito
		System.out.println("------------- Operacion deposito -------------");
		System.out.println("Nro Cuenta: " + this.getNroCuenta());
		System.out.println("Titular: " + this.getTitular());
		System.out.println("Deposito: " + monto);
		System.out.println("Saldo actual: " + this.getSaldoTotal());
		System.out.println("Fecha de la operacion: " + LocalDateTime.now());
		System.out.println("--------------------------------------------");
	}
	
	public void deposito(double monto){
		//Metodo encargado de realizar el deposito si la cuenta esta habilitada y el monto es mayor a 0
		if(this.getHabilitada())
			if(monto > 0) {
				this.setSaldo(this.getSaldo() + monto);
				this.printDataDeposito(monto);
			}
		else
			System.out.println("La cuenta no se encuentra habilitada para realizar operaciones");
	}
	
	public void transferencia(CuentaBancaria cuentaDestino, double monto, double comision) {
		//Metodo publico que se encarga de verificar y llamar al metodo transferir para realizar la transferencia
		if(this.getHabilitada() && cuentaDestino.getHabilitada())
			if(this.getClass().equals(cuentaDestino.getClass()))
				this.transferir(cuentaDestino, monto,0);
			else
				this.transferir(cuentaDestino, monto, comision);
		else
			if(!this.getHabilitada())
				System.out.println("La cuenta " + this.getTitular() + " no se encuentra habilitada para realizar operaciones");
			else
				System.out.println("La cuenta " + cuentaDestino.getTitular() + " no se encuentra habilitada para realizar operaciones");		
	}
	
	public void retirar(double monto) {
		//Metodo encargado de verificar que la cuenta este habilitada para retirar y llame a la funcion retiro en caso de estarlo
		if(this.getHabilitada())
			this.retiro(monto);
		else
			System.out.println("La cuenta no se encuentra habilitada para realizar operaciones");
	}
	
	//Metodos abstractos
	protected abstract void transferir(CuentaBancaria cuentaDestino, double monto, double comision);
	protected abstract void printDataTransferencia(CuentaBancaria cuentaDestino,double monto, double comision);
	protected abstract void printDataRetiro(double monto);
	protected abstract void retiro(double monto);
	public abstract double getSaldoTotal();
}
