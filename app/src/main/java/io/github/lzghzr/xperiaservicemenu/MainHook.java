package io.github.lzghzr.xperiaservicemenu;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MainHook implements IXposedHookLoadPackage {
  @Override
  public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
    if (lpparam.packageName.equals("com.sonyericsson.android.servicemenu")) {
      Class<?> availabilityClass = XposedHelpers.findClass("com.sonyericsson.android.servicemenu.ServiceMenuAvailabilityManager", lpparam.classLoader);
      XposedBridge.hookAllMethods(availabilityClass,
          "isServiceMenuFullyAvailable",
          new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
              return true;
            }
          });
    }
  }
}
