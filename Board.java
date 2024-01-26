package application;

import java.util.ArrayList;

public class Board {    // 보드판을 나타내는 클래스

    Square[] squares;             // 각각의 칸(블록)들의 배열
    ArrayList<Player> players;    // 플레이어들의 리스트
    int finishCount;              // 플레이어들이 게임올 종료한 순서
    int defeatCount;              // 플레이어들이 게임에 패배한 순서(골인을 못하고 말만 먹힌 교착 상태)

    Board(ArrayList<Player> players){
        finishCount = 0;
        defeatCount = 100;
        this.players = players;
        squares = new Square[30];
        for(int i = 1; i <= 29; i++){
            squares[i] = new Square();
        }
    }

    // 보드의 특정 블록에다 말을 놓는 함수
    void addPiece(int currentSquare, Piece piece){
        squares[currentSquare].pieces.add(piece);
    }

    // 보드의 특정 볼록의 모든 말들을 삭제하는 함수
    void deletePieces(int currentSquare){
        squares[currentSquare].pieces.clear();
    }

    // 말을 이동하는 함수
    int movePiece(int currentSquare, int moveDistance, int turn) {

        boolean goalIn = false;
        boolean almostGoal = false;
        boolean eatAndRollAgain = false;
        int nextSquare = -1;

        // 빽도 아닐때 다음 블록 계산
        if(moveDistance != -1){
            if(1 <= currentSquare && currentSquare <= 20 && currentSquare != 6 && currentSquare != 11){ // 정상 라인에서 진행 될때
                nextSquare = currentSquare + moveDistance;
                if(nextSquare == 21) { // 원점에서 딱 멈췄을때
                    nextSquare = 1;
                    almostGoal = true;
                }
                else if(nextSquare > 21) { // 골인
                    goalIn = true;
                    nextSquare = -1;
                    for(int i = 0; i < squares[currentSquare].pieces.size(); i++){
                        players.get(turn).addGoalInNumber();
                    }
                }
                else if(currentSquare == 1 && squares[currentSquare].pieces.get(0).almostGoal) { // 시작점에 있는데 한바퀴 이미 돌았을때 골인. 백도일경우는 안됨.
                    goalIn = true;
                    nextSquare = -1;
                    for(int i = 0; i < squares[currentSquare].pieces.size(); i++){
                        players.get(turn).addGoalInNumber();
                    }
                }
            }
            else if(currentSquare == 6){   // 오른쪽 위에 칸에서 시작할때
                nextSquare = 21;
                moveDistance--;
                nextSquare = nextSquare + moveDistance;
            }
            else if(currentSquare == 11){  // 왼쪽 위에 칸에서 시작할때
                nextSquare = 26;
                moveDistance--;
                nextSquare = nextSquare + moveDistance;
                if(nextSquare == 28){ // 만약 목표 칸이 정중앙이면 정중앙 인덱스로 변환
                    nextSquare = 23;
                }
                else if(29 <= nextSquare && nextSquare <= 30) { // 만약 목표 칸이 아래 부분일 경우. 골인 아님
                    nextSquare--;
                }
            }
            else if(currentSquare == 23){  // 정중앙 칸에서 시작할때
                nextSquare = 28;
                moveDistance--;
                nextSquare = nextSquare + moveDistance;
                if(nextSquare == 30) {     // 원점에서 멈췄을때
                    nextSquare = 1;
                    almostGoal = true;
                }
                else if(nextSquare > 30) { // 골인
                    goalIn = true;
                    nextSquare = -1;
                    for(int i = 0; i < squares[currentSquare].pieces.size(); i++){
                        players.get(turn).addGoalInNumber();
                    }
                }
            }
            else if(21 <= currentSquare && currentSquare <= 25 && currentSquare != 23){ // 오른쪽 위에서 왼쪽 아래로 내려가는 대각선상에 있을때. 정중앙은 아님
                nextSquare = currentSquare + moveDistance;
                if(nextSquare > 25){ // 만약 그 대각선을 탈출하면 왼쪽 아래 칸으로 변형한 후 이동 진행
                    moveDistance = nextSquare - 25;
                    moveDistance--;
                    nextSquare = 16;
                    nextSquare = nextSquare + moveDistance;
                }
            }
            else if(26 <= currentSquare && currentSquare <= 27){ // 왼쪽 위에서 오른쪽 아래로 내려가는 대각선상중 윗 부분에 있을때. 정중앙은 아님
                nextSquare = currentSquare + moveDistance;
                if(nextSquare == 27){ // 똑같이 윗부분에 남아있을 경우
                }
                else if(nextSquare == 28){ // 만약 목표 칸이 정중앙이면 정중앙 인덱스로 변환
                    nextSquare = 23;
                }
                else if(29 <= nextSquare && nextSquare <= 30) { // 만약 목표 칸이 아래 부분일 경우. 골인 아님
                    nextSquare--;
                }
                else if(nextSquare == 31){ // 시작 칸에서 딱 멈출 경우
                    nextSquare = 1;
                    almostGoal = true;
                }
                else{ // 골인
                    goalIn = true;
                    nextSquare = -1;
                    for(int i = 0; i < squares[currentSquare].pieces.size(); i++){
                        players.get(turn).addGoalInNumber();
                    }
                }
            }
            else if(28 <= currentSquare && currentSquare <= 29){ // 왼쪽 위에서 오른쪽 아래로 내려가는 대각선상중 아래 부분에 있을때. 정중앙은 아님
                nextSquare = currentSquare + moveDistance;
                if(nextSquare == 29) { // 똑같이 아래 부분에 남아있을 경우

                }
                else if(nextSquare == 30){ // 시작 칸에서 딱 멈출 경우
                    nextSquare = 1;
                    almostGoal = true;
                }
                else{ // 골인
                    goalIn = true;
                    nextSquare = -1;
                    for(int i = 0; i < squares[currentSquare].pieces.size(); i++){
                        players.get(turn).addGoalInNumber();
                    }
                }
            }
        }

        // 빽도 일때 다음 블록 계산
        else {                  // 빽도 일때
            if(1 <= currentSquare && currentSquare <= 20 && currentSquare != 6 && currentSquare != 11){ // 정상 라인에서 진행 될때
                if(currentSquare == 1){                                             // 첫번째 칸에 있는데
                    if(squares[currentSquare].pieces.get(0).almostGoal == true){    // 한바퀴 이미 돌았을때
                        nextSquare = 20;
                    }
                    else{                                                           // 완전 처음 시작했을때
                        nextSquare = currentSquare;
                    }
                }
                else{                                                               // 첫번째 칸이 아닌 나머지 칸들
                    nextSquare = currentSquare + moveDistance;
                }
            }
            else if(currentSquare == 23) {  // 정중앙 칸에서 시작할때
                nextSquare = 27;
            }
            else if(currentSquare == 21) {  // 오른쪽 윗 부분 시작에 있을때 오른쪽 위쪽 칸으로 백
                nextSquare = 6;
            }
            else if(currentSquare == 26) {  // 왼쪽 윗 부분 시작에 있을때 왼쪽 위쪽 칸으로 백
                nextSquare = 11;
            }
            else if(currentSquare == 24){   // 정중앙 바로 앞 왼쪽에 있을때 정중앙으로 백
                nextSquare = 23;
            }
            else if(currentSquare == 28){   // 정중앙 바로 앞 오른쪽에 있을때 정중앙으로 백
                nextSquare = 23;
            }
            else{                           // 나머지 칸들
                nextSquare = currentSquare + moveDistance;
            }
        }

        if(currentSquare == nextSquare)     // 방금 시작점에서 생성된 말이 백도가 나오면 아무것도 안하고 리턴
            return currentSquare;

        eatAndRollAgain = eatOrMerge(currentSquare, nextSquare, goalIn, almostGoal, turn);    // 말이 먹힌 경우 먹힌 말들을 제거
        deletePieces(currentSquare);
        if(eatAndRollAgain)
            players.get(turn).eatAndRollAgain = true;

        checkFinishedPlayers();                 // 게임 종료한 플레이어를 확인
        boolean finished = checkAllFinished();  // 모든 플레이어들이 게임 종료한지 확인

        if(finished == true){   // 모든 플레이어가 끝났을때
            return -2;
        }

        if(goalIn == true) {    // 골인했을때
            return -1;
        }

        return nextSquare;      // 그냥 말이 움직인 경우
    }

//     플레이어의 말이 보드위에 하나도 없고 남은 말이 존재할 경우. 이때 새로운 말이 생성될때
//    boolean initializePiece(int turn){
//        if(players.get(turn).notOnBoardPieceNumber > 0 && players.get(turn).onBoardPieceNumber == 0 ){
//            eatOrMerge(1, 1, false, false, turn);
//            addPiece(1, new Piece(turn));
//            players.get(turn).addPieceOnBoard();
//            return true;
//        }
//        return false;
//    }

