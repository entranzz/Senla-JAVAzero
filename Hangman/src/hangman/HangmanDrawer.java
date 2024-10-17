package hangman;

public class HangmanDrawer {

    private final String[] stages = {
            """
          -----
          |   |
          |   
          |   
          |   
          |   
         _|_
        """,
            """
          -----
          |   |
          |   O
          |   
          |   
          |   
         _|_
        """,
            """
          -----
          |   |
          |   O
          |   |
          |   
          |   
         _|_
        """,
            """
          -----
          |   |
          |   O
          |  /|
          |   
          |   
         _|_
        """,
            """
          -----
          |   |
          |   O
          |  /|\\
          |   
          |   
         _|_
        """,
            """
          -----
          |   |
          |   O
          |  /|\\
          |  / 
          |   
         _|_
        """,
            """
          -----
          |   |
          |   O
          |  /|\\
          |  / \\
          |   
         _|_
        """
    };


    public void drawHangman(int livesLeft) {
        int stage = stages.length - 1 - livesLeft;
        System.out.println(stages[stage]);
    }


    public void drawVictory() {

        String victoryDrawing = """
                   \\O/    
                    |     
                   / \\    
                You WIN! Good job!
                """;
        System.out.println(victoryDrawing);
    }
}
