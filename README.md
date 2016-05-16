# CycleViewPager
A ViewPager who can scroll from last sliding right to first, or first sliding left to last. 

[![](https://jitpack.io/v/RainbleNi/CycleViewPager.svg)](https://jitpack.io/#RainbleNi/CycleViewPager)

##Introduce
CycleViewPaer to active the Cycle ViewPager, which can scroll from last sliding right to first, or first sliding left to last. 
CycleViewPaer实现了可循环的ViewPager, 可以让ViewPager在第一页左滑至最后一页，最后一页右滑至第一页。

优点：

1 原生态，所有接口以及Adapter直接继承原生的即可。

2 开销少，默认模式下每个position永远只会实例化一次。即使在回收模式下，也是尽可能的多重用之前实例化过的界面。


##Usage

首先，在项目根目录下的build.gradle中添加如下代码：

```java
 allprojects {
    repositories {
      ...
      maven { url "https://jitpack.io" }
    }
  }
```
其次，在module的build.gradle中添加最新版本库的依赖:
```java
 dependencies {
           compile 'com.github.RainbleNi:CycleViewPager:0.0.2'
  }
```
set recycle mode

设置回收模式
```java
/*
 * destroyItemWhenNeeded 
 * true: enable the original recycle stroage. 启用原生的回收策略
 * false: cache the item when originally should destroy .
 *        item不进行回收，因为CycleViewPager已经在item的重用上做了最优处理，
 *        所以在item数量较少或者变动频繁的场景中，例如轮播图的应用场景中，为了减少开销，建议为false
 ＊ default 默认值为false
 */
setRecycleMode(boolean destroyItemWhenNeeded)
```

set jump to next/priv item

设置跳到下一个、上一个
```java
//因为原生的setCurrentItem方法在循环跳转的临界点会存在歧义，添加此方法
setNextItem()
setPrivItem()
```
![ScreenRecord_2016-05-13-13-49-26.gif](http://upload-images.jianshu.io/upload_images/2067811-9ea3f75d1240da4b.gif?imageMogr2/auto-orient/strip)
