package org.example;

import java.util.HashMap;
import java.util.Map;

public class EmojiClassMapper {
    private static final Map<String, String> emojiToClassMap = new HashMap<>();

    static {
        emojiToClassMap.put("🛡️", "tank");
        emojiToClassMap.put("💉", "healer");
        emojiToClassMap.put("⚔️", "melee");
        emojiToClassMap.put("🏹", "distant");
        emojiToClassMap.put("🔮", "magie");
    }

    public static String getClassForEmoji(String emoji) {
        return emojiToClassMap.get(emoji);
    }
}
