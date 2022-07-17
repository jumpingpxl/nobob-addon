package dev.jumpingpxl.addons.nobob.core.listener;

import com.google.inject.Inject;
import dev.jumpingpxl.addons.nobob.core.NoBob;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.network.server.NetworkLoginEvent;

public class NetworkLoginListener {

  private final NoBob addon;

  @Inject
  private NetworkLoginListener(NoBob addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onNetworkLogin(NetworkLoginEvent event) {
    this.addon.sendNotification();
  }
}
