package com.allthing.day16;

import java.util.ArrayList;
import java.util.List;

public enum GridSymbol {
    FORWARDSLASH {
        @Override
        public List<GridDirection> getDirections(GridDirection inboundDirection) {
            return List.of(
                    switch (inboundDirection) {
                        case UP -> GridDirection.RIGHT;
                        case RIGHT -> GridDirection.UP;
                        case LEFT -> GridDirection.DOWN;
                        case DOWN -> GridDirection.LEFT;
                    }
            );
        }
    },
    BACKSLASH {
        @Override
        public List<GridDirection> getDirections(GridDirection inboundDirection) {
            return List.of(
                    switch (inboundDirection) {
                        case UP -> GridDirection.LEFT;
                        case LEFT -> GridDirection.UP;
                        case RIGHT -> GridDirection.DOWN;
                        case DOWN -> GridDirection.RIGHT;
                    }
            );
        }
    },
    HYPHEN {
        @Override
        public List<GridDirection> getDirections(GridDirection inboundDirection) {
            List<GridDirection> directions = new ArrayList<>();
            switch (inboundDirection){
                case UP, DOWN-> {
                    directions.add(GridDirection.LEFT);
                    directions.add(GridDirection.RIGHT);
                }
                case RIGHT -> directions.add(GridDirection.RIGHT);
                case LEFT -> directions.add(GridDirection.LEFT);
            }
            return directions;
        }
    },
    PIPE{
        @Override
        public List<GridDirection> getDirections(GridDirection inboundDirection) {
            List<GridDirection> directions = new ArrayList<>();
            switch (inboundDirection){
                case LEFT, RIGHT-> {
                    directions.add(GridDirection.UP);
                    directions.add(GridDirection.DOWN);
                }
                case UP -> directions.add(GridDirection.UP);
                case DOWN -> directions.add(GridDirection.DOWN);
            }
            return directions;
        }
    },
    PERIOD{
        @Override
        public List<GridDirection> getDirections(GridDirection inboundDirection) {
            return List.of(inboundDirection);
        }
    };
    
    public abstract List<GridDirection> getDirections(GridDirection inboundDirection);
}
