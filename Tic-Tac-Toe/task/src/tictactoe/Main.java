package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        //System.out.println("Enter cells: ");
        //var cells = scanner.nextLine();
        var cells = "         ";
        Game game = new Game(cells);
        game.print();
        while (game.getStatus().equals("Game not finished")){
            game.step();
        }
        game.printStatus();

    }




}
