/*  OpenMorse - An android morse to text on-the-fly converter
 *  
   	Copyright (C) 2010 Samuel Skånberg

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.example.morse;

public class Morse {
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
	
	public static int SHORT_GAP_TIME = UNIT_TIME*3;
	
	public static int MEDIUM_GAP_TIME = UNIT_TIME*7;
	
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
