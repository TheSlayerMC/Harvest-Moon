package net.sham.hmfomt.client.event;

import net.sham.hmfomt.core.HarvestMoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;
import java.util.List;

public class InternetHandler {

    public static boolean isUpdateAvailable() throws IOException {
        BufferedReader versionFile = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/TheSlayerMC/Harvest-Moon/main/version").openStream()));
        String curVersion = versionFile.readLine();
        versionFile.close();
        return !curVersion.contains(HarvestMoon.MOD_VERSION);
    }

    public static String getUpdateVersion() throws IOException {
        BufferedReader versionFile = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/TheSlayerMC/Harvest-Moon/main/version").openStream()));
        return versionFile.readLine();
    }

    public static boolean isOnline() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while(interfaces.hasMoreElements()) {
            NetworkInterface interf = interfaces.nextElement();
            if(interf.isUp() && !interf.isLoopback()) {
                List<InterfaceAddress> adrs = interf.getInterfaceAddresses();
                for(InterfaceAddress adr : adrs) {
                    InetAddress inadr = adr.getAddress();
                    if (inadr instanceof Inet4Address)
                        return true;
                }
            }
        }
        return false;
    }
}