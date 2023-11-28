package com.example.main;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import member.dto.MemberDTO;
import member.service.MemberService;
import static com.example.main.UsefullMethod.*;

public class LoginController {
    @FXML
    private Label idLabel;

    @FXML
    private TextField inputId;
    @FXML
    private PasswordField inputPw;

    MemberService memberService = MemberService.getInstance();

    @FXML
    protected void doLogin(){
        String id = inputId.getText();
        String pw = inputPw.getText();

        MemberDTO temp = new MemberDTO();
        temp.setMemId(id);
        temp.setMemPw(pw);
        MemberDTO getID = memberService.login(temp);

        if (getID.getMemId() != null) {
            memberService.login = getID;

            goMainStage();

        } else {
            showAlertWarn("아이디 혹은 비밀번호가 틀립니다.");
        }
    }

    @FXML
    protected void goMainStage() {
        moveMainStage(idLabel);
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
