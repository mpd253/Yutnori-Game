package application;

public class Piece{       // ���� ��Ÿ���� Ŭ����
    int player;                   // � �÷��̾ �����ϰ� �ִ���
    boolean almostGoal;           // �ѹ��� ���� ���� ĭ�� �־� ������ �����϶� ������ �����ϴ� ���� ����

    Piece(int player) {
        this.player = player;
        this.almostGoal = false;
    }

    Piece(int player, boolean almostGoal){
        this.player = player;
        this.almostGoal = almostGoal;
    }
}