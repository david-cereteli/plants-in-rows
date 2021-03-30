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
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ImageButton extends Button {
    private final TileSet tileSet;
    private final int dimensions;
    private final int locationInTileSet;
    private ImageView icon;
    private final Rectangle2D viewportRect;
    private final Color originalColor;
    private Color currentColor;

    public ImageButton(TileSet tileSet, int dimensions, int locationInTileSet, Color originalColor, Color pressedColor) {
        this.tileSet = tileSet;
        this.dimensions = dimensions;
        this.locationInTileSet = locationInTileSet;
        this.icon = new ImageView(tileSet.getImage());
        this.viewportRect = new Rectangle2D(locationInTileSet * dimensions, 0,
                dimensions, dimensions);
        icon.setViewport(viewportRect);
        this.setGraphic(icon);
        this.hoverProperty().addListener(event -> this.setCursor(Cursor.HAND));

        this.originalColor = originalColor;
        this.currentColor = this.originalColor;
        this.setOnMousePressed(event -> changeColorTemporarily(pressedColor));
        this.setOnMouseReleased(event -> changeColorTemporarily(this.currentColor));
    }

    public void changeColor(Color newColor) {
        this.currentColor = newColor;
        changeColorTemporarily(newColor);
    }

    public void changeColorTemporarily(Color newColor) {
        tileSet.changeColorOfPart(newColor, locationInTileSet * dimensions, 0);
        icon = new ImageView(tileSet.getImage());
        icon.setViewport(viewportRect);
        this.setGraphic(icon);
    }

    public void revertToOriginalColor() {
        changeColor(originalColor);
    }
}
