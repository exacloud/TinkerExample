# you can copy the tinker keep rule at
# build/intermediates/tinker_intermediates/tinker_multidexkeep.pro

-keep class com.tencent.tinker.loader.TinkerTestAndroidNClassLoader {
    <init>();
}

-keep public class * extends com.tencent.tinker.loader.app.DefaultApplicationLike {
    <init>();
}

-keep public class * implements com.tencent.tinker.loader.app.ApplicationLifeCycle {
    <init>();
}

-keep public class * extends com.tencent.tinker.loader.TinkerLoader {
    <init>();
}

-keep public class * extends com.tencent.tinker.loader.app.TinkerApplication {
    <init>();
}

-keep class com.tencent.tinker.loader.** {
    <init>();
}
# here, it is your own keep rules.
# you must be careful that the class name you write won't be proguard
# but the tinker class above is OK, we have already keep for you!