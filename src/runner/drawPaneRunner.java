package runner;
import ui.mainFrame;

public class drawPaneRunner {
    public drawPaneRunner(){
        initMainFrame();
    }

    private void initMainFrame(){
        mainFrame mainFrame=new mainFrame();
    }

    public static void main(String[] args){
        drawPaneRunner dpr=new drawPaneRunner();
    }
}
