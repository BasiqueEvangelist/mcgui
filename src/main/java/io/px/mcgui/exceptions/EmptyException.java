/*
 * MCGui is licensed under the Mozilla Public License 2.0
 * Failure to follow this license will result in further action.
 */

package io.px.mcgui.exceptions;

public class EmptyException extends Exception {
    @Override
    public String toString() {
        return "The mcui file was empty!";
    }
}
