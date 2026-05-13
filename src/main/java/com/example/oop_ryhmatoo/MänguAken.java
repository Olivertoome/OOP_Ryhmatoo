package com.example.oop_ryhmatoo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

public class MänguAken {

    private final MänguLoogika loogika = new MänguLoogika();
    private boolean ootabSisestust = false;

    private Label eludLabel;
    private Label punktidLabel;
    private Label parimLabel;
    private Label numbriLabel;
    private Label teateLabel;
    private TextField sisestusVäli;
    private Button sisestaNupp;

    public MänguAken(Stage lava) {
        BorderPane juur = new BorderPane();
        juur.setPadding(new Insets(20));

        Label pealkiri = new Label("MÄLUMÄNG");
        pealkiri.setFont(Font.font(24));

        eludLabel = new Label();
        punktidLabel = new Label();
        parimLabel = new Label();

        HBox ülarida = new HBox(20, pealkiri, eludLabel, punktidLabel, parimLabel);
        ülarida.setAlignment(Pos.CENTER);
        ülarida.setPadding(new Insets(15));

        teateLabel = new Label("Vajuta „Uus mäng“, et alustada.");
        teateLabel.setFont(Font.font(16));

        numbriLabel = new Label("");
        numbriLabel.setFont(Font.font(80));

        VBox kesk = new VBox(20, teateLabel, numbriLabel);
        kesk.setAlignment(Pos.CENTER);
        kesk.setPadding(new Insets(20));
        kesk.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        sisestusVäli = new TextField();
        sisestusVäli.setPromptText("Sisesta kogu jada");
        sisestusVäli.setMaxWidth(250);
        sisestusVäli.setDisable(true);

        sisestaNupp = new Button("Sisesta");
        sisestaNupp.setDisable(true);

        Button uusMängNupp = new Button("Uus mäng");

        sisestaNupp.setOnAction(e -> sisesta());      // hiire sündmus
        sisestusVäli.setOnAction(e -> sisesta());     // Enter klaviatuurilt
        uusMängNupp.setOnAction(e -> alustaMäng());

        HBox alumine = new HBox(10, sisestusVäli, sisestaNupp, uusMängNupp);
        alumine.setAlignment(Pos.CENTER);
        alumine.setPadding(new Insets(15));

        juur.setTop(ülarida);
        juur.setCenter(kesk);
        juur.setBottom(alumine);

        BorderPane.setAlignment(ülarida, Pos.CENTER);
        BorderPane.setAlignment(kesk, Pos.CENTER);
        BorderPane.setAlignment(alumine, Pos.CENTER);

        Scene stseen = new Scene(juur, 600, 400);

        lava.setScene(stseen);
        lava.setTitle("Mälumäng");
        lava.setMinWidth(420);
        lava.setMinHeight(300);
        lava.show();

        uuendaInfo();
    }

    private void alustaMäng() {
        loogika.uusMäng();
        uuendaInfo();
        näitaSekventsi(loogika.alustaSekventsi());
    }

    private void näitaSekventsi(List<Integer> jada) {
        ootabSisestust = false;
        sisestusVäli.setDisable(true);
        sisestaNupp.setDisable(true);
        sisestusVäli.clear();

        int uusNumber = jada.get(jada.size() - 1);

        teateLabel.setText("Jäta number meelde:");
        numbriLabel.setText(String.valueOf(uusNumber));

        Timeline paus = new Timeline(
                new KeyFrame(Duration.seconds(2), e -> {
                    numbriLabel.setText("");
                    teateLabel.setText("Sisesta kogu jada:");
                    sisestusVäli.setDisable(false);
                    sisestaNupp.setDisable(false);
                    sisestusVäli.requestFocus();
                    ootabSisestust = true;
                })
        );

        paus.play();
    }

    private void sisesta() {
        if (!ootabSisestust) {
            return;
        }

        String vastus = sisestusVäli.getText().replaceAll("\\s", "");

        if (vastus.isEmpty()) {
            teateLabel.setText("Palun sisesta jada!");
            return;
        }

        if (!vastus.matches("[0-9]+")) {
            teateLabel.setText("Kasuta ainult numbreid 0–9!");
            sisestusVäli.clear();
            return;
        }

        MänguLoogika.Tulemus tulemus = loogika.kontrolliVastus(vastus);
        uuendaInfo();

        if (tulemus == MänguLoogika.Tulemus.ÕIGE) {
            teateLabel.setText("Õige! Järgmine number tuleb kohe.");
            ootabSisestust = false;

            Timeline paus = new Timeline(
                    new KeyFrame(Duration.seconds(1), e ->
                            näitaSekventsi(loogika.alustaSekventsi())
                    )
            );
            paus.play();

        } else if (tulemus == MänguLoogika.Tulemus.VALE) {
            teateLabel.setText("Vale! Proovi sama jada uuesti.");
            ootabSisestust = false;

            Timeline paus = new Timeline(
                    new KeyFrame(Duration.seconds(1), e ->
                            näitaSekventsi(loogika.getJada())
                    )
            );
            paus.play();

        } else {
            boolean uusRekord = loogika.salvestaRekord();
            uuendaInfo();

            ootabSisestust = false;
            sisestusVäli.setDisable(true);
            sisestaNupp.setDisable(true);

            if (uusRekord) {
                teateLabel.setText("Mäng läbi! Uus rekord!");
            } else {
                teateLabel.setText("Mäng läbi! Punktid: " + loogika.getPunktid());
            }

            numbriLabel.setText(":(");
        }
    }

    private void uuendaInfo() {
        eludLabel.setText("Elud: " + loogika.getElud());
        punktidLabel.setText("Punktid: " + loogika.getPunktid());
        parimLabel.setText("Rekord: " + loogika.getParimTulemus());
    }
}