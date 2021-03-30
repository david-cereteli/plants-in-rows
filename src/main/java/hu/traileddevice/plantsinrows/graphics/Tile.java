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

package hu.traileddevice.plantsinrows.graphics;

import hu.traileddevice.plantsinrows.Main;
import javafx.scene.canvas.GraphicsContext;

public class Tile {
    private static final int DEFAULT_TILE_SIZE = Main.getTILE_WIDTH();
    private final TileSet TILESET;
    private final int TILESET_X;
    private final int TILESET_Y;
    private final int TILE_WIDTH;
    private final int TILE_HEIGHT;

    private Tile(TileSet tileSet, int x, int y, int tileWidth, int tileHeight) {
        TILESET_X = x * tileWidth;
        TILESET_Y = y * tileHeight;
        TILE_WIDTH = tileWidth;
        TILE_HEIGHT = tileHeight;
        TILESET = tileSet;
    }

    public Tile(TileSet tileSet, int x, int y) {
        this(tileSet, x, y, DEFAULT_TILE_SIZE, DEFAULT_TILE_SIZE);
    }

    public void render(GraphicsContext context, int canvasX, int canvasY) {
        context.drawImage(TILESET.getImage(), TILESET_X, TILESET_Y, TILE_WIDTH, TILE_HEIGHT,
                canvasX * TILE_WIDTH, canvasY * TILE_HEIGHT, TILE_WIDTH, TILE_WIDTH);
    }
}
