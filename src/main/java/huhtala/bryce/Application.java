package huhtala.bryce;

public class Application {

    public static void main(String[] args) {
        Game game = new Game();

        System.out.println(game.getBoard().toString());
    }
}
