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
import hu.traileddevice.plantsinrows.graphics.TileSet;
import javafx.scene.canvas.GraphicsContext;

import java.util.HashMap;
import java.util.Map;

public class BackgroundZones {
    private static final int TOP_EDGE = 2;
    private static final int RIGHT_EDGE = 1;
    private static final int LEFT_EDGE = 2;
    private static final int VERTICAL_SPACING = 1;
    private static final int BOTTOM_EDGE = 2;
    private static final int HORIZONTAL_SPACING = 2;
    private static final int SOLUTION_HEIGHT = 1;
    private static final int GUESS_LAYER_EDGE = 1;
    private static final int GUESS_LAYER_OFFSET = 1;
    private static final TileSet backgroundTiles = new TileSet("/tilesets/ground.png", 96, 384);
    private static final Map<BackgroundElement, Tile> tileMap = new HashMap<>();
    private static final Map<WrapperElement, Tile> tileMapMain = new HashMap<>();
    private static final Map<WrapperElement, Tile> tileMapGuess = new HashMap<>();
    private final BackgroundLayer mainBackgroundLayer;
    private final BackgroundLayer guessBackgroundLayer;
    private final BackgroundLayer solutionBackgroundLayer;

    public BackgroundZones(int gameColumnCount, int gameRowCount, int offsetX, int offsetY) {
        int mainBackgroundWidth = LEFT_EDGE + gameColumnCount + VERTICAL_SPACING + gameColumnCount + RIGHT_EDGE;
        int mainBackgroundHeight = TOP_EDGE + gameRowCount + HORIZONTAL_SPACING + SOLUTION_HEIGHT + BOTTOM_EDGE;
        int guessLayerWidth = GUESS_LAYER_EDGE + gameColumnCount + GUESS_LAYER_EDGE;
        int guessLayerHeight = GUESS_LAYER_EDGE + gameRowCount + GUESS_LAYER_EDGE;
        int guessLayerOffsetX = offsetX + GUESS_LAYER_OFFSET;
        int guessLayerOffsetY = offsetY + GUESS_LAYER_OFFSET;
        int solutionLayerHeight = GUESS_LAYER_EDGE + SOLUTION_HEIGHT + GUESS_LAYER_EDGE;
        int solutionLayerOffsetY = guessLayerOffsetY + guessLayerHeight;

        mainBackgroundLayer = new BackgroundLayer(mainBackgroundWidth, mainBackgroundHeight,
                offsetX, offsetY, tileMapMain, 25);
        guessBackgroundLayer = new BackgroundLayer(guessLayerWidth, guessLayerHeight,
                guessLayerOffsetX, guessLayerOffsetY, tileMapGuess);
        solutionBackgroundLayer = new BackgroundLayer(guessLayerWidth, solutionLayerHeight,
                guessLayerOffsetX, solutionLayerOffsetY, tileMapGuess);
    }

