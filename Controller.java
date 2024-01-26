package application;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Controller implements Initializable { // 모델과 뷰를 컨트롤하는 클래스

	// 모델
	private YutnoriSystem yutnoriSystem; // 윷놀이 시스템(최종모델)을 나타내는 오브젝트\

	// 뷰 관련 오브젝트의 자료구조
	private Alert alert; // 팝업창 관련 오브젝트
	ArrayList<Label> playerPieceStatus; // 플레이어 현황을 나타내는 레이블의 리스트
	Circle[][] subSquares; // 보드위의 칸(블록) 안의 작은 칸들을 나타내는 배열
	Circle[] mainSquares; // 보드위의 칸(블록)을 나타내는 배열

	// 기타
	int currentSquare; // 현재 말의 출발 위치
	boolean rollAgain; // 윷이나 모가 나와 플레이어가 다시 윷을 던지는 상태

	// 뷰
	@FXML
	private TextField playerNumInput;
	@FXML
	private TextField pieceNumInput;
	@FXML
	private Button startGame;
	@FXML
	private Button rollYut;
	@FXML
	private Button newPiece;
	@FXML
	private Label showPlayerTurn;
	@FXML
	private Label player1PieceStatus;
	@FXML
	private Label player2PieceStatus;
	@FXML
	private Label player3PieceStatus;
	@FXML
	private Label player4PieceStatus;
	@FXML
	private ListView yutList;
	@FXML
	private Circle p1Color;
	@FXML
	private Circle p2Color;
	@FXML
	private Circle p3Color;
	@FXML
	private Circle p4Color;
	@FXML
	private Circle square1;
	@FXML
	private Circle square1_1;
	@FXML
	private Circle square1_2;
	@FXML
	private Circle square1_3;
	@FXML
	private Circle square1_4;
	@FXML
	private Circle square1_5;
	@FXML
	private Circle square2;
	@FXML
	private Circle square2_1;
	@FXML
	private Circle square2_2;
	@FXML
	private Circle square2_3;
	@FXML
	private Circle square2_4;
	@FXML
	private Circle square2_5;
	@FXML
	private Circle square3;
	@FXML
	private Circle square3_1;
	@FXML
	private Circle square3_2;
	@FXML
	private Circle square3_3;
	@FXML
	private Circle square3_4;
	@FXML
	private Circle square3_5;
	@FXML
	private Circle square4;
	@FXML
	private Circle square4_1;
	@FXML
	private Circle square4_2;
	@FXML
	private Circle square4_3;
	@FXML
	private Circle square4_4;
	@FXML
	private Circle square4_5;
	@FXML
	private Circle square5;
	@FXML
	private Circle square5_1;
	@FXML
	private Circle square5_2;
	@FXML
	private Circle square5_3;
	@FXML
	private Circle square5_4;
	@FXML
	private Circle square5_5;
	@FXML
	private Circle square6;
	@FXML
	private Circle square6_1;
	@FXML
	private Circle square6_2;
	@FXML
	private Circle square6_3;
	@FXML
	private Circle square6_4;
	@FXML
	private Circle square6_5;
	@FXML
	private Circle square7;
	@FXML
	private Circle square7_1;
	@FXML
	private Circle square7_2;
	@FXML
	private Circle square7_3;
	@FXML
	private Circle square7_4;
	@FXML
	private Circle square7_5;
	@FXML
	private Circle square8;
	@FXML
	private Circle square8_1;
	@FXML
	private Circle square8_2;
	@FXML
	private Circle square8_3;
	@FXML
	private Circle square8_4;
	@FXML
	private Circle square8_5;
	@FXML
	private Circle square9;
	@FXML
	private Circle square9_1;
	@FXML
	private Circle square9_2;
	@FXML
	private Circle square9_3;
	@FXML
	private Circle square9_4;
	@FXML
	private Circle square9_5;
	@FXML
	private Circle square10;
	@FXML
	private Circle square10_1;
	@FXML
	private Circle square10_2;
	@FXML
	private Circle square10_3;
	@FXML
	private Circle square10_4;
	@FXML
	private Circle square10_5;
	@FXML
	private Circle square11;
	@FXML
	private Circle square11_1;
	@FXML
	private Circle square11_2;
	@FXML
	private Circle square11_3;
	@FXML
	private Circle square11_4;
	@FXML
	private Circle square11_5;
	@FXML
	private Circle square12;
	@FXML
	private Circle square12_1;
	@FXML
	private Circle square12_2;
	@FXML
	private Circle square12_3;
	@FXML
	private Circle square12_4;
	@FXML
	private Circle square12_5;
	@FXML
	private Circle square13;
	@FXML
	private Circle square13_1;
	@FXML
	private Circle square13_2;
	@FXML
	private Circle square13_3;
	@FXML
	private Circle square13_4;
	@FXML
	private Circle square13_5;
	@FXML
	private Circle square14;
	@FXML
	private Circle square14_1;
	@FXML
	private Circle square14_2;
	@FXML
	private Circle square14_3;
	@FXML
	private Circle square14_4;
	@FXML
	private Circle square14_5;
	@FXML
	private Circle square15;
	@FXML
	private Circle square15_1;
	@FXML
	private Circle square15_2;
	@FXML
	private Circle square15_3;
	@FXML
	private Circle square15_4;
	@FXML
	private Circle square15_5;
	@FXML
	private Circle square16;
	@FXML
	private Circle square16_1;
	@FXML
	private Circle square16_2;
	@FXML
	private Circle square16_3;
	@FXML
	private Circle square16_4;
	@FXML
	private Circle square16_5;
	@FXML
	private Circle square17;
	@FXML
	private Circle square17_1;
	@FXML
	private Circle square17_2;
	@FXML
	private Circle square17_3;
	@FXML
	private Circle square17_4;
	@FXML
	private Circle square17_5;
	@FXML
	private Circle square18;
	@FXML
	private Circle square18_1;
	@FXML
	private Circle square18_2;
	@FXML
	private Circle square18_3;
	@FXML
	private Circle square18_4;
	@FXML
	private Circle square18_5;
	@FXML
	private Circle square19;
	@FXML
	private Circle square19_1;
	@FXML
	private Circle square19_2;
	@FXML
	private Circle square19_3;
	@FXML
	private Circle square19_4;
	@FXML
	private Circle square19_5;
	@FXML
	private Circle square20;
	@FXML
	private Circle square20_1;
	@FXML
	private Circle square20_2;
	@FXML
	private Circle square20_3;
	@FXML
	private Circle square20_4;
	@FXML
	private Circle square20_5;
	@FXML
	private Circle square21;
	@FXML
	private Circle square21_1;
	@FXML
	private Circle square21_2;
	@FXML
	private Circle square21_3;
	@FXML
	private Circle square21_4;
	@FXML
	private Circle square21_5;
	@FXML
	private Circle square22;
	@FXML
	private Circle square22_1;
	@FXML
	private Circle square22_2;
	@FXML
	private Circle square22_3;
	@FXML
	private Circle square22_4;
	@FXML
	private Circle square22_5;
	@FXML
	private Circle square23;
	@FXML
	private Circle square23_1;
	@FXML
	private Circle square23_2;
	@FXML
	private Circle square23_3;
	@FXML
	private Circle square23_4;
	@FXML
	private Circle square23_5;
	@FXML
	private Circle square24;
	@FXML
	private Circle square24_1;
	@FXML
	private Circle square24_2;
	@FXML
	private Circle square24_3;
	@FXML
	private Circle square24_4;
	@FXML
	private Circle square24_5;
	@FXML
	private Circle square25;
	@FXML
	private Circle square25_1;
	@FXML
	private Circle square25_2;
	@FXML
	private Circle square25_3;
	@FXML
	private Circle square25_4;
	@FXML
	private Circle square25_5;
	@FXML
	private Circle square26;
	@FXML
	private Circle square26_1;
	@FXML
	private Circle square26_2;
	@FXML
	private Circle square26_3;
	@FXML
	private Circle square26_4;
	@FXML
	private Circle square26_5;
	@FXML
	private Circle square27;
	@FXML
	private Circle square27_1;
	@FXML
	private Circle square27_2;
	@FXML
	private Circle square27_3;
	@FXML
	private Circle square27_4;
	@FXML
	private Circle square27_5;
	@FXML
	private Circle square28;
	@FXML
	private Circle square28_1;
	@FXML
	private Circle square28_2;
	@FXML
	private Circle square28_3;
	@FXML
	private Circle square28_4;
	@FXML
	private Circle square28_5;
	@FXML
	private Circle square29;
	@FXML
	private Circle square29_1;
	@FXML
	private Circle square29_2;
	@FXML
	private Circle square29_3;
	@FXML
	private Circle square29_4;
	@FXML
	private Circle square29_5;
	@FXML
	private ImageView randomyoot;

	File file2 = new File("src/application/dice2.png"); // 도
	File file3 = new File("src/application/dice3.png"); // 개
	File file4 = new File("src/application/dice4.png"); // 걸
	File file6 = new File("src/application/dice6.png"); // 윷
	File file5 = new File("src/application/dice5.png"); // 모
	File file1 = new File("src/application/dice1.png"); // 백도

	// 채팅 버튼을 클릭했을때
	@FXML
	public void doChat(ActionEvent event) {
		ClientMain cl = new ClientMain();
		Stage stage = new Stage();
		cl.start(stage);
	}

	// 게임 시작 버튼을 클릭했을때
	@FXML
	public void startGameButtonClicked(ActionEvent event) {
		Random random = new Random();

		String inputPlayerNumTextField = playerNumInput.getText(); // 입력값을 읽는다
		String inputPieceNumTextField = pieceNumInput.getText();
		ObservableList<String> yutListElement = FXCollections.observableArrayList();
		// 입력값에 대한 예외처리
		try {
			int tempPlayerNum = Integer.parseInt(inputPlayerNumTextField);
			int tempPieceNum = Integer.parseInt(inputPieceNumTextField);

			// 플레이어 수와 말의 수의 범위를 벗어날 때
			if (tempPlayerNum < 2 || tempPlayerNum > 4 || (tempPieceNum < 2 || tempPieceNum > 5)) {
				alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Error");
				alert.setContentText("플레이어 수는 2에서 4 사이의 수로 입력하세요." + "\n말의 수는 2에서 5 사이의 수로 입력하세요");
				alert.setHeaderText(null);
				alert.show();
				playerNumInput.setText("");
				pieceNumInput.setText("");
			}
			// 정상적으로 진행될 때
			else {
				showPlayerTurn.setText("플레이어" + 1 + " 순서입니다." + // !!!!순서 랜덤으로 할시 수정사항!!!!
						"\n 윷을 던진 후 움직일 말을 " + "\n 선택해 주세요.");
				yutList.setItems(yutListElement);
				yutnoriSystem.startGame(tempPlayerNum, tempPieceNum); // 모델을 초기화

				for (int i = 0; i < yutnoriSystem.playingPlayer.size(); i++) { // 플레이어별 말 현황 출력
					playerPieceStatus.get(i).setText("보드위 말: " + yutnoriSystem.playingPlayer.get(i).onBoardPieceNumber
							+ " / 골인: " + yutnoriSystem.playingPlayer.get(i).goalInNumber);
				}

				playerNumInput.setDisable(true);
				pieceNumInput.setDisable(true);
				startGame.setDisable(true);
				disableMainSquares();
				rollYut.setDisable(false);
				newPiece.setDisable(true);

				yutnoriSystem.playingPlayer.get(0).turn = true; // 플레이어 1이 첫번째로 시작한다

			}
			return;
		}
		// 숫자가 아닌 입력값일때
		catch (NumberFormatException e) {
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setContentText("숫자로 다시 입력하시오");
			alert.setHeaderText(null);
			alert.show();
			playerNumInput.setText("");
			pieceNumInput.setText("");
		}
	}

	// 윷은 던지는 버튼을 클릭했을때
	@FXML
	private void rollYutButtonClicked(ActionEvent event) {
		int cnt;
		final int yutResult = yutnoriSystem.rollYuts();
		final String[] yutResultList = { "모", "도", "개", "걸", "윷", "빽도" };

		Thread thread = new Thread() {
			Random random = new Random();

			public void run() {
				try {
					for (int i = 0; i < 20; i++) { // 주사위 또로로로롤 번
						File files = new File("src/application/dice" + (random.nextInt(6) + 1) + ".png"); // 이미지 파일 불러오기
						randomyoot.setImage(new Image(files.toURI().toString()));
						Thread.sleep(50); // 시간 0.05초
					}

					if (yutResultList[yutResult].equals("도")) {
						randomyoot.setImage(new Image(file2.toURI().toString()));
					} else if (yutResultList[yutResult].equals("개")) {
						randomyoot.setImage(new Image(file3.toURI().toString()));
					} else if (yutResultList[yutResult].equals("걸")) {
						randomyoot.setImage(new Image(file4.toURI().toString()));
					} else if (yutResultList[yutResult].equals("윷")) {
						randomyoot.setImage(new Image(file6.toURI().toString()));
					} else if (yutResultList[yutResult].equals("모")) {
						randomyoot.setImage(new Image(file5.toURI().toString()));
					} else if (yutResultList[yutResult].equals("빽도")) {
						randomyoot.setImage(new Image(file1.toURI().toString()));
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		};

		thread.start();

		yutList.getItems().add(yutResultList[yutResult]);

		if (yutResult == 0 | yutResult == 4) { // 윷이나 모이면 다시 한번 던질수 있음
			showPlayerTurn.setText("윷이나 모가 나와 한번 더 던질수 있습니다!");
			return;
		}

		yutnoriSystem.playingPlayer.get(yutnoriSystem.currentTurn()).eatAndRollAgain = false;
		rollYut.setDisable(true);
		enableMainSquares();
		yutList.setDisable(true);
		newPiece.setDisable(false);

	}

	// 새로운 말 클릭했을때
	@FXML
	private void newPieceClicked(MouseEvent event) {
		if (yutnoriSystem.playingPlayer.get(yutnoriSystem.currentTurn()).notOnBoardPieceNumber != 0) {
			int turn = yutnoriSystem.currentTurn();// 현재 순서인 플레이어를 확인하고

			mainSquares[1].setFill(Color.WHITE);

			yutnoriSystem.board.eatOrMerge(1, 1, false, false, turn); // 이미 시작칸에 존재하는 다른 플레이어의 말을 먹음
			yutnoriSystem.board.squares[1].pieces.add(new Piece(turn)); // 시작칸에 새로운 말을 추가
			yutnoriSystem.playingPlayer.get(turn).addPieceOnBoard();

			Circle temp;
			for (int i = 1; i <= (yutnoriSystem.board.squares)[1].pieces.size(); i++) { // 시작 지점의 circle들을 알맞게 색칠하고 업데이트
																						// 보이게
				temp = subSquares[1][i];
				temp.setVisible(true);
				temp.setFill(yutnoriSystem.playingPlayer.get(turn).getColor());
			}

			for (int i = 0; i < yutnoriSystem.playingPlayer.size(); i++) { // 플레이어별 말 현황 출력
				playerPieceStatus.get(i).setText("보드위 말: " + yutnoriSystem.playingPlayer.get(i).onBoardPieceNumber
						+ " / 골인: " + yutnoriSystem.playingPlayer.get(i).goalInNumber);
			}

			disableMainSquares();
			yutList.setDisable(false);

			currentSquare = 1;
		} else {

		}
		newPiece.setDisable(true);
		rollYut.setDisable(true);
	}

	// 던진 윷 목록에서 윷을 선택(클릭)했을때
	@FXML
	private void listViewSetOnMouseClicked(MouseEvent event) {

		String yutType;
		Circle temp;
		Color color;
		int turn;
		int yutindex;
		int moveDistance;
		int nextSquare;

		yutType = yutList.getSelectionModel().getSelectedItem().toString();
		yutindex = yutList.getSelectionModel().getSelectedIndex(); // 선택한 윷 목록
		moveDistance = yutnoriSystem.switchYut(yutType); // 선택한 윷 목록을 int형 움직일 거리로 return
		yutList.getItems().remove(yutindex); // 움직인 윷 목록 삭제

		turn = yutnoriSystem.currentTurn();
		color = yutnoriSystem.currentColor(turn);
		// 원래 있던 칸의 circle들을 안보이게
		for (int i = 1; i <= (yutnoriSystem.board.squares)[currentSquare].pieces.size(); i++) {
			subSquares[currentSquare][i].setVisible(false);
		}

		// 말 이동
		nextSquare = yutnoriSystem.board.movePiece(currentSquare, moveDistance, turn);

		// 시작 지점에서 백도나오면 다시 circle들을 보이게
		if (nextSquare == currentSquare) {
			for (int i = 1; i <= (yutnoriSystem.board.squares)[currentSquare].pieces.size(); i++) {
				subSquares[currentSquare][i].setVisible(true);
			}
		}
		// 모든 플레이어의 턴이 끝나면 게임 종료
		else if (nextSquare == -2) {
			for (int i = 0; i < yutnoriSystem.playingPlayer.size(); i++) { // 플레이어별 말 현황 출력
				playerPieceStatus.get(i).setText("보드위 말: " + yutnoriSystem.playingPlayer.get(i).onBoardPieceNumber
						+ " / 골인: " + yutnoriSystem.playingPlayer.get(i).goalInNumber);
			}

			
			int[] rank = new int[yutnoriSystem.playerNum];
			String rankMsg = null;
			rankMsg = "게임이 끝났습니다. 순위는 아래와 같습니다.\n\n";
			// 순위 계산
			yutnoriSystem.calcRank();
			for (int i = 0; i < yutnoriSystem.playerNum; i++) {
				rank[i] = yutnoriSystem.playingPlayer.get(i).playerID;
				rankMsg = rankMsg.concat((i + 1) + "등: 플레이어" + (rank[i] + 1) + "\n");
			}
			showPlayerTurn.setText("");

			// 순위 출력
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Good Game");
			alert.setContentText(rankMsg);
			alert.setHeaderText(null);
			alert.show();

			return;
		}

		// 이동 했는데 골인 안했을 경우 도착 square의 circle들을 보이게
		else if (nextSquare != -1) {
			// 목표 칸을 모두 안보이게 하고
			for (int i = 1; i <= 5; i++) {
				temp = subSquares[nextSquare][i];
				temp.setVisible(false);
			}
			// 목표 칸에 실제 존재하는 말들만 보이게 하기
			for (int i = 1; i <= (yutnoriSystem.board.squares)[nextSquare].pieces.size(); i++) {
				temp = subSquares[nextSquare][i];
				temp.setVisible(true);
				temp.setFill(color);
			}
		}
		// 이동하고 골인했을 경우 남아있는 yutList를 초기화
		else {
//			yutnoriSystem.playingPlayer.get(turn).addGoalInNumber();
			yutList.getItems().clear();

		}
		// 같은 플레이어가 또 윷을 던지지 않을때 turn 변경
		if (yutList.getItems().size() == 0) {
			if (yutnoriSystem.playingPlayer.get(turn).eatAndRollAgain == false) {
				// 다음 차례를 설정
				turn = yutnoriSystem.nextTurn();

				if (yutnoriSystem.playingPlayer.get(turn).notOnBoardPieceNumber == 0) {
					newPiece.setDisable(true);
				} else if (yutnoriSystem.playingPlayer.get(turn).notOnBoardPieceNumber > 0
						&& yutnoriSystem.playingPlayer.get(turn).onBoardPieceNumber == 0) {
					newPiece.setDisable(true);
				} else {
					newPiece.setDisable(false);
				}
				showPlayerTurn.setText("플레이어" + (turn + 1) + " 순서입니다." + "\n말을 선택후 윷을 던져주세요!");
				rollAgain = false;
				rollYut.setDisable(false);
				yutList.setDisable(true);
				newPiece.setDisable(true);
				disableMainSquares();
			} else {
				showPlayerTurn.setText("플레이어" + (turn + 1) + " 순서입니다." + "\n상대 말을 먹었으니 윷을 던지세요!");
				rollAgain = true;
				rollYut.setDisable(false);

				yutList.setDisable(true);
				disableMainSquares();
				currentSquare = nextSquare;
			}
			// 같은 플레이어가 또 윷을 던지는 경우
		} else {
			showPlayerTurn.setText("플레이어" + (turn + 1) + " 순서입니다." + "\n말을 선택후 이동 선택을 해주세요!");
			rollAgain = true;
			rollYut.setDisable(true);
			yutList.setDisable(true);
			newPiece.setDisable(false);
			enableMainSquares();
		}

		// 플레이어별 말 현황 출력
		for (int i = 0; i < yutnoriSystem.playingPlayer.size(); i++) { // 플레이어별 말 현황 출력
			playerPieceStatus.get(i).setText("보드위 말: " + yutnoriSystem.playingPlayer.get(i).onBoardPieceNumber
					+ " / 골인: " + yutnoriSystem.playingPlayer.get(i).goalInNumber);
		}
	}

	// 보드 위에서 칸(블록)을 클릭하여 선택할 경우
	@FXML
	private void boardOnMouseClicked(MouseEvent event) {
		Circle circle = (Circle) event.getSource();
		circle.setFill(Color.WHITE);
		// 모든 칸들은 기본색으로 바꿈
		for (int i = 1; i <= 29; i++) {
			if (mainSquares[i] != circle) {
				mainSquares[i].setFill(Color.LIGHTGOLDENRODYELLOW);
			}
		}
		// 선택한 칸의 인덱스 받아오기
		currentSquare = getSquareIndex(circle);
		// 현재 순서인 플레이어 확인
		int turn = yutnoriSystem.currentTurn();
		// 선택(클릭)한 칸(블록)에 본인 소유의 말이 없거나 상대 말인경우
		if (yutnoriSystem.board.squares[currentSquare].pieces.size() == 0
				|| yutnoriSystem.board.squares[currentSquare].pieces.get(0).player != turn) {
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Warning");
			alert.setContentText("해당 칸에는 소유한 말이 없습니다. 다시 선택해주세요");
			alert.setHeaderText(null);
			alert.show();
			circle.setFill(Color.LIGHTGOLDENRODYELLOW);
			return;
		}

		// 플레이어가 윷을 다시 던질 경우
		if (rollAgain == true) {
			rollYut.setDisable(true);
			yutList.setDisable(false);
		} else {
			rollYut.setDisable(true);
			yutList.setDisable(false);
		}
		newPiece.setDisable(true);
	}

	// 플레이어가 보드에서 선택한 칸(블록)에 해당하는 인덱스를 리턴하는 함수
	int getSquareIndex(Circle circle) {
		if (circle == square1)
			return 1;
		else if (circle == square2)
			return 2;
		else if (circle == square3)
			return 3;
		else if (circle == square4)
			return 4;
		else if (circle == square5)
			return 5;
		else if (circle == square6)
			return 6;
		else if (circle == square7)
			return 7;
		else if (circle == square8)
			return 8;
		else if (circle == square9)
			return 9;
		else if (circle == square10)
			return 10;
		else if (circle == square11)
			return 11;
		else if (circle == square12)
			return 12;
		else if (circle == square13)
			return 13;
		else if (circle == square14)
			return 14;
		else if (circle == square15)
			return 15;
		else if (circle == square16)
			return 16;
		else if (circle == square17)
			return 17;
		else if (circle == square18)
			return 18;
		else if (circle == square19)
			return 19;
		else if (circle == square20)
			return 20;
		else if (circle == square21)
			return 21;
		else if (circle == square22)
			return 22;
		else if (circle == square23)
			return 23;
		else if (circle == square24)
			return 24;
		else if (circle == square25)
			return 25;
		else if (circle == square26)
			return 26;
		else if (circle == square27)
			return 27;
		else if (circle == square28)
			return 28;
		return 29;
	}

	// 보드 위의 모든 칸을 선택 불가능하게 변경하는 함수
	void disableMainSquares() {
		for (int i = 1; i <= 29; i++) {
			mainSquares[i].setDisable(true);
			mainSquares[i].setFill(Color.LIGHTGOLDENRODYELLOW);
		}
	}

	// 보드 위의 모든 칸을 선택 가능하게 변경하는 함수
	void enableMainSquares() {
		for (int i = 1; i <= 29; i++) {
			mainSquares[i].setDisable(false);
			mainSquares[i].setFill(Color.LIGHTGOLDENRODYELLOW);
		}
	}

	Socket socket;

	// 뷰 요소들과 자료구조 초기화
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

//       // 소켓 지정하고 연결해주기
//       if(!MyInfo.socketConnect) {
//          final String IP = "10.20.28.31";
//          final int port = 8080;
//          socket = new Socket();
//          
//          try {
//              socket.connect(new InetSocketAddress(IP, port));
//              System.out.println("서버 연결 성공");
//              MyInfo.setConnect(true);
//              MyInfo.setSocket(socket);
//           } catch(IOException e) {
//              e.printStackTrace();
//           }
//       }

		yutnoriSystem = new YutnoriSystem();

		yutList.setDisable(true);
		rollYut.setDisable(true);

		subSquares = new Circle[30][6];
		mainSquares = new Circle[30];

		// 보드 자료구조에 해당하는 뷰 요소 집어넣기
		subSquares[1][1] = (square1_1);
		subSquares[1][2] = (square1_2);
		subSquares[1][3] = (square1_3);
		subSquares[1][4] = (square1_4);
		subSquares[1][5] = (square1_5);
		subSquares[2][1] = (square2_1);
		subSquares[2][2] = (square2_2);
		subSquares[2][3] = (square2_3);
		subSquares[2][4] = (square2_4);
		subSquares[2][5] = (square2_5);
		subSquares[3][1] = (square3_1);
		subSquares[3][2] = (square3_2);
		subSquares[3][3] = (square3_3);
		subSquares[3][4] = (square3_4);
		subSquares[3][5] = (square3_5);
		subSquares[4][1] = (square4_1);
		subSquares[4][2] = (square4_2);
		subSquares[4][3] = (square4_3);
		subSquares[4][4] = (square4_4);
		subSquares[4][5] = (square4_5);
		subSquares[5][1] = (square5_1);
		subSquares[5][2] = (square5_2);
		subSquares[5][3] = (square5_3);
		subSquares[5][4] = (square5_4);
		subSquares[5][5] = (square5_5);
		subSquares[6][1] = (square6_1);
		subSquares[6][2] = (square6_2);
		subSquares[6][3] = (square6_3);
		subSquares[6][4] = (square6_4);
		subSquares[6][5] = (square6_5);
		subSquares[7][1] = (square7_1);
		subSquares[7][2] = (square7_2);
		subSquares[7][3] = (square7_3);
		subSquares[7][4] = (square7_4);
		subSquares[7][5] = (square7_5);
		subSquares[8][1] = (square8_1);
		subSquares[8][2] = (square8_2);
		subSquares[8][3] = (square8_3);
		subSquares[8][4] = (square8_4);
		subSquares[8][5] = (square8_5);
		subSquares[9][1] = (square9_1);
		subSquares[9][2] = (square9_2);
		subSquares[9][3] = (square9_3);
		subSquares[9][4] = (square9_4);
		subSquares[9][5] = (square9_5);
		subSquares[10][1] = (square10_1);
		subSquares[10][2] = (square10_2);
		subSquares[10][3] = (square10_3);
		subSquares[10][4] = (square10_4);
		subSquares[10][5] = (square10_5);
		subSquares[11][1] = (square11_1);
		subSquares[11][2] = (square11_2);
		subSquares[11][3] = (square11_3);
		subSquares[11][4] = (square11_4);
		subSquares[11][5] = (square11_5);
		subSquares[12][1] = (square12_1);
		subSquares[12][2] = (square12_2);
		subSquares[12][3] = (square12_3);
		subSquares[12][4] = (square12_4);
		subSquares[12][5] = (square12_5);
		subSquares[13][1] = (square13_1);
		subSquares[13][2] = (square13_2);
		subSquares[13][3] = (square13_3);
		subSquares[13][4] = (square13_4);
		subSquares[13][5] = (square13_5);
		subSquares[14][1] = (square14_1);
		subSquares[14][2] = (square14_2);
		subSquares[14][3] = (square14_3);
		subSquares[14][4] = (square14_4);
		subSquares[14][5] = (square14_5);
		subSquares[15][1] = (square15_1);
		subSquares[15][2] = (square15_2);
		subSquares[15][3] = (square15_3);
		subSquares[15][4] = (square15_4);
		subSquares[15][5] = (square15_5);
		subSquares[16][1] = (square16_1);
		subSquares[16][2] = (square16_2);
		subSquares[16][3] = (square16_3);
		subSquares[16][4] = (square16_4);
		subSquares[16][5] = (square16_5);
		subSquares[17][1] = (square17_1);
		subSquares[17][2] = (square17_2);
		subSquares[17][3] = (square17_3);
		subSquares[17][4] = (square17_4);
		subSquares[17][5] = (square17_5);
		subSquares[18][1] = (square18_1);
		subSquares[18][2] = (square18_2);
		subSquares[18][3] = (square18_3);
		subSquares[18][4] = (square18_4);
		subSquares[18][5] = (square18_5);
		subSquares[19][1] = (square19_1);
		subSquares[19][2] = (square19_2);
		subSquares[19][3] = (square19_3);
		subSquares[19][4] = (square19_4);
		subSquares[19][5] = (square19_5);
		subSquares[20][1] = (square20_1);
		subSquares[20][2] = (square20_2);
		subSquares[20][3] = (square20_3);
		subSquares[20][4] = (square20_4);
		subSquares[20][5] = (square20_5);
		subSquares[21][1] = (square21_1);
		subSquares[21][2] = (square21_2);
		subSquares[21][3] = (square21_3);
		subSquares[21][4] = (square21_4);
		subSquares[21][5] = (square21_5);
		subSquares[22][1] = (square22_1);
		subSquares[22][2] = (square22_2);
		subSquares[22][3] = (square22_3);
		subSquares[22][4] = (square22_4);
		subSquares[22][5] = (square22_5);
		subSquares[23][1] = (square23_1);
		subSquares[23][2] = (square23_2);
		subSquares[23][3] = (square23_3);
		subSquares[23][4] = (square23_4);
		subSquares[23][5] = (square23_5);
		subSquares[24][1] = (square24_1);
		subSquares[24][2] = (square24_2);
		subSquares[24][3] = (square24_3);
		subSquares[24][4] = (square24_4);
		subSquares[24][5] = (square24_5);
		subSquares[25][1] = (square25_1);
		subSquares[25][2] = (square25_2);
		subSquares[25][3] = (square25_3);
		subSquares[25][4] = (square25_4);
		subSquares[25][5] = (square25_5);
		subSquares[26][1] = (square26_1);
		subSquares[26][2] = (square26_2);
		subSquares[26][3] = (square26_3);
		subSquares[26][4] = (square26_4);
		subSquares[26][5] = (square26_5);
		subSquares[27][1] = (square27_1);
		subSquares[27][2] = (square27_2);
		subSquares[27][3] = (square27_3);
		subSquares[27][4] = (square27_4);
		subSquares[27][5] = (square27_5);
		subSquares[28][1] = (square28_1);
		subSquares[28][2] = (square28_2);
		subSquares[28][3] = (square28_3);
		subSquares[28][4] = (square28_4);
		subSquares[28][5] = (square28_5);
		subSquares[29][1] = (square29_1);
		subSquares[29][2] = (square29_2);
		subSquares[29][3] = (square29_3);
		subSquares[29][4] = (square29_4);
		subSquares[29][5] = (square29_5);

		mainSquares[1] = square1;
		mainSquares[2] = square2;
		mainSquares[3] = square3;
		mainSquares[4] = square4;
		mainSquares[5] = square5;
		mainSquares[6] = square6;
		mainSquares[7] = square7;
		mainSquares[8] = square8;
		mainSquares[9] = square9;
		mainSquares[10] = square10;
		mainSquares[11] = square11;
		mainSquares[12] = square12;
		mainSquares[13] = square13;
		mainSquares[14] = square14;
		mainSquares[15] = square15;
		mainSquares[16] = square16;
		mainSquares[17] = square17;
		mainSquares[18] = square18;
		mainSquares[19] = square19;
		mainSquares[20] = square20;
		mainSquares[21] = square21;
		mainSquares[22] = square22;
		mainSquares[23] = square23;
		mainSquares[24] = square24;
		mainSquares[25] = square25;
		mainSquares[26] = square26;
		mainSquares[27] = square27;
		mainSquares[28] = square28;
		mainSquares[29] = square29;

		// 플레이어 현황 레이블도 자료구조에 추가
		playerPieceStatus = new ArrayList<>();
		playerPieceStatus.add(player1PieceStatus);
		playerPieceStatus.add(player2PieceStatus);
		playerPieceStatus.add(player3PieceStatus);
		playerPieceStatus.add(player4PieceStatus);

		// 보드의 모든 칸(블록)내의 세부 칸들이 안보이게 초기화
		for (int i = 1; i <= 29; i++) {
			for (int j = 1; j <= 5; j++) {
				subSquares[i][j].setVisible(false);
			}
			// 주요 칸들 색 초기화
			mainSquares[i].setFill(Color.LIGHTGOLDENRODYELLOW);
			mainSquares[i].setDisable(true);
		}

		// 플레이어 색 지정
		p1Color.setFill(Color.ORANGE);
		p2Color.setFill(Color.GREEN);
		p3Color.setFill(Color.PURPLE);
		p4Color.setFill(Color.PINK);

		newPiece.setDisable(false);
	}
}