package com.example.main;

import concert.dto.ConcertDTO;
import concert.service.ConcertService;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import member.service.MemberService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class HelloController {
    @FXML
    public Button searchBtn;
    @FXML
    public Button resetBtn;
    @FXML
    private Button loginBtn;

    @FXML
    private Label loginStatus;
    @FXML
    private VBox showConcert;
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
    ConcertService concertService = ConcertService.getInstance();
    ArrayList<HBox> hBoxList = new ArrayList<>();
    ArrayList<ConcertDTO> selectedDate = new ArrayList<>();

    @FXML
    protected void doSearch() {
        if (memberService.login == null){
            UsefullMethod.showAlertWarn("먼저 로그인을 해주세요.");
            return;
        }

        // 리스트 지우고 새로 띄우기
        showConcert.getChildren().clear();
        hBoxList.clear();
        selectedDate.clear();

        // 날짜 가져오기
        String startDate = startDay.getValue().toString().replace("-", "");
        String endDate = endDay.getValue().toString().replace("-", "");

        //todo 이거 는 좀 나중에
        String selectDate = startDate + " ~ " + endDate;


        // 날짜에 따른 리스트 정리
        if (endDate.equals("20991231")) {
            selectedDate = concertService.selectOnlyStartDay(startDate);
        } else {
            selectedDate = concertService.selectDay(startDate, endDate);
        }


        // 가격이 정해져 있을 시 입력
        int min = 0;
        int max = 99999999;
        if (!startMoney.getText().isEmpty()) {
            min = Integer.parseInt(startMoney.getText());
        }
        if (!endMoney.getText().isEmpty()) {
            max = Integer.parseInt(endMoney.getText());
        }

        selectedDate = setChargeList(selectedDate, min, max);

        System.out.println(region.getValue());
        System.out.println(concertType.getValue());
        // 값이 전체일 경우 모두 긁을 수 있게 값을 null값 지정


        nullChk();

        // 설정된 지역 값이 있을 시 필터링
        if (region.getValue() != null) {

            selectedDate = setRegionList(selectedDate, (String) region.getValue());

        }

        // 설정된 타입 값이 있을 시 필터링
        if (concertType.getValue() != null) {
            selectedDate = setTypeList(selectedDate, (String) concertType.getValue());
        }


        //hbox 생성
        int hBoxCount;
        if (selectedDate.size() % 3 == 0) {
            hBoxCount = selectedDate.size() / 3;
        } else {
            hBoxCount = (selectedDate.size() / 3) + 1;
        }

        for (int i = 0; i < hBoxCount; i++) {
            HBox temp = new HBox();
            hBoxList.add(temp);
        }
        int hIdx = 0;
        // 담기
        for (int i = 0; i < selectedDate.size(); i++) {


            if (i != 0 && i % 3 == 0) {
                hIdx++;
            }

            HBox nowHBox = hBoxList.get(hIdx);

            ConcertDTO temp = selectedDate.get(i);

            Image image = new Image(temp.getCo_imgUrl());

            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(240);
            imageView.setFitHeight(200);

            Label title = new Label(temp.getCo_title());
            title.setAlignment(Pos.CENTER);
            title.setPrefWidth(240);

            Label period = new Label("기간 : " + temp.getCo_period());
            period.setAlignment(Pos.CENTER);

            Label url = new Label(temp.getCo_url());
            url.setAlignment(Pos.CENTER);

            Label tel = new Label("문의처 : " + temp.getCo_tel());
            tel.setAlignment(Pos.CENTER);

            Label charge = new Label("가격 : " + temp.getCo_charge());
            charge.setAlignment(Pos.CENTER);

            Label type = new Label("공연 종류 : " + temp.getCo_type());
            type.setAlignment(Pos.CENTER);

            Label eventSite = new Label("장소 : " + temp.getCo_eventSite());
            eventSite.setAlignment(Pos.CENTER);


            VBox vBox = new VBox();
            vBox.setPrefWidth(260);
            vBox.setPrefHeight(390);
            vBox.setAlignment(Pos.CENTER);

            BorderStroke stroke = new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, null, new BorderWidths(1, 1, 1, 1));
            Border border = new Border(stroke);
            vBox.setBorder(border);

            vBox.getChildren().add(imageView);
            vBox.getChildren().add(title);
            vBox.getChildren().add(period);
            vBox.getChildren().add(url);
            vBox.getChildren().add(tel);
            vBox.getChildren().add(charge);
            vBox.getChildren().add(type);
            vBox.getChildren().add(eventSite);

            nowHBox.getChildren().add(vBox);
        }

        for (int i = 0; i < hBoxList.size(); i++) {
            showConcert.getChildren().add(hBoxList.get(i));
        }


        //todo 날짜값에 따라 검색 가능한 경우의 수
        if (region.getValue() == null) {
            region.setValue("지역 전체");
        }

        if (concertType.getValue() == null) {
            concertType.setValue("종류 전체");
        }

        String co_period; /*공연 기간*/
        String co_tel;/*문의 안내*/
        long co_evPeriod;/*개최기간을 숫자로*/
        String co_title;/*제목*/
        String co_url; /*해당 소개 링크*/
        String co_imgUrl; /*이미지 url*/

    }


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
        LocalDate localDate = LocalDate.now();
        LocalDate targetDate = LocalDate.of(2099, 12, 31);
        startDay.setValue(localDate);
        endDay.setValue(targetDate);
    }

    /**
     * 가격이 설정되어 있을 시 사용 메소드
     *
     * @param conList 날짜 검색으로 가져온 배열
     * @param min     최소금액
     * @param max     최대금액
     * @return 금액필터링 된 배열
     */
    protected ArrayList<ConcertDTO> setChargeList(ArrayList<ConcertDTO> conList, int min, int max) {

        ArrayList<ConcertDTO> tempList = new ArrayList<>();

        for (int i = 0; i < conList.size(); i++) {
            int nowCharge = (int) conList.get(i).getCo_charge();
            if (nowCharge >= min && nowCharge <= max) {
                tempList.add(conList.get(i));
            }
        }
        return tempList;
    }

    /**
     * 지역 설정 시 사용 메소드
     *
     * @param conList 날짜 검색으로 가져온 배열
     * @param region  설정된 지역값
     * @return 필터링 된 배열
     */
    protected ArrayList<ConcertDTO> setRegionList(ArrayList<ConcertDTO> conList, String region) {

        ArrayList<ConcertDTO> tempList = new ArrayList<>();

        for (int i = 0; i < conList.size(); i++) {
            String nowSite = conList.get(i).getCo_eventSite();
            if (nowSite.equals(region)) {
                tempList.add(conList.get(i));
            }
        }
        return tempList;
    }

    /**
     * 지역 설정 시 사용 메소드
     *
     * @param conList 날짜 검색으로 가져온 배열
     * @param type    설정된 공연종류 값
     * @return 필터링 된 배열
     */
    protected ArrayList<ConcertDTO> setTypeList(ArrayList<ConcertDTO> conList, String type) {

        ArrayList<ConcertDTO> tempList = new ArrayList<>();

        for (int i = 0; i < conList.size(); i++) {
            String nowSite = conList.get(i).getCo_eventSite();
            if (nowSite.equals(type)) {
                tempList.add(conList.get(i));
            }
        }
        return tempList;
    }

    @FXML
    protected void resetConfig() {
        LocalDate localDate = LocalDate.now();
        LocalDate targetDate = LocalDate.of(2099, 12, 31);
        startDay.setValue(localDate);
        endDay.setValue(targetDate);
        startMoney.setText("0");
        endMoney.setText("99999999");
        region.setValue("지역 전체");
        concertType.setValue("종류 전체");
        region.setPromptText("지역");
        concertType.setPromptText("종류");


    }

    protected void nullChk() {
        String regionVal = (String) region.getValue();
        String type = (String) concertType.getValue();

        if (regionVal != null && regionVal.equals("지역 전체")) {
            region.setValue(null);
        }
        if (type != null && type.equals("종류 전체")) {
            concertType.setValue(null);
        }
    }
}
