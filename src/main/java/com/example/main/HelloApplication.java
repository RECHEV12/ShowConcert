package com.example.main;

import concert.dto.ConcertDTO;
import concert.service.ConcertService;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {

    private HostServices hostServices;
    public static ArrayList<ConcertDTO> allDay;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Concert List");
        HelloController hc = fxmlLoader.getController();
        hc.setHostServices(getHostServices());
        hc.setAllDayList(allDay);
        hc.firstBooting(allDay);
        hc.areYouLogin();
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        ConcertService concertService = ConcertService.getInstance();
        allDay = concertService.allDay();
        launch();


    }
}