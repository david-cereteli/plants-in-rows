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

import hu.traileddevice.plantsinrows.ui.components.text.TitleText;
import hu.traileddevice.plantsinrows.ui.stage.ScrollStage;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;

import java.util.function.Consumer;

import static hu.traileddevice.plantsinrows.ui.components.text.Fonts.LARGE_REGULAR;

public class QuitConfirmWindow {
    private final Stage primaryStage;
    private static final int WIDTH = 9;
    private static final int HEIGHT = 5;
    private static final int BOTTOM_MARGIN = 40;
    @Getter private boolean shouldExit;

    public QuitConfirmWindow(Stage primaryStage) {
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
        scrollWindow.addYesNoButtons("Exit", "Back to game", yesFunction, noFunction);
        scrollWindow.showAndWait();
    }

    public void setResult(boolean result) {
        this.shouldExit = result;
    }

    private void addContent(ScrollStage scrollWindow) {
        StackPane layout = scrollWindow.getStackPane();
        TitleText title = new TitleText("Are you sure you wish to leave?", LARGE_REGULAR);
        VBox windowContents = new VBox();
        windowContents.setAlignment(Pos.CENTER);
        windowContents.getChildren().add(title);
        layout.getChildren().add(windowContents);
        StackPane.setAlignment(windowContents, Pos.CENTER);
        StackPane.setMargin(windowContents, new Insets(0,0,BOTTOM_MARGIN, 0));
    }
}
