// 代码生成时间: 2025-11-04 08:46:43
package com.smarthome;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Managed Bean to control smart home devices.
 * This class handles the logic to turn devices on or off.
 */
@ManagedBean
@RequestScoped
public class SmartHomeControl implements Serializable {

    // Device status map to keep track of device states
    private Map<String, Boolean> deviceStatusMap = new HashMap<>();

    public SmartHomeControl() {
        // Initialize devices with default states
        deviceStatusMap.put("Light", false);
        deviceStatusMap.put("Thermostat", false);
        deviceStatusMap.put("SecuritySystem", false);
    }

    /**
     * Toggles the state of a device.
     * @param deviceName the name of the device to toggle
     */
    public void toggleDevice(String deviceName) {
        if (deviceStatusMap.containsKey(deviceName)) {
            deviceStatusMap.put(deviceName, !deviceStatusMap.get(deviceName));
        } else {
            // Handle error if device is not found
            throw new IllegalArgumentException("Device not found: " + deviceName);
        }
    }

    /**
     * Checks the status of a device.
     * @param deviceName the name of the device to check
     * @return the current state of the device
     */
    public Boolean isDeviceOn(String deviceName) {
        return deviceStatusMap.getOrDefault(deviceName, false);
    }

    // Getters and setters
    public Map<String, Boolean> getDeviceStatusMap() {
        return deviceStatusMap;
    }

    public void setDeviceStatusMap(Map<String, Boolean> deviceStatusMap) {
        this.deviceStatusMap = deviceStatusMap;
    }
}
