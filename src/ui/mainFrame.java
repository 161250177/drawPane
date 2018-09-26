package ui;

import entity.point;
import entity.shape;
import logicService.*;
import logicServiceImpl.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class mainFrame extends JFrame{
    static private int maxShapesNum=10000;//最大允许存放shape的个数

    JFrame mainFrame;//主体Frame
    JPanel mainPanel;//主体面板
    JButton confirmButton,saveButton,loadButton,clearButton;//确认完成一个图形,保存按钮,读取按钮
    entity.shape[] shapes;

    int startX,startY,endX,endY=0;
    shape shape=new shape();//当前正在绘制的图形
    File file;


    private arrayService arrayService;
    private calculateService calculateService;
    private recognitionService recognitionService;
    private saveAndReadService saveAndReadService;
    private reshapeService reshapeService;
    /**
     * 构造函数,初始化面板的各项组件
     */
    public mainFrame(){
        mainFrame=new JFrame("DrawPane");
        mainPanel=new JPanel();

        file=new File("D:/drawPane/data.txt");
        arrayService=new arrayServiceImpl();
        calculateService =new calculateServiceImpl();
        recognitionService =new recognitionServiceImpl();
        saveAndReadService =new saveAndReadServiceImpl();
        reshapeService =new reshapeServiceImpl();

        shapes=new shape[maxShapesNum];
        for(int i=0;i<maxShapesNum;i++){
            shapes[i]=null;
        }
        confirmButton=new JButton("确认完成");
        saveButton=new JButton("保存");
        loadButton=new JButton("读取");
        clearButton=new JButton("清空");

        //clearButton=new JButton("清空");
        mainPanel.setSize(400,400);
        mainPanel.setBackground(Color.WHITE);
        mainFrame.setSize(1000,800);

        mainPanel.add(confirmButton);
        mainPanel.add(saveButton);
        mainPanel.add(loadButton);
        mainPanel.add(clearButton);
        mainFrame.add(mainPanel);

        mainFrame.setLocation(500,200);
        mainFrame.setResizable(false);
        mainFrame.setUndecorated(true); // 去掉窗口的装饰
        mainFrame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        mainFrame.setVisible(true);
        addListener();

    }

    /**
     * 添加监听
     */
    public void addListener(){
        /**
         * 鼠标点击监听，记录端点
         */
        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startX =e.getX();
                startY =e.getY();
                shape.addPoint(new point(startX, startY));
                shape.addEndPoint(new point(startX, startY));

            }

        });

        /**
         * 鼠标拖动监听
         */
        mainPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endX=e.getX();
                endY=e.getY();

                if(endX<shape.getMinX()) shape.setMinX(endX);//计算x最小点
                if(endX>shape.getMaxX()) shape.setMaxX(endX);
                if(endY<shape.getMinY()) shape.setMinY(endY);
                if(endY>shape.getMaxY()) shape.setMaxY(endY);

                shape.addPoint(new point(e.getX(),e.getY()));//将拖动时每一刻的点保存到数组
                Graphics graphics=mainPanel.getGraphics();
                ((Graphics2D)graphics).setColor(new Color(0,0,0));
                ((Graphics2D)graphics).setStroke(new BasicStroke(3));
                ((Graphics2D)graphics).drawLine(startX,startY,endX,endY);
                startX=endX;
                startY=endY;
            }
        });

        /**
         * 确认完成按钮添加监听
         */
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape=recognitionService.recogntion(shape);
                clearOldShape(shape);
                addShape(shape);
                for(int i=0;i<arrayService.getActualLength(shapes);i++)
                drawNewShape(shapes[i]);
                addTip(shape);
                shape=new shape();
            }
        });

        /**
         * 保存按钮加上监听
         */
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAndReadService.save(shapes);

            }
        });

        /**
         * 读取按钮加上监听
         */
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<arrayService.getActualLength(shapes);i++){
                    clearNewShape(shapes[i]);
                    clearTip(shapes[i]);
                }

                shapes=saveAndReadService.read();

                    for(int i=0;i<arrayService.getActualLength(shapes);i++){
                        drawNewShape(shapes[i]);
                        addTip(shapes[i]);

                    }
            }
        });

        /**
         * 清楚按钮加上监听
         */
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.repaint();
                for(int i=0;i<maxShapesNum;i++){
                    shapes[i]=null;
                }
            }
        });


    }
    /**
     * 标识后贴上标签
     */

    public void addTip(shape shape){
        Graphics graphics=mainPanel.getGraphics();
        ((Graphics2D)graphics).drawString(shape.getType(),(shape.getMaxX()+shape.getMinX())/2-3*(shape.getType().length()),(shape.getMaxY()+shape.getMinY())/2);

    }

    /**
     * 规范化过程中首先擦除原来的图形
     * @param shape
     */
    public void clearOldShape(shape shape){
        Graphics graphics=mainPanel.getGraphics();

        ((Graphics2D)graphics).setColor(new Color(255,255,255));//画笔颜色为白色即为擦除
        ((Graphics2D)graphics).setStroke(new BasicStroke(3));

        startX=shape.getPoints()[0].getX();
        startY=shape.getPoints()[0].getY();
        for(int i=1;i<arrayService.getActualLength(shape.getPoints());i++){
            endX=shape.getPoints()[i].getX();
            endY=shape.getPoints()[i].getY();

            ((Graphics2D)graphics).drawLine(startX,startY,endX,endY);
            startX=endX;
            startY=endY;

        }
    }

    /**
     * 读取过程中需要擦除画板上现有的标识
     */

    public void clearTip(shape shape){
        Graphics graphics=mainPanel.getGraphics();
        graphics.setColor(new Color(255,255,255));//画笔颜色为白色即为擦除
        ((Graphics2D)graphics).setStroke(new BasicStroke(3));
        ((Graphics2D)graphics).drawString(shape.getType(),(shape.getMaxX()+shape.getMinX())/2-3*(shape.getType().length()),(shape.getMaxY()+shape.getMinY())/2);
    }

    /**
     * 规范化过程中绘制新的规范化的图形
     * @param shape
     */
    public void drawNewShape(shape shape){
        Graphics graphics=mainPanel.getGraphics();

        ((Graphics2D)graphics).setColor(new Color(0,0,0));
        ((Graphics2D)graphics).setStroke(new BasicStroke(3));

        switch (shape.getType()){
            case "圆形":
                 int[] result=reshapeService.reshapeCircle(shape);
                ((Graphics2D)graphics).drawRoundRect(result[0]-result[2],result[1]-result[2],result[2]*2,result[2]*2,result[2]*2,result[2]*2);
                break;
            case "三角形":
                for(int i=0;i<3;i++)((Graphics2D)graphics).drawLine((shape.getEndpoints()[i].getX()),(shape.getEndpoints()[i].getY()),(shape.getEndpoints()[(i+1)%3].getX()),(shape.getEndpoints()[(i+1)%3].getY()));
                break;
            case "正方形":
            case "长方形":
                 point[] results=reshapeService.reshapeRectangle(shape);
                 for(int i=0;i<4;i++) ((Graphics2D)graphics).drawLine(results[i].getX(),results[i].getY(),results[(i+1)%4].getX(),results[(i+1)%4].getY());
                break;

        }

    }

    /**
     * 读取过程中需要擦除画板上现有的图形
     * @param shape
     */
    public void clearNewShape(shape shape){
        Graphics graphics=mainPanel.getGraphics();

        ((Graphics2D)graphics).setColor(new Color(255,255,255));//画笔颜色为白色即为擦除
        ((Graphics2D)graphics).setStroke(new BasicStroke(3));

        switch (shape.getType()){
            case "圆形":
                int[] result=reshapeService.reshapeCircle(shape);
                ((Graphics2D)graphics).drawRoundRect(result[0]-result[2],result[1]-result[2],result[2]*2,result[2]*2,result[2]*2,result[2]*2);

                break;
            case "三角形":
                for(int i=0;i<3;i++)((Graphics2D)graphics).drawLine((shape.getEndpoints()[i].getX()),(shape.getEndpoints()[i].getY()),(shape.getEndpoints()[(i+1)%3].getX()),(shape.getEndpoints()[(i+1)%3].getY()));

                break;
            case "正方形":
            case "长方形":
                point[] results=reshapeService.reshapeRectangle(shape);
                for(int i=0;i<4;i++) ((Graphics2D)graphics).drawLine(results[i].getX(),results[i].getY(),results[(i+1)%4].getX(),results[(i+1)%4].getY());

                break;
        }

    }

    public void addShape(shape shape){
        arrayService.insertElement(shapes,shape);
    }

    public static void main(String[] args){
        mainFrame mainFrame=new mainFrame();
    }
}


