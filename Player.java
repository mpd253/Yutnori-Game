package application;
import javafx.scene.paint.Color;

public class Player implements Comparable<Player>{  // �÷��̾ ��Ÿ���� Ŭ����

    int playerID;                 // �ĺ���
    int onBoardPieceNumber;       // ���� ���� �ִ� �� ���� 	 
    int notOnBoardPieceNumber;    // ���忡 ���� ���� �� ����
    int goalInNumber;             // ������ �� ����
    boolean turn;                 // ���� �� ����
    Color color;                  // �÷��̾ ��Ÿ���� ��
    boolean finish;               // ���� ���� ����
    int finishOrder;              // ���� ���� ����
    boolean eatAndRollAgain;
    int count;					// �� ���� Ƚ��

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

    void addPieceOnBoard(){       // ���� ���� ���� �ϳ� ����
        onBoardPieceNumber++;
        notOnBoardPieceNumber--;
    }

    void deletePieceOnBoard(){    // ���� ������ ���� ���ֱ�
        onBoardPieceNumber--;
        notOnBoardPieceNumber++;
    }

    void addGoalInNumber(){       // �����ϱ�
        goalInNumber++;
        onBoardPieceNumber--;
    }

    Color getColor(){             // �÷��̾� �� getter
        return color;
    }

    @Override
    public int compareTo(Player p) {    // ������ �÷��̾� ��ü ���� ���
        if(this.goalInNumber < p.goalInNumber) {
            return 1;                               // ������ ���� ���ؼ��� ��������
        }
        else if(this.goalInNumber == p.goalInNumber) {
            if(this.finishOrder > p.finishOrder) {  // y�� ���ؼ��� ��������
                return 1;
            }
        }
        return -1;
    }
}