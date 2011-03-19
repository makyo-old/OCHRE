/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package us.jnsq.ochre.cs.board;

/**
 *
 * @author makyo
 */
public abstract class AbstractBoardObject {
    private int x, y, z;

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the z
     */
    public int getZ() {
        return z;
    }

    /**
     * @param z the z to set
     */
    public void setZ(int z) {
        this.z = z;
    }
    
    @Override
    public abstract String toString();

}
