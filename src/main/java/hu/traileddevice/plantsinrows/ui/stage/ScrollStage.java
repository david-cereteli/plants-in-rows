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

package hu.traileddevice.plantsinrows.ui.stage;

import hu.traileddevice.plantsinrows.Main;
import hu.traileddevice.plantsinrows.graphics.layers.popup.PopupFrame;
import hu.traileddevice.plantsinrows.ui.components.DragBar;
import hu.traileddevice.plantsinrows.ui.components.ImageButton;
import hu.traileddevice.plantsinrows.ui.components.ImageButtonFactory;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;

public class ScrollStage extends Stage {
    private static final int TILE_SIZE = Main.getTILE_WIDTH();
    @Getter private final StackPane stackPane;
    private final GraphicsContext context;
    private final PopupFrame windowLayer;
    private final int WIDTH;
    private final int HEIGHT;
    private boolean hasDragBar;
    private boolean hasCloseButton;

    public ScrollStage(int width, int height, Stage parentStage) {
        super(StageStyle.TRANSPARENT);
        this.WIDTH = width;
        this.HEIGHT = height;

        windowLayer = new PopupFrame(width, height);

        Canvas canvas = new Canvas(width * TILE_SIZE, height * TILE_SIZE);
        context = canvas.getGraphicsContext2D();

        stackPane = new StackPane();
        stackPane.getChildren().add(canvas);
        stackPane.setStyle("-fx-background-color: transparent;");
        stackPane.setAlignment(Pos.TOP_RIGHT);
        Scene scene = new Scene(stackPane);

        this.setScene(scene);
        this.setResizable(false);
        this.initModality(Modality.APPLICATION_MODAL);
        this.getIcons().add(Main.getGameIcon());
        refresh();
        scene.setFill(null);

        centerOnParentStage(parentStage);
    }

    /*
     * Inspired by: https://stackoverflow.com/a/44165221
     */
    private void centerOnParentStage(Stage parentStage) {
        ChangeListener<Number> widthListener = (ignoredObservable, ignoredOldValue, newValue) -> {
            double stageWidth = newValue.doubleValue();
            this.setX(parentStage.getX() + parentStage.getWidth() / 2 - stageWidth / 2);
        };
        ChangeListener<Number> heightListener = (ignoredObservable, ignoredOldValue, newValue) -> {
            double stageHeight = newValue.doubleValue();
            this.setY(parentStage.getY() + parentStage.getHeight() / 2 - stageHeight / 2);
        };

        this.widthProperty().addListener(widthListener);
        this.heightProperty().addListener(heightListener);

        this.setOnShown(ignoredEvent -> {
            this.widthProperty().removeListener(widthListener);
            this.heightProperty().removeListener(heightListener);
        });
    }

    public void refresh() {
        windowLayer.render(context);
    }

    public void addDragBar() {
        if (hasDragBar) {
            throw new IllegalStateException("addDragBar() already called.");
        }
        stackPane.getChildren().add(new DragBar(WIDTH * TILE_SIZE,
                TILE_SIZE - Main.getVISUAL_CORRECTION_FOR_SCROLL_TOP(), this));
        hasDragBar = true;
    }

    public void addCloseButton(String buttonText) {
        if (hasCloseButton) {
            throw new IllegalStateException("addCloseButton() already called.");
        }
        ImageButtonFactory imageButtonFactory = new ImageButtonFactory(stackPane, Main.getIcons(), TILE_SIZE,
                Color.SIENNA, Color.GREENYELLOW);
        ImageButton exitButton = imageButtonFactory.createImageButton(8, HEIGHT - 2,
                WIDTH / 2, buttonText);
        exitButton.setOnAction(ignoreEvent -> this.close());
        hasCloseButton = true;
    }
}
