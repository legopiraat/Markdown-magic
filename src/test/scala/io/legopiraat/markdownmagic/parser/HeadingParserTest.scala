package io.legopiraat.markdownmagic.parser

import io.legopiraat.markdownmagic.UnitTestBase
import io.legopiraat.markdownmagic.element.MarkdownText

class HeadingParserTest extends UnitTestBase {

  // TODO: Fix a good matcher

  "A Heading" should {
    val sut = HeadingParser(LineParser())

    "heading level 1 when it has '#'" in {
      val line = "# This is a heading"

      val result = sut.parseHeading(line)

      result.headingLevel shouldBe 1
      result.text.line.head.asInstanceOf[MarkdownText].text shouldBe "This is a heading"
    }

    "heading level 4 when it has '####'" in {
      val line = "#### This is a heading 4"

      val result = sut.parseHeading(line)

      result.headingLevel shouldBe 4
      result.text.line.head.asInstanceOf[MarkdownText].text shouldBe "This is a heading 4"
    }

    "Heading level 1 containing a '#' in the middle of the line" in {
      val line = "# This is a heading with a # in the line"

      val result = sut.parseHeading(line)

      result.headingLevel shouldBe 1
      result.text.line.head.asInstanceOf[MarkdownText].text shouldBe "This is a heading with a # in the line"
    }

    "Heading level 1 without a space between the heading indicator and the start of the line" in {
      val line = "#This is a heading"

      val result = sut.parseHeading(line)

      result.headingLevel shouldBe 1
      result.text.line.head.asInstanceOf[MarkdownText].text shouldBe "This is a heading"
    }
  }
}
