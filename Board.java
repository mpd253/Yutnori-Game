package application;

import java.util.ArrayList;

public class Board {    // �������� ��Ÿ���� Ŭ����

    Square[] squares;             // ������ ĭ(���)���� �迭
    ArrayList<Player> players;    // �÷��̾���� ����Ʈ
    int finishCount;              // �÷��̾���� ���ӿ� ������ ����
    int defeatCount;              // �÷��̾���� ���ӿ� �й��� ����(������ ���ϰ� ���� ���� ���� ����)

    Board(ArrayList<Player> players){
        finishCount = 0;
        defeatCount = 100;
        this.players = players;
        squares = new Square[30];
        for(int i = 1; i <= 29; i++){
            squares[i] = new Square();
        }
    }

    // ������ Ư�� ��Ͽ��� ���� ���� �Լ�
    void addPiece(int currentSquare, Piece piece){
        squares[currentSquare].pieces.add(piece);
    }

    // ������ Ư�� ������ ��� ������ �����ϴ� �Լ�
    void deletePieces(int currentSquare){
        squares[currentSquare].pieces.clear();
    }

    // ���� �̵��ϴ� �Լ�
    int movePiece(int currentSquare, int moveDistance, int turn) {

        boolean goalIn = false;
        boolean almostGoal = false;
        boolean eatAndRollAgain = false;
        int nextSquare = -1;

        // ���� �ƴҶ� ���� ��� ���
        if(moveDistance != -1){
            if(1 <= currentSquare && currentSquare <= 20 && currentSquare != 6 && currentSquare != 11){ // ���� ���ο��� ���� �ɶ�
                nextSquare = currentSquare + moveDistance;
                if(nextSquare == 21) { // �������� �� ��������
                    nextSquare = 1;
                    almostGoal = true;
                }
                else if(nextSquare > 21) { // ����
                    goalIn = true;
                    nextSquare = -1;
                    for(int i = 0; i < squares[currentSquare].pieces.size(); i++){
                        players.get(turn).addGoalInNumber();
                    }
                }
                else if(currentSquare == 1 && squares[currentSquare].pieces.get(0).almostGoal) { // �������� �ִµ� �ѹ��� �̹� �������� ����. �鵵�ϰ��� �ȵ�.
                    goalIn = true;
                    nextSquare = -1;
                    for(int i = 0; i < squares[currentSquare].pieces.size(); i++){
                        players.get(turn).addGoalInNumber();
                    }
                }
            }
            else if(currentSquare == 6){   // ������ ���� ĭ���� �����Ҷ�
                nextSquare = 21;
                moveDistance--;
                nextSquare = nextSquare + moveDistance;
            }
            else if(currentSquare == 11){  // ���� ���� ĭ���� �����Ҷ�
                nextSquare = 26;
                moveDistance--;
                nextSquare = nextSquare + moveDistance;
                if(nextSquare == 28){ // ���� ��ǥ ĭ�� ���߾��̸� ���߾� �ε����� ��ȯ
                    nextSquare = 23;
                }
                else if(29 <= nextSquare && nextSquare <= 30) { // ���� ��ǥ ĭ�� �Ʒ� �κ��� ���. ���� �ƴ�
                    nextSquare--;
                }
            }
            else if(currentSquare == 23){  // ���߾� ĭ���� �����Ҷ�
                nextSquare = 28;
                moveDistance--;
                nextSquare = nextSquare + moveDistance;
                if(nextSquare == 30) {     // �������� ��������
                    nextSquare = 1;
                    almostGoal = true;
                }
                else if(nextSquare > 30) { // ����
                    goalIn = true;
                    nextSquare = -1;
                    for(int i = 0; i < squares[currentSquare].pieces.size(); i++){
                        players.get(turn).addGoalInNumber();
                    }
                }
            }
            else if(21 <= currentSquare && currentSquare <= 25 && currentSquare != 23){ // ������ ������ ���� �Ʒ��� �������� �밢���� ������. ���߾��� �ƴ�
                nextSquare = currentSquare + moveDistance;
                if(nextSquare > 25){ // ���� �� �밢���� Ż���ϸ� ���� �Ʒ� ĭ���� ������ �� �̵� ����
                    moveDistance = nextSquare - 25;
                    moveDistance--;
                    nextSquare = 16;
                    nextSquare = nextSquare + moveDistance;
                }
            }
            else if(26 <= currentSquare && currentSquare <= 27){ // ���� ������ ������ �Ʒ��� �������� �밢������ �� �κп� ������. ���߾��� �ƴ�
                nextSquare = currentSquare + moveDistance;
                if(nextSquare == 27){ // �Ȱ��� ���κп� �������� ���
                }
                else if(nextSquare == 28){ // ���� ��ǥ ĭ�� ���߾��̸� ���߾� �ε����� ��ȯ
                    nextSquare = 23;
                }
                else if(29 <= nextSquare && nextSquare <= 30) { // ���� ��ǥ ĭ�� �Ʒ� �κ��� ���. ���� �ƴ�
                    nextSquare--;
                }
                else if(nextSquare == 31){ // ���� ĭ���� �� ���� ���
                    nextSquare = 1;
                    almostGoal = true;
                }
                else{ // ����
                    goalIn = true;
                    nextSquare = -1;
                    for(int i = 0; i < squares[currentSquare].pieces.size(); i++){
                        players.get(turn).addGoalInNumber();
                    }
                }
            }
            else if(28 <= currentSquare && currentSquare <= 29){ // ���� ������ ������ �Ʒ��� �������� �밢������ �Ʒ� �κп� ������. ���߾��� �ƴ�
                nextSquare = currentSquare + moveDistance;
                if(nextSquare == 29) { // �Ȱ��� �Ʒ� �κп� �������� ���

                }
                else if(nextSquare == 30){ // ���� ĭ���� �� ���� ���
                    nextSquare = 1;
                    almostGoal = true;
                }
                else{ // ����
                    goalIn = true;
                    nextSquare = -1;
                    for(int i = 0; i < squares[currentSquare].pieces.size(); i++){
                        players.get(turn).addGoalInNumber();
                    }
                }
            }
        }

        // ���� �϶� ���� ��� ���
        else {                  // ���� �϶�
            if(1 <= currentSquare && currentSquare <= 20 && currentSquare != 6 && currentSquare != 11){ // ���� ���ο��� ���� �ɶ�
                if(currentSquare == 1){                                             // ù��° ĭ�� �ִµ�
                    if(squares[currentSquare].pieces.get(0).almostGoal == true){    // �ѹ��� �̹� ��������
                        nextSquare = 20;
                    }
                    else{                                                           // ���� ó�� ����������
                        nextSquare = currentSquare;
                    }
                }
                else{                                                               // ù��° ĭ�� �ƴ� ������ ĭ��
                    nextSquare = currentSquare + moveDistance;
                }
            }
            else if(currentSquare == 23) {  // ���߾� ĭ���� �����Ҷ�
                nextSquare = 27;
            }
            else if(currentSquare == 21) {  // ������ �� �κ� ���ۿ� ������ ������ ���� ĭ���� ��
                nextSquare = 6;
            }
            else if(currentSquare == 26) {  // ���� �� �κ� ���ۿ� ������ ���� ���� ĭ���� ��
                nextSquare = 11;
            }
            else if(currentSquare == 24){   // ���߾� �ٷ� �� ���ʿ� ������ ���߾����� ��
                nextSquare = 23;
            }
            else if(currentSquare == 28){   // ���߾� �ٷ� �� �����ʿ� ������ ���߾����� ��
                nextSquare = 23;
            }
            else{                           // ������ ĭ��
                nextSquare = currentSquare + moveDistance;
            }
        }

        if(currentSquare == nextSquare)     // ��� ���������� ������ ���� �鵵�� ������ �ƹ��͵� ���ϰ� ����
            return currentSquare;

        eatAndRollAgain = eatOrMerge(currentSquare, nextSquare, goalIn, almostGoal, turn);    // ���� ���� ��� ���� ������ ����
        deletePieces(currentSquare);
        if(eatAndRollAgain)
            players.get(turn).eatAndRollAgain = true;

        checkFinishedPlayers();                 // ���� ������ �÷��̾ Ȯ��
        boolean finished = checkAllFinished();  // ��� �÷��̾���� ���� �������� Ȯ��

        if(finished == true){   // ��� �÷��̾ ��������
            return -2;
        }

        if(goalIn == true) {    // ����������
            return -1;
        }

        return nextSquare;      // �׳� ���� ������ ���
    }

//     �÷��̾��� ���� �������� �ϳ��� ���� ���� ���� ������ ���. �̶� ���ο� ���� �����ɶ�
//    boolean initializePiece(int turn){
//        if(players.get(turn).notOnBoardPieceNumber > 0 && players.get(turn).onBoardPieceNumber == 0 ){
//            eatOrMerge(1, 1, false, false, turn);
//            addPiece(1, new Piece(turn));
//            players.get(turn).addPieceOnBoard();
//            return true;
//        }
//        return false;
//    }

