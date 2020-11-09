package io.legopiraat.markdownmagic.parser

import io.legopiraat.markdownmagic.UnitTestBase
import io.legopiraat.markdownmagic.element._

class LineParserTest extends UnitTestBase {

  "A line" should {

    val sut = LineParser()

    "aaa" in {
      val line = "Hello **am** *a* line and [hyperlink](www.google.com) and image ![image](image.png)."
      val expectedResult = List(
        MarkdownText("Hello", Clear),
        MarkdownText("am", Bold),
        MarkdownText("a", Italic),
        MarkdownText("line and", Clear),
        MarkdownHyperLink(MarkdownText("hyperlink", Clear), "www.google.com"),
        MarkdownText("and image", Clear),
        MarkdownImage(MarkdownText("image", Clear), "image.png"))

      val result = sut.parse(line)

      result shouldBe expectedResult
    }
  }
}
