package training.hexadoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import utils.FileUtils;
import utils.HashUtils;

public class Hexadoku {
	
	/**
	 *	The SHA-256 hash of all the characters composing the complete sudoku will be *the content* of the flag and it needs to be converted to lowercase.
	 *	To get the complete flag, insert the lowercase string obtained between “{FLG:” and “}” without any blank space after the “:” and before the “}”.
	 *	Consider UTF-8 as the encoding character.
	 *	Characters you’re allowed to insert in the puzzle are (0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F).
	 *	Please note that this flag is case sensitive.
	 */
	
	private static final int SUDOKU_SIZE = 16;
	private static final int SUDOKU_BASE = (int) Math.sqrt(SUDOKU_SIZE);
	private static final Set<Character> SUDOKU_VALUES = new TreeSet<>(Arrays.asList('0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'));
	private static final char EMPTY_VALUE = ' ';

	public static void main(String[] args) {
		List<String> file = FileUtils.readFile("training/hexadoku/hexadoku.txt");
		if(file.size() != SUDOKU_SIZE) {
			System.err.println("Invalid sudoku dimension");
			System.exit(1);		}

		Character[][] matrix = new Character[SUDOKU_SIZE][];
		List<List<Set<Character>>> possibilities = new ArrayList<>();
		for(int row = 0; row < SUDOKU_SIZE; row ++) {
			matrix[row] = new Character[SUDOKU_SIZE];
			possibilities.add(new ArrayList<>());
			for(int column = 0; column < SUDOKU_SIZE; column ++) {
				char value = file.get(row).charAt(column);
				matrix[row][column] = value;
				possibilities.get(row).add(new HashSet<>());
				if(EMPTY_VALUE == value) {
					possibilities.get(row).get(column).addAll(SUDOKU_VALUES);
				} else {
					possibilities.get(row).get(column).add(value);
				}
				
			}
		}
		
		checkMatrix(matrix);
		logMatrix(matrix);
		
		int cycles = 0;
		boolean changed = true;
		while(changed) {
			cycles ++;
			changed = false;
			for(int row = 0; row < SUDOKU_SIZE; row ++) {
				for(int column = 0; column < SUDOKU_SIZE; column ++) {
					Set<Character> values = possibilities.get(row).get(column);
					if(values.size() > 1) {
						// Rimuove i valori già presenti nella riga
						values.removeAll(Arrays.asList(matrix[row]));
		
						// Rimuove i valori già presenti nella colonna
						for(int row2 = 0; row2 < SUDOKU_SIZE; row2 ++) {
							values.remove(matrix[row2][column]);
						}
							
						// Rimuove i valori già presenti nel blocco
						for(int row2 = (int) (Math.floor(row / SUDOKU_BASE) * SUDOKU_BASE); Math.floor(row2 / SUDOKU_BASE) == Math.floor(row / SUDOKU_BASE); row2++) {
							for(int column2 = (int) (Math.floor(column / SUDOKU_BASE) * SUDOKU_BASE); Math.floor(column2 / SUDOKU_BASE) == Math.floor(column / SUDOKU_BASE); column2++) {
								values.remove(matrix[row2][column2]);
							}
						}
						
						// Per ogni possibilità, verifica se è l'unica
						for(Character c : values) {
							boolean isUnique = true;
							// Verifica se è l'unica possibilità per quel valore nella riga 
							for(int column2 = 0; column2 < SUDOKU_SIZE; column2 ++) {
								if(column2 != column) {
									isUnique = isUnique && !possibilities.get(row).get(column2).contains(c);
								}
							}

							if(!isUnique) {
								isUnique = true;
								// Verifica se è l'unica possibilità per quel valore nella colonna
								for(int row2 = 0; row2 < SUDOKU_SIZE; row2 ++) {
									if(row2 != row) {
										isUnique = isUnique && !possibilities.get(row2).get(column).contains(c);
									}
								}
							}
							
							if(!isUnique) {
								isUnique = true;
								// Verifica se è l'unica possibilità per quel valore nel blocco
								for(int row2 = (int) (Math.floor(row / SUDOKU_BASE) * SUDOKU_BASE); Math.floor(row2 / SUDOKU_BASE) == Math.floor(row / SUDOKU_BASE); row2++) {
									for(int column2 = (int) (Math.floor(column / SUDOKU_BASE) * SUDOKU_BASE); Math.floor(column2 / SUDOKU_BASE) == Math.floor(column / SUDOKU_BASE); column2++) {
										if(row2 != row || column2 != column) {
											isUnique = isUnique && !possibilities.get(row2).get(column2).contains(c);
										}
									}
								}
							}
							
							if(isUnique) {
								values.removeIf(value -> value != c);
								if(values.size() != 1) {
									System.err.println("Unexpected number of values");
									System.exit(1);
								}
								break;
							}
						}
						
						// Verifica se rimane un solo valore possibile
						if(values.size() == 1) {
							changed = true;
							matrix[row][column] = values.toArray(new Character[0])[0];
							checkMatrix(matrix, row, column);
							checkMatrix(matrix);
						} else if (values.isEmpty()) {
							System.err.println("No value possible for cell: " + row + "," + column);
							System.exit(1);
						}
					}
				}
			}
		}
		
		checkMatrix(matrix);
		System.out.println("Cycles: " + cycles);
		logMatrix(matrix);
		
		StringBuilder sb = new StringBuilder();
		for(int row = 0; row < SUDOKU_SIZE; row ++) {
			for(int column = 0; column < SUDOKU_SIZE; column ++) {
				char solution = matrix[row][column];
				if(solution == EMPTY_VALUE) {
					System.err.println("No solution found");
					System.exit(1);
				}
				sb.append(solution);
			}
		}
		HashUtils.generateFlag(sb.toString());
	}
	
