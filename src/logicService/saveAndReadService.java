package logicService;

/**
 * 逻辑层读写操作，从数据层得到的数据转化为展示层可用的数据
 */

import entity.shape;
public interface saveAndReadService {
    /**
     * 将shape数组对象存储的方法
     * @param shapes
     */
    public void save(shape[] shapes);

    /**
     * 读取shape数组对象
     * @return
     */
    public shape[] read();
}
