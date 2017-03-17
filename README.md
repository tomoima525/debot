[![Download](https://api.bintray.com/packages/tomoima525/maven/debot/images/download.svg) ](https://bintray.com/tomoima525/maven/debot/_latestVersion)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Debot-green.svg?style=flat)](https://android-arsenal.com/details/1/2562)
[![Circle CI](https://circleci.com/gh/tomoima525/debot.svg?style=svg)](https://circleci.com/gh/tomoima525/debot)
# Debot
![debot_logo.png](art/debot_logo.png)  
A simple Android library for Debugging menu.

Debot offers a customizable debug menu for Android app development. Developers can easily add their own custom debugging features with simple steps.

![debot_4.gif](art/debot_demo.gif)

## How it works
If you are using an emulator, just press `command + M`. If you are running your dev app on a real device, shake it. The debug menu dialog will show up.

## How it looks
By default, there are debug menus below.

* Default debugging menu  
![debot-sample1.png](art/debot-sample1.png)
* Check Density  
![debot-sample4.png](art/debot-sample4.png)
* Check App ver  
![debot-sample2.png](art/debot-sample2.png)
* Show intent and Activity Info  
![debot-sample3.png](art/debot-sample3.png)
* Dev input (Automatically adds text to EditText field )  
![debot-sample5.png](art/debot-sample5.png)


## Setup
### Download
Grab Debot from Gradle:

```groovy
debugCompile 'com.tomoima.debot:debot:{latest_version}'
releaseCompile 'com.tomoima.debot:debot-no-op:{latest_version}'
```

{latest_version} is now :[![Download](https://api.bintray.com/packages/tomoima525/maven/debot/images/download.svg) ](https://bintray.com/tomoima525/maven/debot/_latestVersion)

Make sure you compile `debot-no-op` in the release build.

### Initialization
1. Call `DebotConfigurator.configureWithDefault()` at the Application's `onCreate()` class.

```java
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DebotConfigurator.configureWithDefault(this);
    }
}
```

2. Set below to any Activity you want to show the debugging menu.

```java
public class MainActivity extends AppCompatActivity{
    Debot debot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        debot = Debot.getInstance();
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            debot.showDebugMenu(this);
        }
        return super.onKeyUp(keyCode, event);
    }

}    
```

It is preferred to put these code in your BaseActivity if you have one.
That's it!

If you want the debug menu on a real device, add code below.

```java
public class MainActivity extends AppCompatActivity{
    Debot debot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        debot = Debot.getInstance();
        debot.allowShake(this);
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            debot.showDebugMenu(this);
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        debot.startSensor(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        debot.stopSensor();
    }
}
```

See the [`debot-sample` project](debot-sample) for more details.

## Custom debugging plugins
You can create your own debugging feature by developing a class which inherits `DebotStrategy`.


```java
public class MyDebotStrategy extends DebotStrategy{
    @Override
    public void startAction(@NonNull Activity activity) {
    // Do your things
    }
}
```

Then, at the Application class, call `Debot.configureWithCustomizeMenu()`


```java
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DebotStrategyBuilder builder = new DebotStrategyBuilder.Builder(context)
                .registerMenu("My debug feature", new MyDebotStrategy())
                .build();
        DebotConfigurator.configureWithCustomizeMenu(this, builder.getStrategyList());
    }
}
```

## Call a specific method from your Activity
If you want to call a specific method from your Activity, annotate the method with `@DebotAnnotation`

```java
//Your Activity
@DebotAnnotation("debugInput")  // A parameter for @DebotAnnotation should be same as the method's name
public void debugInput() {
    // Do things
}

```

Also, setup a custom debugging plugin with `DebotCallActivityMethodStrategy`

```java
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DebotStrategyBuilder builder = new DebotStrategyBuilder.Builder(context)
                .registerMenu("input", new DebotCallActivityMethodStrategy("debugInput"))
                .build();
        DebotConfigurator.configureWithCustomizeMenu(this, builder.getStrategyList());
    }
}

```

## Credit
[seismic](https://github.com/square/seismic) - Square, Inc.

## License

```
Tomoaki Imai 2017
Licensed under the Apache License, Version 2.0 (the "License").
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed
under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
OR CONDITIONS OF ANY KIND, either express or implied. See the License for
the specific language governing permissions and limitations under the License.

You agree that all contributions to this repository, in the form of fixes, 
pull-requests, new examples etc. follow the above-mentioned license.
```