	private static void checkMatrix(Character[][] matrix) {
		for(int row = 0; row < SUDOKU_SIZE; row ++) {
			for(int column = 0; column < SUDOKU_SIZE; column ++) {
				checkMatrix(matrix, row, column);
			}
		}
	}
		
	private static void checkMatrix(Character[][] matrix, int row, int column) {
		char c = matrix[row][column];
		if(c == EMPTY_VALUE) {
			return;
		}
		int count = 0;
		for(int column2 = 0; column2 < SUDOKU_SIZE; column2 ++) {
			if(matrix[row][column2] == c) {
				count ++;
			}
		}
		if(count > 1) {
			System.err.println("Invalid value found!");
			System.exit(1);
		}
		
		count = 0;
		for(int row2 = 0; row2 < SUDOKU_SIZE; row2 ++) {
			if(matrix[row2][column] == c) {
				count ++;
			}
		}
		if(count > 1) {
			System.err.println("Invalid value found!");
			System.exit(1);
		}	
	
		count = 0;
		for(int row2 = (int) (Math.floor(row / SUDOKU_BASE) * SUDOKU_BASE); Math.floor(row2 / SUDOKU_BASE) == Math.floor(row / SUDOKU_BASE); row2 ++) {
			for(int column2 = (int) (Math.floor(column / SUDOKU_BASE) * SUDOKU_BASE); Math.floor(column2 / SUDOKU_BASE) == Math.floor(column / SUDOKU_BASE); column2 ++) {
				if(matrix[row2][column2] == c) {
					count ++;
				}
			}
		}
		if(count > 1) {
			System.err.println("Invalid value found!");
			System.exit(1);
		}					
	}
	
	private static void logMatrix(Character[][] matrix) {
		for(int row = 0; row < SUDOKU_SIZE; row ++) {
			for(int column = 0; column < SUDOKU_SIZE; column ++) {
				System.out.print(matrix[row][column]);
			}
			System.out.println();
		}
		System.out.println("-------------------");
	}
}
