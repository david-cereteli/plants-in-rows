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
import hu.traileddevice.plantsinrows.logic.Symbol;
import javafx.scene.canvas.GraphicsContext;

import java.util.Map;
import java.util.function.BiFunction;

public class SolutionLayer {
    private final Map<Symbol, Tile> tileMap;
    private final Symbol[] solutionData;
    private final int width;
    private final int offsetX;
    private final int offsetY;
    private final Map<Symbol, BiFunction<GraphicsContext, Integer[], Runnable>> multiTileMap;

    public SolutionLayer(int width, int offsetX, int offsetY, Map<Symbol, Tile> tileMap,
                         Symbol[] solutionData, Map<Symbol, BiFunction<GraphicsContext, Integer[], Runnable>> multiTileMap) {
        this.tileMap = tileMap;
        this.solutionData = solutionData;
        this.width = width;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.multiTileMap = multiTileMap;
    }

    public void render(GraphicsContext context) {
        for (int x = 0; x < width; x++) {
            Symbol currentSymbol = solutionData[x];
            if (multiTileMap != null && multiTileMap.containsKey(currentSymbol)) {
                multiTileMap.get(currentSymbol).apply(context, new Integer[]{x + offsetX, offsetY}).run();
            }
            if (currentSymbol != null) {
                tileMap.get(currentSymbol).render(context, x + offsetX, offsetY);
            }
        }
    }
}
