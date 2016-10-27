package custom_tabs;

/**
 * Created by timot on 10/12/2016.
 */

import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsSession;

import java.lang.ref.WeakReference;

/**
 * A class that keeps tracks of the current {@link CustomTabsSession} and helps other components of
 * the app to get access to the current session.
 */
public class SessionHelper {
    private static WeakReference<CustomTabsSession> sCurrentSession;

    /**
     * @return The current {@link CustomTabsSession} object.
     */
    public static @Nullable CustomTabsSession getCurrentSession() {
        return sCurrentSession == null ? null : sCurrentSession.get();
    }

    /**
     * Sets the current session to the given one.
     * @param session The current session.
     */
   public static void setCurrentSession(CustomTabsSession session) {
        sCurrentSession = new WeakReference<>(session);
    }
}
