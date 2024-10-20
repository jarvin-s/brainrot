package com.brainrot;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.MenuEntry;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@PluginDescriptor(
	name = "Brainrot",
    description = "Adds your favourite brainrot to your game",
    tags = {"brainrot"}
)
public class BrainrotPlugin extends Plugin
{
	private final Map<String, String> optionReplacements = new HashMap<>();

	@Override
	protected void startUp() throws Exception
	{
		optionReplacements.put("Talk-to", "Talk-tuah");
		optionReplacements.put("Eat", "Fanum tax");
		optionReplacements.put("Attack", "Ruin vibes");
	}

	@Inject
	private Client client;

	@Inject
	private BrainrotConfig config;

	@Subscribe
	public void onMenuEntryAdded(MenuEntryAdded event)
	{
		MenuEntry entry = event.getMenuEntry();

		String replacement = optionReplacements.get(entry.getOption());
		if (replacement != null)
		{
			entry.setOption(replacement);
		}
	}

	@Provides
	BrainrotConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BrainrotConfig.class);
	}
}
