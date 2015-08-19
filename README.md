# RadarScanView
自定义View之雷达扫描

## 效果图

![image](https://github.com/gpfduoduo/RadarScanView/blob/master/RadarScanView/gif/radar.gif "效果图")

## 原理
关键是使用SweepGradient进行扫描渲染
        Shader shader = new SweepGradient(centerX, centerY, Color.TRANSPARENT, tailColor);
        mPaintRadar.setShader(shader);
然后让其转起来
       matrix.postRotate(start, centerX, centerY);
