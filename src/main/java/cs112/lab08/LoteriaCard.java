/**************************************************
*  EChALE STEM Loteria - GUI JavaFX Program
*  Author: Ramysannta Hy
*  Description: Draws random STEM-themed Lotería cards
*  Features: GUI layout, card drawing, event handling,
*            prevents duplicates, progress bar, and end-game state
**************************************************/

package cs112.lab08;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.image.Image;

import java.util.Random;

public class Main extends Application {

    // GUI COMPONENTS
    private Label titleLabel, messageLabel;
    private ImageView cardImageView;
    private Button drawCardButton;
    private ProgressBar gameProgressBar;

    // LOTERIA DATA
    private LoteriaCard[] cards = {
        new LoteriaCard("Ingeniera", "1.png", 1),
        new LoteriaCard("Matemático", "2.png", 2),
        new LoteriaCard("Científica", "3.png", 3),
        new LoteriaCard("Astronauta", "4.png", 4)
        // Add more cards if needed!
    };

    private boolean[] drawn = new boolean[cards.length];
    private int drawnCount = 0;
    private Random rand = new Random();

    @Override
    public void start(Stage primaryStage) {
        // SETUP COMPONENTS
        titleLabel = new Label("¡Lotería STEM!");
        titleLabel.setFont(new Font("Arial", 28));
        titleLabel.setTextFill(Color.DARKBLUE);

        cardImageView = new ImageView();
        cardImageView.setFitWidth(300);
        cardImageView.setFitHeight(300);
        cardImageView.setPreserveRatio(true);

        messageLabel = new Label("Presiona el botón para sacar una carta...");
        messageLabel.setFont(new Font(14));

        drawCardButton = new Button("Sacar Carta");
        gameProgressBar = new ProgressBar(0);

        // EVENT HANDLING - Anonymous Class
        drawCardButton.setOnAction(e -> {
            if (drawnCount < cards.length) {
                int index;
                do {
                    index = rand.nextInt(cards.length);
                } while (drawn[index]);

                drawn[index] = true;
                drawnCount++;

                LoteriaCard drawnCard = cards[index];
                cardImageView.setImage(drawnCard.getImage());
                messageLabel.setText("Sacaste: " + drawnCard.getCardName());

                gameProgressBar.setProgress((double) drawnCount / cards.length);

                if (drawnCount == cards.length) {
                    // Game Over
                    cardImageView.setImage(new LoteriaCard().getImage()); // Logo
                    messageLabel.setText("GAME OVER. No more cards!\nExit and run again to reset ^_^");
                    drawCardButton.setDisable(true);
                    gameProgressBar.setStyle("-fx-accent: red;");
                }
            }
        });

        // SETUP LAYOUT
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(titleLabel, cardImageView, messageLabel, drawCardButton, gameProgressBar);

        // SCENE + STAGE
        Scene scene = new Scene(layout, 350, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("EChALE STEM Lotería");
        primaryStage.show();
    }

    // MAIN DRIVER
    public static void main(String[] args) {
        launch(args);
    }
}
