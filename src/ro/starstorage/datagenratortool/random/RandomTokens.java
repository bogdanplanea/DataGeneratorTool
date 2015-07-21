package ro.starstorage.datagenratortool.random;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ro.starstorage.datageneratortool.ui.GenerateFile;
import ro.starstorage.datageneratortool.ui.UserInterface;

public class RandomTokens {

	private static final Logger LOG = LogManager.getLogger(RandomTokens.class.getName());

	public static String generateRandomInt() {

		int nrInt;

		Random randInt = new Random();
		nrInt = randInt.nextInt();

		return Integer.toString(nrInt);
	}

	public static String generateRandomIntP() {
		int nrIntP;

		Random randInt = new Random();
		nrIntP = randInt.nextInt(Integer.MAX_VALUE) + 1;

		return Integer.toString(nrIntP);
	}

	public static String generateRandomReal() {
		double nrReal;

		Random randReal = new Random();
		nrReal = randReal.nextDouble();

		return Double.toString(nrReal);
	}

	public static String generateRandomRealP() {
		double nrRealP;

		Random randRealP = new Random();
		nrRealP = 1 + (Double.MAX_VALUE - 1) * randRealP.nextDouble();

		return Double.toString(nrRealP);
	}

	public static String generateRandomGender() {
		char ranGender;

		Random Gender = new Random();
		ranGender = Gender.nextBoolean() ? 'M' : 'F';
		return String.valueOf(ranGender);
	}

	public static String generateRandomName() {

		Properties configFile = new Properties();

		try {
			StringBuilder ranName = new StringBuilder();
			String ranCap = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			String ranNCap = "abcdefghijklmnopqrstuvwxyz";
			Random rnd = new Random();
			Random rnd1 = new Random();
			int randSLength1 = rnd1.nextInt(20);
			configFile.load(RandomTokens.class.getClassLoader().getResourceAsStream("token.properties"));
			String special = configFile.getProperty("caractere_speciale");

			StringBuilder sbCap = new StringBuilder(1);
			sbCap.append(ranCap.charAt((rnd.nextInt(ranCap.length()))));
			String firstChar = sbCap.toString();

			StringBuilder sbCapLast = new StringBuilder(1);
			sbCapLast.append(ranNCap.charAt((rnd.nextInt(ranNCap.length()))));
			String lastChar = sbCapLast.toString();

			StringBuilder sbCap1 = new StringBuilder(1);
			sbCap1.append(ranCap.charAt((rnd.nextInt(ranCap.length()))));
			String firstChar1 = sbCap1.toString();

			StringBuilder specialChar = new StringBuilder(randSLength1);
			for (int i = 0; i < randSLength1; i++) {
				specialChar.append((special + ranNCap).charAt(rnd.nextInt((special + ranNCap).length())));

			}
			String xy = specialChar.toString();

			String xyFinal = xy.replaceAll("(')\\1+", "$1");

			StringBuilder specialChar1 = new StringBuilder(randSLength1);
			for (int i = 0; i < randSLength1; i++) {
				specialChar1.append((special + ranNCap).charAt(rnd.nextInt((special + ranNCap).length())));
			}
			String yx = specialChar1.toString();

			String yxFinal = yx.replaceAll("(.)\\1+", "$1");

			StringBuilder sbCapLast1 = new StringBuilder(1);
			sbCapLast1.append(ranNCap.charAt((rnd.nextInt(ranNCap.length()))));
			String lastChar2 = sbCapLast1.toString();

			return ranName.append(firstChar).append(xyFinal).append(lastChar).append(" ").append(firstChar1).append(yxFinal).append(lastChar2).toString();
		} catch (IOException e) {
			LOG.debug("Something went wrong generating random name.. ");

		}
		return null;

	}

