package dev.jumpingpxl.addons.nobob.core;

import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.ConfigName;

@SuppressWarnings("FieldMayBeFinal")
@ConfigName("settings")
public class NoBobConfiguration extends Config {

  @SwitchSetting
  private boolean enabled = true;

  public boolean isEnabled() {
    return this.enabled;
  }
}
