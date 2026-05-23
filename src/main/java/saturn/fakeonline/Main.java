package saturn.fakeonline;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyPingEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.ServerPing;
import com.velocitypowered.api.scheduler.ScheduledTask;
import org.slf4j.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.LoaderOptions;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Plugin(
    id = "fakeonline",
    name = "FakeOnline",
    version = "1.0.0",
    description = "Динамический фейковый онлайн с плавающим количеством",
    authors = {"Saturn"}
)
public class Main {

    private final ProxyServer server;
    private final Logger logger;
    private final Path dataDirectory;

    private volatile int currentFakeOnline = 0;
    private int baseCount = 7;
    private int minFake = 3;
    private int maxFake = 12;
    private int slots = 20;
    private int updateInterval = 5;

    private ScheduledTask fluctuationTask;
    private final Random random = new Random();

    @Inject
    public Main(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) {
        this.server = server;
        this.logger = logger;
        this.dataDirectory = dataDirectory;
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
        loadConfig();
        startFluctuationTask();
        logger.info("FakeOnline запущен! Текущий фейк: {}", currentFakeOnline);
    }

    @Subscribe
    public void onProxyPing(ProxyPingEvent event) {
        ServerPing originalPing = event.getPing();
        int realPlayers = server.getPlayerCount();

        // Получаем оригинальное максимальное количество игроков
        int originalMax = originalPing.getPlayers()
                .map(ServerPing.Players::getMax)
                .orElse(0);

        // Подменяем онлайн и слоты
        ServerPing modifiedPing = originalPing.asBuilder()
                .onlinePlayers(realPlayers + currentFakeOnline)
                .maximumPlayers(slots > 0 ? slots : originalMax)
                .build();

        event.setPing(modifiedPing);
    }

    private void loadConfig() {
        try {
            Files.createDirectories(dataDirectory);
        } catch (IOException e) {
            logger.error("Не могу создать папку плагина", e);
            return;
        }

        File configFile = new File(dataDirectory.toFile(), "config.yml");
        if (!configFile.exists()) {
            try (InputStream in = getClass().getResourceAsStream("/config.yml")) {
                if (in != null) {
                    Files.copy(in, configFile.toPath());
                    logger.info("Создан config.yml по умолчанию");
                } else {
                    logger.warn("Ресурс /config.yml не найден, создаю пустой");
                    configFile.createNewFile();
                }
            } catch (IOException e) {
                logger.error("Ошибка создания config.yml", e);
            }
        }

        Yaml yaml = new Yaml(new SafeConstructor(new LoaderOptions()));
        try (FileInputStream fis = new FileInputStream(configFile)) {
            Map<String, Object> data = yaml.load(fis);
            if (data != null && data.containsKey("fakeOnline")) {
                @SuppressWarnings("unchecked")
                Map<String, Object> fakeSection = (Map<String, Object>) data.get("fakeOnline");
                baseCount = getInt(fakeSection, "count", 7);
                minFake = getInt(fakeSection, "min-fake", Math.max(0, baseCount - 3));
                maxFake = getInt(fakeSection, "max-fake", baseCount + 3);
                slots = getInt(fakeSection, "slots", 20);
                updateInterval = getInt(fakeSection, "update-interval", 5);

                currentFakeOnline = Math.max(minFake, Math.min(maxFake, baseCount));
                logger.info("Конфиг загружен: count={}, min={}, max={}, slots={}, interval={}с",
                        baseCount, minFake, maxFake, slots, updateInterval);
            }
        } catch (Exception e) {
            logger.error("Ошибка чтения config.yml. Использую значения по умолчанию.", e);
        }
    }

    private void startFluctuationTask() {
        if (fluctuationTask != null) {
            fluctuationTask.cancel();
        }

        fluctuationTask = server.getScheduler().buildTask(this, () -> {
            int delta = random.nextInt(3) - 1;  // -1, 0, +1
            int newFake = currentFakeOnline + delta;
            newFake = Math.max(minFake, Math.min(maxFake, newFake));

            if (newFake != currentFakeOnline) {
                currentFakeOnline = newFake;
                // Вывод в консоль при каждом изменении
                logger.info("Фейковый онлайн изменился: {}", currentFakeOnline);
            }
        }).repeat(updateInterval, TimeUnit.SECONDS).schedule();
    }

    private int getInt(Map<String, Object> map, String key, int defaultValue) {
        Object value = map.get(key);
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        return defaultValue;
    }
}