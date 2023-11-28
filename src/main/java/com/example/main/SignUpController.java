package com.example.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import member.dto.MemberDTO;
import member.service.MemberService;

import static com.example.main.UsefullMethod.*;

public class SignUpController {


    @FXML
    private TextField inputId;

    @FXML
    private PasswordField inputPw;
    @FXML
    private PasswordField inputRepw;

    @FXML
    private TextField inputName;

    @FXML
    private Button signBtn;

    @FXML
    private Label idLabel;
    @FXML
    private Label pwLabel;
    @FXML
    private Label repwLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label idCheck;
    @FXML
    private Label pwRegret;
    @FXML
    private Label pwCheck;
    @FXML
    private Button goMain;

    MemberService memberService = MemberService.getInstance();

    boolean idChk = false;
    boolean pwChk = false;
    boolean dupleChk = true;
    boolean isNameNull = false;


    protected void isDupleChk(String id) {
        // false일때 가입 가능
        dupleChk = memberService.dupleCheck(id);

    }

    @FXML
    protected void goMainStage() {
        moveMainStage(idLabel);
    }

    @FXML
    public void isIdChk() {
        String id = inputId.getText();
        String regex = "^[a-zA-Z0-9]{7,12}$";
        if (!id.matches(regex)) {
            idChk = false;
            idCheck.setText("아이디는 영어와 숫자로 이루어진 7~12글자로 입력해주세요");
            idCheck.setTextFill(Paint.valueOf("red"));
            //맞지 않다면

        } else {
            idChk = true;
            idCheck.setText("사용 가능한 아이디 입니다.");
            idCheck.setTextFill(Paint.valueOf("blue"));
        }

    }


    @FXML
    protected void isPwChk() {
        String pw = inputPw.getText();
        String rePw = inputRepw.getText();
        if (!pw.isEmpty() && pw.equals(rePw)) {
            pwChk = true;
            pwCheck.setText("비밀번호가 일치합니다!");
            pwCheck.setTextFill(Paint.valueOf("blue"));
        } else {
            pwChk = false;
            pwCheck.setText("비밀번호가 일치하지 않습니다.");
            pwCheck.setTextFill(Paint.valueOf("red"));
        }

    }

    @FXML
    protected void isNameNullChk() {
        String name = inputName.getText();
        if (name.isEmpty()) {
            isNameNull = false;

        } else {
            isNameNull = true;
        }

    }

    @FXML
    protected void doSign() {
        String id = inputId.getText();
        String pw = inputPw.getText();
        String name = inputName.getText();
        // 아이디 중복검사
        isDupleChk(id);
        isNameNullChk();


        if (dupleChk) {
            showAlertErr("아이디가 중복됩니다.");
            return;
        }

        if (!idChk) {
            showAlertErr("알맞은 아이디 양식이 아닙니다.");
            return;
        }

        if (!pwChk) {
            showAlertErr("패스워드가 일치하지 않습니다.");
            return;
        }
        if (!isNameNull) {
            showAlertErr("이름을 입력해주세요.");
            return;
        }
        MemberDTO temp = new MemberDTO();
        temp.setMemId(id);
        temp.setMemPw(pw);
        temp.setMemName(name);

        if (memberService.singUp(temp)) {
            showAlertInfo("회원가입에 성공하였습니다.");
            moveMainStage(idCheck);

        } else {
            showAlertErr("회원가입에 실패하였습니다.");
        }


    }


}
