package edu.neorislab.tp1test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.neorislab.tp1.Banco;
import edu.neorislab.tp1.CuentaBancaria;
import edu.neorislab.tp1.CuentaCajaAhorro;
import edu.neorislab.tp1.CuentaCorriente;

class testCuenta {
	
	/*
	 --------------------------------------------------------------------
	 -------------------------TEST DEPOSITO------------------------------
	 --------------------------------------------------------------------
	 */
	
	@Test
	@DisplayName("Un usuario deposita plata en una cuenta bancaria pero no tiene la cuenta habilitada")
	void test_deposito_en_CuentaBancaria_pero_no_tiene_la_cuenta_habilitada() {
		CuentaCajaAhorro c1 = new CuentaCajaAhorro(1, "usuario1",0);
		c1.setHabilitada(false);
		c1.deposito(50);
		assertEquals(0,c1.getSaldoTotal());
	}
	
	@Test
	@DisplayName("Un usuario deposita plata en una cuenta bancaria")
	void test_deposito_en_CuentaBancaria() {
		CuentaCajaAhorro c1 = new CuentaCajaAhorro(1, "usuario1",0);
		c1.deposito(50);
		assertEquals(50,c1.getSaldoTotal());
	}
	
	@Test
	@DisplayName("Un usuario deposita plata pero por x causa el saldo es negativo")
	void test_deposito_en_CuentaBancaria_saldo_negativo() {
		CuentaCajaAhorro c1 = new CuentaCajaAhorro(1, "usuario1",0);
		c1.deposito(-50);
		assertEquals(0,c1.getSaldoTotal());
	}
	
	/*
	 --------------------------------------------------------------------
	 -------------------------TEST RETIRO--------------------------------
	 --------------------------------------------------------------------
	 */
	
	@Test
	@DisplayName("Un usuario retira plata de caja ahorro pero no tiene la cuenta habilitada")
	void test_retiro_en_CuentaCajaAhorro_pero_no_tiene_la_cuenta_habilitada() {
		CuentaCajaAhorro c1 = new CuentaCajaAhorro(1, "usuario1",100);
		c1.setHabilitada(false);
		c1.retirar(50);
		assertEquals(100,c1.getSaldoTotal());
	}
	
	@Test
	@DisplayName("Un usuario retira plata de caja ahorro pero no toda")
	void test_retiro_en_CuentaCajaAhorro_pero_queda_plata_en_la_cuenta() {
		CuentaCajaAhorro c1 = new CuentaCajaAhorro(1, "usuario1",100);
		c1.retirar(50);
		assertEquals(50,c1.getSaldoTotal());
	}
	
	@Test
	@DisplayName("Un usuario retira toda la plata de caja ahorro")
	void test_retiro_toda_la_plata_en_CuentaCajaAhorro() {
		CuentaCajaAhorro c1 = new CuentaCajaAhorro(1, "usuario1",100);
		c1.retirar(100);
		assertEquals(0,c1.getSaldoTotal());
	}
	
	@Test
	@DisplayName("Un usuario retirar mas plata de la que tiene en la caja ahorro")
	void test_retiro_de_mas_plata_en_CuentaCajaAhorro() {
		CuentaCajaAhorro c1 = new CuentaCajaAhorro(1, "usuario1",100);
		c1.retirar(101);
		assertEquals(100,c1.getSaldoTotal());
	}
	
	@Test
	@DisplayName("Un usuario retira plata de cuenta corriente pero no tiene la cuenta habilitada")
	void test_retiro_en_CuentaCorriente_pero_no_tiene_la_cuenta_habilitada() {
		CuentaCorriente c1 = new CuentaCorriente(1, "usuario1",100,100);
		c1.setHabilitada(false);
		c1.retirar(50);
		assertEquals(200,c1.getSaldoTotal());
	}

