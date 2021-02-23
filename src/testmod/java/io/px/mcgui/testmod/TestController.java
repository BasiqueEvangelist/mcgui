package io.px.mcgui.testmod;

import io.px.mcgui.mcui.elements.UIButton;
import io.px.mcgui.mcui.elements.UIView;
import me.lambdaurora.spruceui.widget.SpruceButtonWidget;
import net.minecraft.text.LiteralText;

public class TestController {
    public TestController() { super(); }

    public int clicks = 0;

    public void onButtonClick(UIView view, UIButton button) {
        clicks++;
        System.out.println("click");
        ((SpruceButtonWidget) button.rendered).setMessage(new LiteralText(String.format("I've been clicked %d times!", clicks)));
    }
}
