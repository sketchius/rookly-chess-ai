package huhtala.bryce;

public class Application {

    public static void main(String[] args) {
        Game game = new Game();

        System.out.println(game.getBoard().toString());

        Board board2 = new Board(game.getBoard(),new Move(new Point(3,1), new Point(3,2)));

        System.out.println(board2.toString());
    }
}