	@Test
	@DisplayName("Un usuario retira saldo de cuenta corriente pero no toda")
	void test_retiro_en_CuentaCorriente_pero_queda_plata_en_la_cuenta() {
		CuentaCorriente c1 = new CuentaCorriente(1, "usuario1",100,100);
		c1.retirar(50);
		assertEquals(150,c1.getSaldoTotal());
	}
	
	@Test
	@DisplayName("Un usuario retira todo el saldo de cuenta corriente pero deja la mitad de descubierto")
	void test_retiro_en_CuentaCorriente_pero_queda_saldoDescubierto_en_la_cuenta() {
		CuentaCorriente c1 = new CuentaCorriente(1, "usuario1",100,100);
		c1.retirar(150);
		assertEquals(50,c1.getSaldoTotal());
	}
	
	@Test
	@DisplayName("Un usuario retira toda la plata de cuenta corriente")
	void test_retiro_toda_la_plata_en_CuentaCorriente() {
		CuentaCorriente c1 = new CuentaCorriente(1, "usuario1",100,100);
		c1.retirar(200);
		assertEquals(0,c1.getSaldoTotal());
	}
	
	@Test
	@DisplayName("Un usuario retirar mas plata de la que tiene en la cuenta corriente")
	void test_retiro_de_mas_plata_en_CuentaCorriente() {
		CuentaCorriente c1 = new CuentaCorriente(1, "usuario1",100,100);
		c1.retirar(201);
		assertEquals(200,c1.getSaldoTotal());
	}
	
	/*
	 --------------------------------------------------------------------
	 -------------------------TEST TRANSFERENCIA-------------------------
	 --------------------------------------------------------------------
	 */
	
	@Test
	@DisplayName("Un usuario con la cuenta deshabilitada transfiere plata a otro usuario")
	void test_trasnferencia_pero_la_cuenta_origen_no_esta_habilitada() {
		CuentaCajaAhorro c1 = new CuentaCajaAhorro(1, "usuario1",100);
		CuentaCajaAhorro c2 = new CuentaCajaAhorro(2, "usuario2", 100);
		c1.setHabilitada(false);
		c1.transferencia(c2, 10,0);
		assertEquals(100,c1.getSaldoTotal());
		assertEquals(100,c2.getSaldoTotal());
	}
	
	@Test
	@DisplayName("Un usuario transfiere plata a otro usuario que tiene la cuenta deshabilitada")
	void test_trasnferencia_pero_la_cuenta_destino_no_esta_habilitada() {
		CuentaCajaAhorro c1 = new CuentaCajaAhorro(1, "usuario1",100);
		CuentaCajaAhorro c2 = new CuentaCajaAhorro(2, "usuario2", 100);
		c2.setHabilitada(false);
		c1.transferencia(c2, 10,0);
		assertEquals(100,c1.getSaldoTotal());
		assertEquals(100,c2.getSaldoTotal());
	}
	
	@Test
	@DisplayName("Un usuario transfiere plata a su misma cuenta")
	void test_trasnferencia_donde_la_cuenta_origen_y_destino_son_iguales() {
		CuentaCajaAhorro c1 = new CuentaCajaAhorro(1, "usuario1",100);
		c1.transferencia(c1, 10,0);
		assertEquals(100,c1.getSaldoTotal());
	}
	
	@Test
	@DisplayName("Un usuario de caja ahorro transfiere plata a otro usuario de caja ahorro ")
	void test_trasnferencia_entre_caja_ahorro() {
		CuentaCajaAhorro c1 = new CuentaCajaAhorro(1, "usuario1",100);
		CuentaCajaAhorro c2 = new CuentaCajaAhorro(2, "usuario2", 100);
		c1.transferencia(c2, 10,0);
		assertEquals(90,c1.getSaldoTotal());
		assertEquals(110,c2.getSaldoTotal());
	}
	
