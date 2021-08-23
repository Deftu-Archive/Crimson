package xyz.matthewtgm.requisite.gui

import gg.essential.elementa.UIComponent
import xyz.matthewtgm.requisite.core.IRequisite
import xyz.matthewtgm.requisite.core.hud.HudElement
import java.util.*

class RequisiteHudMenuWindow(
    requisite: IRequisite
) : UIComponent() {

    private var dragging = false
    private var selected = Optional.empty<HudElement>()

    private var prevX: Int? = null
    private var prevY: Int? = null

    init {
        onMouseClick { event ->
            prevX = event.absoluteX.toInt()
            prevY = event.absoluteY.toInt()
            selected = requisite.getManager().hudRegistry.elements.stream().filter {
                it.isMouseInside(event.absoluteX.toInt(), event.absoluteY.toInt())
            }.findFirst()

            if (selected.isPresent) {
                dragging = true
                event.stopImmediatePropagation()
            }
        }
        onMouseRelease {
            dragging = false
        }
        onMouseDrag { mouseX, mouseY, _ ->
            if (selected.isPresent && dragging) {
                val element = selected.get()
                val position = element.positionSetting.get()
                position.setPosition(position.x + mouseX.toInt() - prevX!!, position.y + mouseY.toInt() - prevY!!)
                element.positionSetting.set(position)
            }

            prevX = mouseX.toInt()
            prevY = mouseY.toInt()
        }
    }

    fun draw(requisite: IRequisite, partialTicks: Float) {
        requisite.manager.hudRegistry.render(partialTicks)
    }

    fun close(requisite: IRequisite) {
        requisite.manager.hudRegistry.save(requisite.manager.configurationManager)
    }

}