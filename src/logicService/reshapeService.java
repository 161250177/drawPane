package logicService;

import entity.point;
import entity.shape;

public interface reshapeService {
    /**
     *规范化圆形的方法
     * @param shape
     * @return 圆心坐标x，y和半径长度
     */
    public int[] reshapeCircle(shape shape);

    /**
     * 规范化矩形的方法
     * @param shape
     * @return 四个端点
     */
    public point[] reshapeRectangle(shape shape);


}