	@Test
	@DisplayName("Un usuario de caja ahorro transfiere plata a otro usuario de caja ahorro pero no tiene plata para realizar la transferencia")
	void test_trasnferencia_entre_caja_ahorro_fondo_insuficientes() {
		CuentaCajaAhorro c1 = new CuentaCajaAhorro(1, "usuario1",100);
		CuentaCajaAhorro c2 = new CuentaCajaAhorro(2, "usuario2", 100);
		c1.transferencia(c2, 110,0);
		assertEquals(100,c1.getSaldoTotal());
		assertEquals(100,c2.getSaldoTotal());
	}
	
	@Test
	@DisplayName("Un usuario de caja ahorro transfiere plata a otro usuario de cuenta corriente pero no tiene plata para pagar la comision")
	void test_trasnferencia_de_caja_ahorro_a_cuenta_corriente_pero_fondos_insuficientes_para_pagar_la_comision_() {
		CuentaCajaAhorro c1 = new CuentaCajaAhorro(1, "usuario1",100);
		CuentaCorriente c2 = new CuentaCorriente(2, "usuario2", 100,100);
		c1.transferencia(c2, 100,0.15);
		assertEquals(100,c1.getSaldoTotal());
		assertEquals(200,c2.getSaldoTotal());
	}
	
	@Test
	@DisplayName("Un usuario de caja ahorro transfiere plata a otro usuario de cuenta corriente")
	void test_trasnferencia_de_caja_ahorro_a_cuenta_corriente() {
		CuentaCajaAhorro c1 = new CuentaCajaAhorro(1, "usuario1",100);
		CuentaCorriente c2 = new CuentaCorriente(2, "usuario2", 100,100);
		c1.transferencia(c2, 50,0.15);
		assertEquals(42.5,c1.getSaldoTotal());
		assertEquals(250,c2.getSaldoTotal());
	}
	
	@Test
	@DisplayName("Un usuario de cuenta corriente transfiere plata a otro usuario de cuenta corriente")
	void test_trasnferencia_entre_cuenta_corriente() {
		CuentaCorriente c1 = new CuentaCorriente(1, "usuario1",100,100);
		CuentaCorriente c2 = new CuentaCorriente(2, "usuario2", 100,100);
		c1.transferencia(c2, 100,0);
		assertEquals(100,c1.getSaldoTotal());
		assertEquals(300,c2.getSaldoTotal());
	}
	
	@Test
	@DisplayName("Un usuario de cuenta corriente transfiere plata a otro usuario de cuenta corriente usando el descubierto")
	void test_trasnferencia_entre_cuenta_corriente_usando_el_descubierto() {
		CuentaCorriente c1 = new CuentaCorriente(1, "usuario1",100,100);
		CuentaCorriente c2 = new CuentaCorriente(2, "usuario2", 100,100);
		c1.transferencia(c2, 150,0);
		assertEquals(50,c1.getSaldoTotal());
		assertEquals(350,c2.getSaldoTotal());
	}
	
	@Test
	@DisplayName("Un usuario de cuenta corriente transfiere plata a otro usuario de cuenta corriente pero no tiene fondos")
	void test_trasnferencia_entre_cuenta_corriente_pero_no_tiene_fondos() {
		CuentaCorriente c1 = new CuentaCorriente(1, "usuario1",100,100);
		CuentaCorriente c2 = new CuentaCorriente(2, "usuario2", 100,100);
		c1.transferencia(c2, 350,0);
		assertEquals(200,c1.getSaldoTotal());
		assertEquals(200,c2.getSaldoTotal());
	}
	
	@Test
	@DisplayName("Un usuario de cuenta corriente transfiere plata a otro usuario de cuenta corriente pero no tiene fondos")
	void test_trasnferencia_de_cuenta_corriente_a_caja_ahorro_pero_no_tiene_fondos() {
		CuentaCorriente c1 = new CuentaCorriente(1, "usuario1",100,100);
		CuentaCajaAhorro c2 = new CuentaCajaAhorro(2, "usuario2", 100);
		c1.transferencia(c2, 350,0.3);
		assertEquals(200,c1.getSaldoTotal());
		assertEquals(100,c2.getSaldoTotal());
	}
	
