package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Game {
    char[][] field = new char[3][3];
    char[][] arrComb = new char[8][3];
    boolean isXWin;
    boolean isOWin;
    int countX;
    int countY;
    String status = "Game not finished";
    char item = 'O';

    public Game(String str) {
        field[0] = str.substring(0, 3).toCharArray();
        field[1] = str.substring(3, 6).toCharArray();
        field[2] = str.substring(6).toCharArray();
        loadStatus(str);
    }

    private void loadStatus(String str) {
        isXWin = isWin('X');
        isOWin = isWin('O');
        countX = getCount(str, 'X');
        countY = getCount(str, 'O');
    }

    private int getCount(String str, char x) {
        var rez = 0;
        for (char c : str.toCharArray()) {
            if (c == x) {
                rez++;
            }
        }
        return rez;
    }

    private boolean isWin(char x) {
        for (int i = 0; i < 3; i++) {
            if (field[i][0] == x && field[i][1] == x && field[i][2] == x) {
                return true;
            }
            if (field[0][i] == x && field[1][i] == x && field[2][i] == x) {
                return true;
            }
        }
        if (field[0][0] == x && field[1][1] == x && field[2][2] == x) {
            return true;
        }
        if (field[0][2] == x && field[1][1] == x && field[2][0] == x) {
            return true;
        }
        return false;
    }

    public String getStatus() {
        StringBuilder builder = new StringBuilder();
        for ( char[] s : field) {
            for ( char c: s) {
                builder.append(c);
            }
        }
        loadStatus(builder.toString());

        if (isXWin && isOWin) return "Impossible";
        else if (isOWin) return "O wins";
        else if (isXWin) return "X wins";
        else if (countY + countX == 9) return "Draw";
        else if (Math.max(countX, countY) - Math.min(countX, countY) > 1) return "Impossible";
        else return "Game not finished";
    }

    public void print() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
        //printStatus();
    }

    public void printStatus() {
        System.out.println(getStatus());
    }


    public void step() {
        Scanner scanner = new Scanner(System.in);
        item = item == 'O' ? 'X' : 'O';

        while (this.getStatus().equals("Game not finished")) {
            System.out.print("Enter the coordinates: ");
            try {
                var x = scanner.next();
                var y = scanner.next();
                addCoordinates(x, y, item);
                print();
                this.status = getStatus();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Coordinates should be from 1 to 3!");
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
            }
        }
    }

    private void addCoordinates(String x, String y, char item) throws Exception {
        int x1 = 0;
        int y1 = 0;

        try {
            x1 = Integer.parseInt(x) - 1;
            y1 = Integer.parseInt(y) - 1;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("You should enter numbers!");
        }

        if (field[x1][y1] == 'X' || field[x1][y1] == 'O') {
            throw new IllegalArgumentException("This cell is occupied! Choose another one!");
        }
        field[x1][y1] = item;
    }
}