    static {
        tileMap.put(BackgroundElement.TOP_LEFT_OUTER, new Tile(backgroundTiles, 0, 2));
        tileMap.put(BackgroundElement.TOP_OUTER, new Tile(backgroundTiles, 1, 2));
        tileMap.put(BackgroundElement.TOP_RIGHT_OUTER, new Tile(backgroundTiles, 2, 2));
        tileMap.put(BackgroundElement.LEFT_OUTER, new Tile(backgroundTiles, 0, 3));
        tileMap.put(BackgroundElement.MAIN_BG_FILL, new Tile(backgroundTiles, 1, 3));
        tileMap.put(BackgroundElement.MAIN_BG_FILL_SPEC_1, new Tile(backgroundTiles, 0, 5));
        tileMap.put(BackgroundElement.MAIN_BG_FILL_SPEC_2, new Tile(backgroundTiles, 1, 5));
        tileMap.put(BackgroundElement.MAIN_BG_FILL_SPEC_3, new Tile(backgroundTiles, 2, 5));
        tileMap.put(BackgroundElement.RIGHT_OUTER, new Tile(backgroundTiles, 2, 3));
        tileMap.put(BackgroundElement.BOTTOM_LEFT_OUTER, new Tile(backgroundTiles, 0, 4));
        tileMap.put(BackgroundElement.BOTTOM_OUTER, new Tile(backgroundTiles, 1, 4));
        tileMap.put(BackgroundElement.BOTTOM_RIGHT_OUTER, new Tile(backgroundTiles, 2, 4));

        tileMap.put(BackgroundElement.TOP_LEFT_INNER, new Tile(backgroundTiles, 0, 8));
        tileMap.put(BackgroundElement.TOP_INNER, new Tile(backgroundTiles, 1, 8));
        tileMap.put(BackgroundElement.TOP_RIGHT_INNER, new Tile(backgroundTiles, 2, 8));
        tileMap.put(BackgroundElement.LEFT_INNER, new Tile(backgroundTiles, 0, 9));
        tileMap.put(BackgroundElement.GUESS_FILL, new Tile(backgroundTiles, 1, 9));
        tileMap.put(BackgroundElement.GUESS_FILL_SPEC_1, new Tile(backgroundTiles, 0, 11));
        tileMap.put(BackgroundElement.GUESS_FILL_SPEC_2, new Tile(backgroundTiles, 1, 11));
        tileMap.put(BackgroundElement.GUESS_FILL_SPEC_3, new Tile(backgroundTiles, 2, 11));
        tileMap.put(BackgroundElement.RIGHT_INNER, new Tile(backgroundTiles, 2, 9));
        tileMap.put(BackgroundElement.BOTTOM_LEFT_INNER, new Tile(backgroundTiles, 0, 10));
        tileMap.put(BackgroundElement.BOTTOM_INNER, new Tile(backgroundTiles, 1, 10));
        tileMap.put(BackgroundElement.BOTTOM_RIGHT_INNER, new Tile(backgroundTiles, 2, 10));

        tileMapMain.put(WrapperElement.TOP_LEFT, tileMap.get(BackgroundElement.TOP_LEFT_OUTER));
        tileMapMain.put(WrapperElement.TOP, tileMap.get(BackgroundElement.TOP_OUTER));
        tileMapMain.put(WrapperElement.TOP_RIGHT, tileMap.get(BackgroundElement.TOP_RIGHT_OUTER));
        tileMapMain.put(WrapperElement.LEFT, tileMap.get(BackgroundElement.LEFT_OUTER));
        tileMapMain.put(WrapperElement.FILL, tileMap.get(BackgroundElement.MAIN_BG_FILL));
        tileMapMain.put(WrapperElement.FILL_SPEC_1, tileMap.get(BackgroundElement.MAIN_BG_FILL_SPEC_1));
        tileMapMain.put(WrapperElement.FILL_SPEC_2, tileMap.get(BackgroundElement.MAIN_BG_FILL_SPEC_2));
        tileMapMain.put(WrapperElement.FILL_SPEC_3, tileMap.get(BackgroundElement.MAIN_BG_FILL_SPEC_3));
        tileMapMain.put(WrapperElement.RIGHT, tileMap.get(BackgroundElement.RIGHT_OUTER));
        tileMapMain.put(WrapperElement.BOTTOM_LEFT, tileMap.get(BackgroundElement.BOTTOM_LEFT_OUTER));
        tileMapMain.put(WrapperElement.BOTTOM, tileMap.get(BackgroundElement.BOTTOM_OUTER));
        tileMapMain.put(WrapperElement.BOTTOM_RIGHT, tileMap.get(BackgroundElement.BOTTOM_RIGHT_OUTER));

        tileMapGuess.put(WrapperElement.TOP_LEFT, tileMap.get(BackgroundElement.TOP_LEFT_INNER));
        tileMapGuess.put(WrapperElement.TOP, tileMap.get(BackgroundElement.TOP_INNER));
        tileMapGuess.put(WrapperElement.TOP_RIGHT, tileMap.get(BackgroundElement.TOP_RIGHT_INNER));
        tileMapGuess.put(WrapperElement.LEFT, tileMap.get(BackgroundElement.LEFT_INNER));
        tileMapGuess.put(WrapperElement.FILL, tileMap.get(BackgroundElement.GUESS_FILL));
        tileMapGuess.put(WrapperElement.FILL_SPEC_1, tileMap.get(BackgroundElement.GUESS_FILL_SPEC_1));
        tileMapGuess.put(WrapperElement.FILL_SPEC_2, tileMap.get(BackgroundElement.GUESS_FILL_SPEC_2));
        tileMapGuess.put(WrapperElement.FILL_SPEC_3, tileMap.get(BackgroundElement.GUESS_FILL_SPEC_3));
        tileMapGuess.put(WrapperElement.RIGHT, tileMap.get(BackgroundElement.RIGHT_INNER));
        tileMapGuess.put(WrapperElement.BOTTOM_LEFT, tileMap.get(BackgroundElement.BOTTOM_LEFT_INNER));
        tileMapGuess.put(WrapperElement.BOTTOM, tileMap.get(BackgroundElement.BOTTOM_INNER));
        tileMapGuess.put(WrapperElement.BOTTOM_RIGHT, tileMap.get(BackgroundElement.BOTTOM_RIGHT_INNER));
    }

    public void render(GraphicsContext context) {
        mainBackgroundLayer.render(context);
        guessBackgroundLayer.render(context);
        solutionBackgroundLayer.render(context);
    }
}
