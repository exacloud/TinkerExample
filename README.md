# TinkerExample
java -jar `tinker-patch-cli-1.7.9.jar` -old `old.apk` -new `new.apk` -config `tinker_config.xml`

下载 [tinker-patch-cli-1.7.9.jar](https://github.com/exacloud/TinkerExample/blob/master/raw/tinker-patch-cli-1.7.9.jar)

下载 [tinker_config.xml](https://github.com/exacloud/TinkerExample/blob/master/raw/tinker_config.xml)



`old.apk` 为基线包，就是你希望打补丁的包

`new.apk` 为修复后的包，以上操作会比对new.apk和old.apk，然后根据差异生成patch，所以新老apk要用同一套混淆规则。



如果需要对old.apk进行加固，则需要设置`tinker_config.xml`中的`<isProtectedApp value="true"/>`


####参考资料
[Tinker：技术的初心与坚持](https://mp.weixin.qq.com/s?__biz=MzAwNDY1ODY2OQ==&mid=2649286483&idx=1&sn=81dffab7f6407eff9d5742d2b483c455&chksm=8334c3d1b4434ac7e153ce214ef30ae474df0795b38a024c38a7def37c12b32eb051fdbd5865#rd)
[Android热修复技术选型——三大流派解析](http://mp.weixin.qq.com/s/uY5N_PSny7_CHOgUA99UjA?spm=a3c0d.7629140.0.0.qzzjPi)

