<h2>1.功能</h2>
<li>每次绘制时拖动一次绘制一条边，确保拖动绘制的是图形的一条且仅一条完整边，一笔一笔进行绘画。一个图形绘制完成后点击上方的“确认完成”按钮，画板会对手绘的图形进行识别并完成规范化的重新绘制。<br>
　　例：一笔画一个整圆，然后点击“确认完成”按钮。<br>
　　　　顺序三笔绘制一个三角形，然后点击“确认完成”按钮。<br>
　　　　顺序四笔绘制一个矩形，然后点击“确认完成按钮”。
　　<h6>（目前仅支持识别圆形、三角形、长方形和正方形）</h6>
<li>识别结果会以文字的方式写在图形中央。

<li>“清空”按钮会清空当前画板的内容。
<li>“保存”按钮会将画板的内容（shape对象数组）保存到项目src文件夹的data.txt里。
<li>“读取”按钮会将data.txt文件里的内容读取并清空当前画板内容，加载上一次保存的内容。

<h2>2.代码：</h2>
<li>ui文件夹里是展示层，放置了整个窗口</li>
<li>logicService和logicServiceImpl是逻辑层的抽象和实现，里面包含数组、计算、识别、规范化重绘、保存和读取等方法的实现，为展示层提供数据以供展示，连接了上层展示层和下层的数据层</li>
<li>dataService和dataServiceImpl是数据层的抽象和实现，具体是底层对文件的读取操作，不关心上层的逻辑</li>
<li>entity是实体类的实现，包括shape和point两个实体类</li>
<li>runner里是项目的启动类</li>