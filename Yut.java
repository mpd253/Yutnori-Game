package application;
import java.util.Random;

public class Yut {  // ���� ��Ÿ���� Ŭ����

    int status;	    // 0 �Ǵ� 1�� ����, �޸� ����

    public int rollYut(){
        Random random = new Random();
        status = random.nextInt(2);
        return status;
    }
}