package logicService;

import entity.point;

/**
 * 有关图形计算的方法
 */
public interface calculateService {
    /**
     * 计算两点之间的距离
     * @param point1 点1
     * @param point2 点2
     * @return
     */
        public double calculateDistanceBetweenPoints(point point1,point point2);

    /**
     * 计算两点构成直线的斜率
     * @param point1 点1
     * @param point2 点2
     * @return
     */
        public double calculateT(point point1,point point2);

}
