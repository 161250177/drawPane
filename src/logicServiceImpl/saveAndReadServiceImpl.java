package logicServiceImpl;

/**
 * 逻辑层读写操作，从数据层得到的数据转化为展示层可用的数据
 */

import entity.shape;
import logicService.saveAndReadService;
import dataService.*;
import dataServiceImpl.*;
public class saveAndReadServiceImpl implements saveAndReadService {
    readAndWriteService readAndWriteService=new readAndWriteServiceImpl();
    @Override
    public void save(shape[] shapes) {
        readAndWriteService.write(shapes);
    }

    @Override
    public shape[] read() {
        shape[] shapes=(shape[])readAndWriteService.read();
        return shapes;
    }
}
