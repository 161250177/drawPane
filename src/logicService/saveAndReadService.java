package logicService;

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
