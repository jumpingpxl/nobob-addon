package dev.jumpingpxl.addons.nobob.core;

import com.google.inject.Singleton;
import dev.jumpingpxl.addons.nobob.core.commands.ToggleMinecraftBobbingCommand;
import dev.jumpingpxl.addons.nobob.core.listener.GameTickListener;
import dev.jumpingpxl.addons.nobob.core.listener.NetworkLoginListener;
import java.util.Objects;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.entity.player.ClientPlayer;
import net.labymod.api.models.addon.annotation.AddonListener;

@Singleton
@AddonListener
public class NoBob extends LabyAddon<NoBobConfiguration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();

    this.registerCommand(ToggleMinecraftBobbingCommand.class);

    this.registerListener(GameTickListener.class);
    this.registerListener(NetworkLoginListener.class);

    this.sendNotification();
  }

  @Override
  protected Class<NoBobConfiguration> configurationClass() {
    return NoBobConfiguration.class;
  }

  public Component prefix() {
    return Component.text("[", NamedTextColor.GRAY)
        .append(Component.text("NoBob", NamedTextColor.GOLD))
        .append(Component.text("] ", NamedTextColor.GRAY));
  }

  public void sendNotification() {
    ClientPlayer clientPlayer = this.labyAPI().minecraft().clientPlayer();
    if (Objects.isNull(clientPlayer) || !this.configuration().isEnabled() || this.labyAPI()
        .minecraft().options().isBobbing()) {
      return;
    }

    String key = "nobob.messages.activateBobbing.";
    Component component = Component.text("").append(this.prefix())
        .append(Component.translatable(key + "important", NamedTextColor.DARK_RED,
            TextDecoration.BOLD))
        .append(Component.translatable(key + "description", NamedTextColor.RED))
        .append(Component.translatable(key + "setting", NamedTextColor.RED)
            .clickEvent(ClickEvent.runCommand("/nobob+toggleminecraftbobbing")))
        .append(Component.translatable(key + "click", NamedTextColor.GREEN, TextDecoration.BOLD)
            .clickEvent(ClickEvent.runCommand("/nobob+toggleminecraftbobbing")));

    this.displayMessage(component);
  }
}
