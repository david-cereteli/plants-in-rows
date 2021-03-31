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

package hu.traileddevice.plantsinrows.logic;

public enum Difficulty {
    EASY(4, 10),
    NORMAL(4, 8),
    HARD(4,6);

    private final int SEQUENCE_LENGTH;
    private final int GUESS_LIMIT;

    Difficulty(int sequenceLength, int guessLimit) {
        this.SEQUENCE_LENGTH = sequenceLength;
        this.GUESS_LIMIT = guessLimit;
    }

    public int getSEQUENCE_LENGTH() {
        return SEQUENCE_LENGTH;
    }

    public int getGUESS_LIMIT() {
        return GUESS_LIMIT;
    }

    public static Difficulty getEnum(String enumStr) {
        return Difficulty.valueOf(enumStr.toUpperCase());
    }
}
