package utilz;

import main.Game;
import static utilz.HelpMethods.*;
public class Constants {
	public static final int FPS_SET = 120;
	public static final int UPS_SET = 200;
	public static final float GRAVITY = 0.04f * Game.SCALE;
	public static final int ANI_SPEED = 13;//25;

	public static class Dialogue {
		public static final int QUESTION = 0;
		public static final int EXCLAMATION = 1;

		public static final int DIALOGUE_WIDTH = (int) (14 * Game.SCALE);
		public static final int DIALOGUE_HEIGHT = (int) (12 * Game.SCALE);

		public static int GetSpriteAmount(int type) {
			switch (type) {
			case QUESTION, EXCLAMATION:
				return 5;
			}

			return 0;
		}
	}

	public static class Projectiles {
		public static final int CANNON_BALL_DEFAULT_WIDTH = 15;
		public static final int CANNON_BALL_DEFAULT_HEIGHT = 15;

		public static final int CANNON_BALL_WIDTH = (int) (Game.SCALE * CANNON_BALL_DEFAULT_WIDTH);
		public static final int CANNON_BALL_HEIGHT = (int) (Game.SCALE * CANNON_BALL_DEFAULT_HEIGHT);
		public static final float SPEED = 0.75f * Game.SCALE;
	}

	public static class ObjectConstants {

		public static final int RED_POTION = 0;
		public static final int BLUE_POTION = 1;
		public static final int BARREL = 2;
		public static final int BOX = 3;
		public static final int SPIKE = 4;
		public static final int CANNON_LEFT = 5;
		public static final int CANNON_RIGHT = 6;
		public static final int TREE_ONE = 7;
		public static final int TREE_TWO = 8;
		public static final int TREE_THREE = 9;

		public static final int RED_POTION_VALUE = 15;
		public static final int BLUE_POTION_VALUE = 10;

		public static final int CONTAINER_WIDTH_DEFAULT = 40;
		public static final int CONTAINER_HEIGHT_DEFAULT = 30;
		public static final int CONTAINER_WIDTH = (int) (Game.SCALE * CONTAINER_WIDTH_DEFAULT);
		public static final int CONTAINER_HEIGHT = (int) (Game.SCALE * CONTAINER_HEIGHT_DEFAULT);

		public static final int POTION_WIDTH_DEFAULT = 12;
		public static final int POTION_HEIGHT_DEFAULT = 16;
		public static final int POTION_WIDTH = (int) (Game.SCALE * POTION_WIDTH_DEFAULT);
		public static final int POTION_HEIGHT = (int) (Game.SCALE * POTION_HEIGHT_DEFAULT);

		public static final int SPIKE_WIDTH_DEFAULT = 32;
		public static final int SPIKE_HEIGHT_DEFAULT = 32;
		public static final int SPIKE_WIDTH = (int) (Game.SCALE * SPIKE_WIDTH_DEFAULT);
		public static final int SPIKE_HEIGHT = (int) (Game.SCALE * SPIKE_HEIGHT_DEFAULT);

		public static final int CANNON_WIDTH_DEFAULT = 40;
		public static final int CANNON_HEIGHT_DEFAULT = 26;
		public static final int CANNON_WIDTH = (int) (CANNON_WIDTH_DEFAULT * Game.SCALE);
		public static final int CANNON_HEIGHT = (int) (CANNON_HEIGHT_DEFAULT * Game.SCALE);

		public static int GetSpriteAmount(int object_type) {
			switch (object_type) {
			case RED_POTION, BLUE_POTION:
				return 7;
			case BARREL, BOX:
				return 8;
			case CANNON_LEFT, CANNON_RIGHT:
				return 7;
			}
			return 1;
		}

		public static int GetTreeOffsetX(int treeType) {
			switch (treeType) {
			case TREE_ONE:
				return (Game.TILES_SIZE / 2) - (GetTreeWidth(treeType) / 2);
			case TREE_TWO:
				return (int) (Game.TILES_SIZE / 2.5f);
			case TREE_THREE:
				return (int) (Game.TILES_SIZE / 1.65f);
			}

			return 0;
		}

