package application;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Collections;

public class YutnoriSystem {    // 윷놀이 모델의 모든 정보가 있는 최상위 모델

    int pieceNum;
    int playerNum;
    ArrayList<Player> playingPlayer;
    Yut[] yuts = new Yut[4];
    Board board;

    YutnoriSystem(){
        playingPlayer = new ArrayList<>();
        board = new Board(playingPlayer);
        for(int i = 0; i < 4; i++){
            yuts[i] = new Yut();        // yut 초기화
        }
    }

    // 플레이어들을 점수 순으로 정렬. 마지막에 순 계산할때 사용
    void calcRank(){
        Collections.sort(playingPlayer);
    }

    // 빽도, 도, 개, 결, 윷, 모의 결과를 반환
    int rollYuts() {

        int yutResult = 0;	                                // 1이면 도(만약 점이 있는 윷이면 빽도), 2면 개, 3면 걸..
        for(int i = 0; i < 4; i++){
            yutResult += yuts[i].rollYut();                 // 윷을 각각 던져 윷 결과를 얻음
        }
        if(yuts[0].status == 1 && yutResult == 1) return 5; // yut[0]은 빽도를 나타내는 윷
        return yutResult;
    }

    // 게임을 시작할때 변수값들은 초기화
    void startGame(int playerNum, int pieceNum) {
        this.playerNum = playerNum;
        this.pieceNum = pieceNum;

        for(int i = 0; i < playerNum; i++) {
            playingPlayer.add(new Player(pieceNum, i));     // 플레이어 수만큼 arraylist 공간 할당
        }

//        board.addPiece(1, new Piece(0));
//        playingPlayer.get(0).addPieceOnBoard();
    }

    // 선택한 말과 윷 목록을 통해 시작지점과 움직일 거리 계산
    int switchYut(String yut){
        int distance = 0;
        switch(yut){
            case "빽도":
                distance =  -1;
                break;
            case "도":
                distance = 1;
                break;
            case "개":
                distance = 2;
                break;
            case "걸":
                distance = 3;
                break;
            case "윷":
                distance = 4;
                break;
            case "모":
                distance = 5;
                break;
        }
        return distance;
    }

    // 다음 플레이어의 턴을 계산하는 메소드
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

    // 현재 어떤 플레이어의 턴인지 리턴
    int currentTurn(){
        for(int i = 0; i < playingPlayer.size(); i++) {
            if(playingPlayer.get(i).turn == true)
                return i;
        }
        return 0;
    }

    // 현재 턴인 플레이어의 색을 리턴
    Color currentColor(int turn){
        return playingPlayer.get(turn).getColor();
    }
}
