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

import java.util.ArrayList;
import java.util.List;

public class TileRowFiller {

    @SafeVarargs
    public static <E> void includeExtraElementsWithChance(E[][] tiles, int row, int start, int end, int chance,
                                                          E... elementsToUse) {
        List<E> extraElements = List.of(elementsToUse).subList(1, elementsToUse.length);
        for (int i = start; i < end; i++) {
            if (RandomHelper.isEventOccurring(chance)) {
                tiles[i][row] = RandomHelper.pickRandom(extraElements);
            } else {
                tiles[i][row] = elementsToUse[0];
            }
        }
    }

    @SafeVarargs
    public static <E> void splitElementsEvenly(E[][] tiles, int row, int start, int end, E... elementsToUse) {
        int availableTilePlaces = end - start;
        List<LimitedUseElement<E>> availableElements = createEqualAmountElements(availableTilePlaces, elementsToUse);
        addElementsForRemainingPlaces(availableTilePlaces, availableElements, elementsToUse);
        fillRow(tiles, row, start, end, availableElements);
    }

    private static <E> List<LimitedUseElement<E>> createEqualAmountElements(int availableTilePlaces, E[] elementsToUse) {
        int tileUseCount = availableTilePlaces / elementsToUse.length;
        List<LimitedUseElement<E>> availableElements = new ArrayList<>();
        for (E tileElement : elementsToUse) {
            availableElements.add(new LimitedUseElement<>(tileUseCount, tileElement));
        }
        return availableElements;
    }

    private static <E> void addElementsForRemainingPlaces(int availableTilePlaces,
                                                          List<LimitedUseElement<E>> availableElements, E[] elementsToUse) {
        int remainingPlaces = availableTilePlaces % elementsToUse.length;
        int distributionIndex = 0;
        boolean placeToEnd = true;
        while (remainingPlaces > 0) {
            if (placeToEnd) {
                availableElements.get(availableElements.size() - (distributionIndex + 1)).addUse();
            } else {
                availableElements.get(distributionIndex).addUse();
                distributionIndex++;
            }
            placeToEnd = !placeToEnd;
            remainingPlaces--;
        }
    }

    private static <E> void fillRow(E[][] tiles, int row, int start, int end, List<LimitedUseElement<E>> availableElements) {
        int tileIndex = 0;
        for (int column = start; column < end; column++) {
            if (availableElements.get(tileIndex).getUsesLeft() == 0) {
                tileIndex++;
            }
            tiles[column][row] = availableElements.get(tileIndex).use();
        }
    }
}
