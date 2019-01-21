import dbus.LauncherEntry;
import java.io.IOException;
import java.util.Scanner;
import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.connections.impl.DBusConnection.DBusBusType;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusSigHandler;
import org.freedesktop.dbus.messages.DBusSignal;

public class Application implements DBusSigHandler {

  private static final String MESSAGE = "app: %s, count: %d, visible: %s";

  public static void main(String[] args) {
    new Application().start();
  }

  public void start() {
    try (DBusConnection connection = DBusConnection.getConnection(DBusBusType.SESSION)) {
      connection.addSigHandler(LauncherEntry.Update.class, this);
      new Scanner(System.in).nextLine();
    } catch (DBusException | IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void handle(DBusSignal s) {
    if (s instanceof LauncherEntry.Update) {
      LauncherEntry.Update u = (LauncherEntry.Update) s;
      System.out.println(String.format(MESSAGE, u.getAppName(), u.getCount(), u.isVisible()));
    } else {
      System.out.println(s);
    }
  }

}
