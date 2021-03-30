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

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomHelper {
    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    public static <T> T pickRandom(List<T> elements) {
        return elements.get(random.nextInt(elements.size()));
    }

    public static int getRandomNumberBetweenBounds(int low, int high) {
        return random.nextInt(low, high + 1);
    }

    public static boolean isEventOccurring(int chance) {
        int randomChance = getRandomNumberBetweenBounds(1, 100);
        return randomChance <= chance;
    }
}