	@Test
	@DisplayName("Un usuario de cuenta corriente transfiere plata a otro usuario de cuenta corriente pero no tiene fondos para pagar la comision")
	void test_trasnferencia_de_cuenta_corriente_a_caja_ahorro_pero_no_tiene_fondos_para_pagar_comision() {
		CuentaCorriente c1 = new CuentaCorriente(1, "usuario1",100,100);
		CuentaCajaAhorro c2 = new CuentaCajaAhorro(2, "usuario2", 100);
		c1.transferencia(c2, 200,0.3);
		assertEquals(200,c1.getSaldoTotal());
		assertEquals(100,c2.getSaldoTotal());
	}
	
	@Test
	@DisplayName("Un usuario de cuenta corriente transfiere plata a otro usuario de cuenta corriente")
	void test_trasnferencia_de_cuenta_corriente_a_caja_ahorro() {
		CuentaCorriente c1 = new CuentaCorriente(1, "usuario1",100,100);
		CuentaCajaAhorro c2 = new CuentaCajaAhorro(2, "usuario2", 100);
		c1.transferencia(c2, 50,0.3);
		assertEquals(135,c1.getSaldoTotal());
		assertEquals(150,c2.getSaldoTotal());
	}
	
	/*
	 --------------------------------------------------------------------
	 -------------------------TEST PRESTAMOS-----------------------------
	 --------------------------------------------------------------------
	 */
	
	@Test
	@DisplayName("Un banco quiere dar prestamos pero ninguna cuenta cumple con el requisito de saldo>10000")
	void test_banco_da_prestamo_pero_ninguna_tiene_saldo_de_10000() {
		CuentaCorriente c1 = new CuentaCorriente(1, "usuario1",100,100);
		CuentaCajaAhorro c2 = new CuentaCajaAhorro(2, "usuario2", 100);
		CuentaCorriente c3 = new CuentaCorriente(3, "usuario3",100,100);
		CuentaCajaAhorro c4 = new CuentaCajaAhorro(4, "usuario4", 100);
		CuentaCorriente c5 = new CuentaCorriente(5, "usuario5",100,100);
		CuentaCajaAhorro c6 = new CuentaCajaAhorro(6, "usuario6", 100);
		ArrayList<CuentaBancaria> lc = new ArrayList<CuentaBancaria>();
		lc.add(c1);
		lc.add(c2);
		lc.add(c3);
		lc.add(c4);
		lc.add(c5);
		lc.add(c6);
		Banco b = new Banco("banco1", lc);
		ArrayList<String> aux = b.obtenerTitularesAptosParaPrestamos();
		assertEquals(0,aux.size());
	}
	
	@Test
	@DisplayName("Un banco quiere dar prestamos pero ninguna cuenta esta habilitada")
	void test_banco_da_prestamo_pero_ninguna_esta_habilitada() {
		CuentaCorriente c1 = new CuentaCorriente(1, "usuario1",100,100);
		CuentaCajaAhorro c2 = new CuentaCajaAhorro(2, "usuario2", 100);
		CuentaCorriente c3 = new CuentaCorriente(3, "usuario3",100,100);
		CuentaCajaAhorro c4 = new CuentaCajaAhorro(4, "usuario4", 100);
		CuentaCorriente c5 = new CuentaCorriente(5, "usuario5",100,100);
		CuentaCajaAhorro c6 = new CuentaCajaAhorro(6, "usuario6", 100);
		ArrayList<CuentaBancaria> lc = new ArrayList<CuentaBancaria>();
		c1.setHabilitada(false);
		c2.setHabilitada(false);
		c3.setHabilitada(false);
		c4.setHabilitada(false);
		c5.setHabilitada(false);
		c6.setHabilitada(false);
		lc.add(c1);
		lc.add(c2);
		lc.add(c3);
		lc.add(c4);
		lc.add(c5);
		lc.add(c6);
		Banco b = new Banco("banco1", lc);
		ArrayList<String> aux = b.obtenerTitularesAptosParaPrestamos();
		assertEquals(0,aux.size());
	}
	
