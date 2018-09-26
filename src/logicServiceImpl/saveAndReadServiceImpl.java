package logicServiceImpl;

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
