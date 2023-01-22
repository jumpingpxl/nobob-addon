package dev.jumpingpxl.addons.nobob.core.commands;

import dev.jumpingpxl.addons.nobob.core.NoBob;
import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.client.options.MinecraftOptions;

public class ToggleMinecraftBobbingCommand extends Command {

  private final NoBob addon;

  public ToggleMinecraftBobbingCommand(NoBob addon) {
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
    this.displayTranslatable("nobob.messages.activated", NamedTextColor.GREEN);
    return true;
  }
}
