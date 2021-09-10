package xyz.deftu.requisite.commands;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.deftu.mango.Strings;
import xyz.deftu.requisite.Requisite;
import xyz.deftu.requisite.core.data.ColourRGB;
import xyz.deftu.requisite.mixins.gui.GuiChatAccessor;
import xyz.deftu.requisite.mixins.gui.GuiChatInvoker;

import java.util.Arrays;

public class CommandCompleter {

    private final Requisite requisite;
    private final GuiChat chatGui;
    private boolean initialized;
    private String[] autocompleted;

    /* Rendering. */
    private int backgroundColour = new ColourRGB(0, 0, 0, 173).getRGB();
    private int xPos = -1;

    private int selected = 0;

    public CommandCompleter(GuiChat chatGui) {
        this.chatGui = chatGui;
        this.requisite = Requisite.getInstance();
    }

    public void initialize() {
        if (initialized)
            return;

        initialized = true;
    }

    public void draw(int mouseX, int mouseY, float partialTicks) {
        System.out.println(selected);
        System.out.println(autocompleted[selected]);

        int fontHeight = requisite.getEnhancedFontRenderer().getFontRenderer().FONT_HEIGHT;
        int xPos = this.xPos = (int) requisite.getMathHelper().lerp(this.xPos, 10, partialTicks / 6);
        int yPos = (chatGui.height - 14) - (fontHeight - 2);
        int width = requisite.getEnhancedFontRenderer().getWidth(Strings.getLongestString(autocompleted)) + 1;

        requisite.getGlHelper().startScissorBox(xPos, yPos, width, fontHeight + 2);

        requisite.getRenderHelper().drawRectEnhanced(xPos, yPos, width, fontHeight + 2, backgroundColour);
        requisite.getEnhancedFontRenderer().drawText(autocompleted[selected], xPos + 1, yPos - (fontHeight / 2));

        requisite.getGlHelper().endScissorBox();
    }

    public void keyTyped(char typedChar, int keyCode, CallbackInfo ci) {
        GuiTextField inputField = ((GuiChatAccessor) chatGui).getInputField();

        if (isShiftTabCombo(keyCode)) {
            ci.cancel();
            ((GuiChatInvoker) chatGui).invokeSendAutocompleteRequest(inputField.getText().substring(inputField.func_146197_a(-1, inputField.getCursorPosition(), false)).toLowerCase(), inputField.getText().substring(0, inputField.getCursorPosition()));
            initialize();
        } else if (initialized() && keyCode != Keyboard.KEY_ESCAPE) {
            for (String str : autocompleted) {
                if (doesStringStartWith(requisite.getStringHelper().removeFormattingCodes(str), inputField.getText().replaceFirst("/", ""))) {
                    selected = Arrays.asList(autocompleted).indexOf(str);
                    break;
                }
            }
        }
    }

    public void autocompleted(String[] autocompleted) {
        this.autocompleted = autocompleted;
    }

    public void close() {
        initialized = false;
    }

    public boolean initialized() {
        return initialized && autocompleted != null;
    }

    private boolean isShiftTabCombo(int keyCode) {
        return GuiScreen.isShiftKeyDown() && keyCode == Keyboard.KEY_TAB;
    }

    private boolean doesStringStartWith(String original, String region) {
        return region.regionMatches(true, 0, original, 0, original.length());
    }

}