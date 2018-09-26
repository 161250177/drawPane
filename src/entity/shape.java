package entity;

import entity.point;
import logicService.arrayService;
import logicServiceImpl.arrayServiceImpl;

import java.io.Serializable;

/*
 * 图形类 维护自己的点集合
 */
public class shape implements Serializable {
    static private int maxPointsNum=599999;//最大允许存放point的个数

    point[] points;//所有点集
    point[] endpoints;//端点
    String type;
    arrayService arrayService;
    int minX=9999,minY=9999,maxX=0,maxY=0;//图形绘制过程中最小/大的横纵坐标  用来确定中心点

    public shape(){
        points=new point[maxPointsNum];
        endpoints=new point[100];//最多4个端点，如果是圆也只存放4个端点
        for(int i=0;i<maxPointsNum;i++){
            points[i]=null;
        }
        for(int i=0;i<endpoints.length;i++){
            endpoints[i]=null;
        }
        type="";
        arrayService=new arrayServiceImpl();

    }

    /**
     * 添加一个point
     */
    public void addPoint(point point){
        arrayService.insertElement(points,point);
    }

    /**
     * 添加一个端点
     * @param point
     */
    public void addEndPoint(point point){
        arrayService.insertElement(endpoints,point);
    }

    public point[] getPoints(){
        return points;
    }

    public point[] getEndpoints(){
        return endpoints;
    }

    public int getMinX(){
        return minX;
    }

    public int getMinY(){
        return minY;
    }

    public int getMaxX(){
        return maxX;
    }

    public int getMaxY(){
        return maxY;
    }

    public String getType(){
        return type;
    }
    public void setMinX(int minX){
        this.minX=minX;
    }

    public void setMinY(int minY){
        this.minY=minY;
    }

    public void setMaxX(int maxX){
        this.maxX=maxX;
    }

    public void setMaxY(int maxY){
        this.maxY=maxY;
    }

    public void setType(String type){
        this.type=type;
    }

}
