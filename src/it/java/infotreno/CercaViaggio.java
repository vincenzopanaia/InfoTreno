package it.java.infotreno;

import java.util.Scanner;
import java.sql.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CercaViaggio extends InfoTrenoAbs {

	public void cercaViaggio() {

		Scanner input = new Scanner(System.in);
		Random z = new Random();
		File ricevuta = new File("ricevuta_prenotazione.txt");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				System.out.println("Stazioni disponibili: \n[1] Lamezia Terme Centrale \n" + "[2] Napoli Centrale \n"
						+ "[3] Roma Termini \n" + "[4] Milano Centrale\n");

				System.out.println("Inserisci stazione partenza:");
				String stazionePartenza = input.nextLine();
				System.out.println("Inserisci stazione arrivo:");
				String stazioneArrivo = input.nextLine();
				System.out.println("Inserisci l'orario nel formato hh:mm:");
				String orarioPartenza = input.nextLine();
				Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/infotreno", "root", "root");

				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery("SELECT \n" + "    corse.nome_corsa,\n"
						+ "    stazioni_orari.ora_partenza,\n" + "    fermate_intermedie,\n"
						+ "    ora_fermata_intermedia,\n" + "    stazioni_orari.ora_arrivo\n" + "FROM\n" + "    corse\n"
						+ "        JOIN\n" + "    stazioni_orari ON corse.id_corsa = stazioni_orari.corsa\n" + "WHERE\n"
						+ "    stazione_partenza = '" + (stazionePartenza) + "'\n" + "        AND stazione_arrivo = '"
						+ (stazioneArrivo) + "'\n" + "		 AND ora_partenza >= '" + (orarioPartenza) + "';");

				int cnt = 0;
				while (rs.next()) {
					cnt++;
					String message = "Soluzioni di viaggio: \n" + "Tratta: " + rs.getString(1) + "\n" + "Ora partenza: "
							+ rs.getString(2) + "\n" + "Fermate intermedie: " + rs.getString(3) + " alle ore "
							+ rs.getString(4) + "\n" + "Ora arrivo: " + rs.getString(5);

					System.out.println(message);
					System.out.println("Desideri salvare il file con la ricevuta?");
					System.out.println("[Y] per confermare:");
					String comandoStampa = input.nextLine();

					if (comandoStampa.toLowerCase().charAt(0) == 'y') {
						try {
							ricevuta.createNewFile();
							FileWriter fw = new FileWriter(ricevuta);
							BufferedWriter bw = new BufferedWriter(fw);
							int numeroRicevuta = z.nextInt(99999) + 10000;
							bw.write("Ricevuta di promemoria n°: " + (numeroRicevuta) + "\n" + message);
							bw.flush();
							bw.close();
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							ricevuta = null;
						}
						input.close();
					}

					else {
						input.close();
					}
				}

				if (cnt == 0) {
					System.out.println("Non sono previste soluzioni di viaggio.");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
