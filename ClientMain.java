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
   // 메세지가 출력되는 공간
   TextArea textArea;
   
   // 클라이언트 프로그램 동작 메소드
   // 어떤 ip, port 번호로 접속할지 설정
   public void startClient(String IP, int port) {
      Thread thread = new Thread() {
         public void run() {
            try {
               // 소켓 초기화
               socket = new Socket(IP, port);
               receive();
            } catch(Exception e) {
               if(!socket.isClosed()) {
                  stopClient();
                  System.out.println("[서버 접속 실패]");
                  // 프로그램 자체를 종료
                  Platform.exit();
               }
            }
         }
      };
      thread.start();
   }
   
   // 클라이언트 프로그램 종료 메소드
   public void stopClient() {
      try {
         if(socket != null && socket.isClosed()) {
            socket.close();
         }
      } catch(Exception e) {
         e.printStackTrace();
      }
   }
   
   // 서버로부터 메세지를 전달받는 메소드
   public void receive() {
      // 서버로부터 메세지를 계속 전달받기 위해 무한루프
      while(true) {
         try {
            // 소켓에서 InputStream을 통해 서버로부터 메세지를 전달받을 수 있도록 만듬
            InputStream in = socket.getInputStream();
            // buffer를 통해 512바이트씩 끊어서 buffer에 담아 전달
            byte[] buffer = new byte[512];
            // read() 함수를 통해 입력을 받음
            int length = in.read(buffer);
            if(length == -1) throw new IOException();
            String message = new String(buffer, 0, length, "UTF-8");
            Platform.runLater(() -> {
               // GUI의 요소로, 화면에 메세지를 주고받은 것을 출력함
               textArea.appendText(message);
            });
         } catch(Exception e) {
             stopClient();
             break;
         }
      }
   }
   
   // 서버로 메세지를 전송하는 메소드
   public void send(String message) {
      Thread thread = new Thread() {
         public void run() {
            try {
               // OutputStream 객체를 통해 서버로 메세지를 전송
               OutputStream out = socket.getOutputStream();
               byte[] buffer = message.getBytes("UTF-8");
               out.write(buffer);
               // 메세지 전송의 끝을 알림
               out.flush();
            } catch(Exception e) {
               stopClient();
            }
         }
      };
      thread.start();
   }
   
   // 실제로 프로그램을 동작시키는 메소드
   @Override
   public void start(Stage primaryStage) {
      BorderPane root = new BorderPane();
      root.setPadding(new Insets(5));
      
      // HBox를 통해 BorderPane 위에 layout 생성
      HBox hbox = new HBox();
      // HBox에 여백주기
      hbox.setSpacing(5);
      
      TextField userName = new TextField();
      userName.setPrefWidth(150);
      // 기본 세팅
      userName.setPromptText("닉네임을 입력하세요.");
      // HBox 내부에서 해당 textfield를 출력할 수 있도록 만듬
      HBox.setHgrow(userName, Priority.ALWAYS);
      
      // IP주소가 들어갈 수 있게 만듬
      TextField IPText = new TextField("10.20.28.31");
      TextField portText = new TextField("9876");
      portText.setPrefWidth(80);
      
      hbox.getChildren().addAll(userName, IPText, portText);
      // HBox를 BorderPane 위쪽에 달아주도록 만듬
      root.setTop(hbox);
      
      // 화면이 구성될 때 객체 초기화
      textArea = new TextArea();
      // 내용을 수정할 수 없도록 만듬
      textArea.setEditable(false);
      root.setCenter(textArea);
      
      TextField input = new TextField();
      input.setPrefWidth(Double.MAX_VALUE);
      // 접속하기 이전에는 메세지를 전송할 수 없도록 함
      input.setDisable(true);
      
      input.setOnAction(event -> {
         send(userName.getText() + ": " + input.getText() + "\n");
         input.setText("");
         // 다시 메세지를 전송할 수 있도록 focus를 설정
         input.requestFocus();
      });
      
      Button sendButton = new Button("보내기");
      // 접속하기 이전에는 이용할 수 없도록 함
      sendButton.setDisable(true);
      
      sendButton.setOnAction(event -> {
         send(userName.getText() + ": " + input.getText() + "\n");
         input.setText("");
         input.requestFocus();
      });
      
      Button connectionButton = new Button("접속하기");
      connectionButton.setOnAction(event -> {
         if(connectionButton.getText().equals("접속하기")) {
            int port = 9876;
            try {
               // 사용자가 입력한(포트번호) 텍스트 내용을 정수 형태로 변환하여 다시 담음
               // 별도의 포트 번호를 입력하면 그 포트 번호로 접속할 수 있도록 만듬
               port = Integer.parseInt(portText.getText());
            } catch(Exception e) {
               e.printStackTrace();
            }
            // 특정한 IP주소에 어떠한 포트 번호로 접속할 수 있도록 만듬
            startClient(IPText.getText(), port);
            // 화면에 출력할 수 있도록 만듬
            Platform.runLater(() -> {
               textArea.appendText("[채팅방 접속]\n");
            });
            connectionButton.setText("종료하기");
            // 사용자가 내용을 직접 입력하여 버튼을 눌렀을 때 보내지도록
            input.setDisable(false);
            sendButton.setDisable(false);
            // 바로 메세지를 입력할 수 있도록 focus
            input.requestFocus();
         
         // 버튼이 접속하기가 아닌 종료하기라면
         } else {
            stopClient();
            Platform.runLater(() -> {
               textArea.appendText("[채팅방 퇴장]");
            });
            connectionButton.setText("접속하기");
            // 다시 입력칸 밑 버튼을 누를 수 없도록 설정
            input.setDisable(true);
            sendButton.setDisable(true);
         }
      });
      
      BorderPane pane = new BorderPane();
      pane.setLeft(connectionButton);
      pane.setCenter(input);
      pane.setRight(sendButton);
      // 전체의 root layout의 아래쪽에 BorderPane이 들어가도록 함
      root.setBottom(pane);
      Scene scene = new Scene(root, 400, 400);
      primaryStage.setTitle("[채팅 클라이언트]");
      primaryStage.setScene(scene);
      // 사용자가 프로그램을 종료하면 stopClient() 함수가 실행되고 종료
      primaryStage.setOnCloseRequest(event -> stopClient());
      primaryStage.show();
      
      // 접속하기 버튼이 focus
      connectionButton.requestFocus();
   }
}