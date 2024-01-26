package application;
   
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;


public class ClientMain extends Application {
   
   Socket socket;
   // �޼����� ��µǴ� ����
   TextArea textArea;
   
   // Ŭ���̾�Ʈ ���α׷� ���� �޼ҵ�
   // � ip, port ��ȣ�� �������� ����
   public void startClient(String IP, int port) {
      Thread thread = new Thread() {
         public void run() {
            try {
               // ���� �ʱ�ȭ
               socket = new Socket(IP, port);
               receive();
            } catch(Exception e) {
               if(!socket.isClosed()) {
                  stopClient();
                  System.out.println("[���� ���� ����]");
                  // ���α׷� ��ü�� ����
                  Platform.exit();
               }
            }
         }
      };
      thread.start();
   }
   
   // Ŭ���̾�Ʈ ���α׷� ���� �޼ҵ�
   public void stopClient() {
      try {
         if(socket != null && socket.isClosed()) {
            socket.close();
         }
      } catch(Exception e) {
         e.printStackTrace();
      }
   }
   
   // �����κ��� �޼����� ���޹޴� �޼ҵ�
   public void receive() {
      // �����κ��� �޼����� ��� ���޹ޱ� ���� ���ѷ���
      while(true) {
         try {
            // ���Ͽ��� InputStream�� ���� �����κ��� �޼����� ���޹��� �� �ֵ��� ����
            InputStream in = socket.getInputStream();
            // buffer�� ���� 512����Ʈ�� ��� buffer�� ��� ����
            byte[] buffer = new byte[512];
            // read() �Լ��� ���� �Է��� ����
            int length = in.read(buffer);
            if(length == -1) throw new IOException();
            String message = new String(buffer, 0, length, "UTF-8");
            Platform.runLater(() -> {
               // GUI�� ��ҷ�, ȭ�鿡 �޼����� �ְ���� ���� �����
               textArea.appendText(message);
            });
         } catch(Exception e) {
             stopClient();
             break;
         }
      }
   }
   
   // ������ �޼����� �����ϴ� �޼ҵ�
   public void send(String message) {
      Thread thread = new Thread() {
         public void run() {
            try {
               // OutputStream ��ü�� ���� ������ �޼����� ����
               OutputStream out = socket.getOutputStream();
               byte[] buffer = message.getBytes("UTF-8");
               out.write(buffer);
               // �޼��� ������ ���� �˸�
               out.flush();
            } catch(Exception e) {
               stopClient();
            }
         }
      };
      thread.start();
   }
   
   // ������ ���α׷��� ���۽�Ű�� �޼ҵ�
   @Override
   public void start(Stage primaryStage) {
      BorderPane root = new BorderPane();
      root.setPadding(new Insets(5));
      
      // HBox�� ���� BorderPane ���� layout ����
      HBox hbox = new HBox();
      // HBox�� �����ֱ�
      hbox.setSpacing(5);
      
      TextField userName = new TextField();
      userName.setPrefWidth(150);
      // �⺻ ����
      userName.setPromptText("�г����� �Է��ϼ���.");
      // HBox ���ο��� �ش� textfield�� ����� �� �ֵ��� ����
      HBox.setHgrow(userName, Priority.ALWAYS);
      
      // IP�ּҰ� �� �� �ְ� ����
      TextField IPText = new TextField("10.20.28.31");
      TextField portText = new TextField("9876");
      portText.setPrefWidth(80);
      
      hbox.getChildren().addAll(userName, IPText, portText);
      // HBox�� BorderPane ���ʿ� �޾��ֵ��� ����
      root.setTop(hbox);
      
      // ȭ���� ������ �� ��ü �ʱ�ȭ
      textArea = new TextArea();
      // ������ ������ �� ������ ����
      textArea.setEditable(false);
      root.setCenter(textArea);
      
      TextField input = new TextField();
      input.setPrefWidth(Double.MAX_VALUE);
      // �����ϱ� �������� �޼����� ������ �� ������ ��
      input.setDisable(true);
      
      input.setOnAction(event -> {
         send(userName.getText() + ": " + input.getText() + "\n");
         input.setText("");
         // �ٽ� �޼����� ������ �� �ֵ��� focus�� ����
         input.requestFocus();
      });
      
      Button sendButton = new Button("������");
      // �����ϱ� �������� �̿��� �� ������ ��
      sendButton.setDisable(true);
      
      sendButton.setOnAction(event -> {
         send(userName.getText() + ": " + input.getText() + "\n");
         input.setText("");
         input.requestFocus();
      });
      
      Button connectionButton = new Button("�����ϱ�");
      connectionButton.setOnAction(event -> {
         if(connectionButton.getText().equals("�����ϱ�")) {
            int port = 9876;
            try {
               // ����ڰ� �Է���(��Ʈ��ȣ) �ؽ�Ʈ ������ ���� ���·� ��ȯ�Ͽ� �ٽ� ����
               // ������ ��Ʈ ��ȣ�� �Է��ϸ� �� ��Ʈ ��ȣ�� ������ �� �ֵ��� ����
               port = Integer.parseInt(portText.getText());
            } catch(Exception e) {
               e.printStackTrace();
            }
            // Ư���� IP�ּҿ� ��� ��Ʈ ��ȣ�� ������ �� �ֵ��� ����
            startClient(IPText.getText(), port);
            // ȭ�鿡 ����� �� �ֵ��� ����
            Platform.runLater(() -> {
               textArea.appendText("[ä�ù� ����]\n");
            });
            connectionButton.setText("�����ϱ�");
            // ����ڰ� ������ ���� �Է��Ͽ� ��ư�� ������ �� ����������
            input.setDisable(false);
            sendButton.setDisable(false);
            // �ٷ� �޼����� �Է��� �� �ֵ��� focus
            input.requestFocus();
         
         // ��ư�� �����ϱⰡ �ƴ� �����ϱ���
         } else {
            stopClient();
            Platform.runLater(() -> {
               textArea.appendText("[ä�ù� ����]");
            });
            connectionButton.setText("�����ϱ�");
            // �ٽ� �Է�ĭ �� ��ư�� ���� �� ������ ����
            input.setDisable(true);
            sendButton.setDisable(true);
         }
      });
      
      BorderPane pane = new BorderPane();
      pane.setLeft(connectionButton);
      pane.setCenter(input);
      pane.setRight(sendButton);
      // ��ü�� root layout�� �Ʒ��ʿ� BorderPane�� ������ ��
      root.setBottom(pane);
      Scene scene = new Scene(root, 400, 400);
      primaryStage.setTitle("[ä�� Ŭ���̾�Ʈ]");
      primaryStage.setScene(scene);
      // ����ڰ� ���α׷��� �����ϸ� stopClient() �Լ��� ����ǰ� ����
      primaryStage.setOnCloseRequest(event -> stopClient());
      primaryStage.show();
      
      // �����ϱ� ��ư�� focus
      connectionButton.requestFocus();
   }
}