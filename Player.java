package application;
import javafx.scene.paint.Color;

public class Player implements Comparable<Player>{  // 플레이어를 나타내는 클래스

    int playerID;                 // 식별자
    int onBoardPieceNumber;       // 보드 위에 있는 말 개수 	 
    int notOnBoardPieceNumber;    // 보드에 있지 않은 말 개수
    int goalInNumber;             // 골인한 말 개수
    boolean turn;                 // 현재 턴 여부
    Color color;                  // 플레이어를 나타내는 색
    boolean finish;               // 게임 종료 여부
    int finishOrder;              // 게임 종료 순서
    boolean eatAndRollAgain;
    int count;					// 윷 던지 횟수

    Player(int pieceNumber, int player){
        playerID = player;
        onBoardPieceNumber = 0;
        notOnBoardPieceNumber = pieceNumber;
        goalInNumber = 0;
        turn = false;
        finish = false;
        eatAndRollAgain = false;
        count = 0;
        switch (player){
            case 0: color = Color.ORANGE;
                break;
            case 1: color = Color.GREEN;
                break;
            case 2: color = Color.PURPLE;
                break;
            case 3: color = Color.PINK;
                break;
        }
    }

    void addPieceOnBoard(){       // 보드 위에 말을 하나 놓기
        onBoardPieceNumber++;
        notOnBoardPieceNumber--;
    }

    void deletePieceOnBoard(){    // 보드 위에서 말을 없애기
        onBoardPieceNumber--;
        notOnBoardPieceNumber++;
    }

    void addGoalInNumber(){       // 골인하기
        goalInNumber++;
        onBoardPieceNumber--;
    }

    Color getColor(){             // 플레이어 색 getter
        return color;
    }

    @Override
    public int compareTo(Player p) {    // 임의의 플레이어 객체 정렬 방법
        if(this.goalInNumber < p.goalInNumber) {
            return 1;                               // 골인한 수에 대해서는 내림차순
        }
        else if(this.goalInNumber == p.goalInNumber) {
            if(this.finishOrder > p.finishOrder) {  // y에 대해서는 오름차순
                return 1;
            }
        }
        return -1;
    }
}