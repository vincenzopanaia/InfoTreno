package it.java.infotreno;

import java.util.Scanner;

public class InfoTreno implements InfoTrenoInterface {

	public void cercaViaggio() {
	}

	public void statoTreno() {
	}

	public void tabelloneStazione() {
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		int selection = -1;

		System.out.println("\t \t \t \t \t *****INFOTRENO*****");
		System.out.println("\t \t \t \t \t    versione 0.1");
		System.out.println("\t \t      Autori: Alizzi, Marozzo, Panaia, Sestito, Spatari, Vadacchino \n");
		System.out.println("\t \t \t \t \t     Benvenuto!");
		while (selection != 0) {
			System.out.println("Cosa vuoi fare?");
			System.out.println("[1] Cercare un viaggio");
			System.out.println("[2] Controllare lo stato di un treno");
			System.out.println("[3] Controllare i tabelloni arrivi/partenze delle stazioni");
			System.out.println("[0] Uscire da Infotreno \n");

			selection = input.nextInt();

			if (selection == 1) {
				CercaViaggio a = new CercaViaggio();
				a.cercaViaggio();
			}

			else if (selection == 2) {
				StatoTreno b = new StatoTreno();
				b.statoTreno();
			}

			else if (selection == 3) {
				TabelloneStazione c = new TabelloneStazione();
				c.tabelloneStazione();
			} else if (selection == 0) {
			} else {
				System.out.println("Comando non valido");
			}
		}
		System.out.println("\n \n \t \t \t \tGrazie per aver usato InfoTreno!");
		input.close();
	}

}