    // ���� ���� �԰ų� �Ʊ� ���� ���� �Լ�
    boolean eatOrMerge(int currentSquare, int nextSquare, boolean goalIn, boolean almostGoal, int turn) {
        boolean eatAndRollAgain = false;
        if(nextSquare == -1)                                          // �����϶��� �ƹ��͵� ����
            return false;
        if(squares[nextSquare].pieces.size() > 0 && goalIn == false){ // ��ǥ�� ������ �����ϰ� ������ �ƴҶ�
            int deletedPlayer = squares[nextSquare].pieces.get(0).player;
            if(deletedPlayer != turn){                                // �ٸ� �÷��̾��� ���� ��쿡�� ����
                for(int i = 0; i < squares[nextSquare].pieces.size(); i++){    // ������ ����ŭ �� �� ����
                    players.get(deletedPlayer).deletePieceOnBoard();
                }
                deletePieces(nextSquare);
                eatAndRollAgain = true;
            }
        }
        if (currentSquare != nextSquare && goalIn == false){                   // ���ο� ���� �����ϴ� ��찡 �ƴҶ�
            for(int i = 0; i < squares[currentSquare].pieces.size(); i++){     // ���� �ִ� ���� ������ ��ǥ�� �߰�
                if(almostGoal == true)
                    addPiece(nextSquare, new Piece(turn, almostGoal));
                else{
                    addPiece(nextSquare, new Piece(turn));
                }
            }
        }
        return eatAndRollAgain;
    }

    // ���� ������ �÷��̾ Ȯ���ϴ� �Լ�
    void checkFinishedPlayers(){
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).finish == false){
                if(players.get(i).onBoardPieceNumber == 0 && players.get(i).notOnBoardPieceNumber == 0 && players.get(i).goalInNumber == 0){
                    players.get(i).finish = true;
                    players.get(i).finishOrder = defeatCount;
                    defeatCount++;
                }
                else if(players.get(i).onBoardPieceNumber == 0 && players.get(i).notOnBoardPieceNumber == 0 && players.get(i).goalInNumber != 0){
                    players.get(i).finish = true;
                    players.get(i).finishOrder = finishCount;
                    finishCount++;
                }
            }
        }
    }

//     ��� �÷��̾���� ���� �������� Ȯ���ϴ� �Լ�
    boolean checkAllFinished(){
        boolean check = true;
        for(int i = 0; i < players.size(); i++){
            check = players.get(i).finish;
            if(check == false)
                return false;
        }
        return check;
    }
}