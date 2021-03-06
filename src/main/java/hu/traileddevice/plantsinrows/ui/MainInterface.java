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
import hu.traileddevice.plantsinrows.graphics.TileSet;
import hu.traileddevice.plantsinrows.logic.Difficulty;
import hu.traileddevice.plantsinrows.logic.GameState;
import hu.traileddevice.plantsinrows.logic.Symbol;
import hu.traileddevice.plantsinrows.ui.components.DragBar;
import hu.traileddevice.plantsinrows.ui.components.ImageButton;
import hu.traileddevice.plantsinrows.ui.components.ImageButtonFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class MainInterface {
    private static final TileSet icons = Main.getIcons();
    private final Main application;
    private final List<ImageButton> symbolButtons;
    private ImageButton commitGuessButton;
    private ImageButton resetGuessButton;
    private ImageButton newGameButton;
    private final Color ACTIVE = Color.SIENNA;
    private final Color INACTIVE = Color.DIMGRAY;
    private final Color IN_USE = Color.FIREBRICK;
    private final Color HIGHLIGHT = Color.DARKORANGE;
    private static final int ROW_COUNT_WITHOUT_GUESS_LIMIT = 7;
    private static final int FRAME_TOP_BAR_HEIGHT = 2;
    private static final int LENGTH_TO_INDEX_MODIFIER = -1;
    private static final int MID_BUTTON_GROUP_HEIGHT = 5;
    private static final int MID_BUTTON_GROUP_MODIFIER = 1;

    public MainInterface(Main application, int tileWidth) {
        symbolButtons = new ArrayList<>();
        icons.changeColor(ACTIVE);
        this.application = application;
        int gameRowCount = application.getGameSession().getGUESS_LIMIT();
        int bottomButtonsVerticalOffset = ROW_COUNT_WITHOUT_GUESS_LIMIT + gameRowCount + FRAME_TOP_BAR_HEIGHT
                + LENGTH_TO_INDEX_MODIFIER;
        int symbolButtonsVerticalOffset = bottomButtonsVerticalOffset / 2 - MID_BUTTON_GROUP_HEIGHT / 2
                + MID_BUTTON_GROUP_MODIFIER;

        addDragBar(application, tileWidth);
        ImageButtonFactory imageButtonFactory = new ImageButtonFactory(application.getStackPane(), icons, tileWidth,
                ACTIVE, IN_USE);
        addButtons(bottomButtonsVerticalOffset, symbolButtonsVerticalOffset, imageButtonFactory);
    }

    private void addDragBar(Main application, int tileWidth) {
        DragBar dragBar = new DragBar(application.getPrimaryStage().getWidth(),
                tileWidth * 2 - Main.getVISUAL_CORRECTION_FOR_SCROLL_TOP(), application.getPrimaryStage());
        application.getStackPane().getChildren().add(dragBar);
        StackPane.setMargin(dragBar, new Insets(0, 0, 0, 0));
        dragBar.setAlignment(Pos.CENTER);
        dragBar.getChildren().add(new Label("This is a test build of Plants in Rows. Intended for internal use only."));
    }

    private void addButtons(int bottomButtonsVerticalOffset, int symbolButtonVerticalOffset, ImageButtonFactory imageButtonFactory) {
        ImageButton exitButton = imageButtonFactory.createImageButton(3, FRAME_TOP_BAR_HEIGHT, 1,
                "Exit");
        exitButton.setOnAction(this::exitGame);
        newGameButton = imageButtonFactory.createImageButton(4, FRAME_TOP_BAR_HEIGHT, 2,
                "New game!");
        newGameButton.setOnAction(this::newGame);

        ImageButton symbolOneButton = imageButtonFactory.createImageButton(5, symbolButtonVerticalOffset,
                2, "Pumpkin");
        symbolOneButton.setOnAction(ignoreEvent -> addSymbol(Symbol.ONE));
        symbolButtons.add(symbolOneButton);
        ImageButton symbolTwoButton = imageButtonFactory.createImageButton(10, symbolButtonVerticalOffset,
                1, "Water-melon");
        symbolTwoButton.setOnAction(ignoreEvent -> addSymbol(Symbol.TWO));
        symbolButtons.add(symbolTwoButton);
        ImageButton symbolThreeButton = imageButtonFactory.createImageButton(9,
                symbolButtonVerticalOffset + 1,1, "Tomato");
        symbolThreeButton.setOnAction(ignoreEvent -> addSymbol(Symbol.THREE));
        symbolButtons.add(symbolThreeButton);
        ImageButton symbolFourButton = imageButtonFactory.createImageButton(0,
                symbolButtonVerticalOffset + 2,2, "Eggplant");
        symbolFourButton.setOnAction(ignoreEvent -> addSymbol(Symbol.FOUR));
        symbolButtons.add(symbolFourButton);
        ImageButton symbolFiveButton = imageButtonFactory.createImageButton(2,
                symbolButtonVerticalOffset + 2,1, "Cauliflower");
        symbolFiveButton.setOnAction(ignoreEvent -> addSymbol(Symbol.FIVE));
        symbolButtons.add(symbolFiveButton);
        ImageButton symbolSixButton = imageButtonFactory.createImageButton(1,
                symbolButtonVerticalOffset + 1,2, "Yellow bell pepper");
        symbolSixButton.setOnAction(ignoreEvent -> addSymbol(Symbol.SIX));
        symbolButtons.add(symbolSixButton);

        commitGuessButton = imageButtonFactory.createImageButton(8,
                symbolButtonVerticalOffset + 4, 1,"Commit guess!");
        commitGuessButton.setOnAction(this::commitGuess);
        commitGuessButton.changeColor(INACTIVE);
        resetGuessButton = imageButtonFactory.createImageButton(7,
                symbolButtonVerticalOffset + 4, 2,"Reset guess!");
        resetGuessButton.setOnAction(this::clearGuess);
        resetGuessButton.changeColor(INACTIVE);
        ImageButton infoButton = imageButtonFactory.createImageButton(6, bottomButtonsVerticalOffset,
                1,"Info");
        infoButton.setOnAction(this::displayInfo);
    }

    private void addSymbol(Symbol symbol) {
        boolean hasAdded = application.getGameSession().addSymbolToRow(symbol);

        if (hasAdded && application.getGameSession().getGameState().equals(GameState.FULL_ROW)) {
            symbolButtons.forEach(button -> button.changeColor(INACTIVE));
            commitGuessButton.changeColor(ACTIVE);
        } else if (hasAdded) {
            resetGuessButton.changeColor(ACTIVE);
        }

        application.refresh();
    }

    private void commitGuess(ActionEvent ignoredEvent) {
        boolean hasCommitted = application.getGameSession().commitGuess();

        if (hasCommitted && (application.getGameSession().getGameState().equals(GameState.WIN) ||
                application.getGameSession().getGameState().equals(GameState.LOSE))) {
            commitGuessButton.changeColor(INACTIVE);
            resetGuessButton.changeColor(INACTIVE);
            newGameButton.changeColor(HIGHLIGHT);
        } else if (hasCommitted) {
            symbolButtons.forEach(button -> button.changeColor(ACTIVE));
            commitGuessButton.changeColor(INACTIVE);
            resetGuessButton.changeColor(INACTIVE);
        }

        application.refresh();
    }

    private void clearGuess(ActionEvent ignoredEvent) {
        boolean hasCleared = application.getGameSession().clearGuess();

        if (hasCleared) {
            symbolButtons.forEach(button -> button.changeColor(ACTIVE));
            commitGuessButton.changeColor(INACTIVE);
            resetGuessButton.changeColor(INACTIVE);
        }

        application.refresh();
    }

    private void newGame(ActionEvent ignoredEvent) {
        NewGameWindow newGameWindow = new NewGameWindow(application.getPrimaryStage());
        newGameWindow.display();
        if (newGameWindow.isShouldStartNew()) {
            Difficulty newDifficulty =
                    Difficulty.getEnum(((RadioButton)newGameWindow.getDifficultyGroup().getSelectedToggle()).getText());
            if (newDifficulty.equals(Main.getDifficulty())) {
                application.getGameSession().reset();
                symbolButtons.forEach(button -> button.changeColor(ACTIVE));
                commitGuessButton.changeColor(INACTIVE);
                resetGuessButton.changeColor(INACTIVE);
                newGameButton.revertToOriginalColor();
                application.refresh();
            } else {
                Main.setDifficulty(newDifficulty);
                application.getPrimaryStage().hide();
                application.setupGame(application.getPrimaryStage());
            }
        }
    }

    private void displayInfo(ActionEvent ignoredEvent) {
        AboutWindow aboutWindow = new AboutWindow(application.getPrimaryStage());
        aboutWindow.display();
    }

    private void exitGame(ActionEvent ignoredEvent) {
        QuitConfirmWindow exitWindow = new QuitConfirmWindow(application.getPrimaryStage());
        exitWindow.display();
        if (exitWindow.isShouldExit()) {
            Platform.exit();
        }
    }
}
