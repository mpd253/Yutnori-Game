package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{  // fxml 실행하기 위한 클래스
   
   @Override
    public void start(Stage stage) {
       try {
           Parent root = FXMLLoader.load(getClass().getResource("First.fxml"));
           Scene first = new Scene(root);
           stage.setTitle("윷놀이");
           stage.setScene(first);

           stage.show();
       } catch(Exception e) {
          e.printStackTrace();
       }
    }
    
    public static void main(String[] args) {
       launch(args);
    }
    
}