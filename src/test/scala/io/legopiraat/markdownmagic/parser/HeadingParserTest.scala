package io.legopiraat.markdownmagic.parser

import io.legopiraat.markdownmagic.UnitTestBase

class HeadingParserTest extends UnitTestBase {

  case class Sut() extends HeadingParser

  "A Heading" should {
    val sut = Sut()

    "heading level 1 when it has '#'" in {
      val line = "# This is a heading"

      val result = sut.parseHeading(line)

      result.headingLevel shouldBe 1
      result.txt shouldBe "This is a heading"
    }

    "heading level 4 when it has '####'" in {
      val line = "#### This is a heading 4"

      val result = sut.parseHeading(line)

      result.headingLevel shouldBe 4
      result.txt shouldBe "This is a heading 4"
    }
  }
}
