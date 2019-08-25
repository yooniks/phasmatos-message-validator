package xyz.yooniks.messagevalidator;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class InvalidMessageListener implements Listener {

  private final MessagesValidator validator;

  InvalidMessageListener(MessagesValidator validator) {
    this.validator = validator;
  }

  @EventHandler(ignoreCancelled = true)
  public void onChat(AsyncPlayerChatEvent event) {
    this.validator.handleEvent(MessageableEventWrapper.Builder.of(event));
  }

  @EventHandler(ignoreCancelled = true)
  public void onChat(PlayerCommandPreprocessEvent event) {
    this.validator.handleEvent(MessageableEventWrapper.Builder.of(event));
  }

}