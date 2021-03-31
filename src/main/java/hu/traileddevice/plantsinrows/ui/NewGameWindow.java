/*
 * Plants in Rows is a clone of the board game Mastermind.
 * Copyright (C) 2021 David Cereteli
 *
 * This file is part of Plants in Rows.
 *
 * Plants in Rows is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Plants in Rows is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Plants in Rows.  If not, see <https://www.gnu.org/licenses/>.
 */

package hu.traileddevice.plantsinrows.ui;

import hu.traileddevice.plantsinrows.Main;
import hu.traileddevice.plantsinrows.ui.components.ImageRadioButton;
import hu.traileddevice.plantsinrows.ui.components.ImageRadioButtonFactory;
import hu.traileddevice.plantsinrows.ui.components.text.TitleText;
import hu.traileddevice.plantsinrows.ui.stage.ScrollStage;
import hu.traileddevice.plantsinrows.util.PixelCalculator;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.Getter;

import java.util.function.Consumer;

import static hu.traileddevice.plantsinrows.ui.components.text.Fonts.*;

public class NewGameWindow {
    private final Stage primaryStage;
    private static final int WIDTH = 7;
    private static final int HEIGHT = 8;
    private static final int BOTTOM_MARGIN = 60;
    @Getter
    private boolean shouldStartNew;
    @Getter
    private ToggleGroup difficultyGroup;

    public NewGameWindow(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void display() {
        ScrollStage scrollWindow = new ScrollStage(WIDTH, HEIGHT, primaryStage);
        Consumer<ActionEvent> yesFunction = (ignoreEvent) -> {
            setResult(true);
            scrollWindow.close();
        };
        Consumer<ActionEvent> noFunction = (ignoreEvent) -> {
            setResult(false);
            scrollWindow.close();
        };
        addContent(scrollWindow);
        scrollWindow.addDragBar();
        scrollWindow.addYesNoButtons("Start new Game", "Back to game", yesFunction, noFunction);
        scrollWindow.showAndWait();
    }

    public void setResult(boolean result) {
        this.shouldStartNew = result;
    }

    private void addContent(ScrollStage scrollWindow) {
        StackPane layout = scrollWindow.getStackPane();

        ImageRadioButtonFactory imageRadioButtonFactory = new ImageRadioButtonFactory(Main.getIcons(),
                Main.getTILE_WIDTH(), Color.SIENNA, Color.KHAKI);
        ImageRadioButton easyDifficulty = imageRadioButtonFactory.createImageRadioButton(4, "Easy");
        ImageRadioButton normalDifficulty = imageRadioButtonFactory.createImageRadioButton(4, "Normal");
        ImageRadioButton hardDifficulty = imageRadioButtonFactory.createImageRadioButton(4, "Hard");

        difficultyGroup = new ToggleGroup();

        easyDifficulty.setToggleGroup(difficultyGroup);
        normalDifficulty.setToggleGroup(difficultyGroup);
        hardDifficulty.setToggleGroup(difficultyGroup);

        switch (Main.getDifficulty()) {
            case EASY:
                difficultyGroup.selectToggle(easyDifficulty);
                break;
            case NORMAL:
                difficultyGroup.selectToggle(normalDifficulty);
                break;
            case HARD:
                difficultyGroup.selectToggle(hardDifficulty);
                break;
        }

        TitleText title = new TitleText("Select difficulty", LARGE_REGULAR);

        VBox windowContents = new VBox();
        windowContents.setAlignment(Pos.CENTER);

        VBox difficultyOptions = new VBox();
        difficultyOptions.getChildren().addAll(easyDifficulty, normalDifficulty, hardDifficulty);
        difficultyOptions.setAlignment(Pos.CENTER_LEFT);
        difficultyOptions.setPadding(new Insets(0, 0, 0, calculateLeftMargin(normalDifficulty)));

        windowContents.getChildren().addAll(title, difficultyOptions);
        layout.getChildren().add(windowContents);
        StackPane.setAlignment(windowContents, Pos.CENTER);
        StackPane.setMargin(windowContents, new Insets(0, 0, BOTTOM_MARGIN, 0));
    }

    private double calculateLeftMargin(ImageRadioButton imageRadioButton) {
        return (WIDTH * Main.getTILE_WIDTH()) / 2.0
                - (PixelCalculator.getStringLength(imageRadioButton.getText()) / 2.0 + Main.getTILE_WIDTH() / 2.0);
    }
}
