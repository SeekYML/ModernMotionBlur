package gg.seekyml.motionblur.config

import gg.essential.api.EssentialAPI
import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.Property
import gg.essential.vigilance.data.PropertyType
import gg.seekyml.motionblur.MotionBlur
import gg.seekyml.motionblur.MotionBlur.NAME
import gg.seekyml.motionblur.MotionBlur.mc
import gg.seekyml.motionblur.updater.DownloadConfirmGui
import gg.seekyml.motionblur.updater.Updater
import java.io.File

object MotionBlurConfig : Vigilant(File(MotionBlur.modDir, "${MotionBlur.ID}.toml"), MotionBlur.NAME) {

    @Property(
        type = PropertyType.SWITCH,
        name = "Show Update Notification",
        description = "Show a notification when you start Minecraft informing you of new updates.",
        category = "Updater"
    )
    var showUpdate = true

    @Property(
        type = PropertyType.BUTTON,
        name = "Update Now",
        description = "Update by clicking the button.",
        category = "Updater"
    )
    private fun update() {
        if (Updater.shouldUpdate) EssentialAPI.getGuiUtil()
            .openScreen(DownloadConfirmGui(mc.currentScreen)) else EssentialAPI.getNotifications()
            .push(NAME, "No update had been detected at startup, and thus the update GUI has not been shown.")
    }

    @Property(
        type = PropertyType.SWITCH,
        name = "Enable Motion Blur",
        description = "Enable a trailing effect smoothing out your actions.",
        category = "General"
    )
    var enableBlur = false

    @Property(
        type = PropertyType.SELECTOR,
        name = "Enable Motion Blur",
        description = "Enable a trailing effect smoothing out your actions.",
        category = "General",
        options = ["New", "Alternate", "Old"]
    )
    var blurtype = 1

    @Property(
        type = PropertyType.PERCENT_SLIDER,
        name = "Blur Strength",
        description = "Determines the strength of the trailing effect",
        category = "General"
    )
    var blurAmount = 1.0F

    @Property(
        type = PropertyType.SWITCH,
        name = "Use Alternate Motion Blur",
        description = "Uses a different Blur type and may have a different impact on the framerate.",
        category = "General"
    )
    var alternateBlur = false

    init {
        initialize()
    }
}