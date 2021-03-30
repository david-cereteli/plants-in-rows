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

package hu.traileddevice.plantsinrows.ui;

import hu.traileddevice.plantsinrows.ui.components.text.BorderlessLink;
import hu.traileddevice.plantsinrows.ui.components.text.CenteredText;
import hu.traileddevice.plantsinrows.ui.components.text.EmptyLine;
import hu.traileddevice.plantsinrows.ui.components.text.TitleText;
import hu.traileddevice.plantsinrows.ui.stage.ScrollStage;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static hu.traileddevice.plantsinrows.ui.components.text.Fonts.BOLD;
import static hu.traileddevice.plantsinrows.ui.components.text.Fonts.REGULAR;

public class AboutWindow {
    private final Stage primaryStage;
    private static final int WIDTH = 13;
    private static final int HEIGHT = 18;
    private static final int BOTTOM_MARGIN = 40;
    private static final int NAME = 0;
    private static final int URL = 1;
    private static final String copyrightText = "Plants in Rows";
    private static final String copyrightDate = "Copyright© 2021 David Cereteli";
    private static final String attributionTitle = "Attribution for art assets used";
    private static final String tilesTitle = "Tiles created by:";
    private static final String commissionTitle = "Some tiles commissioned by:";
    private static final String[][] authorsData = {
            {"bluecarrot16", "https://opengameart.org/users/bluecarrot16"},
            {"Daniel Eddeland (daneeklu)", "https://opengameart.org/content/lpc-farming-tilesets-magic-animations-and-ui-elements"},
            {"Delapouite", "https://delapouite.com/"},
            {"Joshua Taylor", "https://opengameart.org/content/fruit-and-veggie-inventory"},
            {"Lanea Zimmerman (Sharm)", "https://lpc.opengameart.org/static/lpc-style-guide/authors.html#lanea-zimmerman-aka-sharm"},
            {"Lorc", "https://lorcblog.blogspot.com/"},
            {"Richard Kettering (Jetrel)", "https://opengameart.org/content/rpg-item-set"}
    };
    private static final String commissioner = "castelonia";
    private static final String commissionerUrl = "https://opengameart.org/users/castelonia";
    private static final String licenseInfo = "Please find the detailed license information";
    private static final String sourceInfo = "and the source code on GitHub:";
    private static final String[] repoData = {"Plants in Rows repository", "https://github.com/david-cereteli/plants-in-rows#License"};

    public AboutWindow(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void display() {
        ScrollStage scrollWindow = new ScrollStage(WIDTH, HEIGHT, primaryStage);
        addContent(scrollWindow);
        scrollWindow.addDragBar();
        scrollWindow.addCloseButton("Back to game");
        scrollWindow.showAndWait();
    }

    private void addContent(ScrollStage scrollWindow) {
        StackPane layout = scrollWindow.getStackPane();

        BorderlessLink repoLink = new BorderlessLink(repoData[NAME], repoData[URL]);

        VBox authorLinks = new VBox();
        ObservableList<Node> authorLinksChildren = authorLinks.getChildren();
        for (String[] authorData : authorsData) {
            authorLinksChildren.add(new BorderlessLink(authorData[NAME], authorData[URL]));
        }
        authorLinks.setAlignment(Pos.CENTER);

        VBox aboutTextContainer = new VBox();
        aboutTextContainer.getChildren().addAll(
                new TitleText(attributionTitle, BOLD),
                new TitleText(tilesTitle, BOLD),
                authorLinks,
                new TitleText(commissionTitle, BOLD),
                new BorderlessLink(commissioner, commissionerUrl),
                new EmptyLine(),
                new CenteredText(licenseInfo, BOLD),
                new CenteredText(sourceInfo, BOLD),
                repoLink,
                new EmptyLine(),
                new CenteredText(copyrightText, REGULAR),
                new CenteredText(copyrightDate, REGULAR)
        );
        aboutTextContainer.setAlignment(Pos.CENTER);

        layout.getChildren().add(aboutTextContainer);
        StackPane.setAlignment(aboutTextContainer, Pos.CENTER);
        StackPane.setMargin(aboutTextContainer, new Insets(0, 0, BOTTOM_MARGIN, 0));
    }
}
