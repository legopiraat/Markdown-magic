package io.legopiraat.markdownmagic.parser

import io.legopiraat.markdownmagic.element.MarkdownHeading

trait HeadingParser {

  def parseHeading(line: String): MarkdownHeading = {
    val headingLevel = line.split("#")
    MarkdownHeading(line.replaceAll("#", "").trim, headingLevel.length - 1)
  }

}
