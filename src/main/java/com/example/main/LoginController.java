package com.example.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import member.dto.MemberDTO;
import member.service.MemberService;

import java.io.IOException;

import static com.example.main.UsefullMethod.changeStage;
import static com.example.main.UsefullMethod.showAlertWarn;

public class LoginController {
    @FXML
    private Label idLabel;

    @FXML
    private Label pwLabel;

    @FXML
    private Button submitLogin;

    @FXML
    private Label createId;

    @FXML
    private Label findId;

    @FXML
    private TextField inputId;
    @FXML
    private PasswordField inputPw;

    MemberService memberService = MemberService.getInstance();

    @FXML
    protected void doLogin() throws IOException {
        String id = inputId.getText();
        String pw = inputPw.getText();

        MemberDTO temp = new MemberDTO();
        temp.setMemId(id);
        temp.setMemPw(pw);
        MemberDTO getID = memberService.login(temp);

        if (getID.getMemId() != null) {
            memberService.login = getID;

            Stage stage = (Stage) idLabel.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            HelloController hc = fxmlLoader.getController();
            hc.areYouLogin();
            stage.setScene(scene);
            stage.setTitle("Concert List");
            stage.show();

        } else {
            showAlertWarn("아이디 혹은 비밀번호가 틀립니다.");
        }
    }

    @FXML
    protected void goMainStage() {
        changeStage(idLabel, "hello-view.fxml", "Concert List");
    }

    @FXML
    protected void goSignup() {
        changeStage(idLabel, "signUp-view.fxml", "sign up");
    }
@FXML
    protected void goFindPw(){
        changeStage(idLabel, "create-view.fxml", "find password");
    }
}
