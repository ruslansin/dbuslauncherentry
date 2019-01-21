package dbus;

import java.util.Map;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.types.Variant;

@DBusInterfaceName("com.canonical.Unity.LauncherEntry")
public interface LauncherEntry extends DBusInterface {

  class Update extends DBusSignal {

    private final String appName;
    private final Map<String, Variant> parameters;

    // sa{sv}
    public Update(String path, String s, Map<String, Variant> asv) throws DBusException {
      super(path, s, asv);
      this.appName = s;
      this.parameters = asv;
    }

    public String getAppName() {
      return appName;
    }

    // sx
    public Long getCount() {
      return (Long) parameters.get("count").getValue();
    }

    // sb
    public Boolean isVisible() {
      return (Boolean) parameters.get("count-visible").getValue();
    }
  }
}
