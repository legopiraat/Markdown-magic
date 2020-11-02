package com.legopiraat.markdownmagic.parser

import com.legopiraat.markdownmagic.element.MarkdownHeading

trait HeadingParser {

  def parseHeading(line: String): MarkdownHeading = {
    val headingLevel = line.split("#")
    MarkdownHeading(line.replaceAll("#", "").trim, headingLevel.length - 1)
  }

}
