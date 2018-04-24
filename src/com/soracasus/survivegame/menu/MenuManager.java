package com.soracasus.survivegame.menu;

public class MenuManager {

	private static boolean active;

	private static Menu currentMenu;

	static {
		active = false;
		currentMenu = null;
	}

	public static void setMenu (Menu menu) {
		currentMenu = menu;
	}

}
