package zones;

import entities.TetrisTile;
import gamestates.Playing;
import main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import static utilz.Constants.Environment.TEMP_FROM_FINISHED_WINDMILL;
import static utilz.Constants.TetrisTileConstants.*;
import static utilz.HelpMethods.*;


public class BuildingZone {
    private int gridWidth, gridHeight;
    private int[][] matrix, goalMatrix, preMatrix;
    private int buildingZoneIndex;
    protected Rectangle2D.Float hitbox;
    private List<TetrisTile> tetrisTiles = new ArrayList<>();
    private String zoneType;
    private boolean finished = false;
    private BuildingZoneManager buildingZoneManager;

    public BuildingZone(int x, int y, int width, int height, int buildingZoneIndex, String zoneType) {
        this.buildingZoneIndex = buildingZoneIndex;
        hitbox = new Rectangle2D.Float(x, y, (int) (width), (int) (height));
        gridWidth = (int) width / Game.TILES_SIZE * 4;
        gridHeight = (int) height / Game.TILES_SIZE * 4;
        this.zoneType = zoneType;
        initMatrixes();
    }


    private void initMatrixes() {
        switch (zoneType) {
            case "windmill":
                goalMatrix = matrixDeepCopy(WINDMILL_GOAL_MATRIX);
                preMatrix = matrixDeepCopy(WINDMILL_PRE_MATRIX);
                matrix = matrixDeepCopy(WINDMILL_PRE_MATRIX);
                break;
            case "windmill_tutorial":
                goalMatrix = matrixDeepCopy(WINDMILL_GOAL_MATRIX);
                preMatrix = matrixDeepCopy(WINDMILL_TUTORIAL_PRE_MATRIX);
                matrix = matrixDeepCopy(WINDMILL_TUTORIAL_PRE_MATRIX);
                break;
            case "rocket":
                goalMatrix = matrixDeepCopy(ROCKET_GOAL_MATRIX);
                preMatrix = matrixDeepCopy(ROCKET_PRE_MATRIX);
                matrix = matrixDeepCopy(ROCKET_PRE_MATRIX);
                break;
            case "rocket_tutorial":
                goalMatrix = matrixDeepCopy(ROCKET_GOAL_MATRIX);
                preMatrix = matrixDeepCopy(ROCKET_TUTORIAL_PRE_MATRIX);
                matrix = matrixDeepCopy(ROCKET_TUTORIAL_PRE_MATRIX);
                break;
        }
    }

    public boolean addTetrisTile(TetrisTile tetrisTile) {
        //int[][] oldMatrix = matrixDeepCopy(matrix);
        int[][] newMatrix = addTetrisTileMatrix(tetrisTile.getHitbox().x,
                tetrisTile.getHitbox().y,
                tetrisTile.getMatrix(),
                tetrisTile.getXDrawOffset(),
                tetrisTile.getYDrawOffset());
        if (matrixEquiv(newMatrix, matrix)) {
            return false;
        }

        tetrisTile.setXSpeed(0);
        tetrisTile.setAirSpeed(0);
        tetrisTile.setMovingInGrid(false);
        tetrisTile.setInAir(false);
        tetrisTile.setLockedInBuildingZone(this);


        if (!isCompletable(newMatrix)) {
            // unsuccessful
            if (tetrisTile.getIsPredictionTile())
                return false;

            String explosionType = "small";
            if (zoneType.contains("rocket"))
                explosionType = "large";
            tetrisTile.startExplosionTimer(explosionType, this, 0);
            return false;
        } else {
            // successful
            if (tetrisTile.getIsPredictionTile())
                return true;

            tetrisTiles.add(tetrisTile);
            matrix = newMatrix;
            System.out.println("successfully added");
            if (isFinished()) {
                System.out.println("finished a building zone");
                eventOnFinish();
            }

            return true;
        }
    }

    public void removeTetrisTile(TetrisTile tetrisTile) {
        if (tetrisTiles.contains(tetrisTile)) {
            matrix = addTetrisTileMatrix(tetrisTile.getHitbox().x,
                    tetrisTile.getHitbox().y,
                    matrixScalarMul(tetrisTile.getMatrix(), -1),
                    tetrisTile.getXDrawOffset(),
                    tetrisTile.getYDrawOffset());
            tetrisTiles.remove(tetrisTile);
        }
    }

    public boolean isCompletable(int[][] m) {
        int rows = m.length;
        int cols = m[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (m[i][j] == 1 && goalMatrix[i][j] == 0 && preMatrix[i][j] == 0)
                    return false;
            }
        }
        return true;
    }

    public void eventOnFinish() {
        if (finished)
            return;
        finished = true;
        if (zoneType.contains("windmill")) {
            buildingZoneManager.getPlaying().setTempFromWindmills(buildingZoneManager.getPlaying().getTempFromWindmills() + TEMP_FROM_FINISHED_WINDMILL);
            ;
        }
    }

    public void setBuildingZoneManager(BuildingZoneManager buildingZoneManager) {
        this.buildingZoneManager = buildingZoneManager;
    }

    public boolean isFinished() {
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0 && goalMatrix[i][j] == 1)
                    return false;
            }
        }
        return true;
    }

    public int[][] addTetrisTileMatrix(float x, float y, int[][] tileMatrix, float xDrawOffset, float yDrawOffset) {
        int[][] m = new int[gridHeight][gridWidth];
        int xIndexShift = Math.round(-hitbox.x + x - xDrawOffset) / TETRIS_GRID_SIZE;
        int yIndexShift = Math.round(-hitbox.y + y - yDrawOffset) / TETRIS_GRID_SIZE;
        //printArray(m);
        //System.out.println("=============");
        m = matrixAdd(matrix, tileMatrix, xIndexShift, yIndexShift);
        //printArray(m);
        return m;
    }

    public void update(Playing playing) {
    }

    public boolean getFinished() {
        return finished;
    }

    public int getBuildingZoneIndex() {
        return buildingZoneIndex;
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    protected void drawHitbox(Graphics g, int xLvlOffset, int yLvlOffset) {
        g.setColor(Color.GREEN);
        g.drawRect((int) hitbox.x - xLvlOffset, (int) hitbox.y - yLvlOffset, (int) hitbox.width, (int) hitbox.height);
    }


    public String getZoneType() {
        return zoneType;
    }


    public int[][] getMatrix() {
        return matrix;
    }


    public void resetBuildingZone() {
        tetrisTiles = new ArrayList<>();
        finished = false;
        initMatrixes();
    }

    public List<TetrisTile> getTetrisTiles() {
        return tetrisTiles;
    }
}