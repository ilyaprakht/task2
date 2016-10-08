package model;


import model.enums.StepResult;
import model.enums.StepType;

class GameField {

    private byte size;
    private byte winnerCount;
    private StepType[][] gameField;

    GameField(byte size, byte winnerCount) {
        this.size = size;
        this.winnerCount = winnerCount;
        gameField = new StepType[this.size][this.size];
    }

    StepResult enterStep(Field field) {
        if (!checkValidStep(field)) {
            return StepResult.NOT_VALID_FIELD;
        }

        if (!checkNotBusyField(field)) {
            return StepResult.BUSY_FIELD;
        }

        gameField[field.getPosX()-1][field.getPosY()-1] = field.getStepType();

        if (checkWinnerStep(field)) {
            return StepResult.WINNER_STEP;
        }

        if (checkEndOfGame()) {
            return StepResult.END_OF_GAME;
        }

        return StepResult.ENTER_OK;
    }

    private boolean checkValidStep(Field field) {
        return (field.getPosX() >= 0 && field.getPosX() <= size
                && field.getPosY() >= 0 && field.getPosY() <= size);
    }

    private boolean checkNotBusyField(Field field) {
        return (gameField[field.getPosX()-1][field.getPosY()-1] == null);
    }

    private boolean checkWinnerStep(Field field) {
        return (checkWinnerByRow(field)
                || checkWinnerByColumn(field)
                || checkWinnerByDiagonal(field));
    }

    private boolean checkWinnerByRow(Field field) {
        byte countMatches = 1;

        for (int i = field.getPosY()-2; i >= 0; i--) {
            if (gameField[field.getPosX()-1][i] == field.getStepType()) {
                countMatches++;
            } else {
                break;
            }
        }

        for (int i = field.getPosY(); i < size; i++) {
            if (gameField[field.getPosX()-1][i] == field.getStepType()) {
                countMatches++;
            } else {
                break;
            }
        }

        return (countMatches >= winnerCount);
    }

    private boolean checkWinnerByColumn(Field field) {
        byte countMatches = 1;

        for (int i = field.getPosX()-2; i >= 0; i--) {
            if (gameField[i][field.getPosY()-1] == field.getStepType()) {
                countMatches++;
            } else {
                break;
            }
        }

        for (int i = field.getPosX(); i < size; i++) {
            if (gameField[i][field.getPosY()-1] == field.getStepType()) {
                countMatches++;
            } else {
                break;
            }
        }

        return (countMatches >= winnerCount);
    }

    private boolean checkWinnerByDiagonal(Field field) {
        byte countMatches = 1;

        for (int i = 2; i < size; i++) {
            if ((field.getPosX()-i >= 0 && field.getPosY()-i >= 0)
                    && (gameField[field.getPosX()-i][field.getPosY()-i] == field.getStepType())) {
                countMatches++;
            } else {
                break;
            }
        }

        for (int i = 1; i < size; i++) {
            if ((field.getPosX()+i < size && field.getPosY()+i < size)
                    && (gameField[field.getPosX()+i][field.getPosY()+i] == field.getStepType())) {
                countMatches++;
            } else {
                break;
            }
        }

        if (countMatches >= winnerCount) {
            return true;
        }

        countMatches = 1;

        for (int i = 1; i < size; i++) {
            if ((field.getPosX()-i-1 >= 0 && field.getPosY()+i < size)
                    && (gameField[field.getPosX()-i-1][field.getPosY()+i] == field.getStepType())) {
                countMatches++;
            } else {
                break;
            }
        }

        for (int i = 1; i < size; i++) {
            if ((field.getPosX()+i < size && field.getPosY()-i-1 >= 0)
                    && (gameField[field.getPosX()+i][field.getPosY()-i-1] == field.getStepType())) {
                countMatches++;
            } else {
                break;
            }
        }

        return (countMatches >= winnerCount);
    }

    private boolean checkEndOfGame() {
        for(StepType[] row : gameField) {
            for (StepType cell : row) {
                if (cell == null) {
                    return false;
                }
            }
        }
        return true;
    }

    String getVisualGameField() {
        StringBuilder builder = new StringBuilder(size * size + size);
        for(StepType[] row : gameField) {
            for (StepType cell : row) {
                builder.append(cell == null ? " " : cell.getValue());
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
