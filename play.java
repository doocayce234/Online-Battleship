import java.io.*;
import java.net.*;
import java.util.*;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
public class play {
	//All of the kinda usefull stuff for p1, probably could of found batter wasys of doing this!
	public static String[][] p1board;
	public static String[][] p1DummyBoard;
	public static String[][] p2board;
	public static String[][] p2DummyBoard;
	public static String[][] comBoard;
	public static String[][] comDummyBoard;
	public static int[] p1cruiserCords = new int[2];
	public static int p1cruiserLength = 0;
	public static boolean p1cruiserDirection;
	public static int[] p1battleshipCords = new int[2];
	public static int p1battleshipLength = 0;
	public static boolean p1battleshipDirection;
	public static int[] p1battleship2Cords = new int[2];
	public static int p1battleship2Length = 0;
	public static boolean p1battleship2Direction;
	public static int[] p1destroyerCords = new int[2];
	public static int p1destroyerLength = 0;
	public static boolean p1destroyerDirection;
	public static int[] p1destroyer2Cords = new int[2];
	public static int p1destroyer2Length = 0;
	public static boolean p1destroyer2Direction;
	public static String[][] p1OriginalBoard;

	//All of the kinda usefull stuff for p2, probably could of found better ways of doing this!
	public static int[] p2cruiserCords = new int[2];
	public static int p2cruiserLength = 0;
	public static boolean p2cruiserDirection;
	public static int[] p2battleshipCords = new int[2];
	public static int p2battleshipLength = 0;
	public static boolean p2battleshipDirection;
	public static int[] p2battleship2Cords = new int[2];
	public static int p2battleship2Length = 0;
	public static boolean p2battleship2Direction;
	public static int[] p2destroyerCords = new int[2];
	public static int p2destroyerLength = 0;
	public static boolean p2destroyerDirection;
	public static int[] p2destroyer2Cords = new int[2];
	public static int p2destroyer2Length = 0;
	public static boolean p2destroyer2Direction;
	public static String[][] p2OriginalBoard;

	public static int[] comCruiserCords = new int[2];
	public static int comCruiserLength = 0;
	public static boolean comCruiserDirection;
	public static int[] comBattleshipCords = new int[2];
	public static int comBattleshipLength = 0;
	public static boolean comBattleshipDirection;
	public static int[] comBattleship2Cords = new int[2];
	public static int comBattleship2Length = 0;
	public static boolean comBattleship2Direction;
	public static int[] comDestroyerCords = new int[2];
	public static int comDestroyerLength = 0;
	public static boolean comDestroyerDirection;
	public static int[] comDestroyer2Cords = new int[2];
	public static int comDestroyer2Length = 0;
	public static boolean comDestroyer2Direction;
	public static String[][] comOriginalBoard;

