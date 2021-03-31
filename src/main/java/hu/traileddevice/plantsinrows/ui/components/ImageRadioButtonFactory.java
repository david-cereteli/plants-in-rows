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
import javafx.scene.paint.Color;

public class ImageRadioButtonFactory {
    private final TileSet tileSet;
    private final int dimensions;
    private final Color selected;
    private final Color unselected;

    public ImageRadioButtonFactory(TileSet tileSet, int dimensions, Color selected, Color unselected) {
        this.tileSet = tileSet;
        this.dimensions = dimensions;
        this.selected = selected;
        this.unselected = unselected;
    }

    public ImageRadioButton createImageRadioButton(int locationInTileSet, String text) {
        return new ImageRadioButton(tileSet, dimensions, locationInTileSet, selected, unselected, text);
    }
}
