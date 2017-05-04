package com.qunhe.tinkerexample;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * @author dq
 */
public class ExampleApplication extends TinkerApplication {

    public ExampleApplication() {
        super(
                //tinkerFlags, which types is supported
                //dex only, library only, all support
                ShareConstants.TINKER_ENABLE_ALL,
                // This is passed as a string so the shell application does not
                // have a binary dependency on your ApplicationLifeCycle class.
                "com.qunhe.tinkerexample.ExampleApplicationLike");
    }
}
