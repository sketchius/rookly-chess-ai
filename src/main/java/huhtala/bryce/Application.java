package huhtala.bryce;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Game game = new Game();

        Scanner scanner = new Scanner(System.in);

        System.out.println(game.getBoard().toString());

        Board board = new Board();

        while (true) {
            scanner.nextLine();
            board = new Board(board, board.getRandomMove());
            System.out.println(board.toString());
        }
    }
}
