package xyz.yooniks.messagevalidator;

import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public interface MessageableEventWrapper {

  String getMessage();

  void cancel();

  Player getPlayer();

  class Builder {
    static MessageableEventWrapper of(PlayerCommandPreprocessEvent event) {
      return new MessageableEventWrapper() {
        @Override
        public String getMessage() {
          return event.getMessage();
        }

        @Override
        public void cancel() {
          event.setCancelled(true);
        }

        @Override
        public Player getPlayer() {
          return event.getPlayer();
        }
      };
    }
    static MessageableEventWrapper of(AsyncPlayerChatEvent event) {
      return new MessageableEventWrapper() {
        @Override
        public String getMessage() {
          return event.getMessage();
        }

        @Override
        public void cancel() {
          event.setCancelled(true);
        }

        @Override
        public Player getPlayer() {
          return event.getPlayer();
        }
      };
    }
  }

}