	public static String generateRandomCNP() {
		String CNP = "";
		String CNPtwelvechars = "";
		String sString = "";
		String yearString = "";
		String monthString = "";
		String dayString = "";
		String orderString = "";
		String judString = "";
		int[] stdCNP = new int[] { 2, 7, 9, 1, 4, 6, 3, 5, 8, 2, 7, 9 };
		Random sChar = new Random();
		int controlNumber = 0;

		int s = 1 + sChar.nextInt(11 - 5 + 2 - 1);
		if (s > 2) {
			s += (5 - 3);
		}

		Random yearChar = new Random();
		int year = yearChar.nextInt(99) + 1;

		Random monthChar = new Random();
		int month = monthChar.nextInt(12) + 1;

		Random dayChar = new Random();
		int day = dayChar.nextInt(28) + 1;

		Random judChar = new Random();
		int jud = judChar.nextInt(52) + 1;

		Random orderNum = new Random();
		int order = orderNum.nextInt(999) + 1;

		sString = sString + String.valueOf(s);

		if (year < 10) {
			yearString = "0";
			yearString = yearString + String.valueOf(year);
		} else {
			yearString = yearString + String.valueOf(year);
		}

		if (month < 10) {
			monthString = "0";
			monthString = monthString + String.valueOf(month);
		} else {
			monthString = monthString + String.valueOf(month);
		}

		if (day < 10) {
			dayString = "0";
			dayString = dayString + String.valueOf(day);
		} else {
			dayString = dayString + String.valueOf(day);
		}

		if (jud < 10) {
			judString = "0";
			judString = judString + String.valueOf(jud);
		} else {
			judString = judString + String.valueOf(jud);
		}

		if (order < 10) {
			orderString = "00";
			orderString = orderString + String.valueOf(order);
		} else if (order < 100) {
			orderString = "0";
			orderString = orderString + String.valueOf(order);
		} else {
			orderString = orderString + String.valueOf(order);
		}

		CNPtwelvechars = sString + yearString + monthString + dayString + judString + orderString;

		for (int i = 0; i <= 11; i++) {
			controlNumber += Character.getNumericValue(CNPtwelvechars.charAt(i)) * stdCNP[i];
		}
		controlNumber = controlNumber % 11;
		if (controlNumber == 10) {
			controlNumber = 1;
		}
		CNP = CNP + CNPtwelvechars + String.valueOf(controlNumber);

		return CNP;
	}

	public static String generateRandomBool() {
		Properties configFile = new Properties();

		try {

			Random rtfm = new Random();
			configFile.load(RandomTokens.class.getClassLoader().getResourceAsStream("token.properties"));
			String booleanValue = configFile.getProperty("valori_boolene");
			String[] split = booleanValue.split("::");

			int valBool = rtfm.nextInt(split.length);
			return split[valBool];

		} catch (IOException e1) {
			LOG.debug("Something went wrong generating random bool..");
		}
		return null;
	}

	public static String generateRandomDate() throws ParseException {
		Properties configFile = new Properties();

		try {
			String yearString = "";
			String monthString = "";
			String dayString = "";
			Random yearChar2 = new Random();
			int year = yearChar2.nextInt(2015 - 1900) + 1900;

			Random monthChar2 = new Random();
			int month = monthChar2.nextInt(12) + 1;

			Random dayChar2 = new Random();
			int day = dayChar2.nextInt(28) + 1;

			if (year < 10) {
				yearString = "0";
				yearString = yearString + String.valueOf(year);
			} else {
				yearString = yearString + String.valueOf(year);
			}

			if (month < 10) {
				monthString = "0";
				monthString = monthString + String.valueOf(month);
			} else {
				monthString = monthString + String.valueOf(month);
			}

			if (day < 10) {
				dayString = "0";
				dayString = dayString + String.valueOf(day);
			} else {
				dayString = dayString + String.valueOf(day);
			}

			configFile.load(RandomTokens.class.getClassLoader().getResourceAsStream("token.properties"));
			String formatData = configFile.getProperty("data_format");

			DateFormat outputFormat = new SimpleDateFormat(formatData);
			DateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");

			String inputText = yearString + monthString + dayString;
			Date date = inputFormat.parse(inputText);
			String outputText = outputFormat.format(date);

			return outputText;

		} catch (IOException e2) {
			LOG.debug("Something went wrong generating random data");
		}
		return null;
	}

