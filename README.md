# Hammer
Android component-based development framework

# What is a Chuizi
「锤子」是一个Android组件化快速开发框架
### 特征
* 组件化开发通用框架, 高度解耦，方便配置

* 提供Base 基类(BaseActivity, BaseFragment, BaseApplication )

* 提供MVP 基类(IModel, IVIew, IPresenter )并提供一键生成锤子模版

* 高度可自定义化 ，可以自由的对 Retoift, Okhttp, RxCache, Gson 等框架的特有属性进行自定义化配置, 可在不修改框架源码的情况下向 BaseApplication, BaseActivity, BaseFragment 的对应生命周期中插入任意代码, 并且框架独有的 ConfigModule 配置类, 可在不修改框架源码的情况下为框架轻松扩展任何新增功能

* 提供了第三方框架（Retoift, Okhttp, RxCache, Gson 等）的默认的配置，通过简单配置即可上手开发

* 封装RxLifeCycle , 可在不继承 RxLifeCycle 提供的 Activity 和 Fragment 的情况下, 正常使用 RxLifeCycle 的所有功能, 且使用方式不变

* 提供全局配置Module , 可用 Dagger2 在任意位置注入自定义参数, 可轻松扩展任意自定义参数

* 全局使用 Dagger2 管理 (将所有模块使用 Dagger2 连接起来)

* 提供全局监听整个 App 所有 Activity 以及 Fragment 的生命周期 (包括三方库)配置方法, 并可向其生命周期内插入任意代码 

* 提供【AppHttpHandler】实现全局监听 Http Request(请求参数, Headers ...), Response (服务器返回的结果, Headers, 耗时 ...)等信息(包括 Glide 的请求), 可解析 json 后根据状态码做相应的全局操作以及数据加密, Cookie 管理等操作

* 提供RxTransformer转换器简化网络请求生命周期绑定和线程转换操作 

* 封装 【AppManager】实现全局管理所有 Activity (包括三方库的 Activity), 可实现在整个 App 任意位置, 退出所有 Activity, 以及拿到前台 Activity 做相应的操作(如您可以在 App 任何位置做弹出 Dialog 的操作)

* 封装【DataManager】统一管理网络请求和数据缓存，以及快捷的本地数据存储

* 封装全局 Rxjava 错误处理, 错误后自动重试, 捕捉整个应用的所有错误（手动调用）  

* 封装图片加载类【 ImageLoader 】使用策略模式和建造者模式, 方便切换图片加载框架和功能扩展 （提供了glide加载策略作为默认方案）

* 网络请求日志打印封装(提供解析后的服务器的请求信息和服务器的响应信息, 按可自定义的任意格式输出打印日志)

* 组件的缓存机制封装(框架内可缓存内容的组件都提供有接口方便自定义)

* 提供锤子工具集（常用工具类，UI，字符，图片，日志，压缩，判断式等）





TODO
*  添加更多的UI部件和适配器

*  封装leakcanary  
### 使用的库
1. 使用MVP架构
2. Dagger2 Google出品的依赖注入框架，通过Apt编译时生成代码，性能优于使用运行时反射技术的依赖注入框架.
3. RxJava 提供优雅的响应式Api解决异步请求以及事件处理.

4. RxAndroid 为Android提供响应式Api.

5. Rxlifecycle 在Android上使用rxjava都知道的一个坑，就是生命周期的解除订阅，这个框架通过绑定activity和fragment的生命周期完美解决.

6. RxCache 是使用注解为Retrofit加入二级缓存(内存,磁盘)的缓存库.
 
7. RxPermissions 用于处理Android运行时权限的响应式库.

8. Retrofit Okhttp封装库，极大的减少了http请求的代码和步骤.

9. Okhttp 网络请求库

10. GsonGoogle 官方的Json Convert框架.

11. Butterknife view注入框架.

12. AndroidEventBus 一个轻量级使用注解的Eventbus.

13. Timber Log框架容器

14. Glide 图片加载库

15. Hawk 本地轻量储存库，只需一行代码 , 能存任何数据类型

~~16. Green Dao 数据库~~

#开发准备
----
##### 1.导入框架
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.chendongde310:Hammer:0.0.2'
	}

##### 2.引用config.gradle
 本框架提供一个含有大量第三方库的 **config.gradle** 文件 (里面的所有第三方库并不会全部被引入到项目中, 只是作为变量方便项目中多个位置进行引用, 特别适用于多 Module 的项目), 用于第三方库的版本管理, 将 **config.gradle**复制进根目录, 并在项目的顶级 **build.gradle**中添加如下代码引用它

    apply from: "config.gradle" //这里表示引用config.gradle文件

##### 2.5推荐app下build.gradle配置信息（可选）

    android {
        compileSdkVersion rootProject.ext.android["compileSdkVersion"]
        buildToolsVersion rootProject.ext.android["buildToolsVersion"]
        useLibrary 'org.apache.http.legacy'

        defaultConfig {
            minSdkVersion rootProject.ext.android["minSdkVersion"]
            targetSdkVersion rootProject.ext.android["targetSdkVersion"]
            versionCode rootProject.ext.android["versionCode"]
            versionName rootProject.ext.android["versionName"]
        }
    }

##### 3.向app下build.gradle添加依赖（必须，否则报错）
    dependencies {
        annotationProcessor rootProject.ext.dependencies["butterknife-compiler"] //Butterknife 
        annotationProcessor rootProject.ext.dependencies["dagger2-compiler"]//依赖插件
    }

##### 4.配置AndroidManifest
* 添加全局配置ConfigModule（如何配置之后说明）
* 指定application


    <application
        android:name="com.synews.hammer.base.BaseApplication"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:theme="@style/AppTheme">

        <!-- 全局配置 -->
        <meta-data
            android:name="com.synews.hammer.AppConfig"
            android:value="ConfigModule" />

    </application>

##### 5.混淆（可选）
自带一份锤子使用框架的混淆文件（proguard-rules.pro），如使用混淆，请将混淆配置添加到自己的混淆配置中


# 快速开始
-----
##### 全局配置
创建AppConfig实现ConfigModule接口

    public class AppConfig implements ConfigModule {

        @Override
        public void applyOptions(Context context, AppConfigModule.Builder builder) {
            //全局配置
        }

        @Override
        public void injectAppLifecycle(Context context, List<AppLifecycles> lifecycles) {

            //向Application的生命周期中注入一些自定义逻辑
        }

        @Override
        public void injectActivityLifecycle(Context context, List<Application.ActivityLifecycleCallbacks> lifecycles) {
            //向Activity的生命周期中注入一些自定义逻辑
        }

        @Override
        public void injectFragmentLifecycle(Context context, List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {
          //向Fragment的生命周期中注入一些自定义逻辑
        }

    }

