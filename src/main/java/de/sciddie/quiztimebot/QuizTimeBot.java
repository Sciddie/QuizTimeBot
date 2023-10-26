package de.sciddie.quiztimebot;

import de.sciddie.quiztimebot.listeners.EventListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class QuizTimeBot {

    private final ShardManager shardManager;

    public QuizTimeBot() {
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(
                System.getenv("API_KEY"))
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.customStatus("Is watching you"))
                .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_PRESENCES)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .setChunkingFilter(ChunkingFilter.ALL)
                .enableCache(CacheFlag.ROLE_TAGS);
        shardManager = builder.build();
        shardManager.addEventListener(new EventListener());
    }

    public static void main(String[] args) {
        QuizTimeBot bot = new QuizTimeBot();
    }
}