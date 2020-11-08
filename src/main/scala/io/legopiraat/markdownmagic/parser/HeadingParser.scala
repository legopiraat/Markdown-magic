package io.legopiraat.markdownmagic.parser

import io.legopiraat.markdownmagic.element.MarkdownHeading

trait HeadingParser {

  private[this] val headingIndicator = '#'

  def parseHeading(line: String): MarkdownHeading = {
    val headingLevel = determineHeadingLevel(line)
    val remainingText = getLineText(line, headingLevel)

    MarkdownHeading(remainingText, headingLevel)
  }

  def startsWithHeadingIndicator(line: String): Boolean = {
    line.startsWith(headingIndicator.toString)
  }

  private[this] def determineHeadingLevel(line: String): Int = {
    line.takeWhile(c => c.equals(headingIndicator)).length
  }

  private[this] def getLineText(line: String, offset: Int): String = {
    line.substring(offset, line.length).trim
  }
}
