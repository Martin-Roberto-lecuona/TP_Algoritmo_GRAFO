package com.boc_dev.a_star_alg.map;

public class Map {

    private final int TILE_WIDTH = 1;
    private final int TILE_HEIGHT = 1;

    private Tile[][] tileMatrix;

    public Map(TileType[][] tileTypes) {
        this.tileMatrix = new Tile[tileTypes.length][tileTypes[0].length];
        int rowNumber = 0;
        for (TileType[] row : tileTypes) {
            int colNumber = 0;
            for (TileType item : row) {
                Tile tile = new Tile(item, rowNumber, colNumber, TILE_HEIGHT, TILE_WIDTH);
                tileMatrix[rowNumber][colNumber] = tile;
                colNumber++;
            }
            rowNumber++;
        }
    }

    public Tile[][] getTileMatrix() {
        return tileMatrix;
    }
}
