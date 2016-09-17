package rafiz.sooham.immerse.core;

import com.orhanobut.logger.Logger;

public class Core {

    private static boolean INITIALIZED = false;

    public static void INIT(){
        if (INITIALIZED){
            Logger.init("DEBUG").hideThreadInfo().methodCount(2);
        }
    }

}
