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
import hu.traileddevice.plantsinrows.graphics.TileSet;
import hu.traileddevice.plantsinrows.logic.GameSession;
import hu.traileddevice.plantsinrows.logic.GameState;
import hu.traileddevice.plantsinrows.logic.Score;
import hu.traileddevice.plantsinrows.logic.Symbol;
import javafx.scene.canvas.GraphicsContext;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class GameZones {
    private static final int GUESS_OFFSET_X = 2;
    private static final int GUESS_OFFSET_Y = 4;
    private static final int GUESS_SCORE_SPACING_X = 1;
    private static final int GUESS_SOLUTION_SPACING_Y = 2;
    private static final TileSet plantsAndWood = new TileSet("/tilesets/plants_and_wood.png", 352, 32);
    private static final Map<Symbol, Tile> symbolToTileMap = new HashMap<>();
    private static final Map<Score, Tile> scoreToTileMap = new HashMap<>();
    private static final Map<Symbol, Tile> extraTileMap = new HashMap<>();
    private static final Map<Symbol, BiFunction<GraphicsContext, Integer[], Runnable>> multiTileMap = new HashMap<>();
    private final GameLayer<Symbol> guessingLayer;
    private final GameLayer<Score> scoreLayer;
    private final SolutionLayer solutionLayer;
    private final GameSession gameSession;

    public GameZones(GameSession gameSession) {
        this.gameSession = gameSession;
        int width = gameSession.getSEQUENCE_LENGTH();
        int height = gameSession.getGUESS_LIMIT();
        int scoreOffsetX = GUESS_OFFSET_X + width + GUESS_SCORE_SPACING_X;
        int solutionOffsetY = GUESS_OFFSET_Y + height + GUESS_SOLUTION_SPACING_Y;

        guessingLayer = new GameLayer<>(width, height, GUESS_OFFSET_X, GUESS_OFFSET_Y, symbolToTileMap,
                gameSession.getGuesses(), multiTileMap);
        scoreLayer = new GameLayer<>(width, height, scoreOffsetX, GUESS_OFFSET_Y, scoreToTileMap,
                gameSession.getEvaluation());
        solutionLayer = new SolutionLayer(width, GUESS_OFFSET_X, solutionOffsetY, symbolToTileMap,
                gameSession.getSolution(), multiTileMap);
    }

    static {
        scoreToTileMap.put(Score.RIGHT_LOCATION, new Tile(plantsAndWood, 8, 0)); // big woodpile
        scoreToTileMap.put(Score.RIGHT_SYMBOL_WRONG_LOCATION, new Tile(plantsAndWood, 6, 0)); // single wood

        symbolToTileMap.put(Symbol.ONE, new Tile(plantsAndWood, 2, 0)); // pumpkin
        symbolToTileMap.put(Symbol.TWO, new Tile(plantsAndWood, 5, 0)); // water-melon
        symbolToTileMap.put(Symbol.THREE, new Tile(plantsAndWood, 3, 0)); // tomato
        symbolToTileMap.put(Symbol.FOUR, new Tile(plantsAndWood, 1, 0)); // eggplant
        symbolToTileMap.put(Symbol.FIVE, new Tile(plantsAndWood, 0, 0)); // cauliflower
        symbolToTileMap.put(Symbol.SIX, new Tile(plantsAndWood, 9, 0)); // yellow bell pepper

        extraTileMap.put(Symbol.THREE, new Tile(plantsAndWood, 4, 0)); // tomato top
        extraTileMap.put(Symbol.SIX, new Tile(plantsAndWood, 10, 0)); // pepper top

        multiTileMap.put(Symbol.THREE, (context, coordinates) -> {
            Tile tile = extraTileMap.get(Symbol.THREE);
            return () -> tile.render(context, coordinates[0], coordinates[1] - 1);
        });
        multiTileMap.put(Symbol.SIX, (context, coordinates) -> {
            Tile tile = extraTileMap.get(Symbol.SIX);
            return () -> tile.render(context, coordinates[0], coordinates[1] - 1);
        });
    }

    public void render(GraphicsContext context) {
        guessingLayer.render(context);
        scoreLayer.render(context);
        if (gameSession.getGameState().equals(GameState.WIN) || gameSession.getGameState().equals(GameState.LOSE)) {
            solutionLayer.render(context);
        }
    }
}
