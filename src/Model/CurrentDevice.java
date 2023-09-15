package src.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CurrentDevice {
    private String OS;
    private int[] resolution;
    private int resolutionHeight;
    private String chosenBrowser = "Chrome";
    private boolean darkMode = false;
    private String language = "English";

    public CurrentDevice() {
        this.OS = getCurrentOS();
        this.resolution = getCurrentResolution();
        this.darkMode = isDarkMode();
    }

    public String getCurrentOS() {
        System.out.println("Current OS: " + System.getProperty("os.name"));
        return System.getProperty("os.name");
    }

    public int[] getCurrentResolution() {
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println("Current resolution: " + screenSize.width + " by " + screenSize.height);
        return new int[]{screenSize.width, screenSize.height};
    }

    public boolean isDarkMode() {
        String osName = System.getProperty("os.name").toLowerCase();
        boolean darkModeStatus = false; // Default option

        if (osName.contains("mac")) {
            System.out.println("Finding macOS dark mode status");
            try {
                Process process = Runtime.getRuntime().exec("defaults read -g AppleInterfaceStyle");
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("Dark")) {
                        System.out.println("macOS is in Dark Mode");
                        darkModeStatus = true;
                    } else {
                        System.out.println("macOS is not in Dark Mode");
                        darkModeStatus = false;
                    }
                }
            } catch (IOException e) {
                System.out.println("Couldn't find macOS dark mode, defaulting to light mode");
            }
        } else if (osName.contains("win")) {
            System.out.println("Finding Windows dark mode status");
            try {
                Process process = Runtime.getRuntime().exec("reg query \"HKEY_CURRENT_USER\\Software\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize\" /v AppsUseLightTheme");
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("0x0")) {
                        System.out.println("Windows is in Dark Mode");
                        darkModeStatus = true;
                    } else {
                        System.out.println("Windows is not in Dark Mode");
                        darkModeStatus = false;
                    }
                }
            } catch (IOException e) {
                System.out.println("Couldn't find Windows dark mode, defaulting to light mode");
            }
        }

        System.out.println("Sending back dark mode status: " + darkModeStatus);
        return darkModeStatus;
    }
}
