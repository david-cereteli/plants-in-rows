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

package hu.traileddevice.plantsinrows.graphics.layers.popup;

import hu.traileddevice.plantsinrows.graphics.Tile;
import hu.traileddevice.plantsinrows.graphics.TileSet;
import hu.traileddevice.plantsinrows.graphics.layers.background.BackgroundLayer;
import hu.traileddevice.plantsinrows.graphics.layers.background.WrapperElement;
import javafx.scene.canvas.GraphicsContext;

import java.util.HashMap;
import java.util.Map;

public class PopupFrame {
    private static final TileSet windowTiles = new TileSet("tilesets/scrollsandblocks.png",
            544, 320);
    private static final Map<PopupElement, Tile> scrollTileMap = new HashMap<>();
    private static final Map<WrapperElement, Tile> tileMap = new HashMap<>();
    private final BackgroundLayer popupLayer;

    static {
        scrollTileMap.put(PopupElement.TOP_LEFT, new Tile(windowTiles, 11, 1));
        scrollTileMap.put(PopupElement.TOP, new Tile(windowTiles, 12, 1));
        scrollTileMap.put(PopupElement.TOP_RIGHT, new Tile(windowTiles, 13, 1));
        scrollTileMap.put(PopupElement.LEFT, new Tile(windowTiles, 11, 2));
        scrollTileMap.put(PopupElement.FILL, new Tile(windowTiles, 12, 2));
        scrollTileMap.put(PopupElement.RIGHT, new Tile(windowTiles, 13, 2));
        scrollTileMap.put(PopupElement.BOTTOM_LEFT, new Tile(windowTiles, 11, 3));
        scrollTileMap.put(PopupElement.BOTTOM, new Tile(windowTiles, 12, 3));
        scrollTileMap.put(PopupElement.BOTTOM_RIGHT, new Tile(windowTiles, 13, 3));

        tileMap.put(WrapperElement.TOP_LEFT, scrollTileMap.get(PopupElement.TOP_LEFT));
        tileMap.put(WrapperElement.TOP, scrollTileMap.get(PopupElement.TOP));
        tileMap.put(WrapperElement.TOP_RIGHT, scrollTileMap.get(PopupElement.TOP_RIGHT));
        tileMap.put(WrapperElement.LEFT, scrollTileMap.get(PopupElement.LEFT));
        tileMap.put(WrapperElement.FILL, scrollTileMap.get(PopupElement.FILL));
        tileMap.put(WrapperElement.RIGHT, scrollTileMap.get(PopupElement.RIGHT));
        tileMap.put(WrapperElement.BOTTOM_LEFT, scrollTileMap.get(PopupElement.BOTTOM_LEFT));
        tileMap.put(WrapperElement.BOTTOM, scrollTileMap.get(PopupElement.BOTTOM));
        tileMap.put(WrapperElement.BOTTOM_RIGHT, scrollTileMap.get(PopupElement.BOTTOM_RIGHT));
    }

    public PopupFrame(int width, int height) {
        popupLayer = new BackgroundLayer(width, height, 0, 0, tileMap);
    }

    public void render(GraphicsContext context) {
        popupLayer.render(context);
    }
}
