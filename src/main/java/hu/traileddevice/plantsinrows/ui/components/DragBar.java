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

package hu.traileddevice.plantsinrows.ui.components;

import hu.traileddevice.plantsinrows.util.Coordinate;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
 * Inspired by: https://stackoverflow.com/a/11781291
 */
public class DragBar extends HBox {
    private final Coordinate mouseDragDelta = new Coordinate();

    public DragBar(double width, double height, Stage currentStage) {
        this.setPrefWidth(width);
        this.setPrefHeight(height);
        this.setMaxHeight(height);
        hoverProperty().addListener(mouseEvent -> {
            setCursor(Cursor.OPEN_HAND);
        });
        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseDragDelta.setX(currentStage.getX() - mouseEvent.getScreenX());
                mouseDragDelta.setY(currentStage.getY() - mouseEvent.getScreenY());
                setCursor(Cursor.CLOSED_HAND);
            }
        });
        this.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                currentStage.setX(mouseEvent.getScreenX() + mouseDragDelta.getX());
                currentStage.setY(mouseEvent.getScreenY() + mouseDragDelta.getY());
            }
        });
        this.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setCursor(Cursor.OPEN_HAND);
            }
        });
    }
}
