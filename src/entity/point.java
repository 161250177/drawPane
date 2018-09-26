package entity;

import java.io.Serializable;

public class point implements Serializable{
    private int X,Y=0;

    public point(int X,int Y){
        this.X=X;
        this.Y=Y;
    }

    public int getX(){
        return  X;
    }

    public int getY(){
        return Y;
    }


    public void setX(int X){
        this.X=X;
    }

    public void setY(int Y){
        this.Y=Y;
    }


}
