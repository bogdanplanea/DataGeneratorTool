package ro.starstorage.datageneratortool.ui;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ro.starstorage.datageneratortool.jdbc.ConnectionFactory;
import ro.starstorage.datageneratortool.jdbc.DbUtil;
import ro.starstorage.datagenratortool.random.RandomTokens;

public class GenerateFile {

	private static final Logger LOG = LogManager.getLogger(GenerateFile.class.getName());

	public static void generateFile() throws ParseException {

		String inputText = UserInterface.getTextAreaInput();
		// String fisierContinut =UserInterface.getComboBoxFisierContinut();
		int numarRepetari = UserInterface.getTextFieldNrRepetari();

		String[] listaValori = new String[numarRepetari];

		for (int i = 0; i < numarRepetari; i++) {

			listaValori[i] = replaceTokensWithRandom(inputText, i);
		}

		String tipExport = UserInterface.getComboBoxTipExport();

		if (tipExport == "Fisier .CSV") {
			generateCSVFile(listaValori);
		} else if (tipExport == "Script .SQL") {
			generateSQLFile(listaValori);
		} else if (tipExport == "Script .SQL si executie in BD") {
			generateSQLFileAndExecute(listaValori);
		}

		// if(fisierContinut == "Da"){
		//
		// }

	}

	public static void generateFolder(String[] lista) {

		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/Source/filename.sql"), "utf-8"));
			for (int i = 0; i < lista.length; i++) {
				writer.write(lista[i]);
				((BufferedWriter) writer).newLine();

			}
		} catch (IOException ex) {
			LOG.error("A aparut o eroare la deschiderea fisierului sql.");
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception ex) {
					LOG.error("A aparut o eroare la inchiderea fisierului sql.");
				}
			}
		}
	}

	public static String replaceTokensWithSameRandom(String text) {

		String finalText = text.replace("'TK_Valori Bool'", "'" + RandomTokens.generateRandomBool() + "'")
		        .replace("'TK_Nume'", "'" + RandomTokens.generateRandomName() + "'")
		        .replace("'TK_CNP'", "'" + RandomTokens.generateRandomCNP() + "'")
		        .replace("'TK_Numar Intreg'", "'" + RandomTokens.generateRandomInt() + "'")
		        .replace("'TK_Numar Intreg Pozitiv'", "'" + RandomTokens.generateRandomIntP() + "'")
		        .replace("'TK_Numar Real'", "'" + RandomTokens.generateRandomReal() + "'")
		        .replace("'TK_Numar Real Pozitiv'", "'" + RandomTokens.generateRandomRealP() + "'")
		        .replace("'TK_Sex'", "'" + RandomTokens.generateRandomGender() + "'")
		        .replace("'TK_E-mail'", "'" + RandomTokens.generateRandomEmail() + "'")
		        .replace("'TK_Caractere speciale'", "'" + RandomTokens.generateRandomSpecials() + "'");
		LOG.debug("finalText = {}", finalText);
		return finalText;
	}

	public static String replaceTokensWithRandom(String text, int repetare) throws ParseException {
		String finalText = text;
		LOG.debug("Inainte de inlocuirea tokenurilor");

		while (isTokenPresentInText(finalText)) {
			LOG.debug("Inlocuiesc tokenurile");
			finalText = finalText.replaceFirst("TK_Valori Bool", RandomTokens.generateRandomBool())
			        .replaceFirst("TK_Nume", RandomTokens.generateRandomName()).replaceFirst("TK_CNP", RandomTokens.generateRandomCNP())
			        .replaceFirst("TK_Numar Intreg", RandomTokens.generateRandomInt())
			        .replaceFirst("TK_Numar Intreg Pozitiv", RandomTokens.generateRandomIntP())
			        .replaceFirst("TK_Numar Real", RandomTokens.generateRandomReal())
			        .replaceFirst("TK_Numar Real Pozitiv", RandomTokens.generateRandomRealP())
			        .replaceFirst("TK_Sex", RandomTokens.generateRandomGender()).replaceFirst("TK_E-mail", RandomTokens.generateRandomEmail())
			        .replaceFirst("TK_Caractere speciale", RandomTokens.generateRandomSpecials())
			        .replaceFirst("TK_ID", RandomTokens.generateID(repetare)).replaceFirst("TK_Set Lista", RandomTokens.generateSetNames())
					.replaceFirst("TK_Data", RandomTokens.generateRandomDate()).replaceFirst("TK_Data", RandomTokens.generateRandomDate());
		}
		LOG.debug("Textul dupa inlocuirea tokenurilor = {}", finalText);

		return finalText;

	}

	private static boolean isTokenPresentInText(String textToken) {

		return textToken.contains("TK_Valori Bool") || textToken.contains("TK_Nume") || textToken.contains("TK_CNP")
		        || textToken.contains("TK_Numar Intreg") || textToken.contains("TK_Numar Intreg Pozitiv") || textToken.contains("TK_Numar Real")
		        || textToken.contains("TK_Numar Real Pozitiv") || textToken.contains("TK_Sex") || textToken.contains("TK_E-mail")
		        || textToken.contains("TK_Caractere speciale") || textToken.contains("TK_ID") || textToken.contains("TK_Set Lista") || textToken.contains("TK_Data");
	}

	public static void generateSQLFile(String[] lista) {

		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(System.getProperty("user.dir") + "//results//filename1.sql"),
			        "utf-8"));
			if (!"".equals(UserInterface.getTextAreaInput())) {
				LOG.debug("Am gasit text in campul general.");
			}
			writer.write(UserInterface.getTextAreaGeneral());
			for (int i = 0; i < lista.length; i++) {
				writer.write(lista[i]);
				((BufferedWriter) writer).newLine();

			}
		} catch (IOException ex) {
			LOG.error("A aparut o eroare la deschiderea fisierului sql.");
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception ex) {
					LOG.error("A aparut o eroare la inchiderea fisierului sql.");
				}
			}
		}
	}

	public static void generateCSVFile(String[] lista) {

		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(System.getProperty("user.dir") + "//results//filename1.csv"),
			        "utf-8"));
			LOG.debug("Verific daca text pentru campul general.");
			LOG.debug("Textul din campul general este = '{}'", UserInterface.getTextAreaGeneral());
			if (!"".equals(UserInterface.getTextAreaGeneral())) {
				LOG.debug("Am gasit text in campul general.");
				writer.append(UserInterface.getTextAreaGeneral() + "\n");
			}

			for (int i = 0; i < lista.length; i++) {
				writer.write(lista[i]);
				((BufferedWriter) writer).newLine();
			}

		} catch (IOException ex) {
			LOG.error("A aparut o eroare la deschiderea fisierului csv.");
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception ex) {
					LOG.error("A aparut o eroare la inchiderea fisierului.");
				}
			}
		}
	}

	public static void generateSQLFileAndExecute(String[] lista) {
		Connection connection = null;
		Statement statement = null;

		generateSQLFile(lista);

		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			LOG.debug("Inainte de primul for");

			for (int i = 0; i < lista.length; i++) {
				LOG.debug("Dupa primul for cu valoarea= '{}'", lista[i]);

				String[] split = lista[i].split(";\\r\\n");
				for (int j = 0; j < split.length; j++) {
					LOG.debug("split = '{}'", split[j]);
					statement.addBatch(split[j]);
				}

			}

			statement.executeBatch();

		} catch (SQLException e) {
			LOG.error("Nu se poate conecta la baza de date.", e);
		} finally {

			DbUtil.close(statement);
			DbUtil.close(connection);
		}
	}
}
