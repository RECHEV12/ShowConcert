package com.example.main;

import concert.dto.ConcertDTO;
import concert.service.ConcertService;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 화면 전환 메소드
 */
public class UsefullMethod {
    public static HostServices hostServices;
    public static void changeStage(Label nowLabel, String changeScene, String stageName) {

        Stage stage = (Stage) nowLabel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(changeScene));

        try {
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setTitle(stageName);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void moveMainStage(Label nowLabel) {
    ArrayList<ConcertDTO> allDay;
        ConcertService concertService = ConcertService.getInstance();
        allDay = concertService.allDay();

        Stage stage = (Stage) nowLabel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));


        try {
            Scene scene = new Scene(fxmlLoader.load());
            HelloController hc = fxmlLoader.getController();
            hc.setHostServices(hostServices);
            hc.setAllDayList(allDay);
            hc.firstBooting(allDay);
            hc.areYouLogin();
            stage.setScene(scene);
            stage.setTitle("Concert List");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void showAlertInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();
    }

    public static void showAlertErr(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();
    }

    public static void showAlertWarn(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();

    }
}
