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

package hu.traileddevice.plantsinrows.graphics.layers;

import hu.traileddevice.plantsinrows.graphics.Tile;
import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.function.BiFunction;

public abstract class VisualLayer<E> {
    @Getter private final int width;
    @Getter private final int height;
    @Getter protected E[][] tiles;
    private int OFFSET_X;
    private int OFFSET_Y;
    private final Map<E, Tile> tileMap;
    private Map<E, BiFunction<GraphicsContext, Integer[], Runnable>> multiTileMap;

    /*
     * Inspired by: https://stackoverflow.com/a/530289
     */
    public VisualLayer(int width, int height, Class<E> clazz, Map<E, Tile> tileMap) {
        this.width = width;
        this.height = height;
        this.tileMap = tileMap;
        @SuppressWarnings("unchecked")
        final E[][] tiles = (E[][]) Array.newInstance(clazz, width, height);
        this.tiles = tiles;
    }

    public VisualLayer(int width, int height, int offsetX, int offsetY, Class<E> clazz, Map<E, Tile> tileMap) {
        this(width, height, clazz, tileMap);
        this.OFFSET_X = offsetX;
        this.OFFSET_Y = offsetY;
    }

    public VisualLayer(int width, int height, int offsetX, int offsetY, Map<E, Tile> tileMap, E[][] tiles) {
        this.width = width;
        this.height = height;
        this.tileMap = tileMap;
        this.tiles = tiles;
        this.OFFSET_X = offsetX;
        this.OFFSET_Y = offsetY;
    }

    public VisualLayer(int width, int height, int offsetX, int offsetY, Map<E, Tile> tileMap, E[][] tiles,
                       Map<E, BiFunction<GraphicsContext, Integer[], Runnable>> multiTileMap) {
        this.width = width;
        this.height = height;
        this.tileMap = tileMap;
        this.tiles = tiles;
        this.OFFSET_X = offsetX;
        this.OFFSET_Y = offsetY;
        this.multiTileMap = multiTileMap;
    }

    public void render(GraphicsContext context) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                E currentSymbol = tiles[x][y];
                if (multiTileMap != null && multiTileMap.containsKey(currentSymbol)) {
                    multiTileMap.get(currentSymbol).apply(context, new Integer[]{x + OFFSET_X, y + OFFSET_Y}).run();
                }
                if (currentSymbol != null) {
                    tileMap.get(currentSymbol).render(context, x + OFFSET_X, y + OFFSET_Y);
                }
            }
        }
    }
}
