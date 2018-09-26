package logicService;

/**
 * 有关数组操作的接口
 */
public interface arrayService {
    /**
     * 往array末尾添加元素的方法
     * @param array
     * @param element
     */
    public void insertElement(Object[] array,Object element);

    /**
     * 得到数组连续非空元素的真实长度
     * @param array
     * @return
     */
    public int getActualLength(Object[] array);




}
