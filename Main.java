package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{  // fxml �����ϱ� ���� Ŭ����
   
   @Override
    public void start(Stage stage) {
       try {
           Parent root = FXMLLoader.load(getClass().getResource("First.fxml"));
           Scene first = new Scene(root);
           stage.setTitle("������");
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