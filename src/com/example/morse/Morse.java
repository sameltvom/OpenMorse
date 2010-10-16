package com.example.morse;

public class Morse {
	public static final int DOT = 0;
	public static final int DASH = 1;
	
	/* From wikipedia article Morse code
	 * 
	 * International Morse code is composed of five elements:
     * - short mark, dot or 'dit' (·) — one unit long
     * - longer mark, dash or 'dah' (–) — three units long
     * - inter-element gap between the dots and dashes within a character — one unit long
     * - short gap (between letters) — three units long
     * - medium gap (between words) — seven units long[11]
	 *
	 */
	
	/* Unit time in ms */
	public static int UNIT_TIME = 200;
	
	/* Is the tone a dot or a dash? */
	public static int dotOrDash(long toneLength) {
		/* If the tone is shorter than UNIT_TIME, it is a dot, otherwise a dash,
		 * actually a dash should be 3 units but whatever... */
		if (toneLength < UNIT_TIME) {
			return DOT;
		} else {
			return DASH;
		}
	}

	/* Get a letter from the morse code in s.
	 * Morse code alphabet taken from
	 * http://en.wikipedia.org/wiki/Morse_code#Letters.2C_numbers.2C_punctuation */
	public static char getLetter(String s) {
		if (s.equals(".-")) {
			return 'A';
		} else if (s.equals("-...")) {
			return 'B';
		} else if (s.equals("-.-.")) {
			return 'C';
		} else if (s.equals("-..")) {
			return 'D';
		} else if (s.equals(".")) {
			return 'E';
		} else if (s.equals("..-.")) {
			return 'F';
		} else if (s.equals("--.")) {
			return 'G';
		} else if (s.equals("....")) {
			return 'H';
		} else if (s.equals("..")) {
			return 'I';
		} else if (s.equals(".---")) {
			return 'J';
		} else if (s.equals("-.-")) {
			return 'K';
		} else if (s.equals(".-..")) {
			return 'L';
		} else if (s.equals("--")) {
			return 'M';
		} else if (s.equals("-.")) {
			return 'N';
		} else if (s.equals("---")) {
			return 'O';
		} else if (s.equals(".--.")) {
			return 'P';
		} else if (s.equals("--.-")) {
			return 'Q';
		} else if (s.equals(".-.")) {
			return 'R';
		} else if (s.equals("...")) {
			return 'S';
		} else if (s.equals("-")) {
			return 'T';
		} else if (s.equals("..-")) {
			return 'U';
		} else if (s.equals("...-")) {
			return 'V';
		} else if (s.equals(".--")) {
			return 'W';
		} else if (s.equals("-..-")) {
			return 'X';
		} else if (s.equals("-.--")) {
			return 'Y';
		} else if (s.equals("--..")) {
			return 'Z';
		} else if (s.equals("-----")) {
			return '0';
		} else if (s.equals(".----")) {
			return '1';
		} else if (s.equals("..---")) {
			return '2';
		} else if (s.equals("...--")) {
			return '3';
		} else if (s.equals("....-")) {
			return '4';
		} else if (s.equals(".....")) {
			return '5';
		} else if (s.equals("-....")) {
			return '6';
		} else if (s.equals("--...")) {
			return '7';
		} else if (s.equals("---..")) {
			return '8';
		} else if (s.equals("----.")) {
			return '9';
		} else if (s.equals(".-.-.-")) {
			return '.';
		} else if (s.equals("--..--")) {
			return ',';
		} else if (s.equals("..--..")) {
			return '?';
		} else if (s.equals(".----.")) {
			return '\'';
		} else if (s.equals("-.-.--")) {
			return '!';
		} else if (s.equals("-..-.")) {
			return '/';
		} else if (s.equals("-.--.")) {
			return '(';
		} else if (s.equals("-.--.-")) {
			return ')';
		} else if (s.equals(".-...")) {
			return '&';
		} else if (s.equals("---...")) {
			return ':';
		} else if (s.equals("-.-.-.")) {
			return ';';
		} else if (s.equals("-...-")) {
			return '=';
		} else if (s.equals(".-.-.")) {
			return '+';
		} else if (s.equals("-....-")) {
			return '-';
		} else if (s.equals("..--.-")) {
			return '_';
		} else if (s.equals(".-..-.")) {
			return '"';
		} else if (s.equals("...-..-")) {
			return '$';
		} else if (s.equals(".--.-.")) {
			return '@';
		} else {
			return '?';
		}
	}
}
