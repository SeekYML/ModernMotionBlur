package gg.seekyml.motionblur

import com.google.gson.JsonParser
import gg.essential.universal.ChatColor
import gg.seekyml.motionblur.command.MotionBlurCommand
import gg.seekyml.motionblur.config.MotionBlurConfig
import gg.seekyml.motionblur.updater.Updater
import net.minecraft.client.Minecraft
import net.minecraft.util.ChatComponentText
import net.minecraft.util.EnumChatFormatting
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.File

@Mod(
    modid = MotionBlur.ID,
    name = MotionBlur.NAME,
    version = MotionBlur.VERSION,
    modLanguageAdapter = "gg.essential.api.utils.KotlinAdapter"
)
object MotionBlur {

    const val NAME = "Modern Motion Blur"
    const val VERSION = "0.1.0"
    const val ID = "motionblur"

    val mc: Minecraft
        get() = Minecraft.getMinecraft()

    val LOG: Logger = LogManager.getLogger(NAME)

    lateinit var jarFile: File
        private set

    val modDir = File(File(Minecraft.getMinecraft().mcDataDir, "W-OVERFLOW"), NAME)
    val parser = JsonParser()

    @Mod.EventHandler
    fun onFMLPreInitialization(event: FMLPreInitializationEvent) {
        if (!modDir.exists()) modDir.mkdirs()
        jarFile = event.sourceFile
    }

    @Mod.EventHandler
    fun onInitialization(event: FMLInitializationEvent) {
        MotionBlurConfig.preload()
        MotionBlurCommand.register()
        Updater.update()
    }

    fun sendMessage(message: String) {
        if (mc.thePlayer == null)
            return
        val text =
            ChatComponentText(EnumChatFormatting.DARK_PURPLE.toString() + "[$NAME] " + ChatColor.RESET.toString() + " " + message)
        Minecraft.getMinecraft().thePlayer.addChatMessage(text)
    }


}
