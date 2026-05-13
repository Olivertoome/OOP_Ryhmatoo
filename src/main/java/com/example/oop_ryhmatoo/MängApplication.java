package com.example.oop_ryhmatoo;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Rakenduse käivitusklass. Loob peaakna ja käivitab JavaFX.
 */
public class MängApplication extends Application {

    @Override
    public void start(Stage peaLava) {
        new MänguAken(peaLava);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
