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
import javafx.beans.value.ChangeListener;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import static hu.traileddevice.plantsinrows.ui.components.text.Fonts.REGULAR;

public class ImageRadioButton extends RadioButton {
    private final TileSet tileSet;
    private final int dimensions;
    private final int locationInTileSet;
    private final Rectangle2D viewportRect;
    private final Color selectedColor;
    private final Color unselectedColor;

    public ImageRadioButton(TileSet tileSet, int dimensions, int locationInTileSet, Color selectedColor, Color unselectedColor,
                            String text) {
        super(text);
        this.getStyleClass().remove("radio-button");
        this.setFont(REGULAR);
        this.selectedColor = selectedColor;
        this.unselectedColor = unselectedColor;
        this.tileSet = tileSet;
        this.dimensions = dimensions;
        this.locationInTileSet = locationInTileSet;
        this.viewportRect = new Rectangle2D(locationInTileSet * dimensions, 0, dimensions, dimensions);

        changeColor(unselectedColor);

        this.hoverProperty().addListener(event -> this.setCursor(Cursor.HAND));

        ChangeListener<Boolean> selectedListener = (ignoredObservable, ignoredOldValue, newValue) -> {
            if (newValue) {
                changeColor(this.selectedColor);
            } else {
                changeColor(this.unselectedColor);
            }
        };

        this.selectedProperty().addListener(selectedListener);
    }

    public void changeColor(Color newColor) {
        tileSet.changeColorOfPart(newColor, locationInTileSet * dimensions, 0);
        ImageView icon = new ImageView(tileSet.getImage());
        icon.setViewport(viewportRect);
        this.setGraphic(icon);
    }
}