		public static int GetTreeOffsetY(int treeType) {

			switch (treeType) {
			case TREE_ONE:
				return -GetTreeHeight(treeType) + Game.TILES_SIZE * 2;
			case TREE_TWO, TREE_THREE:
				return -GetTreeHeight(treeType) + (int) (Game.TILES_SIZE / 1.25f);
			}
			return 0;

		}

		public static int GetTreeWidth(int treeType) {
			switch (treeType) {
			case TREE_ONE:
				return (int) (39 * Game.SCALE);
			case TREE_TWO:
				return (int) (62 * Game.SCALE);
			case TREE_THREE:
				return -(int) (62 * Game.SCALE);

			}
			return 0;
		}

		public static int GetTreeHeight(int treeType) {
			switch (treeType) {
			case TREE_ONE:
				return (int) (int) (92 * Game.SCALE);
			case TREE_TWO, TREE_THREE:
				return (int) (54 * Game.SCALE);

			}
			return 0;
		}
	}

	public static class EnemyConstants {
		public static final int CRABBY = 0;
		
		public static final int IDLE = 0;
		public static final int RUNNING = 1;
		public static final int ATTACK = 2;
		public static final int HIT = 3;
		public static final int DEAD = 4;

		public static final int CRABBY_WIDTH_DEFAULT = 72;
		public static final int CRABBY_HEIGHT_DEFAULT = 32;
		public static final int CRABBY_WIDTH = (int) (CRABBY_WIDTH_DEFAULT * Game.SCALE);
		public static final int CRABBY_HEIGHT = (int) (CRABBY_HEIGHT_DEFAULT * Game.SCALE);
		public static final int CRABBY_DRAWOFFSET_X = (int) (26 * Game.SCALE);
		public static final int CRABBY_DRAWOFFSET_Y = (int) (9 * Game.SCALE);


		public static int GetSpriteAmount(int enemy_type, int enemy_state) {
			switch (enemy_state) {

			case IDLE: {
				if (enemy_type == CRABBY)
					return 9;
			}
			case RUNNING:
				return 6;
			case ATTACK:
				return 7;
			case HIT:
				return 4;
			case DEAD:
				return 5;
			}

			return 0;

		}

		public static int GetMaxHealth(int enemy_type) {
			switch (enemy_type) {
			case CRABBY:
				return 50;
			default:
				return 1;
			}
		}

		public static int GetEnemyDmg(int enemy_type) {
			switch (enemy_type) {
			case CRABBY:
				return 15;
			default:
				return 0;
			}
		}
	}

	public static class Environment {
		public static final int BIG_CLOUD_WIDTH_DEFAULT = 448;
		public static final int BIG_CLOUD_HEIGHT_DEFAULT = 101;
		public static final int SMALL_CLOUD_WIDTH_DEFAULT = 74;
		public static final int SMALL_CLOUD_HEIGHT_DEFAULT = 24;

		public static final int BIG_CLOUD_WIDTH = (int) (BIG_CLOUD_WIDTH_DEFAULT * Game.SCALE);
		public static final int BIG_CLOUD_HEIGHT = (int) (BIG_CLOUD_HEIGHT_DEFAULT * Game.SCALE);
		public static final int SMALL_CLOUD_WIDTH = (int) (SMALL_CLOUD_WIDTH_DEFAULT * Game.SCALE);
		public static final int SMALL_CLOUD_HEIGHT = (int) (SMALL_CLOUD_HEIGHT_DEFAULT * Game.SCALE);
	}

	public static class UI {
		public static class Buttons {
			public static final int B_WIDTH_DEFAULT = 140;
			public static final int B_HEIGHT_DEFAULT = 56;
			public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
			public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
		}

		public static class PauseButtons {
			public static final int SOUND_SIZE_DEFAULT = 42;
			public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
		}

