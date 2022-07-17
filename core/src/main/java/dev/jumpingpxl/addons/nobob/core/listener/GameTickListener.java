package dev.jumpingpxl.addons.nobob.core.listener;

import com.google.inject.Inject;
import dev.jumpingpxl.addons.nobob.core.NoBob;
import java.util.Objects;
import net.labymod.api.client.Minecraft;
import net.labymod.api.client.entity.player.ClientPlayer;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.lifecycle.GameTickEvent;

public class GameTickListener {

  private final NoBob addon;
  private boolean previousTickBobbing;

  @Inject
  private GameTickListener(NoBob addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onGameTick(GameTickEvent event) {
    if (!this.addon.configuration().isEnabled()) {
      return;
    }

    Minecraft minecraft = this.addon.labyAPI().getMinecraft();
    boolean currentTickBobbing = minecraft.getOptions().isBobbing();
    if (this.previousTickBobbing && !currentTickBobbing) {
      this.addon.sendNotification();
    }

    this.previousTickBobbing = currentTickBobbing;

    ClientPlayer clientPlayer = minecraft.getClientPlayer();
    if (Objects.nonNull(clientPlayer)) {
      clientPlayer.setDistanceWalked(0.0F);
    }
  }
}
