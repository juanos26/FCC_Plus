package src.Model;

import java.util.ArrayList;

public class CurrentDevice {
    private String OS;
    private int[] resolution;
    private int resolutionHeight;
    private String chosenBrowser;
    private boolean darkMode = false;
    private String language;
    private int timeZone;

    public CurrentDevice() {
        this.OS = getCurrentOS();
        this.resolution = getCurrentResolution();
    }

    private String getCurrentOS() {
        System.out.println("Current OS: " + System.getProperty("os.name"));
        return System.getProperty("os.name");
    }

    private int[] getCurrentResolution() {
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println("Current resolution: " + screenSize.width + " by " + screenSize.height);
        return new int[]{screenSize.width, screenSize.height};
    }
}
