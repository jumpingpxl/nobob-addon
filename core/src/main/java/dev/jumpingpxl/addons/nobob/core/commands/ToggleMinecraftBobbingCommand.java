package dev.jumpingpxl.addons.nobob.core.commands;

import com.google.inject.Inject;
import dev.jumpingpxl.addons.nobob.core.NoBob;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.options.MinecraftOptions;

public class ToggleMinecraftBobbingCommand extends Command {

  private final NoBob addon;

  @Inject
  private ToggleMinecraftBobbingCommand(NoBob addon) {
    super("nobob+toggleminecraftbobbing");
    this.addon = addon;
  }

  @Override
  public boolean execute(String prefix, String[] arguments) {
    MinecraftOptions options = this.addon.labyAPI().minecraft().options();
    if (options.isBobbing()) {
      return true;
    }

    options.setBobbing(true);
    Component component = Component.empty().append(this.addon.prefix())
        .append(Component.translatable("nobob.messages.activated", NamedTextColor.GREEN));
    this.displayMessage(component);
    return true;
  }
}