    // 잡힌 말을 먹거나 아군 말을 업는 함수
    boolean eatOrMerge(int currentSquare, int nextSquare, boolean goalIn, boolean almostGoal, int turn) {
        boolean eatAndRollAgain = false;
        if(nextSquare == -1)                                          // 골인일때는 아무것도 안함
            return false;
        if(squares[nextSquare].pieces.size() > 0 && goalIn == false){ // 목표에 말들이 존재하고 골인이 아닐때
            int deletedPlayer = squares[nextSquare].pieces.get(0).player;
            if(deletedPlayer != turn){                                // 다른 플레이어의 말일 경우에만 삭제
                for(int i = 0; i < squares[nextSquare].pieces.size(); i++){    // 삭제된 수만큼 말 수 감소
                    players.get(deletedPlayer).deletePieceOnBoard();
                }
                deletePieces(nextSquare);
                eatAndRollAgain = true;
            }
        }
        if (currentSquare != nextSquare && goalIn == false){                   // 새로운 말이 시작하는 경우가 아닐때
            for(int i = 0; i < squares[currentSquare].pieces.size(); i++){     // 원래 있던 곳의 말들을 목표에 추가
                if(almostGoal == true)
                    addPiece(nextSquare, new Piece(turn, almostGoal));
                else{
                    addPiece(nextSquare, new Piece(turn));
                }
            }
        }
        return eatAndRollAgain;
    }

    // 게임 종료한 플레이어를 확인하는 함수
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

//     모든 플레이어들이 게임 종료한지 확인하는 함수
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