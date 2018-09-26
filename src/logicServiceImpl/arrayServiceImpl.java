package logicServiceImpl;

import logicService.arrayService;

import java.io.Serializable;

public class arrayServiceImpl implements arrayService,Serializable {
    @Override
    public void insertElement(Object[] array,Object element) {
        int oldLength=array.length;
        /**
         * 找到原数组中不为nul的最后一个元素，在其后面添加新元素
         */
        for(int i=0;i<array.length;i++){
            if(array[i]==null){
                oldLength=i;
                break;
            }
        }
        Object[] tempArray=new Object[oldLength+1];
        System.arraycopy(array,0,tempArray,0,oldLength);
        tempArray[oldLength]=element;

        System.arraycopy(tempArray,0,array,0,oldLength+1);//将添加完新元素的数组复制回原数组


    }

    /**
     * 得到数组连续非空元素的真实长度
     * @param array
     * @return
     */
    @Override
    public int getActualLength(Object[] array) {
        for(int i=0;i<array.length;i++){
            if(array[i]==null) {
                return i;

            }
        }
        return array.length;
    }


}
