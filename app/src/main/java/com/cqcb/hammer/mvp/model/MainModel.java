package com.cqcb.hammer.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.synews.hammer.integration.IDataManager;
import com.synews.hammer.mvp.BaseModel;

import com.synews.hammer.di.scope.ActivityScope;

import javax.inject.Inject;

import com.cqcb.hammer.mvp.contract.MainContract;


@ActivityScope
public class MainModel extends BaseModel implements MainContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public MainModel(IDataManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}