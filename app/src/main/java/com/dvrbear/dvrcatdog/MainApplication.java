package com.dvrbear.dvrcatdog;

import android.app.Application;

import com.dvrbear.dvrcatdog.controllers.GlobalController;
import com.dvrbear.dvrcatdog.controllers.NavigatorController;

public class MainApplication extends Application {

  private GlobalController globalController;
  private NavigatorController navigatorController;

  public NavigatorController getNavigatorController(){
    if(navigatorController == null){
      navigatorController = new NavigatorController();
    }
    return navigatorController;
  }

  public GlobalController getGlobalController(){
    if(globalController == null){
      globalController = new GlobalController();
    }
    return globalController;
  }
}
