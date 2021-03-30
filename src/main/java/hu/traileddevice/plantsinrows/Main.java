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

package hu.traileddevice.plantsinrows;

import hu.traileddevice.plantsinrows.graphics.TileSet;
import hu.traileddevice.plantsinrows.graphics.layers.background.BackgroundZones;
import hu.traileddevice.plantsinrows.graphics.layers.frame.FrameLayer;
import hu.traileddevice.plantsinrows.graphics.layers.game.GameZones;
import hu.traileddevice.plantsinrows.logic.GameSession;
import hu.traileddevice.plantsinrows.ui.MainInterface;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;

public class Main extends Application {
    @Getter private static TileSet icons;
    @Getter private static Image gameIcon;
    @Getter private static final int TILE_WIDTH = 32;
    @Getter private static final int VISUAL_CORRECTION_FOR_SCROLL_TOP = TILE_WIDTH / 3;
    @Getter private Stage primaryStage;
    @Getter private StackPane stackPane;
    private GraphicsContext context;
    private FrameLayer frameLayer;
    private BackgroundZones backgroundZones;
    private GameZones gameZones;
    @Getter private GameSession gameSession;
    @Getter private static HostServices services;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        services = getHostServices();

        this.primaryStage = primaryStage;
        gameIcon = new Image("/tilesets/farmer.png");
        icons = new TileSet("/tilesets/icons.png", 352, 32);

        gameSession = new GameSession();
        int gameRowCount = gameSession.getGUESS_LIMIT();
        int gameColumnCount = gameSession.getSEQUENCE_LENGTH();

        frameLayer = FrameLayer.createFrame(gameColumnCount, gameRowCount);
        backgroundZones = new BackgroundZones(gameColumnCount, gameRowCount, 0, FrameLayer.getTOP_BAR_HEIGHT());
        gameZones = new GameZones(gameSession);

        Canvas canvas = new Canvas(frameLayer.getWidth() * TILE_WIDTH, frameLayer.getHeight() * TILE_WIDTH);
        context = canvas.getGraphicsContext2D();

        stackPane = new StackPane();
        stackPane.getChildren().add(canvas);
        stackPane.setStyle("-fx-background-color: transparent;");
        stackPane.setAlignment(Pos.TOP_RIGHT);
        Scene scene = new Scene(stackPane);

        MainInterface mainInterface = new MainInterface(this, TILE_WIDTH);

        primaryStage.setScene(scene);
        refresh();

        primaryStage.setTitle("Plants in Rows");
        primaryStage.getIcons().add(gameIcon);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(null);
        primaryStage.show();
    }

    public void refresh() {
        frameLayer.render(context);
        backgroundZones.render(context);
        gameZones.render(context);
    }
}
