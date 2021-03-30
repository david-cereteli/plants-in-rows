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

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GameSession {
    private static final int DEFAULT_GUESS_LIMIT = 10;
    private static final int DEFAULT_SEQUENCE_LENGTH = 4;
    @Getter private final Symbol[][] guesses;
    @Getter private final Score[][] evaluation;
    @Getter private final Symbol[] solution;
    @Getter private final int SEQUENCE_LENGTH;
    @Getter private final int GUESS_LIMIT;
    @Getter private GameState gameState;
    private int currentRow; // guess count
    private int nextEmptyColumn; // symbol place in guess

    public GameSession(int sequenceLength, int guessLimit) {
        this.SEQUENCE_LENGTH = sequenceLength;
        this.GUESS_LIMIT = guessLimit;
        guesses = new Symbol[sequenceLength][guessLimit]; // x, y
        evaluation = new Score[sequenceLength][guessLimit];
        solution = new Symbol[sequenceLength];
        run();
    }

    public GameSession() {
        this(DEFAULT_SEQUENCE_LENGTH, DEFAULT_GUESS_LIMIT);
    }

    public void reset() {
        for (int i = 0; i < SEQUENCE_LENGTH; i++) {
            Arrays.fill(guesses[i], null);
            Arrays.fill(evaluation[i], null);
        }
        Arrays.fill(solution, null);
        currentRow = 0;
        nextEmptyColumn = 0;
        gameState = null;
        run();
    }

    public void run() {
        gameState = GameState.EMPTY_ROW;
        randomizeSolution();
    }

    private void randomizeSolution() {
        for (int i = 0; i < SEQUENCE_LENGTH; i++) {
            solution[i] = Symbol.getRandom();
        }
    }

    public boolean addSymbolToRow(Symbol symbol) { // boolean indicates success of command
        switch (gameState) {
            case EMPTY_ROW:
                guesses[nextEmptyColumn++][currentRow] = symbol;
                gameState = GameState.IN_PROGRESS_ROW;
                return true;
            case IN_PROGRESS_ROW:
                guesses[nextEmptyColumn++][currentRow] = symbol;
                if (nextEmptyColumn == SEQUENCE_LENGTH) {
                    gameState = GameState.FULL_ROW;
                }
                return true;
            case FULL_ROW:
            case WIN:
            case LOSE:
                return false;
            default:
                throw new IllegalStateException("Invalid game state!");
        }
    }

    public boolean commitGuess() {
        switch (gameState) {
            case FULL_ROW:
                if (evaluateGuess()) {
                    gameState = GameState.WIN;
                    break;
                }
                currentRow++;
                if (currentRow == GUESS_LIMIT) {
                    gameState = GameState.LOSE;
                    break;
                }
                gameState = GameState.EMPTY_ROW;
                nextEmptyColumn = 0;
                break;
            case EMPTY_ROW:
            case IN_PROGRESS_ROW:
            case WIN:
            case LOSE:
                return false;
            default:
                throw new IllegalStateException("Invalid game state!");
        }
        return true;
    }

    public boolean clearGuess() {
        switch (gameState) {
            case IN_PROGRESS_ROW:
            case FULL_ROW:
                for (int i = 0; i < nextEmptyColumn; i++) {
                    guesses[i][currentRow] = null;
                }
                nextEmptyColumn = 0;
                gameState = GameState.EMPTY_ROW;
                return true;
            case EMPTY_ROW:
            case WIN:
            case LOSE:
                return false;
            default:
                throw new IllegalStateException("Invalid game state!");
        }
    }

    private boolean evaluateGuess() {
        Map<Symbol, Integer> remainingSymbols = Arrays.stream(solution)
                .collect(Collectors.toMap(Function.identity(), symbol -> 1, Integer::sum));
        int correctLocations = calculateCorrectLocations(remainingSymbols);
        int correctSymbols = calculateCorrectSymbols(remainingSymbols);
        fillScoreForGuess(correctLocations, correctSymbols);
        return isCurrentGuessCorrect();
    }

    private boolean isCurrentGuessCorrect() {
        return Score.RIGHT_LOCATION.equals(evaluation[SEQUENCE_LENGTH - 1][currentRow]);
    }

    private int calculateCorrectLocations(Map<Symbol, Integer> remainingSymbols) {
        int correctLocations = 0;
        for (int i = 0; i < SEQUENCE_LENGTH; i++) {
            if (guesses[i][currentRow].equals(solution[i])) {
                correctLocations++;
                remainingSymbols.merge(guesses[i][currentRow], -1, (acc, val) -> (acc + val == 0 ? null : acc + val));
            }
        }
        return correctLocations;
    }

    private int calculateCorrectSymbols(Map<Symbol, Integer> remainingSymbols) {
        int correctSymbols = 0;
        for (int i = 0; i < SEQUENCE_LENGTH; i++) {
            if (!guesses[i][currentRow].equals(solution[i]) && remainingSymbols.containsKey(guesses[i][currentRow])) {
                correctSymbols++;
                remainingSymbols.merge(guesses[i][currentRow], -1, (acc, val) -> (acc + val == 0 ? null : acc + val));
            }
        }
        return correctSymbols;
    }

    private void fillScoreForGuess(int correctLocations, int correctSymbols) {
        int evaluationIndex = 0;
        while ((correctLocations > 0) || (correctSymbols > 0)) {
            if (correctLocations > 0) {
                evaluation[evaluationIndex++][currentRow] = Score.RIGHT_LOCATION;
                correctLocations--;
            } else {
                evaluation[evaluationIndex++][currentRow] = Score.RIGHT_SYMBOL_WRONG_LOCATION;
                correctSymbols--;
            }
        }
    }
}
