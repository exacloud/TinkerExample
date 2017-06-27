# TinkerExample
### 接入

1，创建Application

```
public final class TestApplication extends TinkerApplication {
    public TestApplication() {
        super(
                //tinkerFlags, which types is supported
                //dex only, library only, all support
                ShareConstants.TINKER_ENABLE_ALL,
                // This is passed as a string so the shell application does not
                // have a binary dependency on your ApplicationLifeCycle class.
                "com.qunhe.tinkerexample.TestApplicationLike");
    }
}
```

在AndroidManifest.xml的application标签中设定以上TestApplication为name，作为真正的本应用真正的Appliaction。同时在项目中实现`com.qunhe.tinkerexample.TestApplicationLike`，作为代理实现Applicaiton功能。`TestApplicationLike`继承`DefaultApplicationLike`。



因为以上真Application利用反射来获得代理Application，所以在Proguard中需要添加如下混淆规则

```
# hotfix
-keep class com.tencent.tinker.**{*;}
-keep public class * extends com.tencent.tinker.loader.app.DefaultApplicationLike
```



**注：以上做法是为了让我们自己实现的代理Appliaction也可以进行热修复。因为App启动时首先加载的Application是经过Tinker包装的，而我们实现的代理Application就沦为了普通类**



2，加入TINKER_ID

在AndroidManifest.xml中加入一下数据

```
<meta-data
   android:name="TINKER_ID"
   android:value="${TINKER_ID}" />
```

然后在build.gradle中加入

```
manifestPlaceholders = ["TINKER_ID":"tinker_id_you_set"]
```

这边你可以把这串代码放到比如`android { defaultConfig { }}`下，ID你可以设置成任何你想要的，推荐使用git或者hg的提交记录ID作为TINKER_ID。这样能保证每个APP版本的TINKER_ID唯一。



**注：TINKER_ID作为补丁和某个APP的匹配标志。基于某个TINKER_ID的APP版本生成的补丁包也具有该TINKER_ID，而该补丁包只能运行在该APP版本上**



3，MultiDex

如果你的APP使用了MultiDex技术，则需要做以下操作

（1）因为你的代理Application需要继承`DefaultApplicationLike`而无法继承`MultiDexApplication`，所以需要在`onBaseContextAttached`实现`MultiDex.install(base);`。

```
@Override
public void onBaseContextAttached(final Context base) {
    super.onBaseContextAttached(base);
    MultiDex.install(base);
}
```

（2）另外使用MultiDex技术后，因为系统会先加载主Dex，而补丁机制是需要在APP开始启动就首先加载的，所以我们需要保证tinker相关的类被放入到主dex中。需要在`build.gradle`中加入以下内容。

```
multiDexKeepProguard file('keep_in_main_dex.pro')
```

就如添加混淆规则一样。该规则会保证其中的类被分配到主dex中。

下载：[keep_in_main_dex.pro](https://github.com/exacloud/TinkerExample/blob/master/raw/keep_in_main_dex.pro)



4，优化

为了防止两次打的包产生一些不必要的差异，我们可以在build.gradle中加入以下优化。

（1）打开jumboMode，防止由于字符串增多导致force-jumbol，导致更多的变更，这样可以减少patch体积

```
dexOptions {
    jumboMode true
}
```

（2）去除png自动优化，用以减少资源比对的差异

```
aaptOptions{
    cruncherEnabled false
}
```

（3）自动保存生成的apk，R.txt，mapping.txt到某个目录

```
apply from: rootProject.file('app/patch.gradle')
```

下载：[patch.gradle](https://github.com/exacloud/TinkerExample/blob/master/raw/patch.gradle)



#### 打补丁

java -jar `tinker-patch-cli-1.7.9.jar` -old `old.apk` -new `new.apk` -config `tinker_config.xml`

下载 [tinker-patch-cli-1.7.9.jar](https://github.com/exacloud/TinkerExample/blob/master/raw/tinker-patch-cli-1.7.9.jar)

下载 [tinker_config.xml](https://github.com/exacloud/TinkerExample/blob/master/raw/tinker_config.xml)



`old.apk` 为基线包，就是你希望打补丁的包

`new.apk` 为修复后的包，以上操作会比对new.apk和old.apk，然后根据差异生成patch，所以新老apk要用同一套混淆规则。



如果需要对old.apk进行加固，则需要设置`tinker_config.xml`中的`<isProtectedApp value="true"/>`


####参考资料
[Tinker：技术的初心与坚持](https://mp.weixin.qq.com/s?__biz=MzAwNDY1ODY2OQ==&mid=2649286483&idx=1&sn=81dffab7f6407eff9d5742d2b483c455&chksm=8334c3d1b4434ac7e153ce214ef30ae474df0795b38a024c38a7def37c12b32eb051fdbd5865#rd)
[Android热修复技术选型——三大流派解析](http://mp.weixin.qq.com/s/uY5N_PSny7_CHOgUA99UjA?spm=a3c0d.7629140.0.0.qzzjPi)

