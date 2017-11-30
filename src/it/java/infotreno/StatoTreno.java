package it.java.infotreno;

import java.util.Scanner;
import java.sql.*;

public class StatoTreno extends InfoTrenoAbs {

	public void statoTreno() {

		Scanner input = new Scanner(System.in);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/infotreno", "root", "root");

				Statement s = c.createStatement();

				ResultSet rs = s.executeQuery("SELECT nome_treno FROM treni;");
				System.out.println("In questo istante, stanno marciando i seguenti treni: ");

				while (rs.next()) {
					String listaTreni = rs.getString(1);
					System.out.println(listaTreni);
				}
				System.out.println(" ");
				System.out.println("Inserire il numero di treno per ottenere ulteriori informazioni:");
				String numeroTreno = input.nextLine();

				ResultSet rs2 = s.executeQuery("SELECT\n" + "	treni.nome_treno,\n" + "    treni.ritardo,\n"
						+ "    corse.treno,\n" + "    corse.nome_corsa\n" + "FROM\n" + "	treni\n" + "		JOIN\n"
						+ "	corse ON treni.num_treno = corse.treno\n" + "WHERE\n" + "	num_treno ='" + (numeroTreno)
						+ "';\n" + "	");
				int cnt = 0;
				while (rs2.next()) {
					cnt++;

					String message = "Informazioni sul treno:" + (numeroTreno) + " \n\n" + "Corsa: " + rs2.getString(4)
							+ "\n" + rs2.getString(1) + "\n" + "Eventuale ritardo: " + rs2.getString(2) + "\n\n\n";

					System.out.println(message);

				}
				if (cnt == 0) {
					System.out.println("Il numero immesso non corrisponde a nessun treno");
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
