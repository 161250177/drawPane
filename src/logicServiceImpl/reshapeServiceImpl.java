package logicServiceImpl;

import entity.point;
import entity.shape;
import logicService.*;


public class reshapeServiceImpl implements reshapeService {
    int x0,y0,x1,y1,x2,y2,a=0;
    double t1,t2=0;
    point point1=new point(0,0),point2=new point(0,0),point3=new point(0,0),point4=new point(0,0);
    calculateService calculateService=new calculateServiceImpl();
    /**
     *规范化圆形的方法
     * @param shape
     * @return 圆心坐标x，y和半径长度
     */
    @Override
    public int[] reshapeCircle(shape shape) {
        x0=(shape.getMaxX()+shape.getMinX())/2;
        y0=(shape.getMaxY()+shape.getMinY())/2;
        int r= (int)calculateService.calculateDistanceBetweenPoints(new point(x0,y0),new point(shape.getEndpoints()[0].getX(),shape.getEndpoints()[0].getY()));
        int[] result={x0,y0,r};
        return result;
    }    /**
     * 规范化矩形的方法
     * @param shape
     * @return 四个端点
     */

    @Override
    public point[] reshapeRectangle(shape shape) {
        x0=(shape.getEndpoints()[0].getX());
        y0=(shape.getEndpoints()[0].getY());
        x1=(shape.getEndpoints()[1].getX());
        y1=(shape.getEndpoints()[1].getY());
        x2=(shape.getEndpoints()[2].getX());
        y2=(shape.getEndpoints()[2].getY());
        point1=new point(x0,y0);
        point2=new point(x1,y1);
        a=(int) calculateService.calculateDistanceBetweenPoints(new point(x1,y1),new point(x2,y2));//矩形的边长
        t1= calculateService.calculateT(new point(x0,y0),new point(x1,y1));
        t2=-1/t1;

        double x=Math.sqrt(Math.pow(a,2)/(1+Math.pow(t2,2)));
        double y=Math.abs(t2*x);

        if(x1<x0&&y1>y0&&x2>x1){
            point3=new point(x1+(int)x,y1+(int)y);
            point4=new point(x0+(int)x,y0+(int)y);
        }
        else if(x1<x0&&y1>y0&&x2<x1){
            point3=new point(x1-(int)x,y1-(int)y);
            point4=new point(x0-(int)x,y0-(int)y);
        }
        else if(x1>x0&&y1>y0&&x2<x1){
            point3=new point(x1-(int)x,y1+(int)y);
            point4=new point(x0-(int)x,y0+(int)y);
        }
        else if(x1>x0&&y1>y0&&x2>x1){
            point3=new point(x1+(int)x,y1-(int)y);
            point4=new point(x0+(int)x,y0-(int)y);
        }
        else if(x1>x0&&y1<y0&&x2>x1){
            point3=new point(x1+(int)x,y1+(int)y);
            point4=new point(x0+(int)x,y0+(int)y);
        }
        else if(x1>x0&&y1<y0&&x2<x1){
            point3=new point(x1-(int)x,y1-(int)y);
            point4=new point(x0-(int)x,y0-(int)y);
        }
        else if(x1<x0&&y1<y0&&x2>x1){
            point3=new point(x1+(int)x,y1-(int)y);
            point4=new point(x0+(int)x,y0-(int)y);
        }
        else if(x1<x0&&y1<y0&&x2<x1){
            point3=new point(x1-(int)x,y1+(int)y);
            point4=new point(x0-(int)x,y0+(int)y);
        }
        point[] result={point1,point2,point3,point4};
        return result;
    }
}