	@Test
	@DisplayName("Un banco quiere dar prestamos pero todas las cuentas son iguales")
	void test_banco_da_prestamo_con_cuentas_repetidas() {
		CuentaCorriente c1 = new CuentaCorriente(1, "usuario1",100000,100);
		CuentaCajaAhorro c2 = new CuentaCajaAhorro(2, "usuario1", 100000);
		CuentaCorriente c3 = new CuentaCorriente(3, "usuario1",100000,100);
		CuentaCajaAhorro c4 = new CuentaCajaAhorro(4, "usuario1", 100000);
		CuentaCorriente c5 = new CuentaCorriente(5, "usuario1",100000,100);
		CuentaCajaAhorro c6 = new CuentaCajaAhorro(6, "usuario1", 100000);
		ArrayList<CuentaBancaria> lc = new ArrayList<CuentaBancaria>();
		lc.add(c1);
		lc.add(c2);
		lc.add(c3);
		lc.add(c4);
		lc.add(c5);
		lc.add(c6);
		Banco b = new Banco("banco1", lc);
		ArrayList<String> aux = b.obtenerTitularesAptosParaPrestamos();
		assertEquals(1,aux.size());
	}
	
	@Test
	@DisplayName("Un banco quiere dar prestamos")
	void test_banco_da_prestamo() {
		CuentaCorriente c1 = new CuentaCorriente(1, "usuario1",100000,100);
		CuentaCajaAhorro c2 = new CuentaCajaAhorro(2, "usuario2", 100000);
		CuentaCorriente c3 = new CuentaCorriente(3, "usuario3",500,100);
		CuentaCajaAhorro c4 = new CuentaCajaAhorro(4, "usuario4", 100000);
		CuentaCorriente c5 = new CuentaCorriente(5, "usuario5",100000,100);
		CuentaCajaAhorro c6 = new CuentaCajaAhorro(6, "usuario6", 0);
		ArrayList<CuentaBancaria> lc = new ArrayList<CuentaBancaria>();
		c4.setHabilitada(false);
		c5.setHabilitada(false);
		lc.add(c1);
		lc.add(c2);
		lc.add(c3);
		lc.add(c4);
		lc.add(c5);
		lc.add(c6);
		Banco b = new Banco("banco1", lc);
		ArrayList<String> aux = b.obtenerTitularesAptosParaPrestamos();
		assertEquals(2,aux.size());
	}
	
	/*
	 --------------------------------------------------------------------
	 -------------------------TEST HACKEO--------------------------------
	 --------------------------------------------------------------------
	 */
	
	@Test
	@DisplayName("Un banco quiere ver vulnerabilidad pero ninguna cuenta tiene saldo > 50000")
	void test_banco_ver_vulnerabilidad_pero_ninguna_tiene_saldo_de_50000() {
		CuentaCorriente c1 = new CuentaCorriente(1, "usuario1usuario",100,100);
		CuentaCajaAhorro c2 = new CuentaCajaAhorro(2, "usuario2usuario", 100);
		CuentaCorriente c3 = new CuentaCorriente(3, "usuario3usuario",100,100);
		CuentaCajaAhorro c4 = new CuentaCajaAhorro(4, "usuario4usuario", 100);
		CuentaCorriente c5 = new CuentaCorriente(5, "usuario5usuario",100,100);
		CuentaCajaAhorro c6 = new CuentaCajaAhorro(6, "usuario6usuario", 100);
		ArrayList<CuentaBancaria> lc = new ArrayList<CuentaBancaria>();
		lc.add(c1);
		lc.add(c2);
		lc.add(c3);
		lc.add(c4);
		lc.add(c5);
		lc.add(c6);
		Banco b = new Banco("banco1", lc);
		assertFalse(b.algunasCuentasPuedenSerHackeada());
	}
	
