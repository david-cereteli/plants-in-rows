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

package hu.traileddevice.plantsinrows.graphics.layers.frame;

import hu.traileddevice.plantsinrows.graphics.Tile;
import hu.traileddevice.plantsinrows.graphics.TileSet;
import hu.traileddevice.plantsinrows.graphics.layers.VisualLayer;
import hu.traileddevice.plantsinrows.util.TileRowFiller;
import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class FrameLayer extends VisualLayer<FrameElement> {
    private static final int RIGHT_EDGE = 1;
    private static final int LEFT_EDGE = 1; // grass is on this
    private static final int VERTICAL_SPACING = 1;
    private static final int INTERFACE_WIDTH = 2;
    @Getter private static final int TOP_BAR_HEIGHT = 2;
    private static final int BOTTOM_BAR_HEIGHT = 2;
    private static final int HORIZONTAL_SPACING = 1;
    private static final int SOLUTION_HEIGHT = 1;
    private static final TileSet frameTiles = new TileSet("/tilesets/scrollsandblocks.png", 544, 320);
    private static final Map<FrameElement, Tile> tileMap = new HashMap<>();

    static {
        tileMap.put(FrameElement.TOP_LEFT_WINDOW, new Tile(frameTiles, 11, 4));
        tileMap.put(FrameElement.TOP_RIGHT_WINDOW, new Tile(frameTiles, 16, 4));
        tileMap.put(FrameElement.TOP_BOTTOM_LEFT_WINDOW, new Tile(frameTiles, 11, 5));
        tileMap.put(FrameElement.TOP_BOTTOM_RIGHT_WINDOW, new Tile(frameTiles, 16, 5));
        tileMap.put(FrameElement.TOP_WINDOW_1, new Tile(frameTiles, 12, 4));
        tileMap.put(FrameElement.TOP_WINDOW_2, new Tile(frameTiles, 13, 4));
        tileMap.put(FrameElement.TOP_WINDOW_3, new Tile(frameTiles, 14, 4));
        tileMap.put(FrameElement.TOP_WINDOW_4, new Tile(frameTiles, 15, 4));
        tileMap.put(FrameElement.TOP_BOTTOM_WINDOW_1, new Tile(frameTiles, 12, 5));
        tileMap.put(FrameElement.TOP_BOTTOM_WINDOW_2, new Tile(frameTiles, 13, 5));
        tileMap.put(FrameElement.TOP_BOTTOM_WINDOW_3, new Tile(frameTiles, 14, 5));
        tileMap.put(FrameElement.TOP_BOTTOM_WINDOW_4, new Tile(frameTiles, 15, 5));

        tileMap.put(FrameElement.LEFT_WINDOW_HIGH, new Tile(frameTiles, 11, 6));
        tileMap.put(FrameElement.RIGHT_WINDOW_HIGH, new Tile(frameTiles, 16, 6));
        tileMap.put(FrameElement.LEFT_WINDOW_LOW, new Tile(frameTiles, 11, 7));
        tileMap.put(FrameElement.RIGHT_WINDOW_LOW, new Tile(frameTiles, 16, 7));

        tileMap.put(FrameElement.MIDDLE_TOP_WINDOW_1, new Tile(frameTiles, 12, 6));
        tileMap.put(FrameElement.MIDDLE_TOP_WINDOW_2, new Tile(frameTiles, 13, 6));
        tileMap.put(FrameElement.MIDDLE_TOP_WINDOW_3, new Tile(frameTiles, 14, 6));
        tileMap.put(FrameElement.MIDDLE_TOP_WINDOW_4, new Tile(frameTiles, 15, 6));

        tileMap.put(FrameElement.MIDDLE_BOTTOM_WINDOW_1, new Tile(frameTiles, 12, 7));
        tileMap.put(FrameElement.MIDDLE_BOTTOM_WINDOW_2, new Tile(frameTiles, 13, 7));
        tileMap.put(FrameElement.MIDDLE_BOTTOM_WINDOW_3, new Tile(frameTiles, 14, 7));
        tileMap.put(FrameElement.MIDDLE_BOTTOM_WINDOW_4, new Tile(frameTiles, 15, 7));

        tileMap.put(FrameElement.BOTTOM_LEFT_WINDOW, new Tile(frameTiles, 11, 8));
        tileMap.put(FrameElement.BOTTOM_RIGHT_WINDOW, new Tile(frameTiles, 16, 8));
        tileMap.put(FrameElement.BOTTOM_BOTTOM_LEFT_WINDOW, new Tile(frameTiles, 11, 9));
        tileMap.put(FrameElement.BOTTOM_BOTTOM_RIGHT_WINDOW, new Tile(frameTiles, 16, 9));
        tileMap.put(FrameElement.BOTTOM_WINDOW_1, new Tile(frameTiles, 12, 8));
        tileMap.put(FrameElement.BOTTOM_WINDOW_2, new Tile(frameTiles, 13, 8));
        tileMap.put(FrameElement.BOTTOM_WINDOW_3, new Tile(frameTiles, 14, 8));
        tileMap.put(FrameElement.BOTTOM_WINDOW_4, new Tile(frameTiles, 15, 8));
        tileMap.put(FrameElement.BOTTOM_BOTTOM_WINDOW_1, new Tile(frameTiles, 12, 9));
        tileMap.put(FrameElement.BOTTOM_BOTTOM_WINDOW_2, new Tile(frameTiles, 13, 9));
        tileMap.put(FrameElement.BOTTOM_BOTTOM_WINDOW_3, new Tile(frameTiles, 14, 9));
        tileMap.put(FrameElement.BOTTOM_BOTTOM_WINDOW_4, new Tile(frameTiles, 15, 9));
    }

    private FrameLayer(int width, int height) {
        super(width, height, FrameElement.class, tileMap);

        int row = 0;
        super.tiles[0][row] = FrameElement.TOP_LEFT_WINDOW;
        super.tiles[width - 1][row] = FrameElement.TOP_RIGHT_WINDOW;
        super.tiles[width - 2][row] = FrameElement.TOP_WINDOW_4;
        TileRowFiller.splitElementsEvenly(super.tiles, row, 1, width - 2,
                FrameElement.TOP_WINDOW_1, FrameElement.TOP_WINDOW_2, FrameElement.TOP_WINDOW_3);

        row++;
        super.tiles[0][row] = FrameElement.TOP_BOTTOM_LEFT_WINDOW;
        super.tiles[width - 1][row] = FrameElement.TOP_BOTTOM_RIGHT_WINDOW;
        super.tiles[width - 2][row] = FrameElement.TOP_BOTTOM_WINDOW_4;
        TileRowFiller.splitElementsEvenly(super.tiles, row, 1, width - 2,
                FrameElement.TOP_BOTTOM_WINDOW_1, FrameElement.TOP_BOTTOM_WINDOW_2, FrameElement.TOP_BOTTOM_WINDOW_3);

        int expectedMidRowCount = height - 4;
        int remainingMidRows = expectedMidRowCount;
        for (int i = 0; i < expectedMidRowCount / 2; i++) {
            row++;
            remainingMidRows--;
            super.tiles[0][row] = FrameElement.LEFT_WINDOW_HIGH;
            super.tiles[width - 1][row] = FrameElement.RIGHT_WINDOW_HIGH;
            TileRowFiller.splitElementsEvenly(super.tiles, row, 1, width - 1,
                    FrameElement.MIDDLE_TOP_WINDOW_1, FrameElement.MIDDLE_TOP_WINDOW_2,
                    FrameElement.MIDDLE_TOP_WINDOW_3, FrameElement.MIDDLE_TOP_WINDOW_4);
        }

        while (remainingMidRows > 0) {
            row++;
            remainingMidRows--;
            super.tiles[0][row] = FrameElement.LEFT_WINDOW_LOW;
            super.tiles[width - 1][row] = FrameElement.RIGHT_WINDOW_LOW;
            TileRowFiller.splitElementsEvenly(super.tiles, row, 1, width - 1,
                    FrameElement.MIDDLE_BOTTOM_WINDOW_1, FrameElement.MIDDLE_BOTTOM_WINDOW_2,
                    FrameElement.MIDDLE_BOTTOM_WINDOW_3, FrameElement.MIDDLE_BOTTOM_WINDOW_4);
        }

        row++;
        super.tiles[0][row] = FrameElement.BOTTOM_LEFT_WINDOW;
        super.tiles[1][row] = FrameElement.BOTTOM_WINDOW_1;
        super.tiles[width - 1][row] = FrameElement.BOTTOM_RIGHT_WINDOW;
        super.tiles[width - 2][row] = FrameElement.BOTTOM_WINDOW_4;
        TileRowFiller.splitElementsEvenly(super.tiles, row, 2, width - 2,
                FrameElement.BOTTOM_WINDOW_2, FrameElement.BOTTOM_WINDOW_3);

        row++;
        super.tiles[0][row] = FrameElement.BOTTOM_BOTTOM_LEFT_WINDOW;
        super.tiles[width - 1][row] = FrameElement.BOTTOM_BOTTOM_RIGHT_WINDOW;
        super.tiles[width - 2][row] = FrameElement.BOTTOM_BOTTOM_WINDOW_4;
        TileRowFiller.splitElementsEvenly(super.tiles, row, 1, width - 2,
                FrameElement.BOTTOM_BOTTOM_WINDOW_1, FrameElement.BOTTOM_BOTTOM_WINDOW_2,
                FrameElement.BOTTOM_BOTTOM_WINDOW_3);
    }

    public static FrameLayer createFrame(int sequenceLength, int guessLimit) {
        int width = LEFT_EDGE + VERTICAL_SPACING + sequenceLength + VERTICAL_SPACING + sequenceLength +
                VERTICAL_SPACING + INTERFACE_WIDTH + RIGHT_EDGE;
        int height = TOP_BAR_HEIGHT + HORIZONTAL_SPACING * 2 + guessLimit + HORIZONTAL_SPACING * 2 +
                SOLUTION_HEIGHT + HORIZONTAL_SPACING * 2 + BOTTOM_BAR_HEIGHT;

        return new FrameLayer(width, height);
    }

    public void render(GraphicsContext context) {
        super.render(context);
    }
}