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

public enum BackgroundElement {
    TOP_LEFT_OUTER,
    TOP_OUTER,
    TOP_RIGHT_OUTER,
    LEFT_OUTER,
    MAIN_BG_FILL,
    MAIN_BG_FILL_SPEC_1,
    MAIN_BG_FILL_SPEC_2,
    MAIN_BG_FILL_SPEC_3,
    RIGHT_OUTER,
    BOTTOM_LEFT_OUTER,
    BOTTOM_OUTER,
    BOTTOM_RIGHT_OUTER,

    TOP_LEFT_INNER,
    TOP_INNER,
    TOP_RIGHT_INNER,
    LEFT_INNER,
    GUESS_FILL,
    GUESS_FILL_SPEC_1,
    GUESS_FILL_SPEC_2,
    GUESS_FILL_SPEC_3,
    RIGHT_INNER,
    BOTTOM_LEFT_INNER,
    BOTTOM_INNER,
    BOTTOM_RIGHT_INNER;
}
