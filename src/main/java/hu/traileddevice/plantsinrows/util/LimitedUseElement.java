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

package hu.traileddevice.plantsinrows.util;

import lombok.Getter;

public class LimitedUseElement<T> {
    @Getter private int usesLeft;
    private final T element;

    public LimitedUseElement(int uses, T element) {
        this.usesLeft = uses;
        this.element = element;
    }

    public void addUse() {
        usesLeft++;
    }

    public T use() {
        if (usesLeft > 0) {
            usesLeft--;
            return element;
        }
        throw new IllegalStateException("Out of elements!");
    }
}
