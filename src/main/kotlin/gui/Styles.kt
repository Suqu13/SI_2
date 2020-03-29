package gui

import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val page by cssclass()
        val customButton by cssclass()
        val customControllButton by cssclass()
        val element by cssclass()
        val customComboBox by cssclass()
        val white: Color = Color.WHITE
        val black: Color = Color.BLACK
        val orange: Color = c("#ffd31d")
        val darkGrey: Color = c("#323232")
    }

    init {
        s(page) {
            backgroundColor = multi(darkGrey)
        }
        s(element) {
            borderColor = multi(box(white))
            fontWeight = FontWeight.findByWeight(700)
            minHeight = 80.px
            minWidth = 80.px
            backgroundColor = multi(darkGrey)
            textFill = white
            fontSize = 30.px

        }
        s(customButton) {
            minWidth = 300.px
            backgroundColor = multi(white)
            borderColor = multi(box(white))
            borderRadius = multi(box(5.px))
            padding = box(10.px, 20.px)
            fontWeight = FontWeight.findByWeight(700)
            and(hover) {
                backgroundColor = multi(orange)
                borderColor = multi(
                    box(
                        orange
                    )
                )
            }
        }
        s(customControllButton) {
            minWidth = 100.px
            backgroundColor = multi(white)
            borderColor = multi(box(white))
            borderRadius = multi(box(5.px))
            padding = box(10.px, 20.px)
            fontWeight = FontWeight.findByWeight(700)
            and(hover) {
                backgroundColor = multi(orange)
                borderColor = multi(
                    box(
                        orange
                    )
                )
            }
        }
        s(customComboBox) {
            minWidth = 300.px
            backgroundColor = multi(white)
            borderColor = multi(box(white))
            borderRadius = multi(box(5.px))
            padding = box(10.px, 20.px)
            fontWeight = FontWeight.findByWeight(700)
            and(hover) {
                backgroundColor = multi(orange)
                borderColor = multi(
                    box(
                        orange
                    )
                )
            }
        }
    }
}