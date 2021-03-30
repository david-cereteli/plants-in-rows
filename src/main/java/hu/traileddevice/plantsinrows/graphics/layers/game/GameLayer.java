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

package hu.traileddevice.plantsinrows.graphics.layers.game;

import hu.traileddevice.plantsinrows.graphics.Tile;
import hu.traileddevice.plantsinrows.graphics.layers.VisualLayer;
import javafx.scene.canvas.GraphicsContext;

import java.util.Map;
import java.util.function.BiFunction;

public class GameLayer<E> extends VisualLayer<E> {

    public GameLayer(int width, int height, int offsetX, int offsetY, Map<E, Tile> tileMap,
                     E[][] guessData, Map<E, BiFunction<GraphicsContext, Integer[], Runnable>> multiTileMap) {
        super(width, height, offsetX, offsetY, tileMap, guessData, multiTileMap);
    }

    public GameLayer(int width, int height, int offsetX, int offsetY, Map<E, Tile> tileMap,
                     E[][] guessData) {
        super(width, height, offsetX, offsetY, tileMap, guessData);
    }
}
