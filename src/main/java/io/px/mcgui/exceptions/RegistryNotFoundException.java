/*
 * MCGui is licensed under the Mozilla Public License 2.0
 * Failure to follow this license will result in further action.
 */

package io.px.mcgui.exceptions;

public class RegistryNotFoundException extends Exception {
    /**
     * Usually the key that wasn't found.
     */
    public String message;
    public RegistryNotFoundException(String msg) {
        message = msg;
    }

    @Override
    public String toString() {
        return "The key was not in the Registry - " + message;
    }
}
