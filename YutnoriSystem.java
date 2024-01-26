package application;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Collections;

public class YutnoriSystem {    // ������ ���� ��� ������ �ִ� �ֻ��� ��

    int pieceNum;
    int playerNum;
    ArrayList<Player> playingPlayer;
    Yut[] yuts = new Yut[4];
    Board board;

    YutnoriSystem(){
        playingPlayer = new ArrayList<>();
        board = new Board(playingPlayer);
        for(int i = 0; i < 4; i++){
            yuts[i] = new Yut();        // yut �ʱ�ȭ
        }
    }

    // �÷��̾���� ���� ������ ����. �������� �� ����Ҷ� ���
    void calcRank(){
        Collections.sort(playingPlayer);
    }

    // ����, ��, ��, ��, ��, ���� ����� ��ȯ
    int rollYuts() {

        int yutResult = 0;	                                // 1�̸� ��(���� ���� �ִ� ���̸� ����), 2�� ��, 3�� ��..
        for(int i = 0; i < 4; i++){
            yutResult += yuts[i].rollYut();                 // ���� ���� ���� �� ����� ����
        }
        if(yuts[0].status == 1 && yutResult == 1) return 5; // yut[0]�� ������ ��Ÿ���� ��
        return yutResult;
    }

    // ������ �����Ҷ� ���������� �ʱ�ȭ
    void startGame(int playerNum, int pieceNum) {
        this.playerNum = playerNum;
        this.pieceNum = pieceNum;

        for(int i = 0; i < playerNum; i++) {
            playingPlayer.add(new Player(pieceNum, i));     // �÷��̾� ����ŭ arraylist ���� �Ҵ�
        }

//        board.addPiece(1, new Piece(0));
//        playingPlayer.get(0).addPieceOnBoard();
    }

    // ������ ���� �� ����� ���� ���������� ������ �Ÿ� ���
    int switchYut(String yut){
        int distance = 0;
        switch(yut){
            case "����":
                distance =  -1;
                break;
            case "��":
                distance = 1;
                break;
            case "��":
                distance = 2;
                break;
            case "��":
                distance = 3;
                break;
            case "��":
                distance = 4;
                break;
            case "��":
                distance = 5;
                break;
        }
        return distance;
    }

    // ���� �÷��̾��� ���� ����ϴ� �޼ҵ�
    int nextTurn() {

        int i = currentTurn();
        int count = 1;
        while(count < playerNum){
            if(playingPlayer.get(i % playerNum).turn == true && playingPlayer.get((i+count) % playerNum).finish == false){
                playingPlayer.get((i+count) % playerNum).turn = true;
                playingPlayer.get(i % playerNum).turn = false;
                return (i+count)% playerNum;
            }
            count++;
        }
        return i;
    }

    // ���� � �÷��̾��� ������ ����
    int currentTurn(){
        for(int i = 0; i < playingPlayer.size(); i++) {
            if(playingPlayer.get(i).turn == true)
                return i;
        }
        return 0;
    }

    // ���� ���� �÷��̾��� ���� ����
    Color currentColor(int turn){
        return playingPlayer.get(turn).getColor();
    }
}
