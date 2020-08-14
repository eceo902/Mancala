public class Mancala
{
    private int[] board;

    public Mancala()
    {
        board = new int[14];

        for (int i = 0; i < board.length - 1; i++)
            board[i] = 4;

        board[6] = 0;
    }

    public Mancala(int[] board)
    {
        this.board = board;
    }

    /**
     * Returns the number of seeds on the specified
     *  player's side of the board excluding their
     *  Mancala.
     *
     * Precondition: player == 1 || player == 2
     *
     * @param player
     * @return
     */
    public int getSeeds(int player)
    {
        int sum = 0;

        if(player == 1)
            for (int i = 0; i < board.length - 1; i++)
                sum += board[i];
        else
            for (int i = board.length / 2; i < board.length - 1; i++)
                sum += board[i];

        return sum;
    }

    public int[] getBoard()
    {
        return board;
    }

    /**
     * Performs a sowing action by removing all
     *  the seeds from the specified pit and distributing
     *  one seen in each subsequent pit including the
     *  active player's Mancala and enemy pits but excluding
     *  the enemy Mancala.
     *
     *  Precondition: 0 <= pit < board.length - 1
     *                  pit != board.length / 2 - 1
     *                  board[pit] > 0
     * @param pit
     * @return 1 if player 1 should go next,
     *          2 if player 2 should go next.
     */
    public int move(int pit)
    {
        int activePlayer = pit >= board.length / 2 ? 2 : 1;
        int activeMancala = pit >= board.length / 2 ?
                board.length - 1 :
                board.length / 2 - 1;
        int enemyMancala = pit >= board.length / 2 ?
                board.length / 2 - 1 :
                board.length - 1;

        int seeds = board[pit];
        board[pit] = 0;

        while(seeds > 0)
        {
            pit = (pit + 1) % board.length;
            if(pit != enemyMancala)
            {
                board[pit]++;
                seeds--;
            }
        }

        if(pit == activeMancala)
            return activePlayer;

        int opposite = board.length - 2 - pit;
        if(board[pit] == 1 && pit / (board.length / 2) + 1 == activePlayer && board[opposite] > 0)
        {
            board[activeMancala] += board[opposite] + board[pit];
            board[opposite] = 0;
            board[pit] = 0;
        }

        return 3 - activePlayer;
    }

    public boolean gameOver()
    {
        return getSeeds(1) == 0 || getSeeds(2) == 0;
    }

    /**
     * Returns one of the following:
     *  -1: the game is not over
     *  0: the game ends in a tie
     *  1: player 1 is the winner
     *  2: player 2 is the winner
     *
     * @return
     */
    public int getWinner()
    {
        if(!gameOver())
            return -1;

        int one = getSeeds(1) + board[board.length / 2 - 1];
        int two = getSeeds(2) + board[board.length - 1];

        if(one == two)
            return 0;

        return one > two ? 1 : 2;
    }

    public String toString()
    {
        String rtn = "";

        for (int i = board.length - 1; i >= board.length / 2; i--)
            rtn += board[i] + "\t";

        rtn += "\n\t";

        for (int i = 0; i < board.length / 2; i++)
            rtn += board[i] + "\t";

        return rtn;
    }
}
