package xyz.yooniks.messagevalidator;

import java.util.Objects;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MessageValidatorPlugin extends JavaPlugin {

  @Override
  public void onEnable() {
    this.saveDefaultConfig();

    final PluginManager pluginManager = this.getServer().getPluginManager();
    if (pluginManager.getPlugin("CasualProtector") != null) {
      this.getLogger().info("You have already installed CasualProtector, MessageValidator is useless,"
          + " CasualProtector already has this module.");
      pluginManager.disablePlugin(this);
      return;
    }
    else {
      this.getLogger().info("Looks like someone doesn't have CasualProtector.. If you want to be protected against hackers, bots, "
          + "exploits please bought our plugin only for 3$! https://www.spigotmc.org/resources/%E2%80%8D%E2%9C%88-85-discount-casualprotector-protect-and-secure-your-server-antibot-anticrash-1-7-x-1-14-x.59866/");
    }

    final MessagesValidator messagesValidator = new MessagesValidator(
        this.getLogger(),
        this.getConfig().getCharacterList("charsToSkip"),
        this.getConfig().getInt("cancelWhenCountGreaterThan"),
        ChatColor.translateAlternateColorCodes('&',
            Objects.requireNonNull(this.getConfig().getString("cancelledMessage"))));

    this.getServer().getPluginManager()
        .registerEvents(new InvalidMessageListener(messagesValidator), this);
  }

}
