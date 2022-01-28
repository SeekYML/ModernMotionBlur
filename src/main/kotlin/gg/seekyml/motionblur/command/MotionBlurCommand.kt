package gg.seekyml.motionblur.command

import gg.seekyml.motionblur.config.MotionBlurConfig
import gg.essential.api.EssentialAPI
import gg.essential.api.commands.Command
import gg.essential.api.commands.DefaultHandler
import gg.seekyml.motionblur.MotionBlur

object MotionBlurCommand : Command(MotionBlur.ID, true) {

    @DefaultHandler
    fun handle() {
        EssentialAPI.getGuiUtil().openScreen(MotionBlurConfig.gui())
    }
}