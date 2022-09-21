package edu.neorislab.tp1;

import java.time.*;

public class CuentaCajaAhorro extends CuentaBancaria {
	
	public CuentaCajaAhorro(int nroCuenta, String titular, double saldo) {
		//Metodo constructor
		super(nroCuenta, titular, saldo);
	}
	
	@Override
	protected void printDataRetiro(double monto) {
		//Metodo encargado de imprimir por pantalla la operacion retiro
		System.out.println("------------- Operacion retiro -------------");
		System.out.println("Nro Cuenta: " + super.getNroCuenta());
		System.out.println("Titular: " + super.getTitular());
		System.out.println("Retiro: " + monto);
		System.out.println("Saldo actual: " + this.getSaldoTotal());
		System.out.println("Fecha de la operacion: " + LocalDateTime.now());
		System.out.println("--------------------------------------------");
	}
	
	@Override
	protected void printDataTransferencia(CuentaBancaria cuentaDestino,double monto, double comision) {
		//Metodo encargado de imprimir por pantalla la operacion transferencia
		System.out.println("------------- Operacion transferencia -------------");
		System.out.println("Nro cuenta origen: " + super.getNroCuenta());
		System.out.println("Tipo de cuenta origen: " + super.getClass());
		System.out.println("Nro cuenta destino: " + cuentaDestino.getNroCuenta());
		System.out.println("Tipo de cuenta destino: " + cuentaDestino.getClass());
		System.out.println("Titular origen: " + super.getTitular());
		System.out.println("Titular destino: " + cuentaDestino.getTitular());
		System.out.println("Monto transferido: " + monto);
		System.out.println("Saldo actual: " + super.getSaldo());
		System.out.println("Fecha de la operacion: " + LocalDateTime.now());
		if(comision > 0)
			System.out.println("La transferencia tiene un cargo de " + comision + " por realizar una transferencia entre distintas cuentas");
		System.out.println("--------------------------------------------");
	}
	
	@Override
	protected void retiro(double monto) {
		//Metodo encargado de realizar el retiro de saldo
		if(super.getSaldo() >= monto) {
			super.setSaldo((super.getSaldo() - monto));
			this.printDataRetiro(monto);
		}
		else
			System.out.println("No posee suficiente saldo para realizar la operacion, su saldo es de " + super.getSaldo());
	}
	
	@Override
	protected void transferir(CuentaBancaria cuentaDestino, double monto, double comision) {
		//Metodo encargado de realizar la transferencia
		if (super.getNroCuenta() != cuentaDestino.getNroCuenta())
			if(this.getSaldoTotal() >= (monto + monto * comision)) {
				cuentaDestino.setSaldo((cuentaDestino.getSaldo() + monto));
				super.setSaldo((super.getSaldo() - (monto + monto * comision)));
				this.printDataTransferencia(cuentaDestino, monto, comision);
			}
			else
				if ((comision > 0) && (this.getSaldoTotal() >= monto))
					System.out.println("No posee suficiente saldo para realizar la operacion ya que al transferir entre distintas cuentas tiene un cargo, su saldo es de " + this.getSaldoTotal());
				else
					System.out.println("No posee suficiente saldo para realizar la operacion, su saldo es de " + this.getSaldoTotal());
		else
			System.out.println("No se puede transferir entre mismas cuentas");
	}
	
	@Override
	public double getSaldoTotal() {
		//Metodo que retorna el saldo total del usuario
		return super.getSaldo();
	}
	
}
