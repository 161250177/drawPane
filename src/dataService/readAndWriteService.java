package dataService;

import entity.shape;


public interface readAndWriteService {
    /**
     * 往文件里写对象的方法
     * @param object
     */
    public void write(Object object);

    /**
     * 从文件中读取对象的方法
     * @return
     */
    public Object read();
}
