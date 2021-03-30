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

package hu.traileddevice.plantsinrows.ui.components.text;

import hu.traileddevice.plantsinrows.Main;
import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Border;

import static hu.traileddevice.plantsinrows.ui.components.text.Fonts.REGULAR;

public class BorderlessLink extends Hyperlink {
    private static final int TOP_PADDING = 2;
    private static final int BOTTOM_PADDING = 2;

    public BorderlessLink(String displayedText, String destinationUrl) {
        super(displayedText);
        this.setBorder(Border.EMPTY);
        this.setPadding(new Insets(TOP_PADDING, 0, BOTTOM_PADDING, 0));
        this.setFont(REGULAR);
        this.setStyle("-fx-text-fill: black;");
        this.setOnAction(e -> {
            Main.getServices().showDocument(destinationUrl);
        });
    }
}
