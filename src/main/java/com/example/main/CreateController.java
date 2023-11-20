package com.example.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import member.service.MemberService;

import static com.example.main.UsefullMethod.changeStage;

public class CreateController {
    @FXML
    private Label idLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label resultLabel;
    @FXML
    private TextField inputId;
    @FXML
    private TextField inputName;
    @FXML
    private Button searchBtn;
    @FXML
    private Button goMain;

    MemberService memberService = MemberService.getInstance();

    @FXML
    protected void searchPw() {
        String id = inputId.getText();
        String name = inputName.getText();

        String result = memberService.getMyPw(id, name);


        if (result.isEmpty()) {
            resultLabel.setText("아이디 및 이름이 일치하지 않거나 없는 아이디 입니다.");
        } else {
            resultLabel.setText("당신의 비밀번호는 [" + result + "]입니다.");
        }

    }

    @FXML
    protected void goMainStage() {
        changeStage(idLabel, "hello-view.fxml", "Concert List");
    }

}
