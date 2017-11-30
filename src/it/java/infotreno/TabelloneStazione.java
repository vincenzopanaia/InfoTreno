package it.java.infotreno;

import java.sql.*;
import java.util.Scanner;

public class TabelloneStazione extends InfoTrenoAbs {

	public void tabelloneStazione() {

		Scanner input = new Scanner(System.in);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				System.out.println("Stazioni disponibili: \n[1] Lamezia Terme Centrale \n" + "[2] Napoli Centrale \n"
						+ "[3] Roma Termini \n" + "[4] Milano Centrale\n");
				Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/infotreno", "root", "root");

				Statement s = c.createStatement();

				System.out.println("Inserire il numero della stazione:");
				int numStazione = input.nextInt();
				ResultSet rsPartenze = s.executeQuery("SELECT \n"
						+ "    treni.nome_treno, stazioni_orari.ora_partenza, stazioni.nome_stazione\n" + "FROM\n"
						+ "    treni\n" + "        JOIN\n" + "    corse ON treni.num_treno = corse.treno\n"
						+ "        JOIN\n" + "    stazioni_orari ON corse.id_corsa = stazioni_orari.corsa\n"
						+ "		JOIN\n" + "    stazioni ON stazioni.id_stazione = corse.stazione_arrivo    \n"
						+ "WHERE\n" + "    stazione_partenza = '" + (numStazione) + "';");
				System.out.println("\t \t \t \t***PARTENZE***");
				System.out.println("TRENO \t \t \t \t \t \t \t \t  ORARIO \t DESTINAZIONE");

				while (rsPartenze.next()) {
					String tabellonePartenze = rsPartenze.getString(1) + " \t" + rsPartenze.getString(2) + " \t"
							+ rsPartenze.getString(3);
					System.out.println(tabellonePartenze);
				}

				ResultSet rsArrivi = s.executeQuery("SELECT \n"
						+ "    treni.nome_treno, stazioni_orari.ora_partenza, stazioni.nome_stazione\n" + "FROM\n"
						+ "    treni\n" + "        JOIN\n" + "    corse ON treni.num_treno = corse.treno\n"
						+ "        JOIN\n" + "    stazioni_orari ON corse.id_corsa = stazioni_orari.corsa\n"
						+ "		JOIN\n" + "    stazioni ON stazioni.id_stazione = corse.stazione_partenza    \n"
						+ "WHERE\n" + "    stazione_arrivo = '" + (numStazione) + "';");
				System.out.println("\n \t \t \t \t***ARRIVI***");
				System.out.println("TRENO \t \t \t \t \t \t \t \t  ORARIO \t IN ARRIVO DA");

				while (rsArrivi.next()) {
					String tabelloneArrivi = rsArrivi.getString(1) + " \t" + rsArrivi.getString(2) + " \t"
							+ rsArrivi.getString(3);
					System.out.println(tabelloneArrivi);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		input.close();
	}

}
