package cs112.lab08;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;

public class HelloController {
    public Label titleLabel;
    public ImageView cardImageView;
    public Label messageLabel;
    public Button drawCardButton;
    public ProgressBar gameProgressBar;

    // LOTERIA CARD DATA
    private LoteriaCard[] deck = {
            new LoteriaCard("EChALE STEM Logo", "0.png", 0),
            new LoteriaCard("Ingeniera", "1.png", 1),
            new LoteriaCard("Codificador", "2.png", 2),
            new LoteriaCard("Matemática", "8.png", 8),
            new LoteriaCard("Doctora", "9.png", 9)
    };
    private boolean[] drawn = new boolean[deck.length]; // track which have been shown
    private int numDrawn = 0;

    public void initialize() {
        titleLabel.setText("EChALE STEM Lotería!");
        cardImageView.setImage(deck[0].getImage()); // logo start
        messageLabel.setText("Press button to draw a card.");
        gameProgressBar.setProgress(0.0);

        // anonymous class event handler
        drawCardButton.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (numDrawn < deck.length - 1) {
                    int index;
                    do {
                        index = (int) (Math.random() * deck.length);
                    } while (drawn[index] || index == 0); // avoid logo and repeats

                    drawn[index] = true;
                    numDrawn++;
                    cardImageView.setImage(deck[index].getImage());
                    messageLabel.setText("You drew: " + deck[index].getCardName());
                    gameProgressBar.setProgress((double) numDrawn / (deck.length - 1));
                } else {
                    // Game over
                    cardImageView.setImage(deck[0].getImage()); // logo
                    messageLabel.setText("GAME OVER. No more cards! Exit and run program again to reset ^_^");
                    drawCardButton.setDisable(true);
                    gameProgressBar.setProgress(1.0);
                    gameProgressBar.setStyle("-fx-accent: red;");
                }
            }
        });
    }
}
