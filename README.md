# RadarScanView
自定义View之雷达扫描



## 原理
关键是使用SweepGradient进行扫描渲染
关键代码如下：

```JAVA
        Shader shader = new SweepGradient(centerX, centerY, Color.TRANSPARENT, tailColor);
        mPaintRadar.setShader(shader);
```
然后旋转起来即可

```JAVA
        matrix.postRotate(start, centerX, centerY);
```
       
代码中包含了如何自定义一个View，怎样重写onMeasure、onSizeChanged、onDraw函数、自定义属性等等知识点

## 效果图

![image](https://github.com/gpfduoduo/RadarScanView/blob/master/RadarScanView/gif/radar.gif "效果图")


