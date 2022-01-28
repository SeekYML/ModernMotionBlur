package gg.seekyml.motionblur.mixin

import gg.seekyml.motionblur.config.MotionBlurConfig.blurAmount
import gg.seekyml.motionblur.config.MotionBlurConfig.blurtype
import gg.seekyml.motionblur.config.MotionBlurConfig.enableBlur
import net.minecraft.client.Minecraft
import org.lwjgl.opengl.GL11
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

@Mixin(Minecraft::class)
class MinecraftMixin {
    @Inject(
        method = ["runGameLoop"],
        at = [At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/Minecraft;updateDisplay()V",
            shift = At.Shift.BEFORE
        )]
    )
    private fun createMotionBlur(ci: CallbackInfo) {

        if (!enableBlur && blurtype != 1)
            return

        val strength = blurAmount.coerceAtMost(0.99f)
        GL11.glAccum(GL11.GL_MULT, strength)
        GL11.glAccum(GL11.GL_ACCUM, 1.0f - strength)
        GL11.glAccum(GL11.GL_RETURN, 1.0f)
    }

}