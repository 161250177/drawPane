package dataServiceImpl;

import dataService.readAndWriteService;
import entity.shape;
import logicService.arrayService;
import logicServiceImpl.arrayServiceImpl;

import java.io.*;

public class readAndWriteServiceImpl implements readAndWriteService {
    File file=new File("src/data.txt");
    arrayService arrayService=new arrayServiceImpl();

    @Override
    public void write(Object object) {
        ObjectOutputStream oos;
        if (!file.exists())
        {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        try {
            oos=new ObjectOutputStream( new BufferedOutputStream(new FileOutputStream(file)));
            oos.writeObject(object);
            oos.flush();
            oos.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public Object read() {

        try {
            ObjectInputStream ois = new ObjectInputStream( new BufferedInputStream(new FileInputStream(file)));
            Object result=ois.readObject();
            ois.close();
            return result;
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        return new Object();

    }
}
