package edu.neorislab.tp1;

import java.time.LocalDateTime;

public class CuentaCorriente extends CuentaBancaria {
	
	private double saldoDescubierto;
	
	public CuentaCorriente(int nroCuenta, String titular, double saldo, double saldoDescubierto) {
		//Metodo constructor 
		super(nroCuenta, titular, saldo);
		this.setSaldoDescubierto(saldoDescubierto);
	}
	
	public void setSaldoDescubierto(double saldoDescubierto) {
		//Metodo que setea el saldo descubierto
		if (saldoDescubierto >= 0)
			this.saldoDescubierto = saldoDescubierto;
	}
	
	public double getSaldoDescubierto() {
		//Metodo que retorna el saldo descubierto
		return this.saldoDescubierto;
	}

	@Override
	protected void printDataTransferencia(CuentaBancaria cuentaDestino, double monto, double comision) {
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
		System.out.println("Saldo descubierto: " + this.getSaldoDescubierto());
		System.out.println("Fecha de la operacion: " + LocalDateTime.now());
		if(comision > 0)
			System.out.println("La transferencia tiene un cargo de " + comision + " por realizar una transferencia entre distintas cuentas");
		System.out.println("--------------------------------------------");
	}

	@Override
	protected void printDataRetiro(double monto) {
		//Metodo encargado de imprimir por pantalla la operacion retiro
		System.out.println("------------- Operacion Retiro -------------");
		System.out.println("Nro Cuenta: " + super.getNroCuenta());
		System.out.println("Titular: " + super.getTitular());
		System.out.println("Retiro: " + monto);
		System.out.println("Saldo actual: " + super.getSaldo());
		System.out.println("Saldo descubierto: " + this.getSaldoDescubierto());
		System.out.println("Fecha de la operacion: " + LocalDateTime.now());
		System.out.println("--------------------------------------------");	
	}
	
	@Override
	protected void retiro(double monto) {
		//Metodo encargado de realizar el retiro de saldo
		if (monto <= super.getSaldo()) {
			super.setSaldo(super.getSaldo() - monto);
			this.printDataRetiro(monto);
		}
		else{
			if(monto <= super.getSaldo() + this.getSaldoDescubierto()) {
				System.out.println("Aviso se utilizara el saldo descubierto para realizar la operacion");
				this.setSaldoDescubierto((this.getSaldoDescubierto() + (super.getSaldo() - monto)));
				super.setSaldo(0);
				this.printDataRetiro(monto);
			}
			else	
				System.out.println("No posee suficiente saldo para realizar la operacion, su saldo es de " + this.getSaldoTotal());
		}
	}
	
	@Override
	public double getSaldoTotal() {
		//Metodo que retorna el saldo total del usuario
		return super.getSaldo() + this.getSaldoDescubierto();
	}
	
	@Override
	protected void transferir(CuentaBancaria cuentaDestino, double monto, double comision) {
		//Metodo encargado de realizar la transferencia
		if (super.getNroCuenta() != cuentaDestino.getNroCuenta())
			if(super.getSaldo() >= (monto + monto * comision)) {
				cuentaDestino.setSaldo((cuentaDestino.getSaldo() + monto));
				super.setSaldo((super.getSaldo() - (monto + monto * comision)));
				this.printDataTransferencia(cuentaDestino, monto, comision);
			}
			else
				if (this.getSaldoTotal() >= (monto + monto * comision)) {
					cuentaDestino.setSaldo((cuentaDestino.getSaldo() + monto));
					this.setSaldoDescubierto((this.getSaldoDescubierto() - ((monto + monto * comision) - super.getSaldo())));
					super.setSaldo(0);
					this.printDataTransferencia(cuentaDestino, monto,comision);
				}
				else
					if ((comision > 0) && (this.getSaldoTotal() >= monto))
						System.out.println("No posee suficiente saldo para realizar la operacion ya que al transferir entre distintas cuentas tiene un cargo, su saldo es de " + this.getSaldoTotal());
					else
						System.out.println("No posee suficiente saldo para realizar la operacion, su saldo es de " + this.getSaldoTotal());
		else
			System.out.println("No se puede transferir entre mismas cuentas");
	}
}
