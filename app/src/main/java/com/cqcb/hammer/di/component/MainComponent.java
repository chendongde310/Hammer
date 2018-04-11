package com.cqcb.hammer.di.component;

import com.synews.hammer.di.scope.ActivityScope;

import dagger.Component;

import com.synews.hammer.di.component.AppComponent;

import com.cqcb.hammer.di.module.MainModule;

import com.cqcb.hammer.mvp.ui.activity.MainActivity;
import com.cqcb.hammer.mvp.ui.fragment.MainFragment;

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);

    void inject(MainFragment fragment);
}