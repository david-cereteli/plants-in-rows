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
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import lombok.Getter;

public class TileSet {
    @Getter private Image image;

    public TileSet(String tilesetSource, int tileWidth, int tileHeight) {
        this.image = new Image(tilesetSource, tileWidth, tileHeight, true, false);
    }

    /*
     * Inspired by: https://stackoverflow.com/a/61882120
     */
    public void changeColor(Color newColor) {
        double redComponent = newColor.getRed();
        double greenComponent = newColor.getGreen();
        double blueComponent = newColor.getBlue();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PixelReader reader = image.getPixelReader();
        WritableImage coloredImage = new WritableImage(width, height);
        PixelWriter writer = coloredImage.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                writer.setColor(
                        x,
                        y,
                        new Color(
                                redComponent,
                                greenComponent,
                                blueComponent,
                                reader.getColor(x, y).getOpacity() // Retain transparent pixels
                        )
                );
            }
        }
        image = coloredImage;
    }

    public void changeColorOfPart(Color newColor, int posX, int posY) {
        double redComponent = newColor.getRed();
        double greenComponent = newColor.getGreen();
        double blueComponent = newColor.getBlue();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PixelReader reader = image.getPixelReader();
        WritableImage coloredImage = new WritableImage(width, height);
        PixelWriter writer = coloredImage.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x >= posX && x < posX + Main.getTILE_WIDTH() && y >= posY && y < Main.getTILE_WIDTH()) {
                    writer.setColor(
                            x,
                            y,
                            new Color(
                                    redComponent,
                                    greenComponent,
                                    blueComponent,
                                    reader.getColor(x, y).getOpacity()
                            )
                    );
                } else {
                    writer.setColor(x, y, reader.getColor(x, y));
                }
            }
        }
        image = coloredImage;
    }

    public void invert() {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PixelReader reader = image.getPixelReader();
        WritableImage invertedImage = new WritableImage(width, height);
        PixelWriter writer = invertedImage.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color originalColor = reader.getColor(x, y);
                writer.setColor(
                        x,
                        y,
                        originalColor.invert()
                );
            }
        }
        image = invertedImage;
    }

    // swap transparent and colored parts
    public void invertFrom(Color originalColor) { // add in the non-transparent color
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PixelReader reader = image.getPixelReader();
        WritableImage invertedImage = new WritableImage(width, height);
        PixelWriter writer = invertedImage.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color readColor = reader.getColor(x, y);
                if (readColor.equals(originalColor)) {
                    writer.setColor(
                            x,
                            y,
                            new Color(0, 0, 0, 0) // transparent
                    );
                } else {
                    writer.setColor(x, y, originalColor);
                }

            }
        }
        image = invertedImage;
    }
}
