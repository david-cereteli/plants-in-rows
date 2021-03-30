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

package hu.traileddevice.plantsinrows.graphics.layers.background;

import hu.traileddevice.plantsinrows.graphics.Tile;
import hu.traileddevice.plantsinrows.graphics.layers.VisualLayer;
import hu.traileddevice.plantsinrows.util.TileRowFiller;

import java.util.Map;

public class BackgroundLayer extends VisualLayer<WrapperElement> {

    public BackgroundLayer(int width, int height, int offsetX, int offsetY, Map<WrapperElement, Tile> tileMap) {
        super(width, height, offsetX, offsetY, WrapperElement.class, tileMap);

        int row = 0;
        fillRow(width, row, WrapperElement.TOP_LEFT, WrapperElement.TOP_RIGHT, WrapperElement.TOP);

        int expectedMidRowCount = height - 2;
        for (int i = 0; i < expectedMidRowCount; i++) {
            row++;
            fillRow(width, row, WrapperElement.LEFT, WrapperElement.RIGHT, WrapperElement.FILL);
        }

        row++;
        fillRow(width, row, WrapperElement.BOTTOM_LEFT, WrapperElement.BOTTOM_RIGHT, WrapperElement.BOTTOM);
    }

    public BackgroundLayer(int width, int height, int offsetX, int offsetY, Map<WrapperElement, Tile> tileMap,
                           int chanceForExtraTiles) {
        super(width, height, offsetX, offsetY, WrapperElement.class, tileMap);

        if (chanceForExtraTiles <= 0 || chanceForExtraTiles > 99) throw new IllegalArgumentException("Invalid chance supplied.");

        int row = 0;
        fillRow(width, row, WrapperElement.TOP_LEFT, WrapperElement.TOP_RIGHT, WrapperElement.TOP);

        int expectedMidRowCount = height - 2;
        for (int i = 0; i < expectedMidRowCount; i++) {
            row++;
            super.tiles[0][row] = WrapperElement.LEFT;
            super.tiles[width - 1][row] = WrapperElement.RIGHT;
            TileRowFiller.includeExtraElementsWithChance(super.tiles, row, 1, width - 1, chanceForExtraTiles,
                    WrapperElement.FILL, WrapperElement.FILL_SPEC_1, WrapperElement.FILL_SPEC_2, WrapperElement.FILL_SPEC_3);
        }

        row++;
        fillRow(width, row, WrapperElement.BOTTOM_LEFT, WrapperElement.BOTTOM_RIGHT, WrapperElement.BOTTOM);
    }

    private void fillRow(int width, int row, WrapperElement leftEdge, WrapperElement rightEdge, WrapperElement mid) {
        super.tiles[0][row] = leftEdge;
        super.tiles[width - 1][row] = rightEdge;
        for (int i = 1; i < width - 1; i++) {
            super.tiles[i][row] = mid;
        }
    }
}