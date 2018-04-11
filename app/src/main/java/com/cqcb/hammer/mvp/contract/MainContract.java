package com.cqcb.hammer.mvp.contract;

import com.synews.hammer.mvp.IView;
import com.synews.hammer.mvp.IModel;


public interface MainContract {
    //对于经常使用的关于UI的方法可以定义到IView中
    interface View extends IView {

    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节
    interface Model extends IModel {

    }
}
