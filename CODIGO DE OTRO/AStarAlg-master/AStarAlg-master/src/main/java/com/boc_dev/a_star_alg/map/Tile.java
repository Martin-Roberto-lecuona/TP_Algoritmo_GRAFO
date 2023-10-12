package com.boc_dev.a_star_alg.map;

public class Tile {

    private final int rowNumber;
    private final int colNumber;
    private int x;
    private int y;
    private int height;
    private int width;
    private TileType tileType;
    private int score;
    private Tile parent;
    private boolean open;

    public Tile(TileType tileType, int x, int y, int height, int width) {
        rowNumber = x;
        colNumber = y;
        this.tileType = tileType;
        this.x = x * width;
        this.y = y * height;
        this.height = height;
        this.width = width;
        score = 0;
        parent = null;
        open = true;
    }

    public Tile(Tile tile) {
        rowNumber = tile.rowNumber;
        colNumber = tile.colNumber;
        this.tileType = tile.getTileType();
        this.x = tile.getX();
        this.y = tile.getY();
        this.height = tile.getHeight();
        this.width = tile.getWidth();
        score = 0;
        parent = null;
        open = true;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColNumber() {
        return colNumber;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Tile getParent() {
        return parent;
    }

    public void setParent(Tile parent) {
        this.parent = parent;
    }

    public boolean isOpen() {
        return open;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tile) {
            return rowNumber == ((Tile) obj).rowNumber && colNumber == ((Tile) obj).colNumber;
        }
        return false;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
