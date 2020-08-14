public class Driver
{
    public static void main(String[] args)
    {
        int[] test = new int[14];
        test[5] = 8;

        Mancala game = new Mancala();
        game.move(2);
        System.out.println(game);
        game.move(12);
        System.out.println(game);

        Mancala nice = new Mancala(test);
        nice.move(5);
        System.out.println(nice);
    }
}
