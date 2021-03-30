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

import hu.traileddevice.plantsinrows.graphics.TileSet;
import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class ImageButtonFactory {
    private final StackPane stackPane;
    private final TileSet tileSet;
    private final int dimensions;
    private final Color originalColor;
    private final Color pressedColor;

    public ImageButtonFactory(StackPane stackPane, TileSet tileSet, int dimensions,
                              Color originalColor, Color pressedColor) {
        this.stackPane = stackPane;
        this.tileSet = tileSet;
        this.dimensions = dimensions;
        this.originalColor = originalColor;
        this.pressedColor = pressedColor;
    }

    public ImageButton createImageButton(int locationInTileSet, int positionFromTop, int positionFromRight,
                                         String toolTipText) {
        ImageButton button = new ImageButton(tileSet, dimensions, locationInTileSet, originalColor, pressedColor);
        button.setPrefWidth(dimensions);
        button.setPrefHeight(dimensions);
        button.setStyle("-fx-background-color: transparent;");
        button.setPadding(Insets.EMPTY);
        button.setTooltip(new ScrollTooltip(toolTipText));
        stackPane.getChildren().add(button);
        StackPane.setMargin(button, new Insets(positionFromTop * dimensions, positionFromRight * dimensions, 0, 0));
        return button;
    }
}
