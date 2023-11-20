package com.example.main;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import member.service.MemberService;

public class HelloController {
    @FXML
    private Button loginBtn;

    @FXML
    private Label loginStatus;
    @FXML
    private HBox showConcert;
    @FXML
    private DatePicker startDay;
    @FXML
    private DatePicker endDay;
    @FXML
    private ComboBox region;
    @FXML
    private ComboBox concertType;
    @FXML
    private TextField startMoney;
    @FXML
    private TextField endMoney;
    @FXML
    private Button signBtn;

    MemberService memberService = MemberService.getInstance();

    @FXML
    protected void goLogin() {
        UsefullMethod.changeStage(loginStatus, "login-view.fxml", "login");
    }
    @FXML
    protected void goSignup() {
        UsefullMethod.changeStage(loginStatus, "signUp-view.fxml", "sign up");
    }

    protected void areYouLogin() {
        if (memberService.login != null) {
            loginStatus.setText(memberService.login.getMemName() + "님 환영합니다.");
            loginBtn.setText("로그아웃");
        }
    }
}