	public static PrintWriter out;
	public static Socket socket;
	public static ServerSocket server;
	//The main function. it's main use is to main and stuff!
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		p1board = new String[10][10];
		p2board = new String[10][10];
		comBoard = new String[10][10];
		p1DummyBoard = new String[10][10];
		p2DummyBoard = new String[10][10];
		comDummyBoard = new String[10][10];
		for(int i = 0; i < p1board.length; i = i + 1) {
			for(int k = 0; k < p1board[i].length; k = k + 1) {
				p1board[i][k] = "-";
				p2board[i][k] = "-";
				comBoard[i][k] = "-";
				p1DummyBoard[i][k] = "-";
				p2DummyBoard[i][k] = "-";
				comDummyBoard[i][k] = "-";
			}
		}
		System.out.println("BattleShip game!");
		boolean loop = true;
		boolean HorC = true;
		boolean port = true;
		String yourName = "";
		do {
			loop = true;
			System.out.println("What would you like your opponent to be?\n1. Human(2 player)\n2. Computer(1 player)\n3.Online via Port(2 player)");
			String opponent = input.nextLine();
			if(opponent.equalsIgnoreCase("1")) {
				HorC = true;
				port = false;
				loop = false;
			}
			else if(opponent.equalsIgnoreCase("2")) {
				HorC = false;
				port = false;
				loop = false;
			}
			else if(opponent.equalsIgnoreCase("3")) {
				try {
					// Open port 420
					System.out.println("Enter your name: ");
					yourName = input.nextLine();
					server = new ServerSocket(5000);
					System.out.println("Server is open on port: " + server.getLocalPort());;

					// Wait for a client to connect
					System.out.println("Waiting for a player to connect...");
					socket = server.accept();
					System.out.println("player connected!");
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					out = new PrintWriter(socket.getOutputStream(), true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				HorC = false;
				port = true;
				loop = false;
			}
			else {
				System.out.println("Invalid input, please try again.");
				loop = true;
			}
		}
		while(loop);

		System.out.println("Player 1, place your ships below");
		if(HorC || port == false) {
			System.out.println("Player 2, look away");
		}
		int shipsRemaining = 5;
		int cruiser = 1;
		int battleship = 2;
		int destroyer = 2;
		while(shipsRemaining > 0) {
			System.out.println("Ships remaining: " + shipsRemaining);
			if(port == true) {
				sendInput(yourName + " has " + shipsRemaining + " ships left to place");
			}
			printBoard(p1board);
			if(cruiser == 1) {
				boolean verify = verify(p1board, 5,"Cruiser", 1);
				if(verify) {
					cruiser = 0;
					shipsRemaining = shipsRemaining - 1;
				}
			}
			else if(battleship == 2) {
				boolean verify = verify(p1board, 4,"Battleship", 1);
				if(verify) {
					battleship = battleship - 1;
					shipsRemaining = shipsRemaining - 1;
				}
			}
			else if(battleship == 1) {
				boolean verify = verify(p1board, 4,"Battleship2", 1);
				if(verify) {
					battleship = battleship - 1;
					shipsRemaining = shipsRemaining - 1;
				}
			}
			else if(destroyer == 2) {
				boolean verify = verify(p1board, 3, "Destroyer",1);
				if(verify) {
					destroyer = destroyer - 1;
					shipsRemaining = shipsRemaining - 1;
				}
			}
			else if(destroyer == 1) {
				boolean verify = verify(p1board, 3, "Destroyer2",1);
				if(verify) {
					destroyer = destroyer - 1;
					shipsRemaining = shipsRemaining - 1;
				}
			}
		}
		printBoard(p1board);
		p1OriginalBoard = createCopy(p1board);
		if(HorC) {
			System.out.println("Player 1 board complete");
			if(HorC && port == false) {
				System.out.println("Player 2, place your ships below\nPlayer 1, look away");
			}
			else if(port) {
				System.out.println("Waiting for player 2 to place there ships");
			}
			shipsRemaining = 5;
			cruiser = 1;
			battleship = 2;
			destroyer = 2;
			while(shipsRemaining > 0) {
				if(HorC && port == false) {
					System.out.println("Ships remaining: " + shipsRemaining);
				}
				else {
					out.println("Ships remaining: " + shipsRemaining);
				}
				printBoard(p2board);
				if(cruiser == 1) {
					boolean verify = verify(p2board, 5,"Cruiser", 2);
					if(verify) {
						cruiser = 0;
						shipsRemaining = shipsRemaining - 1;
					}
				}
				else if(battleship == 2) {
					boolean verify = verify(p2board, 4,"Battleship", 2);
					if(verify) {
						battleship = battleship - 1;
						shipsRemaining = shipsRemaining - 1;
					}
				}
				else if(battleship == 1) {
					boolean verify = verify(p2board, 4,"Battleship2", 2);
					if(verify) {
						battleship = battleship - 1;
						shipsRemaining = shipsRemaining - 1;
					}
				}
				else if(destroyer == 2) {
					boolean verify = verify(p2board, 3, "Destroyer", 2);
					if(verify) {
						destroyer = destroyer - 1;
						shipsRemaining = shipsRemaining - 1;
					}
				}
				else if(destroyer == 1) {
					boolean verify = verify(p2board, 3, "Destroyer2", 2);
					if(verify) {
						destroyer = destroyer - 1;
						shipsRemaining = shipsRemaining - 1;
					}
				}
			}
			printBoard(p2board);
			p2OriginalBoard = createCopy(p2board);
		}
		else if(HorC == false && port == false) {
			String ranPos = "";
			boolean ranHorV = false;
			int comShipsRemaining = 5;
			int comCruiser = 1;
			int comBattleship = 2;
			int comDestroyer = 2;
			while(comShipsRemaining >= 0) {
				if(comCruiser == 1) {
					ranPos = ranCords(comBoard, 5, 5);
					if(ranPos.contains("true")) {
						ranPos = ranPos.substring(0, ranPos.length() - 4);
						ranHorV = true;
					}
					else if(ranPos.contains("false")) {
						ranPos = ranPos.substring(0, ranPos.length() - 5);
						ranHorV = false;
					}
					addShip.addShip(comBoard, ranPos, 5, ranHorV);
					comCruiser--;
				}
				else if(comBattleship == 1 || comBattleship == 2) {
					ranPos = ranCords(comBoard, 4, comBattleship == 2 ? 41 : 42);
					if(ranPos.contains("true")) {
						ranPos = ranPos.substring(0, ranPos.length() - 4);
						ranHorV = true;
					}
					else if(ranPos.contains("false")) {
						ranPos = ranPos.substring(0, ranPos.length() - 5);
						ranHorV = false;
					}
					addShip.addShip(comBoard, ranPos, 4, ranHorV);
					comBattleship--;
				}
				else if(comDestroyer == 1 || comDestroyer == 2) {
					ranPos = ranCords(comBoard, 3, comDestroyer == 2 ? 31 : 32);
					if(ranPos.contains("true")) {
						ranPos = ranPos.substring(0, ranPos.length() - 4);
						ranHorV = true;
					}
					else if(ranPos.contains("false")) {
						ranPos = ranPos.substring(0, ranPos.length() - 5);
						ranHorV = false;
					}
					addShip.addShip(comBoard, ranPos, 3, ranHorV);
					comDestroyer--;
				}
				System.out.println("ranPos: " + ranPos + ". ranHorV: " + ranHorV);
				comShipsRemaining --;
			}
			comOriginalBoard = createCopy(comBoard);
			printBoard(comBoard);
		}
		boolean win = true;
		System.out.println("Time to play!");
		boolean hit = false;
		int p1TurnCount = 0;
		int p2TurnCount = 0;
		int p1sunkShip = 0;
		int p2sunkShip = 0;
		int comTurnCount = 0;
		int comSunkShip = 0;
		int p1sunkShipCount = 0;
		int p2sunkShipCount = 0;
		int comSunkShipCount = 0;
		do {
			boolean validate = false;
			String pos;
			//Player 1's turn
			do {
				validate = false;
				System.out.println("'X' represents a hit ship. '*' represents a missed shot.\nPlayer 1 Take your shot");
				printBothBoards(p1DummyBoard, p1board);
				pos = input.nextLine().toUpperCase();
				if(!checkCords(pos)) {
					System.out.println("Invalid Cords, Try again");
					validate = true;
				}
				else if(checkCords(pos) && hitShip.hitShip(pos, 1, HorC, true) && validate == false) {
					System.out.println("Ship Hit!");
					p1TurnCount ++;
					if(p1TurnCount >= 3) {
						p1sunkShip = hitShip.sunkShip(HorC == true ? p2board : comBoard, 1, pos, HorC);
						if(p1sunkShip == 31 && HorC == true) {
							System.out.println("You sunk p2's 1st Destroyer!");
							p1sunkShipCount ++;
						}
						else if(p1sunkShip == 31 && HorC == false) {
							System.out.println("You sunk com's 1st Destroyer!");
							p1sunkShipCount ++;
						}
						else if(p1sunkShip == 32 && HorC == true) {
							System.out.println("You sunk p2's 2nd Destroyer!");
							p1sunkShipCount ++;
						}
						else if(p1sunkShip == 32 && HorC == false) {
							System.out.println("You sunk com's 2nd Destroyer!");
							p1sunkShipCount ++;
						}
						else if(p1sunkShip == 41 && HorC == true) {
							System.out.println("You sunk p2's 1st Battleship!");
							p1sunkShipCount ++;
						}
						else if(p1sunkShip == 41 && HorC == false) {
							System.out.println("You sunk com's 1st Battleship!");
							p1sunkShipCount ++;
						}
						else if(p1sunkShip == 42 && HorC == true) {
							System.out.println("You sunk p2's 2nd Battleship!");
							p1sunkShipCount ++;
						}
						else if(p1sunkShip == 42 && HorC == false) {
							System.out.println("You sunk com's 2nd Battleship!");
							p1sunkShipCount ++;
						}
						else if(p1sunkShip == 5 && HorC == true) {
							System.out.println("You sunk p2's Cruiser!");
							p1sunkShipCount ++;
						}
						else if(p1sunkShip == 5 && HorC == false) {
							System.out.println("You sunk com's Cruiser!");
							p1sunkShipCount ++;
						}
						p1sunkShip = 0;
					}
					System.out.println(p1sunkShipCount + " ship(s) sunk!");
				}
				else if(checkCords(pos) && validate == false) {
					p1TurnCount ++;
					System.out.println("Missed!");
				}
			}
			while(validate);
			try {
				Thread.sleep(500);
			}
			catch(Exception ex) {}
			if(p1sunkShipCount >= 5) {
				System.out.println("P1 Wins! with " + p1TurnCount + " shots.");
				win = false;
			}
			if(HorC == true && win == true) {
				//Player 2's turn
				do {
					validate = false;
					System.out.println("'X' represents a hit ship. '*' represents a missed shot.\nPlayer 2 Take your shot");
					printBothBoards(p2DummyBoard, p2board);
					pos = input.nextLine().toUpperCase();
					if(!checkCords(pos)) {
						System.out.println("Invalid Cords, Try again");
						validate = true;
					}
					else if(checkCords(pos) && hitShip.hitShip(pos, 2, HorC, true) && validate == false) {
						System.out.println("Ship Hit!");
						p2TurnCount ++;
						if(p2TurnCount >= 3) {
							p2sunkShip = hitShip.sunkShip(p1board, 2, pos, HorC);
							if(p2sunkShip == 31) {
								System.out.println("You sunk p1's 1st Destroyer!");
								p2sunkShipCount ++;
							}
							else if(p2sunkShip == 32) {
								System.out.println("You sunk p1's 2nd Destroyer!");
								p2sunkShipCount ++;
							}
							else if(p2sunkShip == 41) {
								System.out.println("You sunk p1's 1st Battleship!");
								p2sunkShipCount ++;
							}
							else if(p2sunkShip == 42) {
								System.out.println("You sunk p1's 2nd Battleship!");
								p2sunkShipCount ++;
							}
							else if(p2sunkShip == 5) {
								System.out.println("You sunk p1's Cruiser!");
								p2sunkShipCount ++;
							}
							p2sunkShip = 0;
						}
					}
					else if(checkCords(pos) && validate == false) {
						p2TurnCount ++;
						System.out.println("Missed!");
					}
					System.out.println(p1sunkShipCount + " ship(s) sunk!");
				}
				while(validate);
				if(p2sunkShipCount >= 5) {
					System.out.println("P2 Wins! with " + p2TurnCount + " shots.");
					win = false;
				}
			}
			else if(HorC == false) {
				System.out.println("Computers turn");
				hit = computerTurn();
				if(hit) {
					comTurnCount ++;
					System.out.println("Computer Hit Your Ship Hit!");
					if(comTurnCount >= 3) {
						comSunkShip = hitShip.sunkShip(p1board, 2, ranPos.charAt(0) + "" + (Integer.parseInt(ranPos.substring(1)) + 1), false);
						if(comSunkShip == 31) {
							System.out.println("Computer sunk Your 1st Destroyer!");
							comSunkShipCount ++;
						}
						else if(comSunkShip == 32) {
							System.out.println("Computer sunk Your 2nd Destroyer!");
							comSunkShipCount ++;
						}
						else if(comSunkShip == 41) {
							System.out.println("Computer sunk Your 1st Battleship!");
							comSunkShipCount ++;
						}
						else if(comSunkShip == 42) {
							System.out.println("Computer sunk Your 2nd Battleship!");
							comSunkShipCount ++;
						}
						else if(comSunkShip == 5) {
							System.out.println("Computer sunk Your Cruiser!");
							comSunkShipCount ++;
						}
						comSunkShip = 0;
					}
				}
				else {
					comTurnCount ++;
					System.out.println("Computer Missed!");
				}
				if(comSunkShipCount >= 5) {
					System.out.println("Computer Wins! with " + comSunkShipCount + " shots.");
					win = false;
				}
			}
			try {
				Thread.sleep(500);
			}
			catch(Exception ex) {}
			if(p1sunkShip == 5) {
				System.out.println("Player 1 Wins!");
				win = false;;
			}
			else if(p2sunkShip == 5) {
				System.out.println("Player 2 Wins!");
				win = false;
			}
			else if(comSunkShip == 5) {
				System.out.println("Computer Wins!");
				win = false;
			}
		}
		while(win);
	}
	public static BufferedReader in;
	public static String msg = "";

	public static String recieveInput() {
		try {
			String string;
			while((string = in.readLine()) != null) {
				msg = string;
			}
		}
		catch(Exception e) {
			System.out.println("Error!");
		}
		return msg;
	}
	public static void sendInput(String msg) {
		try {
			out.println(msg);
		}
		catch(Exception e) {
			System.out.println("Error!");
		}
	}
	//Prints the board... Dummy!
	public static void printBoard(String[][] board) {
		String abc = "ABCDEFGHIJ";
		System.out.println("  1 2 3 4 5 6 7 8 9 10");
		for(int i = 0; i < board.length; i = i + 1) {
			System.out.print(abc.charAt(i));
			for(int k = 0; k < board[i].length; k = k + 1) {
				System.out.print(" " + board[i][k]);
			}
			System.out.println();
		}
	}
	public static String ranCords(String[][] board, int length, int typeOfShip) {
		boolean valid = false;
		String ranCords;
		boolean ranHorV;
		do {
			ranCords = (char) ('A' + (int) (Math.random() * 9)) + "" + ((int) (Math.random() * 9) + 1);
			ranHorV = Math.random() < 0.5;
			System.out.println("char: " + ranCords.charAt(0));
			System.out.println("ranCords: " + ranCords + ". ranHorV: " + ranHorV);
			valid = checkShipPlacement(board, ranCords, length, ranHorV, false);
			System.out.println("valid: " + valid);
		}
		while(valid == false);
		int ranCol = Integer.parseInt(ranCords.substring(1)) - 1;
		int ranRow = ranCords.charAt(0) - 'A';
		if(typeOfShip == 5) {
			play.comCruiserCords[0] = ranCol * 10 + ranRow;
			play.comCruiserCords[1] = (ranCol + 4) * 10 + ranRow;
			play.comCruiserLength = 5;
			play.comCruiserDirection = ranHorV;
		}
		else if(typeOfShip == 41) {
			play.comBattleshipCords[0] = ranCol * 10 + ranRow;
			play.comBattleshipCords[1] = (ranCol + 3) * 10 + ranRow;
			play.comBattleshipLength = 4;
			play.comBattleshipDirection = ranHorV;
		}
		else if(typeOfShip == 42) {
			play.comBattleship2Cords[0] = ranCol * 10 + ranRow;
			play.comBattleship2Cords[1] = (ranCol + 3) * 10 + ranRow;
			play.comBattleship2Length = 4;
			play.comBattleship2Direction = ranHorV;
		}
		else if(typeOfShip == 31) {
			play.comDestroyerCords[0] = ranCol * 10 + ranRow;
			play.comDestroyerCords[1] = (ranCol + 2) * 10 + ranRow;
			play.comDestroyerLength = 3;
			play.comDestroyerDirection = ranHorV;
		}
		else if(typeOfShip == 32) {
			play.comDestroyer2Cords[0] = ranCol * 10 + ranRow;
			play.comDestroyer2Cords[1] = (ranCol + 2) * 10 + ranRow;
			play.comDestroyer2Length = 3;
			play.comDestroyer2Direction = ranHorV;
		}
		System.out.println(ranCords + "" + ranHorV);
		return ranCords + "" + ranHorV;
	}
	//Okay, this one might be a little NOT obvious. it prints a dummy board on the left wich keeps track of your shots.
	//and prints your acctual board with the ships that you have placed on the right!
	public static void printBothBoards(String[][] dummyBoard, String[][] yourBoard) {
		String abc = "ABCDEFGHIJ";
		String yourBoardString = "Your Board";
		System.out.println("  1 2 3 4 5 6 7 8 9 10              1 2 3 4 5 6 7 8 9 10");
		for(int i = 0; i < dummyBoard.length; i = i + 1) {
			System.out.print(abc.charAt(i));
			for(int k = 0; k < dummyBoard[i].length; k = k + 1) {
				System.out.print(" " + dummyBoard[i][k]);
			}
			if(i == 0 || i == 3 || i == 4 || i == 5 || i == 9) {
				System.out.print("      " + yourBoardString.charAt(i) + "      " + abc.charAt(i));
			}
			else if(i == 1 || i == 2 || i == 6 || i == 7 || i == 8) {
				System.out.print("      " + yourBoardString.charAt(i) + "->" + "    " + abc.charAt(i));
			}
			for(int k = 0; k < yourBoard[i].length; k = k + 1) {
				System.out.print(" " + yourBoard[i][k]);
			}
			System.out.println();
		}
	}
	//pretty long, caused some serious debugging and some serious headaches!
	public static boolean verify(String[][] board, int length, String shipName, int turn) {
		Scanner input = new Scanner(System.in);
		String cords = "";
		if(length == 5) {
			System.out.print("Place your cruiser(5 spaces long): ");
		}
		else if(length == 4) {
			System.out.print("Place your battleship(4 spaces long): ");
		}
		else if(length == 3) {
			System.out.print("Place your destroyer(3 spaces long): ");
		}
		cords = input.nextLine().toUpperCase();
		if(!cords.matches("^[A-J][1-9]0?$")) {
			System.out.println("Invalid input, must be letter(A-J) followed by a number(1-10).");
			return false;
		}
		else if(cords.matches("^[A-J][1-9]0?$")) {
			boolean hVLoop = true;
			do {
				System.out.print("horizontal or vertical(H / V): ");
				String HorV = input.nextLine();
				if(HorV.equalsIgnoreCase("V")) {
					if(!checkShipPlacement(board, cords, length, false, false)) {
						return false;
					}
					String valid = "";
					do {
						preveiwBoard(board, cords, length, false);
						System.out.println("Are you sure you want to place your ship here(Y/N): ");
						valid = input.nextLine();
						if(valid.equalsIgnoreCase("y")) {
							board = addShip.addShip(board, cords, length, false);
						}
						else if(valid.equalsIgnoreCase("n")) {
							System.out.println("Ship placement cancelled. Returning to Ship placement menu.");
							return false;
						}
						else {
							System.out.println("Invalid input, must be 'Y' or 'N'.");
						}
					}
					while(!(valid.equalsIgnoreCase("y") || valid.equalsIgnoreCase("n")));
					if(turn == 1) {
						System.out.println(addShip.col);
						switch(shipName) {
						case "Cruiser":
							System.out.println(addShip.col);
							play.p1cruiserCords[0] = addShip.col * 10 + addShip.row;
							play.p1cruiserCords[1] = addShip.col * 10 + (addShip.row + 4);
							play.p1cruiserLength = 5;
							play.p1cruiserDirection = false;
							break;
						case "Battleship":
							System.out.println(addShip.col - 1);
							play.p1battleshipCords[0] = addShip.col * 10 + addShip.row;
							play.p1battleshipCords[1] = addShip.col * 10 + (addShip.row + 3);
							play.p1battleshipLength = 4;
							play.p1battleshipDirection = false;
							break;
						case "Battleship2":
							System.out.println(addShip.col);
							play.p1battleship2Cords[0] = addShip.col * 10 + addShip.row;
							play.p1battleship2Cords[1] = addShip.col * 10 + (addShip.row + 3);
							play.p1battleship2Length = 4;
							play.p1battleship2Direction = false;
							break;
						case "Destroyer":
							play.p1destroyerCords[0] = addShip.col * 10 + addShip.row;
							play.p1destroyerCords[1] = addShip.col * 10 + (addShip.row + 2);
							play.p1destroyerLength = 3;
							play.p1destroyerDirection = false;
							break;
						case "Destroyer2":
							play.p1destroyer2Cords[0] = addShip.col * 10 + addShip.row;
							play.p1destroyer2Cords[1] = addShip.col * 10 + (addShip.row + 2);
							play.p1destroyer2Length = 3;
							play.p1destroyer2Direction = false;
							break;
						default:
							break;
						}
					}
					if(turn == 2) {
						switch(shipName) {
						case "Cruiser":
							play.p2cruiserCords[0] = addShip.col * 10 + addShip.row;
							play.p2cruiserCords[1] = addShip.col * 10 + (addShip.row + 4);
							play.p2cruiserLength = 5;
							play.p2cruiserDirection = false;
							break;
						case "Battleship":
							System.out.println((addShip.col - 1));
							play.p2battleshipCords[0] = addShip.col * 10 + addShip.row;
							play.p2battleshipCords[1] = addShip.col * 10 + (addShip.row + 3);
							play.p2battleshipLength = 4;
							play.p2battleshipDirection = false;
							break;
						case "Battleship2":
							play.p2battleship2Cords[0] = addShip.col * 10 + addShip.row;
							play.p2battleship2Cords[1] = addShip.col * 10 + (addShip.row + 3);
							play.p2battleship2Length = 4;
							play.p2battleship2Direction = false;
							break;
						case "Destroyer":
							play.p2destroyerCords[0] = addShip.col * 10 + addShip.row;
							play.p2destroyerCords[1] = addShip.col * 10 + (addShip.row + 2);
							play.p2destroyerLength = 3;
							play.p2destroyerDirection = false;
							break;
						case "Destroyer2":
							play.p2destroyer2Cords[0] = addShip.col * 10 + addShip.row;
							play.p2destroyer2Cords[1] = addShip.col * 10 + (addShip.row + 2);
							play.p2destroyer2Length = 3;
							play.p2destroyer2Direction = false;
							break;
						default:
							break;
						}
					}
					hVLoop = false;
				}
				else if(HorV.equalsIgnoreCase("H")) {
					if(!checkShipPlacement(board, cords, length, true, false)) {
						return false;
					}
					String valid = "";
					do {
						preveiwBoard(board, cords, length, true);
						System.out.println("Are you sure you want to place your ship here(Y/N): ");
						valid = input.nextLine();
						if(valid.equalsIgnoreCase("y")) {
							System.out.println("Updating Board");
							board = addShip.addShip(board, cords, length, true);
						}
						else if(valid.equalsIgnoreCase("n")) {
							System.out.println("Ship placement cancelled. Returning to Ship placement menu.");
							return false;
						}
						else {
							System.out.println("Invalid input, must be 'Y' or 'N'.");
						}
					}
					while(!(valid.equalsIgnoreCase("y") || valid.equalsIgnoreCase("n")));
					if(turn == 1) {
						switch(shipName) {
						case "Cruiser":
							play.p1cruiserCords[0] = addShip.col * 10 + addShip.row;
							play.p1cruiserCords[1] = (addShip.col + 4) * 10 + addShip.row;
							play.p1cruiserLength = 5;
							play.p1cruiserDirection = true;
							break;
						case "Battleship":
							play.p1battleshipCords[0] = addShip.col * 10 + addShip.row;
							play.p1battleshipCords[1] = (addShip.col + 3) * 10 + addShip.row;
							play.p1battleshipLength = 4;
							play.p1battleshipDirection = true;
							break;
						case "Battleship2":
							play.p1battleship2Cords[0] = addShip.col * 10 + addShip.row;
							play.p1battleship2Cords[1] = (addShip.col + 3) * 10 + addShip.row;
							play.p1battleship2Length = 4;
							play.p1battleship2Direction = true;
							break;
						case "Destroyer":
							play.p1destroyerCords[0] = addShip.col * 10 + addShip.row;
							play.p1destroyerCords[1] = (addShip.col + 2) * 10 + addShip.row;
							play.p1destroyerLength = 3;
							play.p1destroyerDirection = true;
							break;
						case "Destroyer2":
							play.p1destroyer2Cords[0] = addShip.col * 10 + addShip.row;
							play.p1destroyer2Cords[1] = (addShip.col + 2) * 10 + addShip.row;
							play.p1destroyer2Length = 3;
							play.p1destroyer2Direction = true;
							break;
						default:
							break;
						}
					}
					if(turn == 2) {
						switch(shipName) {
						case "Cruiser":
							play.p2cruiserCords[0] = addShip.col * 10 + addShip.row;
							play.p2cruiserCords[1] = (addShip.col + 4) * 10 + addShip.row;
							play.p2cruiserLength = 5;
							play.p2cruiserDirection = true;
							break;
						case "Battleship":
							play.p2battleshipCords[0] = addShip.col * 10 + addShip.row;
							play.p2battleshipCords[1] = (addShip.col + 3) * 10 + addShip.row;
							play.p2battleshipLength = 4;
							play.p2battleshipDirection = true;
							break;
						case "Battleship2":
							play.p2battleship2Cords[0] = addShip.col * 10 + addShip.row;
							play.p2battleship2Cords[1] = (addShip.col + 3) * 10 + addShip.row;
							play.p2battleship2Length = 4;
							play.p2battleship2Direction = true;
							break;
						case "Destroyer":
							play.p2destroyerCords[0] = addShip.col * 10 + addShip.row;
							play.p2destroyerCords[1] = (addShip.col + 2) * 10 + addShip.row;
							play.p2destroyerLength = 3;
							play.p2destroyerDirection = true;
							break;
						case "Destroyer2":
							play.p2destroyer2Cords[0] = addShip.col * 10 + addShip.row;
							play.p2destroyer2Cords[1] = (addShip.col + 2) * 10 + addShip.row;
							play.p2destroyer2Length = 3;
							play.p2destroyer2Direction = true;
							break;
						default:
							break;
						}
					}
					hVLoop = false;
				}
				else {
					System.out.println("Invalid input, must be H or V.");
					hVLoop = true;
				}
			}
			while(hVLoop);
		}
		if(!cords.matches("^[A-J][1-9]0?$")) {
			System.out.println("Invalid input, must be letter(A-J) followed by a number(1-10).");
			return true;
		}
		return true;
	}
	//This is getting old, i think you know what this one does!
	public static boolean checkCords(String cords) {
		if(cords.matches("^[A-J][1-9]0?$")) {
			return true;
		}
		return false;
	}
	//Cmon', really? it's what it sounds like, it's the compooter's turn!
	public static String ranPos;
	public static ArrayList <Boolean> hitList = new ArrayList<Boolean>();
	public static ArrayList <String> ranPosList = new ArrayList<String>();
	public static ArrayList <String> notSunkinShips = new ArrayList<String>();
	public static int one = 0;
	public static int AtSymble = 0;
	public static int negOne = 0;
	public static int sunkShipCount = 0;
	public static boolean computerTurn() {
		boolean direction = false;
		if(one == 0) {
			ranPosList.add("N0");
		}
		one ++;
		ranPos = (char) ('A' + (int) (Math.random() * 10)) + "" + (int) (Math.random() * 10);

		if(ranPosList.contains(ranPos)) {
			System.out.println("Entered loop because generated the same position  ####1111111. Original: " + ranPos);
			do {
				ranPos = (char) ('A' + (int) (Math.random() * 10)) + "" + (int) (Math.random() * 10);
				System.out.println("the new generated cords are " + ranPos);
			}
			while(ranPosList.contains(ranPos));
			System.out.println("RanPosList = " + ranPosList);
		}
		System.out.println("does hitList contain more than one true?: " + hitList.stream().filter(Boolean::booleanValue).count());
		System.out.println("AtSymble: " + AtSymble + "\nnegOne: "+ negOne);
		if(one > 1 && hitList.contains(true)) {
			System.out.println("Entered loop because the last shot hit a ship.");
			direction = getDirection(ranPosList.get(hitList.indexOf(true) + 1).charAt(0) + "" + (Integer.parseInt(ranPosList.get(hitList.indexOf(true) + 1).substring(1)) + 1), 2);
			System.out.println("The direction of the ship that the computer hit is " + (direction ? "Horizontal" : "Vertical"));
			System.out.println(ranPosList.get(hitList.indexOf(true) + 1) + "\n" + direction);
			if(direction == false) {
				//False == Vertical
				System.out.println("If ranPosList Index of hitList Index of true + 1 .charAt = " + ranPosList.get(hitList.indexOf(true) + 1).charAt(0) + "\nIf that contains a 'A'");
				if(ranPosList.get(hitList.indexOf(true)).contains("A")) {

					if(hitList.stream().filter(Boolean::booleanValue).count() > 1) {
						hitList.set(hitList.indexOf(true), false);
						System.out.println("Clearing the first index of true in hitList/nhitList indexes of true is: " + hitList.stream().filter(Boolean::booleanValue).count());
					}
					if(hitList.stream().filter(Boolean::booleanValue).count() == 1) {
						ranPos = (char) (ranPosList.get(hitList.indexOf(true) + 1).charAt(0) + 1) + "" + (Integer.parseInt(ranPosList.get(hitList.indexOf(true) + 1).substring(1)));
						System.out.println("Trying to shoot 1 down from original");
					}
				}
				else {
					System.out.println("Ship that was hit is vertical, and the last hit spot did not contain an 'A'.\nIt contained a '" + (char) (ranPosList.get(hitList.indexOf(true) + 1)).charAt(0) + "'.");
					System.out.println("ranPosList last hit char - 1 is: " + (char) (ranPosList.get(hitList.indexOf(true) + 1).charAt(0) - 1) + "\nNot - 1 is: " + (char) (ranPosList.get(hitList.indexOf(true) + 1).charAt(0)));
					if(hitList.stream().filter(Boolean::booleanValue).count() > 1) {
						hitList.set(hitList.indexOf(true), false);
						System.out.println("Clearing the first index of true in hitList\nhitList indexes of true is: " + hitList.stream().filter(Boolean::booleanValue).count());
					}
					if(hitList.stream().filter(Boolean::booleanValue).count() == 1) {
						ranPos = (char) (ranPosList.get(hitList.indexOf(true) + 1).charAt(0) - 1) + "" + (Integer.parseInt(ranPosList.get(hitList.indexOf(true) + 1).substring(1)));
						System.out.println("hitList contains 1 true\nranPosList = " + ranPosList);
						if(AtSymble == 2) {
							System.out.println("AtSymble = " + AtSymble + ". And the first if statment equals: " + (hitShip.hitShip((char) (ranPosList.get(hitList.indexOf(true) + 1).charAt(0) + 1) + "" + (Integer.parseInt(ranPosList.get(hitList.indexOf(true) + 1).substring(1)) + 1), 2, true, false) == true));
							if(hitShip.hitShip((char) (ranPosList.get(hitList.indexOf(true) + 1).charAt(0)) + "" + (Integer.parseInt(ranPosList.get(hitList.indexOf(true) + 1).substring(1)) + 1), 2, true, false) == true) {
								ranPos = (char) (ranPosList.get(hitList.indexOf(true) + 1).charAt(0) + 1) + "" + (Integer.parseInt(ranPosList.get(hitList.indexOf(true) + 1).substring(1)));
								System.out.println("Trying to shoot ONE down from original");
							}
							else if(hitShip.hitShip((char) (ranPosList.get(hitList.indexOf(true) + 1).charAt(0) + 1) + "" + (Integer.parseInt(ranPosList.get(hitList.indexOf(true) + 1).substring(1)) + 1), 2, true, false) == true) {
								ranPos = (char) (ranPosList.get(hitList.indexOf(true) + 1).charAt(0) + 2) + "" + (Integer.parseInt(ranPosList.get(hitList.indexOf(true) + 1).substring(1)));
								System.out.println("Trying to shoot TWO down from original");
							}
						}
						if(AtSymble == 3) {
							System.out.println("AtSymble = " + AtSymble + ". And the first if statment equals: " + (hitShip.hitShip((char) (ranPosList.get(hitList.indexOf(true) + 1).charAt(0) + 1) + "" + (Integer.parseInt(ranPosList.get(hitList.indexOf(true) + 1).substring(1)) + 1), 2, true, false) == true));
							if(hitShip.hitShip((char) (ranPosList.get(hitList.indexOf(true) + 1).charAt(0)) + "" + (Integer.parseInt(ranPosList.get(hitList.indexOf(true) + 1).substring(1)) + 1), 2, true, false) == true) {
								ranPos = (char) (ranPosList.get(hitList.indexOf(true) + 1).charAt(0) + 1) + "" + (Integer.parseInt(ranPosList.get(hitList.indexOf(true) + 1).substring(1)));
								System.out.println("Trying to shoot ONE down from original");
							}
							else if(hitShip.hitShip((char) (ranPosList.get(hitList.indexOf(true) + 1).charAt(0) + 1) + "" + (Integer.parseInt(ranPosList.get(hitList.indexOf(true) + 1).substring(1)) + 1), 2, true, false) == true) {
								ranPos = (char) (ranPosList.get(hitList.indexOf(true) + 1).charAt(0) + 2) + "" + (Integer.parseInt(ranPosList.get(hitList.indexOf(true) + 1).substring(1)));
								System.out.println("Trying to shoot TWO down from original");
							}
						}
						else if(AtSymble == 4) {
							int firstHitShipIndex = 0;
							AtSymble = 4;
							System.out.println("4444: Entered if statement because last shot did not hit\nfirstHitShipIndex = " + firstHitShipIndex + ". And ranPosList size - 1 is " + (ranPosList.size() - 1));
							System.out.println("4444: ranPosList.get(hitList.indexOf(true) + 1: " + ranPosList.get(hitList.indexOf(true) + 1));
							for(int i = ranPosList.size() - 1; i > 0; i = i - 1) {
								if(i == 0) {

								}
								else if(hitShip.hitShip(ranPosList.get(i).charAt(0) + "" + (Integer.parseInt(ranPosList.get(i).substring(1)) + 1), 2, false, false) && getTypeOfShipFromCords(p1board, 2,  ranPosList.get(i), false) == getTypeOfShipFromCords(p1board, 2, ranPosList.get(hitList.indexOf(true) + 1), false)) {
									firstHitShipIndex = i;
								}
							}
							System.out.println("New index of first pos hit on the current targeted ship is " + ranPosList.get(firstHitShipIndex));
							ranPos = (char) (ranPosList.get(firstHitShipIndex).charAt(0) + 2) + "" + Integer.parseInt(ranPosList.get(firstHitShipIndex).substring(1));
						}
						else if(ranPos.contains("@") && AtSymble == 0) {
							AtSymble = 1;
							int firstIndexOfRanPosHitSameShip = 0;
							System.out.println("Entered if statement because ranPos contains @\nfirstIndexOfRanPosHitSameShip = " + firstIndexOfRanPosHitSameShip + ". And ranPosList size - 1 is " + (ranPosList.size() - 1));
							for(int i = ranPosList.size() - 1; i > 0; i = i - 1) {
								if(i == 0) {

								}
								else if(hitShip.hitShip(ranPosList.get(i).charAt(0) + "" + (Integer.parseInt(ranPosList.get(i).substring(1)) + 1), 2, false, false) && getTypeOfShipFromCords(p1board, 2,  ranPosList.get(i), false) == getTypeOfShipFromCords(p1board, 2, ranPosList.get(hitList.indexOf(true) + 1), false)) {
									AtSymble = 2;
									firstIndexOfRanPosHitSameShip = i;  //  fixed get type of ship method
								}
							}
							ranPos = (char) (ranPosList.get(firstIndexOfRanPosHitSameShip).charAt(0) + 1) + "" + Integer.parseInt(ranPosList.get(firstIndexOfRanPosHitSameShip).substring(1));
						}
						else if(hitShip.hitShip(ranPosList.get(ranPosList.size() - 1).charAt(0) + "" + (Integer.parseInt(ranPosList.get(ranPosList.size() - 1).substring(1)) + 1), 2, false, false) == false) {//just switch the if statement to if(ran something does't contain @)
							int firstHitShipIndex = 0;
							AtSymble = 3;
							System.out.println("Entered if statement because last shot did not hit\nfirstHitShipIndex = " + firstHitShipIndex + ". And ranPosList size - 1 is " + (ranPosList.size() - 1));
							System.out.println("ranPosList.get(hitList.indexOf(true) + 1: " + ranPosList.get(hitList.indexOf(true) + 1));
							for(int i = ranPosList.size() - 1; i > 0; i = i - 1) {
								if(i == 0) {

								}
								else if(hitShip.hitShip(ranPosList.get(i).charAt(0) + "" + (Integer.parseInt(ranPosList.get(i).substring(1)) + 1), 2, false, false) && getTypeOfShipFromCords(p1board, 2,  ranPosList.get(i), false) == getTypeOfShipFromCords(p1board, 2, ranPosList.get(hitList.indexOf(true) + 1), false)) {
									firstHitShipIndex = i;
								}
							}
							ranPos = (char) (ranPosList.get(firstHitShipIndex).charAt(0) + 1) + "" + Integer.parseInt(ranPosList.get(firstHitShipIndex).substring(1));
						}
						else if(ranPosList.size() - 1 > 3) {
							if(ranPos.contains("@") && AtSymble == 0) {

							}
							else if((hitShip.hitShip(ranPosList.get(ranPosList.size() - 1).charAt(0) + "" + (Integer.parseInt(ranPosList.get(ranPosList.size() - 1).substring(1)) + 1), 2, false, false) == true) && (hitShip.sunkShip(p1board, 2, ranPosList.get(ranPosList.size() - 2).charAt(0) + "" + (Integer.parseInt(ranPosList.get(ranPosList.size() - 2).substring(1)) + 1), false) == 0) && getTypeOfShipFromCords(p1OriginalBoard, 2, ranPosList.get(ranPosList.size() - 1), false) != getTypeOfShipFromCords(p1OriginalBoard, 2, ranPosList.get(hitList.indexOf(true) + 1), false)) {
								int firstHitShipIndex = 0;
								AtSymble = 4;
								System.out.println("NEW COLISION WITH TWO SHIPS: Entered if statement because last shot did not hit\nfirstHitShipIndex = " + firstHitShipIndex + ". And ranPosList size - 1 is " + (ranPosList.size() - 1));
								System.out.println("NEW COLISION WITH TWO SHIPS: ranPosList.get(hitList.indexOf(true) + 1: " + ranPosList.get(hitList.indexOf(true) + 1));
								for(int i = ranPosList.size() - 1; i > 0; i = i - 1) {
									if(i == 0) {

									}
									else if(hitShip.hitShip(ranPosList.get(i).charAt(0) + "" + (Integer.parseInt(ranPosList.get(i).substring(1)) + 1), 2, false, false) && getTypeOfShipFromCords(p1board, 2,  ranPosList.get(i), false) == getTypeOfShipFromCords(p1board, 2, ranPosList.get(hitList.indexOf(true) + 1), false)) {
										firstHitShipIndex = i;
									}
								}
								System.out.println("setting ranPos to " + ranPos);
								ranPos = (char) (ranPosList.get(firstHitShipIndex).charAt(0) + 1) + "" + Integer.parseInt(ranPosList.get(firstHitShipIndex).substring(1));
							}
						}
					}
				}
			}
			if(direction == true) {
				//True == horizontal
				if(ranPosList.get(hitList.indexOf(true)).contains("9")) {
					System.out.println("Entered if statement where ranPosList.get(hitList.indexOf) + 1).contains(\"9\")");
					if(hitList.stream().filter(Boolean::booleanValue).count() > 1) {
						System.out.println("Entered if statement where hitList contains more than one true");
						hitList.set(hitList.indexOf(true), false);
					}
					if(hitList.stream().filter(Boolean::booleanValue).count() == 1) {
						System.out.println("Entered if statement where hisList contains 1 true");
						ranPos = ranPosList.get(hitList.indexOf(true) + 1).substring(0, 1) + "" + (Integer.parseInt(ranPosList.get(hitList.indexOf(true) + 1).substring(1)) + 1);
					}
				}
				else {
					System.out.println("Entered else statment after if statment where ranPosList.get(hitList.indexOf) + 1).contains(\"9\")");
					if(hitList.stream().filter(Boolean::booleanValue).count() > 1) {
						System.out.println("Entered if statement where hitList contains more than one true");
						hitList.set(hitList.indexOf(true), false);
					}
					if(hitList.stream().filter(Boolean::booleanValue).count() == 1) {
						System.out.println("Entered if statement where hisList contains 1 true");
						System.out.println("ranPosList.get(hitList(true + 1)) = " + ranPosList.get(hitList.indexOf(true) + 1));
						ranPos = ranPosList.get(hitList.indexOf(true) + 1).substring(0, 1) + "" + (Integer.parseInt(ranPosList.get(hitList.indexOf(true) + 1).substring(1)) + 1);
						if(negOne == 2) {
							System.out.println("Entered if statement negOne == 2");
							if(hitShip.hitShip((char) (ranPosList.get(hitList.indexOf(true) + 1).charAt(0)) + "" + (Integer.parseInt(ranPosList.get(hitList.indexOf(true) + 1).substring(1)) - 1), 2, true, false) == true) {
								ranPos = (char) (ranPosList.get(hitList.indexOf(true) + 1).charAt(0)) + "" + (Integer.parseInt(ranPosList.get(hitList.indexOf(true) + 1).substring(1)) - 1);
							}
							else if(hitShip.hitShip((char) (ranPosList.get(hitList.indexOf(true) + 1).charAt(0)) + "" + (Integer.parseInt(ranPosList.get(hitList.indexOf(true) + 1).substring(1)) - 2), 2, true, false) == true) {
								ranPos = (char) (ranPosList.get(hitList.indexOf(true) + 1).charAt(0)) + "" + (Integer.parseInt(ranPosList.get(hitList.indexOf(true) + 1).substring(1)) - 2);
							}
						}
						if(negOne == 3) {
							System.out.println("Entered if statement negOne == 3");
							if(hitShip.hitShip((char) (ranPosList.get(hitList.indexOf(true) + 1).charAt(0)) + "" + (Integer.parseInt(ranPosList.get(hitList.indexOf(true) + 1).substring(1)) + 1), 2, true, false) == true) {
								ranPos = (char) (ranPosList.get(hitList.indexOf(true) + 1).charAt(0)) + "" + (Integer.parseInt(ranPosList.get(hitList.indexOf(true) + 1).substring(1)) - 1);
							}
							System.out.println("ranPos set to: " + ranPos);
						}
						if(ranPos.contains("-1")) {
							System.out.println("Entered if statement where ranPos.contains -1");
							negOne = 1;
							int firstIndexOfRanPosHitSameShip = 0;
							for(int i = ranPosList.size() - 1; i > 0; i = i - 1) {
								if(i == 0) {

								}
								else if(hitShip.hitShip(ranPosList.get(i).charAt(0) + "" + (Integer.parseInt(ranPosList.get(i).substring(1)) + 1), 2, false, false) && getTypeOfShipFromCords(p1board, 2,  ranPosList.get(i), false) == getTypeOfShipFromCords(p1board, 2, ranPosList.get(hitList.indexOf(true) + 1), false)) {
									negOne = 2;
									firstIndexOfRanPosHitSameShip = i;  //  fixed get type of ship method
								}
							}
							ranPos = (char) (ranPosList.get(firstIndexOfRanPosHitSameShip).charAt(0)) + "" + (Integer.parseInt(ranPosList.get(firstIndexOfRanPosHitSameShip).substring(1)) - 1);
							System.out.println("setting ranPos to: " + ranPos + "\n. because firstIndexOfRanPosHitSameShip = " + firstIndexOfRanPosHitSameShip + "\n. and ranPosList.get(fIORPHSS) = " + ranPosList.get(firstIndexOfRanPosHitSameShip));
						}
						else if(hitShip.hitShip(ranPosList.get(ranPosList.size() - 1).charAt(0) + "" + (Integer.parseInt(ranPosList.get(ranPosList.size() - 1).substring(1)) + 1), 2, false, false) == false) {//just switch the if statement to if(ran something does't contain @)
							int firstHitShipIndex = 0;
							System.out.println("RanPos = " + ranPos);
							negOne = 3;
							for(int i = ranPosList.size() - 1; i > 0; i = i - 1) {
								if(i == 0) {

								}
								else if(hitShip.hitShip(ranPosList.get(i).charAt(0) + "" + (Integer.parseInt(ranPosList.get(i).substring(1)) + 1), 2, false, false) && getTypeOfShipFromCords(p1board, 2,  ranPosList.get(i), false) == getTypeOfShipFromCords(p1board, 2, ranPosList.get(hitList.indexOf(true) + 1), false)) {
									firstHitShipIndex = i;
								}
							}
							System.out.println("firstHitShipIndex: " + firstHitShipIndex + "\nranPosList: " + ranPosList);
							ranPos = (char) (ranPosList.get(firstHitShipIndex).charAt(0)) + "" + (Integer.parseInt(ranPosList.get(firstHitShipIndex).substring(1)) - 1);
							System.out.println("RanPos = " + ranPos);
						}
						System.out.println("RanPos = " + ranPos);
					}
				}
			}
		}
		if(one == 1) {
			ranPos = "D0";
		}
		System.out.println("if one > 2:  " + (one > 2));
		if(one > 2) {
			System.out.println("if hitShip.sunkShip(last ranPosList): " + (hitShip.sunkShip(p1board, 2, ranPosList.get(ranPosList.size() - 1).charAt(0) + "" + (Integer.parseInt(ranPosList.get(ranPosList.size() - 1).substring(1)) + 1), false) != 0) + "\nand the cords are " + ranPosList.get(ranPosList.size() - 1).charAt(0) + "" + (Integer.parseInt(ranPosList.get(ranPosList.size() - 1).substring(1)) + 1));
			if(hitShip.sunkShip(p1board, 2, ranPosList.get(ranPosList.size() - 1).charAt(0) + "" + (Integer.parseInt(ranPosList.get(ranPosList.size() - 1).substring(1)) + 1), false) != 0) {
				System.out.println("1: Setting AtSymble and negOne to 0");
				System.out.println("RanPos = " + ranPos);
				AtSymble = 0;
				negOne = 0;
				if(hitList.contains(true)) {
					hitList.set(hitList.indexOf(true), false);
				}
			}
			else if(hitShip.sunkShip(p1board, 2, ranPosList.get(ranPosList.size() - 2).charAt(0) + "" + (Integer.parseInt(ranPosList.get(ranPosList.size() - 2).substring(1)) + 1), false) != 0) {
				System.out.println("2: Setting AtSymble and negOne to 0");
				System.out.println("RanPos = " + ranPos);
				AtSymble = 0;
				negOne = 0;
				if(hitList.contains(true)) {
					hitList.set(hitList.indexOf(true), false);
				}
			}
			System.out.println("RanPos>ist = " + ranPosList);
			if(hitShip.hitShip(ranPosList.get(ranPosList.size() - 1).charAt(0) + "" + (Integer.parseInt(ranPosList.get(ranPosList.size() - 1).substring(1)) + 1), 2, false, false) == true && hitShip.sunkShip(p1board, 2, ranPosList.get(ranPosList.size() - 1).charAt(0) + "" + (Integer.parseInt(ranPosList.get(ranPosList.size() - 1).substring(1)) + 1), false) == 0) {
				notSunkinShips.add(ranPos);
			}
			else if(hitShip.sunkShip(p1board, 2, ranPosList.get(ranPosList.size() - 1).charAt(0) + "" + (Integer.parseInt(ranPosList.get(ranPosList.size() - 1).substring(1)) + 1), false) != 0) {

			}
		}
		System.out.println("RanPos = " + ranPos);
		if(ranPosList.contains(ranPos)) {
			System.out.println("Entered loop because generated the same position   ####2222222. Original: " + ranPos);
			do {
				ranPos = (char) ('A' + (int) (Math.random() * 10)) + "" + (int) (Math.random() * 10);
				System.out.println("the new generated cords are " + ranPos);
			}
			while(ranPosList.contains(ranPos));
			System.out.println("RanPosList = " + ranPosList);
		}
		ranPosList.add(ranPos);
		System.out.println("Computer chooses: " + ranPos.charAt(0) + "" + (Integer.parseInt(ranPos.substring(1)) + 1));
		if(hitShip.hitShip(ranPos.charAt(0) + "" + (Integer.parseInt(ranPos.substring(1)) + 1), 2, !true, true)) {
			if(one <= 1) {
				System.out.println("adding true to hitList");
				hitList.add(true);
			}
			else {
				if(hitShip.hitShip(ranPosList.get(ranPosList.size() - 1).charAt(0) + "" + (Integer.parseInt(ranPosList.get(ranPosList.size() - 1).substring(1)) + 1), 2, false, false) == true && (getTypeOfShipFromCords(p1board, 2, ranPosList.get(ranPosList.size() - 1).charAt(0) + "" + (Integer.parseInt(ranPosList.get(ranPosList.size() - 1).substring(1)) + 1), false) != getTypeOfShipFromCords(p1board, 2, ranPosList.get(ranPosList.size() - 2).charAt(0) + "" + (Integer.parseInt(ranPosList.get(ranPosList.size() - 2).substring(1)) + 1), false))) {
					if(direction == false) {
					    if(one > 3) {
					        if((getTypeOfShipFromCords(p1board, 2, ranPosList.get(ranPosList.size() - 1).charAt(0) + "" + (Integer.parseInt(ranPosList.get(ranPosList.size() - 1).substring(1)) + 1), false) == getTypeOfShipFromCords(p1board, 2, ranPosList.get(ranPosList.size() - 3).charAt(0) + "" + (Integer.parseInt(ranPosList.get(ranPosList.size() - 3).substring(1)) + 1), false))) {
					            System.out.println("hello... setting AtSymble to 4\nadding true to hitList");
					            AtSymble = 4;
					            hitList.add(true);
					        }
					    }
					    else {
					        System.out.println("setiitng AtSymble to 0\nadding false to hitList");
						    AtSymble = 0;
						    hitList.add(false);
					    }
					}
					else if(direction == true) {
					    System.out.println("setiitng AtSymble to 0\nadding false to hitList");
						negOne = 0;
						hitList.add(false);
					}
				}
				else {
					System.out.println("adding true to hitList");
					hitList.add(true);
				}
			}
			if(hitShip.sunkShip(p1board, 2, ranPosList.get(ranPosList.size() - 1).charAt(0) + "" + (Integer.parseInt(ranPosList.get(ranPosList.size() - 1).substring(1)) + 1), false) != 0) {
				sunkShipCount ++;
				System.out.println("added one to sunkShipCount: " + sunkShipCount);
			}
			return true;
		}
		else {
			hitList.add(false);
			return false;
		}
	}
	//what does it sound like?... it preveiws the given board!
	public static void preveiwBoard(String[][] board, String cords, int length, boolean direction) {
		String[][] prevBoard = createCopy(board);
		int row = cords.charAt(0) - 'A';
		int col = Integer.parseInt(cords.substring(1, cords.length())) - 1;
		if(checkShipPlacement(prevBoard, cords, length, direction, false)) {
			for(int i = 0; i < length; i = i + 1) {
				if(direction) {
					if(length == 5) {
						prevBoard[row][col + i] = "C";
					}
					else if(length == 4) {
						prevBoard[row][col + i] = "B";
					}
					else if(length == 3) {
						prevBoard[row][col + i] = "D";
					}
				}
				else if(!direction) {
					if(length == 5) {
						prevBoard[row + i][col] = "C";
					}
					else if(length == 4) {
						prevBoard[row + i][col] = "B";
					}
					else if(length == 3) {
						prevBoard[row + i][col] = "D";
					}
				}
			}
			printBoard(prevBoard);
		}
	}
	//Checks if the given ship cords and its given length is a valid place to use!
	public static boolean checkShipPlacement(String[][] board, String cords, int length, boolean direction, boolean comOrNot) {
		int row = cords.charAt(0) - 'A';
		int col = Integer.parseInt(cords.substring(1, cords.length())) - 1;
		if(direction && col + length > board.length) {
			System.out.println("Error, ship out of bounds");
			return false;
		}
		else if(!direction && row + length > board.length) {
			System.out.println("Error, ship out of bounds");
			return false;
		}
		for(int i = 0; i < length; i = i + 1) {
			if(direction && board[row][col + i].equals("C") || direction && board[row][col + i].equals("B") || direction && board[row][col + i].equals("D")) {
				System.out.println("Error, ship overlap");
				return false;
			}
			else if(!direction && board[row + i][col].equals("C") || !direction && board[row + i][col].equals("B") || !direction && board[row + i][col].equals("D")) {
				System.out.println("Error, ship overlap");
				return false;
			}
		}
		return true;
	}
	//returns a copy of given String[][] and returns a copy of it without poiting to the same String[][] in the memory!
	public static String[][] createCopy(String[][] original) {
		String[][] copy = new String[original.length][];
		for(int i = 0; i < original.length; i = i + 1) {
			copy[i] = Arrays.copyOf(original[i], original[i].length);
		}
		return copy;
	}
	public static boolean getDirection(String cords, int turn) {
		//false == verticle
		//true == horizontal
		boolean direction = false;
		int col = Integer.parseInt(cords.substring(1)) - 1;
		int row = cords.charAt(0) - 'A';
		System.out.println("getDirection methods given cords: " + cords + ". getDirection methods cords conversion is: " + ((char) col) + row);
		if(turn == 1 && play.p2OriginalBoard[row][col].equalsIgnoreCase("C")) {
			System.out.println("turn == 1 && play.p2OriginalBoard[row][col].equalsIgnoreCase(\"C\")" + (turn == 1) + (play.p2OriginalBoard[row][col].equalsIgnoreCase("C")));
			direction = play.p2cruiserDirection;
			return direction;
		}
		else if(turn == 2 && play.p1OriginalBoard[row][col].equalsIgnoreCase("C")) {
			System.out.println("turn == 2 && play.p1OriginalBoard[row][col].equalsIgnoreCase(\"C\")" + (turn == 2) + (play.p1OriginalBoard[row][col].equalsIgnoreCase("C")));
			direction = play.p1cruiserDirection;
			return direction;
		}
		else if(turn == 1 && play.p2OriginalBoard[row][col].equalsIgnoreCase("B")) {
			System.out.println("play.p2OriginalBoard[row][col].equalsIgnoreCase(\"C\")" + (play.p2OriginalBoard[row][col].equalsIgnoreCase("B")));
			if(play.p2battleshipCords[0] / 10 == col || play.p2battleshipCords[0] % 10 == row) {
				System.out.println("play.p2battleshipCords[0] / 10 == col || play.p2battleshipCords[0] % 10 == row" + (play.p2battleshipCords[0] / 10 == col) + (play.p2battleshipCords[0] % 10 == row));
				direction = play.p2battleshipDirection;
				return direction;
			}
			else if(play.p2battleship2Cords[0] / 10 == col || play.p2battleship2Cords[0] % 10 == row) {
				direction = play.p2battleship2Direction;
				return direction;
			}
		}
		else if(turn == 2 && play.p1OriginalBoard[row][col].equalsIgnoreCase("B")) {
			System.out.println("entered turn == 2 and play.p1OriginalBoard[row][col].equalsIgnoreCase(\"B\")");
			if(play.p1battleshipCords[0] / 10 == col || play.p1battleshipCords[0] % 10 == row) {
				direction = play.p1battleshipDirection;
				return direction;
			}
			else if(play.p1battleship2Cords[0] / 10 == col || play.p1battleship2Cords[0] % 10 == row) {
				direction = play.p1battleship2Direction;
				return direction;
			}
		}
		else if(turn == 1 && play.p2OriginalBoard[row][col].equalsIgnoreCase("D")) {
			direction = play.p2destroyerDirection;
			return direction;
		}
		else if(turn == 2 && play.p1OriginalBoard[row][col].equalsIgnoreCase("D")) {
			System.out.println("entered turn == 2 and play.p1OriginalBoard[row][col].equalsIgnoreCase(\"D\")");
			if(col >= play.p1destroyerCords[0] / 10 && col <= play.p1destroyerCords[1] / 10 && row >= play.p1destroyerCords[0] % 10 && row <= play.p1destroyerCords[1] % 10) {
				direction = play.p1destroyerDirection;
				return direction;
			}
			else if(col >= play.p1destroyer2Cords[0] / 10 && col <= play.p1destroyer2Cords[1] / 10 && row >= play.p1destroyer2Cords[0] % 10 && row <= play.p1destroyer2Cords[1] % 10) {
				direction = play.p1destroyer2Direction;
				return direction;
			}
		}
		System.out.println("Did not enter any if statements");
		return direction;
	}
	public static int getTypeOfShipFromCords(String[][] board, int turn, String cords, boolean comOrNot) {
		//returns 5 for cruiser
		//returns 41 for battleship #1
		//returns 42 for battleship #2
		//returns 31 for destroyer #1
		//returns 32 for destroyer #2
		//returns 0 for nothing
		System.out.println("Cords for getTypeOfShipFromCords is " + cords);
		String[][] copy = play.createCopy(board);
		int col = Integer.parseInt(cords.substring(1));
		int row = cords.charAt(0) - 'A';
		int length = 0;
		boolean direction = false;
		String typeOfShip = "";
		int sunk = 0;
		if((turn == 2 && (comOrNot == true || comOrNot == false))  && play.p1OriginalBoard[row][col].equalsIgnoreCase("C")) {
			length = 5;
			col = play.p1cruiserCords[0] / 10;
			row = play.p1cruiserCords[0] % 10;
			direction = play.p1cruiserDirection;
			typeOfShip = "C";
		}
		else if((turn == 2 && (comOrNot == true || comOrNot == false))  && play.p1OriginalBoard[row][col].equalsIgnoreCase("B")) {
			length = 4;
			System.out.println("col: " + col + ", row: " + row + ", col >= col of p1battleshipCords is " + (col >= play.p1battleshipCords[0] / 10) + " and is " + (play.p1battleshipCords[0] / 10));
			System.out.println("2: col: " + col + ", row: " + row + ", col >= col of p1battleship2Cords is " + (col >= play.p1battleship2Cords[0] / 10) + " and is " + (play.p1battleship2Cords[0] / 10));
			if(col >= play.p1battleshipCords[0] / 10 && col <= play.p1battleshipCords[1] / 10 && row >= play.p1battleshipCords[0] % 10 && row <= play.p1battleshipCords[1] % 10) {
				col = play.p1battleshipCords[0] / 10;
				row = play.p1battleshipCords[0] % 10;
				direction = play.p1battleshipDirection;
				typeOfShip = "B1";
			}
			else if(col >= play.p1battleship2Cords[0] / 10 && col <= play.p1battleship2Cords[1] / 10 && row >= play.p1battleship2Cords[0] % 10 && row <= play.p1battleship2Cords[1] % 10) {
				col = play.p1battleship2Cords[0] / 10;
				row = play.p1battleship2Cords[0] % 10;
				direction = play.p1battleship2Direction;
				typeOfShip = "B2";
			}
		}
		else if(turn == 2 && (comOrNot == true || comOrNot == false)  && play.p1OriginalBoard[row][col].equalsIgnoreCase("D")) {
			length = 3;
			if(col >= play.p1destroyerCords[0] / 10 && col <= play.p1destroyerCords[1] / 10 && row >= play.p1destroyerCords[0] % 10 && row <= play.p1destroyerCords[1] % 10) {
				col = play.p1destroyerCords[0] / 10;
				row = play.p1destroyerCords[0] % 10;
				direction = play.p1destroyerDirection;
				typeOfShip = "D1";
			}
			else if(col >= play.p1destroyer2Cords[0] / 10 && col <= play.p1destroyer2Cords[1] / 10 && row >= play.p1destroyer2Cords[0] % 10 && row <= play.p1destroyer2Cords[1] % 10) {
				col = play.p1destroyer2Cords[0] / 10;
				row = play.p1destroyer2Cords[0] % 10;
				direction = play.p1destroyer2Direction;
				typeOfShip = "D2";
			}
		}
		//false is vertical(row)
		//true is horizontal(col)
		System.out.println("col: " + col + " row: " + row + " length: " + length + " direction: " + direction + " typeOfShip: " + typeOfShip);
		if(typeOfShip.equals("C")) {
			System.out.println("Returning 5 for getTypeOfShipFromCords");
			return 5;
		}
		else if(typeOfShip.equals("B1")) {
			System.out.println("Returning 41 for getTypeOfShipFromCords");
			return 41;
		}
		else if(typeOfShip.equals("B2")) {
			System.out.println("Returning 42 for getTypeOfShipFromCords");
			return 42;
		}
		else if(typeOfShip.equals("D1")) {
			System.out.println("Returning 31 for getTypeOfShipFromCords");
			return 31;
		}
		else if(typeOfShip.equals("D2")) {
			System.out.println("Returning 32 for getTypeOfShipFromCords");
			return 32;
		}
		System.out.println("Returning default length(" + length + ") for getTypeOfShipFromCords");
		return length;
	}
}
