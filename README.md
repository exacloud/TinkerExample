# TinkerExample
java -jar `tinker-patch-cli-1.7.9.jar` -old `old.apk` -new `new.apk` -config `tinker_config.xml`

下载 [tinker-patch-cli-1.7.9.jar](https://github.com/exacloud/TinkerExample/blob/master/raw/tinker-patch-cli-1.7.9.jar)

下载 [tinker_config.xml](https://github.com/exacloud/TinkerExample/blob/master/raw/tinker_config.xml)



`old.apk` 为基线包，就是你希望打补丁的包

`new.apk` 为修复后的包，以上操作会比对new.apk和old.apk，然后根据差异生成patch，所以新老apk要用同一套混淆规则。



如果需要对old.apk进行加固，则需要设置`tinker_config.xml`中的`<isProtectedApp value="true"/>`

