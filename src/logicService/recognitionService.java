package logicService;

import entity.shape;

/**
 * 识别图形的类
 */
public interface recognitionService {
    /**
     * 识别图形
     * @param shape
     * @return
     */
    public shape recogntion(shape shape);
}
