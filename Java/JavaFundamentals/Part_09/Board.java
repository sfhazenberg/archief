package assignment42;

class Board {

    private int[] currentBoardBack;
    private String[] currentBoardFront;

    Board(){
        initialBoard();
        currentBoardBack = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        String EMPTY_POS = "  ";
        currentBoardFront = new String[]{EMPTY_POS, EMPTY_POS, EMPTY_POS,
                EMPTY_POS, EMPTY_POS, EMPTY_POS,
                EMPTY_POS, EMPTY_POS, EMPTY_POS, };
    }

    void updateBoard(boolean isPlayerTurn, int position) {
        currentBoardBack[position] = isPlayerTurn ? 1 : 2;
        for (int i = 0; i < currentBoardBack.length; i++) {
            String PLAYER_POS = "XX";
            String CPU_POS = "OO";
            switch (currentBoardBack[i]) {
                case 1:
                    currentBoardFront[i] = PLAYER_POS;
                    break;
                case 2:
                    currentBoardFront[i] = CPU_POS;
                    break;
                default:
                    break;
            }
        }
        printBoard();
    }

    void printBoard() {
        String line = "────────────";
        String upperRow = String.format(
                "%s | %s | %s",
                currentBoardFront[0],
                currentBoardFront[1],
                currentBoardFront[2]
        );
        String middleRow = String.format(
                "%s | %s | %s",
                currentBoardFront[3],
                currentBoardFront[4],
                currentBoardFront[5]
        );
        String bottomRow = String.format(
                "%s | %s | %s",
                currentBoardFront[6],
                currentBoardFront[7],
                currentBoardFront[8]
        );

        System.out.println(
                upperRow + "\n" +
                line + "\n" +
                middleRow + "\n" +
                line + "\n" +
                bottomRow
        );
    }

    int isThreeInRow() {
        int result = 0;
        //checkt rij 1
        if(currentBoardBack[0] == currentBoardBack[1] && currentBoardBack[1] == currentBoardBack[2]) {
            if (currentBoardBack[0] == 1 || currentBoardBack[0] == 2) {
                result = currentBoardBack[0] == 1 ? 1 : 2;
            }
        }
        //checkt rij 2
        if(currentBoardBack[3] == currentBoardBack[4] && currentBoardBack[4] == currentBoardBack[5]) {
            if (currentBoardBack[3] == 1 || currentBoardBack[3] == 2) {
                result = currentBoardBack[3] == 1 ? 1 : 2;
            }
        }
        //checkt rij 3
        if(currentBoardBack[6] == currentBoardBack[7] && currentBoardBack[7] == currentBoardBack[8]) {
            if (currentBoardBack[6] == 1 || currentBoardBack[6] == 2) {
                result = currentBoardBack[6] == 1 ? 1 : 2;
            }
        }
        //checkt kolom A
        if(currentBoardBack[0] == currentBoardBack[3] && currentBoardBack[3] == currentBoardBack[6]) {
            if (currentBoardBack[0] == 1 || currentBoardBack[0] == 2) {
                result = currentBoardBack[0] == 1 ? 1 : 2;
            }
        }
        //checkt kolom B
        if(currentBoardBack[1] == currentBoardBack[4] && currentBoardBack[4] == currentBoardBack[7]) {
            if (currentBoardBack[1] == 1 || currentBoardBack[1] == 2) {
                result = currentBoardBack[1] == 1 ? 1 : 2;
            }
        }
        //checkt kolom C
        if(currentBoardBack[2] == currentBoardBack[5] && currentBoardBack[5] == currentBoardBack[8]) {
            if (currentBoardBack[2] == 1 || currentBoardBack[2] == 2) {
                result = currentBoardBack[2] == 1 ? 1 : 2;
            }
        }
        //checkt diagonaal linksboven <-> rechtsonder
        if(currentBoardBack[0] == currentBoardBack[4] && currentBoardBack[4] == currentBoardBack[8]) {
            if (currentBoardBack[0] == 1 || currentBoardBack[0] == 2) {
                result = currentBoardBack[0] == 1 ? 1 : 2;
            }
        }
        //checkt diagonaal rechtsboven <-> linksonder
        if(currentBoardBack[2] == currentBoardBack[4] && currentBoardBack[4] == currentBoardBack[6]) {
            if (currentBoardBack[2] == 1 || currentBoardBack[2] == 2) {
                result = currentBoardBack[2] == 1 ? 1 : 2;
            }
        }

        return result;
    }

    void initialBoard() {
        System.out.println("A1 | B1 | C1");
        System.out.println("────────────");
        System.out.println("A2 | B2 | C2");
        System.out.println("────────────");
        System.out.println("A3 | B3 | C3");
    }

    int[] getCurrentBoardBack() {
        return currentBoardBack;
    }

    String[] getCurrentBoardFront() {
        return currentBoardFront;
    }
}