	@Test
	@DisplayName("Un banco quiere ver vulnerabilidad pero ninguna cuenta es par")
	void test_banco_ver_vulnerabilidad_pero_ninguna_es_par() {
		CuentaCorriente c1 = new CuentaCorriente(1, "usuario1usuario",50100,100);
		CuentaCajaAhorro c2 = new CuentaCajaAhorro(7, "usuario2usuario", 50100);
		CuentaCorriente c3 = new CuentaCorriente(3, "usuario3usuario",50100,100);
		CuentaCajaAhorro c4 = new CuentaCajaAhorro(9, "usuariousuario4", 50100);
		CuentaCorriente c5 = new CuentaCorriente(5, "usuario5usuario",150000,100);
		CuentaCajaAhorro c6 = new CuentaCajaAhorro(11, "usuario6usuario", 50100);
		ArrayList<CuentaBancaria> lc = new ArrayList<CuentaBancaria>();
		lc.add(c1);
		lc.add(c2);
		lc.add(c3);
		lc.add(c4);
		lc.add(c5);
		lc.add(c6);
		Banco b = new Banco("banco1", lc);
		assertFalse(b.algunasCuentasPuedenSerHackeada());
	}
	
	@Test
	@DisplayName("Un banco quiere ver vulnerabilidad pero ninguna cuenta tiene titular > 15 letras")
	void test_banco_ver_vulnerabilidad_pero_ninguna_tiene_titular_mayor_a_15() {
		CuentaCorriente c1 = new CuentaCorriente(1, "usuario1",50100,100);
		CuentaCajaAhorro c2 = new CuentaCajaAhorro(2, "usuario2", 50100);
		CuentaCorriente c3 = new CuentaCorriente(3, "usuario3",50100,100);
		CuentaCajaAhorro c4 = new CuentaCajaAhorro(4, "usuario4", 50100);
		CuentaCorriente c5 = new CuentaCorriente(5, "usuario5",150000,100);
		CuentaCajaAhorro c6 = new CuentaCajaAhorro(6, "usuario6", 50100);
		ArrayList<CuentaBancaria> lc = new ArrayList<CuentaBancaria>();
		lc.add(c1);
		lc.add(c2);
		lc.add(c3);
		lc.add(c4);
		lc.add(c5);
		lc.add(c6);
		Banco b = new Banco("banco1", lc);
		assertFalse(b.algunasCuentasPuedenSerHackeada());
	}
	
	@Test
	@DisplayName("Un banco quiere ver vulnerabilidad y un usuario es vulnerable")
	void test_banco_vulnerabilidad(){
		CuentaCorriente c1 = new CuentaCorriente(1, "usuario1usuario",555555,555555);
		CuentaCajaAhorro c2 = new CuentaCajaAhorro(2, "usuario2uusuario", 50100);
		CuentaCorriente c3 = new CuentaCorriente(3, "usuario3",5100,100);
		CuentaCajaAhorro c4 = new CuentaCajaAhorro(4, "usuario4usuuario", 100);
		CuentaCorriente c5 = new CuentaCorriente(5, "usuario5",150000,100);
		CuentaCajaAhorro c6 = new CuentaCajaAhorro(6, "usuario6", 50100);
		ArrayList<CuentaBancaria> lc = new ArrayList<CuentaBancaria>();
		lc.add(c1);
		lc.add(c2);
		lc.add(c3);
		lc.add(c4);
		lc.add(c5);
		lc.add(c6);
		Banco b = new Banco("banco1", lc);
		assertTrue(b.algunasCuentasPuedenSerHackeada());
	}
}
