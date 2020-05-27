package co.wckd.lobbyessentials.lifecycle;

import co.wckd.boilerplate.lifecycle.Lifecycle;
import co.wckd.lobbyessentials.LobbyEssentialsPlugin;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Getter
public class FileLifecycle extends Lifecycle {

    private final LobbyEssentialsPlugin plugin;
    private final File dataFolder;
    private File configFile;
    private FileConfiguration config;

    public FileLifecycle(LobbyEssentialsPlugin plugin) {
        this.plugin = plugin;
        this.dataFolder = plugin.getDataFolder();
    }

    @Override
    public void enable() {
        loadFiles();
    }

    private void loadFiles() {

        if(!dataFolder.exists())
            dataFolder.mkdirs();

        configFile = new File(dataFolder, "config.yml");
        if(!configFile.exists())
            copyResource("config.yml", configFile);

        config = YamlConfiguration.loadConfiguration(configFile);

    }

    private void copyResource(String name, File to) {
        try {
            if (!to.exists())
                to.createNewFile();

            InputStream in = plugin.getResource(name);
            OutputStream out = new FileOutputStream(to);
            byte[] buf = new byte[1024];

            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            out.close();
            in.close();
        } catch (Exception exception) {
        }
    }

}
