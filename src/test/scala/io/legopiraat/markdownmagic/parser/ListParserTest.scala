package io.legopiraat.markdownmagic.parser

import io.legopiraat.markdownmagic.UnitTestBase
import io.legopiraat.markdownmagic.element.{Dotted, MarkdownText, Numeric}

class ListParserTest extends UnitTestBase {

  // TODO: Fix a good matcher

  "A list" should {

    val sut = ListParser(LineParser())

    "Be Numeric" when {
      "constructed from pattern '1. '" in {
        val line = "1. hello am an list entry"

        val result = sut.parse(line)

        result.listType shouldBe Numeric
        result.text.line.head.asInstanceOf[MarkdownText].text shouldBe "hello am an list entry"
      }

      "constructed from pattern '1) '" in {
        val line = "1) hello am an list entry"

        val result = sut.parse(line)

        result.listType shouldBe Numeric
        result.text.line.head.asInstanceOf[MarkdownText].text shouldBe "hello am an list entry"
      }

      "constructed from pattern '1. ' with an inline '1.'" in {
        val line = "1. hello 1. list entry"

        val result = sut.parse(line)

        result.listType shouldBe Numeric
        result.text.line.head.asInstanceOf[MarkdownText].text shouldBe "hello 1. list entry"
      }
    }

    "Be Dotted" when {
      "constructed from pattern '- '" in {
        val line = "- hello am an list entry"

        val result = sut.parse(line)

        result.listType shouldBe Dotted
        result.text.line.head.asInstanceOf[MarkdownText].text shouldBe "hello am an list entry"
      }

      "constructed from pattern '* '" in {
        val line = "* hello am an list entry"

        val result = sut.parse(line)

        result.listType shouldBe Dotted
        result.text.line.head.asInstanceOf[MarkdownText].text shouldBe "hello am an list entry"
      }

      "constructed from pattern '* ' with an inline '*'" in {
        val line = "* hello am * an list entry"

        val result = sut.parse(line)

        result.listType shouldBe Dotted
        result.text.line.head.asInstanceOf[MarkdownText].text shouldBe "hello am * an list entry"
      }
    }
  }

}
