package application;

public class Piece{       // 말을 나타내는 클래스
    int player;                   // 어떤 플레이어가 소유하고 있는지
    boolean almostGoal;           // 한바퀴 돌고 시작 칸에 있어 다음에 움직일때 무조건 골인하는 상태 유무

    Piece(int player) {
        this.player = player;
        this.almostGoal = false;
    }

    Piece(int player, boolean almostGoal){
        this.player = player;
        this.almostGoal = almostGoal;
    }
}