package io.legopiraat.markdownmagic.parser

import io.legopiraat.markdownmagic.UnitTestBase
import io.legopiraat.markdownmagic.element.{Numeric, Dotted}

class ListParserTest extends UnitTestBase {

  case class Sut() extends ListParser

  "A list" should {

    val sut = Sut()

    "Be Numeric" when {
      "constructed from pattern '1. '" in {
        val line = "1. hello am an list entry"

        val result = sut.parseListEntry(line)

        result.listType shouldBe Numeric
        result.txt shouldBe "hello am an list entry"
      }

      "constructed from pattern '1) '" in {
        val line = "1) hello am an list entry"

        val result = sut.parseListEntry(line)

        result.listType shouldBe Numeric
        result.txt shouldBe "hello am an list entry"
      }
    }

    "Be Dotted" when {
      "constructed from pattern '- '" in {
        val line = "- hello am an list entry"

        val result = sut.parseListEntry(line)

        result.listType shouldBe Dotted
        result.txt shouldBe "hello am an list entry"
      }

      "constructed from pattern '* '" in {
        val line = "* hello am an list entry"

        val result = sut.parseListEntry(line)

        result.listType shouldBe Dotted
        result.txt shouldBe "hello am an list entry"
      }
    }
  }

}