		public static class URMButtons {
			public static final int URM_DEFAULT_SIZE = 56;
			public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE);

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
		public static final int PLAYER_GREEN_VALUE = 100;
		public static final int HITBOX_BASE_WIDTH = 16;
		public static final int GRABBOX_BASE_WIDTH = (int) (HITBOX_BASE_WIDTH * 1.75f);
		public static final int HITBOX_BASE_HEIGHT = 27;
		public static final int GRABBOX_BASE_HEIGHT = (int) (HITBOX_BASE_HEIGHT * 1.25f);
		
		public static final int PLAYER_BASE_HEIGHT = 64;
		public static final int PLAYER_BASE_WIDTH = 64;
		
		public static final int IDLE = 0;
		public static final int RUNNING = 1;
		public static final int JUMP = 2;
		public static final int FALLING = 3;
		public static final int ATTACK = 4;
		public static final int HIT = 5;
		public static final int DEAD = 6;
		public static final int NUM_ANIMATIONS = 7;
		public static final int MAX_ANIMATION_LENGTH = 12;
		
		public static int GetSpriteAmount(int player_action) {
			switch (player_action) {
			case DEAD:
				return 1;//8;
			case RUNNING:
				return 12;//6;
			case IDLE:
				return 1;//5;
			case HIT:
				return 4;
			case JUMP:
				return 5;
			case ATTACK:
				return 1;//3;
			case FALLING:
				return 4;
			default:
				return 1;
			}
		}
	}
	
	public static class TetrisTileConstants {
		public static final int TETRIS_TILE_GREEN_VALUE = 255;
		
		
		public static final int TETRIS_TILE_WIDTH_DEFAULT = 32;
		public static final int TETRIS_TILE_HEIGHT_DEFAULT = 32;
		public static final int TETRIS_TILE_WIDTH = (int) (TETRIS_TILE_WIDTH_DEFAULT * Game.SCALE);
		public static final int TETRIS_TILE_HEIGHT = (int) (TETRIS_TILE_HEIGHT_DEFAULT * Game.SCALE);
		public static final int NUM_TETRIS_TILES = 1;
		
		public static final int T_TILE = 0;
		public static final int L_TILE = 1;
		public static final int J_TILE = 2;
		public static final int Z_TILE = 3;
		public static final int S_TILE = 4;
		public static final int O_TILE = 5;
		public static final int Q_TILE = 6;
		public static final int CROSS_TILE = 7;
		public static final int LONG_T_TILE = 8;
		public static final int LONG_Z_TILE = 9;
		public static final int LONG_S_TILE = 10;
		
		public static final float TETRIS_TILE_MAX_THROW_HEIGHT = 32*Game.SCALE*5.0f;
		public static final float TETRIS_TILE_MAX_THROW_SPEED = (float) Math.sqrt(TETRIS_TILE_MAX_THROW_HEIGHT*2*GRAVITY);
		public static final float TETRIS_TILE_TIME_FOR_MAX_THROW_SPEED = 2.0f;
		
		public static final float TETRIS_TILE_TIME_TO_REACH_WINDSPEED = 0.5f;
		public static final float TETRIS_TILE_TIME_TO_STOP_WHEN_IS_ON_FLOOR = 0.2f;
		public static final int TETRIS_GRID_SIZE = Game.TILES_SIZE/4;
		
		public static int[][] GetTetrisTileShape (int tileIndex, int rotation) {
			int[][] matrix;
			switch (tileIndex) {
			case T_TILE:
				matrix = new int[][] {{0,0,0,0}, 
									{0,1,0,0},
									{1,1,1,0},
									{0,0,0,0}};
				break;									
			default:
				matrix = new int[][] {{1,1,1,1}, 
									{1,1,1,1},
									{1,1,1,1},
									{1,1,1,1}};
				break;
			}
			
			for (int i = 0; i < rotation; i++) {
				matrix = rotateMatrixBy90Degree(matrix);
			}
			return matrix;
		}

	}

}