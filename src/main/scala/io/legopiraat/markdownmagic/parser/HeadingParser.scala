package io.legopiraat.markdownmagic.parser

import io.legopiraat.markdownmagic.element.MarkdownHeading

trait HeadingParser {

  private[this] val headingIndicator = '#'

  def parseHeading(line: String): MarkdownHeading = {
    val headingLevel = line.takeWhile(c => c.equals(headingIndicator)).length
    val remainingText = line.substring(headingLevel, line.length).trim

    MarkdownHeading(remainingText, headingLevel)
  }

}
