package logicServiceImpl;

import entity.shape;
import logicService.arrayService;
import logicService.calculateService;
import logicService.recognitionService;

public class recognitionServiceImpl implements recognitionService {
    arrayService arrayService=new arrayServiceImpl();
    calculateService calculateService=new calculateServiceImpl();

    @Override
    public shape recogntion(shape shape) {
        if(arrayService.getActualLength(shape.getEndpoints())==1) shape.setType("圆形");
        else if(arrayService.getActualLength(shape.getEndpoints())==3) shape.setType("三角形");
        else if(arrayService.getActualLength(shape.getEndpoints())==4) {
            double length1= calculateService.calculateDistanceBetweenPoints(shape.getEndpoints()[0],shape.getEndpoints()[1]);
            double length2= calculateService.calculateDistanceBetweenPoints(shape.getEndpoints()[1],shape.getEndpoints()[2]);
            if(length1/length2>1.2||length1/length2<0.83) shape.setType("长方形");
            else shape.setType("正方形");

        }
        else{
            shape.setType("无法识别的图形");
        }

        return shape;

    }
}
