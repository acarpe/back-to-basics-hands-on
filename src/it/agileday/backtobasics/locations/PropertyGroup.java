package it.agileday.backtobasics.locations;
import it.agileday.backtobasics.Player;

import java.util.ArrayList;
import java.util.List;


public class PropertyGroup {

	private List<Property> properties;

	public PropertyGroup() {
		this.properties = new ArrayList<Property>();
	}

	public void add(Property property) {
		properties.add(property);
	}

	public boolean ownedBy(Player player) {
		boolean result = false;
		for (Property property : properties) {
			result = player.equals(property.owner());
		}
		return result;
	}

	public int howManyPropertiesOwnedBy(Player player) {
		int numberOfPropertiesOwnedByPlayer = 0;
		for (Property property : properties) {
			if(player.equals(property.owner())) {
				numberOfPropertiesOwnedByPlayer++;
			}
		}
		return numberOfPropertiesOwnedByPlayer;
	}
}
