package dev.jumpingpxl.addons.nobob.core;

import dev.jumpingpxl.addons.nobob.core.commands.ToggleMinecraftBobbingCommand;
import dev.jumpingpxl.addons.nobob.core.listener.GameTickListener;
import dev.jumpingpxl.addons.nobob.core.listener.NetworkLoginListener;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.Minecraft;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.event.ClickEvent;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.client.component.format.TextDecoration;
import net.labymod.api.client.entity.player.ClientPlayer;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class NoBob extends LabyAddon<NoBobConfiguration> {

  private static final Component PREFIX = Component.text("[", NamedTextColor.GRAY)
      .append(Component.text("NoBob", NamedTextColor.GOLD))
      .append(Component.text("] ", NamedTextColor.GRAY));

  @Override
  protected void enable() {
    this.registerSettingCategory();

    this.registerCommand(new ToggleMinecraftBobbingCommand(this).messagePrefix(PREFIX));

    this.registerListener(new GameTickListener(this));
    this.registerListener(new NetworkLoginListener(this));
  }

  @Override
  protected Class<NoBobConfiguration> configurationClass() {
    return NoBobConfiguration.class;
  }

  public void sendNotification() {
    Minecraft minecraft = this.labyAPI().minecraft();
    ClientPlayer clientPlayer = minecraft.clientPlayer();
    if (clientPlayer == null || !this.configuration().enabled().get() || minecraft.options()
        .isBobbing()) {
      return;
    }

    String key = "nobob.messages.activateBobbing.";
    Component component = Component.empty()
        .append(PREFIX)
        .append(Component.translatable(
            key + "important",
            NamedTextColor.DARK_RED,
            TextDecoration.BOLD
        ))
        .append(Component.translatable(
            key + "description",
            NamedTextColor.RED
        ).append(Component.translatable(key + "setting")
            .clickEvent(ClickEvent.runCommand("/nobob+toggleminecraftbobbing"))
            .append(Component.translatable(
                key + "click",
                NamedTextColor.GREEN,
                TextDecoration.BOLD
            ))
        ));

    this.displayMessage(component);
  }
}
