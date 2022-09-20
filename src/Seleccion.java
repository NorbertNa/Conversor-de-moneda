import javax.swing.*;
import java.awt.event.*;

public class Seleccion {
	static void CuadroDialogoMonedaInicial(){
		JFrame cuadro = new JFrame("Conversor de Moneda");
		String monedas[] = {"Dolar","Peso (Mexicano)","Euro","Libra","Yen","Won Coreano"};
		JComboBox<String> dialogoMonedaInit = new JComboBox<>(monedas);
		JButton boton = new JButton("Seleccionar");
		final JLabel texto = new JLabel("Selecciona la moneda base");
		texto.setHorizontalAlignment(JLabel.CENTER);
		texto.setSize(400, 100);
		boton.setBounds(50,110,300,20);
		dialogoMonedaInit.setBounds(50,80,300,20);
		cuadro.add(dialogoMonedaInit);
		cuadro.add(boton);
		cuadro.add(texto);
		cuadro.setLayout(null);
		cuadro.setSize(400, 250);
		cuadro.setVisible(true);
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String opcion = dialogoMonedaInit.getItemAt(dialogoMonedaInit.getSelectedIndex());
				cuadro.setVisible(false);
				CuadroDialogoMonedaDos(opcion, dialogoMonedaInit);
			}
		});
	}
	static void CuadroDialogoMonedaDos(String op, JComboBox<String> mon){
		JFrame cuadroDos = new JFrame("Conversor de Moneda");
		JButton boton = new JButton("Seleccionar");
		final JLabel dialogoDos = new JLabel("Selecciona la moneda a convertir");
		dialogoDos.setHorizontalAlignment(JLabel.CENTER);
		dialogoDos.setSize(400,100);
		boton.setBounds(50,110,300,20);
		cuadroDos.add(dialogoDos);
		cuadroDos.add(mon);
		cuadroDos.add(boton);
		cuadroDos.setSize(400,250);
		cuadroDos.setLayout(null);
		cuadroDos.setVisible(true);
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String segundaOpcion = mon.getItemAt(mon.getSelectedIndex());
				cuadroDos.setVisible(false);
				cuadroConversion(op, segundaOpcion);
			}
		});
	}
	static void cuadroConversion(String op, String op2) {
		JFrame cuadroTres = new JFrame("Conversor de Moneda");
		JTextField cantidad = new JTextField(16);
		JButton boton = new JButton("Convertir");
		final JLabel texto = new JLabel("Escribe la cantidad:");
		texto.setBounds(150,50,400,20);
		cantidad.setBounds(50,90,300,20);
		boton.setBounds(50,120,300,20);
		cuadroTres.add(texto);
		cuadroTres.add(cantidad);
		cuadroTres.add(boton);
		cuadroTres.setSize(400,250);
		cuadroTres.setLayout(null);
		cuadroTres.setVisible(true);
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dinero = cantidad.getText();
				cuadroTres.setVisible(false);
				if(verificarNumero(dinero)) {
					Conversion(dinero, op, op2);
				} else {
					Continuar();
				}
			}
		});
		
	}
	static boolean verificarNumero(String dinero) {
		try {
			Double.parseDouble(dinero);
		}
		catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	static void Continuar() {
		JFrame error = new JFrame("Error");
		JLabel continuar = new JLabel("¿Quieres continuar?");
		JButton si = new JButton("Sí");
		JButton no = new JButton("No");
		no.setBounds(150,100,50,20);
		si.setBounds(50,100,50,20);
		continuar.setSize(400,20);
		continuar.setHorizontalAlignment(JLabel.CENTER);
		error.setLayout(null);
		error.setSize(400,250);
		error.add(continuar);
		error.add(si);
		error.add(no);
		error.setVisible(true);
		si.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error.setVisible(false);
				CuadroDialogoMonedaInicial();
			}
		});
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	static void Conversion(String dinero, String op, String op2) {
		/*Matriz 6x6
		 * 
		 * 1 Dolar, 20.04  Peso,   0.99   Euro,  0.87   Libra, 142.83 Yen,   1386.46 Won    Moneda a convertir(x)
		 * 1 Peso,  0.04   Euro,   0.04   Libra, 7.12   Yen,   69.17  Won,   0.04    Dolar
		 * 1 Euro,  0.87   Libra,  143.12 Yen,   1389.1 Won,   1.00   Dolar, 20.07   Peso
		 * 1 Libra, 163.28 Yen,    1584.3 Won,   1.14   Dolar, 22.9   Peso,  1.14    Euro
		 * 1 Yen,   9.7    Won,    0.007  Dolar, 0.14   Peso,  0.007  Euro,  0.006   Libra
		 * 1 Won,   0.0007 Dolar,  0.01   Peso,  0.0007 Euro,  0.0006 Libra, 0.1     Yen
		 *
		*/
		JFrame cuadroFinal = new JFrame("Conversor de monedas");
		JLabel texto = new JLabel("La conversión es: ");
		cuadroFinal.add(texto);
		texto.setHorizontalAlignment(JLabel.CENTER);
		texto.setVerticalAlignment(JLabel.CENTER);
		cuadroFinal.setSize(400,250);
		cuadroFinal.setVisible(true);
		double conversion = 0;
		double res = Double.parseDouble(dinero);
		if(op == "Dolar") {
			switch(op2) {
			case "Dolar":
				conversion = res;
				break;
			case "Peso (Mexicano)":
				conversion = res * 20.04;
				break;
			case "Euro":
				conversion = res * 0.99;
				break;
			case "Libra":
				conversion = res * 0.87;
				break;
			case "Yen":
				conversion = res * 142.83;
				break;
			case "Won":
				conversion = res * 1386.46;
				break;
			}
			
		} else if(op == "Peso (Mexicano)") {
			switch(op2) {
			case "Dolar":
				conversion = res * 0.04;
				break;
			case "Peso (Mexicano)":
				conversion = res;
				break;
			case "Euro":
				conversion = res * 0.04;
				break;
			case "Libra":
				conversion = res * 0.04;
				break;
			case "Yen":
				conversion = res * 7.12;
				break;
			case "Won":
				conversion = res * 69.17;
				break;
			}
		} else if(op == "Euro") {
			switch(op2) {
			case "Dolar":
				conversion = res * 1.00;
				break;
			case "Peso (Mexicano)":
				conversion = res * 20.07;
				break;
			case "Euro":
				conversion = res;
				break;
			case "Libra":
				conversion = res * 0.87;
				break;
			case "Yen":
				conversion = res * 143.12;
				break;
			case "Won":
				conversion = res * 1389.1;
				break;
			}
		}
		else if(op == "Libra") {
			switch(op2) {
			case "Dolar":
				conversion = res * 1.14;
				break;
			case "Peso (Mexicano)":
				conversion = res * 22.9;
				break;
			case "Euro":
				conversion = res * 1.14;
				break;
			case "Libra":
				conversion = res;
				break;
			case "Yen":
				conversion = res * 163.28;
				break;
			case "Won":
				conversion = res * 1584.3;
				break;
			}
		}
		else if(op == "Yen") {
			switch(op2) {
			case "Dolar":
				conversion = res * 0.007;
				break;
			case "Peso (Mexicano)":
				conversion = res * 0.14;
				break;
			case "Euro":
				conversion = res * 0.007;
				break;
			case "Libra":
				conversion = res * 0.006;
				break;
			case "Yen":
				conversion = res;
				break;
			case "Won":
				conversion = res * 9.7;
				break;
			}
		}
		else if(op == "Won") {
			switch(op2) {
			case "Dolar":
				conversion = res * 0.0007;
				break;
			case "Peso (Mexicano)":
				conversion = res * 0.01;
				break;
			case "Euro":
				conversion = res * 0.0007;
				break;
			case "Libra":
				conversion = res * 0.0006;
				break;
			case "Yen":
				conversion = res * 0.1;
				break;
			case "Won":
				conversion = res;
				break;
			}
		}
		texto.setText("La conversión final es: " + conversion);
	}
	public static void main(String[] args) {
		CuadroDialogoMonedaInicial();
	}
}