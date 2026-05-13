import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

/**
 * Peaaken. Ehitab JavaFX kasutajaliidese ja haldab kõiki sündmusi.
 * Ühendab MänguLoogika kasutajaliidese elementidega.
 *
 * Olulisemad meetodid:
 * MänguAken(Stage) – konstruktor, ehitab akna ja näitab intro
 * alustaMäng()     – lähtestab mängu ja alustab esimest sekventsi
 * näitaSekventsi() – animeerib numbrite kuvamise Timeline abil
 * sisesta()        – töötleb mängija vastuse, valideerib ja edastab loogikale
 * uuendaInfo()     – uuendab elu/punkti/rekordi silte
 */
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

        // --- ÜLABAAR ---
        Label pealkiri = new Label("MÄLUMÄNG");
        pealkiri.setFont(Font.font("Arial", 22));
        pealkiri.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        eludLabel = new Label("♥♥♥");
        eludLabel.setFont(Font.font(20));
        eludLabel.setStyle("-fx-text-fill: #e74c3c;");

        punktidLabel = new Label("Punktid: 0");
        punktidLabel.setFont(Font.font(15));
        punktidLabel.setStyle("-fx-text-fill: white;");

        parimLabel = new Label("Rekord: " + loogika.getParimTulemus());
        parimLabel.setFont(Font.font(15));
        parimLabel.setStyle("-fx-text-fill: #f1c40f;");

        Region vahe = new Region();
        HBox.setHgrow(vahe, Priority.ALWAYS);

        HBox ülabaar = new HBox(15, pealkiri, vahe, eludLabel, punktidLabel, parimLabel);
        ülabaar.setPadding(new Insets(12, 16, 12, 16));
        ülabaar.setAlignment(Pos.CENTER_LEFT);
        ülabaar.setStyle("-fx-background-color: #2c3e50;");

        // --- KESKEL: number display ---
        teateLabel = new Label("Vajuta 'Uus mäng' alustamiseks  |  F2 = uus mäng");
        teateLabel.setFont(Font.font(15));

        numbriLabel = new Label("");
        numbriLabel.setFont(Font.font(100));
        numbriLabel.setAlignment(Pos.CENTER);
        numbriLabel.setMaxWidth(Double.MAX_VALUE);

        VBox kesk = new VBox(20, teateLabel, numbriLabel);
        kesk.setAlignment(Pos.CENTER);
        kesk.setPadding(new Insets(20));
        VBox.setVgrow(kesk, Priority.ALWAYS);

        // Skaala numbri font akna kõrgusega
        juur.heightProperty().addListener((obs, oldH, newH) ->
                numbriLabel.setFont(Font.font(Math.max(50, newH.doubleValue() * 0.22)))
        );

        // --- ALAMOSA: sisestus + nupud ---
        sisestusVäli = new TextField();
        sisestusVäli.setPromptText("Sisesta jada siin...");
        sisestusVäli.setFont(Font.font(18));
        sisestusVäli.setMaxWidth(230);
        sisestusVäli.setDisable(true);

        sisestaNupp = new Button("Sisesta ↵");
        sisestaNupp.setFont(Font.font(15));
        sisestaNupp.setDisable(true);
        sisestaNupp.setDefaultButton(true);
        sisestaNupp.setOnAction(e -> sisesta());

        HBox sisestusRida = new HBox(10, sisestusVäli, sisestaNupp);
        sisestusRida.setAlignment(Pos.CENTER);

        // Numbrinupud 0-9 (hiir)
        HBox nupurida = new HBox(5);
        nupurida.setAlignment(Pos.CENTER);
        for (int i = 0; i <= 9; i++) {
            final int nr = i;
            Button b = new Button(String.valueOf(i));
            b.setFont(Font.font(15));
            b.setMinSize(42, 42);
            b.setOnAction(e -> {
                if (ootabSisestust) sisestusVäli.appendText(String.valueOf(nr));
            });
            nupurida.getChildren().add(b);
        }

        Button uusMängNupp = new Button("⟳  Uus mäng  (F2)");
        uusMängNupp.setFont(Font.font(14));
        uusMängNupp.setOnAction(e -> alustaMäng());

        VBox alamosa = new VBox(10, sisestusRida, nupurida, uusMängNupp);
        alamosa.setAlignment(Pos.CENTER);
        alamosa.setPadding(new Insets(12));
        alamosa.setStyle("-fx-background-color: #ecf0f1;");

        juur.setTop(ülabaar);
        juur.setCenter(kesk);
        juur.setBottom(alamosa);

        Scene stseen = new Scene(juur, 640, 440);

        // --- KLAVIATUURI SÜNDMUSED ---
        stseen.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                sisesta();
            } else if (e.getCode() == KeyCode.F2) {
                alustaMäng();
            } else if (e.getCode() == KeyCode.BACK_SPACE && ootabSisestust) {
                String cur = sisestusVäli.getText();
                if (!cur.isEmpty()) {
                    sisestusVäli.setText(cur.substring(0, cur.length() - 1));
                    sisestusVäli.positionCaret(sisestusVäli.getText().length());
                }
            } else if (ootabSisestust) {
                String tekst = e.getText();
                if (tekst != null && tekst.matches("[0-9]")) {
                    sisestusVäli.appendText(tekst);
                }
            }
        });

        lava.setScene(stseen);
        lava.setTitle("Mälumäng");
        lava.setMinWidth(420);
        lava.setMinHeight(320);
        lava.show();

        // Intro
        Alert intro = new Alert(Alert.AlertType.INFORMATION);
        intro.initOwner(lava);
        intro.setTitle("Tere tulemast!");
        intro.setHeaderText("Mälumäng");
        intro.setContentText(
                "Mängu reeglid:\n" +
                "  • Vaata numbreid, mis ilmuvad ekraanile\n" +
                "  • Sisesta need samas järjekorras\n" +
                "  • Iga raundiga lisandub üks number juurde\n" +
                "  • Sul on 3 elu\n\n" +
                "Juhtimine:\n" +
                "  • Numbrid: klõpsa nuppe (0–9) või kirjuta klaviatuurilt\n" +
                "  • Enter = sisesta vastus\n" +
                "  • F2 = uus mäng\n\n" +
                "Edu!"
        );
        intro.showAndWait();
    }

    private void alustaMäng() {
        loogika.uusMäng();
        uuendaInfo();
        List<Integer> jada = loogika.alustaSekventsi();
        näitaSekventsi(jada);
    }

    /** Animeerib jada numbrite kuvamise ükshaaval. */
    private void näitaSekventsi(List<Integer> jada) {
        ootabSisestust = false;
        sisestusVäli.setDisable(true);
        sisestaNupp.setDisable(true);
        sisestusVäli.clear();

        teateLabel.setText("Vaata! (" + jada.size() + " numbrit)");
        numbriLabel.setText("");

        Timeline tl = new Timeline();
        for (int i = 0; i < jada.size(); i++) {
            final String nr = String.valueOf(jada.get(i));
            long delay = (long) i * 1200;
            tl.getKeyFrames().add(new KeyFrame(Duration.millis(delay),     e2 -> numbriLabel.setText(nr)));
            tl.getKeyFrames().add(new KeyFrame(Duration.millis(delay + 750), e2 -> numbriLabel.setText("")));
        }
        long lõpp = (long) jada.size() * 1200;
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(lõpp), e2 -> {
            teateLabel.setText("Sisesta jada:");
            sisestusVäli.setDisable(false);
            sisestaNupp.setDisable(false);
            sisestusVäli.requestFocus();
            ootabSisestust = true;
        }));
        tl.play();
    }

    /** Töötleb mängija sisestuse: valideerib, edastab loogikale, reageerib tulemusele. */
    private void sisesta() {
        if (!ootabSisestust) return;

        String tekst = sisestusVäli.getText().trim();

        // Erinditöötlus: tühi sisestus
        if (tekst.isEmpty()) {
            teateLabel.setText("Palun sisesta jada!");
            return;
        }

        // Erinditöötlus: mittenumeeriline sisestus
        String puhastatud = tekst.replaceAll("\\s", "");
        if (!puhastatud.matches("[0-9]+")) {
            teateLabel.setText("Kasuta ainult numbreid 0–9!");
            sisestusVäli.clear();
            return;
        }

        MänguLoogika.Tulemus tulemus;
        try {
            tulemus = loogika.kontrolliVastus(puhastatud);
        } catch (Exception e) {
            teateLabel.setText("Viga: " + e.getMessage());
            return;
        }

        uuendaInfo();

        switch (tulemus) {
            case ÕIGE -> {
                teateLabel.setText("✓ Õige! Järgmine raund...");
                ootabSisestust = false;
                new Timeline(new KeyFrame(Duration.millis(900), e2 ->
                        näitaSekventsi(loogika.alustaSekventsi())
                )).play();
            }
            case VALE -> {
                teateLabel.setText("✗ Vale! Elusid: " + loogika.getElud() + ". Näitame uuesti...");
                ootabSisestust = false;
                new Timeline(new KeyFrame(Duration.millis(1500), e2 ->
                        näitaSekventsi(loogika.getJada())
                )).play();
            }
            case MÄNG_LÄBI -> {
                boolean uusRekord = loogika.salvestaRekord();
                uuendaInfo();
                teateLabel.setText("Mäng läbi! Punktid: " + loogika.getPunktid());
                numbriLabel.setText(":(");
                ootabSisestust = false;
                sisestusVäli.setDisable(true);
                sisestaNupp.setDisable(true);

                String rekordRida = uusRekord
                        ? "UUS REKORD! Palju õnne!"
                        : "Rekord: " + loogika.getParimTulemus();

                Alert lõppAlert = new Alert(Alert.AlertType.INFORMATION);
                lõppAlert.setTitle("Mäng läbi");
                lõppAlert.setHeaderText("Mäng läbi!");
                lõppAlert.setContentText(
                        "Sinu punktid: " + loogika.getPunktid() + "\n" +
                        rekordRida + "\n\n" +
                        "Vajuta F2 uue mängu alustamiseks."
                );
                lõppAlert.showAndWait();
            }
        }
    }

    private void uuendaInfo() {
        int e = loogika.getElud();
        eludLabel.setText("♥".repeat(Math.max(0, e)) + "♡".repeat(Math.max(0, 3 - e)));
        punktidLabel.setText("Punktid: " + loogika.getPunktid());
        parimLabel.setText("Rekord: " + loogika.getParimTulemus());
    }
}
