package application;
import java.util.Random;

public class Yut {  // 윷을 나타내는 클래스

    int status;	    // 0 또는 1로 윗면, 뒷면 구분

    public int rollYut(){
        Random random = new Random();
        status = random.nextInt(2);
        return status;
    }
}