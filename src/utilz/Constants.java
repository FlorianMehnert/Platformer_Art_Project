package utilz;

import main.Game;

import static utilz.HelpMethods.*;

import java.awt.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Constants {
    public static final int UPS_SET = 200;
    public static final float GRAVITY = 0.08f * Game.SCALE;
    public static final int ANI_SPEED = 13;

    public static class ObjectConstants {

        public static final int EXPLOSION = 0;
        public static final int SPIKE = 4;

        public static final int ROCKET_EXPLOSION_WIDTH = (int) (200 * Game.SCALE);
        public static final int ROCKET_EXPLOSION_HEIGHT = (int) (200 * Game.SCALE);
        public static final int WINDMILL_EXPLOSION_WIDTH = (int) (100 * Game.SCALE);
        public static final int WINDMILL_EXPLOSION_HEIGHT = (int) (100 * Game.SCALE);
        public static final float MAX_DISTANCE_FOR_FOLOWUP_EXPLOSION = 4.0f * Game.TILES_SIZE / 4.0f;

        public static int GetSpriteAmount(int object_type) {
            if (object_type == EXPLOSION) {
                return 12;
            }
            return 1;
        }
    }

    public static class EnemyConstants {
        public static final int TUMBLE_WEED = 0;

        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int HIT = 2;
        public static final int DEAD = 3;

        public static final int TUMBLE_WEED_WIDTH_DEFAULT = 72;
        public static final int TUMBLE_WEED_HEIGHT_DEFAULT = 72;
        public static final int TUMBLE_WEED_WIDTH = (int) (TUMBLE_WEED_WIDTH_DEFAULT * Game.SCALE);
        public static final int TUMBLE_WEED_HEIGHT = (int) (TUMBLE_WEED_HEIGHT_DEFAULT * Game.SCALE);
        public static final int TUMBLE_WEED_HITBOX_WIDTH_DEFAULT = 24;
        public static final int TUMBLE_WEED_HITBOX_HEIGHT_DEFAULT = 24;
        public static final int TUMBLE_WEED_HITBOX_WIDTH = (int) (TUMBLE_WEED_HITBOX_WIDTH_DEFAULT * Game.SCALE);
        public static final int TUMBLE_WEED_HITBOX_HEIGHT = (int) (TUMBLE_WEED_HITBOX_HEIGHT_DEFAULT * Game.SCALE);

        public static final int TUMBLE_WEED_DRAWOFFSET_X = (TUMBLE_WEED_WIDTH - TUMBLE_WEED_HITBOX_WIDTH) / 2;
        public static final int TUMBLE_WEED_DRAWOFFSET_Y = (TUMBLE_WEED_HEIGHT - TUMBLE_WEED_HITBOX_HEIGHT) / 2;
        public static final int TUMBLE_WEED_NUM_ANIMATIONS = 5;
        public static final int TUMBLE_WEED_MAX_ANIMATION_LENGTH = 10;
        public static final float TUMBLE_WEED_MAX_SPEED = Game.SCALE;
        public static final float TUMBLE_WEED_TIME_TO_REACH_WIND_SPEED = 5.0f;
        public static final int TUMBLE_WEED_MAX_ANI_SPEED = (int) (0.5 * ANI_SPEED);
        public static final int TUMBLE_WEED_MIN_ANI_SPEED = 3 * ANI_SPEED;

        public static int GetSpriteAmount(int enemy_type, int enemy_state) {
            switch (enemy_state) {
                case IDLE: {
                    if (enemy_type == TUMBLE_WEED)
                        return 1;
                }
                case RUNNING:
                    return 10;
                case HIT:
                    return 10;
                case DEAD:
                    return 4;
            }
            return 0;
        }

        public static int GetMaxHealth(int enemy_type) {
            if (enemy_type == TUMBLE_WEED) {
                return 20;
            }
            return 1;
        }

        public static int GetEnemyDmg(int enemy_type) {
            if (enemy_type == TUMBLE_WEED) {
                return 10;
            }
            return 0;
        }
    }

    public static class Environment {
        // temperature
        public static final float MAX_TEMP = 100.0f;
        public static final float TIME_TO_REACH_MAX_TEMP = 10 * 60;//15.0f * 60;
        public static final float TEMP_FROM_FINISHED_WINDMILL = -30.0f;
        public static final float TEMP_FROM_ROCKET_EXPLOSION = 5.0f;
        public static final float TEMP_FROM_WINDMILL_EXPLOSION = 2.0f;

        // wind
        public static final float WEAK_WIND_TH = 0.25f * Game.SCALE;
        public static final float STRONG_WIND_TH = 0.75f * Game.SCALE;
        public static final float MAX_WIND_SPEED_START = 0.5f * Game.SCALE;
        public static final float MAX_WIND_SPEED_END = 1.5f * Game.SCALE;
        public static final float TIME_BETWEEN_WIND_CHANGE_START = 60;
        public static final float TIME_BETWEEN_WIND_CHANGE_END = 5;
        public static final Color FLOOR_TILE_COLOR = new Color(40, 40, 45, 150);

        // darkness
        public static final float DARKNESS_START_ALPHA = 0f;//-0.15f;
        public static final float DARKNESS_END_ALPHA = 150f;//0.10f;
        public static final float DARKNESS_CHANGE_SPEED = 0.05f * Game.SCALE;

        // clouds
        public static final float CLOUD_START_OFFSET_FACTOR = 0.05f;//-0.15f;
        public static final float CLOUD_END_OFFSET_FACTOR = 0.05f;//0.10f;
        public static final float CLOUD_MOVE_SPEED = 0.05f * Game.SCALE;

        // water
        public static final float WATER_START_OFFSET_FACTOR = 1.0f;
        public static final float WATER_END_OFFSET_FACTOR = 0.83f;
        public static final float WATER_MOVE_SPEED = 0.05f * Game.SCALE;
        public static final float WATER_DMG_PER_SECOND = 10.0f;
        public static final float WATER_PLAYER_SLOW_FACTOR = 0.5f;
        public static final float WATER_PLAYER_JUMP_SLOW_FACTOR = 0.75f;
        public static final int WATER_HEIGHT = 1000;

        // layer speed
        public static final float SKY_SPEED = 0.5f;
        public static final float BG1_SPEED = 0.6f;
        public static final float C1_SPEED = 0.7f;
        public static final float C2_SPEED = 0.8f;
        public static final float BG2_SPEED = 0.9f;
    }

    public static class UI {
        public static final Color BASE_GREY = new Color(131, 131, 131);
        public static final Color BACKGROUND_GREY = new Color(60, 60, 60);

        public static class Buttons {
            public static final int B_WIDTH_DEFAULT = 768;
            public static final int B_HEIGHT_DEFAULT = 256;
            public static final int B_WIDTH = (int) (180 * Game.SCALE);
            public static final int B_HEIGHT = (int) (60 * Game.SCALE);
        }

        public static class PauseButtons {
            public static final int SOUND_SIZE_DEFAULT = 42;
        }

        public static class VolumeButtons {
            public static final int VOLUME_DEFAULT_WIDTH = 28;
            public static final int VOLUME_DEFAULT_HEIGHT = 44;
            public static final int SLIDER_DEFAULT_WIDTH = 215;

            public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
            public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
            public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);
        }
    }

    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants {
        public static final float PLAYER_WALKSPEED = Game.SCALE * 2.0f;
        public static final float PLAYER_JUMP_SPEED = -2.25f * Game.SCALE;
        public static final float COYOTE_TIME = 0.25f;


        public static final float CLOSE_TO_BORDER_HORIZONTAL = 0.6f;
        public static final float CLOSE_TO_BORDER_VERTICAL = 0.6f;
        public static final float MAX_X_LVL_OFFSET_STEP_HORIZONTAL = 0.004f * Game.GAME_WIDTH;
        public static final int MAX_ALLOWED_JUMPS = 3;

        public static final int PLAYER_GREEN_VALUE = 100;
        public static final int HITBOX_BASE_WIDTH = 16;
        public static final int GRABBOX_BASE_WIDTH = (int) (HITBOX_BASE_WIDTH * 1.75f);
        public static final int ATTACKBOX_BASE_WIDTH = (int) (HITBOX_BASE_WIDTH * 1.75f);
        public static final int HITBOX_BASE_HEIGHT = 27;
        public static final int GRABBOX_BASE_HEIGHT = (int) (HITBOX_BASE_HEIGHT * 1.25f);
        public static final int ATTACKBOX_BASE_HEIGHT = (int) (HITBOX_BASE_HEIGHT * 1.25f);

        public static final int PLAYER_BASE_HEIGHT = 64;
        public static final int PLAYER_BASE_WIDTH = 64;

        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2;
        public static final int FALLING = 3;
        public static final int ATTACK = 4;
        public static final int HIT = 5;
        public static final int DEAD = 6;
        public static final int THROW = 7;
        public static final int FASTFALLING = 8;

        public static final int NUM_ANIMATIONS = 9;
        public static final int MAX_ANIMATION_LENGTH = 12;

        public static int GetSpriteAmount(int player_action) {
            return switch (player_action) {
                case DEAD -> 4;
                case RUNNING -> 12;
                case IDLE -> 1;
                case HIT -> 6;
                case JUMP -> 5;
                case ATTACK -> 5;
                case FALLING -> 4;
                case FASTFALLING -> 4;
                case THROW -> 4;
                default -> 1;
            };
        }

        public static final Color PLAYER_DEFAULT_COLOR = new Color(21, 19, 26); // BLACK
        public static final int PLAYER_COLOR_TOLERANCE = 8; // BLACK

        public static final Map<Color, Color> COLOR_MAP;

        static {
            Map<Color, Color> map = new HashMap<>();
            map.put(new Color(233, 38, 28), new Color(252, 191, 43)); // A
            map.put(new Color(171, 40, 28), new Color(215, 150, 33)); // B
            map.put(new Color(97, 39, 24), new Color(153, 104, 20)); // C
            map.put(new Color(225, 85, 39), new Color(248, 214, 126)); // D
            map.put(new Color(22, 22, 28), new Color(22, 22, 28)); // BLACK
            map.put(new Color(14, 0, 12), new Color(14, 0, 12)); // BLACK

            COLOR_MAP = Collections.unmodifiableMap(map);
        }
    }

    public static class TetrisTileConstants {
        public static final Color THROW_ARC_COLOR_PLAYER1 = new Color(233, 38, 28, 100);
        public static final Color THROW_ARC_COLOR_PLAYER2 = new Color(252, 191, 43, 100);
        public static final int TETRIS_TILE_GREEN_VALUE = 255;

        public static final int TETRIS_TILE_WIDTH_DEFAULT = 32;
        public static final int TETRIS_TILE_HEIGHT_DEFAULT = 32;
        public static final int TETRIS_TILE_WIDTH = (int) (TETRIS_TILE_WIDTH_DEFAULT * Game.SCALE);
        public static final int TETRIS_TILE_HEIGHT = (int) (TETRIS_TILE_HEIGHT_DEFAULT * Game.SCALE);
        public static final int NUM_TETRIS_TILES = 12;

        public static final int LONG_I_TILE = 0;
        public static final int L_TILE = 1;
        public static final int J_TILE = 2;
        public static final int STAIRS_TILE = 3;
        public static final int DOUBLE_TILE = 4;
        public static final int O_TILE = 5;
        public static final int Q_TILE = 6;
        public static final int SINGLE_TILE = 7;
        public static final int LONG_T_TILE = 8;
        public static final int LONG_Z_TILE = 9;
        public static final int LONG_S_TILE = 10;
        public static final int I_TILE = 11;
        public static final float[] TETRIS_TILE_PROBS = {
                0.10f,
                0.05f,
                0.05f,
                0.15f,
                0.15f,
                0.025f,
                0.025f,
                0.20f,
                0.10f,
                0.025f,
                0.025f,
                0.10f};

        public static int GetRandomTetrisTileIndex(Random random) {
            double r = random.nextFloat();
            double cumulativeProbability = 0.0;
            float probSum = 0.0f;

            for (int i = 0; i < NUM_TETRIS_TILES; i++) {
                probSum += TETRIS_TILE_PROBS[i];
            }
            if (probSum < 0.999 || probSum > 1.001) {
                System.out.println("probability of tetris tiles does not add up to 1.0");
                System.out.println(probSum);
            }

            for (int i = 0; i < NUM_TETRIS_TILES; i++) {
                cumulativeProbability += TETRIS_TILE_PROBS[i];
                if (r <= cumulativeProbability) {
                    return i;
                }
            }
            return NUM_TETRIS_TILES - 1; // fallback
        }

        public static final int FINAL_PREDICTION_POINT = -1;
        public static final float THROW_ARC_PREDICTION_TIME = 1.5f;
        public static final int NUM_THROW_ARC_PREDICTION_POINTS = 30;
        public static final float TIME_BETWEEN_THROW_CHANGES = 0.06f;
        public static final float TIME_FOR_FIRST_THROW_ARC_CHANGE = 0.15f;
        public static final int TETRIS_TILE_MAX_THROW_HEIGHT_IN_SMALL_TILES = 6 * 4;
        public static final int TETRIS_TILE_MAX_THROW_WIDTH_IN_SMALL_TILES = 6 * 4;
        public static final float TETRIS_TILE_MAX_THROW_SPEED = (float) Math.sqrt(TETRIS_TILE_MAX_THROW_HEIGHT_IN_SMALL_TILES * Game.TILES_SIZE * 2 * GRAVITY);

        public static final float TETRIS_TILE_MIN_EXPLOSION_X_SPEED = TETRIS_TILE_MAX_THROW_SPEED * 0.1f;
        public static final float TETRIS_TILE_MAX_EXPLOSION_X_SPEED = TETRIS_TILE_MAX_THROW_SPEED * 0.5f;
        public static final float TETRIS_TILE_MIN_EXPLOSION_Y_SPEED = TETRIS_TILE_MAX_THROW_SPEED * 0.75f;
        public static final float TETRIS_TILE_MAX_EXPLOSION_Y_SPEED = TETRIS_TILE_MAX_THROW_SPEED * 1.5f;
        public static final float TETRIS_TILE_TIME_TO_EXPLODE = 1f;

        public static final float TETRIS_TILE_TIME_TO_REACH_WINDSPEED = 1.0f;
        public static final float TETRIS_TILE_TIME_TO_STOP_WHEN_IS_ON_FLOOR = 0.1f;
        public static final int TETRIS_GRID_SIZE = Game.TILES_SIZE / 4;

        public enum Direction {
            LEFT, RIGHT
        }

        public enum DashState {
            NOTHING, ACTIVATE1, RELEASE1, ACTIVATE2, DASHING
        }

        public enum PlayerState {
            IDLE, RUNNING, JUMP, FALLING, ATTACK, HIT, DEAD, THROW, FASTFALLING
        }

        public static final int[][] ROCKET_GOAL_MATRIX = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},

                {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},

                {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0},

                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0},

                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0},

                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        public static final int[][] ROCKET_TUTORIAL_PRE_MATRIX = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},

                {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},

                {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0},

                {0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0},

                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0},

                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };

        public static final int[][] ROCKET_PRE_MATRIX = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };

        public static final int[][] WINDMILL_GOAL_MATRIX = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},

                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},

                {0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0},
                {0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0},
                {0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},

                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},

                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        public static final int[][] WINDMILL_TUTORIAL_PRE_MATRIX = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},

                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0},

                {0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0},
                {0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0},
                {0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0},
                {0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0},

                {0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0},
                {0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0},
                {0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0},
                {0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0},

                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };

        public static final int[][] WINDMILL_PRE_MATRIX = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0},

                {0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0},

                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };


        public static int[][] GetTetrisTileShape(int tileIndex, int rotation) {
            int[][] matrix = switch (tileIndex) {
                case STAIRS_TILE -> new int[][]{{0, 0, 0, 1},
                        {0, 0, 1, 1},
                        {0, 1, 1, 0},
                        {1, 1, 0, 0}};
                case L_TILE -> new int[][]{{0, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 1, 1, 0},
                        {0, 0, 0, 0}};
                case J_TILE -> new int[][]{{0, 0, 1, 0},
                        {0, 0, 1, 0},
                        {0, 1, 1, 0},
                        {0, 0, 0, 0}};
                case O_TILE -> new int[][]{{0, 0, 0, 0},
                        {0, 1, 1, 0},
                        {0, 1, 1, 0},
                        {0, 0, 0, 0}};
                case LONG_T_TILE -> new int[][]{{0, 1, 0, 0},
                        {0, 1, 0, 0},
                        {1, 1, 1, 0},
                        {0, 0, 0, 0}};
                case Q_TILE -> new int[][]{{0, 0, 0, 0},
                        {0, 1, 1, 0},
                        {1, 1, 1, 0},
                        {0, 0, 0, 0}};
                case LONG_Z_TILE -> new int[][]{{0, 0, 0, 0},
                        {1, 1, 0, 0},
                        {0, 1, 1, 1},
                        {0, 0, 0, 0}};
                case LONG_S_TILE -> new int[][]{{0, 0, 0, 0},
                        {0, 1, 1, 1},
                        {1, 1, 0, 0},
                        {0, 0, 0, 0}};
                case I_TILE -> new int[][]{{0, 0, 0, 0},
                        {0, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 1, 0, 0}};
                case LONG_I_TILE -> new int[][]{{0, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 1, 0, 0}};
                case SINGLE_TILE -> new int[][]{{0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 1, 0, 0},
                        {0, 0, 0, 0}};
                case DOUBLE_TILE -> new int[][]{{0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 1, 1, 0},
                        {0, 0, 0, 0}};
                default -> new int[][]{{1, 1, 1, 1},
                        {1, 1, 1, 1},
                        {1, 1, 1, 1},
                        {1, 1, 1, 1}};
            };

            for (int i = 0; i < rotation; i++) {
                matrix = rotateMatrixBy90Degree(matrix);
            }
            return matrix;
        }
    }

    public static class ControllerConstants {
        public static final float JOYSTICK_DEAD_ZONE = 0.75f;
        public static final int CONTROLLER_B_BUTTON_ID = 0;
        public static final int CONTROLLER_A_BUTTON_ID = 1;
        public static final int CONTROLLER_X_BUTTON_ID = 2;
        public static final int CONTROLLER_Y_BUTTON_ID = 3;
        public static final int CONTROLLER_O_BUTTON_ID = 4;
        public static final int CONTROLLER_L_BUTTON_ID = 5;
        public static final int CONTROLLER_R_BUTTON_ID = 6;
        public static final int CONTROLLER_ZL_BUTTON_ID = 7;
        public static final int CONTROLLER__BUTTON_ID = 8;
        public static final int CONTROLLER_MINUS_BUTTON_ID = 9;
        public static final int CONTROLLER_PLUS_BUTTON_ID = 10;
        public static final int CONTROLLER_H_BUTTON_ID = 11;
        public static final int CONTROLLER_LJS_BUTTON_ID = 12;
        public static final int CONTROLLER_RJS_BUTTON_ID = 13;
        public static final int CONTROLLER_UP_BUTTON_ID = 14;
        public static final int CONTROLLER_RIGHT_BUTTON_ID = 15;
        public static final int CONTROLLER_DOWN_BUTTON_ID = 16;
        public static final int CONTROLLER_LEFT_BUTTON_ID = 17;
        public static final int NUM_BUTTONS = 18;

    }

}