	public static String generateRandomEmail() {
		Properties configFile = new Properties();
		try {
			Random rnd1 = new Random();
			Random rnd2 = new Random();
			Random rnd3 = new Random();
			configFile.load(RandomTokens.class.getClassLoader().getResourceAsStream("token.properties"));
			String domain = configFile.getProperty("email_domain");
			String ranFirstLastLetter = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVXYZ1234567890";
			String special = ".";
			String[] split = domain.split("::");
			int domainPick = rnd3.nextInt(split.length);
			String domainFinal = split[domainPick];
			int randEmailLength = rnd1.nextInt(15) + 6;

			StringBuilder ranEmail = new StringBuilder();
			for (int i = 0; i < randEmailLength; i++) {
				ranEmail.append((ranFirstLastLetter + special).charAt(rnd1.nextInt((ranFirstLastLetter + special).length())));
			}
			String xy = ranEmail.toString();

			String xyFinal = xy.replaceAll("(\\.)\\1+", "$1");

			StringBuilder ranEmailLastLetter = new StringBuilder(1);
			ranEmailLastLetter.append((ranFirstLastLetter).charAt(rnd1.nextInt((ranFirstLastLetter).length())));

			String lastLetter = ranEmailLastLetter.toString();

			StringBuilder ranEmailFirstLetter = new StringBuilder(1);
			ranEmailFirstLetter.append((ranFirstLastLetter).charAt(rnd2.nextInt((ranFirstLastLetter).length())));

			String firstLetter = ranEmailFirstLetter.toString();

			StringBuilder finalEmail = new StringBuilder();

			return finalEmail.append(firstLetter).append(xyFinal).append(lastLetter).append(domainFinal).toString();
		} catch (Exception e4) {
			LOG.debug("Something went wrong generating a random email...");
		}

		return null;
	}

	public static String generateRandomSpecials() {
		Properties configFile = new Properties();
		try {

			configFile.load(RandomTokens.class.getClassLoader().getResourceAsStream("token.properties"));
			String strLen = configFile.getProperty("special_characters_length");
			String specialChars = configFile.getProperty("special_characters");
			Random rnd1 = new Random();
			int stringLength = Integer.valueOf(strLen);

			StringBuilder caractereRandom = new StringBuilder();
			for (int i = 0; i < stringLength; i++) {
				caractereRandom.append((specialChars).charAt(rnd1.nextInt((specialChars).length())));
			}
			String specialFinal = caractereRandom.toString();

			return specialFinal;
		} catch (IOException e5) {
			LOG.debug("Something went wrong generating a random specials...");
		}

		return null;
	}

	public static String generateSetNames() {
		Properties configFile = new Properties();
		try {

			configFile.load(RandomTokens.class.getClassLoader().getResourceAsStream("token.properties"));
			String setNames = configFile.getProperty("set_names");
			Random rnd1 = new Random();
			String[] split = setNames.split("::");
			int namePick = rnd1.nextInt(split.length);
			String nameFinal = split[namePick];
			return nameFinal;
		} catch (IOException e5) {
			LOG.debug("Something went wrong generating set names...");
		}

		return null;
	}

	public static String generateID(int index) {
		Properties configFile = new Properties();

		try {
			configFile.load(RandomTokens.class.getClassLoader().getResourceAsStream("token.properties"));
			String setID = configFile.getProperty("set_id");
			
			String idConfig =Integer.toString(index + Integer.valueOf(setID)) ;
			
			
			//TODO:implementat logica generare ID
			
			
			
			return idConfig;
					
		} catch (IOException e6) {
			LOG.error("Eroare la citirea fisierului.", e6);
		}
		return null;
		
		
	}

}
