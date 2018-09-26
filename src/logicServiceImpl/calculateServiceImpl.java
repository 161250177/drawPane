package logicServiceImpl;

import logicService.arrayService;
import logicService.calculateService;
import entity.point;

import java.text.DecimalFormat;

public class calculateServiceImpl implements calculateService {
    arrayService arrayService=new arrayServiceImpl();


    @Override
    public double calculateDistanceBetweenPoints(point point1, point point2) {
        double distanceX=Math.pow(point1.getX()-point2.getX(),2);
        double distanceY=Math.pow(point1.getY()-point2.getY(),2);

        DecimalFormat df=new DecimalFormat("#.00");

        double result=Double.parseDouble(df.format(Math.sqrt(distanceX+distanceY)));

        return result;
    }

    @Override
    public double calculateT(point point1, point point2) {
        int x0=point1.getX();
        int y0=point1.getY();
        int x1=point2.getX();
        int y1=point2.getY();

        return (y1-y0)*1.0/(x1-x0);
    }
}
