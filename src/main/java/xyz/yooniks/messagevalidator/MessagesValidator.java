package xyz.yooniks.messagevalidator;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.bukkit.entity.Player;

class MessagesValidator {

  private final Logger logger;
  private final List<Character> charsToSkip;
  private final int cancelWhenGreaterThan;
  private final String cancelledMessage;

  MessagesValidator(Logger logger, List<Character> charsToSkip, int cancelWhenGreaterThan,
      String cancelledMessage) {
    this.logger = logger;
    this.charsToSkip = charsToSkip;
    this.cancelWhenGreaterThan = cancelWhenGreaterThan;
    this.cancelledMessage = cancelledMessage;
  }

  void handleEvent(MessageableEventWrapper event) {
    final String message = event.getMessage();
    final Player player = event.getPlayer();
    int count = 0;

    for (int i = 0; i < message.length(); i++) {
      final char current = message.charAt(i);
      if (this.charsToSkip.contains(current)) {
        continue;
      }
      if (String.valueOf(current).getBytes().length > 1) {
        if (++count > this.cancelWhenGreaterThan) {

          this.logger.info(String.format("MessageValidator -> %s's message has been blocked! Message content: %s",
              player.getName(), message));

          player.sendMessage(this.cancelledMessage);
          event.cancel();
          return;
        }
      }
    }
  }

}
