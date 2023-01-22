package dev.jumpingpxl.addons.nobob.core.listener;

import dev.jumpingpxl.addons.nobob.core.NoBob;
import net.labymod.api.client.Minecraft;
import net.labymod.api.client.entity.player.ClientPlayer;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.lifecycle.GameTickEvent;

public class GameTickListener {

  private final NoBob addon;
  private boolean previousTickBobbing;

  public GameTickListener(NoBob addon) {
    this.addon = addon;
    this.previousTickBobbing = true;
  }

  @Subscribe
  public void onGameTick(GameTickEvent event) {
    Minecraft minecraft = this.addon.labyAPI().minecraft();
    boolean currentTickBobbing = minecraft.options().isBobbing();
    if (this.previousTickBobbing && !currentTickBobbing) {
      this.addon.sendNotification();
    }

    this.previousTickBobbing = currentTickBobbing;
    ClientPlayer clientPlayer = minecraft.clientPlayer();
    if (clientPlayer != null) {
      clientPlayer.setDistanceWalked(0.0F);
    }
  }
}
