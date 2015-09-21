# Debot
A simple Android library to create Debugging menu.

Debot offers features that are useful to debug Android applications. Developers can easily add their custom debugging features with simple steps.


## Setup
### Download
Grab Debot from Gradle:

```groovy
compileDev 'com.tomoima.debot:debot:1.0.0'
compileRelease 'com.tomoima.debot:no-op:1.0.0'
```

### Initialization
1. Call `DebotConfigurator.configureWithDefault()` at the Application class.

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Debot.onCreateOptionsMenu(menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Debot.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Debot.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Debot.onPause(this);
    }
}    
```

That's it! Start your app and there will be the debugging menu added on the toolbar. 

## Change Debot visibility

In some cases, You don't want to show debugging menu. You can control the visibility of Debugging menu by just calling `Debot.setVisibility()` at `onPrepareOptionsMenu`  

```java
@Override
public boolean onPrepareOptionsMenu(Menu menu) {
    Debot.setVisibility(menu, menuVisibility);
    return super.onPrepareOptionsMenu(menu);
}
```


See the [`debot-sample` project](debot-sample) for more details.

## Custom debugging plugins
You can create your own debugging feature by developing a class which inherits `DebotStrategy`.


```java
```

Then, at the Application class, call `Debot.configureWithCustomizeMenu()`


## Set up BaseActivity
 It might be better to call Debot from BaseActivity so that you don't have to care about 
 
## License